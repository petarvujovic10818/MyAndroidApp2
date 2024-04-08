package rs.raf.projekat1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import rs.raf.projekat1.fragments.UnosFragment;
import rs.raf.projekat1.models.Prihod;
import rs.raf.projekat1.recycler.adapter.Adapterx;
import rs.raf.projekat1.recycler.differ.Differx;
import rs.raf.projekat1.viewmodels.PrihodiViewModel;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditPrihodActivity extends AppCompatActivity {

    private Button btnOdustani;
    private Button btnIzmeni;

    private EditText editNaslov;
    private EditText editKolicina;
    private EditText editOpis;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_prihod);
        init();
    }

    private void init(){
        initView();
        initListeners();
    }

    private void initView(){
        btnOdustani = findViewById(R.id.xoo9);
        btnIzmeni = findViewById(R.id.xoo10);

        editNaslov = findViewById(R.id.xoo6);
        editKolicina = findViewById(R.id.xoo7);
        editOpis = findViewById(R.id.xoo8);

        Bundle bundle = getIntent().getExtras();
        String data = bundle.getString("naslov");
        String data2 = bundle.getString("suma");
        String data3 = bundle.getString("opis");

        Intent i = getIntent();
        Prihod pr = (Prihod) i.getSerializableExtra("prihod");
        editNaslov.setText(pr.getNaslov());
        editKolicina.setText(String.valueOf(pr.getSuma()));
        editOpis.setText(pr.getOpis());

       // editNaslov.setText(data);
        //editKolicina.setText(data2);
        //editOpis.setText(data3);

    }

    private void initListeners(){
        btnOdustani.setOnClickListener(v -> {
            finish();
        });

        btnIzmeni.setOnClickListener(v -> {
            if(editNaslov.getText().toString().equals("")){
                Toast.makeText(this, "Naslov ne moze biti empty!", Toast.LENGTH_SHORT).show();
            } else if(editKolicina.getText().toString().equals("")){
                Toast.makeText(this, "Kolicina ne moze biti empty!", Toast.LENGTH_SHORT).show();
            } else{
                Intent i = getIntent();
                Prihod pr = (Prihod) i.getSerializableExtra("prihod");
                pr.setNaslov(editNaslov.getText().toString());
                pr.setSuma(Integer.parseInt(editKolicina.getText().toString()));
                pr.setOpis(editOpis.getText().toString());

                finish();
            }
        });

    }


}