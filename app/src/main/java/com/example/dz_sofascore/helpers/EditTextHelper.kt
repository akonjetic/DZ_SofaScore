package com.example.dz_sofascore.helpers

import android.content.Context
import android.widget.EditText
import com.example.dz_sofascore.R

class EditTextHelper {

    fun EditText.editTextFocusListener(context: Context) {
        this.setOnFocusChangeListener { _, focused ->
            if (!focused) {
                this.error = this.validateLength(context)
            }
        }
    }

    fun EditText.validateLength(context: Context): String? {
        val validText = this.text.toString()
        if (validText.isEmpty() || validText.length > 20) {
            return context.getString(R.string.lengthMessage)
        }

        return null
    }

    fun EditText.numberFocusListener(context: Context) {
        this.setOnFocusChangeListener { _, focused ->
            if (!focused) {
                this.error = this.validateNumber(context)
            }
        }
    }

    fun EditText.validateNumber(context: Context): String? {
        if (this.text.toString() != "") {
            val validNumber = this.text.toString().toInt()
            if (validNumber < 18 || validNumber > 50) {
                return context.getString(R.string.numberAgeMessage)
            }
        } else if (this.text.toString() == "") {
            return context.getString(R.string.numberAgeMessage)
        }
        return null
    }
}