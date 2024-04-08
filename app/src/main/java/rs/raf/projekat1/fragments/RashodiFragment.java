package rs.raf.projekat1.fragments;

import android.os.Bundle;;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import rs.raf.projekat1.R;
import rs.raf.projekat1.recycler.adapter.Adaptery;
import rs.raf.projekat1.recycler.differ.Differy;
import rs.raf.projekat1.viewmodels.RashodiViewModel;


public class RashodiFragment extends Fragment {

    private RashodiViewModel rashodiViewModel;
    private RecyclerView recyclerView;
    private Adaptery rashodAdapter;

    public RashodiFragment(){
        super(R.layout.activity_recycler_rashodi);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rashodiViewModel = new ViewModelProvider(getActivity()).get(RashodiViewModel.class); //get context?
        recyclerView = view.findViewById(R.id.listRv2);
        init();
    }

    private void init() {
        initRecycler();
        initObservers();
    }


    private void initObservers(){
        rashodiViewModel.getRashodi().observe(getActivity(), rashodi -> {
            rashodAdapter.submitList(rashodi);
        });
    }

    private void initRecycler(){
        rashodAdapter = new Adaptery(new Differy(), rashod -> {
            rashodiViewModel.removeRashod(rashod.getId());
            return null;
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(rashodAdapter);
    }


}
