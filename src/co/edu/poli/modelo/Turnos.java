package co.edu.poli.modelo;

/**
 * Clase que gestiona los turnos de los jugadores en el juego BuscaTrufas.
 * Permite alternar turnos entre jugadores y verificar si el juego ha terminado.
 */
public class Turnos {
    private Jugador[] listaJugadores;
    private String jugadorActivo;
    private int indiceJugadorActivo;

    /**
     * Constructor de la clase Turnos.
     *
     * @param listaJugadores arreglo con los jugadores participantes.
     * @param jugadorActivo  el nombre del jugador que inicia el turno.
     */
    public Turnos(Jugador[] listaJugadores, String jugadorActivo) {
        this.listaJugadores = listaJugadores;
        this.jugadorActivo = jugadorActivo;
        this.indiceJugadorActivo = obtenerIndiceJugador(jugadorActivo);
    }

    /**
     * Obtiene la lista de jugadores.
     *
     * @return un arreglo de jugadores.
     */
    public Jugador[] getListaJugadores() {
        return listaJugadores;
    }

    /**
     * Establece la lista de jugadores.
     *
     * @param listaJugadores un arreglo de jugadores.
     */
    public void setListaJugadores(Jugador[] listaJugadores) {
        this.listaJugadores = listaJugadores;
    }

    /**
     * Obtiene el jugador activo actual.
     *
     * @return el nombre del jugador activo.
     */
    public String getJugadorActivo() {
        return jugadorActivo;
    }

    /**
     * Establece el jugador activo.
     *
     * @param jugadorActivo el nombre del nuevo jugador activo.
     */
    public void setJugadorActivo(String jugadorActivo) {
        this.jugadorActivo = jugadorActivo;
    }

    /**
     * Cambia al siguiente jugador en la lista de turnos.
     *
     * @return el nombre del nuevo jugador activo.
     */
    public String cambiarTurno() {
        indiceJugadorActivo = (indiceJugadorActivo + 1) % listaJugadores.length;
        jugadorActivo = listaJugadores[indiceJugadorActivo].getNombreJugador();
        return jugadorActivo;
    }

    /**
     * Finaliza el juego verificando si algún jugador no tiene vidas restantes.
     * Si todos los jugadores tienen vidas, el juego continúa.
     *
     * @return {@code true} si el juego ha terminado, {@code false} en caso contrario.
     */
    public boolean terminarJuego() {
        for (Jugador jugador : listaJugadores) {
            if (jugador.getNumeroVidas() <= 0) {
                return true; // El juego termina si un jugador se queda sin vidas.
            }
        }
        return false; // El juego continúa si todos tienen vidas.
    }

    /**
     * Busca el índice del jugador activo en la lista de jugadores.
     *
     * @param nombreJugador el nombre del jugador.
     * @return el índice del jugador en la lista, o 0 si no se encuentra.
     */
    private int obtenerIndiceJugador(String nombreJugador) {
        for (int i = 0; i < listaJugadores.length; i++) {
            if (listaJugadores[i].getNombreJugador().equals(nombreJugador)) {
                return i;
            }
        }
        return 0; // Por defecto, devuelve el índice 0 si no encuentra al jugador.
    }
}
