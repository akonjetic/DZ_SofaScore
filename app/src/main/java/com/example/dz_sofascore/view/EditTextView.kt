package com.example.dz_sofascore.view

import android.content.Context
import android.text.InputType
import android.util.AttributeSet
import android.view.InputDevice
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.FrameLayout
import com.example.dz_sofascore.R
import com.example.dz_sofascore.databinding.EditTextLayoutBinding

const val STRING = 0
const val INT = 1
class EditTextView (context: Context, attr: AttributeSet): FrameLayout(context, attr) {

    private val binding: EditTextLayoutBinding
    private val dataType: Int

    init{
        binding = EditTextLayoutBinding.inflate(LayoutInflater.from(context))
        addView(binding.root)

        context.theme.obtainStyledAttributes(
            attr,
            R.styleable.EditTextView, 0, 0
        ).apply {
            try {
                getString(R.styleable.EditTextView_textViewText)?.let {
                    binding.driverFirstNameTV.text = it
                }
                getString(R.styleable.EditTextView_editTextHint)?.let {
                    binding.driverFirstName.hint = it
                }
                dataType = getInt(R.styleable.EditTextView_dataType, 0)
                binding.driverFirstName.inputType =
                    when (dataType) {
                        STRING -> InputType.TYPE_TEXT_VARIATION_PERSON_NAME
                        INT -> InputType.TYPE_CLASS_NUMBER
                        else -> InputType.TYPE_TEXT_VARIATION_PERSON_NAME
                    }
            }finally{
                recycle()
            }
        }
    }

    fun getContent(): String{
        return binding.driverFirstName.text.toString()
    }


    fun getEditTextContent(): EditText{
        return binding.driverFirstName
    }

    fun clearContent(){
        binding.driverFirstName.text.clear()
    }
}