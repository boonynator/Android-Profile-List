package com.steven.casestudyprofilelist.adapters

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.steven.casestudyprofilelist.ProfileSlideActivity
import com.steven.casestudyprofilelist.R
import com.steven.casestudyprofilelist.fragments.ProfileDetailFragment
import com.steven.casestudyprofilelist.models.Profiles

/**
 * Implements the logic for the ProfileAdapter.
 * @param models Profiles data object holding each Profile to show on the View
 * @param context Context on which the adapter operates
 */
class ProfileAdapter(private val models: Profiles, private val context: Context) :
    RecyclerView.Adapter<ProfileAdapter.ViewHolder>() {

    /**
     * Implements the ViewHolder for the ProfileAdapter.
     */
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val avatarView: ImageView = view.findViewById(R.id.imageview_profile_avatar)
        val nameView: TextView = view.findViewById(R.id.textview_profile_name)
        val ageView: TextView = view.findViewById(R.id.textview_profile_age)
        val cityView: TextView = view.findViewById(R.id.textview_profile_city)
        val zipView: TextView = view.findViewById(R.id.textview_profile_zip)

        init {
            view.setOnClickListener {
                val intent = Intent(context, ProfileSlideActivity::class.java)
                val bundle = Bundle()
                bundle.putParcelable("user", models.profiles[adapterPosition])
                bundle.putParcelable("user_list", models)
                bundle.putInt("user_index", adapterPosition)
                intent.putExtras(bundle)

//                val detailFragment = ProfileDetailFragment()
//                detailFragment.arguments = bundle
//                val activity = context as FragmentActivity
//                activity.supportFragmentManager.beginTransaction()
//                    .replace(R.id.fragment_container, detailFragment)
//                    .addToBackStack(null)
//                    .commit()
                context.startActivity(intent)
            }
        }
    }

    /**
     * Implements the functionality of adding items to the models data object.
     */
    fun addItems(profilesToAdd: Profiles) {
        val startPosition = models.profiles.size
        models.profiles += profilesToAdd.profiles
        notifyItemRangeInserted(startPosition, profilesToAdd.profiles.size)
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
                if (models.profiles[position].gender == "MALE")
                    R.drawable.ic_user_male
                else R.drawable.ic_female_user
            )

        val age = models.profiles[position].age

        if (age != null) {
            holder.nameView.text =
                context.getString(R.string.formatted_user_name, models.profiles[position].name)
            holder.ageView.text = age.toString()
            holder.ageView.visibility = View.VISIBLE
        } else {
            holder.nameView.text = models.profiles[position].name
            holder.ageView.visibility = View.GONE
        }

        holder.zipView.text = context.getString(
            R.string.formatted_user_zip,
            models.profiles[position].location.zip
        )
        holder.cityView.text = models.profiles[position].location.city
    }

    override fun getItemCount(): Int {
        return models.profiles.size
    }

}