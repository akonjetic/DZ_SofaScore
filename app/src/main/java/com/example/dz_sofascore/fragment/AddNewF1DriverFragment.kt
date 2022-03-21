package com.example.dz_sofascore.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.dz_sofascore.MainActivityViewModel
import com.example.dz_sofascore.R
import com.example.dz_sofascore.model.F1Driver

class AddNewF1DriverFragment : Fragment() {

    private val viewModel: MainActivityViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val prikaz = inflater.inflate(R.layout.fragment_add_new_f1_driver, container, false)

        val btn = prikaz.findViewById<Button>(R.id.addNewDriverButton)
        val driverFirstName = prikaz.findViewById<EditText>(R.id.driverFirstName)
        val driverLastName = prikaz.findViewById<EditText>(R.id.driverLastName)
        val driverTeam = prikaz.findViewById<EditText>(R.id.driverTeam)
        val driverNationality = prikaz.findViewById<EditText>(R.id.driverNationality)
        val driverFavTrack = prikaz.findViewById<EditText>(R.id.driverFavTrack)
        val driverTeammate = prikaz.findViewById<EditText>(R.id.driverTeammate)
        val driverEngine = prikaz.findViewById<EditText>(R.id.driverEngine)

        btn.setOnClickListener {
            val f1Driver = F1Driver(
                driverFirstName.text.toString(), driverLastName.text.toString(), driverTeam.text.toString(), driverNationality.text.toString(),
                driverFavTrack.text.toString(), driverTeammate.text.toString(), driverEngine.text.toString()
            )

            viewModel.f1DriverAdd(f1Driver)

            driverFirstName.text.clear()
            driverLastName.text.clear()
            driverTeam.text.clear()
            driverNationality.text.clear()
            driverFavTrack.text.clear()
            driverTeammate.text.clear()
            driverEngine.text.clear()
        }

        return prikaz
    }
}
