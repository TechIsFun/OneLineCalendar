package com.github.techisfun.onelinecalendar;

import androidx.annotation.NonNull;

import java.util.Date;

/**
 * @author Andrea Maglie
 */
public interface DateSelectionListener {

    boolean onDateSelected(@NonNull Date date);

    boolean onDateUnselected();
}
