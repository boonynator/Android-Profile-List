package com.steven.casestudyprofilelist.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.steven.casestudyprofilelist.R

class ProfileDetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("Test", "TESTEST")
        Log.d("Test", requireArguments().get("user").toString())
        return inflater.inflate(R.layout.activity_profiledetails, container, false)
    }

}