package com.project.idiscovery;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.project.idiscovery.models.Activity;

public class ViewActivity extends AppCompatActivity {

    private TableLayout allActivities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        findViewById(R.id.cancelBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBack();
            }
        });

        findViewById(R.id.searchActivityBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToSearch();
            }
        });

        allActivities = findViewById(R.id.allActivities);
        TextView activityName, activityDate, activityReporter;

        Activity activity = new Activity();

        for (Activity activity2 : activity.getAllActivities()) {
            View inflatedView = getLayoutInflater().inflate(R.layout.row_activity_details, null);
            activityName = inflatedView.findViewById(R.id.activityNameView);
            activityDate = inflatedView.findViewById(R.id.activityDateView);
            activityReporter = inflatedView.findViewById(R.id.activityReporterView);

            inflatedView.setTag(activity2.getActivityId());
            activityName.setText(activity2.getActivityName());
            activityDate.setText(activity2.getActivityDate());
            activityReporter.setText(activity2.getActivityDate());
            allActivities.addView(inflatedView);

            inflatedView.findViewWithTag(activity2.getActivityId()).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getApplicationContext(), "Id : " + v.getTag(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void goBack() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void goToSearch() {
        Intent intent = new Intent(this, SearchActivity.class);
        startActivity(intent);
    }


}
