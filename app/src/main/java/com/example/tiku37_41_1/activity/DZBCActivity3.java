package com.example.tiku37_41_1.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tiku37_41_1.AppClient;
import com.example.tiku37_41_1.R;
import com.example.tiku37_41_1.adapter.SpinnerAdapter;
import com.example.tiku37_41_1.bean.DZBC;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

public class DZBCActivity3 extends AppCompatActivity implements View.OnClickListener {

    private ImageView caidan;
    private TextView daolu;

    private Spinner spinner;
    private Button xiayibu;
    private int index,index1;
    private List<DZBC> dzbcs;
    private AppClient appClient;
    private EditText xingming;
    private EditText shoujihao;
    private SpinnerAdapter spinnerAdapter;
    private String [] name;
    private String date;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_d_z_b_c3);
        initView();
        appClient = (AppClient) getApplication();
        dzbcs = appClient.getDzbcs();
        index = getIntent().getIntExtra("index", 0);
        Log.d("bbbbbbbbbbbbbbbbbbbbb", "onCreate: "+(dzbcs.get(index).getBusline().size()-1));
        date=getIntent().getStringExtra("date");
        setspinner();
        setxiugai();
        dainji();
    }

    private void setspinner() {
        name=new String[dzbcs.get(index).getBusline().size()];
        for (int i=0;i<dzbcs.get(index).getBusline().size();i++){
            name[i]=dzbcs.get(index).getBusline().get(i);
        }
        if (spinnerAdapter==null){
            spinnerAdapter=new SpinnerAdapter(name);
            spinner.setAdapter(spinnerAdapter);
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    index1=i;
                    Log.d("bbbbbbbbb", "onItemSelected: "+name[index1]);
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
        }else {
            spinnerAdapter.notifyDataSetChanged();
        }
    }

    private void dainji() {
        xiayibu.setOnClickListener(this);
        caidan.setOnClickListener(this);
    }

    private void setxiugai() {
        daolu.setText(dzbcs.get(index).getBusline().get(0) + "——" + dzbcs.get(index).getBusline().get(dzbcs.get(index).getBusline().size() - 1));

    }

    private void initView() {
        caidan = (ImageView) findViewById(R.id.caidan);
        daolu = (TextView) findViewById(R.id.daolu);

        spinner = (Spinner) findViewById(R.id.spinner);
        xiayibu = (Button) findViewById(R.id.xiayibu);
        xingming = (EditText) findViewById(R.id.xingming);
        shoujihao = (EditText) findViewById(R.id.shoujihao);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.caidan:
                finish();
                break;
            case R.id.xiayibu:
                String name1=xingming.getText().toString();
                String number=shoujihao.getText().toString();
                if (name1.equals("")){
                    Toast.makeText(DZBCActivity3.this,"乘客姓名不能为空",Toast.LENGTH_SHORT).show();
                }if (number.equals("")){
                    Toast.makeText(DZBCActivity3.this,"手机号码不能为空",Toast.LENGTH_SHORT).show();
            }else {
                Intent intent=new Intent(DZBCActivity3.this,DZBCActivity4.class);
                intent.putExtra("name",name1);
                intent.putExtra("number",number);
                intent.putExtra("index",index);
                intent.putExtra("index1",name[index1]);
                intent.putExtra("date",date);
                startActivity(intent);

            }
                break;
        }
    }
}