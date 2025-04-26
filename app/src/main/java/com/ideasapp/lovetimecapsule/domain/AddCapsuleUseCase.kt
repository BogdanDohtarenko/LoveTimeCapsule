package com.ideasapp.lovetimecapsule.domain

class AddCapsuleUseCase(private val repository: Repository) {
    operator fun invoke(newCapsule: Capsule) {
        return repository.addCapsule(newCapsule)
    }
}