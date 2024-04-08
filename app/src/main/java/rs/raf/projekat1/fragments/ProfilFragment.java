package rs.raf.projekat1.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import rs.raf.projekat1.EditProfilActivity;
import rs.raf.projekat1.LogInActivity;
import rs.raf.projekat1.R;

public class ProfilFragment extends Fragment {

    private TextView ime_value;
    private TextView prezime_value;
    private TextView banka_value;

    private Button btn_odjava;
    private Button btn_izmeni;

    public ProfilFragment(){
        super(R.layout.activity_profil_fragment);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        setElements();
        initListeners();
    }

    @Override
    public void onResume() {
        super.onResume();
        setElements();
    }

    private void initView(){
        ime_value=getActivity().findViewById(R.id.ime_value);
        prezime_value=getActivity().findViewById(R.id.prezime_value);
        banka_value=getActivity().findViewById(R.id.banka_value);

        btn_izmeni = getActivity().findViewById(R.id.btn_izmeni);
        btn_odjava = getActivity().findViewById(R.id.btn_odjava);
    }

    private void setElements(){
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(getActivity().getPackageName(), Context.MODE_PRIVATE);
        String ime = sharedPreferences.getString(LogInActivity.KEY_IME, "error");
        String prezime = sharedPreferences.getString(LogInActivity.KEY_PREZIME, "error");
        String banka = sharedPreferences.getString(LogInActivity.KEY_BANKA, "error");
        ime_value.setText(ime);
        prezime_value.setText(prezime);
        banka_value.setText(banka);
    }

    private void initListeners(){
        btn_izmeni.setOnClickListener(v -> {
            Intent i = new Intent(getContext(), EditProfilActivity.class);
            startActivity(i);
        });

        btn_odjava.setOnClickListener(v -> {
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences(getActivity().getPackageName(), Context.MODE_PRIVATE);
            sharedPreferences
                    .edit()
                    .putString(LogInActivity.KEY_IME, "")
                    .apply();

            sharedPreferences
                    .edit()
                    .putString(LogInActivity.KEY_PREZIME, "")
                    .apply();

            sharedPreferences
                    .edit()
                    .putString(LogInActivity.KEY_BANKA, "")
                    .apply();

            Intent i = new Intent(getContext(), LogInActivity.class);
            startActivity(i);
            getActivity().finish();
        });
    }

}
