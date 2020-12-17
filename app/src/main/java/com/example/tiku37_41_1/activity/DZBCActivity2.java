package com.example.tiku37_41_1.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tiku37_41_1.R;
import com.example.tiku37_41_1.adapter.DZBCAdapter2;
import com.example.tiku37_41_1.bean.DZBC;
import com.example.tiku37_41_1.bean.RL;
import com.haibin.calendarview.LunarCalendar;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

public class DZBCActivity2 extends AppCompatActivity implements View.OnClickListener {

    private ImageView caidan;
    private GridView girdView;
    private TextView riqi;
    private Button xiayibu;
    private List<RL> rls;
    private List<Integer> bglist;
    private DZBCAdapter2 dzbcAdapter2;
    private List<CCRQ> ccrqs;
    private int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_d_z_b_c2);
        initView();
        initDate();
        index=getIntent().getIntExtra("index",0);
       dianji();
    }

    private void dianji() {
        xiayibu.setOnClickListener(this);
        caidan.setOnClickListener(this);
    }

    private void initDate() {
        rls=new ArrayList<>();
        bglist=new ArrayList<>();
        ccrqs=new ArrayList<>();
        Calendar calendar=Calendar.getInstance();
        int week=calendar.get(Calendar.DAY_OF_WEEK);
        for (int i=0;i<week-1;i++){
            rls.add(new RL("","",1,0,false));
            bglist.add(1);
        }
        for (int i=0;i<42;i++){
           com.haibin.calendarview.Calendar calendar1=new com.haibin.calendarview.Calendar();
           calendar1.setYear(calendar.get(Calendar.YEAR));
           calendar1.setMonth(calendar.get(Calendar.MONTH)+1);
           calendar1.setDay(calendar.get(Calendar.DAY_OF_MONTH));
           rls.add(new RL(calendar.get(Calendar.DAY_OF_MONTH) + "", LunarCalendar.getLunarText(calendar1)
                   ,getWeek(calendar), calendar.get(Calendar.MONTH) + 1, true));
            Log.d("aaaaaaaa", "initDate: "+rls.toString());
           bglist.add(getWeek(calendar));
           calendar.add(Calendar.DAY_OF_MONTH,1);
        }
        if (rls.size()!=42){
            int j=49-rls.size();
            for (int i=0;i<j;i++){
                rls.add(new RL("","",1,0,false));
                bglist.add(1);
            }
        }
        dzbcAdapter2=new DZBCAdapter2(rls,this);
        girdView.setAdapter(dzbcAdapter2);
        dzbcAdapter2.setClickItem(new DZBCAdapter2.ClickItem() {
            @Override
            public void myClick(int position, int bg) {
                RL rl=rls.get(position);
                if (bg==0||bg==1){
                    rl.setBg(2);
                    rls.set(position,rl);
                    ccrqs.add(new CCRQ(position,"2020-"+rls.get(position).getMonth()+"-"+rls.get(position).getSolar()));
                }else {
                    rl.setBg(bglist.get(position));
                    rls.set(position,rl);
                    for (int i=0;i<ccrqs.size();i++){
                        if (ccrqs.get(i).getId()==position){
                            ccrqs.remove(i);
                        }
                    }
                }
                setDateTime();
                dzbcAdapter2.notifyDataSetChanged();
            }
        });
    }

    private void setDateTime() {
        Collections.sort(ccrqs, new Comparator<CCRQ>() {
            @Override
            public int compare(CCRQ o1, CCRQ o2) {
                return o1.getId()-o2.getId();
            }
        });
        String str="";
        for (int i=0;i<ccrqs.size();i++){
            if (i==0){
                str=ccrqs.get(i).getMsg();
            }else {
                str+=","+ccrqs.get(i).getMsg();
            }
        }
        riqi.setText(str);
    }

    private int getWeek(Calendar calendar) {
        int week=calendar.get(Calendar.DAY_OF_WEEK);
        if (week==1 || week==7){
            return 1;
        }else {
            return 0;
        }
    }


    private void initView() {
        caidan = (ImageView) findViewById(R.id.caidan);
        girdView = (GridView) findViewById(R.id.girdView);
        riqi = (TextView) findViewById(R.id.riqi);
        xiayibu = (Button) findViewById(R.id.xiayibu);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.caidan:
                finish();
                break;
            case R.id.xiayibu:
                Intent intent=new Intent(DZBCActivity2.this,DZBCActivity3.class);
                intent.putExtra("index", index);
                intent.putExtra("date",riqi.getText());
                startActivity(intent);
                break;
        }
    }

    class CCRQ{
        private int id;
        private String msg;

        public CCRQ(int id, String msg) {
            this.id = id;
            this.msg = msg;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
    }
}