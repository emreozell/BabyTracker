package com.example.babytracker.ui.fragment.home

import android.app.TimePickerDialog
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.babytracker.R
import com.example.babytracker.data.entity.CalenderItem
import com.example.babytracker.databinding.FragmentDiaperChangeBinding
import com.example.babytracker.databinding.FragmentFeedingBinding
import com.example.babytracker.ui.viewmodel.CalenderViewModel
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Locale


class DiaperChangeFragment : Fragment() {
    private lateinit var binding: FragmentDiaperChangeBinding
    private lateinit var viewmodel: CalenderViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewmodel = ViewModelProvider(this)[CalenderViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDiaperChangeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.dirtyButton.setOnClickListener {
            binding.dirtyButton.isSelected = true
            binding.diryText.setTextColor(Color.parseColor("#4625C3"))

            binding.dryButton.isSelected = false
            binding.dryText.setTextColor(Color.parseColor("#C2C2C2"))

            binding.mixedButton.isSelected = false
            binding.mixedTxt.setTextColor(Color.parseColor("#C2C2C2"))

            binding.wetButton.isSelected = false
            binding.wetText.setTextColor(Color.parseColor("#C2C2C2"))
        }

        binding.dryButton.setOnClickListener {
            binding.dirtyButton.isSelected = false
            binding.diryText.setTextColor(Color.parseColor("#C2C2C2"))

            binding.dryButton.isSelected = true
            binding.dryText.setTextColor(Color.parseColor("#4625C3"))

            binding.mixedButton.isSelected = false
            binding.mixedTxt.setTextColor(Color.parseColor("#C2C2C2"))

            binding.wetButton.isSelected = false
            binding.wetText.setTextColor(Color.parseColor("#C2C2C2"))
        }

        binding.mixedButton.setOnClickListener {
            binding.dirtyButton.isSelected = false
            binding.diryText.setTextColor(Color.parseColor("#C2C2C2"))

            binding.dryButton.isSelected = false
            binding.dryText.setTextColor(Color.parseColor("#C2C2C2"))

            binding.mixedButton.isSelected = true
            binding.mixedTxt.setTextColor(Color.parseColor("#4625C3"))

            binding.wetButton.isSelected = false
            binding.wetText.setTextColor(Color.parseColor("#C2C2C2"))
        }

        binding.wetButton.setOnClickListener {
            binding.dirtyButton.isSelected = false
            binding.diryText.setTextColor(Color.parseColor("#C2C2C2"))

            binding.dryButton.isSelected = false
            binding.dryText.setTextColor(Color.parseColor("#C2C2C2"))

            binding.mixedButton.isSelected = false
            binding.mixedTxt.setTextColor(Color.parseColor("#C2C2C2"))

            binding.wetButton.isSelected = true
            binding.wetText.setTextColor(Color.parseColor("#4625C3"))
        }

        binding.timeFeeding.setOnClickListener {
            val calendar = Calendar.getInstance()
            val hour = calendar.get(Calendar.HOUR_OF_DAY)
            val minute = calendar.get(Calendar.MINUTE)
            val timePickerDialog = TimePickerDialog(
                requireContext(),
                { _, hourOfDay, minute ->
                    val time = "$hourOfDay:$minute"
                    val sdf = SimpleDateFormat("HH:mm", Locale.getDefault())
                    val date = sdf.parse(time)
                    val timeText = SimpleDateFormat("hh:mm aa", Locale.getDefault()).format(date)
                    binding.timeFeeding.text=timeText
                },
                hour,
                minute,
                false
            )
            timePickerDialog.show()
        }
        binding.backButtonDiaper.setOnClickListener {
            findNavController().navigate(R.id.action_diaperChangeFragment_to_homeFragment)
        }
        binding.saveFeeding.setOnClickListener {


            if (binding.noteFeeding.text.toString().isNotEmpty() &&
                binding.timeFeeding.text.toString().isNotEmpty()
            ){
                activity?.runOnUiThread {
                    val currentDate = LocalDate.now()
                    val formatter = DateTimeFormatter.ofPattern("E d MMM")
                    val formattedDate = currentDate.format(formatter)
                    saveData(
                        CalenderItem(0,
                            "Diaper Change",
                            formattedDate ,
                            binding.timeFeeding.text.toString(),
                        )
                    )
                    findNavController().navigate(R.id.action_diaperChangeFragment_to_homeFragment)
                }


            }else{

                activity?.runOnUiThread {
                    Toast.makeText(requireContext(), "Please fill in information!!!!", Toast.LENGTH_LONG)
                        .show()
                }
            }



        }

    }
    private fun saveData(calenderItem: CalenderItem) {
        viewmodel.insertCalender(calenderItem)
    }

}