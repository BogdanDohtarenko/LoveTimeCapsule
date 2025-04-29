package com.ideasapp.lovetimecapsule.presentation

import android.os.Bundle
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
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    override fun onCreate(savedInstanceState:Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater:LayoutInflater,container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.list_capsule_fragment, container, false)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val adapter = CapsuleListAdapter(viewModel.getSampleData()) //TODO
        recyclerView.adapter = adapter

        _binding = ListCapsuleFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance(): ListCapsuleFragment {
            return ListCapsuleFragment()
        }
    }

}