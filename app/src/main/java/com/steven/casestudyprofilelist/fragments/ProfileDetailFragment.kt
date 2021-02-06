package com.steven.casestudyprofilelist.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.steven.casestudyprofilelist.R
import com.steven.casestudyprofilelist.models.Profiles

class ProfileDetailFragment : Fragment() {

    lateinit var users: Profiles
    var userIndex = 0
    var userCurrentIndex = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (arguments != null && requireArguments().get("user_list") != null
            && requireArguments().get("user_curr_index") != null
            && requireArguments().get("user_position") != null
        ) {
            users = requireArguments().get("user_list") as Profiles
            userCurrentIndex = requireArguments().get("user_curr_index") as Int
            userIndex = requireArguments().get("user_position") as Int
        } else {
            Snackbar.make(
                requireActivity().findViewById(android.R.id.content),
                "User data could not be loaded.",
                Snackbar.LENGTH_INDEFINITE
            ).show()
        }
        return inflater.inflate(R.layout.fragment_profiledetails, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d("Profile Detail Fragment", users.profiles.toString())
        Log.d("Profile Detail Fragment", userIndex.toString())

        val profiles = users.profiles

        if (profiles[userIndex].age != null) {
            view.findViewById<TextView>(R.id.textview_profiledetails_age).text =
                profiles[userIndex].age.toString()
            view.findViewById<TextView>(R.id.textview_profiledetails_name).text =
                getString(R.string.formatted_user_name, profiles[userIndex].name)
        } else {
            view.findViewById<TextView>(R.id.textview_profiledetails_name).text =
                profiles[userIndex].name
        }

        view.findViewById<TextView>(R.id.textview_profiledetails_zip).text =
            getString(R.string.formatted_user_zip, profiles[userIndex].location.zip)
        view.findViewById<TextView>(R.id.textview_profiledetails_city).text =
            profiles[userIndex].location.city
        view.findViewById<TextView>(R.id.textview_profiledetails_description).text =
            profiles[userIndex].description

        view.findViewById<ImageView>(R.id.imageview_profiledetails_avatar)
            .setImageResource(
                if (profiles[userIndex].gender == "MALE")
                    R.drawable.ic_user_male_128
                else R.drawable.ic_female_user_128
            )
    }

}