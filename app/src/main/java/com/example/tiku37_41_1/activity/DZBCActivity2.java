package com.example.tiku37_41_1.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tiku37_41_1.R;

import androidx.appcompat.app.AppCompatActivity;

public class DZBCActivity2 extends AppCompatActivity {

    private ImageView caidan;
    private GridView girdView;
    private TextView riqi;
    private Button xiayibu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_d_z_b_c2);
        initView();
    }

    private void initView() {
        caidan = (ImageView) findViewById(R.id.caidan);
        girdView = (GridView) findViewById(R.id.girdView);
        riqi = (TextView) findViewById(R.id.riqi);
        xiayibu = (Button) findViewById(R.id.xiayibu);
    }
}