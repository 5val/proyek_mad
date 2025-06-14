package com.example.proyek_mad

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class UserActivityTest {
    @get:Rule
    val activityRule = ActivityScenarioRule(UserActivity::class.java)

    @Test
    fun startAtHomeFragment() {
        onView(withId(R.id.constraint_home))
            .check(matches(isDisplayed()))
    }

    @Test
    fun navigateToCoursesFragment() {
        onView(withId(R.id.courses_mi)).perform(click())
        onView(withId(R.id.constraint_courses))
            .check(matches(isDisplayed()))
    }

    @Test
    fun navigateToOfflineCoursesFragment() {
        onView(withId(R.id.offline_mi)).perform(click())
        onView(withId(R.id.constraint_offline))
            .check(matches(isDisplayed()))
    }

    @Test
    fun navigateToProfileFragment() {
        onView(withId(R.id.profile_mi)).perform(click())
        onView(withId(R.id.constraint_profile))
            .check(matches(isDisplayed()))
    }

    @Test
    fun navigateBackToHomeFragment() {
        onView(withId(R.id.profile_mi)).perform(click())
        onView(withId(R.id.constraint_profile)).check(matches(isDisplayed()))

        onView(withId(R.id.home_mi)).perform(click())
        onView(withId(R.id.constraint_home)).check(matches(isDisplayed()))
    }

    @Test
    fun navigateToEditProfileFragment() {
        onView(withId(R.id.profile_mi)).perform(click())
        onView(withId(R.id.constraint_profile)).check(matches(isDisplayed()))

        onView(withId(R.id.btnEditProfile)).perform(click())
        onView(withId(R.id.constraint_edit_profile)).check(matches(isDisplayed()))
    }

}