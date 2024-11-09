package com.example.chapter_ten

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import com.example.chapter_ten.databinding.FragmentCrimeDetailBinding
import java.util.Date
import java.util.UUID


@SuppressLint("StaticFieldLeak")
private lateinit var binding:FragmentCrimeDetailBinding
private lateinit var crime:Crime

private const val TAG = "CrimeDetailFragment"

class CrimeDetailFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "HELLO")
        crime = Crime (
            id = UUID.randomUUID(),
            title = "",
            date = Date(),
            isSolved = false
        )

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding =
            FragmentCrimeDetailBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            crimeTitle.doOnTextChanged { text, _, _, _ ->
                crime = crime.copy(title = text.toString())
            }

            crimeDate.apply {
                text = crime.date.toString()
                isEnabled = false

            }

            crimeSolved.setOnCheckedChangeListener { _, isChecked ->
                crime = crime.copy(isSolved = isChecked)
            }
        }
    }
}