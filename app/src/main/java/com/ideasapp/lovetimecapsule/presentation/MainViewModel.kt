package com.ideasapp.lovetimecapsule.presentation

import androidx.lifecycle.ViewModel
import com.ideasapp.lovetimecapsule.domain.AddCapsuleUseCase
import com.ideasapp.lovetimecapsule.domain.ListCapsuleUseCase
import com.ideasapp.lovetimecapsule.domain.ShowCapsuleUseCase

class MainViewModel: ViewModel() {
    val repository = com.ideasapp.lovetimecapsule.data.RepositoryImpl
    val addCapsuleUseCase = AddCapsuleUseCase(repository)
    val listCapsuleUseCase = ListCapsuleUseCase(repository)
    val showCapsuleUseCase = ShowCapsuleUseCase(repository)

}