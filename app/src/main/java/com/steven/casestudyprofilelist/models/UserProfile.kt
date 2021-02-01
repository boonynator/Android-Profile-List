package com.steven.casestudyprofilelist.models

data class UserProfile constructor(
    // declare all as var since it makes sense for them to be mutable
    // (unless you wanna create a user every single time)
    var name: String,
    var age: Int?,
    var gender: String,
    var description: String,
    var zipCode: String,
    var city: String
) {
    fun hasAge() = age != null

    fun formattedLocation() = "$zipCode $city"

    fun formattedName() = if (hasAge()) "$name, " else name

    override fun toString(): String {
        return "UserProfile(name='$name', gender='$gender', description='$description', location=${formattedLocation()}, age=$age)"
    }
}