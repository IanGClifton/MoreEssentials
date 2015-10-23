package com.iangclifton.moreessentials.lesson4;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iangclifton.moreessentials.R;

/**
 * Fragment for running the Lesson 4 examples.
 *
 * @author Ian G. Clifton
 */
public class Lesson4Fragment extends Fragment {

    public Lesson4Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lesson4, container, false);
    }


}
