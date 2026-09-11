package com.colegioibpi.myibpi.presentation.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.colegioibpi.myibpi.core.designsystem.theme.IbpiTheme
import com.colegioibpi.myibpi.core.navigation.MainDestination
import com.colegioibpi.myibpi.presentation.component.MainBottomBar
import com.colegioibpi.myibpi.presentation.navigation.MainNavHost
import com.colegioibpi.myibpi.presentation.navigation.navigateToMainDestination

/**
 * Tela raiz do app: as cinco abas principais com a barra de navegação inferior.
 *
 * Ainda não há restrição por perfil — todas as abas são exibidas a qualquer usuário. A aba
 * Financeiro deve ficar oculta para o perfil aluno assim que a autenticação existir (FASE 2).
 */
@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = MainDestination.fromRoute(backStackEntry?.destination?.route)
        ?: MainDestination.START

    Scaffold(
        modifier = modifier.fillMaxSize(),
        bottomBar = {
            MainBottomBar(
                currentDestination = currentDestination,
                onDestinationClick = { destination ->
                    navController.navigateToMainDestination(destination)
                },
            )
        },
    ) { innerPadding ->
        MainNavHost(
            navController = navController,
            modifier = Modifier.padding(innerPadding),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MainScreenPreview() {
    IbpiTheme {
        MainScreen()
    }
}
