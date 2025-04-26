package com.ideasapp.lovetimecapsule.domain

class ListCapsuleUseCase(private val repository: Repository) {
    operator fun invoke() : List<Capsule> {
        return repository.listCapsule()
    }
}