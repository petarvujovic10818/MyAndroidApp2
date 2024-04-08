package rs.raf.projekat1.viewpager;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import rs.raf.projekat1.R;
import rs.raf.projekat1.fragments.ListeFragment;
import rs.raf.projekat1.fragments.ProfilFragment;
import rs.raf.projekat1.fragments.StanjeFragment;
import rs.raf.projekat1.fragments.UnosFragment;

public class MainPagerAdapter extends FragmentPagerAdapter {

    private final int ITEM_CNT = 4;

    public static final int MFRAGMENT_1 = 0;
    public static final int MFRAGMENT_2 = 1;
    public static final int MFRAGMENT_3 = 2;
    public static final int MFRAGMENT_4 = 3;

    private Context context;

    public MainPagerAdapter(Context context, @NonNull FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.context=context;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment;
        switch(position){
            case MFRAGMENT_1: fragment = new StanjeFragment(); break;
            case MFRAGMENT_2: fragment = new UnosFragment(); break;
            case MFRAGMENT_3: fragment = new ListeFragment(); break;
            case MFRAGMENT_4: fragment = new ProfilFragment(); break;
            default: fragment = new StanjeFragment();
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return ITEM_CNT;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch(position){
            case MFRAGMENT_1: return context.getString(R.string.balance);
            case MFRAGMENT_2: return context.getString(R.string.enter);
            case MFRAGMENT_3: return context.getString(R.string.lists);
            case MFRAGMENT_4: return context.getString(R.string.profile);
            default: return "ERROR";
        }
    }
}
