package cn.codef1.apidemo.demo.app;

import android.app.*;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;

import cn.codef1.apidemo.demo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by CoderF1 on 2017/10/16.
 */

public class NotificationLauncher extends AppCompatActivity {

    @BindView(R.id.hide)
    Button hide;
    @BindView(R.id.show)
    Button show;
    @BindView(R.id.control_number)
    Button controlNumber;

    private NotificationManager manager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_launcher);
        ButterKnife.bind(this);

        manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
    }

    @OnClick({R.id.hide, R.id.control_number, R.id.show})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.hide:
               android.app.NotificationChannel chan1 = new android.app.NotificationChannel("Test Hide",
                       getString(R.string.noti_channel_default), NotificationManager.IMPORTANCE_DEFAULT);
                chan1.setLightColor(Color.GREEN);
                chan1.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
                chan1.setShowBadge(false);
                manager.createNotificationChannel(chan1);

                Notification.Builder builder = new Notification.Builder(getApplicationContext(), "Test Hide")
                        .setContentTitle("测试不显示在桌面")
                        .setContentText("测试不显示在桌面内容")
                        .setSmallIcon(android.R.drawable.stat_notify_chat)
                        .setAutoCancel(true);
                manager.notify(3001, builder.build());
                break;
            case R.id.show:
               android.app.NotificationChannel chan2 = new android.app.NotificationChannel("Test Show",
                       getString(R.string.noti_channel_second), NotificationManager.IMPORTANCE_DEFAULT);
                chan2.setLightColor(Color.GREEN);
                chan2.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
                chan2.setShowBadge(true);
                manager.createNotificationChannel(chan2);

                Notification.Builder builder2 = new Notification.Builder(getApplicationContext(), "Test Show")
                        .setContentTitle("测试显示在桌面")
                        .setContentText("测试显示在桌面内容")
                        .setSmallIcon(android.R.drawable.stat_notify_chat)
                        .setAutoCancel(true);
                manager.notify(3002, builder2.build());
                break;
            case R.id.control_number:
                NotificationHelper notificationHelper = new NotificationHelper(this);
                Notification.Builder builder3 = notificationHelper.getNotification1(this,"桌面显示数目", "桌面显示数目为20");
                builder3.setNumber(20);
                notificationHelper.notify(3003, builder3);
                break;
        }
    }
}
