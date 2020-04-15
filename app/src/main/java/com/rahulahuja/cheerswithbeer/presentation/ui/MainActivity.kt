package com.rahulahuja.cheerswithbeer.presentation.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.rahulahuja.cheerswithbeer.R
import com.rahulahuja.cheerswithbeer.presentation.ui.beers.BeersFragment


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()

        replaceFragment(BeersFragment.newInstance(), true)
    }

    fun replaceFragment(newFragment: Fragment, addToBackStack: Boolean) {
        supportFragmentManager.beginTransaction()
            .addToBackStack(newFragment::class.java.simpleName)
            .replace(R.id.fcv_fragment_container, newFragment).commit()
    }
}
