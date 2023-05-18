package com.example.babytracker.ui.fragment.calender

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.babytracker.R
import com.example.babytracker.data.entity.CalenderItem
import com.example.babytracker.databinding.FragmentCalenderBinding
import com.example.babytracker.ui.adapter.CalenderAdapter

import com.example.babytracker.ui.viewmodel.CalenderViewModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class CalenderFragment : Fragment() {

    private lateinit var binding: FragmentCalenderBinding
    private lateinit var viewmodel: CalenderViewModel

    val calenderList=ArrayList<CalenderItem>()
    private lateinit var recyclerViewAdapter:CalenderAdapter

    var temp="all"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewmodel = ViewModelProvider(this)[CalenderViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCalenderBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val currentDate = Date()
        val dateFormat = SimpleDateFormat("EEE, MMM d", Locale.getDefault())
        val formattedDate = dateFormat.format(currentDate)
        binding.dateCalander.text = formattedDate
        binding.allText.setOnClickListener {
            calenderList.clear()
            recyclerViewAdapter.notifyDataSetChanged()
            binding.allText.isSelected = true
            binding.diaperText.isSelected = false
            binding.feedingText.isSelected = false
            binding.sleepText.isSelected = false
            temp="all"
            getData()
        }

        binding.diaperText.setOnClickListener {
            calenderList.clear()
            recyclerViewAdapter.notifyDataSetChanged()
            binding.allText.isSelected = false
            binding.diaperText.isSelected = true
            binding.feedingText.isSelected = false
            binding.sleepText.isSelected = false
            temp="Diaper Change"
            getData()
        }

        binding.feedingText.setOnClickListener {
            calenderList.clear()
            recyclerViewAdapter.notifyDataSetChanged()
            binding.allText.isSelected = false
            binding.diaperText.isSelected = false
            binding.feedingText.isSelected = true
            binding.sleepText.isSelected = false
            temp="Feeding"
            getData()
        }

        binding.sleepText.setOnClickListener {
            calenderList.clear()

            binding.allText.isSelected = false
            binding.diaperText.isSelected = false
            binding.feedingText.isSelected = false
            binding.sleepText.isSelected = true
            temp="Sleep"
            getData()
            recyclerViewAdapter.notifyDataSetChanged()
        }
        binding.backButton.setOnClickListener {
            findNavController().navigate(R.id.action_calenderFragment_to_homeFragment)
        }


        binding.recyclerView.layoutManager= LinearLayoutManager(requireContext())
        binding.recyclerView.setHasFixedSize(true)
        recyclerViewAdapter= CalenderAdapter(calenderList)
        binding.recyclerView.adapter=recyclerViewAdapter


    }
    fun getData(){
        viewmodel.viewModelScope.launch {

            viewmodel.getData().forEach {

                if (temp==it.itemName){
                    calenderList.add(it)
                    println(it.toString())
                    recyclerViewAdapter.notifyDataSetChanged()
                }else if(temp=="all"){
                    calenderList.add(it)
                    println("asdasda:"+it.toString())
                    recyclerViewAdapter.notifyDataSetChanged()
                }
            }
        }
    }
}