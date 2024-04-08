package rs.raf.projekat1.viewmodels;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import rs.raf.projekat1.models.Prihod;

public class PrihodiViewModel extends ViewModel {

    private final MutableLiveData<List<Prihod>> prihodi = new MutableLiveData<>();
    private final ArrayList<Prihod> prihodi_list = new ArrayList<>();

    private int ukupnoPrihodi=0;

    private static int id = 0;

    public PrihodiViewModel(){
        for(int i=0;i<10;i++){
            Prihod prihod = new Prihod(id++, "Prihod" + id, 100);
            prihodi_list.add(prihod);
            ukupnoPrihodi+=prihod.getSuma();
        }
        ArrayList<Prihod> listToSubmit = new ArrayList<>(prihodi_list);
        prihodi.setValue(listToSubmit);
    }

    public LiveData<List<Prihod>> getPrihodi() {
        return prihodi;
    }

    public void removePrihod(int id){
        prihodi_list.removeIf(e -> e.getId()==id);
        ArrayList<Prihod> listToSubmit = new ArrayList<>(prihodi_list);
        prihodi.setValue(listToSubmit);
        int novaVr=0;
        for(Prihod prihod: prihodi_list){
            novaVr+=prihod.getSuma();
        }
        ukupnoPrihodi=novaVr;
    }

    public int getUkupnoPrihodi(){
        return ukupnoPrihodi;
    }

    public static int getId() {
        return id++;
    }


    public ArrayList<Prihod> getPrihodi_list() {
        return prihodi_list;
    }

    public void addPrihod(Prihod prihod){
        prihodi_list.add(prihod);
        ukupnoPrihodi+=prihod.getSuma();
        ArrayList<Prihod> listToSubmit = new ArrayList<>(prihodi_list);
        prihodi.setValue(listToSubmit);
    }

    public void refresh(){
        ArrayList<Prihod> listToSubmit = new ArrayList<>(prihodi_list);
        prihodi.setValue(listToSubmit);
    }

}
