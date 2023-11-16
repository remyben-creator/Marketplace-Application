package edu.vassar.cmpu203.vassarmarketplace.view;

import android.content.Context;
import android.location.GnssAntennaInfo;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.snackbar.Snackbar;

import edu.vassar.cmpu203.vassarmarketplace.R;
import edu.vassar.cmpu203.vassarmarketplace.databinding.FragmentAddItemBinding;
import edu.vassar.cmpu203.vassarmarketplace.databinding.FragmentHomeFeedBinding;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFeedFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFeedFragment extends Fragment implements IHomeFeedView{

    FragmentHomeFeedBinding binding;
    Listener listener;

    public HomeFeedFragment(@NonNull Listener listener){
        this.listener = listener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.binding = FragmentHomeFeedBinding.inflate(inflater);
        return this.binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.binding.searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Search Button was clicked", Snackbar.LENGTH_LONG).show();
            }
        });
        this.binding.myItemsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "My Items Button was clicked", Snackbar.LENGTH_LONG).show();
            }
        });
        this.binding.addItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Add Item Button was clicked", Snackbar.LENGTH_LONG).show();
                HomeFeedFragment.this.listener.uponAddItem();
            }
        });
        this.binding.accountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Account Button was clicked", Snackbar.LENGTH_LONG).show();
                HomeFeedFragment.this.listener.uponAccount();
            }
        });
    }
}