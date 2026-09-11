package com.colegioibpi.myibpi.feature.finance.presentation.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.colegioibpi.myibpi.core.designsystem.theme.IbpiTheme
import com.colegioibpi.myibpi.core.ui.component.FeaturePlaceholder
import com.colegioibpi.myibpi.feature.finance.R

/**
 * Aba 4 — Financeiro.
 *
 * Conteúdo ainda não implementado. Esta aba é visível apenas para responsáveis; a restrição por
 * perfil será aplicada junto com a autenticação (FASE 2).
 */
@Composable
fun FinanceScreen(modifier: Modifier = Modifier) {
    FeaturePlaceholder(
        title = stringResource(R.string.finance_title),
        description = stringResource(R.string.finance_placeholder),
        modifier = modifier,
    )
}

@Preview(showBackground = true)
@Composable
private fun FinanceScreenPreview() {
    IbpiTheme {
        FinanceScreen()
    }
}
