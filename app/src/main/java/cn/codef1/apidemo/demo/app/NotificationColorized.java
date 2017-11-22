package cn.codef1.apidemo.demo.app;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Color;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;

import cn.codef1.apidemo.demo.MainActivity;
import cn.codef1.apidemo.demo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by CoderF1 on 2017/10/16.
 */

public class NotificationColorized extends AppCompatActivity {

    @BindView(R.id.red)
    Button red;
    @BindView(R.id.green)
    Button green;

    private ColorizedService bindService = null;

    private ServiceConnection conn = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

            ColorizedService.MyBinder binder = (ColorizedService.MyBinder) service;
            bindService = binder.getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_colorized);
        ButterKnife.bind(this);
        Intent intent = new Intent(this, ColorizedService.class);
        bindService(intent,conn,BIND_AUTO_CREATE);
    }

    @OnClick({R.id.red, R.id.green})
    public void onViewClicked(View view) {

        switch (view.getId()) {
            case R.id.red:
                if(bindService != null){
                    bindService.sendNotification(Color.RED);
                }
                break;
            case R.id.green:
                if(bindService != null){
                    bindService.sendNotification(Color.GREEN);
                }
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(conn);
    }
}
