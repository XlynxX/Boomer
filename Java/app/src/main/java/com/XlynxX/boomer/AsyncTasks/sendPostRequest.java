package com.XlynxX.boomer.AsyncTasks;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import com.XlynxX.boomer.Request;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Map;

public class sendPostRequest extends AsyncTask<Request, Void, Void> {

    @Override
    protected Void doInBackground(Request... requests) {
        try {
            Log.v("[BOOMER]", "TRYING TO SEND");
            URL url = new URL("https://b.utair.ru/api/v1/login/");
            Map<String,Object> params = new LinkedHashMap<>();
            params.put("login", requests[0].getPhoneNumber());
            if (requests[0].isCallAllowed()) {
                params.put("confirmation_type", "call_code");
            }
            else {
                params.put("confirmation_type", "message_code");
            }

            StringBuilder postData = new StringBuilder();
            for (Map.Entry<String,Object> param : params.entrySet()) {
                if (postData.length() != 0) postData.append('&');
                postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
                postData.append('=');
                postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
            }
            byte[] postDataBytes = postData.toString().getBytes("UTF-8");

            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
            conn.setDoOutput(true);
            conn.getOutputStream().write(postDataBytes);

            Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
        }
        catch (Exception e) {
            Log.v("[BOOMER]", e.toString());
        }
        return null;
    }
}
