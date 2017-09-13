package com.github.techisfun.onelinecalendar;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.Calendar;
import java.util.List;

import static com.github.techisfun.onelinecalendar.OnLineCalendarUtils.capitalize;

/**
 * @author Andrea Maglie
 */

public class OneLineCalendarView extends FrameLayout implements OneLineCalendarContract.View {
    private RecyclerView mRecyclerView;
    private TextView mStickyHeaderTextView;
    private OneLineCalendarContract.Presenter mPresenter;
    private LinearLayoutManager mLayoutManager;
    private OnDateClickListener mOnDateClickListener;

    public OneLineCalendarView(@NonNull Context context) {
        super(context);
        init(context);
    }

    public OneLineCalendarView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public OneLineCalendarView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        inflate(context, R.layout.onlinecalendar_layout, this);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mStickyHeaderTextView = (TextView) findViewById(R.id.sticky_header).findViewById(R.id.item_month);

        Calendar today = Calendar.getInstance();
        mPresenter = new OneLineCalendarPresenter(today);

        mLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addOnScrollListener(mPresenter.buildOnScrollListener());
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        mPresenter.takeView(this);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mPresenter.dropView();
    }

    @Override
    public void setStickyHeaderText(String string) {
        mStickyHeaderTextView.setText(string);
    }

    @Override
    public void populateWithItems(final List<SimpleDate> simpleDateList) {
        mStickyHeaderTextView.setText(simpleDateList.get(0).toString());
        mRecyclerView.setAdapter(new RecyclerView.Adapter<AbstracViewHolder>() {
            @Override
            public AbstracViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                LayoutInflater inflater = LayoutInflater.from(parent.getContext());
                if (viewType == SimpleDate.MONTH_TYPE) {
                    View view = inflater.inflate(R.layout.item_month_layout, parent, false);
                    return new MonthViewHolder(view);
                } else {
                    View view = inflater.inflate(R.layout.item_day_layout, parent, false);
                    return new DayViewHolder(view);
                }
            }

            @Override
            public void onBindViewHolder(AbstracViewHolder holder, int position) {
                holder.bind(simpleDateList.get(position));
            }

            @Override
            public int getItemCount() {
                return simpleDateList.size();
            }

            @Override
            public int getItemViewType(int position) {
                return simpleDateList.get(position).getType();
            }
        });
    }

    public void setOnDateClickListener(OnDateClickListener mOnDateClickListener) {
        this.mOnDateClickListener = mOnDateClickListener;
    }

    private abstract static class AbstracViewHolder extends RecyclerView.ViewHolder {

        AbstracViewHolder(View itemView) {
            super(itemView);
        }

        abstract void bind(SimpleDate simpleDate);
    }

    private static class MonthViewHolder extends AbstracViewHolder {
        private final TextView mItemText;

        MonthViewHolder(View itemView) {
            super(itemView);
            mItemText = (TextView) itemView.findViewById(R.id.item_month);
        }

        void bind(SimpleDate simpleDate) {
            mItemText.setText(simpleDate.toString());
        }
    }

    private class DayViewHolder extends AbstracViewHolder {

        private final TextView mDayNameTextView;
        private final TextView mDayNumberTextView;
        private final View mItemView;
        private final Context mContext;

        DayViewHolder(View itemView) {
            super(itemView);
            mContext = itemView.getContext();
            mDayNameTextView = (TextView) itemView.findViewById(R.id.item_day_name);
            mDayNumberTextView = (TextView) itemView.findViewById(R.id.item_day_number);
            this.mItemView = itemView;
        }

        void bind(final SimpleDate simpleDate) {
            CharSequence dayNameFormatted = simpleDate.getDayNameFormatted(mContext);
            mDayNameTextView.setText(capitalize(dayNameFormatted.toString()));
            mDayNumberTextView.setText(simpleDate.getDayNumberFormatted());

            if (mOnDateClickListener != null) {
                mItemView.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mOnDateClickListener.onDateClicked(simpleDate.getDate());
                    }
                });
            }

        }
    }

}
