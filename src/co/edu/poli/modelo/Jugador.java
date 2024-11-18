package co.edu.poli.modelo;

/**
 * Clase que representa a un jugador en el juego BuscaTrufas.
 * Cada jugador tiene un nombre, un número de vidas, una puntuación acumulada
 * y un tiempo acumulado por turnos.
 */
public class Jugador {
    private String nombreJugador;
    private int numeroVidas;
    private double puntuacion;
    private double tiempoAcumulado;

    /**
     * Constructor de la clase Jugador.
     *
     * @param nombreJugador   el nombre del jugador.
     * @param numeroVidas     el número inicial de vidas del jugador.
     * @param puntuacion      la puntuación inicial del jugador.
     * @param tiempoAcumulado el tiempo inicial acumulado del jugador.
     */
    public Jugador(String nombreJugador, int numeroVidas, double puntuacion, double tiempoAcumulado) {
        this.nombreJugador = nombreJugador;
        this.numeroVidas = numeroVidas;
        this.puntuacion = puntuacion;
        this.tiempoAcumulado = tiempoAcumulado;
    }

    /**
     * Obtiene el nombre del jugador.
     *
     * @return el nombre del jugador.
     */
    public String getNombreJugador() {
        return nombreJugador;
    }

    /**
     * Establece el nombre del jugador.
     *
     * @param nombreJugador el nombre del jugador.
     */
    public void setNombreJugador(String nombreJugador) {
        this.nombreJugador = nombreJugador;
    }

    /**
     * Obtiene el número de vidas restantes del jugador.
     *
     * @return el número de vidas.
     */
    public int getNumeroVidas() {
        return numeroVidas;
    }

    /**
     * Establece el número de vidas del jugador.
     *
     * @param numeroVidas el número de vidas.
     */
    public void setNumeroVidas(int numeroVidas) {
        this.numeroVidas = numeroVidas;
    }

    /**
     * Obtiene la puntuación actual del jugador.
     *
     * @return la puntuación del jugador.
     */
    public double getPuntuacion() {
        return puntuacion;
    }

    /**
     * Establece la puntuación del jugador.
     *
     * @param puntuacion la nueva puntuación del jugador.
     */
    public void setPuntuacion(double puntuacion) {
        this.puntuacion = puntuacion;
    }

    /**
     * Obtiene el tiempo acumulado de turnos del jugador.
     *
     * @return el tiempo acumulado.
     */
    public double getTiempoAcumulado() {
        return tiempoAcumulado;
    }

    /**
     * Establece el tiempo acumulado del jugador.
     *
     * @param tiempoAcumulado el nuevo tiempo acumulado.
     */
    public void setTiempoAcumulado(double tiempoAcumulado) {
        this.tiempoAcumulado = tiempoAcumulado;
    }

    /**
     * Actualiza la puntuación del jugador sumando una nueva puntuación.
     *
     * @param nuevaPuntuacion la puntuación a agregar.
     * @return la puntuación actualizada.
     */
    public double actualizarPuntuacion(double nuevaPuntuacion) {
        puntuacion += nuevaPuntuacion;
        return puntuacion;
    }

    /**
     * Resta una vida al jugador. 
     * Si las vidas llegan a menos de cero, se considera que el jugador ha perdido.
     *
     * @return {@code true} si el jugador todavía tiene vidas, {@code false} en caso contrario.
     */
    public boolean restarVida() {
        numeroVidas--;
        return numeroVidas >= 0;
    }

    /**
     * Suma el tiempo de un turno al tiempo acumulado del jugador.
     *
     * @param nuevoTiempo el tiempo que duró el turno.
     * @return el tiempo acumulado actualizado.
     */
    public double tiempoTurno(double nuevoTiempo) {
        tiempoAcumulado += nuevoTiempo;
        return tiempoAcumulado;
    }
}
