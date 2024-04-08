package rs.raf.projekat1.viewpager;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import rs.raf.projekat1.R;
import rs.raf.projekat1.fragments.PrihodiFragment;
import rs.raf.projekat1.fragments.RashodiFragment;

public class PagerAdapter extends FragmentPagerAdapter {

    private final int ITEM_COUNT = 2;
    public static final int FRAGMENT_1 = 0;
    public static final int FRAGMENT_2 = 1;

    private Context context;

    public PagerAdapter(Context context, @NonNull FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.context=context;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment;
        switch(position){
            case FRAGMENT_1: fragment = new PrihodiFragment(); break;
            case FRAGMENT_2: fragment = new RashodiFragment(); break;
            default: fragment = new RashodiFragment();
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return ITEM_COUNT;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch(position){
            case FRAGMENT_1: return context.getString(R.string.income);
            case FRAGMENT_2: return context.getString(R.string.expense);
            default: return "ERROR";
        }
    }

}
