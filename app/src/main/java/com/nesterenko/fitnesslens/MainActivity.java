package com.nesterenko.fitnesslens;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.Menu;

import com.nesterenko.fitnesslens.Fragments.CaloriesFragment;
import com.nesterenko.fitnesslens.Fragments.SettingsFragment;
import com.nesterenko.fitnesslens.Fragments.StatisticsFragment;
import com.nesterenko.fitnesslens.Fragments.WorkoutFragment;
import com.nesterenko.fitnesslens.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new WorkoutFragment());
        getSupportActionBar().hide();

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {

            switch (item.getItemId()) {
                case R.id.workout:
                    replaceFragment(new WorkoutFragment());
                    break;
                case R.id.statistics:
                    replaceFragment(new StatisticsFragment());
                    break;
                case R.id.calories:
                    replaceFragment(new CaloriesFragment());
                    break;
                case R.id.settings:
                    replaceFragment(new SettingsFragment());
                    break;
            }

            return true;
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();
    }
}