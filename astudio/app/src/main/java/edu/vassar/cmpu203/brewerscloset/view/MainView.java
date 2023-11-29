package edu.vassar.cmpu203.brewerscloset.view;


import android.view.LayoutInflater;
import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import edu.vassar.cmpu203.vassarmarketplace.databinding.MainBinding;

public class MainView implements IMainView{

    MainBinding binding;
    FragmentManager fManager;
    public MainView(FragmentActivity activity) {
        this.fManager = activity.getSupportFragmentManager();
        this.binding = MainBinding.inflate(activity.getLayoutInflater());
    }
    @Override
    public View getRootView() {
        return this.binding.getRoot();
    }

    @Override
    public void displayFragment(Fragment fragment, boolean addToStack, String name) {
        FragmentTransaction ft = fManager.beginTransaction();

        ft.replace(this.binding.fragmentContainerView.getId(), fragment);
        if (addToStack) ft.addToBackStack(name);
        ft.commit();
    }
}