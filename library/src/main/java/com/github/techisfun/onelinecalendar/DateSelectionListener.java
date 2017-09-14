package com.github.techisfun.onelinecalendar;

import android.support.annotation.NonNull;

import java.util.Date;

/**
 * @author Andrea Maglie
 */
public interface DateSelectionListener {

    boolean onDateSelected(@NonNull Date date);

    void onDateUnselected();
}
