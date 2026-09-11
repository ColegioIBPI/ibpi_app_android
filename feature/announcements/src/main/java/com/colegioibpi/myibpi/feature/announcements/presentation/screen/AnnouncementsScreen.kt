package com.colegioibpi.myibpi.feature.announcements.presentation.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.colegioibpi.myibpi.core.designsystem.theme.IbpiTheme
import com.colegioibpi.myibpi.core.ui.component.FeaturePlaceholder
import com.colegioibpi.myibpi.feature.announcements.R

/**
 * Aba 3 — Avisos.
 *
 * Conteúdo ainda não implementado. A tela real listará os comunicados destinados ao usuário,
 * considerando os alvos possíveis: aluno, responsável, turma, segmento ou todos.
 */
@Composable
fun AnnouncementsScreen(modifier: Modifier = Modifier) {
    FeaturePlaceholder(
        title = stringResource(R.string.announcements_title),
        description = stringResource(R.string.announcements_placeholder),
        modifier = modifier,
    )
}

@Preview(showBackground = true)
@Composable
private fun AnnouncementsScreenPreview() {
    IbpiTheme {
        AnnouncementsScreen()
    }
}
