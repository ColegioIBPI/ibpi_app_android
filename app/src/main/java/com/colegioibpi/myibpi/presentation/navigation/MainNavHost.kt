package com.colegioibpi.myibpi.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.colegioibpi.myibpi.core.navigation.MainDestination
import com.colegioibpi.myibpi.feature.announcements.presentation.screen.AnnouncementsScreen
import com.colegioibpi.myibpi.feature.attendance.presentation.screen.AttendanceScreen
import com.colegioibpi.myibpi.feature.finance.presentation.screen.FinanceScreen
import com.colegioibpi.myibpi.feature.info.presentation.screen.InfoScreen
import com.colegioibpi.myibpi.feature.report.presentation.screen.ReportCardScreen

/**
 * Grafo de navegação das abas principais.
 *
 * O `app` é o único módulo que conhece todas as features — nenhuma feature depende de outra.
 */
@Composable
fun MainNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = MainDestination.START.route,
        modifier = modifier,
    ) {
        composable(MainDestination.ATTENDANCE.route) { AttendanceScreen() }
        composable(MainDestination.REPORT_CARD.route) { ReportCardScreen() }
        composable(MainDestination.ANNOUNCEMENTS.route) { AnnouncementsScreen() }
        composable(MainDestination.FINANCE.route) { FinanceScreen() }
        composable(MainDestination.INFO.route) { InfoScreen() }
    }
}

/**
 * Troca de aba.
 *
 * Não empilha destinos: volta até o início do grafo preservando o estado de cada aba, para que
 * alternar entre abas não crie um histórico crescente nem recarregue a tela já visitada.
 */
fun NavHostController.navigateToMainDestination(destination: MainDestination) {
    navigate(destination.route) {
        popUpTo(graph.findStartDestination().id) { saveState = true }
        launchSingleTop = true
        restoreState = true
    }
}
