package com.kadirdogan97.newsexample.viewmodel;

import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;
import androidx.lifecycle.ViewModel;

public class VMSettingsActivity extends ViewModel {
    public final ObservableField<String> isWifiConnected = new ObservableField<String>();
    public final ObservableField<String> batteryText= new ObservableField<String>();
    public final ObservableInt progressLevel = new ObservableInt();
}
