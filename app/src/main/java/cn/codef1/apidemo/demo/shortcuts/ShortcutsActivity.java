package cn.codef1.apidemo.demo.shortcuts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;

import cn.codef1.apidemo.demo.R;

/**
 * Created by CoderF1 on 2017/10/17.
 */

public class ShortcutsActivity extends AppCompatActivity {

    private final static String ACTION_1 = "action1";
    private final static String ACTION_2 = "action2";
    private final static String ACTION_3 = "action3";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shortcuts);

        TextView textView = findViewById(R.id.textview);

        if(getIntent() == null){
            return;
        }

        if(TextUtils.isEmpty(getIntent().getAction())){
            return;
        }
        switch (getIntent().getAction()){
            case ACTION_1:
                textView.setText(ACTION_1);
                break;
            case ACTION_2:
                textView.setText(ACTION_2);
                break;
            case ACTION_3:
                textView.setText(ACTION_3);
                break;
            default:
                break;
        }
    }
}