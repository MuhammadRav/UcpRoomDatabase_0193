package com.example.week11.ui.viewModel.matakuliahViewModel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.week11.data.entity.Matakuliah
import com.example.week11.repository.RepoMatakuliah
import com.example.week11.ui.navigation.DestinasiDetail
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

data class  DetailUiState(
    val detailUiEvent: MatakuliahEvent = MatakuliahEvent(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
){
    val isUiEventEmpty: Boolean
        get() = detailUiEvent == MatakuliahEvent()

    val isUiEventNotEmpty: Boolean
        get() = detailUiEvent != MatakuliahEvent()
}

class DetailMatakuliahViewModel (
    savedStateHandle: SavedStateHandle,
    private val repoMatakuliah: RepoMatakuliah,

    ) : ViewModel() {
    private val _kodeMk: String = checkNotNull(savedStateHandle[DestinasiDetail.KODEMK])

    val detailUiState: StateFlow<DetailUiState> = repoMatakuliah.getMatakuliah(_kodeMk)
        .filterNotNull()
        .map {
            DetailUiState(
                detailUiEvent = it.toDetailUiEvent(),
                isLoading = false,
            )
        }
        .onStart {
            emit(DetailUiState(isLoading = true))
            delay(600)
        }
        .catch {
            emit(
                DetailUiState(
                    isLoading = false,
                    isError = true,
                    errorMessage = it.message ?: "Terjadi kesalahan"
                )
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(2000),
            initialValue = DetailUiState(
                isLoading = true,
            ),
        )
    fun deleteMatakuliah(){
        detailUiState.value.detailUiEvent.toMatakuliahEntity().let{
            viewModelScope.launch {
                repoMatakuliah.deleteMatakuliah(it)
            }
        }
    }
}

fun Matakuliah.toDetailUiEvent(): MatakuliahEvent {
    return MatakuliahEvent(
        kodeMk = kodeMk,
        namaMk = namaMk,
        sks = sks,
        semester = semester,
        jenisMk = jenisMk,
        dosenPengampu = dosenPengampu
    )
}