package com.colegioibpi.myibpi.feature.report.presentation.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.colegioibpi.myibpi.core.designsystem.theme.IbpiTheme
import com.colegioibpi.myibpi.core.ui.component.FeaturePlaceholder
import com.colegioibpi.myibpi.feature.report.R

/**
 * Aba 2 — Boletim.
 *
 * Conteúdo ainda não implementado. A modelagem depende da definição do sistema de avaliação do
 * colégio (períodos, cálculo de média, recuperação), registrada como pendência no README.
 */
@Composable
fun ReportCardScreen(modifier: Modifier = Modifier) {
    FeaturePlaceholder(
        title = stringResource(R.string.report_card_title),
        description = stringResource(R.string.report_card_placeholder),
        modifier = modifier,
    )
}

@Preview(showBackground = true)
@Composable
private fun ReportCardScreenPreview() {
    IbpiTheme {
        ReportCardScreen()
    }
}
