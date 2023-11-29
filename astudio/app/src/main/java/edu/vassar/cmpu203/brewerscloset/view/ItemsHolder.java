package edu.vassar.cmpu203.brewerscloset.view;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import edu.vassar.cmpu203.brewerscloset.R;

public class ItemsHolder extends RecyclerView.ViewHolder {

    TextView titleView, descriptionView, priceView;
    Button editButton, deleteButton, viewInterestButton, interestButton;

    public ItemsHolder(@NonNull View itemView) {
        super(itemView);
        this.titleView = itemView.findViewById(R.id.itemTitle);
        this.descriptionView = itemView.findViewById(R.id.itemDescription);
        this.priceView = itemView.findViewById(R.id.itemPrice);

        if (itemView.findViewById(R.id.interestButton) != null) {
            this.interestButton = itemView.findViewById(R.id.interestButton);
            this.interestButton.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    Snackbar.make(v, "Interest Button Clicked", Snackbar.LENGTH_LONG).show();
                }
            });
        }

        if (itemView.findViewById(R.id.editButton) != null
                && itemView.findViewById(R.id.deleteButton) != null
                    && itemView.findViewById(R.id.viewInterestButton) != null) {
            this.editButton = itemView.findViewById(R.id.editButton);
            this.deleteButton = itemView.findViewById(R.id.deleteButton);
            this.viewInterestButton = itemView.findViewById(R.id.viewInterestButton);

            this.editButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Snackbar.make(v, "Edit Button Clicked", Snackbar.LENGTH_LONG).show();
                }
            });

            this.deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Snackbar.make(v, "Delete Button Clicked", Snackbar.LENGTH_LONG).show();
                }
            });

        }
    }
}