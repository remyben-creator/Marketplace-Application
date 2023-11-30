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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ConfirmDeleteFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ConfirmDeleteFragment extends Fragment implements IConfirmDeleteView{
    FragmentConfirmDeleteBinding binding;

    Listener listener;
    Item item;

    public ConfirmDeleteFragment(@NonNull Listener listener, Item item){
        this.listener = listener;
        this.item = item;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.binding = FragmentConfirmDeleteBinding.inflate(inflater);
        return this.binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.binding.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConfirmDeleteFragment.this.listener.uponBackToMyItems();
            }
        });
        this.binding.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConfirmDeleteFragment.this.listener.uponConfirmDelete(item);
            }
        });
    }


}