package edu.vassar.cmpu203.vassarmarketplace.view;

import android.media.Image;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.WindowDecorActionBar;
import androidx.recyclerview.widget.RecyclerView;

import edu.vassar.cmpu203.vassarmarketplace.R;

public class MyViewHolder extends RecyclerView.ViewHolder {

    TextView titleView, descriptionView, priceView;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        View titleView = itemView.findViewById(R.id.itemTitle);
        View descriptionView = itemView.findViewById(R.id.itemDescription);
        View priceView = itemView.findViewById(R.id.itemPrice);
        //picsView = ;
        //sellerView =
    }
}