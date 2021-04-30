package com.XlynxX.boomer;

public class Request {
    private String phoneNumber;
    private boolean callPermission = false;

    public String getPhoneNumber() { return phoneNumber; }
    public boolean isCallAllowed() { return callPermission; }

    public void setCallPermission(boolean callPermission) {
        this.callPermission = callPermission;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
