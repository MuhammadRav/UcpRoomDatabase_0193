package com.example.week11.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.week11.ui.view.matakuliah.DestinasiInsert
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
        startDestination = DestinasiHome.route,
        modifier = modifier
    ) {
        composable(
            route = DestinasiHome.route
        ) {
            HomeMatakuliahView(
                onDetailClick = { kodeMk ->
                    navController.navigate("${DestinasiDetail.route}/$kodeMk")
                    println(
                        "Pengelola Halaman: kodeMk = $kodeMk"
                    )
                },
                onAddMatakuliah = {
                    navController.navigate(DestinasiInsert.route)
                },
                modifier = modifier
            )
        }
        composable(
            route = DestinasiInsert.route
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
            DestinasiDetail.routesWithArg,
            arguments = listOf(
                navArgument(DestinasiDetail.KODEMK){
                    type = NavType.StringType
                }
            )
        ){
            val kodeMk = it.arguments?.getString(DestinasiDetail.KODEMK)
            kodeMk?.let { kodeMk ->
                DetailMatakuliahView(
                    onBack = {
                        navController.popBackStack()
                    },
                    onEditClick = {
                        navController.navigate("${DestinasiUpdate.route}/$it")
                    },
                    modifier = modifier,
                    onDeleteClick = {
                        navController.popBackStack()
                    }
                )
            }
        }
        composable(
            DestinasiUpdate.routesWithArg,
            arguments = listOf(
                navArgument(DestinasiUpdate.KODEMK){
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