package edu.vassar.cmpu203.brewerscloset.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import edu.vassar.cmpu203.brewerscloset.databinding.FragmentAccountBinding;
import edu.vassar.cmpu203.brewerscloset.databinding.FragmentConfirmDeleteBinding;
import edu.vassar.cmpu203.brewerscloset.model.Item;
import edu.vassar.cmpu203.brewerscloset.model.ItemInterestCatalog;
import edu.vassar.cmpu203.brewerscloset.model.ItemInterestForm;
import edu.vassar.cmpu203.brewerscloset.model.User;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ConfirmDeleteFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ConfirmDeleteFragment extends Fragment implements IConfirmDeleteView{
    FragmentConfirmDeleteBinding binding;

    Listener listener;
    Item item;
    ItemInterestCatalog interests;
    int deleteIndex;
    User user;

    public ConfirmDeleteFragment(@NonNull Listener listener, Object object, ItemInterestCatalog interests, int deleteIndex, User user){
        this.listener = listener;
        this.item = item;
        this.interests = interests;
        this.deleteIndex = deleteIndex;
        this.user = user;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.binding = FragmentConfirmDeleteBinding.inflate(inflater);
        return this.binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (item != null) {
            this.binding.backButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ConfirmDeleteFragment.this.listener.uponBackToMyItems();
                }
            });
            this.binding.deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ConfirmDeleteFragment.this.listener.uponConfirmDeleteItem(item);
                }
            });
        }
        if (this.interests != null && this.deleteIndex != -1) {
            this.binding.backButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ConfirmDeleteFragment.this.listener.uponBackToInterests(interests);
                }
            });
            this.binding.deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ConfirmDeleteFragment.this.listener.uponConfirmDeleteInterest(interests, deleteIndex);
                }
            });
        }
        if (user != null) {
            this.binding.backButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ConfirmDeleteFragment.this.listener.uponAccount();
                }
            });
            this.binding.deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ConfirmDeleteFragment.this.listener.uponConfirmDeleteUser();
                }
            });
        }


    }


}