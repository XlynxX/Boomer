package com.XlynxX.boomer.proxy;

import com.XlynxX.boomer.logging.Logger;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.URL;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Map;

public class ProxyManager
{
    private static URL URL;
    private Proxy proxy;

    public ProxyManager()
    {
        try {
            URL = new URL("https://gimmeproxy.com/api/getProxy?");
        }
        catch (Exception ignore) { Logger.Error("Can't initialize proxy manager link"); }
    }

    public void getNewProxy() {
        try {
            Map<String,Object> params = new LinkedHashMap<>();
            params.put("curl", "true");
            params.put("protocol", "http");
            params.put("supportsHttps", "true");

            StringBuilder postData = new StringBuilder();
            for (Map.Entry<String,Object> param : params.entrySet()) {
                if (postData.length() != 0) postData.append('&');
                postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
                postData.append('=');
                postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
            }
            byte[] postDataBytes = postData.toString().getBytes("UTF-8");

            HttpURLConnection conn = (HttpURLConnection)URL.openConnection();
            conn.setRequestMethod("GET");
            conn.setDoOutput(true);
            //conn.getOutputStream().write(postDataBytes);

            //BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            Logger.Error("Can't get access to [" + this.getClass().getSimpleName() + "]" +
                    " Response code: " + conn.getResponseCode() + " ||" +
                    " Response message: " + conn.getResponseMessage());
            //Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress())
    } catch (Exception e)
        {
            Logger.Error("Cant get proxy: " + e);
        }
    }
}
