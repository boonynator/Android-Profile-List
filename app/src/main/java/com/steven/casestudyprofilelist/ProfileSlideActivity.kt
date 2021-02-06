package com.steven.casestudyprofilelist

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.steven.casestudyprofilelist.fragments.ProfileDetailFragment
import com.steven.casestudyprofilelist.models.Profile
import com.steven.casestudyprofilelist.models.Profiles

class ProfileSlideActivity : FragmentActivity() {

    private lateinit var viewPager: ViewPager2
    private var pagerSize: Int = 0

    private lateinit var user: Profile
    private lateinit var users: Profiles
    private var userIndex: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profileslide)

        viewPager = findViewById(R.id.viewpager_profileslider)

        if (intent.extras != null) {
            user = intent.extras!!.get("user") as Profile
            users = intent.extras!!.get("user_list") as Profiles
            userIndex = intent.extras!!.get("user_index") as Int

            pagerSize = users.profiles.size
        }

        val pagerAdapter = SlideAdapter(this)
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