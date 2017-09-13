package com.github.techisfun.onelinecalendar;

import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;

import static java.util.Calendar.SEPTEMBER;
import static java.util.Calendar.getInstance;
import static org.junit.Assert.assertEquals;

/**
 * Created by Andrea Maglie on 13/09/17.
 */
public class OneLineCalendarPresenterTest {

    private OneLineCalendarPresenter mPresenter;

    private Calendar today;

    @Before
    public void setUp() {
        today = getInstance();
        today.set(2017, SEPTEMBER, 12);

        mPresenter = new OneLineCalendarPresenter(today);
    }

    @Test
    public void testDateList_firstItemIsMonth() {
        SimpleDate date = mPresenter.getSimpleDateList().get(0);
        assertEquals(0, date.getDay());
        assertEquals(SEPTEMBER, date.getMonth());
        assertEquals(2017, date.getYear());
    }

    @Test
    public void testDateList_secondItemIsToday() {
        SimpleDate date = mPresenter.getSimpleDateList().get(1);
        assertEquals(12, date.getDay());
        assertEquals(SEPTEMBER, date.getMonth());
        assertEquals(2017, date.getYear());
    }

}