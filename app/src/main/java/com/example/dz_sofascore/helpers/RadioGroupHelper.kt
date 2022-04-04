package com.example.dz_sofascore.helpers

import android.content.Context
import android.widget.RadioGroup
import com.example.dz_sofascore.R

class RadioGroupHelper {
    fun RadioGroup.validateRadioGroup(context: Context): String? {
        if (this.checkedRadioButtonId == -1) {
            return context.getString(R.string.radioButtonMessage)
        }

        return null
    }
}
