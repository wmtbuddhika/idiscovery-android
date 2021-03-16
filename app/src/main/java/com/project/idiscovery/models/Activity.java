package com.project.idiscovery.models;

import androidx.annotation.NonNull;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Activity {
    private String activityId;
    private String activityName;
    private String activityLocation;
    private String activityDate;
    private String activityTime;
    private String activityReporter;
    private FirebaseDatabase root = FirebaseDatabase.getInstance();
    private DatabaseReference activityDatabase =  root.getReference("activity");

    public Activity() {

    }

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getActivityLocation() {
        return activityLocation;
    }

    public void setActivityLocation(String activityLocation) {
        this.activityLocation = activityLocation;
    }

    public String getActivityDate() {
        return activityDate;
    }

    public void setActivityDate(String activityDate) {
        this.activityDate = activityDate;
    }

    public String getActivityTime() {
        return activityTime;
    }

    public void setActivityTime(String activityTime) {
        this.activityTime = activityTime;
    }

    public String getActivityReporter() {
        return activityReporter;
    }

    public void setActivityReporter(String activityReporter) {
        this.activityReporter = activityReporter;
    }

    public boolean saveActivity(String activityName, String activityLocation, String activityDate, String activityTime, String activityReporter) {
        try {
            String id = activityDatabase.push().getKey();
            if (id != null) {
                activityDatabase.child(id).setValue(id);
                activityDatabase.child(id).child("activityName").setValue(activityName);
//                activityDatabase.child(id).child("activityLocation").setValue(activityLocation);
//                activityDatabase.child(id).child("activityDate").setValue(activityDate);
//                activityDatabase.child(id).child("activityTime").setValue(activityTime);
//                activityDatabase.child(id).child("activityReporter").setValue(activityReporter);

                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public List<Activity> getAllActivities() {
        final List<Activity> activities = new ArrayList<>();

        activityDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Activity activity = new Activity();
                    Map<String,String> activityDetails = (Map<String, String>) snapshot.getValue();

                    activity.setActivityId(snapshot.getKey());
                    activity.setActivityName(activityDetails.get("activityName"));
                    activity.setActivityLocation(activityDetails.get("activityLocation"));
                    activity.setActivityDate(activityDetails.get("activityDate"));
                    activity.setActivityTime(activityDetails.get("activityTime"));
                    activity.setActivityReporter(activityDetails.get("activityReporter"));
                    activities.add(activity);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return activities;
    }

}
