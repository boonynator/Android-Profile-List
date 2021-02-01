package com.steven.casestudyprofilelist

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.steven.casestudyprofilelist.helpers.processProfilesJson
import com.steven.casestudyprofilelist.models.UserModel

class ProfilesActivity : AppCompatActivity() {

    companion object {
        const val PROFILES_TAG = "Profiles Activity Log"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview_profiles)
        val recyclerViewAdapter = ProfileAdapter(emptyList())

        recyclerView.apply {
            adapter = recyclerViewAdapter
            addItemDecoration(DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL))
        }

        recyclerViewAdapter.addItems(processProfilesJson(baseContext))
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

    class ProfileAdapter(private var models: List<UserModel>) :
        RecyclerView.Adapter<ProfileAdapter.ViewHolder>() {

        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val avatarView: ImageView = view.findViewById(R.id.imageview_profile_avatar)
            val nameView: TextView = view.findViewById(R.id.textview_profile_name)
            val ageView: TextView = view.findViewById(R.id.textview_profile_age)
            val locationView: TextView = view.findViewById(R.id.textview_profile_location)

            init {
                view.setOnClickListener {
                    val bundle = Bundle()
                    bundle.putString("gender", models[adapterPosition].gender)
                    bundle.putString("name", models[adapterPosition].name)
                    if(models[adapterPosition].age != null) {
                        bundle.putInt("age", models[adapterPosition].age!!)
                    }
                    bundle.putString("location", models[adapterPosition].formattedLocation())
                    val intent = Intent(view.context, ProfileDetailActivity().javaClass)
                    view.context.startActivity(intent, bundle)
                }
            }
        }

        fun addItems(profilesToAdd: List<UserModel>) {
            val previousLastPosition = models.size - 1
            models += profilesToAdd
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
                    if (models[position].gender == "MALE")
                        R.drawable.ic_user_male
                    else R.drawable.ic_female_user
                )

            if (models[position].hasAge()) {
                holder.ageView.text = models[position].age.toString()
            } else {
                holder.ageView.visibility = View.INVISIBLE
            }

            holder.locationView.text = models[position].formattedLocation()
            // put name in textview
            holder.nameView.text = models[position].formattedName()
        }

        override fun getItemCount(): Int {
            return models.size
        }

    }
}