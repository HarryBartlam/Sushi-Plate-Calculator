package com.simplyapp.plate.calculator.ui.calculator;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.simplyapp.plate.calculator.R;
import com.simplyapp.plate.calculator.RecyclerViewMatcher;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class CalculatorActivityPlatesTest {

    @Rule
    public ActivityTestRule<CalculatorActivity> mActivityTestRule = new ActivityTestRule<>(CalculatorActivity.class);

    @Test
    public void calculatorActivityPlatesTest() {
        onView(RecyclerViewMatcher.withRecyclerView(R.id.plate_recyclerview).atPositionOnView(0, R.id.plate_plus)).perform(click());
        onView(RecyclerViewMatcher.withRecyclerView(R.id.plate_recyclerview).atPositionOnView(1, R.id.plate_plus)).perform(click());
        onView(RecyclerViewMatcher.withRecyclerView(R.id.plate_recyclerview).atPositionOnView(2, R.id.plate_plus)).perform(click());
        onView(RecyclerViewMatcher.withRecyclerView(R.id.plate_recyclerview).atPositionOnView(3, R.id.plate_plus)).perform(click());
        onView(RecyclerViewMatcher.withRecyclerView(R.id.plate_recyclerview).atPositionOnView(4, R.id.plate_plus)).perform(click());
        onView(RecyclerViewMatcher.withRecyclerView(R.id.plate_recyclerview).atPositionOnView(5, R.id.plate_plus)).perform(click());
        onView(RecyclerViewMatcher.withRecyclerView(R.id.plate_recyclerview).atPositionOnView(6, R.id.plate_plus)).perform(click());
        onView(RecyclerViewMatcher.withRecyclerView(R.id.plate_recyclerview).atPositionOnView(7, R.id.plate_plus)).perform(click());

        ViewInteraction textView = onView(
                allOf(withId(R.id.plate_total_textview),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.button_calc_bar_layout),
                                        1),
                                1),
                        isDisplayed()));
        textView.check(matches(withText("£37.60")));

        ViewInteraction actionMenuItemView = onView(
                allOf(withId(R.id.action_clear), withContentDescription("Clear All"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.calculator_toolbar),
                                        2),
                                0),
                        isDisplayed()));
        actionMenuItemView.perform(click());

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.plate_total_textview),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.button_calc_bar_layout),
                                        1),
                                1),
                        isDisplayed()));
        textView2.check(matches(withText("£0.00")));

    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
