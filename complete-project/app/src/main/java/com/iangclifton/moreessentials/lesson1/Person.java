package com.iangclifton.moreessentials.lesson1;

import android.os.Handler;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Ian G. Clifton
 */
public class Person {

    private final AtomicInteger mAge = new AtomicInteger();

    private final Object mMyLock = new Object();

    private String mName;

    public Person(int age, String name) {
        mAge.set(age);
        mName = name;
    }

    synchronized public String getName() {
        return mName;
    }

    synchronized public void setName(String name) {
        mName = name;
    }

//    public void moreComplicatedMethod() {
//        MyThread myThread = new MyThread();
//        myThread.start();
//        Thread thread = new Thread(new MyRunnable());
//        thread.start();
//
//        ExecutorHelper.getInstance().execute(new MyRunnable());
//        ExecutorHelper.staticExecute(new MyRunnable());
//
//        Handler myHandler = new Handler();
//        myHandler.postDelayed(new MyRunnable(), 1000);
//
//        mAge.getAndIncrement();
//        int blah = 5 * 5;
//        synchronized (mMyLock) {
//            mName = null;
//        }
//        int blahblah = 7;
//    }
}
