package com.example.week11.ui.viewModel.matakuliahViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.week11.data.entity.Matakuliah
import com.example.week11.repository.RepoMatakuliah
import com.example.week11.ui.navigation.DestinasiUpdateMk
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class UpdateMatakuliahViewModel (
    savedStateHandle: SavedStateHandle,
    private val repoMatakuliah: RepoMatakuliah
): ViewModel(){
    var updateUIState by mutableStateOf(MatakuliahUIState())
        private set

    private val _kodeMk: String = checkNotNull(savedStateHandle[DestinasiUpdateMk.KODEMK])

    init{
        viewModelScope.launch {
            updateUIState = repoMatakuliah.getMatakuliah(_kodeMk)
                .filterNotNull()
                .first()
                .toUiStateMatakuliah()
        }
    }

    fun updateState(matakuliahEvent: MatakuliahEvent){
        updateUIState = updateUIState.copy(
            matakuliahEvent = matakuliahEvent,
        )
    }

    fun validateFields(): Boolean{
        val event = updateUIState.matakuliahEvent
        val errorState = FormErrorState(
            kodeMk = if (event.kodeMk.isNotEmpty()) null else "Kode Matakuliah tidak boleh kosong",
            namaMk = if (event.namaMk.isNotEmpty()) null else "Nama Matakuliah tidak boleh kosong",
            sks = if (event.sks.isNotEmpty()) null else "SKS tidak boleh kosong",
            semester = if (event.semester.isNotEmpty()) null else "Semester tidak boleh kosong",
            jenisMk = if (event.jenisMk.isNotEmpty()) null else "Jenis Matakuliah tidak boleh kosong",
            dosenPengampu = if (event.dosenPengampu.isNotEmpty()) null else "Dosen Pengampu tidak boleh kosong"
        )
        updateUIState = updateUIState.copy(isEntryValid = errorState)
        return errorState.isValid()
    }

    fun UpdateData(){
        val currentEvent = updateUIState.matakuliahEvent

        if (validateFields()) {
            viewModelScope.launch {
                try {
                    repoMatakuliah.updateMatakuliah(currentEvent.toMatakuliahEntity())
                    updateUIState = updateUIState.copy(
                        snackBarMessage = "Data berhasil diupdate",
                        matakuliahEvent = MatakuliahEvent(),
                        isEntryValid = FormErrorState()
                    )
                    println("snackBarMessage diatur: ${updateUIState.
                    snackBarMessage}")
                } catch (e: Exception){
                    updateUIState = updateUIState.copy(
                        snackBarMessage = "Data gagal diupdate"
                    )
                }
            }
        } else{
            updateUIState = updateUIState.copy(
                snackBarMessage = "Data gagal diupdate"
            )
        }
    }

    fun resetSnackBarMessage(){
        updateUIState = updateUIState.copy(snackBarMessage = null)
    }
}

fun Matakuliah.toUiStateMatakuliah(): MatakuliahUIState = MatakuliahUIState(
    matakuliahEvent = this.toDetailUiEvent(),
    )