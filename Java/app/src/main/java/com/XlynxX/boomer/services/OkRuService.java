package com.XlynxX.boomer.services;

import com.XlynxX.boomer.RequestHelper;

public class OkRuService extends RequestHelper
{
    public OkRuService() {
        setURL("https://ok.ru/dk?cmd=AnonymRegistrationEnterPhone&st.cmd=anonymRegistrationEnterPhone");
    }

    @Override
    public void sendMessage() {
        addParam("st.r.phone", getFullNumber());
        super.sendMessage();
    }

    @Override
    public void sendCall() {
        //super.sendCall();
    }
}
