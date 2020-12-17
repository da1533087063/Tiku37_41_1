package com.example.tiku37_41_1.net;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import androidx.annotation.NonNull;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkHttpTo extends Thread {
    private String Url="http:192.168.155.250:8080/traffic/";
    private int time;
    private boolean isloop;
    private JSONObject jsonObject=new JSONObject();
    private ProgressDialog dialog;
    private OkHttpLo okHttpLo;
    private Handler handler=new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message message) {
            if (dialog!=null&&dialog.isShowing()){
                dialog.dismiss();
            }if (message.what==1){
                okHttpLo.onFailure((IOException) message.obj);
            }else if (message.what==2){
                okHttpLo.onResponse((JSONObject) message.obj);
            }
            return false;
        }
    });
    public OkHttpTo setUrl(String url){
        Url+=url;
        return this;
    }
    public OkHttpTo setDialog(Context context){
        dialog=new ProgressDialog(context);
        dialog.setTitle("提示");
        dialog.setMessage("Loading...");
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        return this;
    }
    public OkHttpTo setisLoop(boolean isloop){
        this.isloop=isloop;
        return this;
    }
    public OkHttpTo setTime(int time){
        this.time=time;
        return this;
    }
    public OkHttpTo setJSONObject(String k,Object v){
        try {
            jsonObject.put(k,v);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return this;
    }
    public OkHttpTo setOkHttpLo(OkHttpLo okHttpLo){
        this.okHttpLo=okHttpLo;
        return this;
    }

    @Override
    public void run() {
        super.run();
        do {
            OkHttpClient client=new OkHttpClient();
            RequestBody requestBody=RequestBody.create(jsonObject.toString(), MediaType.get("application/json;charset=usf-8"));
            Request request=new Request.Builder()
                    .url(Url)
                    .post(requestBody)
                    .build();
            client.newCall(request)
                    .enqueue(new Callback() {
                        @Override
                        public void onFailure(@NotNull Call call, @NotNull IOException e) {
                           Message msg=new Message();
                           msg.what=1;
                           msg.obj=e;
                           if (dialog!=null){
                               handler.sendMessageDelayed(msg,600);
                           }else {
                               handler.sendMessage(msg);
                           }
                        }

                        @Override
                        public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                            Message msg=new Message();
                            msg.what=2;
                            try {
                                msg.obj=new JSONObject(response.body().string());
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            if (msg.obj==null){
                                msg.obj=new JSONObject();
                            }
                            if (dialog!=null){
                                handler.sendMessageDelayed(msg,600);
                            }else {
                                handler.sendMessage(msg);
                            }
                        }
                    });
            try {
                Thread.sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }while (isloop);
    }
}
