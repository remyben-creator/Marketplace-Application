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
import edu.vassar.cmpu203.brewerscloset.model.Catalog;
import edu.vassar.cmpu203.brewerscloset.model.Item;
import edu.vassar.cmpu203.brewerscloset.model.ItemCatalog;
import edu.vassar.cmpu203.brewerscloset.model.ItemInterestCatalog;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsHolder>{
    Context context;
    //possibly make into list
    //but will need to make an interest holder and interest adapter
    Catalog items;
    Boolean my_list;
    Boolean toInterest;
    IHomeFeedView.Listener listener;

    public ItemsAdapter(Context context, Catalog items, Boolean my_list, Boolean toInterest, IHomeFeedView.Listener listener) {
        this.context = context;
        this.items = items;
        this.my_list = my_list;
        this.toInterest = toInterest;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ItemsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (items instanceof ItemCatalog) {
            if (my_list) {
                return new ItemsHolder(LayoutInflater.from(context).inflate(R.layout.my_item_view, parent, false));
            } else if (toInterest) {
                return new ItemsHolder(LayoutInflater.from(context).inflate(R.layout.item_view_interest, parent, false));
            } else {
                return new ItemsHolder(LayoutInflater.from(context).inflate(R.layout.item_view, parent, false));
            }
        }
        return new ItemsHolder(LayoutInflater.from(context).inflate(R.layout.interests_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ItemsHolder holder, int position) {
        if (items instanceof ItemCatalog) {
            ItemCatalog itemCatalog = (ItemCatalog) items;
            holder.titleView.setText(itemCatalog.getItem(holder.getAdapterPosition()).getTitle());
            holder.descriptionView.setText(itemCatalog.getItem(holder.getAdapterPosition()).getDescription());
            holder.priceView.setText(itemCatalog.getItem(holder.getAdapterPosition()).getPriceString());
            holder.imageView.setImageBitmap(itemCatalog.getItem(holder.getAdapterPosition()).pictures);


            //for regular home feed
            if (holder.interestButton != null) {
                holder.interestButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ItemsAdapter.this.listener.uponInterest(itemCatalog.getItem(holder.getAdapterPosition()));
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
                        ItemsAdapter.this.listener.uponEdit(itemCatalog.getItem(holder.getAdapterPosition()));
                    }
                });
                holder.viewInterestButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        ItemsAdapter.this.listener.uponViewInterest(itemCatalog.getItem(holder.getAdapterPosition()));
                    }
                });

                holder.deleteButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ItemsAdapter.this.listener.uponDeleteItem(itemCatalog.getItem(holder.getAdapterPosition()));
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

                        ItemsAdapter.this.listener.uponConfirm(itemCatalog.getItem(holder.getAdapterPosition()), interestStr);
                    }
                });

            }
        }
        if (items instanceof ItemInterestCatalog) {
            ItemInterestCatalog interestCatalog = (ItemInterestCatalog) items;
            String userId = interestCatalog.getItem(position).getUser();
            holder.userEmailView.setText(ItemsAdapter.this.listener.getSomeUser(userId).email);
            holder.userInterestView.setText(interestCatalog.getItem(position).getInterest());

            holder.deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //this needs to make sure it deletes an interest in this scenario
                    ItemsAdapter.this.listener.uponDeleteInterest(interestCatalog, holder.getAdapterPosition());

                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return items.getLength();
    }
}
