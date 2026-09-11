package com.colegioibpi.myibpi.core.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.colegioibpi.myibpi.core.designsystem.theme.IbpiTheme

/**
 * Conteúdo exibido por uma aba cuja funcionalidade ainda não foi implementada.
 *
 * Existe para que a navegação entre as cinco abas seja validável desde já. Cada tela substitui
 * esta chamada pelo seu conteúdo real quando a feature correspondente for desenvolvida.
 */
@Composable
fun FeaturePlaceholder(
    title: String,
    description: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center,
        )
        Text(
            text = description,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun FeaturePlaceholderPreview() {
    IbpiTheme {
        FeaturePlaceholder(
            title = "Boletim",
            description = "Consulta de notas e desempenho escolar.",
        )
    }
}
