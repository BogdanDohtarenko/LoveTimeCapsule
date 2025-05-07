package com.ideasapp.lovetimecapsule.domain

import io.reactivex.rxjava3.core.Completable

class AddCapsuleUseCase(private val repository: Repository) {
    operator fun invoke(newCapsule: Capsule): Completable {
        return repository.saveCapsule(newCapsule)
    }
}