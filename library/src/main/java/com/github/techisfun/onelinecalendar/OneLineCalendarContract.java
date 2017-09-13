package com.github.techisfun.onelinecalendar;

import android.support.v7.widget.RecyclerView;

import java.util.List;

/**
 * @author Andrea Maglie
 */

public class OneLineCalendarContract {

    interface View {
        void populateWithItems(List<SimpleDate> simpleDateList);

        void setStickyHeaderText(String string);
    }

    interface Presenter {

        void dropView();

        void takeView(OneLineCalendarContract.View oneLineCalendar);

        RecyclerView.OnScrollListener buildOnScrollListener();
    }
}
