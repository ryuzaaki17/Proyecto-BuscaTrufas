package co.edu.poli.test;

import co.edu.poli.modelo.Casilla;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CasillaTest {

    @Test
    void verificaConstructor() {
        Casilla casilla = new Casilla(2, 3, false);
        assertEquals(2, casilla.getFila());
        assertEquals(3, casilla.getColumna());
        assertFalse(casilla.isTrufa());
        assertFalse(casilla.isDescubierta());
        assertEquals(0, casilla.getPistas());
    }

    @Test
    void verificaGettersAndSetters() {
        Casilla casilla = new Casilla(0, 0, true);
        casilla.setFila(5);
        casilla.setColumna(7);
        casilla.setDescubierta(true);
        assertEquals(5, casilla.getFila());
        assertEquals(7, casilla.getColumna());
        assertTrue(casilla.isDescubierta());
    }

    @Test
    void verificaIncrementarPistas() {
        Casilla casilla = new Casilla(1, 1, false);
        casilla.incrementarPistas();
        assertEquals(1, casilla.getPistas());
        casilla.incrementarPistas();
        assertEquals(2, casilla.getPistas());
    }
}