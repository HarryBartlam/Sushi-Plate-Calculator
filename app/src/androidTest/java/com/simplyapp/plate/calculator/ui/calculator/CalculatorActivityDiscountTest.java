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
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class CalculatorActivityDiscountTest {

    @Rule
    public ActivityTestRule<CalculatorActivity> mActivityTestRule = new ActivityTestRule<>(CalculatorActivity.class);

    @Test
    public void calculatorActivityDiscountTest() {
        ViewInteraction linearLayout = onView(
                allOf(withId(R.id.plate_discount_layout),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.button_calc_bar_layout),
                                        0),
                                1),
                        isDisplayed()));
        linearLayout.perform(click());

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.dialog_discount_edit_text),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        appCompatEditText.perform(replaceText("5"));

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.dialog_discount_edit_text), withText("5"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        appCompatEditText2.perform(closeSoftKeyboard());

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.dialog_discount_save), withText("save"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        3),
                                1),
                        isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction textView = onView(
                allOf(withId(R.id.plate_discount_prefix), withText("-"),
                        childAtPosition(
                                allOf(withId(R.id.plate_discount_layout),
                                        childAtPosition(
                                                IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                                1)),
                                0),
                        isDisplayed()));
        textView.check(matches(isDisplayed()));

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.plate_discount_textview),
                        childAtPosition(
                                allOf(withId(R.id.plate_discount_layout),
                                        childAtPosition(
                                                IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                                1)),
                                1),
                        isDisplayed()));
        textView2.check(matches(withText("5")));



        //reset
        ViewInteraction linearLayout1 = onView(
                allOf(withId(R.id.plate_discount_layout),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.button_calc_bar_layout),
                                        0),
                                1),
                        isDisplayed()));
        linearLayout1.perform(click());

        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(R.id.dialog_discount_edit_text),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed())).perform(replaceText("0"));
        appCompatEditText3.perform(replaceText("0"));

        ViewInteraction appCompatEditText4 = onView(
                allOf(withId(R.id.dialog_discount_edit_text), withText("0"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        appCompatEditText4.perform(closeSoftKeyboard());

        ViewInteraction appCompatButton1 = onView(
                allOf(withId(R.id.dialog_discount_save), withText("save"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        3),
                                1),
                        isDisplayed()));
        appCompatButton1.perform(click());
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
