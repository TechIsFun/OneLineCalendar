package com.github.techisfun.onelinecalendar;

import android.app.Application;

/**
 * Base application class
 */
public abstract class OnelinecalendarApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initialize();
    }

   /**
    *
    */
    abstract void initialize();
}