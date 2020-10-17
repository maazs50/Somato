package com.example.somato.categories.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import com.example.somato.R;
import com.example.somato.categories.model.CategoryFields;
import com.example.somato.categories.view.CategoryActivity;

import java.util.List;
import java.util.zip.Inflater;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyHolder> {
    private final String TAG = CategoryAdapter.class.getSimpleName();
    private Context mcontext;
    private Inflater inflater;
    private List<CategoryFields> list;
    private int selectedPosition = 0;
    private CatListener catListener;
    public CategoryAdapter(List<CategoryFields> list, Context context) {
        this.list = list;
        this.mcontext = context;
        selectedPosition= CategoryActivity.oldPosition;

        try{
            this.catListener = (CatListener) context;
        }catch (Exception e){

        }
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.app_category_item, parent, false);
        return new MyHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyHolder holder, final int position) {


        holder.textView.setText(list.get(position).getName());

        if (selectedPosition == position) {
            holder.imageView.setVisibility(View.VISIBLE);
            holder.cardView.setCardBackgroundColor(Color.parseColor("#fafafa"));
            holder.mLanguageLayout.setVisibility(View.VISIBLE);
        } else {
            holder.imageView.setVisibility(View.INVISIBLE);
            holder.cardView.setCardBackgroundColor(Color.parseColor("#fafafa"));
        }
        holder.linearLayout.setOnClickListener(view -> {
            selectedPosition = position;
            int catId = list.get(selectedPosition).getId();
            String catName = String.valueOf(list.get(selectedPosition).getName());

            try {
                this.catListener = (CatListener) mcontext;
                catListener.getCategory(catName, catId,selectedPosition);
                notifyDataSetChanged();
            } catch (ClassCastException ex) {
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageView;
        LinearLayout linearLayout;
        CardView cardView;
        LinearLayoutCompat mLanguageLayout;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tv_category);
            imageView = itemView.findViewById(R.id.imageView);
            linearLayout = itemView.findViewById(R.id.LinearLangLayout);
            cardView = itemView.findViewById(R.id.cardView);
            mLanguageLayout = itemView.findViewById(R.id.app_language_layout);
        }
    }

    public interface CatListener {
        void getCategory(String name, int id, int langPos);
    }

}
