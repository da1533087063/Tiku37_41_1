package com.example.tiku37_41_1.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tiku37_41_1.R;
import com.example.tiku37_41_1.net.OkHttpLo;
import com.example.tiku37_41_1.net.OkHttpTo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

import androidx.appcompat.app.AppCompatActivity;

public class ICCZActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText kahao;
    private TextView yue;
    private EditText shuju;
    private Button chongzhi;
    private Button tuichu;
    private TextView tvName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_i_c_c_z);
        initView();
        setkahao();
        dianji();
    }

    private void dianji() {
        chongzhi.setOnClickListener(this);
        tuichu.setOnClickListener(this);
    }


    private void setkahao() {
        kahao.addTextChangedListener(new TextWatcher() {
            String ickahao;

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                ickahao=kahao.getText().toString();

                if (ickahao.equals("10001")) {
                    huoqu(ickahao);
                }
                if (ickahao.equals("10002")) {
                    huoqu(ickahao);
                }
                if (ickahao.equals("10003")) {
                    huoqu(ickahao);
                }
                if (ickahao.equals("10004")) {
                    huoqu(ickahao);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void huoqu(final String ickahao) {
        OkHttpTo okHttpTo = new OkHttpTo();
        okHttpTo.setUrl("get_vehicle")
                .setJSONObject("UserName", "user1")
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        JSONArray jsonArray = jsonObject.optJSONArray("ROWS_DETAIL");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject1 = jsonArray.optJSONObject(i);
                            if (jsonObject1.optString("plate").equals("鲁A" + ickahao)) {
                                tvName.setText(jsonObject1.optString("owner"));
                                setyue(ickahao);
                            }
                        }
                    }

                    @Override
                    public void onFailure(IOException e) {

                    }
                }).start();
    }

    private void setyue(String ickahao) {
        OkHttpTo okHttpTo=new OkHttpTo();
        okHttpTo.setUrl("get_balance_c")
                .setJSONObject("UserName","user1")
                .setJSONObject("plate","鲁A"+ickahao)
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        yue.setText(jsonObject.optString("balance"));
                    }

                    @Override
                    public void onFailure(IOException e) {

                    }
                }).start();
    }

    private void initView() {
        kahao = (EditText) findViewById(R.id.kahao);
        yue = (TextView) findViewById(R.id.yue);
        shuju = (EditText) findViewById(R.id.shuju);
        chongzhi = (Button) findViewById(R.id.chongzhi);
        tuichu = (Button) findViewById(R.id.tuichu);
        tvName = (TextView) findViewById(R.id.tv_name);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tuichu:
                finish();
                break;
            case R.id.chongzhi:
                String name=tvName.getText().toString();
                String jine=shuju.getText().toString();
                if (name.equals("")){
                    Toast.makeText(ICCZActivity.this,"请输入正确的IC卡卡号",Toast.LENGTH_SHORT).show();
                }if (jine.equals("")){
                Toast.makeText(ICCZActivity.this,"充值金额不能为空",Toast.LENGTH_SHORT).show();
                }
                else {
                    setchongzhi(jine);
                }
                break;
        }
    }

    private void setchongzhi(String jine) {
        OkHttpTo okHttpTo=new OkHttpTo();
        okHttpTo.setUrl("set_balance")
                .setJSONObject("UserName","user1")
                .setJSONObject("plate","鲁A"+kahao.getText().toString())
                .setJSONObject("balance",jine)
                .setDialog(this)
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        if (jsonObject.optString("RESULT").equals("S")){
                            Toast.makeText(ICCZActivity.this,"充值成功",Toast.LENGTH_SHORT).show();
                            shuju.setText("");
                        }
                        setyue(kahao.getText().toString());
                    }

                    @Override
                    public void onFailure(IOException e) {

                    }
                }).start();
    }
}