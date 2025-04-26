package com.ideasapp.lovetimecapsule.domain

data class Capsule(
    val scheduledTime: Long,
    val writingTime: Long,
    val text: String
) {}