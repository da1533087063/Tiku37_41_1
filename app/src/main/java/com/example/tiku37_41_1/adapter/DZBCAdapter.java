package com.example.tiku37_41_1.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tiku37_41_1.R;
import com.example.tiku37_41_1.bean.DZBC;

import java.util.List;

public class DZBCAdapter extends BaseAdapter {
    private List<DZBC> dzbcs;
    private Context context;
    private TextView chehao;
    private TextView daolu;
    private TextView piaojia;
    private TextView licheng;
    private ImageView jiantou;
    private TextView kaishi;
    private TextView jieshu;

    public DZBCAdapter(List<DZBC> dzbcs, Context context) {
        this.dzbcs = dzbcs;
        this.context = context;
    }

    @Override
    public int getCount() {
        return dzbcs.size();
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
        view = LayoutInflater.from(context).inflate(R.layout.dzbc_listview, null);
        chehao = (TextView) view.findViewById(R.id.chehao);
        daolu = (TextView) view.findViewById(R.id.daolu);
        piaojia = (TextView) view.findViewById(R.id.piaojia);
        licheng = (TextView) view.findViewById(R.id.licheng);
        kaishi = (TextView) view.findViewById(R.id.kaishi);
        jieshu = (TextView) view.findViewById(R.id.jieshu);
        chehao.setText(dzbcs.get(i).getId()+"号");
        daolu.setText(dzbcs.get(i).getBusline().get(0)+"——"+dzbcs.get(i).getBusline().get(dzbcs.get(i).getBusline().size()-1));
        piaojia.setText("票价：￥"+dzbcs.get(i).getFares()+".0");
        licheng.setText("里程:"+dzbcs.get(i).getMileage()+".0Km");
        kaishi.setText(dzbcs.get(i).getTime().split("~")[0]);
        jieshu.setText(dzbcs.get(i).getTime().split("~")[1]);
        return view;
    }



}
