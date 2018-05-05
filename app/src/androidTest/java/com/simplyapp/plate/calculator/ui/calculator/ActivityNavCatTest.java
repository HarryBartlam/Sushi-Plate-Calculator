package com.simplyapp.plate.calculator.ui.calculator;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.simplyapp.plate.calculator.R;

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
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.not;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class ActivityNavCatTest {

    @Rule
    public ActivityTestRule<CalculatorActivity> mActivityTestRule = new ActivityTestRule<>(CalculatorActivity.class);

    @Test
    public void calculatorActivityNavTest() {
        ViewInteraction appCompatImageButton = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.calculator_toolbar),
                                childAtPosition(
                                        withId(R.id.calculator_toolbar_layout),
                                        0)),
                        1),
                        isDisplayed()));
        appCompatImageButton.perform(click());

        ViewInteraction imageView = onView(
                allOf(withId(R.id.nav_cat), withContentDescription("Cat"),
                        childAtPosition(
                                allOf(withId(R.id.header_navigation),
                                        childAtPosition(
                                                withId(R.id.navigation_header_container),
                                                0)),
                                0),
                        isDisplayed()));
        imageView.check(matches(isDisplayed()));

        ViewInteraction navigationMenuItemView = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.design_navigation_view),
                                childAtPosition(
                                        withId(R.id.nav_view),
                                        0)),
                        1),
                        isDisplayed()));
        navigationMenuItemView.perform(click());

        onView(allOf(withId(R.id.nav_cat), withContentDescription("Cat"),
                        childAtPosition(
                                allOf(withId(R.id.header_navigation),
                                        childAtPosition(
                                                withId(R.id.navigation_header_container),
                                                0)),
                                0),
                        not(isDisplayed())));



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
