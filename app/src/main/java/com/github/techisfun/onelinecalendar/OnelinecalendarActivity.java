package com.github.techisfun.onelinecalendar;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.github.techisfun.onelinecalendar.app.R;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Andrea Maglie
 */
public class OnelinecalendarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView selectedDateTw = (TextView) findViewById(R.id.selected_date_tw);

        OneLineCalendarView calendarView = (OneLineCalendarView) findViewById(R.id.calendar_view);
        calendarView.setOnDateClickListener(new DateSelectionListener() {
            @Override
            public boolean onDateSelected(@NonNull Date date) {
                selectedDateTw.setText(SimpleDateFormat.getDateInstance().format(date));
                return true;
            }

            @Override
            public void onDateUnselected() {
                selectedDateTw.setText(R.string.no_selection);
            }
        });
    }
}
