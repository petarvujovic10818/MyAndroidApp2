package rs.raf.projekat1;

import androidx.appcompat.app.AppCompatActivity;
import rs.raf.projekat1.viewpager.MainPagerAdapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sharedPreferences= getSharedPreferences(getPackageName(), Context.MODE_PRIVATE);
        String str = sharedPreferences.getString(LogInActivity.KEY_IME, "error");
        Intent i;
        if(str.equals("error")){
            i = new Intent(this, LogInActivity.class);
        } else {
            i = new Intent(this, MainActivity.class);
        }
        startActivity(i);
        finish();
    }
}