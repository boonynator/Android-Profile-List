package com.steven.casestudyprofilelist.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.steven.casestudyprofilelist.R
import com.steven.casestudyprofilelist.adapters.ProfileAdapter
import com.steven.casestudyprofilelist.helpers.profilesFromJson
import com.steven.casestudyprofilelist.models.Profiles

class ProfilesFragment : Fragment() {

    companion object {
        const val TAG = "Profiles Fragment Log"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profiles, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (context != null) {
            val parentActivity = activity as FragmentActivity
            val profileAdapter = ProfileAdapter(Profiles(emptyList()), parentActivity)
            val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerview_profiles)

            recyclerView.apply {
                adapter = profileAdapter
                addItemDecoration(
                    DividerItemDecoration(
                        requireContext(),
                        DividerItemDecoration.VERTICAL
                    )
                )
                profileAdapter.addItems(profilesFromJson("profiles.json", requireContext(), TAG))
            }
        }
    }
}