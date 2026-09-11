package com.colegioibpi.myibpi.core.designsystem.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

/**
 * Tema único do app.
 *
 * O escopo definido no levantamento de requisitos é celular em retrato e tema claro apenas,
 * portanto não há variante escura aqui. Caso o modo escuro entre no escopo, acrescente um
 * `darkColorScheme` e a escolha por `isSystemInDarkTheme()`.
 */
private val IbpiLightColorScheme = lightColorScheme(
    primary = IbpiNavy,
    onPrimary = IbpiWhite,
    primaryContainer = IbpiNavyContainer,
    onPrimaryContainer = IbpiNavyDark,
    secondary = IbpiAmber,
    onSecondary = IbpiWhite,
    secondaryContainer = IbpiAmberContainer,
    onSecondaryContainer = IbpiNavyDark,
    background = IbpiSurface,
    onBackground = IbpiOnSurface,
    surface = IbpiSurface,
    onSurface = IbpiOnSurface,
    surfaceVariant = IbpiSurfaceVariant,
    onSurfaceVariant = IbpiOnSurfaceVariant,
    outline = IbpiOutline,
    error = IbpiError,
    onError = IbpiWhite,
    errorContainer = IbpiErrorContainer,
    onErrorContainer = IbpiOnErrorContainer,
)

@Composable
fun IbpiTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = IbpiLightColorScheme,
        typography = IbpiTypography,
        content = content,
    )
}
