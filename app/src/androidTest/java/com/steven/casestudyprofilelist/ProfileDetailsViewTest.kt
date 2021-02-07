package com.steven.casestudyprofilelist

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.swipeLeft
import androidx.test.espresso.action.ViewActions.swipeRight
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.schibsted.spain.barista.internal.matcher.DrawableMatcher.Companion.withDrawable
import com.steven.casestudyprofilelist.helpers.*
import org.hamcrest.CoreMatchers.allOf
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ProfileDetailsViewTest {

    @get:Rule
    val activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun setUp() {
        addItemToStartOfRecyclerView(activityScenarioRule.scenario, userA)
    }

    /**
     * Tests if all detail views exist.
     */
    @Test
    fun test_checkViewsExist() {
        navigateToDetailView(R.id.recyclerview_profiles, 0)
        onView(withId(R.id.textview_profiledetails_name)).check(matches(isDisplayed()))
        onView(withId(R.id.textview_profiledetails_zip)).check(matches(isDisplayed()))
        onView(withId(R.id.textview_profiledetails_city)).check(matches(isDisplayed()))
        onView(withId(R.id.imageview_profiledetails_avatar)).check(matches(isDisplayed()))
        onView(withId(R.id.textview_profiledetails_age)).check(matches(isDisplayed()))
        onView(withId(R.id.textview_profiledetails_description)).check(matches(isDisplayed()))
    }

    /**
     * Tests if the TextViews are set correctly.
     */
    @Test
    fun test_detailViewText() {
        navigateToDetailView(R.id.recyclerview_profiles, 0)
        onView(withId(R.id.fragment_profiledetails))
            .check(matches(isDisplayed()))

        onView(withId(R.id.textview_profiledetails_name)).check(matches(withText("Gustav, ")))
        onView(withId(R.id.textview_profiledetails_zip)).check(matches(withText("22397 ")))
        onView(withId(R.id.textview_profiledetails_city)).check(matches(withText("Hamburg")))
        onView(withId(R.id.textview_profiledetails_age)).check(matches(withText("29")))
        onView(withId(R.id.textview_profiledetails_description)).check(matches(withText("Test description")))
    }

    /**
     * Tests the case for age being null in a Profile.
     */
    @Test
    fun test_detailViewNullAge() {
        addItemToStartOfRecyclerView(activityScenarioRule.scenario, userB)
        navigateToDetailView(R.id.recyclerview_profiles, 0)

        onView(withId(R.id.textview_profiledetails_name)).check(matches(withText("Lena")))
        onView(withId(R.id.textview_profiledetails_age)).check(matches(withText("")))
    }

    /**
     * Tests if the correct avatar is being shown.
     */
    @Test
    fun test_detailViewAvatarMale() {
        navigateToDetailView(R.id.recyclerview_profiles, 0)

        onView(withId(R.id.imageview_profiledetails_avatar)).check(matches(withDrawable(R.drawable.ic_user_male_128)))
    }

    /**
     * Tests if the correct avatar is being shown.
     */
    @Test
    fun test_detailViewAvatarFemale() {
        addItemToStartOfRecyclerView(activityScenarioRule.scenario, userB)
        navigateToDetailView(R.id.recyclerview_profiles, 0)

        onView(withId(R.id.imageview_profiledetails_avatar)).check(matches(withDrawable(R.drawable.ic_female_user_128)))
    }

    /**
     * Tests the swipe functionality between Profiles.
     */
    @Test
    fun test_swipeFunctionalityLeft() {
        addItemToStartOfRecyclerView(activityScenarioRule.scenario, userB)
        addItemToStartOfRecyclerView(activityScenarioRule.scenario, userC)
        navigateToDetailView(R.id.recyclerview_profiles, 0)

        onView(allOf(isDisplayingAtLeast(80), withId(R.id.textview_profiledetails_name))).check(
            matches(withText("Steven, "))
        )
        onView(withId(R.id.fragment_profiledetails)).perform(swipeLeft())
        onView(allOf(isDisplayingAtLeast(80), withId(R.id.textview_profiledetails_name))).check(
            matches(withText("Lena"))
        )
    }

    /**
     * Tests the swipe functionality between Profiles.
     */
    @Test
    fun test_swipeFunctionalityRight() {
        addItemToStartOfRecyclerView(activityScenarioRule.scenario, userB)
        addItemToStartOfRecyclerView(activityScenarioRule.scenario, userC)

        navigateToDetailView(R.id.recyclerview_profiles, 2)

        onView(allOf(isDisplayingAtLeast(80), withId(R.id.textview_profiledetails_name))).check(
            matches(withText("Gustav, "))
        )
        onView(withId(R.id.fragment_profiledetails)).perform(swipeRight())
        onView(allOf(isDisplayingAtLeast(80), withId(R.id.textview_profiledetails_name))).check(
            matches(withText("Lena"))
        )
    }
}