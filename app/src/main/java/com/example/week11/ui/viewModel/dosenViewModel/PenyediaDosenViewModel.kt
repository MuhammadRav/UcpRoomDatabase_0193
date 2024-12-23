package com.example.week11.ui.viewModel.dosenViewModel

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.week11.KrsApp
import com.example.week11.ui.viewModel.matakuliahViewModel.krsApp

object PenyediaDosenViewModel{
    val Factory = viewModelFactory {
        initializer {
            DosenViewModel(
                krsApp().containerApp.repoDosen
            )
        }
        initializer {
            HomeDosenViewModel(
                krsApp().containerApp.repoDosen
            )
        }
        initializer {
            DetailDosenViewModel(
                createSavedStateHandle(),
                krsApp().containerApp.repoDosen
            )
        }
    }
}
fun CreationExtras.krsApp(): KrsApp =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as KrsApp)