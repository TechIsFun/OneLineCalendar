[![Build Status](https://travis-ci.org/TechIsFun/OneLineCalendar.svg)](https://travis-ci.org/TechIsFun/OneLineCalendar)
[![](https://jitpack.io/v/techisfun/OneLineCalendar.svg)](https://jitpack.io/#techisfun/OneLineCalendar)

# OneLineCalendar

A calendar widget for Android displayed on one line

![Screencap](img/onelinecalendar.gif)

## Download

```
dependencies {
  compile 'com.github.techisfun:onelinecalendar:0.4'
}
```

## Example usage

- Add the view to your layout:

```xml
<com.github.techisfun.onelinecalendar.OneLineCalendarView
        android:theme="@style/CalendarTheme"
        android:id="@+id/calendar_view"
        android:layout_width="match_parent"
        android:layout_height="70dp"
        />
```

- Create a theme
```xml
<style name="CalendarTheme" parent="Theme.AppCompat.Light">
    <item name="itemDayBackground">@drawable/selectable_background</item>
    <item name="itemDayNameTextColor">@color/day_name_text_color</item>
    <item name="itemDayNumberTextColor">@color/day_number_text_color</item>
    <item name="itemMonthBackground">@color/selected_bg</item>
    <item name="itemMonthTextColor">@android:color/white</item>
</style>
```

- Then set the listener for date selected/unselected:

```java
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
```

License
-------

    Copyright 2017 Andrea Maglie

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

