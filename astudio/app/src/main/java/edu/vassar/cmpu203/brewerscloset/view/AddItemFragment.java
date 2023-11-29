package edu.vassar.cmpu203.brewerscloset.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.material.snackbar.Snackbar;

import java.net.URI;

import edu.vassar.cmpu203.brewerscloset.R;
import edu.vassar.cmpu203.brewerscloset.databinding.FragmentAddItemBinding;
import edu.vassar.cmpu203.brewerscloset.model.Moderator;

/**
 *
 */
public class AddItemFragment extends Fragment implements IAddItemView{

    FragmentAddItemBinding binding;

    Listener listener;
    Moderator moderator;

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

        this.binding.backButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Back To Home Feed", Snackbar.LENGTH_LONG).show();
                AddItemFragment.this.listener.uponBackToHome();
            }
        });

        this.binding.addPicsButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Add Pictures", Snackbar.LENGTH_LONG).show();
                AddItemFragment.this.listener.uponAddPics(AddItemFragment.this);
            }
        });


        this.binding.postButton.setOnClickListener(new OnClickListener() {
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

                //need to retrieve pictures


                //check em
                if (itemDescStr.length() == 0
                || itemTitleStr.length() == 0 || itemPriceStr.length() == 0) {
                    Snackbar.make(v, "Invalid Item: Please make sure all fields are filled", Snackbar.LENGTH_LONG).show();
                    return;
                }

                if (Moderator.isBannedItem(itemTitleStr, itemDescStr) == true) {
                    Snackbar.make(v, "Invalid Item: Please keep item clean", Snackbar.LENGTH_LONG).show();
                    return;
                }

                Snackbar.make(v, "Item Posted", Snackbar.LENGTH_LONG).show();
                //shouldnt throw exception because the input field accepts double
                double itemPrice = Double.parseDouble(itemPriceStr);

                //clear the input fields to ready them
                itemTitleEditable.clear();
                itemPriceEditable.clear();
                itemDescEditable.clear();


                //notify listener
                AddItemFragment.this.listener.uponPost(itemTitleStr, itemPrice, itemDescStr, null);
            }
        });
    }
    @Override
    public void updateImage(Uri data) {
        this.binding.imageView.setImageURI(data);
    }


}