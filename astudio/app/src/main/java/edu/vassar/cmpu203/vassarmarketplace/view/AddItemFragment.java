package edu.vassar.cmpu203.vassarmarketplace.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.material.snackbar.Snackbar;

import edu.vassar.cmpu203.vassarmarketplace.R;
import edu.vassar.cmpu203.vassarmarketplace.databinding.FragmentAddItemBinding;

/**
 *
 */
public class AddItemFragment extends Fragment implements IAddItemView{

    FragmentAddItemBinding binding;

    Listener listener;

    public AddItemFragment(@NonNull Listener listener){
        this.listener = listener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.binding = FragmentAddItemBinding.inflate(inflater);
        return this.binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.binding.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Back To Home Feed", Snackbar.LENGTH_LONG).show();
                AddItemFragment.this.listener.uponBack();
            }
        });

        this.binding.postButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //retrieve title
                final Editable itemTitleEditable = AddItemFragment.this.binding.titleBar.getText();
                final String itemTitleStr = itemTitleEditable.toString();
                //retrieve price
                final Editable itemPriceEditable = AddItemFragment.this.binding.priceBar.getText();
                final String itemPriceStr = itemPriceEditable.toString();
                //retrieve description
                final Editable itemDescEditable = AddItemFragment.this.binding.descriptionBar.getText();
                final String itemDescStr = itemDescEditable.toString();
                //retrieve pictures
                final Editable itemPicsEditable = AddItemFragment.this.binding.picsBar.getText();
                final String itemPicsStr = itemPicsEditable.toString();

                //check em
                if (itemDescStr.length() == 0 || itemPicsStr.length() == 0
                || itemTitleStr.length() == 0 || itemPriceStr.length() == 0) {
                    Snackbar.make(v, "Invalid Item: Please make sure all fields are filled", Snackbar.LENGTH_LONG).show();
                    return;
                }

                Snackbar.make(v, "Item Posted", Snackbar.LENGTH_LONG).show();
                //shouldnt throw exception because the input field accepts double
                double itemPrice = Double.parseDouble(itemPriceStr);

                //clear the input fields to ready them
                itemTitleEditable.clear();
                itemPriceEditable.clear();
                itemDescEditable.clear();
                itemPicsEditable.clear();

                //notify listener
                AddItemFragment.this.listener.uponPost(itemTitleStr, itemPrice, itemDescStr, itemPicsStr);
            }
        });
    }
}