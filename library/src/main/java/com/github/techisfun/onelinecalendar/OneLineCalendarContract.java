package com.github.techisfun.onelinecalendar;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.List;

/**
 * @author Andrea Maglie
 */

public class OneLineCalendarContract {

    interface View {
        void populateWithItems(List<SimpleDate> simpleDateList);
    }

    interface Presenter {

        void dropView();

        void takeView(OneLineCalendarContract.View oneLineCalendar);

        void onScrolled(RecyclerView recyclerView, LinearLayoutManager layoutManager, TextView stickyHeaderTextView);
    }
}
