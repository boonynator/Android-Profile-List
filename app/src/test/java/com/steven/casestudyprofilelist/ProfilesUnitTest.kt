package com.steven.casestudyprofilelist

import com.steven.casestudyprofilelist.models.Location
import com.steven.casestudyprofilelist.models.Profile
import com.steven.casestudyprofilelist.models.Profiles
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Test

class ProfilesUnitTest {

    /**
     * Tests that similar profile objects are not the same.
     */
    @Test
    fun test_similarProfileNotEquals() {
        val profileOne = Profile("Mia", 22, "FEMALE", "description", Location("Hamburg", "20095"))
        val profileTwo = Profile("Mia", 25, "FEMALE", "description", Location("Hamburg", "20095"))

        assertNotEquals(profileOne, profileTwo)
    }

    /**
     * Tests that same data profile objects are the same.
     */
    @Test
    fun test_sameProfileEquals() {
        val profileOne = Profile("Mia", 22, "FEMALE", "description", Location("Hamburg", "20095"))
        val profileTwo = Profile("Mia", 22, "FEMALE", "description", Location("Hamburg", "20095"))

        assertEquals(profileOne, profileTwo)
    }

    /**
     * Tests that similar profiles object is not the same.
     */
    @Test
    fun test_multipleProfilesNotEquals() {
        val profilesOne = Profiles(
            listOf(
                Profile("Mia", 22, "FEMALE", "description", Location("Hamburg", "20095")),
                Profile("Jonas", null, "MALE", "description", Location("Hamburg", "20095"))
            )
        )

        val profilesTwo = Profiles(
            listOf(
                Profile("Marc", 27, "MALE", "description", Location("Hamburg", "20095")),
                Profile("Jonas", null, "MALE", "description", Location("Hamburg", "20095"))
            )
        )

        assertNotEquals(profilesOne, profilesTwo)
    }

    /**
     * Tests that same data profiles object is the same.
     */
    @Test
    fun test_multipleProfilesEquals() {
        val profilesOne = Profiles(
            listOf(
                Profile("Mia", 22, "FEMALE", "description", Location("Hamburg", "20095")),
                Profile("Jonas", null, "MALE", "description", Location("Hamburg", "20095"))
            )
        )

        val profilesTwo = Profiles(
            listOf(
                Profile("Mia", 22, "FEMALE", "description", Location("Hamburg", "20095")),
                Profile("Jonas", null, "MALE", "description", Location("Hamburg", "20095"))
            )
        )

        assertEquals(profilesOne, profilesTwo)
    }

    /**
     * Tests that profiles objects with empty lists are the same.
     */
    @Test
    fun test_emptyProfilesEquals() {
        val profilesOne = Profiles(emptyList())
        val profilesTwo = Profiles(emptyList())

        assertEquals(profilesOne, profilesTwo)
    }

    /**
     * Tests that same data locations are the same.
     */
    @Test
    fun test_locationEquals() {
        val locationOne = Location("Hamburg", "20095")
        val locationTwo = Location("Hamburg", "20095")

        assertEquals(locationOne, locationTwo)
    }

    /**
     * Tests that different data locations are not the same.
     */
    @Test
    fun test_locationNotEquals() {
        val locationOne = Location("Hamburg", "20095")
        val locationTwo = Location("Hamburg", "22397")

        assertNotEquals(locationOne, locationTwo)
    }
}