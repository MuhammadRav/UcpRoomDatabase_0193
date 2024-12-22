package com.example.week11.ui.navigation

interface AlamatNavigasi {
    val route: String
}
object DestinasiHome : AlamatNavigasi {
    override val route = "home"
}

object DestinasiDetail : AlamatNavigasi {
    override val route = "detail"
    const val KODEMK = "kodeMk"
    val routesWithArg = "$route/{$KODEMK}"
}

object DestinasiUpdate : AlamatNavigasi {
    override val route = "update"
    const val KODEMK = "kodeMk"
    val routesWithArg = "$route/{$KODEMK}"
}
