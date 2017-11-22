package cn.codef1.apidemo.demo;

import android.app.Application;
import android.util.Log;


/**
 * Created by CoderF1 on 2017/11/10.
 */

public class DemoApplication extends Application {

    private static final String TAG = "DemoApplication";

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        Log.d(TAG, "onTerminate: ");
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }
}
