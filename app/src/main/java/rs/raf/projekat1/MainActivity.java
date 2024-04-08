package rs.raf.projekat1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.ListFragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import rs.raf.projekat1.fragments.ListeFragment;
import rs.raf.projekat1.fragments.ProfilFragment;
import rs.raf.projekat1.fragments.StanjeFragment;
import rs.raf.projekat1.fragments.UnosFragment;
import rs.raf.projekat1.recycler.adapter.Adapterx;
import rs.raf.projekat1.recycler.differ.Differx;
import rs.raf.projekat1.viewmodels.PrihodiViewModel;
import rs.raf.projekat1.viewpager.MainPagerAdapter;
import rs.raf.projekat1.viewpager.PagerAdapter;

import android.annotation.SuppressLint;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init(){
        initViewPager();
        initNavigation();
    }


    private void initViewPager(){
        viewPager = findViewById(R.id.viewPagerNav);
        viewPager.setAdapter(new MainPagerAdapter(this, getSupportFragmentManager()));

    }

    private void initNavigation(){
        ((BottomNavigationView)findViewById(R.id.bottom_navigation)).setOnNavigationItemSelectedListener(item -> {
            switch(item.getItemId()){
                case R.id.navigation_stanje: viewPager.setCurrentItem(MainPagerAdapter.MFRAGMENT_1, false); break;
                case R.id.navigation_unos: viewPager.setCurrentItem(MainPagerAdapter.MFRAGMENT_2, false); break;
                case R.id.navigation_liste: viewPager.setCurrentItem(MainPagerAdapter.MFRAGMENT_3, false); break;
                case R.id.navigation_profil: viewPager.setCurrentItem(MainPagerAdapter.MFRAGMENT_4, false); break;
            }
            return true;
        });
    }


}