package com.github.techisfun.onelinecalendar;

import android.content.Context;
import androidx.annotation.AttrRes;
import androidx.annotation.NonNull;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.Calendar;
import java.util.List;

import static com.github.techisfun.onelinecalendar.OnLineCalendarUtils.capitalize;

/**
 * @author Andrea Maglie
 */
public class OneLineCalendarView extends FrameLayout implements OneLineCalendarContract.View {
    protected RecyclerView.Adapter<AbstracViewHolder> mAdapter;
    private RecyclerView mRecyclerView;
    private TextView mStickyHeaderTextView;
    private OneLineCalendarContract.Presenter mPresenter;
    private DateSelectionListener mDateSelectionListener;
    private int mSelectedPos = -1;

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

        mRecyclerView = findViewById(R.id.recycler_view);
        mStickyHeaderTextView = findViewById(R.id.sticky_header).findViewById(R.id.item_month);

        Calendar today = Calendar.getInstance();
        mPresenter = new OneLineCalendarPresenter(today);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
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
        mAdapter = new RecyclerView.Adapter<AbstracViewHolder>() {
            @NonNull
            @Override
            public AbstracViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
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
            public void onBindViewHolder(@NonNull AbstracViewHolder holder, int position) {
                holder.bind(simpleDateList.get(position));
                holder.setSelected(mSelectedPos == position);
            }

            @Override
            public int getItemCount() {
                return simpleDateList.size();
            }

            @Override
            public int getItemViewType(int position) {
                return simpleDateList.get(position).getType();
            }
        };
        mRecyclerView.setAdapter(mAdapter);
    }

    public void setOnDateClickListener(DateSelectionListener mDateSelectionListener) {
        this.mDateSelectionListener = mDateSelectionListener;
    }

    private abstract static class AbstracViewHolder extends RecyclerView.ViewHolder {

        AbstracViewHolder(View itemView) {
            super(itemView);
        }

        abstract void bind(SimpleDate simpleDate);

        abstract void setSelected(boolean selected);
    }

    private static class MonthViewHolder extends AbstracViewHolder {
        private final TextView mItemText;

        MonthViewHolder(View itemView) {
            super(itemView);
            mItemText = itemView.findViewById(R.id.item_month);
        }

        void bind(SimpleDate simpleDate) {
            mItemText.setText(simpleDate.toString());
        }

        @Override
        void setSelected(boolean selected) {
            // nothing
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
            mDayNameTextView = itemView.findViewById(R.id.item_day_name);
            mDayNumberTextView = itemView.findViewById(R.id.item_day_number);
            this.mItemView = itemView;
        }

        @Override
        void setSelected(boolean selected) {
            mItemView.setSelected(selected);
            mDayNameTextView.setSelected(selected);
            mDayNumberTextView.setSelected(selected);
        }

        void bind(final SimpleDate simpleDate) {
            CharSequence dayNameFormatted = simpleDate.getDayNameFormatted(mContext);
            mDayNameTextView.setText(capitalize(dayNameFormatted.toString()));
            mDayNumberTextView.setText(simpleDate.getDayNumberFormatted());

            if (mDateSelectionListener != null) {
                mItemView.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (view.isSelected()) {
                            if (mDateSelectionListener.onDateUnselected()) {
                                clearSelection();
                            }
                        } else {
                            if (mDateSelectionListener.onDateSelected(simpleDate.getDate())) {
                                updateSelection();
                            }
                        }
                    }
                });
            } else {
                updateSelection();
            }

        }

        private void clearSelection() {
            setSelected(false);
            mSelectedPos = -1;
            mAdapter.notifyItemChanged(getLayoutPosition());
        }

        private void updateSelection() {
            setSelected(true);
            mSelectedPos = getLayoutPosition();
            mAdapter.notifyDataSetChanged();
        }
    }

}
