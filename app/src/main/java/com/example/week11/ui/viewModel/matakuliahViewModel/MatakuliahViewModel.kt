package com.example.week11.ui.viewModel.matakuliahViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.week11.data.entity.Matakuliah
import com.example.week11.repository.RepoMatakuliah

data class MatakuliahUIState(
    val matakuliahEvent: MatakuliahEvent = MatakuliahEvent(),
    val isEntryValid: FormErrorState = FormErrorState(),
    val snackBarMessage: String? = null,
)
data class FormErrorState(
    val kodeMk: String? = null,
    val namaMk: String? = null,
    val sks: String? = null,
    val semester: String? = null,
    val jenisMk: String? = null,
    val dosenPengampu: String? = null,
){
    fun isValid(): Boolean{
        return kodeMk == null && namaMk == null && sks == null &&
                semester == null && jenisMk == null && dosenPengampu == null
    }
}
// data class variabel yang menyimpan data input form
data class MatakuliahEvent(
    val kodeMk: String = "",
    val namaMk: String = "",
    val sks: String = "",
    val semester: String = "",
    val jenisMk: String = "",
    val dosenPengampu: String = ""
)
// menyimpan input form ke dalam activity
fun MatakuliahEvent.toMatakuliahEntity(): Matakuliah = Matakuliah(
    kodeMk = kodeMk,
    namaMk = namaMk,
    sks = sks,
    semester = semester,
    jenisMk = jenisMk,
    dosenPengampu = dosenPengampu
)
class MatakuliahViewModel (private val  repoMatakuliah: RepoMatakuliah): ViewModel(){
    var uiState by mutableStateOf(MatakuliahUIState())
    // Memperbarui state berdasarkan input pengguna
    fun updateState(matakuliahEvent: MatakuliahEvent){
        uiState = uiState.copy(
            matakuliahEvent = matakuliahEvent,
        )
    }
    // Validasi data input pengguna
    private fun validateFields(): Boolean {
        val event = uiState.matakuliahEvent
        val errorState = FormErrorState(
            kodeMk = if (event.kodeMk.isNotEmpty()) null else "Kode Matakuliah tidak boleh kosong",
            namaMk = if (event.namaMk.isNotEmpty()) null else "Nama Matakuliah tidak boleh kosong",
            sks = if (event.sks.isNotEmpty()) null else "SKS tidak boleh kosong",
            semester = if (event.semester.isNotEmpty()) null else "Semester tidak boleh kosong",
            jenisMk = if (event.jenisMk.isNotEmpty()) null else "Jenis Matakuliah tidak boleh kosong",
            dosenPengampu = if (event.dosenPengampu.isNotEmpty()) null else "Dosen Pengampu tidak boleh kosong",
        )
        uiState = uiState.copy(isEntryValid = errorState)
        return errorState.isValid()
    }
}