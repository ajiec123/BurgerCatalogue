package com.example.gallery;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.appbar.CollapsingToolbarLayout;

public class BurgerDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_burger_details);

        Burger dataRecieved = getIntent().getParcelableExtra("burger");
        ImageView imagesBurger = findViewById(R.id.image_burger);
        TextView tvDescription = findViewById(R.id.tv_description);

        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        collapsingToolbarLayout.setCollapsedTitleTextColor(getResources().getColor(R.color.colorText));
        collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(R.color.colorText));


        if (dataRecieved != null) {
            collapsingToolbarLayout.setTitle(dataRecieved.getName());
            imagesBurger.setImageResource(dataRecieved.getPhoto());
            tvDescription.setText(dataRecieved.getDetail());
        }else{
            System.out.println("Null");
        }


    }
}
