package rs.raf.projekat1.recycler.differ;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import rs.raf.projekat1.models.Prihod;

public class Differx extends DiffUtil.ItemCallback<Prihod> {

    @Override
    public boolean areItemsTheSame(@NonNull Prihod oldItem, @NonNull Prihod newItem) {
        return oldItem.getId() == newItem.getId();
    }

    @Override
    public boolean areContentsTheSame(@NonNull Prihod oldItem, @NonNull Prihod newItem) {
        return oldItem.getNaslov().equals(newItem.getNaslov()) && oldItem.getSuma()==newItem.getSuma();
    }
}
