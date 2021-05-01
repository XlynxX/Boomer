package com.XlynxX.boomer.logging;

import android.util.Log;

public class Logger
{
    public static void Info(Object message) {
        Log.i("[BOOMER]", message.toString());
    }
    public static void Warn(Object message) {
        Log.w("[BOOMER]", message.toString());
    }
    public static void Error(Object message) {
        Log.e("[BOOMER]", message.toString());
    }
}
