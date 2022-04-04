package com.example.dz_sofascore.fragment

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import androidx.fragment.app.Fragment
import com.example.dz_sofascore.R
import com.example.dz_sofascore.databinding.FragmentSettingsBinding
import com.example.dz_sofascore.helpers.StatusBarHelper
import java.util.*

private lateinit var binding: FragmentSettingsBinding
private lateinit var locale: Locale

class SettingsFragment : Fragment() {

    @SuppressLint("ResourceType")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        with(StatusBarHelper()) {
            activity?.setStatusBarColor(Color.parseColor(getString(R.color.teal_200)))
        }

        binding = FragmentSettingsBinding.inflate(LayoutInflater.from(context))

        binding.languageRadioGroup.setOnCheckedChangeListener(
            RadioGroup.OnCheckedChangeListener { group, checkedId ->
                if (binding.eng.isChecked) {
                    locale = Locale("en")
                    setLocale()
                }
                if (binding.hrv.isChecked) {
                    locale = Locale("hr")
                    setLocale()
                }
            }
        )

        return binding.root
    }

    @SuppressLint("ResourceType")
    fun setLocale() {
        binding.languageRadioGroup.check(1)
        Locale.setDefault(locale)
        val configuration = Configuration()
        configuration.locale = locale
        context?.getResources()?.updateConfiguration(
            configuration,
            context?.getResources()?.getDisplayMetrics()
        )
        activity?.recreate()
    }
}
