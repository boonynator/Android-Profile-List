package com.steven.casestudyprofilelist.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.steven.casestudyprofilelist.R
import com.steven.casestudyprofilelist.models.Profiles

class ProfileSlideFragment : Fragment() {

    private lateinit var viewPager: ViewPager2
    private var pagerSize: Int = 0

    private lateinit var users: Profiles
    private var userIndex: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (arguments != null) {
            users = requireArguments().get("user_list") as Profiles
            userIndex = requireArguments().get("user_index") as Int

            pagerSize = users.profiles.size
        }
        return inflater.inflate(R.layout.activity_profileslide, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewPager = view.findViewById(R.id.viewpager_profileslider)

        val pagerAdapter = SlideAdapter(requireActivity())
        viewPager.adapter = pagerAdapter
        viewPager.setCurrentItem(userIndex, false)
    }

    private inner class SlideAdapter(fragmentActivity: FragmentActivity) :
        FragmentStateAdapter(fragmentActivity) {

        override fun getItemCount(): Int = pagerSize

        override fun createFragment(position: Int): Fragment {
            val profileDetailFragment = ProfileDetailFragment()
            profileDetailFragment.arguments = Bundle().apply {
                putParcelable("user_list", users)
                putInt("user_curr_index", userIndex)
                putInt("user_position", position)
            }

            return profileDetailFragment
        }

    }

}