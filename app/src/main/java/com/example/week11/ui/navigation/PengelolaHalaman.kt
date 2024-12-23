package com.example.week11.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.week11.ui.view.dosen.DestinasiInsertDosen
import com.example.week11.ui.view.dosen.DetailDosenView
import com.example.week11.ui.view.dosen.HomeDosenView
import com.example.week11.ui.view.dosen.InsertDosenView
import com.example.week11.ui.view.matakuliah.DestinasiInsertMk
import com.example.week11.ui.view.matakuliah.DetailMatakuliahView
import com.example.week11.ui.view.matakuliah.HomeMatakuliahView
import com.example.week11.ui.view.matakuliah.InsertMatakuliahView
import com.example.week11.ui.view.matakuliah.UpdateMatakuliahView

@Composable
fun PengelolaHalaman(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
){
    NavHost(
        navController = navController,
        startDestination = DestinasiHomeDosen.route,
        modifier = modifier
    ) {
        composable(
            route = DestinasiHomeDosen.route
        ) {
            HomeDosenView(
                onDetailClick = { nidn ->
                    navController.navigate("${DestinasiDetailDosen.route}/$nidn")
                    println(
                        "Pengelola Halaman: NIDN = $nidn"
                    )
                },
                onAddDosen = {
                    navController.navigate(DestinasiInsertDosen.route)
                },
                modifier = modifier
            )
        }
        composable(
            route = DestinasiHomeMk.route
        ) {
            HomeMatakuliahView(
                onDetailClick = { kodeMk ->
                    navController.navigate("${DestinasiDetailMk.route}/$kodeMk")
                    println(
                        "Pengelola Halaman: kodeMk = $kodeMk"
                    )
                },
                onAddMatakuliah = {
                    navController.navigate(DestinasiInsertMk.route)
                },
                modifier = modifier
            )
        }
        composable(
            route = DestinasiInsertMk.route
        ){
            InsertMatakuliahView(
                onBack = {
                    navController.popBackStack()
                },
                onNavigate = {
                    navController.popBackStack()
                },
                modifier = modifier,
            )
        }
        composable(
            route = DestinasiInsertDosen.route
        ){
            InsertDosenView(
                onBack = {
                    navController.popBackStack()
                },
                onNavigate = {
                    navController.popBackStack()
                },
                modifier = modifier,
            )
        }
        composable(
            DestinasiDetailDosen.routesWithArg,
            arguments = listOf(
                navArgument(DestinasiDetailDosen.NIDN){
                    type = NavType.StringType
                }
            )
        ){
            val nidn = it.arguments?.getString(DestinasiDetailDosen.NIDN)
            nidn?.let { nidn ->
                DetailDosenView(
                    onBack = {
                        navController.popBackStack()
                    },
                )
            }
        }
        composable(
            DestinasiDetailMk.routesWithArg,
            arguments = listOf(
                navArgument(DestinasiDetailMk.KODEMK){
                    type = NavType.StringType
                }
            )
        ){
            val kodeMk = it.arguments?.getString(DestinasiDetailMk.KODEMK)
            kodeMk?.let { kodeMk ->
                DetailMatakuliahView(
                    onBack = {
                        navController.popBackStack()
                    },
                    onEditClick = {
                        navController.navigate("${DestinasiUpdateMk.route}/$it")
                    },
                    modifier = modifier,
                    onDeleteClick = {
                        navController.popBackStack()
                    }
                )
            }
        }
        composable(
            DestinasiUpdateMk.routesWithArg,
            arguments = listOf(
                navArgument(DestinasiUpdateMk.KODEMK){
                    type = NavType.StringType
                }
            )
        ){
            UpdateMatakuliahView(
                onBack = {
                    navController.popBackStack()
                },
                onNavigate = {
                    navController.popBackStack()
                },
                modifier = modifier,
            )
        }
    }
}