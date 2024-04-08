package rs.raf.projekat1.recycler.differ;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import rs.raf.projekat1.models.Rashod;

public class Differy extends DiffUtil.ItemCallback<Rashod> {

    @Override
    public boolean areItemsTheSame(@NonNull Rashod oldItem, @NonNull Rashod newItem) {
        return oldItem.getId() == newItem.getId();
    }

    @Override
    public boolean areContentsTheSame(@NonNull Rashod oldItem, @NonNull Rashod newItem) {
        return oldItem.getNaslov().equals(newItem.getNaslov()) && oldItem.getSuma()==newItem.getSuma();
    }
}
