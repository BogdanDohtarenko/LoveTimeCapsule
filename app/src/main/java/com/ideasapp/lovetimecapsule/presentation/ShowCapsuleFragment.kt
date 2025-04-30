package com.ideasapp.lovetimecapsule.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.ideasapp.lovetimecapsule.databinding.ShowCapsuleFragmentBinding

class ShowCapsuleFragment: Fragment() {

    private var _binding: ShowCapsuleFragmentBinding? = null
    private val binding:ShowCapsuleFragmentBinding
        get() = _binding ?: throw RuntimeException("FragmentChooseLevelBinding don't allow here")

    private val viewModel by lazy {
        ViewModelProvider(requireActivity())[MainViewModel::class.java]
    }

    override fun onCreate(savedInstanceState:Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("Navigation", "ShowCapsuleFragment")
    }

    override fun onCreateView(
        inflater:LayoutInflater,
        container:ViewGroup?,
        savedInstanceState:Bundle?
    ):View {
        _binding = ShowCapsuleFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance(): ShowCapsuleFragment {
            return ShowCapsuleFragment()
        }
    }
}