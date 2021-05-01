package com.XlynxX.boomer.services;

import com.XlynxX.boomer.RequestHelper;

public class UtairService extends RequestHelper {

    public UtairService() {
        setURL("https://b.utair.ru/api/v1/login/");
    }

    @Override
    public void sendMessage() {
        addParam("login", getFullNumber());
        addParam("confirmation_type", "message_code");
        super.sendMessage();
    }

    @Override
    public void sendCall() {
        addParam("login", getFullNumber());
        addParam("confirmation_type", "call_code");
        super.sendCall();
    }
}
