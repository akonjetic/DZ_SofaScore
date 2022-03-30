package com.example.dz_sofascore.helpers

import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Spinner

class FormClearHelper {
    fun clearText(listOfElements: ArrayList<EditText>) {
        for (element in listOfElements) {
            element.text.clear()
        }
    }

    fun clearRadioButton(radioGroup: RadioGroup) {
        radioGroup.clearCheck()
    }

    fun clearSpinner(spinner: Spinner) {
        spinner.setSelection(0)
    }
}