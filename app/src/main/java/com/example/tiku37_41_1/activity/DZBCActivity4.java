package com.example.tiku37_41_1.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tiku37_41_1.AppClient;
import com.example.tiku37_41_1.R;
import com.example.tiku37_41_1.bean.DZBC;
import com.example.tiku37_41_1.bean.WDDD;

import java.util.List;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class DZBCActivity4 extends AppCompatActivity implements View.OnClickListener {

    private ImageView caidan;
    private TextView xingming;
    private TextView shouji;
    private TextView didian;
    private TextView riqi;
    private Button tijiao;
    private String name, number;
    private List<DZBC> dzbcs;
    private AppClient appClient;
    private String date;
    private int index;
    private String jiedao;
    private TextView luxian;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_d_z_b_c4);
        initView();
        appClient = (AppClient) getApplication();
        dzbcs = appClient.getDzbcs();
        name = getIntent().getStringExtra("name");
        number = getIntent().getStringExtra("number");
        index = getIntent().getIntExtra("index", 0);
        date = getIntent().getStringExtra("date");
        jiedao = getIntent().getStringExtra("index1");
        setxiugai();
        dianji();
    }

    private void dianji() {
        tijiao.setOnClickListener(this);
        caidan.setOnClickListener(this);
    }

    private void setxiugai() {
        luxian.setText("乘车路线："+ dzbcs.get(index).getBusline().get(0)+"——"+dzbcs.get(index).getBusline().get(dzbcs.get(index).getBusline().size()-1));
        xingming.setText("乘客姓名:" + name);
        shouji.setText("手机号码:" + number);
        didian.setText("乘车地点:" + jiedao);
        riqi.setText("乘车日期:"+date);

    }

    private void initView() {
        caidan = (ImageView) findViewById(R.id.caidan);
        xingming = (TextView) findViewById(R.id.xingming);
        shouji = (TextView) findViewById(R.id.shouji);
        didian = (TextView) findViewById(R.id.didian);
        riqi = (TextView) findViewById(R.id.riqi);
        tijiao = (Button) findViewById(R.id.tijiao);
        luxian = (TextView) findViewById(R.id.luxian);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tijiao:
                WDDD wddd=new WDDD();
                wddd.setDate(date);
                wddd.setTel(number);
                wddd.setLocation(jiedao);
                wddd.setName(name);
                wddd.setBusline(dzbcs.get(index).getBusline().get(0)+"——"+dzbcs.get(index).getBusline().get(dzbcs.get(index).getBusline().size()-1));
                if (wddd.save()){
                    AlertDialog.Builder builder=new AlertDialog.Builder(this);
                    builder.setTitle("提示");
                    builder.setMessage("提交成功");
                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            AppClient.finAll();
                        }
                    });
                    builder.create().show();
                }
                break;
            case R.id.caidan:
                finish();
                break;


        }
    }
}