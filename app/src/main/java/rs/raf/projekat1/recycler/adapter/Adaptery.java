package rs.raf.projekat1.recycler.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.function.Function;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import rs.raf.projekat1.EditPrihodActivity;
import rs.raf.projekat1.R;
import rs.raf.projekat1.ShowPrihodActivity;
import rs.raf.projekat1.models.Rashod;

public class Adaptery extends ListAdapter<Rashod, Adaptery.ViewHolder> {

    private Function<Rashod, Void> onRashodClicked;

    public Adaptery(@NonNull DiffUtil.ItemCallback<Rashod> diffCallback, Function<Rashod, Void> onRashodClicked) {
        super(diffCallback);
        this.onRashodClicked=onRashodClicked;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rashodi_item, parent,false);
        return new ViewHolder(view, parent.getContext(), pos -> {
            Rashod rashod = getItem(pos);
            onRashodClicked.apply(rashod);
            return null;
        });
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Rashod rashod = getItem(position);
        holder.bind(rashod);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final Context context;

        public ViewHolder(@NonNull View itemView, Context context, Function<Integer, Void> onItemClicked) {
            super(itemView);
            this.context = context;
            ImageView imgViewDelete = itemView.findViewById(R.id.imageViewDelete2);
            imgViewDelete.setOnClickListener(v->{
                onItemClicked.apply(getAdapterPosition());

            });

            ImageView imgViewEdit = itemView.findViewById(R.id.imageViewEdit2);

            imgViewEdit.setOnClickListener(v -> {
                Intent i = new Intent(context, EditPrihodActivity.class);
                context.startActivity(i);
            });

            itemView.setOnClickListener(v -> {
                Rashod rashod = getItem(getAdapterPosition());
                Intent i = new Intent(context, ShowPrihodActivity.class);
                i.putExtra("naslov",rashod.getNaslov());
                i.putExtra("suma",String.valueOf(rashod.getSuma()));
                i.putExtra("opis",rashod.getOpis());
                i.putExtra("audio", rashod.isAudioChecked());
                context.startActivity(i);
            });

        }

        public void bind(Rashod rashod){
            ((TextView)itemView.findViewById(R.id.textViewNaslov2)).setText(rashod.getNaslov());
            ((TextView)itemView.findViewById(R.id.textViewSuma2)).setText(String.valueOf(rashod.getSuma()));
        }

    }


}
