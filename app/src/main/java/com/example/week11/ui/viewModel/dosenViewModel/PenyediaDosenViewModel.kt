package com.example.week11.ui.viewModel.dosenViewModel

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.week11.KrsApp

object PenyediaDosenViewModel{
    val Factory = viewModelFactory {
        initializer {
            DosenViewModel(
                KrsApp().containerApp.repoDosen
            )
        }
    }
}
fun CreationExtras.krsApp(): KrsApp =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as KrsApp)