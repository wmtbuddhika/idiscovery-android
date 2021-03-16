package com.project.idiscovery;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.google.android.material.textfield.TextInputEditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AddActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private TextInputEditText activityName, activityLocation, activityDate, activityTime, activityReporter;
    private AwesomeValidation validation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        loadComponents();
        setComponentValues();
        initializeValidations();

        findViewById(R.id.datePicker).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker();
            }
        });

        findViewById(R.id.timePicker).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePicker();
            }
        });

        findViewById(R.id.cancelBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBack();
            }
        });

        findViewById(R.id.saveActivityBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                confirmSaveActivity();
                if (validation.validate()) {
                    // confirmSaveActivity();
                } else {
                    Toast.makeText(getApplicationContext(), "Please enter Details to continue.", Toast.LENGTH_SHORT).show();
                }
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

    private void initializeValidations() {
        validation = new AwesomeValidation(ValidationStyle.BASIC);
        validation.addValidation(this, R.id.activityName, RegexTemplate.NOT_EMPTY, R.string.activity_name_required);
        validation.addValidation(this, R.id.activityDate, RegexTemplate.NOT_EMPTY, R.string.activity_date_required);
        validation.addValidation(this, R.id.activityReporter, RegexTemplate.NOT_EMPTY, R.string.activity_reporter_required);
    }

    private void showDatePicker() {
        DatePickerDialog datePicker = new DatePickerDialog(
                AddActivity.this,
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        );
        datePicker.show();
    }

    private void showTimePicker() {
        TimePickerDialog timePicker = new TimePickerDialog(
                AddActivity.this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        String time = hourOfDay + ":" + minute;
                        activityTime.setText(time);
                    }
                },
                Calendar.getInstance().get(Calendar.HOUR_OF_DAY),
                Calendar.getInstance().get(Calendar.MINUTE),
                false
        );
        timePicker.show();
    }

    private void goBack() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void confirmSaveActivity() {
        Toast.makeText(getApplicationContext(), "Please Verify and Confirm.", Toast.LENGTH_SHORT).show();

        String activityNameValue, activityLocationValue, activityDateValue, activityTimeValue, activityReporterValue;
        activityNameValue = activityName.getText().toString();
        activityLocationValue = activityLocation.getText().toString();
        activityDateValue = activityDate.getText().toString();
        activityTimeValue = activityTime.getText().toString();
        activityReporterValue = activityReporter.getText().toString();

        Intent intent = new Intent(this, SaveActivity.class);
        intent.putExtra("activityNameValue", activityNameValue);
        intent.putExtra("activityLocationValue", activityLocationValue);
        intent.putExtra("activityDateValue", activityDateValue);
        intent.putExtra("activityTimeValue", activityTimeValue);
        intent.putExtra("activityReporterValue", activityReporterValue);
        startActivity(intent);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

        String dateString = year + "-" + ++month + "-" + dayOfMonth;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        AwesomeValidation dateValidation = new AwesomeValidation(ValidationStyle.BASIC);
        dateValidation.addValidation(this, R.id.activityDate, RegexTemplate.NOT_EMPTY, R.string.activity_date_required);
        dateValidation.clear();

        try {
            Date date = dateFormat.parse(dateString);
            activityDate.setText(dateFormat.format(date.getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
