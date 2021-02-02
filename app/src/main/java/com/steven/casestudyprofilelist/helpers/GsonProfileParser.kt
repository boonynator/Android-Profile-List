package com.steven.casestudyprofilelist.helpers

import android.content.Context
import com.google.gson.Gson
import com.steven.casestudyprofilelist.models.Profiles
import java.io.InputStream

class GsonProfileParser(private val context: Context) {

    fun readDataFromJson(): Profiles {
        val inputStream: InputStream = context.assets.open("profiles.json")
        val file = inputStream.bufferedReader()
        return Gson().fromJson(file.readText(), Profiles::class.java)
    }

}