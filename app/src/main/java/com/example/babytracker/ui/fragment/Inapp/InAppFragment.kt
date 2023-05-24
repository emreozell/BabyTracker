package com.example.babytracker.ui.fragment.Inapp

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.example.babytracker.R
import com.example.babytracker.databinding.FragmentInAppBinding


class InAppFragment : Fragment() {
    private lateinit var _binding: FragmentInAppBinding
    private val binding get() = _binding!!
    lateinit var deger:String

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)



    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentInAppBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.cancleButton.setOnClickListener {
            val sharedPreferences = requireActivity().getPreferences(Context.MODE_PRIVATE)
            val deger22 = sharedPreferences.getString("anahtar", "false")

            if (deger22=="true"){
                findNavController().navigate(R.id.action_inAppFragment_to_homeFragment)
            }else{
                findNavController().navigate(R.id.action_inAppFragment_to_loginInformationFragment)
            }
        }
        binding.imageView17.setOnClickListener {
            binding.cancleButton.isVisible=true
            deger="true"
            binding.imageView17.setImageResource(R.drawable.rectangle_4006)
            binding.imageView18.setImageResource(R.drawable.rectangle_4006_1)
            binding.imageView19.setImageResource(R.drawable.rectangle_4006_1)
        }
        binding.imageView18.setOnClickListener {
            binding.cancleButton.isVisible=true
            deger="true"
            binding.imageView17.setImageResource(R.drawable.rectangle_4006_1)
            binding.imageView18.setImageResource(R.drawable.rectangle_4006)
            binding.imageView19.setImageResource(R.drawable.rectangle_4006_1)

        }
        binding.imageView19.setOnClickListener {
            binding.cancleButton.isVisible=true
            deger="true"
            binding.imageView17.setImageResource(R.drawable.rectangle_4006_1)
            binding.imageView18.setImageResource(R.drawable.rectangle_4006_1)
            binding.imageView19.setImageResource(R.drawable.rectangle_4006)


        }
        binding.imageView26.setOnClickListener {
            if(deger=="true"){
                val sharedPreferences = requireActivity().getPreferences(Context.MODE_PRIVATE)
                val editor = sharedPreferences.edit()
                editor.putString("premium", "true")
                editor.apply()
                findNavController().navigate(R.id.action_inAppFragment_to_homeFragment)
            }else{
                Toast.makeText(requireContext(),"Please Select",Toast.LENGTH_SHORT).show()
            }


        }


    }
}
