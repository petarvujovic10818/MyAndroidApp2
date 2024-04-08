package rs.raf.projekat1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LogInActivity extends AppCompatActivity {

    public static final String KEY_IME = "ime";
    public static final String KEY_PREZIME = "prezime";
    public static final String KEY_BANKA = "banka";

    private static final String LOGIN_PASSWORD = "password";

    private Button button;
    private EditText ime;
    private EditText prezime;
    private EditText banka;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        initViews();
        initListeners();
    }

    private void initViews(){
        button =findViewById(R.id.my_button);
        ime = findViewById(R.id.editIme);
        prezime = findViewById(R.id.editPrezime);
        banka = findViewById(R.id.editBanka);
        password = findViewById(R.id.editPassword);
    }

    private void initListeners(){
            button.setOnClickListener(v -> {
                if(ime.getText().toString().equals("")){
                    Toast.makeText(this, "Ime can't be empty!", Toast.LENGTH_SHORT).show();
                }else if(prezime.getText().toString().equals("")) {
                    Toast.makeText(this, "Prezime can't be empty!", Toast.LENGTH_SHORT).show();
                }
                else if(banka.getText().toString().equals("")) {
                        Toast.makeText(this, "Ime banke can't me empty!", Toast.LENGTH_SHORT).show();
                    }
                else if(password.getText().length()<5) {
                        Toast.makeText(this, "Password length must be minimum 5 characters!", Toast.LENGTH_SHORT).show();
                    }
                else if(!password.getText().toString().equals(LOGIN_PASSWORD)) {
                        Toast.makeText(this, "Wrong password!", Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent = new Intent(this, MainActivity.class);
                    SharedPreferences sharedPreferences = getSharedPreferences(getPackageName(), Context.MODE_PRIVATE);
                    sharedPreferences
                            .edit()
                            .putString(KEY_IME, String.valueOf(ime.getText()))
                            .apply();
                    sharedPreferences
                            .edit()
                            .putString(KEY_PREZIME, String.valueOf(prezime.getText()))
                            .apply();
                    sharedPreferences
                            .edit()
                            .putString(KEY_BANKA, String.valueOf(banka.getText()))
                            .apply();
                    startActivity(intent);
                }
            });
    }

}