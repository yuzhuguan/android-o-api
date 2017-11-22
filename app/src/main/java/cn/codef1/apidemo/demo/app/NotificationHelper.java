package cn.codef1.apidemo.demo.app;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Color;

import cn.codef1.apidemo.demo.R;

/**
 * Created by CoderF1 on 2017/10/13.
 */

public class NotificationHelper {
    public static final String PRIMARY_CHANNEL = "default";
    public static final String SECONDARY_CHANNEL = "second";

    private NotificationManager manager;

    /**
     * Registers notification channels, which can be used later by individual notifications.
     * @param ctx The application context
     */
    public NotificationHelper(Context ctx){
        manager = (NotificationManager) ctx.getSystemService(Context.NOTIFICATION_SERVICE);
        init(ctx);
    }

    private void init(Context ctx) {
        NotificationChannel chan1 = new NotificationChannel(PRIMARY_CHANNEL,
                ctx.getString(R.string.noti_channel_default), NotificationManager.IMPORTANCE_DEFAULT);
        chan1.setLightColor(Color.GREEN);
        chan1.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
        manager.createNotificationChannel(chan1);

        NotificationChannel chan2 = new NotificationChannel(SECONDARY_CHANNEL,
                ctx.getString(R.string.noti_channel_second), NotificationManager.IMPORTANCE_HIGH);
        chan2.setLightColor(Color.BLUE);
        chan2.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
        manager.createNotificationChannel(chan2);
    }

    /**
     * Get a notification of type 1
     * Provide the builder rather than the notification it's self as useful for making notification
     * changes.
     * @param title the title of the notification
     * *
     * @param body the body text for the notification
     * *
     * @return the builder as it keeps a reference to the notification (since API 24)
     */
    public Notification.Builder getNotification1(Context context, String title,String body) {
        return new Notification.Builder(context.getApplicationContext(), PRIMARY_CHANNEL)
                .setContentTitle(title)
                .setContentText(body)
                .setSmallIcon(getSmallIcon())
                .setAutoCancel(true);
    }

    /**
     * Build notification for secondary channel.
     * @param title Title for notification.
     * *
     * @param body Message for notification.
     * *
     * @return A Notification.Builder configured with the selected channel and details
     */
    public Notification.Builder getNotification2(Context context, String title, String body){
        return new Notification.Builder(context.getApplicationContext(), SECONDARY_CHANNEL)
                .setContentTitle(title)
                .setContentText(body)
                .setSmallIcon(getSmallIcon())
                .setAutoCancel(true);
    }

    /**
     * Send a notification.
     * @param id The ID of the notification
     * *
     * @param notification The notification object
     */
    public void notify(int id, Notification.Builder notification ) {
        manager.notify(id, notification.build());
    }

    /**
     * Get the small icon for this app
     * @return The small icon resource id
     */
    private int getSmallIcon(){
         return android.R.drawable.stat_notify_chat;
    }

}
