package rs.raf.projekat1.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import rs.raf.projekat1.R;
import rs.raf.projekat1.viewmodels.PrihodiViewModel;
import rs.raf.projekat1.viewmodels.RashodiViewModel;

public class StanjeFragment extends Fragment {

    private TextView prihodiSuma;
    private TextView rashodiSuma;
    private TextView razlikaSuma;

    private PrihodiViewModel prihodiViewModel;
    private RashodiViewModel rashodiViewModel;

    public StanjeFragment(){
        super(R.layout.activity_stanje_fragment);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    private void initView(){
        prihodiViewModel = new ViewModelProvider(getActivity()).get(PrihodiViewModel.class);
        rashodiViewModel = new ViewModelProvider(getActivity()).get(RashodiViewModel.class);
        prihodiSuma = getActivity().findViewById(R.id.text_view_prihod_broj);
        prihodiSuma.setText(prihodiViewModel.getUkupnoPrihodi() +"");
        rashodiSuma = getActivity().findViewById(R.id.text_view_rashod_broj);
        rashodiSuma.setText(rashodiViewModel.getUkupnoRashodi()+"");
        razlikaSuma = getActivity().findViewById(R.id.text_view_razlika_broj);
        int razlika = prihodiViewModel.getUkupnoPrihodi() - rashodiViewModel.getUkupnoRashodi();
        if(razlika>0){
            razlikaSuma.setTextColor(Color.GREEN);
        } else if(razlika<0){
            razlikaSuma.setTextColor(Color.RED);
        }
        razlikaSuma.setText(razlika +  "");
    }

}
