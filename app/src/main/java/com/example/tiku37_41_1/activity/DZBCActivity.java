package com.example.tiku37_41_1.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.tiku37_41_1.AppClient;
import com.example.tiku37_41_1.R;
import com.example.tiku37_41_1.adapter.DZBCAdapter;
import com.example.tiku37_41_1.bean.DZBC;
import com.example.tiku37_41_1.bean.WDDD;
import com.example.tiku37_41_1.net.OkHttpLo;
import com.example.tiku37_41_1.net.OkHttpTo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

public class DZBCActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView caidan;
    private TextView wodedingdan;
    private ListView listView;
    private DZBCAdapter dzbcAdapter;
    private AppClient appClient;
    private List<DZBC> dzbcs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_d_z_b_c);
        initView();
        appClient= (AppClient) getApplication();
        dzbcs=appClient.getDzbcs();
        huoqu();
        dianji();
    }

    private void dianji() {
        wodedingdan.setOnClickListener(this);
    }

    private void huoqu() {
        OkHttpTo okHttpTo=new OkHttpTo();
        okHttpTo.setUrl("get_bus_info")
                .setJSONObject("UserName","user1")
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        JSONArray jsonArray=jsonObject.optJSONArray("ROWS_DETAIL");
                        for (int i=0;i<jsonArray.length();i++){
                            JSONObject jsonObject1=jsonArray.optJSONObject(i);
                            List<String> busline=new ArrayList<>();
                            JSONArray jsonArray1=jsonObject1.optJSONArray("busline");
                            for (int y=0;y<jsonArray1.length();y++){
                                busline.add(jsonArray1.optString(y));
                            }
                            dzbcs.add(new DZBC(jsonObject1.optInt("id"),
                                    busline,
                                    jsonObject1.optInt("fares"),
                                    jsonObject1.optInt("mileage"),
                                    jsonObject1.optString("time")));
                        }
                        setAdapter();
                    }

                    @Override
                    public void onFailure(IOException e) {

                    }
                }).start();
    }

    private void setAdapter() {
        if (dzbcAdapter==null){
            dzbcAdapter=new DZBCAdapter(dzbcs,this);
            listView.setAdapter(dzbcAdapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent intent=new Intent(DZBCActivity.this, DZBCActivity1.class);
                    intent.putExtra("index",i);
                    startActivity(intent);
                }
            });
        }else {
            dzbcAdapter.notifyDataSetChanged();
        }
    }

    private void initView() {
        caidan = (ImageView) findViewById(R.id.caidan);
        wodedingdan = (TextView) findViewById(R.id.wodedingdan);
        listView = (ListView) findViewById(R.id.list_view);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.wodedingdan:
                startActivity(new Intent(DZBCActivity.this, WDDDActivity.class));
                break;
        }
    }
}