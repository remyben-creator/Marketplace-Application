package edu.vassar.cmpu203.brewerscloset.view;

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
import edu.vassar.cmpu203.vassarmarketplace.databinding.FragmentAccountBinding;
import edu.vassar.cmpu203.vassarmarketplace.databinding.FragmentLoggedInAccountBinding;
import edu.vassar.cmpu203.vassarmarketplace.model.Moderator;

/**
 *
 */
public class LoggedInAccountFragment extends Fragment implements IAccountView{

    FragmentLoggedInAccountBinding binding;

    Listener listener;

    public LoggedInAccountFragment(@NonNull Listener listener){
        this.listener = listener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.binding = FragmentLoggedInAccountBinding.inflate(inflater);
        return this.binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String userEmail = LoggedInAccountFragment.this.listener.getUserEmail();
        this.binding.userEmailView.setText(userEmail);

        this.binding.backButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Back To Home Feed", Snackbar.LENGTH_LONG).show();
                LoggedInAccountFragment.this.listener.uponBackToHome();
            }
        });

        this.binding.logoutButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "User Logged Out", Snackbar.LENGTH_LONG).show();
                LoggedInAccountFragment.this.listener.uponLogout();
            }
        });

    }
}