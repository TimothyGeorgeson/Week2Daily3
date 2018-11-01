package com.example.user.week2daily3;

import android.util.Log;
import android.widget.ProgressBar;

import java.util.Random;

public class MyTask {

    public static final String TAG = MyTask.class.getSimpleName() + "_TAG";
    public static void start(ProgressBar pb, String thread) throws InterruptedException {

        Log.d(TAG, "start: Task starting on Thread: " + thread);

        //gets random number for sleep amount
        Random rand = new Random();
        int value = rand.nextInt((100-10) + 1) + 10;

        //increments the progress bar
        for (int i = 0; i < pb.getMax(); i++) {
            Thread.sleep(value);
            pb.setProgress(i);
            Log.d(TAG, "start: iteration: " + i);
        }

        Log.d(TAG, "start: Task completed on Thread: " + thread);
    }
}
