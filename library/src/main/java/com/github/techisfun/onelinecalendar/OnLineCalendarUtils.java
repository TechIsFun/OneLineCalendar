package com.github.techisfun.onelinecalendar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.Locale;

/**
 * @author Andrea Maglie
 */
abstract class OnLineCalendarUtils {

    @NonNull
    static String capitalize(@Nullable String s) {
        if (isEmpty(s)) {
            return "";
        }

        return s.substring(0, 1).toUpperCase(Locale.getDefault()) + s.substring(1);
    }

    static boolean isEmpty(String s) {
        return s == null || s.length() == 0;
    }

}
