package com.ideasapp.lovetimecapsule.domain

class ShowCapsuleUseCase(private val repository:Repository) {
    operator fun invoke(): Capsule {
        return repository.showCapsule()
    }
}