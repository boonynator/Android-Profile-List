package com.steven.casestudyprofilelist

import android.content.Context
import android.content.res.AssetManager
import com.steven.casestudyprofilelist.helpers.profilesFromJson
import com.steven.casestudyprofilelist.models.Gender
import com.steven.casestudyprofilelist.models.Location
import com.steven.casestudyprofilelist.models.Profile
import com.steven.casestudyprofilelist.models.Profiles
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.anyString
import org.mockito.MockitoAnnotations.initMocks
import java.io.FileNotFoundException

class ParserUnitTest {

    private val parserTestTag = "Parser Unit Test Log"

    @Mock
    lateinit var mockContext: Context

    @Mock
    lateinit var mockAssetManager: AssetManager

    /**
     * Tests functionality of parsing JSON to Profiles
     */
    @Test
    fun test_profilesFromJson() {
        val defaultDescription =
            "Lorem ipsum dolorsitamet,consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et"
        val fileName = "test.json"
        val jsonString = """
        {
          "profiles": [
                {
                  "name": "Mia",
                  "age": 22,
                  "gender": "FEMALE",
                  "location": {
                    "city": "Hamburg",
                    "zip": "20095"
                  },
                  "description": "$defaultDescription"
                },
                {
                  "name": "Jonas",
                  "gender": "MALE",
                  "location": {
                    "city": "Hamburg",
                    "zip": "20095"
                  },
                  "description": "$defaultDescription"
                }
            ]
        }
        """.trimIndent()

        initMocks(this)

        `when`(mockContext.assets).thenReturn(mockAssetManager)
        `when`(mockContext.assets.open(fileName)).thenReturn(jsonString.byteInputStream())

        val profilesFromJson = profilesFromJson(fileName, mockContext, parserTestTag)
        val actualProfiles =
            Profiles(
                listOf(
                    Profile("Mia", 22, Gender.FEMALE, defaultDescription, Location("Hamburg", "20095")),
                    Profile("Jonas", null, Gender.MALE, defaultDescription, Location("Hamburg", "20095"))
                )
            )

        assertEquals(actualProfiles, profilesFromJson)
    }

    /**
     * Tests functionality of parsing an empty JSON.
     */
    @Test
    fun test_emptyJson() {
        val jsonString = ""

        initMocks(this)

        `when`(mockContext.assets).thenReturn(mockAssetManager)
        `when`(mockContext.assets.open(anyString())).thenReturn(jsonString.byteInputStream())

        val profilesFromJson = profilesFromJson("any.json", mockContext, parserTestTag)
        val actualProfiles = Profiles(emptyList())

        assertEquals(actualProfiles, profilesFromJson)
    }

    /**
     * Tests functionality of parsing a non-existing JSON file.
     */
    @Test
    fun test_fileNotFound() {
        initMocks(this)

        `when`(mockContext.assets).thenReturn(mockAssetManager)
        `when`(mockContext.assets.open(anyString())).thenThrow(FileNotFoundException())

        assertEquals(
            profilesFromJson("any.json", mockContext, parserTestTag),
            Profiles(emptyList())
        )
    }
}