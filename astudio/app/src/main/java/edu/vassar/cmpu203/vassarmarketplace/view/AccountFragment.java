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
import edu.vassar.cmpu203.vassarmarketplace.databinding.FragmentAccountBinding;

/**
 *
 */
public class AccountFragment extends Fragment implements IAccountView{

    FragmentAccountBinding binding;

    Listener listener;

    public AccountFragment(@NonNull Listener listener){
        this.listener = listener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.binding = FragmentAccountBinding.inflate(inflater);
        return this.binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.binding.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Back To Home Feed", Snackbar.LENGTH_LONG).show();
                AccountFragment.this.listener.uponBack();
            }
        });

        this.binding.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //retrieve email
                final Editable userEmailEditable = AccountFragment.this.binding.emailBar.getText();
                final String userEmailStr = userEmailEditable.toString();
                //retrieve password
                final Editable userPasswordEditable = AccountFragment.this.binding.passwordBar.getText();
                final String userPasswordStr = userPasswordEditable.toString();

                //check em
                if (userEmailStr.length() == 0 || userPasswordStr.length() == 0) {
                    Snackbar.make(v, "Invalid Item: Please make sure all fields are filled", Snackbar.LENGTH_LONG).show();
                    return;
                }

                Snackbar.make(v, "User Logged In", Snackbar.LENGTH_LONG).show();

                //clear the input fields to ready them
                userEmailEditable.clear();
                userPasswordEditable.clear();

                //notify listener
                AccountFragment.this.listener.uponLogin(userEmailStr, userPasswordStr);
            }
        });
    }
}