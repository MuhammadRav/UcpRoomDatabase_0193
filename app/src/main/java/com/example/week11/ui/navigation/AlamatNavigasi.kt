package com.example.week11.ui.navigation

interface AlamatNavigasi {
    val route: String
}
object DestinasiHomeMk : AlamatNavigasi {
    override val route = "home MK"
}

object DestinasiDetailMk : AlamatNavigasi {
    override val route = "detail"
    const val KODEMK = "kodeMk"
    val routesWithArg = "$route/{$KODEMK}"
}

object DestinasiUpdateMk : AlamatNavigasi {
    override val route = "update"
    const val KODEMK = "kodeMk"
    val routesWithArg = "$route/{$KODEMK}"
}
object DestinasiHomeDosen : AlamatNavigasi {
    override val route = "home Dosen"
}

object DestinasiDetailDosen : AlamatNavigasi {
    override val route = "detail"
    const val NIDN = "nidn"
    val routesWithArg = "$route/{$NIDN}"
}
object Utama : AlamatNavigasi {
    override val route = "utama"
}