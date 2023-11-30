package edu.vassar.cmpu203.brewerscloset.view;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import edu.vassar.cmpu203.brewerscloset.R;

public class ItemsHolder extends RecyclerView.ViewHolder {

    TextView titleView, descriptionView, priceView;
    Button editButton, deleteButton, viewInterestButton, interestButton, backButton, confirmButton;
    EditText interestBar;

    public ItemsHolder(@NonNull View itemView) {
        super(itemView);
        this.titleView = itemView.findViewById(R.id.itemTitle);
        this.descriptionView = itemView.findViewById(R.id.itemDescription);
        this.priceView = itemView.findViewById(R.id.itemPrice);

        this.editButton = itemView.findViewById(R.id.editButton);
        this.deleteButton = itemView.findViewById(R.id.deleteButton);
        this.viewInterestButton = itemView.findViewById(R.id.viewInterestButton);

        this.interestButton = itemView.findViewById(R.id.interestButton);

        this.backButton = itemView.findViewById(R.id.backButton);
        this.confirmButton = itemView.findViewById(R.id.confirmButton);
        this.interestBar = itemView.findViewById(R.id.interestBar);



    }
}
