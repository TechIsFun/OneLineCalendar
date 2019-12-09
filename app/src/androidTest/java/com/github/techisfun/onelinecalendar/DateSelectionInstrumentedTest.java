package com.github.techisfun.onelinecalendar;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;
import com.facebook.testing.screenshot.Screenshot;
import com.github.techisfun.onelinecalendar.app.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.RuleChain;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


/**
 * @author Andrea Maglie
 */
public class DateSelectionInstrumentedTest {

    private ForceLocaleRule mForceLocaleRule = new ForceLocaleRule(Locale.US);

    private ActivityTestRule<OnelinecalendarActivity> mActivityRule =
            new ActivityTestRule<>(OnelinecalendarActivity.class);

    @Rule
    public RuleChain mRuleChain =
            RuleChain.outerRule(mForceLocaleRule).around(mActivityRule);

    @Test
    public void testDateSelection() {
        OnelinecalendarActivity activity = mActivityRule.getActivity();

        // take screenshot
        Screenshot.snapActivity(activity)
                .setName("no-selection")
                .record();

        onView(withText(R.string.no_selection)).check(matches(isDisplayed()));

        Calendar today = Calendar.getInstance();
        String todayFormatted = SimpleDateFormat.getDateInstance().format(today.getTime());

        // click on Today to select
        onView(withText(R.string.today)).perform(click());
        onView(withText(todayFormatted)).check(matches(isDisplayed()));

        // take screenshot
        Screenshot.snapActivity(activity)
                .setName("today-selected")
                .record();

        // click again on Today to unselect
        onView(withText(R.string.today)).perform(click());
        onView(withText(R.string.no_selection)).check(matches(isDisplayed()));
    }
}
