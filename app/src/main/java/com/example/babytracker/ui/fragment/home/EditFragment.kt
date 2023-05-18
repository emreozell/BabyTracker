package com.example.babytracker.ui.fragment.home

import android.annotation.SuppressLint
import android.app.Activity
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.example.babytracker.R
import com.example.babytracker.data.entity.Baby
import com.example.babytracker.databinding.FragmentEditBinding
import com.example.babytracker.databinding.FragmentLoginInformationBinding
import com.example.babytracker.room.BabyDataBase
import com.example.babytracker.ui.viewmodel.HomeViewModel
import com.example.babytracker.ui.viewmodel.LoginInformationViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


class EditFragment : Fragment() {


    private lateinit var _binding: FragmentEditBinding
    private val binding get() = _binding!!
    private lateinit var babyDataBase: BabyDataBase
    private lateinit var viewmodel: LoginInformationViewModel
    private lateinit var gender:String
    private lateinit var viewmodel2: HomeViewModel
    var selectedBitmap: Bitmap? = null
    var selectedPostImage: Uri? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewmodel = ViewModelProvider(this)[LoginInformationViewModel::class.java]
        viewmodel2 = ViewModelProvider(this)[HomeViewModel::class.java]
        gender=""



    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEditBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.saveButton.isEnabled=false

        viewmodel2.viewModelScope.launch {

            viewmodel2.getData().forEach{
                binding.babyName.setText(it.babyFullName)
                binding.dueDate.setText(it.dueDate)
                binding.birthDate.setText(it.birthDate)
                binding.timeOfBirth.setText(it.timeofBirth)
                var photo=it.image
                var bitmap = photo?.let { it1 -> BitmapFactory.decodeByteArray(photo, 0, it1.size)
                }
                gender=it.babyGender
                selectedBitmap=bitmap
                binding.babyPhoto.setImageBitmap(bitmap)
                if (it.babyGender=="male"){
                    binding.babyMale.setImageResource(R.drawable.baby__male_selected)
                    binding.babyFemale.setImageResource(R.drawable.baby__famale_unslected)
                }else{
                    binding.babyFemale.setImageResource(R.drawable.baby__female_selected)
                    binding.babyMale.setImageResource(R.drawable.baby__male_unslected)
                }


            }}
        binding.dueDate.addTextChangedListener {
            control()
        }
        binding.birthDate.addTextChangedListener {
            control()
        }
        binding.timeOfBirth.addTextChangedListener {
            control()
        }
        binding.babyName.addTextChangedListener {
            control()
        }
        binding.babyMale.setOnClickListener {
            gender="male"
            binding.babyMale.setImageResource(R.drawable.baby__male_selected)
            binding.babyFemale.setImageResource(R.drawable.baby__famale_unslected)


        }
        binding.babyFemale.setOnClickListener {
            gender="female"
            binding.babyFemale.setImageResource(R.drawable.baby__female_selected)
            binding.babyMale.setImageResource(R.drawable.baby__male_unslected)

        }
        binding.babyPhoto.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intent, 1)
        }
        binding.birthDate.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)
            val datePickerDialog = DatePickerDialog(
                requireContext(),
                { _, year, month, dayOfMonth ->
                    binding.birthDate.setText("${month+1}/$dayOfMonth/$year")
                },
                year,
                month,
                day
            )
            datePickerDialog.show()
        }
        binding.timeOfBirth.setOnClickListener {
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
                    binding.timeOfBirth.setText(timeText)
                },
                hour,
                minute,
                false
            )
            timePickerDialog.show()
        }
        binding.dueDate.setOnClickListener {
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
                    binding.dueDate.setText(timeText)
                },
                hour,
                minute,
                false
            )
            timePickerDialog.show()
        }
        binding.backHomeButton.setOnClickListener {
            findNavController().navigate(R.id.action_editFragment_to_homeFragment)
        }
        binding.saveButton.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {

                val stream = ByteArrayOutputStream()
                selectedBitmap?.compress(Bitmap.CompressFormat.PNG, 100, stream)
                val imageByteArray = stream.toByteArray()


                if (binding.babyName.text.toString().isNotEmpty() &&
                    binding.timeOfBirth.text.toString().isNotEmpty() &&
                    binding.dueDate.text.toString().isNotEmpty() &&
                    binding.birthDate.text.toString().isNotEmpty() &&
                    gender.isNotEmpty()

                ) {   if(selectedBitmap != null){


                    saveData(
                        Baby(0,
                        binding.babyName.text.toString(),
                        binding.birthDate.text.toString(),
                        binding.timeOfBirth.text.toString(),
                        binding.dueDate.text.toString(),
                        gender,
                        imageByteArray
                    )
                    )
                    activity?.runOnUiThread{

                        findNavController().navigate(R.id.action_editFragment_to_homeFragment)
                    }
                }
                else{
                    activity?.runOnUiThread {
                        Toast.makeText(requireContext(), "Please select a picture!!", Toast.LENGTH_LONG)
                            .show()
                    }
                }


                }else {
                    activity?.runOnUiThread {
                        Toast.makeText(requireContext(), "Please fill in information!!", Toast.LENGTH_LONG)
                            .show()
                    }
                }
            }

        }

    }

    private fun control() {
        if (binding.babyName.text.toString()!="" &&
            binding.timeOfBirth.text.toString()!=""&&
            binding.birthDate.text.toString()!=""&&
            binding.dueDate.text.toString()!=""
        ){
            binding.saveButton.setBackgroundResource(R.drawable.rectangle_4009)
            binding.saveButton.isEnabled=true
        }else{
            binding.saveButton.isEnabled=false
        }
    }

    @SuppressLint("SuspiciousIndentation")
    fun saveData(baby: Baby) {
        viewmodel.insertBaby(baby)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 1 && resultCode == Activity.RESULT_OK && data != null) {
            val imageUri = data.data
            selectedPostImage = data.data
            val source = ImageDecoder.createSource(
                requireContext().contentResolver,
                selectedPostImage!!

            )
            selectedBitmap = ImageDecoder.decodeBitmap(source)
            binding.babyPhoto.setImageURI(imageUri)
        }else{
            selectedBitmap = MediaStore.Images.Media.getBitmap(
                requireContext().contentResolver,
                selectedPostImage
            )
            binding.babyPhoto.setImageBitmap(selectedBitmap)
        }
    }

}