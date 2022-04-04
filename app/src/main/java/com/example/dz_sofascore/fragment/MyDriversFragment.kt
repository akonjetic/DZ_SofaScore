package com.example.dz_sofascore.fragment

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dz_sofascore.MainActivityViewModel
import com.example.dz_sofascore.R
import com.example.dz_sofascore.adapter.MyDriversAdapter
import com.example.dz_sofascore.databinding.FragmentMyDriversBinding
import com.example.dz_sofascore.helpers.StatusBarHelper
import com.example.dz_sofascore.model.F1Driver

class MyDriversFragment : Fragment() {

    private val viewModel: MainActivityViewModel by activityViewModels()
    private var _binding: FragmentMyDriversBinding? = null
    private val binding get() = _binding!!

    @SuppressLint("ResourceType")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        with(StatusBarHelper()) {
            activity?.setStatusBarColor(Color.parseColor(getString(R.color.teal_200)))
        }

        _binding = FragmentMyDriversBinding.inflate(inflater, container, false)
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        viewModel.listOfDrivers.observe(viewLifecycleOwner) {
            val adapter = MyDriversAdapter(requireContext(), it)
            binding.recyclerView.adapter = adapter
        }
        return binding.root
    }

    private fun F1Driver.getInfo() = "$firstName $lastName, $age, $gender, $team, $favoriteTrack"
}
