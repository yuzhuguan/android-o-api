package cn.codef1.apidemo.demo.app;

import android.app.Notification;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

import cn.codef1.apidemo.demo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by CoderF1 on 2017/10/16.
 */

public class NotificationTimeout extends AppCompatActivity {

    private final static long DEFAULT_DURATION = 5000;

    @BindView(R.id.duration)
    EditText duration;
    @BindView(R.id.bnt_send)
    Button bntSend;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_timeout);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.bnt_send)
    public void onViewClicked() {
        NotificationHelper notificationHelper = new NotificationHelper(this);
        Notification.Builder builder = notificationHelper.getNotification1(this,"Test timeout", "api setTimeoutAfter");
        builder.setTimeoutAfter(TextUtils.isEmpty(duration.getText().toString()) ? DEFAULT_DURATION : Integer.parseInt(duration.getText().toString())*1000);
        notificationHelper.notify(2000, builder);
    }
}
