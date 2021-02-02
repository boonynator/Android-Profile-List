package com.steven.casestudyprofilelist

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.steven.casestudyprofilelist.models.Location

class ProfileDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profiledetails)

        Log.d("Profile Detail Activity", intent.extras.toString())
        if (intent.extras != null) {
            val gender: String = intent.extras!!.get("gender") as String
            val name: String = intent.extras!!.get("name") as String
            val age: Int? = intent.extras!!.get("age") as Int?
            val location: Location = intent.extras!!.get("location") as Location
            val description: String = intent.extras!!.get("description") as String

            if (age != null) {
                findViewById<TextView>(R.id.textview_profiledetails_age).text = "$age"
                findViewById<TextView>(R.id.textview_profiledetails_name).text = "$name, "
            } else {
                findViewById<TextView>(R.id.textview_profiledetails_name).text = name
            }

            findViewById<TextView>(R.id.textview_profiledetails_zip).text = "${location.zip} "
            findViewById<TextView>(R.id.textview_profiledetails_city).text = location.city
            findViewById<TextView>(R.id.textview_profiledetails_description).text = description

            findViewById<ImageView>(R.id.imageview_profiledetails_avatar)
                .setImageResource(
                    if (gender == "MALE")
                        R.drawable.ic_user_male_128
                    else R.drawable.ic_female_user_128
                )
        }
    }
}