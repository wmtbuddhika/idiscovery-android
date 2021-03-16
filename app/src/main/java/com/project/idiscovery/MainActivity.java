package com.project.idiscovery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button addActivity;
    private Button viewActivity;
    private Button searchActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addActivity = findViewById(R.id.addActivity);
        addActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddActivity();
            }
        });

        /*viewActivity = findViewById(R.id.viewActivity);
        viewActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openViewActivity();
            }
        });

        searchActivity = findViewById(R.id.searchActivity);
        searchActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSearchActivity();
            }
        });*/
    }

    public void openAddActivity() {
        Intent intent = new Intent(this, AddActivity.class);
        startActivity(intent);
    }

    public void openViewActivity() {
        Intent intent = new Intent(this, ViewActivity.class);
        startActivity(intent);
    }

    public void openSearchActivity() {
        Intent intent = new Intent(this, SearchActivity.class);
        startActivity(intent);
    }
}
