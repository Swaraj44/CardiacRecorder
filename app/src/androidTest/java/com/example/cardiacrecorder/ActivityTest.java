package com.example.cardiacrecorder;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.NoMatchingViewException;
import androidx.test.espresso.ViewAssertion;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
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
    public ActivityScenarioRule<MainActivity> loginActivityRule = new ActivityScenarioRule<>(MainActivity.class);

//    @Rule
//    public ActivityScenarioRule<Dash_Board> activityRule = new ActivityScenarioRule<>(Dash_Board.class);

//    @Test
//    public void testHeading() {
//        onView(withText("Add Record")).check(matches(isDisplayed())); //Check the name on the screen
//    }

    public class RecyclerViewItemCountAssertion implements ViewAssertion {
        private final int expectedCount;

        public RecyclerViewItemCountAssertion(int expectedCount) {
            this.expectedCount = expectedCount;
        }

        @Override
        public void check(View view, NoMatchingViewException noViewFoundException) {
            if (noViewFoundException != null) {
                throw noViewFoundException;
            }

            RecyclerView recyclerView = (RecyclerView) view;
            RecyclerView.Adapter adapter = recyclerView.getAdapter();
//            assert adapter != null;
            assertThat(adapter.getItemCount(), is(expectedCount));
        }
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




    @Test
    public void testAddEntry(){

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

        onView(withId(R.id.fabNewEntry)).perform(click());
        onView(withId(R.id.sp)).perform(ViewActions.typeText("120"));
        onView(withId(R.id.dp)).perform(ViewActions.typeText("60"));
        onView(withId(R.id.hr)).perform(ViewActions.typeText("80"));

        Espresso.pressBack();
        onView(withId(R.id.save_btn)).perform(click());

        try {
            latch.await(5, TimeUnit.SECONDS); // Adjust the timeout as needed
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        onView(withId(R.id.back_btn)).perform(click());
        onView(withId(R.id.recordRecyclerView)).check(new RecyclerViewItemCountAssertion(1));
    }


    @Test
    public void testLogout() {
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


        onView(withId(R.id.imageSignOut)).perform(click());
        onView(withId(R.id.welcomeText)).check(matches(isDisplayed()));
    }

    @Test
    public void testDeleteEntry(){

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





        onView(withId(R.id.delete)).perform(click());

        // Perform assertion after the RecyclerView action is completed
        Espresso.onView(withId(R.id.recordRecyclerView))
                .check(matches(isDisplayed()));
        Espresso.onView(withId(R.id.systolic))
                .check(doesNotExist());

    }

    @Test
    public void testUpdateEntry(){
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

        onView(withId(R.id.edit)).perform(click());
        Espresso.onView(ViewMatchers.withId(R.id.systolic)).perform(clearText());
        Espresso.onView(ViewMatchers.withId(R.id.diastolic)).perform(clearText());
        Espresso.onView(ViewMatchers.withId(R.id.heart)).perform(clearText());

        onView(withId(R.id.systolic)).perform(ViewActions.typeText("110"));
        onView(withId(R.id.diastolic)).perform(ViewActions.typeText("50"));
        onView(withId(R.id.heart)).perform(ViewActions.typeText("90"));
        Espresso.pressBack();
        onView(withId(R.id.save_btn1)).perform(click());


        onView(withId(R.id.discard1)).perform(click());

        onView(withText("110")).check(matches(isDisplayed()));
    }




}
