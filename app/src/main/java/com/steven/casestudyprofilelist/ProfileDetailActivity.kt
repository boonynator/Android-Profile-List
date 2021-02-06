package com.steven.casestudyprofilelist

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.steven.casestudyprofilelist.models.Profile
import com.steven.casestudyprofilelist.models.Profiles

class ProfileDetailActivity : AppCompatActivity() {

    lateinit var profiles: Profiles

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profiledetails)

        val intentExists = intent.extras != null

        if (intentExists && intent.extras!!.get("user_list") != null) {
            profiles = intent.extras!!.get("user_list") as Profiles
        }

        if (intentExists && intent.extras!!.get("user") != null) {
            val user: Profile = intent.extras!!.get("user") as Profile

            if (user.age != null) {
                findViewById<TextView>(R.id.textview_profiledetails_age).text = user.age.toString()
                findViewById<TextView>(R.id.textview_profiledetails_name).text =
                    getString(R.string.formatted_user_name, user.name)
            } else {
                findViewById<TextView>(R.id.textview_profiledetails_name).text = user.name
            }

            findViewById<TextView>(R.id.textview_profiledetails_zip).text =
                getString(R.string.formatted_user_zip, user.location.zip)
            findViewById<TextView>(R.id.textview_profiledetails_city).text = user.location.city
            findViewById<TextView>(R.id.textview_profiledetails_description).text = user.description

            findViewById<ImageView>(R.id.imageview_profiledetails_avatar)
                .setImageResource(
                    if (user.gender == "MALE")
                        R.drawable.ic_user_male_128
                    else R.drawable.ic_female_user_128
                )
        } else {
            Snackbar.make(
                findViewById(R.id.activity_profiledetails),
                "User data could not be loaded.",
                Snackbar.LENGTH_INDEFINITE
            ).show()
        }
    }


}