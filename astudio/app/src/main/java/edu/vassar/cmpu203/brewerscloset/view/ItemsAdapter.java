package edu.vassar.cmpu203.brewerscloset.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import edu.vassar.cmpu203.brewerscloset.R;
import edu.vassar.cmpu203.brewerscloset.model.ItemCatalog;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsHolder> {
    Context context;
    ItemCatalog items;
    Boolean my_list;

    public ItemsAdapter(Context context, ItemCatalog items, Boolean my_list) {
        this.context = context;
        this.items = items;
        this.my_list = my_list;
    }

    @NonNull
    @Override
    public ItemsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (my_list) {
            return new ItemsHolder(LayoutInflater.from(context).inflate(R.layout.my_item_view,parent,false));}
        return new ItemsHolder(LayoutInflater.from(context).inflate(R.layout.item_view,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ItemsHolder holder, int position) {

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