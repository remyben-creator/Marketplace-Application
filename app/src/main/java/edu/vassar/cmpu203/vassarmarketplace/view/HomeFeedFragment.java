package edu.vassar.cmpu203.vassarmarketplace.view;

import android.content.Context;
import android.location.GnssAntennaInfo;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.snackbar.Snackbar;

import java.util.LinkedList;

import edu.vassar.cmpu203.vassarmarketplace.R;
import edu.vassar.cmpu203.vassarmarketplace.databinding.FragmentAddItemBinding;
import edu.vassar.cmpu203.vassarmarketplace.databinding.FragmentHomeFeedBinding;
import edu.vassar.cmpu203.vassarmarketplace.model.Item;
import edu.vassar.cmpu203.vassarmarketplace.model.ItemCatalog;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFeedFragment #newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFeedFragment extends Fragment implements IHomeFeedView{

    FragmentHomeFeedBinding binding;
    Listener listener;

    ItemCatalog currentList;


    public HomeFeedFragment(@NonNull Listener listener, @NonNull ItemCatalog currentList){
        this.listener = listener;
        this.currentList = currentList;
    }

@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  setContentView(R.layout.activity_main);
        this.binding = FragmentHomeFeedBinding.inflate(inflater);
/**
        // somehow get the current list to get displayed in the scroller at this moment
        RecyclerView recyclerView = findViewById(R.id.item_catalog_widget);

        currentList.addItem(new Item("Title", 1.99, "Description", "user"));

        //making the recycler
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MyAdapter(getApplicationContext(),currentList));
*/
        return this.binding.getRoot();


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.binding.searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Search Button was clicked", Snackbar.LENGTH_LONG).show();

                //retrieve search string
                final Editable SSEditable = HomeFeedFragment.this.binding.searchBar.getText();
                final String SSStr = SSEditable.toString();
                HomeFeedFragment.this.listener.uponSearch(SSStr);
            }
        });
        this.binding.myItemsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "My Items Button was clicked", Snackbar.LENGTH_LONG).show();
                HomeFeedFragment.this.listener.uponMyItems();
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
        this.binding.homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Returning to Home Feed", Snackbar.LENGTH_LONG).show();
                HomeFeedFragment.this.listener.uponHome();
            }
        });
    }
}