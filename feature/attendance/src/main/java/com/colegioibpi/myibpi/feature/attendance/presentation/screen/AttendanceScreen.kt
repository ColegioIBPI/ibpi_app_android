package com.colegioibpi.myibpi.feature.attendance.presentation.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.colegioibpi.myibpi.core.designsystem.theme.IbpiTheme
import com.colegioibpi.myibpi.core.ui.component.FeaturePlaceholder
import com.colegioibpi.myibpi.feature.attendance.R

/**
 * Aba 1 — Frequência e Ocorrências.
 *
 * Conteúdo ainda não implementado. A tela real exibirá os registros individuais do aluno com
 * filtro por tipo (frequência, ocorrências disciplinares e ocorrências acadêmicas), além do
 * lançamento pelo professor.
 */
@Composable
fun AttendanceScreen(modifier: Modifier = Modifier) {
    FeaturePlaceholder(
        title = stringResource(R.string.attendance_title),
        description = stringResource(R.string.attendance_placeholder),
        modifier = modifier,
    )
}

@Preview(showBackground = true)
@Composable
private fun AttendanceScreenPreview() {
    IbpiTheme {
        AttendanceScreen()
    }
}
