package com.w3xplorers.scheduledservice;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    public static int JOB_ID = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((Button)findViewById(R.id.schedule)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ComponentName serviceName = new ComponentName(MainActivity.this, MyJobService.class);
                JobInfo jobInfo = new JobInfo.Builder(JOB_ID, serviceName)
                        .setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED)
                        .setOverrideDeadline(10000)  //Use this if you want to run your job forcefully
                        .build();

                JobScheduler scheduler = (JobScheduler) getSystemService(Context.JOB_SCHEDULER_SERVICE);
                int result = scheduler.schedule(jobInfo);
                if (result == JobScheduler.RESULT_SUCCESS)
                    Log.d("Scheduled Activity", "Job scheduled successfully!");
            }
        });
    }
}
