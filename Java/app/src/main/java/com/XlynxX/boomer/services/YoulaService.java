package com.XlynxX.boomer.services;

import com.XlynxX.boomer.RequestHelper;

public class YoulaService extends RequestHelper {
    public YoulaService() {
        setURL("https://youla.ru/web-api/auth/request_code");
    }
    @Override
    public void sendMessage() {
        addParam("phone", getFullNumber());
        super.sendMessage();
    }
}
