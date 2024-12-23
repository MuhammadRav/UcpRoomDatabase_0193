package com.example.week11.ui.viewModel.matakuliahViewModel

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.week11.KrsApp

object PenyediaMatakuliahViewModel{
    val Factory = viewModelFactory {
        initializer {
            MatakuliahViewModel(
                krsApp().containerApp.repoMatakuliah
            )
        }
        initializer {
            HomeMatakuliahViewModel(
                krsApp().containerApp.repoMatakuliah
            )
        }
        initializer {
            DetailMatakuliahViewModel(
                createSavedStateHandle(),
                krsApp().containerApp.repoMatakuliah
            )
        }
        initializer {
            UpdateMatakuliahViewModel(
                createSavedStateHandle(),
                krsApp().containerApp.repoMatakuliah
            )
        }
    }
}
fun CreationExtras.krsApp(): KrsApp =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as KrsApp)