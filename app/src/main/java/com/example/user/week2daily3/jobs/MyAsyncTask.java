package com.example.user.week2daily3.jobs;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

public class MyAsyncTask extends AsyncTask<String, Integer, String> {

    public static final String TAG = MyAsyncTask.class.getSimpleName() + "_TAG";
    TextView tvMain;
    String[] message = {"These", "TextViews", "Update", "At", "Same", "Time", "Task completed"};

    public MyAsyncTask(TextView tvMain)
    {
        this.tvMain = tvMain;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Log.d(TAG, "onPreExecute: " + Thread.currentThread().getName());
        tvMain.setText("Task starting");
    }

    @Override
    protected String doInBackground(String... strings) {
        Log.d(TAG, "doInBackground: " + Thread.currentThread().getName());
        Log.d(TAG, "doInBackground: Params: " + strings[0]);

        //updates progress every .5 seconds
        for (int i = 0; i < message.length; i++) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
            publishProgress(i);
        }

        return "Background task result";
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        Log.d(TAG, "onPostExecute: " + Thread.currentThread().getName());
        Log.d(TAG, "onPostExecute: Result: " + s);

        tvMain.setText("Task completed");
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        Log.d(TAG, "onProgressUpdate: " + Thread.currentThread().getName());
        //loops through and prints messages in the TextViews
        for (int i = 0; i < message.length; i++) {
            if (values[0] == i)
            {
                tvMain.setText(message[i]);
            }
        }
    }
}

