package co.edu.poli.modelo;

public class Casilla {
	
	
	private int fila; //posicion fila de la casilla 
	private int columna; //posicion columna de la casilla
	private boolean trufa; // valor true si tiene trufa envenenada, false si no tiene
	private boolean descubierta; // valor true si la casilla esta descubierta(ya fue revelado su valor) o esta oculta la casilla
	private int pistas; //cantidad de trufas envenenadas que hay alrededor de una casilla
	
	//método constructor
	public Casilla(int fila, int columna, boolean tienemina) {
		this.fila = fila;
		this.columna = columna;
		this.trufa = tienemina;
		this.descubierta = false;
		this.pistas = 0;
	}

	 //retorna el valor de fila
	public int getFila() {
		return fila;
	}

	
	//asigna el valor de fila
	public void setFila(int fila) {
		this.fila = fila;
	}

	 //retorna el valor de columna
	public int getColumna() {
		return columna;
	}
	
	//asigna el valor de columna
	public void setColumna(int columna) {
		this.columna = columna;
	}

	 //retorna el valor de mina
	public boolean isTrufa() {
		return trufa;
	}

    //asigna el valor de mina to set
	public void setTrufa(boolean trufa) {
		this.trufa = trufa;
	}

	//retorna el valor de descubierta
	public boolean isDescubierta() {
		return descubierta;
	}

	//asigna el valor de descubierta
	public void setDescubierta(boolean descubierta) {
		this.descubierta = descubierta;
	}

	//retorna el valor de pistas
	public int getPistas() {
		return pistas;
	}

	//asigna el valor de pistas
	public void setPistas(int pistas) {
		this.pistas = pistas;
	}
	
	//asigna el valor de pistas
	public void incrementarPistas() {
		this.pistas++;
	}

	
}
