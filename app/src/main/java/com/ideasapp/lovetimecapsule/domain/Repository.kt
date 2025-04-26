package com.ideasapp.lovetimecapsule.domain

interface Repository {
    fun addCapsule(newCapsule:Capsule)
    fun listCapsule(): List<Capsule>
    fun showCapsule(): Capsule
}