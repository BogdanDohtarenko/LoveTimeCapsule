package com.ideasapp.lovetimecapsule.presentation

import androidx.recyclerview.widget.DiffUtil
import com.ideasapp.lovetimecapsule.domain.Capsule

class DiffUtilCallback(
    private val oldList:List<Capsule>,
    private val newList:List<Capsule>
): DiffUtil.Callback() {

    override fun getOldListSize():Int {
        return oldList.size
    }

    override fun getNewListSize():Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition:Int,newItemPosition:Int):Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItemPosition:Int,newItemPosition:Int):Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]
        return oldItem == newItem
    }
}