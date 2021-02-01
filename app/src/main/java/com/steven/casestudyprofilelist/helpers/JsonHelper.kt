package com.steven.casestudyprofilelist.helpers

import android.content.Context
import android.util.JsonReader
import com.steven.casestudyprofilelist.models.UserModel

fun processProfilesJson(context: Context): List<UserModel> {
    val newModelList: MutableList<UserModel> = listOf<UserModel>().toMutableList()
    val jsonReader = JsonReader(context.assets.open("profiles.json").reader())
    jsonReader.beginObject()
    if (jsonReader.nextName() == "profiles") {
        jsonReader.beginArray()
        while (jsonReader.hasNext()) {
            newModelList += createProfilesFromString(jsonReader)
        }
        jsonReader.endArray()
    }
    jsonReader.endObject()
    return newModelList
}

private fun createProfilesFromString(reader: JsonReader): UserModel {
    var name = ""
    var age: Int? = null
    var gender = ""
    var description = ""
    var zipCode = ""
    var city = ""

    reader.beginObject()
    while (reader.hasNext()) {
        val token = reader.nextName()
        if (token == "name") name = reader.nextString()
        if (token == "age") age = reader.nextInt()
        if (token == "gender") gender = reader.nextString()
        if (token == "description") description = reader.nextString()
        if (token == "location") {
            val locationList = readLocationFromJson(reader)
            city = locationList[0]
            zipCode = locationList[1]
        }
    }
    reader.endObject()

    return UserModel(name, age, gender, description, zipCode, city)
}

private fun readLocationFromJson(reader: JsonReader): List<String> {
    val locationList = emptyList<String>().toMutableList()
    reader.beginObject()
    while (reader.hasNext()) {
        val token = reader.nextName()
        if (token == "city") locationList += reader.nextString()
        if (token == "zip") locationList += reader.nextString()
    }
    reader.endObject()

    return locationList
}