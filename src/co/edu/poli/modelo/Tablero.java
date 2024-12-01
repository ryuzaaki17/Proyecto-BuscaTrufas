package co.edu.poli.modelo;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;

import co.edu.poli.controlador.Coordinador;

public class Tablero {

	private Coordinador miCoordinador; //creamos un objeto de tipo coordinador para controlar el patron MVC
	private Casilla [][] matrizTablero; //matriz de casillas que contendrá nuestro tablero de buscaminas
	private int numFilas; //cantidad de filas que contiene el tablero
	private int numColumnas; //cantidad de columnas que contiene el tablero
	private int numTrufas; //cantidad de trufas envenenadas en valor entero que debe tener nuestro tablero
	private boolean juegoTerminado; //Bandera que determina cuando el juego se haya acabado
	private int numCasillasAbiertas; //cantidad de casillas activadas en el juego
	
	private List<Casilla> trufasEnvenenadas = new LinkedList<>(); //lista que contiene todas las casillas donde hay una trufa envenenada
	

	private Consumer<List<Casilla>> eventoPierdeJuego;
	
	private Consumer<List<Casilla>> eventoGanaJuego;
	
	private Consumer<Casilla> eventoDescubrirCasilla;

	//metodo constructor de la clase
	public Tablero(int numFilas, int numColumnas, int porcentajeTrufas) {
		this.numFilas = numFilas;
		this.numColumnas = numColumnas;
		//deteminamos la cantidad de trufas envenenadas a crear en el tablero de acuerdo al porcentaje suministrado en la entrada del constructor
		this.numTrufas = retornaCantTrufas(numFilas, numColumnas,porcentajeTrufas);
		System.out.println("numero de trufas"+this.numTrufas);
		
		//Creamos el objeto con el tablero y sus casillas
		IniciarCasillas();
		
	}
	
	//metodo para calcular la cantidad de trufas a partir de un porcentaje
	private int retornaCantTrufas(int numFilas, int numColumnas, double porcentajeTrufas) {
		int cantidad = (int) Math.floor((numFilas*numColumnas)*(porcentajeTrufas/100));
		//System.out.println("cantidad"+cantidad+porcentajeTrufas);
		return cantidad;
	}
	
	//metodo para inicializar las variables de nuestro tablero
	private void IniciarCasillas() {
		//crear la matriz de objetos tipo casilla con un determinado tamaño parametrizable
		this.matrizTablero = new Casilla[this.numFilas][this.numColumnas];
		
		//System.out.println(matrizTablero.length); //cantidad de filas
		//System.out.println(matrizTablero[0].length); // cantidad de columnas
		
		//recorrer cada casilla de nuestra matrizTablero para crear dentro de este un objeto casilla
		for(int i = 0; i < this.matrizTablero.length; i++) {
			
			for (int j = 0; j < this.matrizTablero[i].length; j++) {
				
				//asignarle valores a nuestro objeto casilla a travez del metodo constructor, 
				this.matrizTablero[i][j] = new Casilla(i, j, false);
			}
		}
		//despues de generar el tablero, generamos las trufas aleatoriamente dentro del tablero
		generarTrufas();
		
		//despues de generar las trufas envenenadas, generamos las pistas en las casillas alrededor
		generarPistas();
		
		//Generamos la lista que contiene todas las casillas con trufasEnvenenadas
	    listaCasillasconTrufa();
	}
	
	//metodo para generar las casillas con trufas envenenadas dentro de nuestro tablero de forma aleatoria
	private void generarTrufas() {
		Random random = new Random();
		int trufasGeneradas = 0;
		
		while (trufasGeneradas < this.numTrufas) {
			int posibleFila = random.nextInt(this.matrizTablero.length);
			int posibleColumna = random.nextInt(this.matrizTablero[0].length);
			
			//Validamos si en la casilla generada aleatoriamente ya habia una mina, para descartarla y volver a generar unos nuevos valores aleatorios
			if(this.matrizTablero[posibleFila][posibleColumna].isTrufa() == false ) {
				this.matrizTablero[posibleFila][posibleColumna].setTrufa(true);
				trufasGeneradas++;
			}
			
		}
	}
	
	//metodo para generar la matriz de pistas(numeros) que indican la cantidad de trufas envenenadas alrededor de una casilla
	private void generarPistas() {
		//recorrer cada casilla de nuestra matrizTablero 
				for(int i = 0; i < this.matrizTablero.length; i++) {
					for (int j = 0; j < this.matrizTablero[i].length; j++) {
						
						//validamos si la casilla recorrida tiene una trufa envenenada
						if (this.matrizTablero[i][j].isTrufa()) {
							//ejecutamos el metodo retornarCasillasPerimetro para conocer cuales son las casillas que deben contener un valor en su atributo pista
							List<Casilla> perimetroCasilla = retornarCasillasPerimetro(i,j);
							//sumamos +1 al valor de la pista de cada una de estas casillas, ejecutamos funcion lambda
							perimetroCasilla.forEach( (c) ->c.incrementarPistas() );
							
						}
					}
				}		
	}
	
	//metodo que toma una posición dentro del tablero de juego y retorna una lista con las posiciones aledañas a esa casilla
	private List<Casilla> retornarCasillasPerimetro (int numFila, int numColumna) {
		List<Casilla> listaCasillas = new LinkedList<>();
		
		//Creamos un ciclo que ejecute 8 veces, para retornar las 8 posiciones alrededor de una casilla( o menos en caso de ser una esquina)
				for(int i = 0; i < 8; i++) {
						int posibleFila = numFila;
						int posibleColumna = numColumna; 
						//validamos si la casilla recorrida tiene una trufa envenenada
						switch(i) {
						case 0:posibleFila-- ;break;//posicion arriba
						case 1:posibleFila--;posibleColumna++ ;break;//posicion arriba-derecha
						case 2:posibleColumna++ ;break;//posicion derecha
						case 3:posibleFila++;posibleColumna++ ;break;//posicion derecha-abajo
						case 4:posibleFila++ ;break;//posicion abajo
						case 5:posibleFila++;posibleColumna-- ;break;//posicion abajo-izquierda
						case 6:posibleColumna-- ;break;//posicion izquierda
						case 7:posibleFila--;posibleColumna-- ;break;//posicion izquierda-arriba
						}
						
				//Validamos si la casilla esta dentro del rango posible del tablero para agregarla a la lista
				if ( (posibleFila>=0 && posibleFila<this.matrizTablero.length) && (posibleColumna>=0 && posibleColumna < this.matrizTablero[0].length) ) {
					//agregamos el objeto a la lista de casillas alrededor
					listaCasillas.add(matrizTablero[posibleFila][posibleColumna]);
				}
				}
	    //retornamos la lista de casillas del perimetro de una casilla
		return listaCasillas;
	}	
	
	
	//metodo para retornar una impresion de la matriz del juego en la consola
	public void imprimirTableroConsola() {
		//recorrer cada casilla de nuestra matrizTablero para imprimir el valor dentro de la casilla
				for(int i = 0; i < this.matrizTablero.length; i++) {
					for (int j = 0; j < this.matrizTablero[i].length; j++) {
						
						//imprimir si tiene o no trufa envenenada en la consola
							System.out.print(this.matrizTablero[i][j].isTrufa()?"*":this.matrizTablero[i][j].getPistas()); 
					}System.out.println("");
				}
				
	}
	
	//metodo para generar la lista con todas las trufas envenenadas del tablero
		public void listaCasillasconTrufa() {
			//recorrer cada casilla de nuestra matrizTablero para validar si tienen una trufa envenenada
					for(int i = 0; i < this.matrizTablero.length; i++) {
						for (int j = 0; j < this.matrizTablero[i].length; j++) {
							if (this.matrizTablero[i][j].isTrufa() == true ) {
								this.trufasEnvenenadas.add(matrizTablero[i][j]);
							} 
						
						}
					}
					
		}
	
	//metodo cuando se selecciona una casilla en la interfaz
	public void casillaSeleccionada(int numFila, int numColumna) {
		this.eventoDescubrirCasilla.accept(this.matrizTablero[numFila][numColumna]);
		//ejecutamos el evento de perder juego y retornamos la lista con todas la casillas envenenadas
		if (this.matrizTablero[numFila][numColumna].isTrufa()){
			this.eventoPierdeJuego.accept(this.trufasEnvenenadas);
		}
		//flujo cuando se abre una casilla que no tiene minas alrededor (Pistas 0), se debe abrir todo alrededor con recursividad
		else if (this.matrizTablero[numFila][numColumna].getPistas()==0) {
			//llevar el conteo de casillas abiertas para determinar cuando acaba el juego
			marcarCasillaAbierta(numFila,numColumna);
			List<Casilla> casillasAlrededor = retornarCasillasPerimetro(numFila,numColumna);
			for (Casilla casilla: casillasAlrededor) {
				if(!casilla.isDescubierta()) {
					//casilla.setDescubierta(true);
					casillaSeleccionada(casilla.getFila(),casilla.getColumna());
				}
			}
			
		}else {
			//llevar el conteo de casillas abiertas para determinar cuando acaba el juego
			marcarCasillaAbierta(numFila,numColumna);
		}
		//validamos si despues de seleccionar la casilla, se acabó el juego
		if (partidaGanada()) {
			this.eventoGanaJuego.accept(this.trufasEnvenenadas);
		}
	}
	
	//metodo que incrementa la cantidad de casillas abiertas para determinar cuando el juego termine
	void marcarCasillaAbierta(int numFila, int numColumna) {
		if(!this.matrizTablero[numFila][numColumna].isDescubierta()) {
			this.numCasillasAbiertas++;
			this.matrizTablero[numFila][numColumna].setDescubierta(true);
		}
	}
	
	
	//metodo a ejecutar cuando el juego ha terminado
	boolean partidaGanada() {
		return this.numCasillasAbiertas >= (this.numFilas * this.numColumnas) - this.numTrufas;
	}
	
	
	
	//GETTERS Y SETTERS
	
	//metodo para setear el valor del consumer que avisa cuando se gana el juego
	public void setEventoGanaJuego(Consumer<List<Casilla>> eventoGanaJuego) {
		this.eventoGanaJuego = eventoGanaJuego;
	}
	
	//metodo para setear el valor del consumer que avisa cuando se pierde el juego
	public void setEventoPierdeJuego(Consumer<List<Casilla>> eventoPierdeJuego) {
		this.eventoPierdeJuego = eventoPierdeJuego;
	}
	

	//metodo para setear el objeto coordinador dentro de la clase
	public void setCoordinador(Coordinador miCoordinador) 	{
		this.miCoordinador = miCoordinador;
	}
	
	//retorna el array de trufas envenenadas
		public List<Casilla> getTrufasEnvenenadas() {
			return trufasEnvenenadas;
	}
	
	//metodo para setear el valor del consumer que avisa cuando se revela una casilla
	public void setEventoDescubrirCasilla(Consumer<Casilla> eventoDescubrirCasilla) {
		this.eventoDescubrirCasilla = eventoDescubrirCasilla;
	}
	
	//método para retornar una copia del tablero
	public Casilla[][] getMatrizTablero() {
		return this.matrizTablero;
	}

}
