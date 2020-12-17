package com.example.tiku37_41_1.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.tiku37_41_1.R;
import com.example.tiku37_41_1.adapter.WDDDAdapter;
import com.example.tiku37_41_1.bean.WDDD;

import org.litepal.LitePal;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

public class WDDDActivity extends AppCompatActivity {

    private ImageView caidan;
    private ListView listView;
    private TextView wushuju;
    private List<WDDD> wddds;
    private WDDDAdapter wdddAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_w_d_d_d);
        initView();
        wddds= LitePal.findAll(WDDD.class);
        setAdapter();
        dianji();
    }

    private void dianji() {
        caidan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void setAdapter() {
        if (wdddAdapter==null){
            wdddAdapter=new WDDDAdapter(wddds);
            listView.setAdapter(wdddAdapter);
        }else {
            wdddAdapter.notifyDataSetChanged();
        }
    }

    private void initView() {
        caidan = (ImageView) findViewById(R.id.caidan);
        listView = (ListView) findViewById(R.id.list_view);
        wushuju = (TextView) findViewById(R.id.wushuju);
    }
}