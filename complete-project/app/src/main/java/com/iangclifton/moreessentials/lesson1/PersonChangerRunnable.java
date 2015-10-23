package com.iangclifton.moreessentials.lesson1;

import android.util.Log;

/**
 * @author Ian G. Clifton
 */
public class PersonChangerRunnable implements Runnable {
    private static final String TAG = "PersonChangerRunnable";

    private final Person mPerson;
    private final ResultHandler<Person> mHandler;

    public PersonChangerRunnable(Person person, ResultHandler<Person> handler) {
        mPerson = person;
        mHandler = handler;
    }

    @Override
    public void run() {
        mPerson.setName("New Background Thread Name");
        Log.d(TAG, "Name is now: " + mPerson.getName());
        Log.d(TAG, "Main thread? " + ExecutorHelper.isMainThread());
        mHandler.sendSuccess(mPerson);
    }

    public static void changeName(PersonChangerRunnable personChangerRunnable) {
        ExecutorHelper.staticExecute(personChangerRunnable);
    }

    public static void changeName(Person person, ResultHandler<Person> handler) {
        PersonChangerRunnable personChangerRunnable = new PersonChangerRunnable(person, handler);
        ExecutorHelper.staticExecute(personChangerRunnable);
    }
}
