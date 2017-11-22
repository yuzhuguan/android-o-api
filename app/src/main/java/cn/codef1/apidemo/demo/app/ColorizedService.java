package cn.codef1.apidemo.demo.app;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.Color;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import cn.codef1.apidemo.demo.MainActivity;

/**
 * Created by CoderF1 on 2017/10/16.
 */

public class ColorizedService extends Service{

    private int motifyID = 1000;
    private MyBinder binder = new MyBinder();

    public void sendNotification(int color) {
        NotificationHelper notificationHelper = new NotificationHelper(this);
        Notification.Builder builder = notificationHelper.getNotification2(this,"Test colorized", "Notification.Builder.setColor()" +
                ", Notification.Builder.setColorized()");
        Intent i = new Intent(this, MainActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent intent = PendingIntent.getActivity(this, 0, i,
                PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(intent);

        builder.setOngoing(true);
        builder.setColor(color);
        builder.setColorized(true);
//        notificationHelper.notify(4000, builder);
        startForeground(motifyID++, builder.build());
    }

    public class MyBinder extends Binder {

        public ColorizedService getService(){
            return ColorizedService.this;
        }
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }


}
