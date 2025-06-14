package com.example.proyek_mad

import androidx.test.espresso.Espresso.*
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.action.ViewActions.*
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
class MainActivityTest {
    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun showLoginFragmentFirst() {
        onView(withId(R.id.constraint_login))
            .check(matches(isDisplayed()))
    }

    @Test
    fun navigateToRegisterFragment() {
        onView(withId(R.id.register_mi))
            .perform(click())
        onView(withId(R.id.constraint_register))
            .check(matches(isDisplayed()))
    }

    @Test
    fun navigateBackToLoginFragment() {
        onView(withId(R.id.register_mi))
            .perform(click())
        onView(withId(R.id.constraint_register))
            .check(matches(isDisplayed()))

        onView(withId(R.id.login_mi))
            .perform(click())
        onView(withId(R.id.constraint_login))
            .check(matches(isDisplayed()))
    }
}