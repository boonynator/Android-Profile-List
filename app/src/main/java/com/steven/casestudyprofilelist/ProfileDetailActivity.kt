package com.steven.casestudyprofilelist

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.w3c.dom.Text

class ProfileDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.fragment_profiledetails)

        val name: String = savedInstanceState?.get("name") as String
        val age: Int = savedInstanceState.get("age") as Int

        findViewById<TextView>(R.id.textview_profile_name).text = name
        findViewById<TextView>(R.id.textview_profile_age).text = age.toString()
    }

}