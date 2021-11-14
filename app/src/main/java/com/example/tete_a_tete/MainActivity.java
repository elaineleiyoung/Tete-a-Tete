package com.example.tete_a_tete;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.tete_a_tete.databinding.ActivityMainBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    private String level;
    private String topic;
    static String[][] l1 = new String[3][2];
    static String[][] l2 = new String[3][2];
    static String[][] l3 = new String[3][2];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference().child("l1");
        DatabaseReference myRef2 = database.getReference().child("l2");
        DatabaseReference myRef3 = database.getReference().child("l3");

        myRef3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot3) {
                int outerLoop = 0;
                for(DataSnapshot snapshot3 : dataSnapshot3.getChildren()) {
                    //Log.d("snap:", snapshot2.toString());
                    for (int i = 0; i < snapshot3.getChildrenCount(); i++) {
                        l3[outerLoop][i] = snapshot3.child(String.valueOf(i)).getValue().toString();
                        //Log.d("test3", snapshot.child(String.valueOf(i)).getValue().toString());
                    }
                    outerLoop++;
                }

                // Loop through all rows
                for (String[] row : l3) {

                    // converting each row as string
                    // and then printing in a separate line
                    Log.d("final", Arrays.toString(row));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        myRef2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot2) {
                int outerLoop = 0;
                for(DataSnapshot snapshot2 : dataSnapshot2.getChildren()) {
                    //Log.d("snap:", snapshot2.toString());
                    for (int i = 0; i < snapshot2.getChildrenCount(); i++) {
                        l2[outerLoop][i] = snapshot2.child(String.valueOf(i)).getValue().toString();
                        //Log.d("test3", snapshot.child(String.valueOf(i)).getValue().toString());
                    }
                    outerLoop++;
                }

                // Loop through all rows
                for (String[] row : l2) {

                    // converting each row as string
                    // and then printing in a separate line
                    //Log.d("final", Arrays.toString(row));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int outerLoop = 0;
                for(DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    //Log.d("snap:", snapshot.toString());
                    for (int i = 0; i < snapshot.getChildrenCount(); i++) {
                        l1[outerLoop][i] = snapshot.child(String.valueOf(i)).getValue().toString();
                        //Log.d("test3", snapshot.child(String.valueOf(i)).getValue().toString());
                    }
                    outerLoop++;
                }

                // Loop through all rows
                for (String[] row : l1) {

                    // converting each row as string
                    // and then printing in a separate line
                    //Log.d("final", Arrays.toString(row));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }


}