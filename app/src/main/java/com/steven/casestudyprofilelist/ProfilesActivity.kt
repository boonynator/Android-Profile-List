package com.steven.casestudyprofilelist

import android.os.Bundle
import android.util.JsonReader
import android.util.Log
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.steven.casestudyprofilelist.models.UserProfile

class ProfilesActivity : AppCompatActivity() {

    companion object {
        const val PROFILES_TAG = "Profiles Activity Log"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview_profiles)
        val recyclerViewAdapter = ProfileAdapter(emptyList())
        recyclerView.adapter = recyclerViewAdapter

        val testUser1 = UserProfile("Anna", 17, "FEMALE", "Test1", "22397", "Hamburg")
        val testUser2 = UserProfile("Magnus", 29, "MALE", "Test2", "28201", "Bremen")
        val testUser3 = UserProfile("Steven", null, "MALE", "Test3", "00001", "Mars")
        recyclerViewAdapter.addItems(listOf(testUser1, testUser2, testUser3))

        Log.e(PROFILES_TAG, processProfilesJson().toString())
    }

    fun processProfilesJson(): List<UserProfile> {
        val newProfileList: MutableList<UserProfile> = listOf<UserProfile>().toMutableList()
        val jsonReader = JsonReader(baseContext.assets.open("profiles.json").reader())
        jsonReader.beginObject()
        while (jsonReader.hasNext()) {
            if (jsonReader.nextName() == "profiles") {
                jsonReader.beginArray()
                newProfileList += createProfilesFromString(jsonReader)
            }
        }
        jsonReader.endObject()
        return newProfileList
    }

    private fun createProfilesFromString(reader: JsonReader): UserProfile {
        var name = ""
        var age: Int? = null
        var gender = ""
        var description = ""
        var zipCode = ""
        var city = ""

        while (reader.hasNext()) {
            reader.beginObject()
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
            Log.d(PROFILES_TAG, token)
            reader.endObject()
        }

        return UserProfile(name, age, gender, description, zipCode, city)
    }

    private fun readLocationFromJson(reader: JsonReader): List<String> {
        val locationList = emptyList<String>().toMutableList()
        reader.beginObject()
        while (reader.hasNext()) {
            val token = reader.nextName()
            if (token == "city") locationList += reader.nextString()
            if (token == "zip") locationList += reader.nextString()
            Log.d(PROFILES_TAG, token)
        }
        reader.endObject()

        return locationList
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    class ProfileAdapter(private var profiles: List<UserProfile>) :
        RecyclerView.Adapter<ProfileAdapter.ViewHolder>() {

        var itemClick: ((UserProfile) -> Unit)? = null

        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val avatarView: ImageView = view.findViewById(R.id.imageview_profile_avatar)
            val nameView: TextView = view.findViewById(R.id.textview_profile_name)
            val ageView: TextView = view.findViewById(R.id.textview_profile_age)
            val locationView: TextView = view.findViewById(R.id.textview_profile_location)

            init {
                view.setOnClickListener {
                    itemClick?.invoke(profiles[adapterPosition])
                    Snackbar.make(
                        view,
                        "Clicked on ${profiles[adapterPosition]}]",
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
            }
        }

        fun addItems(profilesToAdd: List<UserProfile>) {
            val previousLastPosition = profiles.size - 1
            profiles += profilesToAdd
            notifyItemRangeInserted(previousLastPosition, profilesToAdd.size)
        }

        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.row_item, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {

            holder.avatarView
                .setImageResource(
                    if (profiles[position].gender == "MALE")
                        R.drawable.ic_user_male
                    else R.drawable.ic_female_user
                )

            if (profiles[position].hasAge()) {
                holder.ageView.text = profiles[position].age.toString()
            } else {
                holder.ageView.visibility = View.INVISIBLE
            }

            holder.locationView.text = profiles[position].formattedLocation()
            // put name in textview
            holder.nameView.text = profiles[position].formattedName()
        }

        override fun getItemCount(): Int {
            return profiles.size
        }

    }
}