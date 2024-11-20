package co.edu.poli.test;

import junit.framework.TestCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import co.edu.poli.modelo.Jugador;

/**
 * Clase de prueba para la clase Jugador.
 */
public class JugadorTest extends TestCase  {

    private Jugador jugador;

    /**
     * Configura un jugador antes de cada prueba.
     */
    @BeforeEach
    void setUp() {
        jugador = new Jugador("Jugador1", 3, 0.0, 0.0);
    }

    /**
     * Prueba el constructor y los getters de la clase Jugador.
     */
    @Test
    void testConstructorYGetters() {
        assertEquals("Jugador1", jugador.getNombreJugador(), "El nombre del jugador no coincide.");
        assertEquals(3, jugador.getNumeroVidas(), "El número de vidas no coincide.");
        assertEquals(0.0, jugador.getPuntuacion(), "La puntuación inicial no coincide.");
        assertEquals(0.0, jugador.getTiempoAcumulado(), "El tiempo acumulado inicial no coincide.");
    }

    /**
     * Prueba el método setNombreJugador.
     */
    @Test
    void testSetNombreJugador() {
        jugador.setNombreJugador("NuevoNombre");
        assertEquals("NuevoNombre", jugador.getNombreJugador(), "El nombre del jugador no se actualizó correctamente.");
    }

    /**
     * Prueba el método actualizaarPuntuacion.
     */
    @Test
    void testActualizaarPuntuacion() {
        double nuevaPuntuacion = 50.0;
        jugador.actualizarPuntuacion(nuevaPuntuacion);
        assertEquals(50.0, jugador.getPuntuacion(), "La puntuación no se actualizó correctamente.");
    }

    /**
     * Prueba el método restarVida.
     */
    @Test
    void testRestarVida() {
        assertTrue(jugador.restarVida(), "El jugador debería seguir vivo.");
        assertTrue(jugador.restarVida(), "El jugador debería seguir vivo.");
        assertTrue(jugador.restarVida(), "El jugador debería seguir vivo.");
        assertFalse(jugador.restarVida(), "El jugador no debería tener más vidas.");
        assertEquals(0, jugador.getNumeroVidas(), "El número de vidas no coincide.");
    }

    /**
     * Prueba el método tiempoTurno.
     */
    @Test
    void testTiempoTurno() {
        double tiempoAgregado = 10.0;
        jugador.tiempoTurno(tiempoAgregado);
        assertEquals(10.0, jugador.getTiempoAcumulado(), "El tiempo acumulado no se actualizó correctamente.");
        jugador.tiempoTurno(5.0);
        assertEquals(15.0, jugador.getTiempoAcumulado(), "El tiempo acumulado no se actualizó correctamente.");
    }
}
