package com.steven.casestudyprofilelist.helpers

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.steven.casestudyprofilelist.models.Profiles
import java.io.FileNotFoundException
import java.io.InputStream

/**
 * Parses Profiles data from a JSON file.
 *
 * @param filename Input JSON file name.
 * @param context Context from which the function is being called.
 * @return Profiles data object with corresponding List of Profile data objects within.
 *         If the file does not exist or the JSON is empty return Profiles object with
 *         empty list inside.
 */
fun profilesFromJson(filename: String, context: Context, logTag: String): Profiles {
    val inputStream: InputStream = try {
        context.assets.open(filename)
    } catch (fileError: FileNotFoundException) {
        Log.e(logTag, "JSON File could not be found: ${fileError.message}")
        return Profiles(emptyList())
    }
    val file = inputStream.bufferedReader()
    return Gson().fromJson(file.readText(), Profiles::class.java) ?: Profiles(emptyList())
}
