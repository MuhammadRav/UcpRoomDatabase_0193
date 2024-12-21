package com.example.week11.ui.viewModel

import androidx.lifecycle.ViewModel
import com.example.week11.data.entity.Dosen
import com.example.week11.repository.RepoDosen


data class  HomeUiState(
    val listDosen: List<Dosen> = listOf(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
)

class HomeDosenViewModel (
    private val repoDosen: RepoDosen
) : ViewModel() {

}