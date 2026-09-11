package com.colegioibpi.myibpi.presentation.component

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.colegioibpi.myibpi.core.designsystem.theme.IbpiTheme
import com.colegioibpi.myibpi.core.navigation.MainDestination

/** Prefixo das tags usadas pelos testes para identificar cada item da barra inferior. */
const val BOTTOM_BAR_ITEM_TAG_PREFIX = "bottom_bar_item_"

/**
 * Barra inferior com as cinco abas principais.
 *
 * Stateless: recebe a aba atual e devolve a aba escolhida, sem conhecer o NavController.
 */
@Composable
fun MainBottomBar(
    currentDestination: MainDestination,
    onDestinationClick: (MainDestination) -> Unit,
    modifier: Modifier = Modifier,
) {
    NavigationBar(modifier = modifier) {
        MainDestination.entries.forEach { destination ->
            val label = stringResource(destination.labelRes)
            NavigationBarItem(
                selected = destination == currentDestination,
                onClick = { onDestinationClick(destination) },
                icon = { Icon(imageVector = destination.icon, contentDescription = null) },
                label = { Text(text = label) },
                modifier = Modifier.testTag(BOTTOM_BAR_ITEM_TAG_PREFIX + destination.route),
            )
        }
    }
}

@Preview
@Composable
private fun MainBottomBarPreview() {
    IbpiTheme {
        MainBottomBar(
            currentDestination = MainDestination.START,
            onDestinationClick = {},
        )
    }
}
