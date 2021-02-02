package com.steven.casestudyprofilelist

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.steven.casestudyprofilelist.helpers.GsonProfileParser
import com.steven.casestudyprofilelist.models.Profiles

class ProfilesActivity : AppCompatActivity() {

    companion object {
        const val PROFILES_TAG = "Profiles Activity Log"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview_profiles)
        val recyclerViewAdapter = ProfileAdapter(Profiles(emptyList()))

        recyclerView.apply {
            adapter = recyclerViewAdapter
            addItemDecoration(DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL))
        }

        recyclerViewAdapter.addItems(GsonProfileParser(applicationContext).readDataFromJson())
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

    class ProfileAdapter(private var models: Profiles) :
        RecyclerView.Adapter<ProfileAdapter.ViewHolder>() {

        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val avatarView: ImageView = view.findViewById(R.id.imageview_profile_avatar)
            val nameView: TextView = view.findViewById(R.id.textview_profile_name)
            val ageView: TextView = view.findViewById(R.id.textview_profile_age)
            val cityView: TextView = view.findViewById(R.id.textview_profile_city)
            val zipView: TextView = view.findViewById(R.id.textview_profile_zip)

            init {
                view.setOnClickListener {
                    val bundle = Bundle()
                    bundle.putString("gender", models.profiles[adapterPosition].gender)
                    bundle.putString("name", models.profiles[adapterPosition].name)
                    if (models.profiles[adapterPosition].age != null) {
                        bundle.putInt("age", models.profiles[adapterPosition].age!!)
                    }
                    bundle.putParcelable("location", models.profiles[adapterPosition].location)
                    val intent = Intent(view.context, ProfileDetailActivity::class.java)
                        .putExtras(bundle)
                    view.context.startActivity(intent)
                }
            }
        }

        fun addItems(profilesToAdd: Profiles) {
            val previousLastPosition = models.profiles.size - 1
            models = profilesToAdd
            notifyItemRangeInserted(previousLastPosition, profilesToAdd.profiles.size)
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
                holder.ageView.text = age.toString()
                val formattedName = "${models.profiles[position].name}, "
                holder.nameView.text = formattedName
            } else {
                holder.nameView.text = models.profiles[position].name
                holder.ageView.visibility = View.INVISIBLE
            }

            holder.zipView.text = models.profiles[position].location.zip
            holder.cityView.text = models.profiles[position].location.city
            // put name in textview
        }

        override fun getItemCount(): Int {
            return models.profiles.size
        }

    }
}