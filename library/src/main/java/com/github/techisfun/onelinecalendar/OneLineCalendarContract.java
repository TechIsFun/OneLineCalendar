package com.github.techisfun.onelinecalendar;

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
    }
}
