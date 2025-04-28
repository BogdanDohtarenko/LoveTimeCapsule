package com.ideasapp.lovetimecapsule.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ideasapp.lovetimecapsule.R

class ListCapsuleFragment : Fragment() {

    override fun onCreateView(
        inflater:LayoutInflater,container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.list_capsule_fragment, container, false)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val adapter = CapsuleListAdapter(getSampleData())
        recyclerView.adapter = adapter

        return view
    }

    // Функция для создания тестовых данных
    private fun getSampleData(): List<String> {
        return listOf("Item 1", "Item 2", "Item 3", "Item 4", "Item 5")
    }
}