package com.steven.casestudyprofilelist.helpers

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import com.steven.casestudyprofilelist.MainActivity
import com.steven.casestudyprofilelist.R
import com.steven.casestudyprofilelist.adapters.ProfileAdapter
import com.steven.casestudyprofilelist.models.Gender
import com.steven.casestudyprofilelist.models.Location
import com.steven.casestudyprofilelist.models.Profile
import com.steven.casestudyprofilelist.models.Profiles

/**
 * User A for UI Tests.
 */
val userA = Profile(
    "Gustav",
    29,
    Gender.MALE,
    "Test description",
    Location("Hamburg", "22397")
)

/**
 * User B for UI Tests.
 */
val userB = Profile(
    "Lena",
    null,
    Gender.FEMALE,
    "Test description",
    Location("Hamburg", "20095")
)

/**
 * User C for UI Tests.
 */
val userC = Profile(
    "Steven",
    22,
    Gender.MALE,
    "Test description",
    Location("Hamburg", "22397")
)

fun addItemToStartOfRecyclerView(scenario: ActivityScenario<MainActivity>, profile: Profile) {
    scenario.onActivity {
        val rv = it.findViewById<RecyclerView>(R.id.recyclerview_profiles)
        val adapter = rv.adapter as ProfileAdapter
        adapter.addItemsToBeginning(Profiles(listOf(profile)))
    }
}

fun navigateToDetailView(viewId: Int, position: Int) {
    Espresso.onView(ViewMatchers.withId(viewId)).perform(
        RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
            position,
            ViewActions.click()
        )
    )
}