package com.example.dz_sofascore.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.dz_sofascore.MainActivityViewModel
import com.example.dz_sofascore.R
import com.example.dz_sofascore.model.F1Driver

class MyDriversFragment : Fragment() {

    private val viewModel: MainActivityViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val prikaz = inflater.inflate(R.layout.fragment_my_drivers, container, false)

        val linLayout = prikaz.findViewById<LinearLayout>(R.id.myDrivers_linearLayout)
        viewModel.listOfDrivers.observe(viewLifecycleOwner) {
            linLayout.removeAllViews()

            for (f1Driver in it) {
                val row = inflater.inflate(R.layout.my_drivers_rows, linLayout, false)
                val driverInfo = row.findViewById<TextView>(R.id.myDrivers_tv)
                driverInfo.text = f1Driver.getInfo()
                linLayout.addView(row)
            }
        }
        return prikaz
    }

    private fun F1Driver.getInfo() = "$firstName $lastName, $team, $nationality"
}
