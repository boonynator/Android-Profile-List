package com.steven.casestudyprofilelist

import com.steven.casestudyprofilelist.models.Gender
import com.steven.casestudyprofilelist.models.Location
import com.steven.casestudyprofilelist.models.Profile
import com.steven.casestudyprofilelist.models.Profiles
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Test

class ProfilesUnitTest {

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

    /**
     * Tests every single attribute of profile.
     */
    @Test
    fun test_profileConstructor() {
        val profile = Profile("A", 10, Gender.FEMALE, "D", Location("C", "Z"))

        assertEquals(profile.name, "A")
        assertEquals(profile.age, 10)
        assertEquals(profile.gender, Gender.FEMALE)
        assertEquals(profile.description, "D")
        assertEquals(profile.location.zip, "Z")
        assertEquals(profile.location.city, "C")
    }

    /**
     * Tests that similar profile objects are not the same.
     */
    @Test
    fun test_similarProfileNotEquals() {
        val profileOne =
            Profile("Mia", 22, Gender.FEMALE, "description", Location("Hamburg", "20095"))
        val profileTwo =
            Profile("Mia", 25, Gender.FEMALE, "description", Location("Hamburg", "20095"))

        assertNotEquals(profileOne, profileTwo)
    }

    /**
     * Tests that same data profile objects are the same.
     */
    @Test
    fun test_sameProfileEquals() {
        val profileOne =
            Profile("Mia", 22, Gender.FEMALE, "description", Location("Hamburg", "20095"))
        val profileTwo =
            Profile("Mia", 22, Gender.FEMALE, "description", Location("Hamburg", "20095"))

        assertEquals(profileOne, profileTwo)
    }

    /**
     * Tests that similar profiles object is not the same.
     */
    @Test
    fun test_multipleProfilesNotEquals() {
        val profilesOne = Profiles(
            listOf(
                Profile("Mia", 22, Gender.FEMALE, "description", Location("Hamburg", "20095")),
                Profile("Jonas", null, Gender.MALE, "description", Location("Hamburg", "20095"))
            )
        )

        val profilesTwo = Profiles(
            listOf(
                Profile("Marc", 27, Gender.MALE, "description", Location("Hamburg", "20095")),
                Profile("Jonas", null, Gender.MALE, "description", Location("Hamburg", "20095"))
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
                Profile("Mia", 22, Gender.FEMALE, "description", Location("Hamburg", "20095")),
                Profile("Jonas", null, Gender.MALE, "description", Location("Hamburg", "20095"))
            )
        )

        val profilesTwo = Profiles(
            listOf(
                Profile("Mia", 22, Gender.FEMALE, "description", Location("Hamburg", "20095")),
                Profile("Jonas", null, Gender.MALE, "description", Location("Hamburg", "20095"))
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

}