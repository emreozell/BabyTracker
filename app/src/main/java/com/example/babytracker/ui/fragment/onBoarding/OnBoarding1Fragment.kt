package com.example.babytracker.ui.fragment.onBoarding

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.babytracker.R
import com.example.babytracker.databinding.FragmentOnBoarding1Binding

class OnBoarding1Fragment : Fragment() {

    private lateinit var _binding:FragmentOnBoarding1Binding
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val sharedPreferences = requireActivity().getPreferences(Context.MODE_PRIVATE)
        val deger22 = sharedPreferences.getString("anahtar", "false")

        if (deger22=="true"){
            findNavController().navigate(R.id.action_onBoarding1Fragment2_to_homeFragment)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOnBoarding1Binding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.imageView6.setOnClickListener {
            findNavController().navigate(R.id.action_onBoarding1Fragment2_to_onBoarding2Fragment)
        }
    }

}