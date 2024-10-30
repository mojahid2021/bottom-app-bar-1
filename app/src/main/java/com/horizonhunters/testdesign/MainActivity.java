package com.horizonhunters.testdesign;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private BottomAppBar bottomAppBar;
    private FloatingActionButton fab;
    private TextView helloText;

    // Keep track of the currently selected button
    private LinearLayout currentSelectedButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        loadFragment(new FragmentOne());

        // Initialize UI components
        bottomAppBar = findViewById(R.id.appBar);
        fab = findViewById(R.id.fab);

        // Set up click listeners for BottomAppBar icons
        setUpBottomAppBarListeners();

        // Floating Action Button click listener
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadFragment(new FragmentTwo());
            }
        });
    }

    private void loadFragment(Fragment fragment) {
        // Create a new FragmentTransaction to switch fragments
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(null); // Add transaction to back stack
        transaction.commit();
    }

    private void setUpBottomAppBarListeners() {
        // Set up listeners for each icon in the BottomAppBar
        LinearLayout homeButton = bottomAppBar.findViewById(R.id.homeButton);
        LinearLayout searchButton = bottomAppBar.findViewById(R.id.searchButton);
        LinearLayout profileButton = bottomAppBar.findViewById(R.id.profileButton);
        LinearLayout settingsButton = bottomAppBar.findViewById(R.id.settingsButton);

        // Home button click listener
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectButton(homeButton);
                helloText.setText("Home Selected");
            }
        });

        // Search button click listener
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectButton(searchButton);
                helloText.setText("Search Selected");
            }
        });

        // Profile button click listener
        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectButton(profileButton);
                helloText.setText("Profile Selected");
            }
        });

        // Settings button click listener
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectButton(settingsButton);
                helloText.setText("Settings Selected");
            }
        });
    }

    private void selectButton(LinearLayout button) {
        // Reset the background of the previously selected button if it exists
        if (currentSelectedButton != null) {
            currentSelectedButton.setBackgroundResource(R.drawable.button_default_background);
        }

        // Set the new selected button background and update the currently selected button
        button.setBackgroundResource(R.drawable.button_selected_background);
        currentSelectedButton = button;
    }
}