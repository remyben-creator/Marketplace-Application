package edu.vassar.cmpu203.vassarmarketplace.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.material.snackbar.Snackbar;

import edu.vassar.cmpu203.vassarmarketplace.databinding.HomeFeedBinding;
public class HomeFeedView implements IHomeFeedView, View.OnClickListener {

    HomeFeedBinding binding;

    public HomeFeedView(Context context) {
        this.binding = HomeFeedBinding.inflate(LayoutInflater.from(context));

        this.binding.searchButton.setOnClickListener(this);
        this.binding.myItemsButton.setOnClickListener(this);
        // possibly make abstract class if more buttons in future
    }
    @Override
    public View getRootView() {

        return this.binding.getRoot();
    }

    public void onClick(View v) {
        if (v == this.binding.searchButton) {
        Snackbar.make(v, "Search Button was clicked", Snackbar.LENGTH_LONG).show();}
        else {Snackbar.make(v, "My Items Button was clicked", Snackbar.LENGTH_LONG).show();}
    }

}
