package com.colegioibpi.myibpi.core.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Assessment
import androidx.compose.material.icons.filled.Campaign
import androidx.compose.material.icons.filled.EventAvailable
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Payments
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * As cinco abas principais do app, na ordem em que aparecem na barra inferior.
 *
 * Esta enum é a única fonte de verdade da navegação principal: a barra inferior e o grafo de
 * navegação são construídos a partir dela, de modo que incluir ou reordenar uma aba acontece
 * em um único lugar.
 *
 * A visibilidade por perfil ainda não é aplicada — hoje todas as abas aparecem para qualquer
 * usuário. A aba Financeiro deve ficar oculta para o perfil aluno, o que será implementado
 * junto com a autenticação (FASE 2).
 */
enum class MainDestination(
    val route: String,
    @StringRes val labelRes: Int,
    val icon: ImageVector,
) {
    ATTENDANCE(
        route = "attendance",
        labelRes = R.string.destination_attendance,
        icon = Icons.Filled.EventAvailable,
    ),
    REPORT_CARD(
        route = "report_card",
        labelRes = R.string.destination_report_card,
        icon = Icons.Filled.Assessment,
    ),
    ANNOUNCEMENTS(
        route = "announcements",
        labelRes = R.string.destination_announcements,
        icon = Icons.Filled.Campaign,
    ),
    FINANCE(
        route = "finance",
        labelRes = R.string.destination_finance,
        icon = Icons.Filled.Payments,
    ),
    INFO(
        route = "info",
        labelRes = R.string.destination_info,
        icon = Icons.Filled.Info,
    );

    companion object {
        /** Aba aberta quando o app inicia. */
        val START: MainDestination = ATTENDANCE

        fun fromRoute(route: String?): MainDestination? = entries.find { it.route == route }
    }
}
