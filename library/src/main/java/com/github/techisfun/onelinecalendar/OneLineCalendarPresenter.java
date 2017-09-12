package com.github.techisfun.onelinecalendar;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * @author Andrea Maglie
 */

class OneLineCalendarPresenter implements OneLineCalendarContract.Presenter {

    private static final int MAX_DAYS = 365;
    private static final int MAX_SIZE = MAX_DAYS + 12;

    private OneLineCalendarContract.View mView;

    List<SimpleDate> mSimpleDateList = new ArrayList<>(MAX_SIZE);

    public OneLineCalendarPresenter() {
        buildSimpleDateList();
    }

    private void buildSimpleDateList() {
        Calendar calendar = Calendar.getInstance();
        for (int i = 0; i < MAX_DAYS ; i++) {
            calendar.add(Calendar.DATE, 1);
            if (calendar.get(Calendar.DATE) == 1) {
                mSimpleDateList.add(SimpleDate.monthFrom(calendar));
            } else if (i == 0) {
                mSimpleDateList.add(SimpleDate.monthFrom(calendar));
            }
            mSimpleDateList.add(SimpleDate.dateFrom(calendar));
        }
    }

    @Override
    public void dropView() {
        mView = null;
    }

    @Override
    public void takeView(OneLineCalendarContract.View view) {
        mView = view;
        populateView();
    }

    private void populateView() {
        mView.populateWithItems(mSimpleDateList);
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, LinearLayoutManager layoutManager, TextView stickyHeaderTextView) {
        int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();
        SimpleDate simpleDate = mSimpleDateList.get(firstVisibleItemPosition);
        if (simpleDate.getType() == SimpleDate.MONTH_TYPE) {
            stickyHeaderTextView.setText(simpleDate.toString());
        }
    }
}
