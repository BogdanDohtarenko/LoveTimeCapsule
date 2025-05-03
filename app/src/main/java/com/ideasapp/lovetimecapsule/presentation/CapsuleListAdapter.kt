package com.ideasapp.lovetimecapsule.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ideasapp.lovetimecapsule.R
import com.ideasapp.lovetimecapsule.domain.Capsule

class CapsuleListAdapter:
    RecyclerView.Adapter<CapsuleListAdapter.ViewHolder>() {

    var items: List<Capsule> = listOf()
        set(value) {
            val callback = DiffUtilCallback(items, value)
            val diffResult = DiffUtil.calculateDiff(callback)
            diffResult.dispatchUpdatesTo(this)
            field = value
        }

    class ViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView) {
        val textView:TextView = itemView.findViewById(R.id.textView)
    }

    override fun onCreateViewHolder(parent:ViewGroup,viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = items[position].scheduledTime.toTimeDateString()
    }

    override fun getItemCount(): Int = items.size
}