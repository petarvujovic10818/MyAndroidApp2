package rs.raf.projekat1.fragments;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import rs.raf.projekat1.R;
import rs.raf.projekat1.recycler.adapter.Adapterx;
import rs.raf.projekat1.recycler.differ.Differx;
import rs.raf.projekat1.viewmodels.PrihodiViewModel;

public class PrihodiFragment extends Fragment {

    private PrihodiViewModel prihodiViewModel;
    private RecyclerView recyclerView;
    private Adapterx prihodiAdapter;

    public PrihodiFragment(){
        super(R.layout.activity_recycler_prihodi);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        prihodiViewModel = new ViewModelProvider(getActivity()).get(PrihodiViewModel.class);
        recyclerView = view.findViewById(R.id.listRv);
        init();
    }

    private void init() {
        initRecycler();
        initObservers();
    }

    private void initObservers(){
        prihodiViewModel.getPrihodi().observe(getActivity(), prihodi -> {
            prihodiAdapter.submitList(prihodi);
        });
    }

    private void initRecycler(){
        prihodiAdapter = new Adapterx(new Differx(), prihod -> {
            prihodiViewModel.removePrihod(prihod.getId());
            return null;
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(prihodiAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        prihodiViewModel.refresh();
        prihodiAdapter.notifyDataSetChanged();
    }

}
