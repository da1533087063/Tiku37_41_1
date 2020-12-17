package com.example.tiku37_41_1.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.tiku37_41_1.R;
import com.example.tiku37_41_1.bean.RL;

import java.util.List;

public class DZBCAdapter2 extends BaseAdapter {
    private List<RL> rls;
    private Context context;
    private ClickItem clickItem;
    private LinearLayout bgColor;
    private TextView itemSolar;
    private TextView itemLunar;



    public interface ClickItem {
        void myClick(int position, int bg);
    }

    public void setClickItem(ClickItem clickItem) {
        this.clickItem = clickItem;
    }

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
    public View getView(final int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(context).inflate(R.layout.dzbc_item, null);
        bgColor = (LinearLayout) view.findViewById(R.id.bg_color);
        itemSolar = (TextView) view.findViewById(R.id.item_solar);
        itemLunar = (TextView) view.findViewById(R.id.item_lunar);
        final RL rl=rls.get(i);
        itemSolar.setText(rl.getSolar());
        itemLunar.setText(rl.getLunar());
        if (rl.getBg()==0){
            bgColor.setBackgroundResource(R.drawable.rl_0);
        }else if (rl.getBg()==1){
            bgColor.setBackgroundResource(R.drawable.rl_1);
        }else {
            bgColor.setBackgroundResource(R.drawable.rl_2);
        }
        bgColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rl.isEnable()){
                    clickItem.myClick(i,rl.getBg());
                }
            }
        });
        return null;
    }
}
