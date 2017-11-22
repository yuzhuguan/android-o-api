package cn.codef1.apidemo.demo.app;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;
import cn.codef1.apidemo.demo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by CoderF1 on 2017/10/13.
 */
public class NotificationChannel extends AppCompatActivity {

    private static final int NOTI_PRIMARY1 = 1100;
    private static final int NOTI_PRIMARY2 = 1101;
    private static final int NOTI_SECONDARY1 = 1200;
    private static final int NOTI_SECONDARY2 = 1201;

    @BindView(R.id.main_primary_title)
    EditText mainPrimaryTitle;
    @BindView(R.id.main_primary_send1)
    Button mainPrimarySend1;
    @BindView(R.id.main_primary_send2)
    Button mainPrimarySend2;
    @BindView(R.id.main_primary_config)
    ImageButton mainPrimaryConfig;
    @BindView(R.id.main_secondary_title)
    EditText mainSecondaryTitle;
    @BindView(R.id.main_secondary_send1)
    Button mainSecondarySend1;
    @BindView(R.id.main_secondary_send2)
    Button mainSecondarySend2;
    @BindView(R.id.main_secondary_config)
    ImageButton mainSecondaryConfig;
    @BindView(R.id.btnA)
    Button btnA;
    @BindView(R.id.main_footer)
    LinearLayout mainFooter;
    @BindView(R.id.activity_main)
    RelativeLayout activityMain;

    private NotificationHelper notificationHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_channel);
        ButterKnife.bind(this);

        notificationHelper = new NotificationHelper(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    @OnClick({R.id.main_primary_send1, R.id.main_primary_send2, R.id.main_primary_config, R.id.main_secondary_send1, R.id.main_secondary_send2, R.id.main_secondary_config, R.id.btnA})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.main_primary_send1:
                sendNotification(NOTI_PRIMARY1, mainPrimaryTitle != null ? mainPrimaryTitle.getText().toString() : "");
                break;
            case R.id.main_primary_send2:
                sendNotification(NOTI_PRIMARY2, mainPrimaryTitle != null ? mainPrimaryTitle.getText().toString() : "");
                break;
            case R.id.main_primary_config:
                goToNotificationSettings(NotificationHelper.PRIMARY_CHANNEL);
                break;
            case R.id.main_secondary_send1:
                sendNotification(NOTI_SECONDARY1, mainSecondaryTitle != null ? mainSecondaryTitle.getText().toString() : "");
                break;
            case R.id.main_secondary_send2:
                sendNotification(NOTI_SECONDARY2, mainSecondaryTitle != null ? mainSecondaryTitle.getText().toString() : "");
                break;
            case R.id.main_secondary_config:
                goToNotificationSettings(NotificationHelper.SECONDARY_CHANNEL);
                break;
            case R.id.btnA:
                goToNotificationSettings();
                break;
        }
    }

    /**
     * Send activity notifications.
     * @param id The ID of the notification to create
     * *
     * @param title The title of the notification
     */
    private void sendNotification(int id, String title){
        switch (id){
            case NOTI_PRIMARY1:
                notificationHelper.notify(
                        id, notificationHelper.getNotification1(this, title, getString(R.string.primary1_body)));
                break;
            case NOTI_PRIMARY2:
                notificationHelper.notify(
                        id, notificationHelper.getNotification1(this, title, getString(R.string.primary2_body)));
                break;
            case NOTI_SECONDARY1:
                notificationHelper.notify(
                        id, notificationHelper.getNotification2(this, title, getString(R.string.secondary1_body)));
                break;
            case NOTI_SECONDARY2:
                notificationHelper.notify(
                        id, notificationHelper.getNotification2(this, title, getString(R.string.secondary2_body)));
                break;

        }
    }

    /**
     * Send Intent to load system Notification Settings for this app.
     */
    private void goToNotificationSettings() {
        Intent i = new Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS);
        i.putExtra(Settings.EXTRA_APP_PACKAGE, getPackageName());
        startActivity(i);
    }

    /**
     * Send intent to load system Notification Settings UI for a particular channel.
     * @param channel Name of channel to configure
     */
    private void goToNotificationSettings(String channel) {
        Intent i = new Intent(Settings.ACTION_CHANNEL_NOTIFICATION_SETTINGS);
        i.putExtra(Settings.EXTRA_APP_PACKAGE, getPackageName());
        i.putExtra(Settings.EXTRA_CHANNEL_ID, channel);
        startActivity(i);
    }
}
