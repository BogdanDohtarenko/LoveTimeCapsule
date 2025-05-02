package com.ideasapp.lovetimecapsule.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.ideasapp.lovetimecapsule.databinding.AddCapsuleFragmentBinding
import com.ideasapp.lovetimecapsule.domain.Capsule
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class AddCapsuleFragment: Fragment() {

    private var _binding: AddCapsuleFragmentBinding? = null
    private val binding: AddCapsuleFragmentBinding
        get() = _binding ?: throw RuntimeException("FragmentChooseLevelBinding don't allow here")

    private val viewModel by lazy {
        ViewModelProvider(requireActivity())[MainViewModel::class.java]
    }

    override fun onCreate(savedInstanceState:Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("Navigation", "AddCapsuleFragment")
    }

    override fun onCreateView(
        inflater:LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ):View {
        _binding = AddCapsuleFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view:View,savedInstanceState:Bundle?) {
        super.onViewCreated(view,savedInstanceState)

        binding.saveButton.setOnClickListener {
            val year =  binding.datePicker.year
            val month =  binding.datePicker.month + 1
            val day =  binding.datePicker.dayOfMonth
            val text = binding.textInput.text.toString()
            val sendingTime = System.currentTimeMillis()

            val scheduledDate = LocalDate.of(year, month, day)
            val scheduledDateLong = scheduledDate.toEpochDay()

            val newCapsule = Capsule(
                writingTime = sendingTime,
                scheduledTime = scheduledDateLong,
                text = text
            )

            Toast.makeText(requireContext(), "capsule have saved to $scheduledDate", Toast.LENGTH_SHORT).show()

            viewModel.saveCapsule(newCapsule)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}