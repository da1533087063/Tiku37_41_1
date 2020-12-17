package com.example.tiku37_41_1;

import android.app.Activity;
import android.app.Application;

import com.example.tiku37_41_1.bean.DZBC;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

public class AppClient extends Application {
    private List<DZBC> dzbcs=new ArrayList<>();
    public List<DZBC> getDzbcs(){
        return dzbcs;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        LitePal.initialize(this);
    }
    private static  List<Activity> activities=new ArrayList<>();
    public static  void  finAll(){
        for (int i=0;i<activities.size();i++){
            Activity activity=activities.get(i);
            if (!activity.isFinishing()){
                activity.finish();
            }
        }
    }
}
