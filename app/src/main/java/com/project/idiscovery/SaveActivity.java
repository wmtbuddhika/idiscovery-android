package com.project.idiscovery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.project.idiscovery.models.Activity;

public class SaveActivity extends AppCompatActivity {

    private TextInputEditText activityName, activityLocation, activityDate, activityTime, activityReporter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_confirm);

        loadComponents();
        setComponentValues();

        findViewById(R.id.cancelConfirmBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBack();
            }
        });

        findViewById(R.id.confirmActivityBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveActivity();
            }
        });
    }

    private void loadComponents() {
        activityName = findViewById(R.id.activityNameValue);
        activityLocation = findViewById(R.id.activityLocationValue);
        activityDate = findViewById(R.id.activityDateValue);
        activityTime = findViewById(R.id.activityTimeValue);
        activityReporter = findViewById(R.id.activityReporterValue);
    }

    private void setComponentValues() {
        Intent intent = getIntent();
        String activityNameValue = intent.getStringExtra("activityNameValue");
        String activityLocationValue = intent.getStringExtra("activityLocationValue");
        String activityDateValue = intent.getStringExtra("activityDateValue");
        String activityTimeValue = intent.getStringExtra("activityTimeValue");
        String activityReporterValue = intent.getStringExtra("activityReporterValue");

        activityName.setText(activityNameValue);
        activityLocation.setText(activityLocationValue);
        activityDate.setText(activityDateValue);
        activityTime.setText(activityTimeValue);
        activityReporter.setText(activityReporterValue);
    }

    private void goBack() {
        String activityNameValue, activityLocationValue, activityDateValue, activityTimeValue, activityReporterValue;
        activityNameValue = activityName.getText().toString();
        activityLocationValue = activityLocation.getText().toString();
        activityDateValue = activityDate.getText().toString();
        activityTimeValue = activityTime.getText().toString();
        activityReporterValue = activityReporter.getText().toString();

        Intent intent = new Intent(this, AddActivity.class);
        intent.putExtra("activityNameValue", activityNameValue);
        intent.putExtra("activityLocationValue", activityLocationValue);
        intent.putExtra("activityDateValue", activityDateValue);
        intent.putExtra("activityTimeValue", activityTimeValue);
        intent.putExtra("activityReporterValue", activityReporterValue);
        startActivity(intent);
    }

    private void saveActivity() {
        String activityNameValue, activityLocationValue, activityDateValue, activityTimeValue, activityReporterValue;
        activityNameValue = activityName.getText().toString();
        activityLocationValue = activityLocation.getText().toString();
        activityDateValue = activityDate.getText().toString();
        activityTimeValue = activityTime.getText().toString();
        activityReporterValue = activityReporter.getText().toString();

        Activity activity = new Activity();
        if (activity.saveActivity(activityNameValue,activityLocationValue,activityDateValue,activityTimeValue,activityReporterValue)) {
            Toast.makeText(getApplicationContext(), "Activity Saved Successfully.", Toast.LENGTH_SHORT).show();
        }
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


}
