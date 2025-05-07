package com.ideasapp.lovetimecapsule.domain

import io.reactivex.rxjava3.core.Single

class ListCapsuleUseCase(private val repository: Repository) {
//    The {@code Single} class implements the Reactive Pattern for a single value response.
//    * <p>
//    * {@code Single} behaves similarly to {@link Observable} except that it can only emit either a single successful
//    * value or an error (there is no {@code onComplete} notification as there is for an {@code Observable}).
//    * <p>
    operator fun invoke() :Single<List<Capsule>> {
        return repository.listCapsule()
    }
}