package com.ideasapp.lovetimecapsule.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Capsules")
data class CapsuleDbModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val scheduledTime: Long,
    val writingTime: Long,
    val text: String
) {}