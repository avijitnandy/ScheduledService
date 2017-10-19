package com.w3xplorers.scheduledservice;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.os.AsyncTask;

/**
 * Created by Avijit on 10/19/2017.
 */

public class MyJobService extends JobService {
    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        new MyJob().execute(jobParameters);
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        return JOB_DONE_FLAG;
    }

    boolean JOB_DONE_FLAG = false;
    private class MyJob extends AsyncTask<JobParameters, Void, JobParameters[]> {

        @Override
        protected JobParameters[] doInBackground(JobParameters... jobParameterses) {
            for(int i=0;i<=5;i++){
                System.out.println("Looping ...."+i);
            }
            JOB_DONE_FLAG = true;
            return jobParameterses;
        }

        @Override
        protected void onPostExecute(JobParameters[] jobParameterses) {
            for (JobParameters params : jobParameterses) {
                jobFinished(params, false);
            }
        }


    }
}
