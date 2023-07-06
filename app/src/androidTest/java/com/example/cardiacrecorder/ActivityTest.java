package com.example.cardiacrecorder;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@RunWith(AndroidJUnit4.class)
public class ActivityTest {

    @Rule
    public ActivityScenarioRule<Dash_Board> activityRule =
            new ActivityScenarioRule<>(Dash_Board.class);
    @Rule
    public ActivityScenarioRule<MainActivity> loginActivityRule = new ActivityScenarioRule<>(MainActivity.class);



    @Test
    public void testHeading() {
        onView(withText("Add Record")).check(matches(isDisplayed())); //Check the name on the screen
    }


    @Test
    public void testLogin(){
        try {
            Thread.sleep(4000); // Adjust the delay as needed
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Create a CountDownLatch with a count of 1
        final CountDownLatch latch = new CountDownLatch(1);



        onView(withId(R.id.inputEmail)).perform(ViewActions.typeText("jobairnahian2017@gmail.com"));
        onView(withId(R.id.inputPassword)).perform(ViewActions.typeText("12345678"));
        onView(withId(R.id.buttonSignIn)).perform(click());

        // Wait for the RecyclerView action to complete
        try {
            latch.await(5, TimeUnit.SECONDS); // Adjust the timeout as needed
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withId(R.id.imageSignOut)).check(matches(isDisplayed()));

    }

}
