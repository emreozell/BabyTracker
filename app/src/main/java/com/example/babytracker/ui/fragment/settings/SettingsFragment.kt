package com.example.babytracker.ui.fragment.settings

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.babytracker.R
import com.example.babytracker.databinding.FragmentFeedingBinding
import com.example.babytracker.databinding.FragmentSettingsBinding
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class SettingsFragment : Fragment() {
    private lateinit var binding: FragmentSettingsBinding
    lateinit var deger:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSettingsBinding.inflate(inflater,container,false)
        return binding.root

    }

    @OptIn(DelicateCoroutinesApi::class)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.backButton.setOnClickListener {
            findNavController().navigate(R.id.action_settingsFragment_to_homeFragment)

        }
        binding.imageView10.setOnClickListener {
            findNavController().navigate(R.id.action_settingsFragment_to_inAppFragment)
        }
        val sharedPreferences = requireActivity().getPreferences(Context.MODE_PRIVATE)
        deger = sharedPreferences.getString("premium", "false").toString()

        if (deger=="true"){
            binding.imageView10.isVisible=false
        }
        binding.imageView11.setOnClickListener {
            val appPackageName = "com.example.babytracker.ui.fragment.settings" // Uygulamanın paket adını alın
            try {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$appPackageName")))
            } catch (e: android.content.ActivityNotFoundException) {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=$appPackageName")))
            }
        }
        binding.imageView14.setOnClickListener {
            val url = "https://www.neonapps.co/contact-us" // Yönlendirmek istediğiniz internet sayfasının URL'si
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            startActivity(intent)
        }
        binding.imageView12.setOnClickListener {
            val url = "https://www.neonapps.co/" // Yönlendirmek istediğiniz internet sayfasının URL'si
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            startActivity(intent)
        }
        binding.imageView13.setOnClickListener {
            val url = "https://www.neonapps.co/" // Yönlendirmek istediğiniz internet sayfasının URL'si
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            startActivity(intent)
        }
        val sharedPreferences2 = requireActivity().getPreferences(Context.MODE_PRIVATE)
        val deger22 = sharedPreferences.getString("premium", "false")
        if (deger22=="true"){

            binding.imageView15.isVisible=false
            binding.imageView26.isVisible=false
            binding.textView31.isVisible=false
            binding.imageView32.isVisible=false
        }
        binding.imageView15.setOnClickListener {
            val sharedPreferences = requireActivity().getPreferences(Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putString("premium", "true")
            editor.apply()
            Toast.makeText(requireContext(), "Premium Controling", Toast.LENGTH_SHORT).show()

            lifecycleScope.launch {
                // Ana iş parçacığında çalışacak kod bloğu
                delay(2000)
                findNavController().navigate(R.id.action_settingsFragment_to_homeFragment)
            }
        }

    }

}