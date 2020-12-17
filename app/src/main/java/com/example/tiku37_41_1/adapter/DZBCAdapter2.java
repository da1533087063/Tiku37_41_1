package com.example.tiku37_41_1.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.tiku37_41_1.bean.RL;

import java.util.List;

public class DZBCAdapter2 extends BaseAdapter {
    private List<RL> rls;
    private Context context;
    private ClickItem clickItem;
    public interface 

    public DZBCAdapter2(List<RL> rls, Context context) {
        this.rls = rls;
        this.context = context;
    }

    @Override
    public int getCount() {
        return rls.size();
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
        return null;
    }
}
