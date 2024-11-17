package co.edu.poli.controlador;

import co.edu.poli.modelo.Tablero;
import co.edu.poli.vista.VentanaPrincipal;

public class Coordinador {
	
	private VentanaPrincipal miVentana;
	private Tablero miTablero;
	
	public void setVentanaPrincipal(VentanaPrincipal miVentana) {
		this.miVentana = miVentana;
		
	}
	
	public void setTablero(Tablero miTablero) {
		this.miTablero = miTablero;
		
	}
	

}
