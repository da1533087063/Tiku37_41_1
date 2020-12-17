package com.example.tiku37_41_1;

import android.app.Application;

import com.example.tiku37_41_1.bean.DZBC;

import java.util.ArrayList;
import java.util.List;

public class AppClient extends Application {
    private List<DZBC> dzbcs=new ArrayList<>();
    public List<DZBC> getDzbcs(){
        return dzbcs;
    }
}
