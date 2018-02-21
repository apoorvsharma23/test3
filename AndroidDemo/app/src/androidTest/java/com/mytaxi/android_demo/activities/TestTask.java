package com.mytaxi.android_demo.activities;


import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.support.test.espresso.ViewInteraction;
import com.mytaxi.android_demo.R;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withHint;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class TestTask {
    public static final String username = "whiteelephant261";
    public static final String password = "video";

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void logIn() throws InterruptedException {

        //Enter username
        onView(withId(R.id.edt_username))
                .perform(click())
                .perform(typeText(username), closeSoftKeyboard());

        //Enter password
        onView(withId(R.id.edt_password))
                .perform(click())
                .perform(typeText(password), closeSoftKeyboard());

        //Click on Login button
        onView(allOf(withId(R.id.btn_login), withText("Login"), isDisplayed()))
                .perform(click());

    }

    @Test
    public void selectDriver() throws InterruptedException {
        //Assert for successful login
        onView(withText("mytaxi demo"))
                .check(matches(withText("mytaxi demo")));

        //search for driver
        ViewInteraction searchBox = onView(allOf(withId(R.id.textSearch), withHint("Search driver here"), isDisplayed()))
                .perform(typeText("s"));
        Thread.sleep(1000);
        searchBox
                .perform(typeText("a"));

        //selecting the driver from suggestions
        onView(withText("Sarah Friedrich"))
                .inRoot(withDecorView(not(is(mActivityRule.getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed())).perform(click());

        //Assertions on driver details page
        onView(withText("Driver Profile"))
                .check(matches(withText("Driver Profile")));

        onView(allOf(withId(R.id.textViewDriverName), withText("Sarah Friedrich"),isDisplayed()))
                .check(matches(withText("Sarah Friedrich")));

        onView(withId(R.id.imageViewDriverLocation))
                .check(matches(isDisplayed()));

        onView(allOf(withId(R.id.textViewDriverLocation), withText("2297 bahnhofstraße"), isDisplayed()))
                .check(matches(withText("2297 bahnhofstraße")));

        onView(withId(R.id.imageViewDriverDate))
                .check(matches(isDisplayed()));

        onView(allOf(withId(R.id.textViewDriverDate), withText("2013-03-05"), isDisplayed()))
                .check(matches(withText("2013-03-05")));

        onView(allOf(withId(R.id.imageViewDriverAvatar), isDisplayed()))
                .check(matches(isDisplayed()));

        //Click on call button
        onView(withId(R.id.fab))
                .check(matches(isDisplayed()))
                .perform(click());

    }
}