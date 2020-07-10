package com.example.gallery;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


import java.util.ArrayList;

public class BurgerActivity extends AppCompatActivity {
    private RecyclerView rvBurgers;
    private ArrayList<Burger> list =  new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_burger);

        rvBurgers = findViewById(R.id.recyclerView);
        list.addAll(BurgerData.getListData());
        showRecyclerList();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        toolbar.setTitle("Burger Menu");
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_bar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.profile) {
            Intent moveIntent = new Intent(BurgerActivity.this, ProfileActivity.class );
            startActivity(moveIntent);
        }
        return super.onOptionsItemSelected(item);
    }

    private void showRecyclerList() {
        rvBurgers.setLayoutManager(new LinearLayoutManager(this));
        BurgerAdapter burgerDataAdapter = new BurgerAdapter(list);
        rvBurgers.setAdapter(burgerDataAdapter);
    }

}
