package edu.vassar.cmpu203.brewerscloset.view;

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

import edu.vassar.cmpu203.brewerscloset.databinding.FragmentHomeFeedBinding;
import edu.vassar.cmpu203.brewerscloset.databinding.MyItemViewBinding;
import edu.vassar.cmpu203.brewerscloset.model.Catalog;
import edu.vassar.cmpu203.brewerscloset.model.ItemCatalog;
import edu.vassar.cmpu203.brewerscloset.model.ItemInterestCatalog;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFeedFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFeedFragment extends Fragment implements IHomeFeedView{

    FragmentHomeFeedBinding binding;
    Listener listener;

    //maybe make a list instead
    Catalog currentList;


    public HomeFeedFragment(@NonNull Listener listener, @NonNull Catalog currentList){
        this.listener = listener;
        this.currentList = currentList;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.binding = FragmentHomeFeedBinding.inflate(inflater);

        //somehow get the current list to get displayed in the scroller at this moment
        RecyclerView recyclerView = this.binding.itemCatalogWidget;


        //making the items recycler
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        if (this.currentList instanceof ItemCatalog) {
            if (this.listener.checkForMyItems((ItemCatalog) currentList)) {
                recyclerView.setAdapter(new ItemsAdapter(this.getContext(), this.currentList, true, false, this.listener));
            } else if (((ItemCatalog) this.currentList).forInterest) {
                recyclerView.setAdapter(new ItemsAdapter(this.getContext(), this.currentList, false, true, this.listener));
            } else {
                recyclerView.setAdapter(new ItemsAdapter(this.getContext(), this.currentList, false, false, this.listener));
            }
        }
        //making the interests recycler
        if (this.currentList instanceof ItemInterestCatalog) {
            recyclerView.setAdapter(new ItemsAdapter(this.getContext(), this.currentList, false, false, this.listener));

        }


        return this.binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (this.currentList instanceof ItemCatalog) {
            if (this.listener.checkForMyItems((ItemCatalog) currentList)) {
                this.binding.myItemsAddButton.setText("Add Item");
            }}

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
            if (this.binding.myItemsAddButton.getText().equals("Add Item")) {
                this.binding.myItemsAddButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        HomeFeedFragment.this.listener.uponAddItem();
                    }

                });
            } else {
                this.binding.myItemsAddButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (HomeFeedFragment.this.listener.loggedIn() == false) {
                            Snackbar.make(v, "Error: Logged in as Guest", Snackbar.LENGTH_LONG).show();
                            return;
                        }
                        Snackbar.make(v, "My Items Button was clicked", Snackbar.LENGTH_LONG).show();
                        HomeFeedFragment.this.listener.uponMyItems();
                    }

                });
            }
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