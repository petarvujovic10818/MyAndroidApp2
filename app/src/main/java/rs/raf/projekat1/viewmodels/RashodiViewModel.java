package rs.raf.projekat1.viewmodels;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import rs.raf.projekat1.models.Rashod;

public class RashodiViewModel extends ViewModel {

    private final MutableLiveData<List<Rashod>> rashodi = new MutableLiveData<>();
    private final ArrayList<Rashod> rashodi_list = new ArrayList<>();
    private int ukupnoRashodi = 0;

    private static int id=0;

    public RashodiViewModel(){
        for(int i=0;i<10;i++){
            Rashod rashod = new Rashod(id++, "Rashod" + id, 100);
            rashodi_list.add(rashod);
            ukupnoRashodi+=rashod.getSuma();
        }
        ArrayList<Rashod> listToSubmit = new ArrayList<>(rashodi_list);
        rashodi.setValue(listToSubmit);
    }

    public LiveData<List<Rashod>> getRashodi() {
        return rashodi;
    }

    public void removeRashod(int id){
        rashodi_list.removeIf(e -> e.getId()==id);
        ArrayList<Rashod> listToSubmit = new ArrayList<>(rashodi_list);
        rashodi.setValue(listToSubmit);
        int novaVr=0;
        for(Rashod rashod: rashodi_list){
            novaVr+=rashod.getSuma();
        }
        ukupnoRashodi=novaVr;
    }

    public int getUkupnoRashodi() {
        return ukupnoRashodi;
    }

    public static int getId() {
        return id;
    }

    public void addRashod(Rashod rashod){
        rashodi_list.add(rashod);
        ukupnoRashodi+=rashod.getSuma();
        ArrayList<Rashod> listToSubmit = new ArrayList<>(rashodi_list);
        rashodi.setValue(listToSubmit);
    }

}
