package com.colegioibpi.myibpi.core.navigation

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class MainDestinationTest {

    @Test
    fun `define exatamente as cinco abas principais`() {
        assertEquals(5, MainDestination.entries.size)
    }

    @Test
    fun `mantem a ordem das abas definida no levantamento de requisitos`() {
        val ordemEsperada = listOf(
            MainDestination.ATTENDANCE,
            MainDestination.REPORT_CARD,
            MainDestination.ANNOUNCEMENTS,
            MainDestination.FINANCE,
            MainDestination.INFO,
        )

        assertEquals(ordemEsperada, MainDestination.entries.toList())
    }

    @Test
    fun `nao permite rotas duplicadas`() {
        val rotas = MainDestination.entries.map { it.route }

        assertEquals(rotas.size, rotas.distinct().size)
    }

    @Test
    fun `inicia o app na aba de frequencia`() {
        assertEquals(MainDestination.ATTENDANCE, MainDestination.START)
    }

    @Test
    fun `resolve o destino a partir da rota`() {
        MainDestination.entries.forEach { destino ->
            assertEquals(destino, MainDestination.fromRoute(destino.route))
        }
    }

    @Test
    fun `retorna nulo para rota desconhecida ou ausente`() {
        assertNull(MainDestination.fromRoute("rota_inexistente"))
        assertNull(MainDestination.fromRoute(null))
    }
}
