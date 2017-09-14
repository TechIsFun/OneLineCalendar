package com.github.techisfun.onelinecalendar;

import android.support.annotation.NonNull;

import java.util.Date;

/**
 * @author Andrea Maglie
 */
interface DateSelectionListener {

    boolean onDateSelected(@NonNull Date date);

    void onDateUnselected();
}
