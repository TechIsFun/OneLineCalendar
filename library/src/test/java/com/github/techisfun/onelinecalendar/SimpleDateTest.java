package com.github.techisfun.onelinecalendar;

import android.content.Context;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import java.util.Calendar;
import java.util.Locale;

import static java.util.Calendar.SEPTEMBER;
import static java.util.Calendar.getInstance;
import static org.junit.Assert.assertEquals;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class SimpleDateTest {

    private Calendar today;
    private Context context = RuntimeEnvironment.application;

    @Before
    public void setUp() {
        today = getInstance();
        today.set(2017, SEPTEMBER, 12);
    }

    @Test
    public void getDayNameFormatted_en() {
        Locale.setDefault(Locale.ENGLISH);
        Calendar calendar = getInstance();
        calendar.set(2017, SEPTEMBER, 13);
        SimpleDate simpleDate = SimpleDate.dateFrom(calendar, today);
        assertEquals("Wed", simpleDate.getDayNameFormatted(context));
    }

    @Test
    public void getDayNameFormatted_it() {
        Locale.setDefault(Locale.ITALIAN);
        Calendar calendar = getInstance();
        calendar.set(2017, SEPTEMBER, 13);
        SimpleDate simpleDate = SimpleDate.dateFrom(calendar, today);
        assertEquals("mer", simpleDate.getDayNameFormatted(context));
    }

    @Test
    @Config(qualifiers = "en")
    public void getDayNameFormatted_today_en() {
        Locale.setDefault(Locale.ENGLISH);

        SimpleDate simpleDate = SimpleDate.dateFrom(today, today);
        assertEquals("Today", simpleDate.getDayNameFormatted(context).toString());
    }

    @Test
    @Config(qualifiers = "it")
    public void getDayNameFormatted_today_it() {
        Locale.setDefault(Locale.ITALIAN);

        SimpleDate simpleDate = SimpleDate.dateFrom(today, today);
        assertEquals("Oggi", simpleDate.getDayNameFormatted(context).toString());
    }

    @Test
    public void getDayNumberFormatted() {
        Calendar calendar = getInstance();
        calendar.set(2017, SEPTEMBER, 13);
        SimpleDate simpleDate = SimpleDate.dateFrom(calendar, today);
        assertEquals("13", simpleDate.getDayNumberFormatted());
    }

    @Test
    public void getDayNumberFormatted_today() {
        SimpleDate simpleDate = SimpleDate.dateFrom(today, today);
        assertEquals("12", simpleDate.getDayNumberFormatted());
    }
}