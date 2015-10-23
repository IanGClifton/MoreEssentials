package com.iangclifton.moreessentials;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.iangclifton.moreessentials.lesson1.Lesson1Fragment;
import com.iangclifton.moreessentials.lesson2.Lesson2Fragment;
import com.iangclifton.moreessentials.lesson3.Lesson3Fragment;
import com.iangclifton.moreessentials.lesson4.Lesson4Fragment;
import com.iangclifton.moreessentials.lesson5.Lesson5Fragment;
import com.iangclifton.moreessentials.lesson6.Lesson6Fragment;
import com.iangclifton.moreessentials.lesson7.Lesson7Fragment;
import com.iangclifton.moreessentials.lesson8.Lesson8Fragment;

/**
 * Adapter for all the Lesson objects.
 *
 * @author Ian G. Clifton
 */
public class LessonAdapter extends BaseAdapter {

    private static final Lesson[] LESSONS = {
            new Lesson("Lesson 1", Lesson1Fragment.class),
            new Lesson("Lesson 2", Lesson2Fragment.class),
            new Lesson("Lesson 3", Lesson3Fragment.class),
            new Lesson("Lesson 4", Lesson4Fragment.class),
            new Lesson("Lesson 5", Lesson5Fragment.class),
            new Lesson("Lesson 6", Lesson6Fragment.class),
            new Lesson("Lesson 7", Lesson7Fragment.class),
            new Lesson("Lesson 8", Lesson8Fragment.class),
    };

    private LayoutInflater mLayoutInflater;

    public LessonAdapter(Context context) {
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return LESSONS.length;
    }

    @Override
    public Lesson getItem(int position) {
        return LESSONS[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(android.R.layout.simple_list_item_activated_1, parent, false);
        }
        final TextView textView = (TextView) convertView.findViewById(android.R.id.text1);
        textView.setText(getItem(position).getName());
        return convertView;
    }
}
