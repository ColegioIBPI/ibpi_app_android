package com.colegioibpi.myibpi.feature.info.presentation.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.colegioibpi.myibpi.core.designsystem.theme.IbpiTheme
import com.colegioibpi.myibpi.core.ui.component.FeaturePlaceholder
import com.colegioibpi.myibpi.feature.info.R

/**
 * Aba 5 — Informações Úteis.
 *
 * Conteúdo ainda não implementado. A tela real exibirá cards com horários, calendários,
 * critérios de avaliação e demais documentos institucionais, filtrados pelo escopo de cada card.
 */
@Composable
fun InfoScreen(modifier: Modifier = Modifier) {
    FeaturePlaceholder(
        title = stringResource(R.string.info_title),
        description = stringResource(R.string.info_placeholder),
        modifier = modifier,
    )
}

@Preview(showBackground = true)
@Composable
private fun InfoScreenPreview() {
    IbpiTheme {
        InfoScreen()
    }
}
