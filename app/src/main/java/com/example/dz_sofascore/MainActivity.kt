package com.example.dz_sofascore

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.dz_sofascore.fragment.AddNewF1DriverFragment
import com.example.dz_sofascore.fragment.MyDriversFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // zadaca1()

        // zadaca2()

        zadaca3()
    }

    /*private fun zadaca1() {
        setContentView(R.layout.activity_main)

        val showHideBtn = findViewById<Button>(R.id.showHideButton)
        val helloWorldTextView = findViewById<TextView>(R.id.helloWorldTextView)

        showHideBtn.setOnClickListener {
            if (showHideBtn.text.equals(getString(R.string.button_show))) {
                helloWorldTextView.isVisible = true
                showHideBtn.text = getString(R.string.button_hide)
            } else {
                helloWorldTextView.isVisible = false
                showHideBtn.text = getString(R.string.button_show)
            }
        }
    }*/

   /* private fun zadaca2() {
        setContentView(R.layout.activity_main_view_model)

        setUpTabs()
    }

    private fun setUpTabs() {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(AddNewF1DriverFragment(), getString(R.string.addDriverTab))
        adapter.addFragment(MyDriversFragment(), getString(R.string.myDriversTab))

        val viewPager = findViewById<ViewPager>(R.id.tab_viewpager)
        viewPager.adapter = adapter

        val tabs = findViewById<TabLayout>(R.id.tabs)
        tabs.setupWithViewPager(viewPager)
    }
*/
    private fun zadaca3() {
        setContentView(R.layout.activity_main)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        val addDriversFragment = AddNewF1DriverFragment()
        val myDriversFragment = MyDriversFragment()

        setCurrentFragment(addDriversFragment)

        bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.ic_addNewDriver -> setCurrentFragment(addDriversFragment)
                R.id.ic_myDrivers -> setCurrentFragment(myDriversFragment)
            }
            true
        }
    }

    private fun setCurrentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment, fragment)
            commit()
        }
    }
}
