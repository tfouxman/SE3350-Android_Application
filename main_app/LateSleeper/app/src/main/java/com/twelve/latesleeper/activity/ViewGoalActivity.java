package com.twelve.latesleeper.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.twelve.latesleeper.R;
import com.twelve.latesleeper.model.Goal;

import java.util.Date;
import java.util.HashMap;

import static java.lang.Integer.parseInt;

public class ViewGoalActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private Goal goal;
    private HashMap<String, Object> goalInfo;

    ProgressBar loadingBar;
    ConstraintLayout dimLayout;

    TextView sleepTimeTextView;
    TextView daysTextView;
    TextView daysCompletedTextView;
    TextView totalHoursTextView;
    TextView dateCreatedTextView;
    TextView completedTextView;

    private String sleepTime;
    private Integer days;
    private Integer daysCompleted;
    private Integer totalHours;
    private Date dateCreated;
    private Boolean completed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_view_goal);

        loadingBar = (ProgressBar) findViewById(R.id.loadingBar);
        dimLayout = (ConstraintLayout) findViewById(R.id.dimLayout);

        sleepTimeTextView = findViewById(R.id.sleepTimeTextView);
        daysTextView = findViewById(R.id.daysTextView);
        daysCompletedTextView = findViewById(R.id.daysCompletedTextView);
        totalHoursTextView = findViewById(R.id.totalHoursTextView);
        dateCreatedTextView = findViewById(R.id.dateCreatedTextView);
        completedTextView = findViewById(R.id.completedTextView);

    }

    @Override
    public void onStart() {
        super.onStart();

        dimLayout.setVisibility(View.VISIBLE);
        dimLayout.bringToFront();

        FirebaseUser currentUser = mAuth.getCurrentUser();

        goal = (Goal) getIntent().getSerializableExtra("goal");
        goalInfo = goal.getGoal();

        sleepTime = goalInfo.get("sleepTime").toString();
        days = parseInt(goalInfo.get("days").toString());
        daysCompleted = parseInt(goalInfo.get("daysCompleted").toString());
        totalHours = parseInt(goalInfo.get("daysCompleted").toString());
        dateCreated = new Date(goalInfo.get("dateCreated").toString());
        completed = Boolean.parseBoolean(goalInfo.get("completed").toString());

        sleepTimeTextView.setText("Sleep time: " + goalInfo.get("sleepTime").toString());
        daysTextView.setText("Days for goal: " + goalInfo.get("days").toString());
        daysCompletedTextView.setText("Days completed: " + goalInfo.get("daysCompleted").toString());
        totalHoursTextView.setText("Total hours recorded: " + goalInfo.get("totalHours").toString());
        dateCreatedTextView.setText("Created on  " + goalInfo.get("dateCreated").toString());
        completedTextView.setText("Completed: " + goalInfo.get("completed").toString());

        dimLayout.setVisibility(View.GONE);
    }

    public void startGoal(View view) {
        Intent intent = new Intent(ViewGoalActivity.this, AlarmClockWakeUpActivity.class);
        startActivity(intent);
    }

    public void fourSteps(View view) {
        Intent intent = new Intent(ViewGoalActivity.this, RelabelActivity.class);
        startActivity(intent);
    }

}
