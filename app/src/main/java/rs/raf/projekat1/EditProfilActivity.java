package rs.raf.projekat1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditProfilActivity extends AppCompatActivity {

    private Button btnIzmeni;
    private Button btnCancel;

    private EditText editIme;
    private EditText editPrezime;
    private EditText editBanka;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profil);
        init();
    }

    private void init(){
        initView();
        initListeners();
    }

    private void initView(){
        btnIzmeni=findViewById(R.id.btn1);
        btnCancel=findViewById(R.id.btn2);

        editIme=findViewById(R.id.et_1);
        editPrezime = findViewById(R.id.et_2);
        editBanka = findViewById(R.id.et_3);

        SharedPreferences sharedPreferences = getSharedPreferences(getPackageName(), Context.MODE_PRIVATE);
        String ime = sharedPreferences.getString(LogInActivity.KEY_IME, "error");
        String prezime = sharedPreferences.getString(LogInActivity.KEY_PREZIME, "error");
        String banka = sharedPreferences.getString(LogInActivity.KEY_BANKA, "error");

        editIme.setText(ime);
        editPrezime.setText(prezime);
        editBanka.setText(banka);

    }

    private void initListeners(){
        btnCancel.setOnClickListener(v -> {
            finish();
        });

        btnIzmeni.setOnClickListener(v -> {
            if(editIme.getText().toString().equals("")){
                Toast.makeText(this, "Ime can't be empty!", Toast.LENGTH_SHORT).show();
            }else if(editPrezime.getText().toString().equals("")){
                Toast.makeText(this, "Prezime can't be empty!", Toast.LENGTH_SHORT).show();
            }else if(editBanka.getText().toString().equals("")){
                Toast.makeText(this, "Ime banke can't me empty!", Toast.LENGTH_SHORT).show();
            } else {
                SharedPreferences sharedPreferences = getSharedPreferences(getPackageName(), Context.MODE_PRIVATE);
                sharedPreferences
                        .edit()
                        .putString(LogInActivity.KEY_IME, String.valueOf(editIme.getText()))
                        .apply();

                sharedPreferences
                        .edit()
                        .putString(LogInActivity.KEY_PREZIME, String.valueOf(editPrezime.getText()))
                        .apply();

                sharedPreferences
                        .edit()
                        .putString(LogInActivity.KEY_BANKA, String.valueOf(editBanka.getText()))
                        .apply();
                finish();
            }
        });

    }

}