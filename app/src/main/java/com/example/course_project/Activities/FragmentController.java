package com.example.course_project.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.course_project.Fragments.MealEntry;
import com.example.course_project.Fragments.MealLog;
import com.example.course_project.Fragments.PersonInfoEntry;
import com.example.course_project.Fragments.PersonInfoLog;
import com.example.course_project.R;
import com.google.android.material.navigation.NavigationView;

public class FragmentController extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            System.out.println("Vaihtuu mealEntry-fragmenttiin");
            Fragment mealEntry = MealEntry.getInstance();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, mealEntry).commit();
            navigationView.setCheckedItem(R.id.nav_mealEntry);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case (R.id.nav_mealEntry):
                System.out.println("Vaihtuu mealEntry-fragmenttiin");
                Fragment mealEntry = MealEntry.getInstance();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, mealEntry).commit();
                break;
            case (R.id.nav_logOff):
                Toast.makeText(this, "Change to Chat fragment", Toast.LENGTH_SHORT).show();
                changeActivity();
                break;
            case (R.id.nav_profile):
                Toast.makeText(this, "Change to Profile fragment", Toast.LENGTH_SHORT).show();
                break;
            case (R.id.nav_meal_log):
                Toast.makeText(this, "Meal log", Toast.LENGTH_SHORT).show();
                Fragment mealLog = MealLog.getInstance();
                transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, mealLog).commit();
                break;
            case (R.id.nav_weight_log):
                Toast.makeText(this, "Send", Toast.LENGTH_SHORT).show();
                Fragment personInfoLog = PersonInfoLog.getInstance();
                transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, personInfoLog).commit();
                break;
            case (R.id.nav_weightAndHeight):
                Toast.makeText(this, "Weight entry", Toast.LENGTH_SHORT).show();
                Fragment personInfoEntry = PersonInfoEntry.getInstance();
                transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, personInfoEntry).commit();
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public void changeActivity() {
        Intent intent = new Intent(this, StartAppFragmentController.class);
        startActivity(intent);
    }
}