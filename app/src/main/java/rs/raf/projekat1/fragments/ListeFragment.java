package rs.raf.projekat1.fragments;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import rs.raf.projekat1.R;
import rs.raf.projekat1.viewpager.PagerAdapter;

public class ListeFragment extends Fragment {

    private ViewPager viewPager;
    private TabLayout tabLayout;

    public ListeFragment(){
        super(R.layout.activity_liste_fragment);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViewPager();
        initTabs();
    }

    private void initViewPager(){
        viewPager = getActivity().findViewById(R.id.viewPager);
        viewPager.setAdapter(new PagerAdapter(getContext(), getActivity().getSupportFragmentManager()));
    }

    private void initTabs(){
        tabLayout = getActivity().findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);
    }


}
