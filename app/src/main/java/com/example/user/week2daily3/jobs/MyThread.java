package com.example.user.week2daily3.jobs;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.ProgressBar;

import com.example.user.week2daily3.MyTask;

public class MyThread extends Thread {

    public static final String TAG = MyThread.class.getSimpleName() + "_TAG";

    Handler handler = new Handler(Looper.getMainLooper());

    ProgressBar pb;

    public MyThread(ProgressBar pb) {
        this.pb = pb;
    }

    @Override
    public void run() {
        super.run();

        //start the task
        try {
            MyTask.start(pb, Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        handler.post(new Runnable(){
            @Override
            public void run() {
                Log.d(TAG, "run: " + currentThread().getName());
            }
        });
    }
}

