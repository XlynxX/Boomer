package com.XlynxX.boomer.services;

import com.XlynxX.boomer.RequestHelper;

public class FindCloneService extends RequestHelper
{
    @Override
    public void sendMessage() {}

    @Override
    public void sendCall() {
        setURL("https://findclone.ru/register?phone=" + getFullNumber());
        addParam("phone", getCountryCode() + getNumber());
        super.sendCall();
    }
}
