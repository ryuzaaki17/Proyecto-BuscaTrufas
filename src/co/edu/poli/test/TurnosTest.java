package co.edu.poli.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import co.edu.poli.modelo.Jugador;
import co.edu.poli.modelo.Turnos;

public class TurnosTest {

    private Turnos turnos;
    private Jugador jugador1;
    private Jugador jugador2;
    private Jugador[] listaJugadores;

    // setUp() inicializa el objeto Turnos y los jugadores antes de cada prueba
    @BeforeEach
    public void setUp() {
        jugador1 = new Jugador("Jugador1", 3, 100.0, 50.0);
        jugador2 = new Jugador("Jugador2", 3, 100.0, 50.0);
        listaJugadores = new Jugador[] { jugador1, jugador2 };
        turnos = new Turnos(listaJugadores, "Jugador1");
    }

    // getListaJugadores() devuelve la lista correcta de jugadores.
    @Test
    public void testGetListaJugadores() {
        assertArrayEquals(listaJugadores, turnos.getListaJugadores());
    }

    // setListaJugadores() cambia la lista de jugadores correctamente.
    @Test
    public void testSetListaJugadores() {
        Jugador jugador3 = new Jugador("Jugador3", 3, 100.0, 50.0);
        Jugador[] nuevaLista = new Jugador[] { jugador1, jugador2, jugador3 };
        turnos.setListaJugadores(nuevaLista);
        assertArrayEquals(nuevaLista, turnos.getListaJugadores());
    }

    // getJugadorActivo() devuelve el nombre correcto del jugador activo.
    @Test
    public void testGetJugadorActivo() {
        assertEquals("Jugador1", turnos.getJugadorActivo());
    }

    // setJugadorActivo() cambia el jugador activo correctamente.
    @Test
    public void testSetJugadorActivo() {
        turnos.setJugadorActivo("Jugador2");
        assertEquals("Jugador2", turnos.getJugadorActivo());
    }

    // cambiarTurno() alterna el turno entre los jugadores correctamente.
    @Test
    public void testCambiarTurno() {
        String nuevoJugadorActivo = turnos.cambiarTurno();
        assertEquals("Jugador2", nuevoJugadorActivo);
        nuevoJugadorActivo = turnos.cambiarTurno();
        assertEquals("Jugador1", nuevoJugadorActivo);
    }

    /** terminarJuego() devuelve true cuando un jugador pierde todas sus vidas
     */
    @Test
    public void testTerminarJuego() {
        // Caso donde ningún jugador ha perdido todas sus vidas.
        assertFalse(turnos.terminarJuego());
        
        // Simulamos que un jugador pierde todas sus vidas.
        jugador1.setNumeroVidas(0);
        assertTrue(turnos.terminarJuego());
    }
}
