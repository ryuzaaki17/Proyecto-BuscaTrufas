package co.edu.poli.controlador;

import co.edu.poli.modelo.Jugador;
import co.edu.poli.modelo.Tablero;
import co.edu.poli.modelo.Turnos;
import co.edu.poli.vista.VentanaPrincipal;

public class Coordinador {
	
	private VentanaPrincipal miVentana;
	private Tablero miTablero;
	private Turnos miTurno;
	private Jugador miJugador;
	
	//setear un objeto Ventana dentro de la clase coordinadora
	public void setVentanaPrincipal(VentanaPrincipal miVentana) {
		this.miVentana = miVentana;}
	//setear un objeto Tablero dentro de la clase coordinadora
	public void setTurno(Tablero miTablero) {
		this.miTablero = miTablero;}
	//setear un objeto Turno dentro de la clase coordinadora
	public void setTablero(Turnos miTurno) {
		this.miTurno = miTurno;	}
	//setear un objeto Jugador dentro de la clase coordinadora
	public void setTablero(Jugador miJugador) {
		this.miJugador = miJugador;
	}
}
