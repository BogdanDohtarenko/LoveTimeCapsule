package com.ideasapp.lovetimecapsule.domain

data class Capsule(
    val id: Int = 0,
    val scheduledTime: Long,
    val writingTime: Long,
    val text: String
) {}