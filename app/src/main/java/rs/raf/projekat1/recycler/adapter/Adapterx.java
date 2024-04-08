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
import rs.raf.projekat1.models.Prihod;

public class Adapterx extends ListAdapter<Prihod, Adapterx.ViewHolder> {

    private Function<Prihod, Void> onPrihodClicked;

    public Adapterx(@NonNull DiffUtil.ItemCallback<Prihod> diffCallback, Function<Prihod, Void> onPrihodClicked) {
        super(diffCallback);
        this.onPrihodClicked=onPrihodClicked;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.prihodi_item, parent,false);
        return new ViewHolder(view, parent.getContext(), pos -> {
            Prihod prihod = getItem(pos);
            onPrihodClicked.apply(prihod);
            return null;
        });
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Prihod prihod = getItem(position);
        holder.bind(prihod);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final Context context;

        public ViewHolder(@NonNull View itemView, Context context, Function<Integer, Void> onItemClicked) {
            super(itemView);
            this.context = context;
            ImageView imgViewDelete = itemView.findViewById(R.id.imageViewDelete);
            imgViewDelete.setOnClickListener(v->{
                onItemClicked.apply(getAdapterPosition());

            });

            ImageView imgViewEdit = itemView.findViewById(R.id.imageViewEdit);

            imgViewEdit.setOnClickListener(v -> {
                Prihod prihod = getItem(getAdapterPosition());
                Intent i = new Intent(context, EditPrihodActivity.class);
                i.putExtra("naslov", prihod.getNaslov());
                i.putExtra("suma",String.valueOf(prihod.getSuma()));
                i.putExtra("opis",prihod.getOpis());
                i.putExtra("prihod",prihod);
                context.startActivity(i);
            });

            itemView.setOnClickListener(v -> {
                Prihod prihod = getItem(getAdapterPosition());
                Intent i = new Intent(context, ShowPrihodActivity.class);
                i.putExtra("naslov",prihod.getNaslov());
                i.putExtra("suma",String.valueOf(prihod.getSuma()));
                i.putExtra("opis",prihod.getOpis());
                i.putExtra("audio", prihod.isAudioChecked());

                context.startActivity(i);
            });

        }

        public void bind(Prihod prihod){
            ((TextView)itemView.findViewById(R.id.textViewNaslov)).setText(prihod.getNaslov());
            ((TextView)itemView.findViewById(R.id.textViewSuma)).setText(String.valueOf(prihod.getSuma()));
        }

    }

}
