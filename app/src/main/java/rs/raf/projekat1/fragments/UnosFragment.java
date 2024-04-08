package rs.raf.projekat1.fragments;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import rs.raf.projekat1.R;
import rs.raf.projekat1.models.Prihod;
import rs.raf.projekat1.models.Rashod;
import rs.raf.projekat1.viewmodels.PrihodiViewModel;
import rs.raf.projekat1.viewmodels.RashodiViewModel;

public class UnosFragment extends Fragment {

    private PrihodiViewModel prihodiViewModel;
    private RashodiViewModel rashodiViewModel;

    private Button btnNew;
    private EditText editNaziv;
    private EditText editSuma;
    private CheckBox checkBox;
    private ImageView micView;
    private ImageView micViewPlay;
    private EditText textArea;
    private Spinner spinner;

    private MediaRecorder mediaRecorder;
    private final int PERMISSION_ALL = 1;
    private final String[] PERMISSIONS = {
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    public static File file;

    public UnosFragment(){
        super(R.layout.activity_unos_fragment);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        initListeners();
    }

    private void initView(){
        prihodiViewModel = new ViewModelProvider(getActivity()).get(PrihodiViewModel.class);
        rashodiViewModel = new ViewModelProvider(getActivity()).get(RashodiViewModel.class);
        spinner = getActivity().findViewById(R.id.spinner_1);
        btnNew = getActivity().findViewById(R.id.btn_unos_prihoda);
        editNaziv = getActivity().findViewById(R.id.editTextUnosFrag);
        editSuma = getActivity().findViewById(R.id.editTextKolicinaFrag);
        checkBox = getActivity().findViewById(R.id.check_box1);
        micView = getActivity().findViewById(R.id.audio_1);
        textArea = getActivity().findViewById(R.id.text_area);
        micViewPlay = getActivity().findViewById(R.id.audio_play);
    }

    private void initListeners(){
        btnNew.setOnClickListener(v -> {
            if(editNaziv.getText().toString().equals("")){
                Toast.makeText(getContext(), "Obavezno polje naslov!", Toast.LENGTH_SHORT).show();
            }else if(editSuma.getText().toString().equals("")){
                Toast.makeText(getContext(), "Obavezno polje kolicina!", Toast.LENGTH_SHORT).show();
            } else{
                if(spinner.getSelectedItemId()==0){
                    Prihod prihod = new Prihod(PrihodiViewModel.getId(),editNaziv.getText().toString(), Integer.parseInt(editSuma.getText().toString()));
                    if(checkBox.isChecked()){
                        prihod.setAudioChecked(true);

                    } else {
                        prihod.setOpis(textArea.getText().toString());
                    }
                    prihodiViewModel.addPrihod(prihod);
                } else{
                    Rashod rashod = new Rashod(RashodiViewModel.getId(), editNaziv.getText().toString(),Integer.parseInt(editSuma.getText().toString()));
                    if(checkBox.isChecked()){
                        rashod.setAudioChecked(true);
                    }else{
                        rashod.setOpis(textArea.getText().toString());
                    }
                    rashodiViewModel.addRashod(rashod);
                }

                editNaziv.setText("");
                editSuma.setText("");
                textArea.setText("");
                Toast.makeText(getContext(), "Uspesno ste dodali item!", Toast.LENGTH_SHORT).show();

            }
        });

        checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked){
                if(hasPermission(getContext(), PERMISSIONS)){
                    textArea.setVisibility(View.INVISIBLE);
                    micView.setVisibility(View.VISIBLE);
                    init();
                } else requestPermissions(PERMISSIONS, PERMISSION_ALL);
            } else {
                textArea.setVisibility(View.VISIBLE);
                micView.setVisibility(View.INVISIBLE);
            }
        });

    }

    private boolean hasPermission(Context context, String... permissions){
        if(context!=null && permissions!=null){
            for(String permission: permissions){
                if(ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED){
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissionsList, @NonNull int[] grantResults) {
        if(requestCode==PERMISSION_ALL){
            if(grantResults.length>0){
                StringBuilder permissionsDenied = new StringBuilder();
                for(int i=0;i<grantResults.length;i++){
                    if(grantResults[i]==PackageManager.PERMISSION_DENIED){
                        permissionsDenied.append("\n").append(permissionsList[i]);
                    }
                }
                if(permissionsDenied.toString().length()==0){
                    textArea.setVisibility(View.INVISIBLE);
                    micView.setVisibility(View.VISIBLE);
                    init();
                }else{
                    Toast.makeText(getContext(), "Missing permissions!" + permissionsDenied.toString(), Toast.LENGTH_SHORT).show();
                    checkBox.setChecked(false);
                    //finish
                }
            }
        }
    }

    private void init(){
        File folder = new File(getActivity().getFilesDir(), "sounds");
        if(!folder.exists()) folder.mkdir();
        file = new File(folder,"record.3gp");
        initMic();
    }

    private void initMediaRecord(File file){
        mediaRecorder = new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mediaRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
        mediaRecorder.setOutputFile(file);
    }

    private void initMic(){
        micView.setOnClickListener(v -> {
            try {
                micView.setVisibility(View.GONE);
                micViewPlay.setVisibility(View.VISIBLE);
                initMediaRecord(file);
                mediaRecorder.prepare();
                mediaRecorder.start();
            }catch(IOException e){
                e.printStackTrace();
            }
        });

        micViewPlay.setOnClickListener(v -> {
                micView.setVisibility(View.VISIBLE);
                micViewPlay.setVisibility(View.GONE);
                mediaRecorder.stop();
                mediaRecorder.release();
                mediaRecorder=null;
        });

    }

}
