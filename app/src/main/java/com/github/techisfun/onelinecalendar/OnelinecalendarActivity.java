package com.github.techisfun.onelinecalendar;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.github.techisfun.onelinecalendar.app.R;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Andrea Maglie
 */
public class OnelinecalendarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView selectedDateTw = findViewById(R.id.selected_date_tw);

        final OneLineCalendarView calendarView = findViewById(R.id.calendar_view);
        calendarView.setOnDateClickListener(new DateSelectionListener() {
            @Override
            public boolean onDateSelected(@NonNull final Date date) {
                selectedDateTw.setText(SimpleDateFormat.getDateInstance().format(date));
                return true;
            }

            @Override
            public boolean onDateUnselected() {
                selectedDateTw.setText(R.string.no_selection);
                return true;
            }
        });
    }
}
