package com.github.techisfun.onelinecalendar;

import android.support.annotation.NonNull;

import java.util.Date;

interface OnDateClickListener {

    boolean onDateClicked(@NonNull Date date);

}
