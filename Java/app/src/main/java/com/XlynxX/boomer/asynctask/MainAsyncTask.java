package com.XlynxX.boomer.asynctask;

import android.os.AsyncTask;

import com.XlynxX.boomer.MainActivity;
import com.XlynxX.boomer.RequestHelper;
import com.XlynxX.boomer.logging.Logger;
import com.XlynxX.boomer.proxy.ProxyManager;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MainAsyncTask extends AsyncTask<String, Void, Void> {

    private ArrayList<RequestHelper> services = new ArrayList<>();
    private final ProxyManager proxyManager;
    private Timer t;

    public MainAsyncTask() {
        proxyManager = new ProxyManager();
        registerServices();
    }

    @Override
    protected Void doInBackground(String... strings) {

        t = new Timer();
        t.scheduleAtFixedRate(
                new TimerTask()
                {
                    public void run()
                    {
                        proxyManager.getNewProxy();
                        registerServices();
                        for (RequestHelper service : services)
                        {
                            new Runnable(){
                                public void run() {

                                    service.defineNumber(strings[0]);
                                    service.sendMessage();
                                }
                            }.run();
                        }
                        Logger.Info("All dudes are sent with love");
                        Logger.Info("Waiting for " + MainActivity.getPeriod() + " to send again.");
                    }
                },
                0,
                1000 * MainActivity.getPeriod());
        return null;
    }

    public void stopScheduler() {
        t.cancel();
    }

    private void registerServices() {
        services.clear();
        //services.add(new UtairService());
        //services.add(new FindCloneService());
        //services.add(new ICQService());
        //services.add(new YoulaService());
        //services.add(new OkRuService());
        //services.add(new YandexEdaService());
    }
}
