package com.github.techisfun.onelinecalendar;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * @author Andrea Maglie
 */

class SimpleDate {
    private static SimpleDateFormat sMonthFormat = new SimpleDateFormat("MMM", Locale.getDefault());
    private static final int MONTH_TYPE = 1;
    private static final int DATE_TYPE = 2;
    private int mDay;
    private int mMonth;
    private int mYear;
    private int mType;
    private Date mDate;

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

    static SimpleDate monthFrom(Calendar calendar) {
        SimpleDate simpleDate = new SimpleDate();
        simpleDate.mDay = 0;
        simpleDate.mMonth = calendar.get(Calendar.MONTH);
        simpleDate.mYear = calendar.get(Calendar.YEAR);
        simpleDate.mType = MONTH_TYPE;
        simpleDate.mDate = calendar.getTime();
        return simpleDate;
    }

    static SimpleDate dateFrom(Calendar calendar) {
        SimpleDate simpleDate = new SimpleDate();
        simpleDate.mDay = calendar.get(Calendar.DATE);
        simpleDate.mMonth = calendar.get(Calendar.MONTH);
        simpleDate.mYear = calendar.get(Calendar.YEAR);
        simpleDate.mType = DATE_TYPE;
        simpleDate.mDate = calendar.getTime();
        return simpleDate;
    }
}
