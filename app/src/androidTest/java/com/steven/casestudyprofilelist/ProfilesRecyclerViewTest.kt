package com.steven.casestudyprofilelist

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.schibsted.spain.barista.assertion.BaristaListAssertions.assertDisplayedAtPosition
import com.steven.casestudyprofilelist.adapters.ProfileAdapter
import com.steven.casestudyprofilelist.helpers.addItemToStartOfRecyclerView
import com.steven.casestudyprofilelist.helpers.navigateToDetailView
import com.steven.casestudyprofilelist.helpers.userA
import com.steven.casestudyprofilelist.helpers.userB
import com.steven.casestudyprofilelist.models.Profiles
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ProfilesRecyclerViewTest {

    @get:Rule
    val activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    /**
     * Tests if the RecyclerView is displayed correctly on startup.
     */
    @Test
    fun test_recyclerViewLoadedIntoActivity() {
        onView(withId(R.id.recyclerview_profiles)).check(matches(isDisplayed()))
    }

    /**
     * Tests if adding an item to the RecyclerView (Adapter) works.
     */
    @Test
    fun test_recyclerViewAddItem() {
        var itemCount = 0
        activityScenarioRule.scenario.onActivity {
            val rv = it.findViewById<RecyclerView>(R.id.recyclerview_profiles)
            val profileAdapter = rv.adapter as ProfileAdapter
            profileAdapter.addItems(Profiles(listOf(userB)))
            itemCount = profileAdapter.itemCount
        }

        onView(withId(R.id.recyclerview_profiles)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(itemCount - 1)
        )
        assertDisplayedAtPosition(
            R.id.recyclerview_profiles,
            itemCount - 1,
            R.id.textview_profile_name,
            "Lena"
        )
    }

    /**
     * Tests if adding an item to the beginning of the RecyclerView (Adapter) works.
     */
    @Test
    fun test_recyclerViewAddItemBeginning() {
        addItemToStartOfRecyclerView(activityScenarioRule.scenario, userA)

        assertDisplayedAtPosition(
            R.id.recyclerview_profiles,
            0,
            R.id.textview_profile_name,
            "Gustav, "
        )
    }

    /**
     * Test if the texts are being shown correctly.
     */
    @Test
    fun test_recyclerViewItemText() {
        // Add item for testing purposes in case the list is empty
        addItemToStartOfRecyclerView(activityScenarioRule.scenario, userA)

        assertDisplayedAtPosition(
            R.id.recyclerview_profiles,
            0,
            R.id.textview_profile_name,
            "Gustav, "
        )
        assertDisplayedAtPosition(R.id.recyclerview_profiles, 0, R.id.textview_profile_age, "29")
        assertDisplayedAtPosition(
            R.id.recyclerview_profiles,
            0,
            R.id.textview_profile_zip,
            "22397 "
        )
        assertDisplayedAtPosition(
            R.id.recyclerview_profiles,
            0,
            R.id.textview_profile_city,
            "Hamburg"
        )
    }

    /**
     * Tests the clicking functionality.
     */
    @Test
    fun test_recyclerViewItemClick() {
        navigateToDetailView(R.id.recyclerview_profiles, 0)

        onView(withId(R.id.fragment_profiledetails)).check(matches(isDisplayed()))
    }

}