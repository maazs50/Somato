package com.example.somato;

import android.content.Context;

import androidx.multidex.MultiDexApplication;

import com.example.somato.network.BaseRetrofitHandler;
import com.example.somato.network.NetworkChangeReceiver;


public class SmtApplication extends MultiDexApplication {
    private static final String TAG = SmtApplication.class.getSimpleName();
    private static Context sAppContext;
    private static SmtApplication mInstance;
    public static synchronized SmtApplication getInstance() {
        return mInstance;
    }
    public static Context getsAppContext() {
        return sAppContext.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        sAppContext = this;

        BaseRetrofitHandler.getInstance().setupRetrofitAndOkHttp();
    }
    public void setConnectivityListener(NetworkChangeReceiver.ConnectivityReceiverListener listener) {
        NetworkChangeReceiver.connectivityReceiverListener = listener;
    }
}
