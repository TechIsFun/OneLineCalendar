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

import java.util.List;

/**
 * @author Andrea Maglie
 */

public class OneLineCalendarView extends FrameLayout implements OneLineCalendarContract.View {
    private RecyclerView mRecyclerView;
    private TextView mStickyHeaderTextView;
    private OneLineCalendarContract.Presenter mPresenter;
    private LinearLayoutManager mLayoutManager;

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
        mStickyHeaderTextView = (TextView) findViewById(R.id.sticky_header).findViewById(R.id.item_text);

        mPresenter = new OneLineCalendarPresenter();

        mLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                mPresenter.onScrolled(recyclerView, mLayoutManager, mStickyHeaderTextView);
            }
        });
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
    public void populateWithItems(final List<SimpleDate> simpleDateList) {
        mStickyHeaderTextView.setText(simpleDateList.get(0).toString());
        mRecyclerView.setAdapter(new RecyclerView.Adapter<DateViewHolder>() {
            @Override
            public DateViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
                return new DateViewHolder(view);
            }

            @Override
            public void onBindViewHolder(DateViewHolder holder, int position) {
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


    private static class DateViewHolder extends RecyclerView.ViewHolder {
        private final TextView mItemText;

        DateViewHolder(View itemView) {
            super(itemView);
            mItemText = (TextView) itemView.findViewById(R.id.item_text);
        }

        void bind(SimpleDate simpleDate) {
            mItemText.setText(simpleDate.toString());
        }
    }

}
