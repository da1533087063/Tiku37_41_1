package com.example.tiku37_41_1.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.tiku37_41_1.R;

import java.util.List;

public class XWMTAdapter extends BaseAdapter {
    private List<String> xwmt;

    public XWMTAdapter(List<String> xwmt) {
        this.xwmt = xwmt;
    }

    @Override
    public int getCount() {
        return xwmt.size();
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
        view= LayoutInflater.from(viewGroup.getContext()).inflate(android.R.layout.simple_list_item_1,null);
        TextView textView=view.findViewById(android.R.id.text1);
        textView.setText(xwmt.get(i));
        textView.setTextSize(30);
        textView.setTextColor(Color.BLACK);
        textView.setPadding(40,20,0,20);
        return view;
    }
}
