package com.example.user.week2daily3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.user.week2daily3.jobs.MyAsyncTask;
import com.example.user.week2daily3.jobs.MyThread;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class MainActivity extends AppCompatActivity {

    private ProgressBar pbOne;
    private ProgressBar pbTwo;
    private ProgressBar pbThree;
    private ProgressBar pbFour;
    private TextView tvOne;
    private TextView tvTwo;
    private ThreadPoolExecutor myThreadPool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pbOne = findViewById(R.id.pbOne);
        pbTwo = findViewById(R.id.pbTwo);
        pbThree = findViewById(R.id.pbThree);
        pbFour = findViewById(R.id.pbFour);
        tvOne = findViewById(R.id.tvOne);
        tvTwo = findViewById(R.id.tvTwo);

        myThreadPool = (ThreadPoolExecutor)Executors.newFixedThreadPool(4);
    }

    public void startThreads(View view) {
        //create four threads
        MyThread threadOne = new MyThread(pbOne);
        MyThread threadTwo = new MyThread(pbTwo);
        MyThread threadThree = new MyThread(pbThree);
        MyThread threadFour = new MyThread(pbFour);

        //add threads to the threadpool
        if(!myThreadPool.isShutdown()) {// && myThreadPool.getActiveCount() != myThreadPool.getMaximumPoolSize()) {
            myThreadPool.submit(threadOne);
            myThreadPool.submit(threadTwo);
            myThreadPool.submit(threadThree);
            myThreadPool.submit(threadFour);
        }

    }

    //used asyncTask.executeOnExecutor so they both run at the same time
    public void updateTextViews(View view) {
        MyAsyncTask at1 = new MyAsyncTask(tvOne);
        MyAsyncTask at2 = new MyAsyncTask(tvTwo);
        at1.executeOnExecutor(MyAsyncTask.THREAD_POOL_EXECUTOR, "AT1 initial params");
        at2.executeOnExecutor(MyAsyncTask.THREAD_POOL_EXECUTOR, "AT2 Initial params");
    }
}
