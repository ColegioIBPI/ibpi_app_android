package com.colegioibpi.myibpi.presentation.screen

import android.app.Application
import android.content.Context
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsSelected
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.core.app.ApplicationProvider
import com.colegioibpi.myibpi.core.designsystem.theme.IbpiTheme
import com.colegioibpi.myibpi.core.navigation.MainDestination
import com.colegioibpi.myibpi.presentation.component.BOTTOM_BAR_ITEM_TAG_PREFIX
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import com.colegioibpi.myibpi.feature.attendance.R as AttendanceR
import com.colegioibpi.myibpi.feature.finance.R as FinanceR
import com.colegioibpi.myibpi.feature.report.R as ReportR

/**
 * Testes da tela principal.
 *
 * Rodam na JVM via Robolectric, sem emulador. A Application padrão substitui a
 * [com.colegioibpi.myibpi.MyIbpiApplication] porque a tela não depende de injeção de dependência.
 */
@RunWith(RobolectricTestRunner::class)
@Config(application = Application::class, sdk = [34])
class MainScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val context: Context get() = ApplicationProvider.getApplicationContext()

    /**
     * O conteúdo de cada aba é identificado pela sua descrição, e não pelo título: títulos como
     * "Boletim" e "Financeiro" também são o rótulo da aba na barra inferior, o que tornaria a
     * busca ambígua.
     */
    private fun conteudoDaTela(descricaoRes: Int) =
        composeTestRule.onNodeWithText(context.getString(descricaoRes))

    private fun renderizarTelaPrincipal() {
        composeTestRule.setContent {
            IbpiTheme {
                MainScreen()
            }
        }
    }

    @Test
    fun `exibe as cinco abas na barra inferior`() {
        renderizarTelaPrincipal()

        MainDestination.entries.forEach { destino ->
            composeTestRule
                .onNodeWithTag(BOTTOM_BAR_ITEM_TAG_PREFIX + destino.route)
                .assertIsDisplayed()
        }
    }

    @Test
    fun `abre na aba de frequencia e ocorrencias`() {
        renderizarTelaPrincipal()

        conteudoDaTela(AttendanceR.string.attendance_placeholder).assertIsDisplayed()

        composeTestRule
            .onNodeWithTag(BOTTOM_BAR_ITEM_TAG_PREFIX + MainDestination.ATTENDANCE.route)
            .assertIsSelected()
    }

    @Test
    fun `navega para o boletim ao selecionar a aba`() {
        renderizarTelaPrincipal()

        composeTestRule
            .onNodeWithTag(BOTTOM_BAR_ITEM_TAG_PREFIX + MainDestination.REPORT_CARD.route)
            .performClick()

        conteudoDaTela(ReportR.string.report_card_placeholder).assertIsDisplayed()

        composeTestRule
            .onNodeWithTag(BOTTOM_BAR_ITEM_TAG_PREFIX + MainDestination.REPORT_CARD.route)
            .assertIsSelected()
    }

    @Test
    fun `navega para o financeiro ao selecionar a aba`() {
        renderizarTelaPrincipal()

        composeTestRule
            .onNodeWithTag(BOTTOM_BAR_ITEM_TAG_PREFIX + MainDestination.FINANCE.route)
            .performClick()

        conteudoDaTela(FinanceR.string.finance_placeholder).assertIsDisplayed()
    }

    @Test
    fun `volta para a aba inicial preservando a selecao`() {
        renderizarTelaPrincipal()

        composeTestRule
            .onNodeWithTag(BOTTOM_BAR_ITEM_TAG_PREFIX + MainDestination.INFO.route)
            .performClick()
        composeTestRule
            .onNodeWithTag(BOTTOM_BAR_ITEM_TAG_PREFIX + MainDestination.ATTENDANCE.route)
            .performClick()

        conteudoDaTela(AttendanceR.string.attendance_placeholder).assertIsDisplayed()

        composeTestRule
            .onNodeWithTag(BOTTOM_BAR_ITEM_TAG_PREFIX + MainDestination.ATTENDANCE.route)
            .assertIsSelected()
    }
}
