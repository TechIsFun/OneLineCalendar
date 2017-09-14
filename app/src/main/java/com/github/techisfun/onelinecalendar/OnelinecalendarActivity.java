package com.github.techisfun.onelinecalendar;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.github.techisfun.onelinecalendar.app.R;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 */
public class OnelinecalendarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        OneLineCalendarView calendarView = (OneLineCalendarView) findViewById(R.id.calendar_view);
        calendarView.setOnDateClickListener(new DateSelectionListener() {
            @Override
            public boolean onDateSelected(@NonNull Date date) {
                Toast.makeText(OnelinecalendarActivity.this,
                        SimpleDateFormat.getDateInstance().format(date),
                        Toast.LENGTH_SHORT)
                        .show();
                return true;
            }

            @Override
            public void onDateUnselected() {
                Toast.makeText(OnelinecalendarActivity.this,
                        "Date unselected",
                        Toast.LENGTH_SHORT)
                        .show();
            }
        });
    }
}
