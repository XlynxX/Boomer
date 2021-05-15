package com.XlynxX.boomer.services;

import com.XlynxX.boomer.RequestHelper;

public class YandexEdaService extends RequestHelper
{
    public YandexEdaService() {
        setURL("https://eda.yandex/api/v1/user/request_authentication_code/");
    }

    @Override
    public void sendMessage() {
        addParam("phone_number", getFullNumber());
        super.sendMessage();
    }

    @Override
    public void sendCall() {
        //super.sendCall();
    }
}
