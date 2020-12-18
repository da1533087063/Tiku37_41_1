package com.example.tiku37_41_1.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.tiku37_41_1.R;
import com.example.tiku37_41_1.adapter.XWMTAdapter;
import com.example.tiku37_41_1.net.OkHttpLo;
import com.example.tiku37_41_1.net.OkHttpTo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class XWMTActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView caidan;
    private TextView keji;
    private TextView jiaoyu;
    private TextView tiyu;
    private ListView listView;
    private List<String> xwmt;
    private XWMTAdapter xwmtAdapter;
    private Handler handler=new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message message) {
            xwmtAdapter=new XWMTAdapter((List<String>) message.obj);
            listView.setAdapter(xwmtAdapter);
            return false;
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_x_w_m_t);
        initView();
        huoqu(1);
        dianji();
    }

    private void dianji() {
        caidan.setOnClickListener(this);
        keji.setOnClickListener(this);
        jiaoyu.setOnClickListener(this);
        tiyu.setOnClickListener(this);
    }

    private void huoqu(int i) {
        OkHttpTo okHttpTo=new OkHttpTo();
        okHttpTo.setUrl("get_all_news")
                .setJSONObject("UserName","user1")
                .setJSONObject("type",i)
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        xwmt=new ArrayList<>();
                        JSONArray jsonArray=jsonObject.optJSONArray("news");
                        for (int i=0;i<jsonArray.length();i++){
                            xwmt.add(jsonArray.optString(i));
                        }
                        Message msg=new Message();
                        msg.obj=xwmt;
                        handler.sendMessage(msg);
                    }

                    @Override
                    public void onFailure(IOException e) {

                    }
                }).start();
    }

    private void initView() {
        caidan = (ImageView) findViewById(R.id.caidan);
        keji = (TextView) findViewById(R.id.keji);
        jiaoyu = (TextView) findViewById(R.id.jiaoyu);
        tiyu = (TextView) findViewById(R.id.tiyu);
        listView = (ListView) findViewById(R.id.list_view);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.caidan:
                finish();
                break;
            case R.id.keji:
                keji.setBackgroundResource(R.drawable.line_text);
                jiaoyu.setBackgroundResource(0);
                tiyu.setBackgroundResource(0);
                huoqu(1);
                break;
            case R.id.jiaoyu:
                keji.setBackgroundResource(0);
                jiaoyu.setBackgroundResource(R.drawable.line_text);
                tiyu.setBackgroundResource(0);
                huoqu(2);
                break;
            case R.id.tiyu:
                keji.setBackgroundResource(0);
                jiaoyu.setBackgroundResource(0);
                tiyu.setBackgroundResource(R.drawable.line_text);
                huoqu(3);
                break;
        }
    }
}