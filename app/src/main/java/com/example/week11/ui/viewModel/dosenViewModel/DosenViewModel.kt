package com.example.week11.ui.viewModel.dosenViewModel

import androidx.compose.runtime.mutableStateOf
import com.example.week11.data.entity.Dosen
import com.example.week11.repository.RepoDosen

data class DosenUIState(
    val dosenEvent: DosenEvent = DosenEvent(),
    val isEntryValid: FormErrorState = FormErrorState(),
    val snackBarMessage: String? = null,
)
data class FormErrorState(
    val nidn: String? = null,
    val nama: String? = null,
    val jenisKelamin: String? = null,
){
    fun isValid(): Boolean{
        return nidn == null && jenisKelamin == null
    }
}
// data class variabel yang menyimpan data input form
data class DosenEvent(
    val nidn: String = "",
    val nama: String = "",
    val jenisKelamin: String = ""
)
// menyimpan input form ke dalam activity
fun DosenEvent.toDosenEntity(): Dosen = Dosen(
    nidn = nidn,
    nama = nama,
    jenisKelamin = jenisKelamin
)
class DosenViewModel (private val  repoDosen: RepoDosen): ViewModel(){
    var uiState by mutableStateOf(DosenUIState())
    // Memperbarui state berdasarkan input pengguna
    fun updateState(dosenEvent: DosenEvent){
        uiState = uiState.copy(
            dosenEvent = dosenEvent,
        )
    }
    // Validasi data input pengguna
    private fun validateFields(): Boolean {
        val event = uiState.dosenEvent
        val errorState = FormErrorState(
            nidn = if (event.nidn.isNotEmpty()) null else "NIDN tidak boleh kosong",
            nama = if (event.nama.isNotEmpty()) null else "Nama tidak boleh kosong",
            jenisKelamin = if (event.jenisKelamin.isNotEmpty()) null else "Jenis Kelamin tidak boleh kosong",
        )
        uiState = uiState.copy(isEntryValid = errorState)
        return errorState.isValid()
    }
    // Menyimpan data ke repository
    fun saveData(){
        val currentEvent = uiState.dosenEvent
        if (validateFields()) {
            viewModelScope.launch {
                try {
                    repoDosen.insertDosen(currentEvent.toDosenEntity())
                    uiState = uiState.copy(
                        snackBarMessage = "Data berhasil disimpan",
                        dosenEvent = DosenEvent(), // Reset input form
                        isEntryValid = FormErrorState() // Reset error state
                    )
                } catch (e: Exception){
                    uiState = uiState.copy(
                        snackBarMessage = "Data gagal disimpan"
                    )
                }
            }
        } else {
            uiState = uiState.copy(
                snackBarMessage = "Input tidak valid. Periksa kembali data anda."
            )
        }
    }
    //Reset pesan snackBar setelah ditampilkan
    fun resetSnackBarMessage() {
        uiState = uiState.copy(snackBarMessage = null)
    }
}