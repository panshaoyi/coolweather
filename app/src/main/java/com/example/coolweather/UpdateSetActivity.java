package com.example.coolweather;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

/**
 * Created by Administrator on 2021/1/15 0015.
 */

public class UpdateSetActivity extends AppCompatActivity{
    private CheckBox autoUpdateBox;
    private EditText autoUpdateTime;
    private SharedPreferences pref;
    private Button setOk;
    private SharedPreferences.Editor editor;

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, WeatherActivity.class);
        startActivity(intent);
        finish();
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_update);
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        autoUpdateBox = (CheckBox) findViewById(R.id.update);
        autoUpdateTime = (EditText) findViewById(R.id.update_time);
        setOk = (Button) findViewById(R.id.set_ok);
        boolean autoUpdate = pref.getBoolean("auto_update", false);
        int updateTime = pref.getInt("auto_update_time", 8);
        if (autoUpdate) {
            autoUpdateBox.setChecked(true);
            autoUpdateTime.setText(Integer.toString(updateTime));
        }
        setOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor = pref.edit();
                if (autoUpdateBox.isChecked()) {
                    editor.putBoolean("auto_update", true);
                    int time = 8;
                    if (!TextUtils.isEmpty(autoUpdateTime.getText())) {
                        String updataTime = autoUpdateTime.getText().toString();
                        time = Integer.parseInt(updataTime);
                        editor.putInt("auto_update_time", time);
                    }
                    Intent StartIntent = new Intent(UpdateSetActivity.this, AutoUpdateService.class);
                    StartIntent.putExtra("auto_update_time", time);
                    startService(StartIntent);
                    // Log.v("MainActivity", "ServiceStart");

                } else {
                    editor.putBoolean("auto_update", false);
                    Intent stopIntent = new Intent(UpdateSetActivity.this,
                            AutoUpdateService.class);
                    stopService(stopIntent);
                    // Log.v("MainActivity", "StopStart");
                }
                editor.commit();
                Intent intent1 = new Intent(UpdateSetActivity.this,
                        WeatherActivity.class);
                startActivity(intent1);
                finish();
            }
        });

    }
}
