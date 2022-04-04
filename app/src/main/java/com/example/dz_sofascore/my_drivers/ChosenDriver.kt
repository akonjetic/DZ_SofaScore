package com.example.dz_sofascore.my_drivers

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import coil.load
import coil.transform.CircleCropTransformation
import com.example.dz_sofascore.R
import com.example.dz_sofascore.databinding.ChosenDriverLayoutBinding
import com.example.dz_sofascore.helpers.StatusBarHelper
import com.example.dz_sofascore.model.F1Driver
import com.google.android.material.snackbar.Snackbar

private lateinit var binding: ChosenDriverLayoutBinding
const val EXTRA_DRIVER = "newDriver"

class ChosenDriver : AppCompatActivity() {
    @SuppressLint("ResourceType", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ChosenDriverLayoutBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        with(StatusBarHelper()) {
            setStatusBarColor(Color.parseColor(getString(R.color.teal_200)))
        }

        val chosenDriver = intent.extras?.get(EXTRA_DRIVER) as F1Driver

        Snackbar.make(view, chosenDriver.firstName + " " + chosenDriver.lastName + " " + getString(R.string.chosenDriver), Snackbar.LENGTH_LONG).show()

        binding.driverImage.load(chosenDriver.imageURL) {
            placeholder(R.drawable.driver_profile_image)
            error(R.drawable.ic_racer)
            transformations(CircleCropTransformation())
        }
        binding.toolbarTitle.text = chosenDriver.firstName + " " + chosenDriver.lastName
        binding.driverAge.text = getString(R.string.driverAge) + ": " + chosenDriver.age.toString()
        binding.driverGender.text = getString(R.string.driverGender) + ": " + chosenDriver.gender
        binding.driverTeam.text = getString(R.string.driverTeam) + ": " + chosenDriver.team
        binding.driverTeammate.text = getString(R.string.driverTeammate) + ": " + chosenDriver.teammate
        binding.driverFavoriteTrack.text = getString(R.string.driverFavTrack) + ": " + chosenDriver.favoriteTrack
        binding.driverEngine.text = getString(R.string.driverConstructor) + ": " + chosenDriver.engineConstructor
    }
}
