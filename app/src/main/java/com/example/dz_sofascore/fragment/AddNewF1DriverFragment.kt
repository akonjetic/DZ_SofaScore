package com.example.dz_sofascore.fragment

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.graphics.Color
import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.dz_sofascore.MainActivityViewModel
import com.example.dz_sofascore.R
import com.example.dz_sofascore.databinding.FragmentAddNewF1DriverBinding
import com.example.dz_sofascore.enums.RaceTracks
import com.example.dz_sofascore.helpers.EditTextHelper
import com.example.dz_sofascore.helpers.FormClearHelper
import com.example.dz_sofascore.helpers.RadioGroupHelper
import com.example.dz_sofascore.helpers.StatusBarHelper
import com.example.dz_sofascore.model.F1Driver

class AddNewF1DriverFragment : Fragment() {

    private val viewModel: MainActivityViewModel by activityViewModels()
    private lateinit var binding: FragmentAddNewF1DriverBinding
    private lateinit var listOfElements: ArrayList<EditText>

    @SuppressLint("ResourceType")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        with(StatusBarHelper()) {
            activity?.setStatusBarColor(Color.parseColor(getString(R.color.teal_200)))
        }

        binding = FragmentAddNewF1DriverBinding.inflate(LayoutInflater.from(context))

        listOfElements = ArrayList<EditText>()
        listOfElements.add(binding.editFirstName.getEditTextContent())
        listOfElements.add(binding.editLastName.getEditTextContent())
        listOfElements.add(binding.editTeam.getEditTextContent())
        listOfElements.add(binding.editNationality.getEditTextContent())
        listOfElements.add(binding.editTeammate.getEditTextContent())
        listOfElements.add(binding.editEngine.getEditTextContent())
        listOfElements.add(binding.editAge.getEditTextContent())

        val raceTracks = getNames(enumValues<RaceTracks>().toList())
        val arrayAdapter = ArrayAdapter(
            requireContext(),
            androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
            raceTracks
        )
        binding.favoriteTrackSpinner.adapter = arrayAdapter

        with(EditTextHelper()) {
            for (editText in listOfElements) {
                if (editText.inputType != InputType.TYPE_CLASS_NUMBER) {
                    editText.editTextFocusListener(requireContext())
                } else {
                    editText.numberFocusListener(requireContext())
                }
            }
        }

        binding.addNewDriverButton.setOnClickListener {

            var flag = false
            var isItValid: String?

            with(EditTextHelper()) {
                for (editText in listOfElements) {

                    isItValid = if (editText.inputType != InputType.TYPE_CLASS_NUMBER) {
                        editText.validateLength(requireContext())
                    } else {
                        editText.validateNumber(requireContext())
                    }

                    if (isItValid != null) {
                        flag = true
                    }

                    isItValid = ""
                }

                isItValid = binding.imageUrl.getEditTextContent().validateURLLength(requireContext())

                if (isItValid != null) {
                    flag = true
                }

                isItValid = ""
            }

            with(RadioGroupHelper()) {
                isItValid = binding.genderRadioGroup.validateRadioGroup(requireContext())
            }

            if (isItValid != null) {
                flag = true
            }

            isItValid = ""

            if (!flag) {
                val genderId = binding.genderRadioGroup.checkedRadioButtonId
                val genderString = resources.getResourceEntryName(genderId)

                val f1Driver = F1Driver(
                    binding.editFirstName.getContent(), binding.editLastName.getContent(), binding.editAge.getContent().toInt(), genderString, binding.editTeam.getContent(), binding.editNationality.getContent(),
                    binding.favoriteTrackSpinner.selectedItem.toString(), binding.editTeammate.getContent(), binding.editEngine.getContent(), binding.imageUrl.getContent()
                )

                viewModel.f1DriverAdd(f1Driver)

                Toast.makeText(requireContext(), getString(R.string.submittedForm), Toast.LENGTH_LONG).show()

                FormClearHelper().clearText(listOfElements)
                binding.imageUrl.getEditTextContent().text.clear()
                FormClearHelper().clearRadioButton(binding.genderRadioGroup)
                FormClearHelper().clearSpinner(binding.favoriteTrackSpinner)
            } else {
                val builder = AlertDialog.Builder(requireContext())
                    .setTitle(getString(R.string.invalidForm))
                    .setMessage(getString(R.string.invalidFormText))
                    .setPositiveButton(getString(R.string.ok)) { _, _ ->
                    }

                builder.show()

                flag = false
            }
        }

        return binding.root
    }

    private fun getNames(list: List<RaceTracks>): ArrayList<String> {
        val names = ArrayList<String>()
        for (item in list) {
            names.add(item.names)
        }

        return names
    }
}
