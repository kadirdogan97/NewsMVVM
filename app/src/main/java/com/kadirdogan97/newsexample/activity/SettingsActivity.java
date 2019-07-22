package com.kadirdogan97.newsexample.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModel;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.os.Bundle;

import com.kadirdogan97.newsexample.R;
import com.kadirdogan97.newsexample.databinding.ActivitySettingsBinding;
import com.kadirdogan97.newsexample.viewmodel.VMSettingsActivity;

public class SettingsActivity extends AppCompatActivity {

    private ActivitySettingsBinding mBinding;
    private BatteryBroadcastReceiver mBatteryReceiver;
    private WifiBroadcastReceiver mWifiReceiver;
    private VMSettingsActivity model;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_settings);
        this.model = new VMSettingsActivity();
        mBinding.setStatusModel(this.model);
        mBatteryReceiver = new BatteryBroadcastReceiver();
        mWifiReceiver = new WifiBroadcastReceiver();
    }

    @Override
    protected void onStart() {
        registerReceiver(mBatteryReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        IntentFilter intentFilter = new IntentFilter(WifiManager.WIFI_STATE_CHANGED_ACTION);
        registerReceiver(mWifiReceiver, new IntentFilter(intentFilter));
        super.onStart();
    }

    @Override
    protected void onStop() {
        unregisterReceiver(mBatteryReceiver);
        unregisterReceiver(mWifiReceiver);
        super.onStop();
    }

    private class BatteryBroadcastReceiver extends BroadcastReceiver {
        private final static String BATTERY_LEVEL = "level";
        @Override
        public void onReceive(Context context, Intent intent) {
            int level = intent.getIntExtra(BATTERY_LEVEL, 0);
            model.batteryText.set(getString(R.string.battery_level) + "%" + level);
            model.progressLevel.set(level);
            mBinding.batteryLevel.setText(getString(R.string.battery_level) + "%" + level);
            mBinding.progressBar.setProgress(level);
        }
    }
    private class WifiBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            int wifiState = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE, WifiManager.WIFI_STATE_UNKNOWN);
            switch (wifiState){
                case WifiManager.WIFI_STATE_ENABLED:
                    model.isWifiConnected.set("Wifi is ON");
                    break;
                case WifiManager.WIFI_STATE_DISABLED:
                    model.isWifiConnected.set("Wifi is OFF");
                    break;
            }
        }
    }

}
