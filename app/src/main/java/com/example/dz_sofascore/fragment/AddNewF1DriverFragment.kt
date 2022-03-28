package com.example.dz_sofascore.fragment

import android.app.AlertDialog
import android.content.Context
import android.content.res.Resources
import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.dz_sofascore.MainActivity
import com.example.dz_sofascore.MainActivityViewModel
import com.example.dz_sofascore.R
import com.example.dz_sofascore.databinding.FragmentAddNewF1DriverBinding
import com.example.dz_sofascore.enums.RaceTracks
import com.example.dz_sofascore.model.F1Driver


class AddNewF1DriverFragment : Fragment() {

    private val viewModel: MainActivityViewModel by activityViewModels()
    private lateinit var binding: FragmentAddNewF1DriverBinding
    private lateinit var listOfElements: ArrayList<EditText>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentAddNewF1DriverBinding.inflate(LayoutInflater.from(context))

        listOfElements = ArrayList<EditText>()
        listOfElements.add(binding.editFirstName.getEditTextContent())
        listOfElements.add(binding.editLastName.getEditTextContent())
        listOfElements.add(binding.editTeam.getEditTextContent())
        listOfElements.add(binding.editNationality.getEditTextContent())
        listOfElements.add(binding.editTeammate.getEditTextContent())
        listOfElements.add(binding.editEngine.getEditTextContent())
        listOfElements.add(binding.editAge.getEditTextContent())


        var raceTracks = getNames(enumValues<RaceTracks>().toList())
        val arrayAdapter = ArrayAdapter(requireContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, raceTracks)
        binding.favoriteTrackSpinner.adapter = arrayAdapter

        for(editText in listOfElements){
            if(editText.inputType != InputType.TYPE_CLASS_NUMBER)
                FocusAndValidate().editTextFocusListener(editText, requireContext())
            else
                FocusAndValidate().numberFocusListener(editText, requireContext())
        }

        binding.addNewDriverButton.setOnClickListener {
            val genderId = binding.genderRadioGroup.checkedRadioButtonId
            val genderString = resources.getResourceEntryName(genderId)
            var flag = true

           /* for(item in listOfElements){
                if (item.error != null){
                    flag = false
                }
            }*/

           /* if(!flag){*/
                val f1Driver = F1Driver(
                    binding.editFirstName.getContent(), binding.editLastName.getContent(), binding.editAge.getContent().toInt(), genderString, binding.editTeam.getContent(), binding.editNationality.getContent(),
                    binding.favoriteTrackSpinner.selectedItem.toString(), binding.editTeammate.getContent(), binding.editEngine.getContent()
                )

                viewModel.f1DriverAdd(f1Driver)
           /* }else{
                AlertDialog.Builder(requireContext())
                    .setTitle(getString(R.string.invalidForm))
                    .setMessage("Error.")
                    .setPositiveButton(getString(R.string.ok)){_,_ ->
                    }
            }*/

            /*val f1Driver = F1Driver(
                binding.editFirstName.getContent(), binding.editLastName.getContent(), binding.editAge.getContent().toInt(), genderString, binding.editTeam.getContent(), binding.editNationality.getContent(),
                binding.favoriteTrackSpinner.selectedItem.toString(), binding.editTeammate.getContent(), binding.editEngine.getContent()
            )

            binding.editFirstName
            viewModel.f1DriverAdd(f1Driver)*/

            //submitForm(listOfElements, genderString)


            FormWrapper().clearText(listOfElements)
            FormWrapper().clearRadioButton(binding.genderRadioGroup)/*
            ClearFormWrapper().clearSpinner(binding.favoriteTrackSpinner)*/
        }

        return binding.root
    }

    fun getNames(list : List<RaceTracks>) : ArrayList<String>{
        var names = ArrayList<String>()
        for(item in list){
            names.add(item.names)
        }

        return names
    }

    fun submitForm(listET : ArrayList<EditText>, buttonString: String){
        var flag = false

        for(item in listET){
            if(item.error.toString() != ""){
                flag = true
            }
        }

        if(buttonString == ""){
            flag = true
        }

        if(!flag){
            resetForm(listET, buttonString)
        }else{
            invalidForm(listET, buttonString)
        }

    }

    fun invalidForm(listET: ArrayList<EditText>, buttonString: String){
        var message = ""
        for(item in listET){
            if(item.error == null){
                message += "\n" + item.text.toString() +": " + item.error.toString()
            }
        }

        if(buttonString == ""){
            message += "\nGender must be chosen."
        }

        AlertDialog.Builder(requireContext())
            .setTitle(getString(R.string.invalidForm))
            .setMessage(message)
            .setPositiveButton(getString(R.string.ok)){_,_ ->
            }

    }

    fun resetForm(listET: ArrayList<EditText>, buttonString: String){
        var message = ""
        for(item in listET){
            message += "\n" + item.text.toString()
        }

        message += buttonString

        AlertDialog.Builder(requireContext())
            .setTitle(getString(R.string.invalidForm))
            .setMessage(message)
            .setPositiveButton(getString(R.string.ok)){_,_ ->
            }

        val f1Driver = F1Driver(
            binding.editFirstName.getContent(), binding.editLastName.getContent(), binding.editAge.getContent().toInt(), buttonString, binding.editTeam.getContent(), binding.editNationality.getContent(),
            binding.favoriteTrackSpinner.selectedItem.toString(), binding.editTeammate.getContent(), binding.editEngine.getContent()
        )

        viewModel.f1DriverAdd(f1Driver)

        FormWrapper().clearText(listOfElements)
        FormWrapper().clearRadioButton(binding.genderRadioGroup)
    }

}

class FocusAndValidate{
    fun editTextFocusListener(editText: EditText, context: Context){
        editText.setOnFocusChangeListener{_, focused ->
            if(!focused){
                editText.error = validateLength(editText, context)
            }

        }
    }

    fun validateLength(editText: EditText, context: Context): String?{
        val validText = editText.text.toString()
        if(validText.length < 1 || validText.length > 20){
            return context.getString(R.string.lengthMessage)
        }

        return null
    }

    fun numberFocusListener(editText: EditText, context: Context){
        editText.setOnFocusChangeListener{_, focused ->
            if(!focused){
                editText.error = validateNumber(editText, context)
            }

        }
    }

    fun validateNumber(editText: EditText, context: Context):String?{
        if(editText.text.toString() != "") {
            val validNumber = editText.text.toString().toInt()
            if (validNumber < 18 || validNumber > 50) {
                return context.getString(R.string.numberAgeMessage)
            }
        }else if(editText.text.toString() == "") {
            return context.getString(R.string.numberAgeMessage)
        }

        return null

    }


}



class FormWrapper {



    fun clearText (listOfElements: ArrayList<EditText>){
        for(element in listOfElements){
            element.text.clear()
        }
    }

    fun clearRadioButton(radioGroup: RadioGroup){
        radioGroup.clearCheck()
    }

    fun clearSpinner(spinner: Spinner){
        spinner.setSelection(0)
    }

}



