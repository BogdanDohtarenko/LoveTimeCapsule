package com.ideasapp.lovetimecapsule.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ideasapp.lovetimecapsule.R

class CapsuleListAdapter(private val items: List<String>) :
    RecyclerView.Adapter<CapsuleListAdapter.ViewHolder>() {

    class ViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView) {
        val textView:TextView = itemView.findViewById(R.id.textView)
    }

    override fun onCreateViewHolder(parent:ViewGroup,viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = items[position]
    }

    override fun getItemCount(): Int = items.size
}