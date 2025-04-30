package com.ideasapp.lovetimecapsule.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.ideasapp.lovetimecapsule.databinding.AddCapsuleFragmentBinding

class AddCapsuleFragment: Fragment() {

    private var _binding: AddCapsuleFragmentBinding? = null
    private val binding: AddCapsuleFragmentBinding
        get() = _binding ?: throw RuntimeException("FragmentChooseLevelBinding don't allow here")

    private val viewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance(): AddCapsuleFragment {
            return AddCapsuleFragment()
        }
    }
}