package com.example.babytracker.ui.fragment.home

import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.example.babytracker.R
import com.example.babytracker.databinding.FragmentHomeBinding
import com.example.babytracker.ui.viewmodel.HomeViewModel
import com.example.babytracker.ui.viewmodel.LoginInformationViewModel
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewmodel:HomeViewModel
    var photo: ByteArray? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewmodel = ViewModelProvider(this)[HomeViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewmodel.viewModelScope.launch {

            viewmodel.getData().forEach{

                binding.babyName.text=it.babyFullName
                val dateString = it.birthDate
                val formatter = DateTimeFormatter.ofPattern("M/d/yyyy")
                val date = LocalDate.parse(dateString, formatter)
                var now = LocalDate.now()
                val months = ChronoUnit.MONTHS.between(date, now)
                val days = ChronoUnit.DAYS.between(date.plusMonths(months), now)

                binding.babyAge.text="$months Months $days Days"
                photo=it.image
                var bitmap = photo?.let { it1 -> BitmapFactory.decodeByteArray(photo, 0, it1.size)
                }
                binding.babyImage.setImageBitmap(bitmap)

            }
        }
        binding.editButton.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_editFragment)
        }
        binding.feedingButton.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_feedingFragment)
        }
        binding.diaperButton.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_diaperChangeFragment)
        }
        binding.sleepButton.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_sleepFragment)
        }
        binding.calenderButton.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_calenderFragment)
        }
        binding.settingButton.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_settingsFragment)
        }
    }

}