package com.steven.casestudyprofilelist.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class Profiles(val profiles: List<Profile>)

data class Profile(
    val name: String,
    val age: Int?,
    val gender: String,
    val description: String,
    val location: Location
)

@Parcelize
data class Location(val city: String, val zip: String) : Parcelable