package com.example.cardiacrecorder;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class ActivityTest {

    @Rule
    public ActivityScenarioRule<Add_Info> activityRule =
            new ActivityScenarioRule<>(Add_Info.class);


    @Test
    public void testHeading() {
        onView(withText("Add Record")).check(matches(isDisplayed())); //Check the name on the screen
    }

}
