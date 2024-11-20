package co.edu.poli.test;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import co.edu.poli.modelo.Tablero;
import co.edu.poli.modelo.Casilla;

public class TableroTest {

    private Tablero tablero;

    @BeforeEach
    public void setUp() {
        tablero = new Tablero(10, 10, 20);  // Ejemplo con 10x10 casillas y 20% de trufas
    }

    @Test
    public void testTableroInicializado() {
        Casilla[][] matriz = tablero.getMatrizTablero();
        assertNotNull(matriz, "La matriz del tablero no debería ser nula");
        assertEquals(10, matriz.length, "El número de filas debería ser 10");
        assertEquals(10, matriz[0].length, "El número de columnas debería ser 10");
    }

    @Test
    public void testNumeroCorrectoDeTrufas() {
        List<Casilla> trufas = tablero.getTrufasEnvenenadas();
        assertEquals(20, trufas.size(), "Debería haber 20 trufas envenenadas generadas");
    }

    @Test
    public void testCasillaSeleccionadaPerdida() {
        tablero.setEventoPierdeJuego(trufasEnvenenadas -> assertTrue(trufasEnvenenadas.size() > 0));
        List<Casilla> trufas = tablero.getTrufasEnvenenadas();
        if (!trufas.isEmpty()) {
            Casilla trufa = trufas.get(0);
            tablero.casillaSeleccionada(trufa.getFila(), trufa.getColumna());
        } else {
            fail("No se generaron trufas envenenadas en el tablero.");
        }
    }

    @Test
    public void testCasillaSeleccionadaDescubierta() {
        tablero.setEventoDescubrirCasilla(casilla -> assertTrue(casilla.isDescubierta()));
        Casilla casilla = tablero.getMatrizTablero()[0][0];
        tablero.casillaSeleccionada(casilla.getFila(), casilla.getColumna());
    }
}
