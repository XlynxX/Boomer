package com.XlynxX.boomer;

import android.os.AsyncTask;
import android.util.Log;

import com.XlynxX.boomer.logging.Logger;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.SocketAddress;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

public abstract class RequestHelper extends AsyncTask<Void, Void, Void>
{
    private final ArrayList<String> allowedCountries = new ArrayList<String>() {{
        add("+371");
    }};
    private Map<String,Object> params = new LinkedHashMap<>();
    private java.net.URL URL;
    private String phone[] = new String[2];
    private boolean callPermission = false;

    public void sendMessage() {
        Logger.Info("Sending message request to " + getClass().getSimpleName());
        this.execute();
    }

    public void sendCall() {
        Logger.Info("Sending call request to " + getClass().getSimpleName());
        this.execute();
    }

    protected URL getURL() {
        return this.URL;
    }

    protected void addParam(String key, String value) {
        params.put(key, value);
    }

    protected void setCallPermission(boolean value) {
        this.callPermission = value;
    }

    protected void setURL(String url) {
        try {
            this.URL = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    protected String getFullNumber() {
        return "+" + phone[0] + phone[1];
    }

    protected String getCountryCode() {
        return phone[0];
    }

    protected String getNumber() {
        return phone[1];
    }

    @Override
    protected Void doInBackground(Void... voids) {
        if (phone[0] == null) { Logger.Warn("Number is not defined"); return null;}
        try {
            StringBuilder postData = new StringBuilder();
            for (Map.Entry<String,Object> param : params.entrySet()) {
                if (postData.length() != 0) postData.append('&');
                postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
                postData.append('=');
                postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
            }
            byte[] postDataBytes = postData.toString().getBytes("UTF-8");

            HttpURLConnection conn = (HttpURLConnection)URL.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("X-CSRFToken", "9tKY96jb358WKiZBMwhz2EcranwljWDbxdqrQCnvqQWXNGbIvtfEQQLCbrzA8ssj");
            conn.setRequestProperty("X-Requested-With", "XMLHttpRequest");
            conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Linux; Android 10; ) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/85.0.4183.101 Mobile Safari/537.36");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
            conn.setDoOutput(true);
            conn.getOutputStream().write(postDataBytes);

            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
                Logger.Info(getClass().getSimpleName() + " Response: " + in.readLine());
            }
            catch (Exception e)
            {
                Logger.Error("Can't get access to [" + this.getClass().getSimpleName() + "]" +
                        " Response code: " + conn.getResponseCode() + " ||" +
                        " Response message: " + conn.getResponseMessage());
                return null;
            }
            //JSONObject data = new JSONObject(json.toString());
        }
        catch (Exception e) {
            Logger.Error("Error in class [" + getClass().getSimpleName() + "]\n" + e.toString());
        }
        return null;
    }

    public void defineNumber(String number) {
        for (String code : allowedCountries)
        {
            if (number.startsWith(code)) {
                phone[0] = code.replace("+", "");
                phone[1] = number.replace(code, "");
                return;
            }
        }
    }
}
