package com.steven.casestudyprofilelist.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Profiles(var profiles: List<Profile>) : Parcelable

@Parcelize
data class Profile(
    val name: String,
    val age: Int?,
    val gender: String,
    val description: String,
    val location: Location
) : Parcelable

@Parcelize
data class Location(val city: String, val zip: String) : Parcelable