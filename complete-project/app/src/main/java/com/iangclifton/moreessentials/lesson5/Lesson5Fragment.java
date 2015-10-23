package com.iangclifton.moreessentials.lesson5;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.iangclifton.moreessentials.R;

/**
 * Fragment for running the Lesson 5 examples.
 *
 * The Google Play Services SDK is a great way to add Google features to your
 * app, but it can be a bit confusing due to its necessarily asynchronous
 * nature. Remember that the documentation exists at the Google developer site:
 * https://developers.google.com/android/guides/overview
 *
 * It can be difficult to demonstrate something as large as Google Play
 * Services within a relatively short video, but the important part is to
 * remember that you need to verify Google Play Services is available and
 * accessible to your app whenever you want to use it.
 *
 * @author Ian G. Clifton
 */
public class Lesson5Fragment extends Fragment {

    public Lesson5Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_lesson5, container, false);
        rootView.findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MapsActivity.class);
                startActivity(intent);
            }
        });
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();

        GoogleApiAvailability googleApiAvailability = GoogleApiAvailability.getInstance();
        int code = googleApiAvailability.isGooglePlayServicesAvailable(getActivity());

        TextView textView = (TextView) getView().findViewById(R.id.text);
        View button = getView().findViewById(R.id.button);
        if (code == ConnectionResult.SUCCESS) {
            // Everything is working
            textView.setText("Got successful result");
            button.setVisibility(View.VISIBLE);
        } else {
            // Something is wrong
            textView.setText("Got error: " + code);
            button.setVisibility(View.INVISIBLE);
        }
    }
}
