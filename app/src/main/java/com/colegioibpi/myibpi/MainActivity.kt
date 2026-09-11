package com.colegioibpi.myibpi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.colegioibpi.myibpi.core.designsystem.theme.IbpiTheme
import com.colegioibpi.myibpi.presentation.screen.MainScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // A partir do targetSdk 35 o desenho edge-to-edge é obrigatório. Declarar isso aqui faz
        // o sistema escolher ícones escuros na barra de status, legíveis sobre o tema claro.
        enableEdgeToEdge()
        setContent {
            IbpiTheme {
                MainScreen()
            }
        }
    }
}
