package edu.vassar.cmpu203.vassarmarketplace.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import edu.vassar.cmpu203.vassarmarketplace.R;
import edu.vassar.cmpu203.vassarmarketplace.model.ItemCatalog;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {
    Context context;
    ItemCatalog items;

    public MyAdapter(Context context, ItemCatalog items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_view,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.titleView.setText(items.getItem(position).getTitle());
        holder.descriptionView.setText(items.getItem(position).getDescription());
        holder.priceView.setText(items.getItem(position).getPriceString());
        //for the seller
        //for the pics
    }

    @Override
    public int getItemCount() {
        return items.length;
    }

}