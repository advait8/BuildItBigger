package com.udacity.nanodegree.advait.builditbiggerv4;


import android.test.AndroidTestCase;
import android.test.UiThreadTest;

import com.udacity.nanodegree.IAsyncTaskListener;
import com.udacity.nanodegree.JokeAsyncTask;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;


/**
 * Created by Advait on 8/10/2017.
 */

public class JokeAsyncTaskTest extends AndroidTestCase implements IAsyncTaskListener {

    String result;

    CountDownLatch signal;

    JokeAsyncTask jokeAsyncTask;

    protected void setUp() throws Exception {
        super.setUp();

        signal = new CountDownLatch(1);
        jokeAsyncTask = new JokeAsyncTask(this);
    }

    @UiThreadTest
    public void test() throws InterruptedException {
        jokeAsyncTask.execute();
        signal.await(5, TimeUnit.SECONDS);
    }


    @Override
    public void onResult(Object o) {
        signal.countDown();
        result = (String)o;
        assertNotNull(result);
    }
}
