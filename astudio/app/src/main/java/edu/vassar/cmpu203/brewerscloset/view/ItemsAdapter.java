package edu.vassar.cmpu203.brewerscloset.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import edu.vassar.cmpu203.brewerscloset.R;
import edu.vassar.cmpu203.brewerscloset.model.ItemCatalog;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsHolder> {
    Context context;
    ItemCatalog items;
    Boolean my_list;
    IHomeFeedView.Listener listener;

    public ItemsAdapter(Context context, ItemCatalog items, Boolean my_list, IHomeFeedView.Listener listener) {
        this.context = context;
        this.items = items;
        this.my_list = my_list;
        this.listener = listener;
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

        if (holder.interestButton != null) {
            holder.interestButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Snackbar.make(v, "Interest Button Clicked", Snackbar.LENGTH_LONG).show();
                    ItemsAdapter.this.listener.uponInterest();
                }
            });
        }

        if (holder.editButton != null
                && holder.deleteButton != null
                && holder.viewInterestButton != null) {
            holder.editButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Snackbar.make(v, "Edit Button Clicked", Snackbar.LENGTH_LONG).show();
                    ItemsAdapter.this.listener.uponEdit(items.getItem(position));
                }
            });
            holder.viewInterestButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Snackbar.make(v, "Interests Button Clicked", Snackbar.LENGTH_LONG).show();
                    ItemsAdapter.this.listener.uponViewInterest();
                }
            });

            holder.deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Snackbar.make(v, "Delete Button Clicked", Snackbar.LENGTH_LONG).show();
                    ItemsAdapter.this.listener.uponDelete(items.getItem(position));
                }
            });
        }
    }

        @Override
        public int getItemCount () {
            return items.length;
        }

}