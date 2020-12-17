package com.example.tiku37_41_1.net;

import org.json.JSONObject;

import java.io.IOException;

public interface OkHttpLo {
    void onResponse(JSONObject jsonObject);

    void onFailure(IOException e);
}
