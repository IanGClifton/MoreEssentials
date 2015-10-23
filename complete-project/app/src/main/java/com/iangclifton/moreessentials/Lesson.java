package com.iangclifton.moreessentials;

import android.support.v4.app.Fragment;

/**
 * Represents a lesson, which has a name and an associated Fragment.
 *
 * @author Ian G. Clifton
 */
public class Lesson {

    private String mName;
    private Class<? extends Fragment> mFragmentClass;

    public Lesson(String name, Class<? extends Fragment> fragmentClass) {
        mName = name;
        mFragmentClass = fragmentClass;
    }

    /**
     * Returns the name of the Lesson such as "Lesson 1"
     *
     * @return String Lesson name
     */
    public String getName() {
        return mName;
    }

    /**
     * Returns the Fragment for demoing the Lesson
     *
     * @return Fragment class to show the Lesson
     */
    public Class<? extends Fragment> getFragmentClass() {
        return mFragmentClass;
    }
}
