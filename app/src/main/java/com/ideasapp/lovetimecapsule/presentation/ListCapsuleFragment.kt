package com.ideasapp.lovetimecapsule.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ideasapp.lovetimecapsule.R
import com.ideasapp.lovetimecapsule.databinding.AddCapsuleFragmentBinding
import com.ideasapp.lovetimecapsule.databinding.ListCapsuleFragmentBinding

class ListCapsuleFragment : Fragment() {

    private var _binding: ListCapsuleFragmentBinding? = null
    private val binding:ListCapsuleFragmentBinding
        get() = _binding ?: throw RuntimeException("FragmentChooseLevelBinding don't allow here")

    private val viewModel by lazy {
        ViewModelProvider(requireActivity())[MainViewModel::class.java]
    }

    override fun onCreate(savedInstanceState:Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("Navigation", "ListCapsuleFragment")
    }

    override fun onCreateView(
        inflater:LayoutInflater,container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ListCapsuleFragmentBinding.inflate(inflater, container, false)
        
        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val adapter = CapsuleListAdapter(viewModel.getSampleData())
        recyclerView.adapter = adapter

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}