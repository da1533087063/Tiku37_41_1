package com.example.tiku37_41_1.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.tiku37_41_1.R;
import com.example.tiku37_41_1.bean.WDDD;

import java.util.List;

public class WDDDAdapter extends BaseAdapter {
    private List<WDDD> wddds;
    private TextView tvBus;
    private TextView tvName;
    private TextView tvTel;
    private TextView tvLocation;
    private TextView tvDate;

    public WDDDAdapter(List<WDDD> wddds) {
        this.wddds = wddds;
    }

    @Override
    public int getCount() {
        return wddds.size();
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
        view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.wddd_item, null);
        tvBus = (TextView) view.findViewById(R.id.tv_bus);
        tvName = (TextView) view.findViewById(R.id.tv_name);
        tvTel = (TextView) view.findViewById(R.id.tv_tel);
        tvLocation = (TextView) view.findViewById(R.id.tv_location);
        tvDate = (TextView) view.findViewById(R.id.tv_date);
        tvBus.setText("乘车路线:"+wddds.get(i).getBusline());
        tvName.setText("乘车姓名："+wddds.get(i).getName());
        tvTel.setText("手机号码："+wddds.get(i).getTel());
        tvLocation.setText("上车地点："+wddds.get(i).getLocation());
        tvDate.setText("乘车日期："+wddds.get(i).getDate());
        return view;
    }


}
