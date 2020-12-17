package com.example.tiku37_41_1.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.tiku37_41_1.R;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.util.Hashtable;
import java.util.Random;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class EWMTPActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView caidan;
    private ImageView shuaxin;
    private ImageView im1;
    private boolean isloop = true;
    private int time;
    private int jine;
    private int index;
    private String url;
    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message message) {
            Random random = new Random();
            drawImage(url + random.nextInt(100));
            return false;
        }
    });


    private TextView tvInfo;
    private ImageView ivImage2;
    private LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_e_w_m_t_p);
        initView();
        time = getIntent().getIntExtra("time", 0) * 1000;
        jine = getIntent().getIntExtra("jine", 0);
        index = getIntent().getIntExtra("index", 0);
        url = "车辆编号=" + index + ",付费金额=" + jine + "元";
        new Thread(new Runnable() {
            @Override
            public void run() {
                do {
                    handler.sendEmptyMessage(0);
                    try {
                        Thread.sleep(time);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } while (isloop);
            }
        }).start();
        im1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                tvInfo.setText(url);
                return true;
            }
        });
        dianji();
    }

    private void dianji() {
        im1.setOnClickListener(this);
        ivImage2.setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        isloop = false;
    }

    private void drawImage(String s) {
        Hashtable<EncodeHintType, String> hashtable = new Hashtable<>();
        hashtable.put(EncodeHintType.CHARACTER_SET, "utf-8");
        try {
            BitMatrix bitMatrix = new QRCodeWriter().encode(s, BarcodeFormat.QR_CODE, 300, 300, hashtable);
            int[] pix = new int[300 * 300];
            for (int x = 0; x < 300; x++) {
                for (int y = 0; y < 300; y++) {
                    if (bitMatrix.get(x, y)) {
                        pix[y * 300 + x] = 0xff000000;
                    } else {
                        pix[y * 300 + x] = 0xffffffff;
                    }
                }
            }
            Bitmap bitmap = Bitmap.createBitmap(300, 300, Bitmap.Config.ARGB_8888);
            bitmap.setPixels(pix, 0, 300, 0, 0, 300, 300);
            im1.setImageBitmap(bitmap);
            ivImage2.setImageBitmap(bitmap);
        } catch (WriterException e) {
            e.printStackTrace();
        }


    }

    private void initView() {
        caidan = (ImageView) findViewById(R.id.caidan);
        shuaxin = (ImageView) findViewById(R.id.shuaxin);
        im1 = (ImageView) findViewById(R.id.im1);
        tvInfo = (TextView) findViewById(R.id.tv_info);
        ivImage2 = (ImageView) findViewById(R.id.iv_image2);
        layout = (LinearLayout) findViewById(R.id.layout);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.im1:
                ivImage2.setVisibility(View.VISIBLE);
                layout.setVisibility(View.GONE);
                break;
            case R.id.iv_image2:
                ivImage2.setVisibility(View.GONE);
                layout.setVisibility(View.VISIBLE);
                break;
        }
    }
}