package android.example.rickandmorty;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.CharacterViewHolder> {

    private Context mContext;
    private List<Person> personList;
    private RecyclerViewClickListener listener;

    public PersonAdapter(Context mContext, List<Person> personList, RecyclerViewClickListener listener) {
        this.mContext = mContext;
        this.personList = personList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public PersonAdapter.CharacterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        v = inflater.inflate(R.layout.list_rv,parent,false);
        return new CharacterViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CharacterViewHolder holder, int position) {
        holder.textViewName.setText(personList.get(position).getName());
        holder.textViewStatus.setText(personList.get(position).getStatus());
        holder.textViewSpecies.setText(personList.get(position).getSpecies());
        holder.textViewGender.setText(personList.get(position).getGender());

        Glide.with(mContext)
                .load(personList.get(position).getImage())
                .into(holder.imageViewCharactor);
    }

    @Override
    public int getItemCount() {
        return personList.size();
    }

    public interface RecyclerViewClickListener{
        void onClick(View v, int position);
    }

    public //static
    class CharacterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView imageViewCharactor;
        private TextView textViewName, textViewStatus, textViewSpecies, textViewGender;
        public CharacterViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewCharactor = itemView.findViewById(R.id.imageViewCharacter);
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewStatus = itemView.findViewById(R.id.textViewStatus);
            textViewSpecies = itemView.findViewById(R.id.textViewSpecies);
            textViewGender = itemView.findViewById(R.id.textViewGender);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onClick(v ,getAdapterPosition());
        }
    }
}
