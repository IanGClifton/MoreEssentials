package com.iangclifton.moreessentials.lesson8;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.iangclifton.moreessentials.R;

/**
 * Fragment for running the Lesson 8 examples.
 *
 * @author Ian G. Clifton
 */
public class Lesson8Fragment extends Fragment {

    public Lesson8Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_lesson8, container, false);

        ImageView imageView1 = (ImageView) rootView.findViewById(R.id.image1);
        ImageView imageView2 = (ImageView) rootView.findViewById(R.id.image2);
        ImageView imageView3 = (ImageView) rootView.findViewById(R.id.image3);

        return rootView;
    }


}
