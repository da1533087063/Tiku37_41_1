package com.example.tiku37_41_1.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.tiku37_41_1.AppClient;
import com.example.tiku37_41_1.R;
import com.example.tiku37_41_1.bean.DZBC;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

public class DZBCActivity1 extends AppCompatActivity implements View.OnClickListener {

    private ImageView caidan;
    private TextView daolu;
    private TextView piaojia;
    private TextView licheng;
    private Button xiayibu;
    private List<DZBC> dzbcs;
    private AppClient appClient;
    private int index;
    private LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_d_z_b_c1);
        initView();
        appClient = (AppClient) getApplication();
        dzbcs = appClient.getDzbcs();
        index = getIntent().getIntExtra("index", 0);
        setxiugai();
        dianji();
    }

    private void dianji() {
        caidan.setOnClickListener(this);
        xiayibu.setOnClickListener(this);
    }

    private void setxiugai() {
        Log.d("aaaaaaaaaaa", "setxiugai: " + dzbcs.size());
        daolu.setText(dzbcs.get(index).getBusline().get(0) + "——" + dzbcs.get(index).getBusline().get(dzbcs.get(index).getBusline().size() - 1));
        piaojia.setText("票价：￥" + dzbcs.get(index).getFares() + ".0");
        licheng.setText("里程:" + dzbcs.get(index).getMileage() + ".0Km");
        if (dzbcs.get(index).getBusline().get(0).equals("光谷金融街")){
            layout.setBackgroundResource(R.mipmap.ditu);
        }else if (dzbcs.get(index).getBusline().get(0).equals("德州职业")){
            layout.setBackgroundResource(R.mipmap.ditu2);
        }
    }

    private void initView() {
        caidan = (ImageView) findViewById(R.id.caidan);
        daolu = (TextView) findViewById(R.id.daolu);
        piaojia = (TextView) findViewById(R.id.piaojia);
        licheng = (TextView) findViewById(R.id.licheng);
        xiayibu = (Button) findViewById(R.id.xiayibu);
        layout = (LinearLayout) findViewById(R.id.layout);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.caidan:
                finish();
                break;
            case R.id.xiayibu:
                startActivity(new Intent(DZBCActivity1.this, DZBCActivity2.class));
                break;
        }
    }
}