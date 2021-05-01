package com.XlynxX.boomer.asynctask;

import android.os.AsyncTask;
import com.XlynxX.boomer.RequestHelper;
import com.XlynxX.boomer.services.FindCloneService;
import com.XlynxX.boomer.services.UtairService;

import java.util.ArrayList;

public class MainAsyncTask extends AsyncTask<String, Void, Void> {
    private ArrayList<RequestHelper> services = new ArrayList<>();

    public MainAsyncTask() {
        services.add(new UtairService());
        services.add(new FindCloneService());
    }

    @Override
    protected Void doInBackground(String... strings) {
        for (RequestHelper service : services)
        {
            new Runnable(){
                public void run() {
                    service.defineNumber(strings[0]);
                    service.sendCall();
                }
            }.run();
        }
        return null;
    }
}
