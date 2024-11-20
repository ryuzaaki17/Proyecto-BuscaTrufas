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

    // getNombreJugador() devuelve el nombre correcto del jugador.
    @Test
    public void testGetNombreJugador() {
        assertEquals("Jugador1", jugador.getNombreJugador());
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
}
