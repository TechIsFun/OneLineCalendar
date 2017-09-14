package com.github.techisfun.onelinecalendar;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.facebook.testing.screenshot.Screenshot;
import com.github.techisfun.onelinecalendar.app.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.RuleChain;
import org.junit.runner.RunWith;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * @author Andrea Maglie
 */
@RunWith(AndroidJUnit4.class)
public class DateSelectionInstrumentedTest {

    private ForceLocaleRule mForceLocaleRule = new ForceLocaleRule(Locale.US);

    private ActivityTestRule<OnelinecalendarActivity> mActivityRule =
            new ActivityTestRule<>(OnelinecalendarActivity.class);

    @Rule
    public RuleChain mRuleChain =
            RuleChain.outerRule(mForceLocaleRule).around(mActivityRule);

    @Test
    public void testDateSelection() throws InterruptedException {
        OnelinecalendarActivity activity = mActivityRule.getActivity();

        onView(withText(R.string.no_selection)).check(matches(isDisplayed()));

        Calendar today = Calendar.getInstance();
        String todayFormatted = SimpleDateFormat.getDateInstance().format(today.getTime());

        // click on Today to select
        onView(withText("Today")).perform(click());
        onView(withText(todayFormatted)).check(matches(isDisplayed()));
        Screenshot.snapActivity(activity).record();

        // click again on Today to unselect
        onView(withText("Today")).perform(click());
        onView(withText(R.string.no_selection)).check(matches(isDisplayed()));
    }
}
