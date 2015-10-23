package com.iangclifton.moreessentials.lesson1;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iangclifton.moreessentials.R;

/**
 * Fragment for running the Lesson 1 examples.
 *
 * Remember that Handlers created with the default constructor
 * will be tied to the Looper on that thread. You generally
 * create Handlers from the main thread and pass Message objects
 * to them in a background thread.
 *
 * We simplified the code a bit by creating an anonymous Handler,
 * but you have to be aware that an anonymous class retains a
 * reference to whatever class it is built in (in this case,
 * this Lesson1Fragment). That means when you hand it to a
 * background thread, it will retain that reference and prevent
 * garbage collection until the Handler is no longer used anywhere.
 * That means it's a good idea to create Handlers as named classes
 * and then attach/detach whatever you need to interact with on
 * success/failure (such as a Fragment or a View).
 *
 * @author Ian G. Clifton
 */
public class Lesson1Fragment extends Fragment {
    private static final String TAG = "Lesson1Fragment";

    public Lesson1Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lesson1, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();

        // Anonymous Handler
        ResultHandler<Person> handler = new ResultHandler<Person>() {
            @Override
            public void onFailure() {
                Log.w(TAG, "onFailure called");
            }

            @Override
            public void onSuccess(Person person) {
                Log.d(TAG, "New name on main thread: " + person.getName());
                Log.d(TAG, "Main thread? " + ExecutorHelper.isMainThread());
            }
        };


        Person person = new Person(20, "Pat");
        PersonChangerRunnable personChangerRunnable = new PersonChangerRunnable(person, handler);
        Log.d(TAG, "Original name: " + person.getName());
        PersonChangerRunnable.changeName(personChangerRunnable);

        PersonChangerRunnable.changeName(person, handler);
    }
}
