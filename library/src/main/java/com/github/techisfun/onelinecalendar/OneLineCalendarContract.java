package com.github.techisfun.onelinecalendar;

import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

/**
 * @author Andrea Maglie
 */
class OneLineCalendarContract {

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
