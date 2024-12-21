package com.example.week11.ui.viewModel.matakuliahViewModel

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.week11.KrsApp

object PenyediaMatakuliahViewModel{
    val Factory = viewModelFactory {
        initializer {
            MatakuliahViewModel(
                KrsApp().containerApp.repoMatakuliah
            )
        }
    }
}
fun CreationExtras.krsApp(): KrsApp =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as KrsApp)