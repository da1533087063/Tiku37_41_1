package com.example.tiku37_41_1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.tiku37_41_1.activity.DZBCActivity;
import com.example.tiku37_41_1.activity.EWMTPActivity;
import com.example.tiku37_41_1.activity.ICCZActivity;
import com.example.tiku37_41_1.activity.XWMTActivity;
import com.example.tiku37_41_1.adapter.SpinnerAdapter;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView caidan;
    private ImageView shuaxin;
    private Spinner spinner;
    private EditText jine;
    private EditText shijian;
    private Button shengcheng;
    private SpinnerAdapter spinnerAdapter;
    private String name[] = {"1", "2", "3", "4"};
    private int index;
    private DrawerLayout drawer;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        setspinner();
        dianji();
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                index = i + 1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        setcaidan();
    }

    private void setcaidan() {
        final List<String>list=new ArrayList<>();
        list.add("");
        list.add("定制班车");
        list.add("新闻媒体");
        list.add("IC卡充值");
        listView.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,list));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (list.get(i)){
                    case "定制班车":
                        startActivity(new Intent(MainActivity.this, DZBCActivity.class));
                        break;
                    case "新闻媒体":
                        startActivity(new Intent(MainActivity.this, XWMTActivity.class));
                        break;
                    case "IC卡充值":
                        startActivity(new Intent(MainActivity.this, ICCZActivity.class));
                        break;
                }
            }
        });
    }

    private void dianji() {
        shengcheng.setOnClickListener(this);
        caidan.setOnClickListener(this);
    }

    private void setspinner() {
        if (spinnerAdapter == null) {
            spinnerAdapter = new SpinnerAdapter(name);
            spinner.setAdapter(spinnerAdapter);
        } else {
            spinnerAdapter.notifyDataSetChanged();
        }
    }

    private void initView() {
        caidan = (ImageView) findViewById(R.id.caidan);
        shuaxin = (ImageView) findViewById(R.id.shuaxin);
        spinner = (Spinner) findViewById(R.id.spinner);
        jine = (EditText) findViewById(R.id.jine);
        shijian = (EditText) findViewById(R.id.shijian);
        shengcheng = (Button) findViewById(R.id.shengcheng);
        drawer = (DrawerLayout) findViewById(R.id.drawer);
        listView = (ListView) findViewById(R.id.list_view);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.shengcheng:
                Intent intent = new Intent(MainActivity.this, EWMTPActivity.class);
                String je = jine.getText().toString();
                String time = shijian.getText().toString();
                if (je.equals("")) {
                    Toast.makeText(MainActivity.this, "输入的金额不能为空", Toast.LENGTH_SHORT).show();
                }
                if (time.equals("")) {
                    Toast.makeText(MainActivity.this, "输入的刷新时间不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    intent.putExtra("jine", Integer.parseInt(je));
                    intent.putExtra("time", Integer.parseInt(time));
                    intent.putExtra("index", index);
                    startActivity(intent);
                }
                break;
            case R.id.caidan:
                drawer.openDrawer(GravityCompat.START);
                break;
        }
    }
}