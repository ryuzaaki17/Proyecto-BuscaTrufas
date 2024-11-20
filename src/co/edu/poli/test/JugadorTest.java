package co.edu.poli.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import co.edu.poli.modelo.Jugador;

public class JugadorTest {

    private Jugador jugador;

    @BeforeEach
    public void setUp() {
        // Inicializa el objeto Jugador antes de cada prueba
        jugador = new Jugador("Jugador1", 3, 100.0, 50.0);
    }

    // getNombreJugador() devuelve el nombre correcto del jugador.
    @Test
    public void testGetNombreJugador() {
        assertEquals("Jugador1", jugador.getNombreJugador());
    }

    // setNombreJugador() cambia el nombre del jugador correctamente.
    @Test
    public void testSetNombreJugador() {
        jugador.setNombreJugador("NuevoNombre");
        assertEquals("NuevoNombre", jugador.getNombreJugador());
    }

    // getNumeroVidas() devuelve el número correcto de vidas.
    @Test
    public void testGetNumeroVidas() {
        assertEquals(3, jugador.getNumeroVidas());
    }

    // setNumeroVidas() cambia el número de vidas del jugador correctamente.
    @Test
    public void testSetNumeroVidas() {
        jugador.setNumeroVidas(5);
        assertEquals(5, jugador.getNumeroVidas());
    }

    // getPuntuacion() devuelve la puntuación correcta.
    @Test
    public void testGetPuntuacion() {
        assertEquals(100.0, jugador.getPuntuacion());
    }

    // setPuntuacion() cambia la puntuación del jugador correctamente.
    @Test
    public void testSetPuntuacion() {
        jugador.setPuntuacion(150.0);
        assertEquals(150.0, jugador.getPuntuacion());
    }

    // getTiempoAcumulado() devuelve el tiempo acumulado correcto.
    @Test
    public void testGetTiempoAcumulado() {
        assertEquals(50.0, jugador.getTiempoAcumulado());
    }

    // setTiempoAcumulado() cambia el tiempo acumulado del jugador correctamente.
    @Test
    public void testSetTiempoAcumulado() {
        jugador.setTiempoAcumulado(75.0);
        assertEquals(75.0, jugador.getTiempoAcumulado());
    }

    // actualizarPuntuacion() actualiza la puntuación del jugador correctamente.
    @Test
    public void testActualizarPuntuacion() {
        double nuevaPuntuacion = jugador.actualizarPuntuacion(50.0);
        assertEquals(150.0, nuevaPuntuacion);
    }

    // restarVida() resta una vida correctamente y determina si el jugador tiene vidas restantes.
    @Test
    public void testRestarVida() {
        boolean tieneVidas = jugador.restarVida();
        assertEquals(2, jugador.getNumeroVidas());
        assertTrue(tieneVidas);

        jugador.restarVida();
        jugador.restarVida();
        tieneVidas = jugador.restarVida();
        assertFalse(tieneVidas);
    }

    // tiempoTurno() actualiza el tiempo acumulado del jugador correctamente.
    @Test
    public void testTiempoTurno() {
        double nuevoTiempo = jugador.tiempoTurno(20.0);
        assertEquals(70.0, nuevoTiempo);
    }
}


