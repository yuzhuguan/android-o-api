package cn.codef1.apidemo.demo.app;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import cn.codef1.apidemo.demo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by CoderF1 on 2017/10/16.
 */

public class NotificationMessageStyle extends AppCompatActivity {

    @BindView(R.id.style)
    Button style;
    @BindView(R.id.addHistoricMessage)
    Button addHistoricMessage;

    private NotificationManager manager;
    private Notification.MessagingStyle messagingStyle;

    private Notification.MessagingStyle.Message message = new Notification.MessagingStyle.Message("messages[0]", System.currentTimeMillis(), "s0");
    private Notification.MessagingStyle.Message message1 = new Notification.MessagingStyle.Message("messages[1]", System.currentTimeMillis() + 10000, "s1");
    private Notification.MessagingStyle.Message message2 = new Notification.MessagingStyle.Message("messages[2]", System.currentTimeMillis() + 20000, "s2");

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_style);
        ButterKnife.bind(this);
        manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        messagingStyle = new Notification.MessagingStyle("style1")
                .addMessage(message)
                .addMessage(message1)
                .addMessage(message2);
    }

    @OnClick({R.id.style, R.id.addHistoricMessage})
    public void onViewClicked(View view) {
        NotificationHelper notificationHelper = new NotificationHelper(this);
        Notification.Builder builder = notificationHelper.getNotification1(this,"Test NotificationMessageStyle", "api MessagingStyle");
        switch (view.getId()) {
            case R.id.style:
                builder.setContentTitle("2 new messages wtih " + "history 1");
                builder.setContentText("message style");
                builder.setSmallIcon(R.mipmap.ic_launcher);
                builder.setLargeIcon(BitmapFactory.decodeResource(getResources(),android.R.drawable.stat_sys_speakerphone));
                builder.setStyle(messagingStyle)
                        .build();
                manager.notify(5000, builder.build());
                break;
            case R.id.addHistoricMessage:
                builder.setContentTitle("2 new messages wtih " + "history 1");
                builder.setContentText("message style");
                builder.setSmallIcon(R.mipmap.ic_launcher);
                builder.setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher));
                builder.setStyle(messagingStyle.addHistoricMessage(new Notification.MessagingStyle.Message("messages[3]", System.currentTimeMillis() + 20000, "s3")))
                        .build();
                manager.notify(5001, builder.build());
                break;
        }
    }
}
