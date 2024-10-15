package com.example.stars.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.app.AlertDialog;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.stars.beans.Star;
import com.example.stars.R;
import com.example.stars.service.StarService;

import java.util.ArrayList;
import java.util.List;
import android.widget.Filter;
import android.widget.Filterable;

public class StarAdapter extends RecyclerView.Adapter<StarAdapter.StarViewHolder> implements Filterable {

    private static final String TAG = "StarAdapter";
    private List<Star> stars; 
    private List<Star> starsFiltered; 
    private Context context; 
    private NewFilter mFilter; 

   
    public StarAdapter(Context context, List<Star> stars) {
        this.stars = stars;
        this.starsFiltered = new ArrayList<>(stars);
        this.context = context;
        this.mFilter = new NewFilter(this);
    }

    @NonNull
    @Override
    public StarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.star_item, parent, false);
        return new StarViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StarViewHolder holder, int position) {
        
        Glide.with(context)
                .asBitmap()
                .load(starsFiltered.get(position).getImg())
                .apply(new RequestOptions().override(100, 100))
                .into(holder.img);

       
        holder.name.setText(starsFiltered.get(position).getName().toUpperCase());
        holder.stars.setRating(starsFiltered.get(position).getStar());
        holder.idss.setText(String.valueOf(starsFiltered.get(position).getId()));

        
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showEditPopup(starsFiltered.get(position), holder);
            }
        });
    }

    @Override
    public int getItemCount() {
        return starsFiltered != null ? starsFiltered.size() : 0;
    }

   
    @Override
    public Filter getFilter() {
        return mFilter;
    }

   
    public class StarViewHolder extends RecyclerView.ViewHolder {
        TextView idss, name;
        ImageView img;
        RatingBar stars;
        RelativeLayout parent;

        public StarViewHolder(@NonNull View itemView) {
            super(itemView);
            idss = itemView.findViewById(R.id.ids);
            img = itemView.findViewById(R.id.img);
            name = itemView.findViewById(R.id.name);
            stars = itemView.findViewById(R.id.stars);
            parent = itemView.findViewById(R.id.parent);
        }
    }

    
    private void showEditPopup(Star star, StarViewHolder holder) {
        View popup = LayoutInflater.from(context).inflate(R.layout.star_edit_item, null, false);
        ImageView img = popup.findViewById(R.id.img);
        RatingBar bar = popup.findViewById(R.id.ratingBar);
        TextView idss = popup.findViewById(R.id.idss);

     
        Bitmap bitmap = ((BitmapDrawable) holder.img.getDrawable()).getBitmap();
        img.setImageBitmap(bitmap);
        bar.setRating(star.getStar());
        idss.setText(String.valueOf(star.getId()));

               AlertDialog dialog = new AlertDialog.Builder(context)
                .setTitle("Modifier la note")
                .setMessage("Donner une note entre 1 et 5 :")
                .setView(popup)
                .setPositiveButton("Valider", (dialog1, which) -> {
                    float newRating = bar.getRating();
                    star.setStar(newRating);
                    StarService.getInstance().update(star);
                    notifyItemChanged(holder.getAdapterPosition());
                })
                .setNegativeButton("Annuler", null)
                .create();

        dialog.show();
    }

   
    private class NewFilter extends Filter {
        private final StarAdapter mAdapter;

        public NewFilter(StarAdapter adapter) {
            this.mAdapter = adapter;
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Star> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(stars);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (Star star : stars) {
                    if (star.getName().toLowerCase().contains(filterPattern)) {
                        filteredList.add(star);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;
            results.count = filteredList.size();
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            starsFiltered.clear();
            starsFiltered.addAll((List<Star>) results.values);
            mAdapter.notifyDataSetChanged();
        }
    }
}
