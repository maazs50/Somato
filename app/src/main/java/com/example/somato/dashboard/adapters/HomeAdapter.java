package com.example.somato.dashboard.adapters;

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

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.somato.R;
import com.example.somato.categories.model.CategoryFields;
import com.example.somato.dashboard.model.Restaurant;
import com.example.somato.dashboard.model.Restaurant_;
import com.example.somato.dashboard.model.SearchedResponse;

import java.util.List;
import java.util.zip.Inflater;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyHolder> {
    private final String TAG = HomeAdapter.class.getSimpleName();
    private Context mcontext;
    private Inflater inflater;
    private List<Restaurant> list;
    private HonClickListener onClickListener;
    public HomeAdapter(List<Restaurant> list, Context context) {
        this.list = list;
        this.mcontext = context;
        try{
        this.onClickListener = (HonClickListener) context;
        }
        catch (Exception e){

        }

    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_list_item, parent, false);
        return new MyHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyHolder holder, final int position) {
        Restaurant_ restaurant = list.get(position).getRestaurant();
        holder.textView_Name.setText(restaurant.getName());
        Glide.with(mcontext).load(restaurant.getThumb()).apply(new RequestOptions().placeholder(R.drawable.image6)).into(holder.imageView);
        holder.itemView.setOnClickListener(view -> onClickListener.getRestaurantId(restaurant));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder{
        TextView textView_Name;
        ImageView imageView;


        public MyHolder(@NonNull View itemView) {
            super(itemView);
            textView_Name = itemView.findViewById(R.id.nameTextView);
            imageView = itemView.findViewById(R.id.thumbImageView);
        }

    }
    public interface HonClickListener{
        void getRestaurantId(Restaurant_ restaurant);
    }

}
