package com.example.week11.ui.view.matakuliah

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.week11.R
import com.example.week11.data.DosenDropDown
import com.example.week11.data.entity.Dosen
import com.example.week11.ui.costumwidget.CstTopAppBar
import com.example.week11.ui.navigation.AlamatNavigasi
import com.example.week11.ui.viewModel.matakuliahViewModel.MatakuliahEvent
import com.example.week11.ui.viewModel.matakuliahViewModel.FormErrorState
import com.example.week11.ui.viewModel.matakuliahViewModel.MatakuliahUIState
import com.example.week11.ui.viewModel.matakuliahViewModel.MatakuliahViewModel
import com.example.week11.ui.viewModel.matakuliahViewModel.PenyediaMatakuliahViewModel
import kotlinx.coroutines.launch

object DestinasiInsertMk : AlamatNavigasi {
    override val route: String = "insert_mk"
}

@Composable
fun InsertMatakuliahView(
    onBack: () -> Unit,
    onNavigate: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: MatakuliahViewModel = viewModel(factory = PenyediaMatakuliahViewModel.Factory)
){
    val uiState = viewModel.uiState
    val snackbarHostState =  remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    LaunchedEffect(uiState.snackBarMessage) {
        uiState.snackBarMessage?.let { message ->
            coroutineScope.launch {
                snackbarHostState.showSnackbar(message)
                viewModel.resetSnackBarMessage()
            }
        }
    }
    Scaffold (
        modifier = modifier,
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
    )
    { padding ->
        Image(
            painter = painterResource(id = R.drawable.pp),
            contentDescription = "Background",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            CstTopAppBar(
                onBack = onBack,
                showBackButton = true,
                judul = "Tambah Matakuliah"
            )
            InsertBodyMatakuliah(
                uiState = uiState,
                onValueChange = { updateEvent ->
                    viewModel.updateState(updateEvent)
                },
                onClick = {
                    coroutineScope.launch {
                        viewModel.saveData()
                    }
                    onNavigate()
                }
            )
        }
    }
}

@Composable
fun InsertBodyMatakuliah(
    modifier: Modifier = Modifier,
    onValueChange: (MatakuliahEvent) -> Unit,
    uiState: MatakuliahUIState,
    onClick: () -> Unit
){
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        FormMatakuliah(
            matakuliahEvent = uiState.matakuliahEvent,
            onValueChange = onValueChange,
            errorState = uiState.isEntryValid,
            modifier = Modifier
                .fillMaxWidth()
        )
        Button(
            onClick = onClick,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text("Simpan")
        }
    }
}

@Composable
fun FormMatakuliah(
    matakuliahEvent: MatakuliahEvent = MatakuliahEvent(),
    onValueChange: (MatakuliahEvent) -> Unit,
    errorState: FormErrorState = FormErrorState(),
    dosenList: List<Dosen> = emptyList(),
    modifier: Modifier = Modifier
){
    val jenisMk = listOf("Wajib", "Peminatan")
    var chosenDropdown by remember { mutableStateOf("") }
    val expanded = remember { mutableStateOf(false) }
    val selectedDosen = remember { mutableStateOf(matakuliahEvent.dosenPengampu)}

    Column (
        modifier = modifier.fillMaxWidth()
    ){
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = matakuliahEvent.kodeMk,
            onValueChange = {
                onValueChange(matakuliahEvent.copy(kodeMk = it))
            },
            label = { Text("Kode Matakuliah") },
            isError = errorState.kodeMk != null,
            placeholder = { Text("Masukkan kode matakuliah") },
        )
        Text(
            text = errorState.kodeMk ?: "",
            color = Color.Red
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = matakuliahEvent.namaMk,
            onValueChange = {
                onValueChange(matakuliahEvent.copy(namaMk = it))
            },
            label = { Text("Nama Matakuliah") },
            isError = errorState.namaMk != null,
            placeholder = { Text("Masukkan nama matakuliah") },
        )
        Text(
            text = errorState.namaMk ?: "",
            color = Color.Red
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = matakuliahEvent.sks,
            onValueChange = {
                onValueChange(matakuliahEvent.copy(sks = it))
            },
            label = { Text("SKS") },
            isError = errorState.sks != null,
            placeholder = { Text("Masukkan SKS") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Text(
            text = errorState.sks ?: "",
            color = Color.Red
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = matakuliahEvent.semester,
            onValueChange = {
                onValueChange(matakuliahEvent.copy(semester = it))
            },
            label = { Text("Semester") },
            isError = errorState.semester != null,
            placeholder = { Text("Masukkan Semester") },
        )
        Text(
            text = errorState.semester ?: "",
            color = Color.Red
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Jenis Matakuliah")
        Row (
            modifier = Modifier.fillMaxWidth()
        ){
            jenisMk.forEach { jenisMk ->
                Row (
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ){
                    RadioButton(
                        selected = matakuliahEvent.jenisMk == jenisMk,
                        onClick = {
                            onValueChange(matakuliahEvent.copy(jenisMk = jenisMk))
                        },
                    )
                    Text(text = jenisMk)
                }
            }
        }
        DynamicSelectTextField(
            selectedValue = chosenDropdown,
            options = DosenDropDown.option.map { it.nama },
            label = "Dosen Pengampu",
            onValueChangedEvent = {
                chosenDropdown = it
                onValueChange(matakuliahEvent.copy(dosenPengampu = it))
            },
            )
        Text(
            text = errorState.dosenPengampu ?: "",
            color = Color.Red
        )
    }
}