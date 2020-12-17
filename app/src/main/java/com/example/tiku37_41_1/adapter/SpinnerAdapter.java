package com.example.tiku37_41_1.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.tiku37_41_1.R;

public class SpinnerAdapter extends BaseAdapter {
    private String [] name;

    public SpinnerAdapter(String[] name) {
        this.name = name;
    }

    @Override
    public int getCount() {
        return name.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view= LayoutInflater.from(viewGroup.getContext()).inflate(android.R.layout.simple_spinner_dropdown_item,null);
        TextView textView=view.findViewById(android.R.id.text1);
        textView.setText(name[i]);
        textView.setTextSize(28);
        textView.setTextColor(Color.BLACK);
        return view;
    }
}
