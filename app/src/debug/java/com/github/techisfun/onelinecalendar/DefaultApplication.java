package com.github.techisfun.onelinecalendar;

import android.os.StrictMode;

import timber.log.Timber;

/**
 *
 */
public class DefaultApplication extends OnelinecalendarApplication {

    @Override
    void initialize() {
        Timber.plant(new Timber.DebugTree());

        // Detect all kind of problems and log it
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .build());

        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .build());
    }
}