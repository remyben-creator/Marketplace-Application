package edu.vassar.cmpu203.brewerscloset.view;

import android.content.Context;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import edu.vassar.cmpu203.brewerscloset.R;
import edu.vassar.cmpu203.brewerscloset.model.ItemCatalog;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsHolder>{
    Context context;
    ItemCatalog items;
    Boolean my_list;
    Boolean toInterest;
    IHomeFeedView.Listener listener;

    public ItemsAdapter(Context context, ItemCatalog items, Boolean my_list, Boolean toInterest, IHomeFeedView.Listener listener) {
        this.context = context;
        this.items = items;
        this.my_list = my_list;
        this.toInterest = toInterest;
        this.listener = listener;
        this.toInterest = toInterest;
    }

    @NonNull
    @Override
    public ItemsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (my_list) {
            return new ItemsHolder(LayoutInflater.from(context).inflate(R.layout.my_item_view,parent,false));}
        if (toInterest) {
            return new ItemsHolder(LayoutInflater.from(context).inflate(R.layout.item_view_interest,parent,false));
        }
        return new ItemsHolder(LayoutInflater.from(context).inflate(R.layout.item_view,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ItemsHolder holder, int position) {

        holder.titleView.setText(items.getItem(position).getTitle());
        holder.descriptionView.setText(items.getItem(position).getDescription());
        holder.priceView.setText(items.getItem(position).getPriceString());

        //for regular home feed
        if (holder.interestButton != null) {
            holder.interestButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Snackbar.make(v, "Interest Button Clicked", Snackbar.LENGTH_LONG).show();
                    ItemsAdapter.this.listener.uponInterest(items.getItem(holder.getAdapterPosition()));
                }
            });
        }
        //for my items feed
        if (holder.editButton != null
                && holder.deleteButton != null
                && holder.viewInterestButton != null) {
            holder.editButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Snackbar.make(v, "Edit Button Clicked", Snackbar.LENGTH_LONG).show();
                    ItemsAdapter.this.listener.uponEdit(items.getItem(holder.getAdapterPosition()));
                }
            });
            holder.viewInterestButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Snackbar.make(v, "Interests Button Clicked", Snackbar.LENGTH_LONG).show();
                    //start here
                    //add item to param
                    //make the item list comments be what shows in the home feed
                    ItemsAdapter.this.listener.uponViewInterest();
                }
            });

            holder.deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Snackbar.make(v, "Delete Button Clicked", Snackbar.LENGTH_LONG).show();
                    ItemsAdapter.this.listener.uponDelete(items.getItem(holder.getAdapterPosition()));
                }
            });
        }
        //for to show interest form
        if (holder.backButton != null
                && holder.confirmButton != null
                && holder.interestBar != null) {
            holder.backButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ItemsAdapter.this.listener.uponHome();
                }
            });
            holder.confirmButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final Editable interestEditable = holder.interestBar.getText();
                    final String interestStr = interestEditable.toString();

                    if (interestStr.length() == 0) {
                        Snackbar.make(v, "Invalid: Please make sure all fields are filled", Snackbar.LENGTH_LONG).show();
                        return;
                    }

                    ItemsAdapter.this.listener.uponConfirm(items.getItem(holder.getAdapterPosition()), interestStr);
                }
            });

        }
    }

        @Override
        public int getItemCount () {
            return items.length;
        }

}