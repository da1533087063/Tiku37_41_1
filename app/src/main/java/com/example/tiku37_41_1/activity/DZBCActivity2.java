package com.example.tiku37_41_1.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tiku37_41_1.R;
import com.example.tiku37_41_1.adapter.DZBCAdapter2;
import com.example.tiku37_41_1.bean.RL;
import com.haibin.calendarview.LunarCalendar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

public class DZBCActivity2 extends AppCompatActivity {

    private ImageView caidan;
    private GridView girdView;
    private TextView riqi;
    private Button xiayibu;
    private List<RL> rls;
    private List<Integer> bglist;
    private DZBCAdapter2 dzbcAdapter2;
    private List<CCRQ> ccrqs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_d_z_b_c2);
        initView();
        initDate();
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
           rls.add(new RL(calendar.get(Calendar.DAY_OF_MONTH)+"" , LunarCalendar.getLunarText(calendar1) ,
                   getWeek(calendar) , calendar.get(Calendar.MONTH)+1 , true));
//           rls.add(new RL(calendar.get(Calendar.DAY_OF_MONTH)+"",
//                   ,getWeek(calendar),calendar.get(Calendar.MONTH)+1),true);
           bglist.add(getWeek(calendar));
        }
    }

    private int getWeek(Calendar calendar) {
        int week=calendar.get(Calendar.DAY_OF_WEEK);
        if (week==1||week==7){
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