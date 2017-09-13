package com.github.techisfun.onelinecalendar;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.format.DateUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * @author Andrea Maglie
 */

class SimpleDate {
    static final int MONTH_TYPE = 1;
    static final int DATE_TYPE = 2;
    private static SimpleDateFormat sMonthFormat = new SimpleDateFormat("MMM", Locale.getDefault());
    private static SimpleDateFormat sDayNameFormat = new SimpleDateFormat("EEE", Locale.getDefault());
    int mDay;
    int mMonth;
    int mYear;
    boolean mIsToday;
    private int mType;
    private Date mDate;

    static SimpleDate monthFrom(@NonNull Calendar calendar) {
        SimpleDate simpleDate = new SimpleDate();
        simpleDate.mDay = 0;
        simpleDate.mMonth = calendar.get(Calendar.MONTH);
        simpleDate.mYear = calendar.get(Calendar.YEAR);
        simpleDate.mType = MONTH_TYPE;
        simpleDate.mDate = calendar.getTime();
        return simpleDate;
    }

    static SimpleDate dateFrom(@NonNull Calendar dayToDisplay, Calendar today) {
        SimpleDate simpleDate = new SimpleDate();
        simpleDate.mDay = dayToDisplay.get(Calendar.DATE);
        simpleDate.mMonth = dayToDisplay.get(Calendar.MONTH);
        simpleDate.mYear = dayToDisplay.get(Calendar.YEAR);
        simpleDate.mType = DATE_TYPE;
        simpleDate.mDate = dayToDisplay.getTime();
        simpleDate.mIsToday = equals(dayToDisplay, today);
        return simpleDate;
    }

    private static boolean equals(@NonNull Calendar c1, @NonNull Calendar c2) {
        return c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR) &&
                c1.get(Calendar.MONTH) == c2.get(Calendar.MONTH) &&
                c1.get(Calendar.DATE) == c2.get(Calendar.DATE);
    }

    int getType() {
        return mType;
    }

    @Override
    public String toString() {
        if (mType == DATE_TYPE) {
            return String.valueOf(mDay);
        } else if (mType == MONTH_TYPE) {
            return sMonthFormat.format(mDate);
        } else {
            return super.toString();
        }
    }

    CharSequence getDayNameFormatted(Context context) {
        if (mIsToday) {
            return context.getString(R.string.today);
        }

        return DateUtils.formatDateTime(context,
                mDate.getTime(),
                DateUtils.FORMAT_ABBREV_WEEKDAY | DateUtils.FORMAT_SHOW_WEEKDAY);
    }

    String getDayNumberFormatted() {
        return String.valueOf(mDay);
    }

}
