package co.edu.poli.controlador;

import java.awt.GridLayout;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import co.edu.poli.modelo.Casilla;
import co.edu.poli.modelo.Jugador;
import co.edu.poli.modelo.Tablero;
import co.edu.poli.modelo.Turnos;
import co.edu.poli.vista.TableroJuego;
import co.edu.poli.vista.VentanaPrincipal;


//Clase coordinadora, es la que relaciona lo que muestra la interfaz con lo que procesa el modelo logico
public class Coordinador {
	
	private VentanaPrincipal miVentanaPrincipal;  //Ventana principal del juego
	private TableroJuego miTableroJuego;          //Ventana con el tablero mostrado en el modo de 1 Jugador
	private Turnos miTurno;						  //Objeto miTurno
	private Jugador miJugador1;				      //Objeto con la información del jugador 1
	private Jugador miJugador2;					  //Objeto con la información del jugador 1
	private JButton [][] tablero ;				  //Objeto Matriz que contiene todos los botones del tablero
	private Tablero tableroModelo;				  //Objeto matriz que contiene Objetos tipo Casilla
	private JPanel contentPaneTablero;            //Jpanel donde se genera el tablero del juego
	private int numFilas = 3;					  //Cantidad de filas que debe tener el tablero del juego
	private int numColumnas = 3;				  //Cantidad de columnas que debe tener el tablero del juego
	private int porcentajeTrufas = 3;			  //Porcentaje de trufas que debe tener el tablero del juego
	private int cantidadHabilidadEliminarTrufas;  //Cantidad de cargas de la habilidad 1 (habilidad de descartar trufas envenenadas)
	private int cantidadHabilidadRevelarTrufas;   //Cantidad de cargas de la habilidad 2 (habilidad de mostrar las trufas por un breve momento)
	
    //setear un objeto Ventana dentro de la clase coordinadora
	public void setVentanaPrincipal(VentanaPrincipal miVentanaPrincipal) {
		this.miVentanaPrincipal = miVentanaPrincipal;}
	
	//setear un objeto Tablero dentro de la clase coordinadora
	public void setTurno(Turnos miTurno) {
		this.miTurno= miTurno;}
	
	//setear un objeto Turno dentro de la clase coordinadora
	public void setTablero(Tablero miTablero) {
	}
	
	//setear un objeto Jugador1 dentro de la clase coordinadora
	public void setJugador1(Jugador miJugador1) {
		this.miJugador1 = miJugador1;
	}
	
	//setear un objeto Jugador2 dentro de la clase coordinadora
	public void setJugador2(Jugador miJugador2) {
		this.miJugador2 = miJugador2;
	}
	
	//setear un objeto miTableroJuego dentro de la clase coordinadora
	public void setTableroJuego(TableroJuego miTableroJuego) {
		this.miTableroJuego = miTableroJuego;	}
	
	//metodo para crear un juego de partida de 1 jugador
	public void CrearJuego1(String nombrejugador1, int numFilas, int numColumnas,int porcentajeTrufas) {
		//Ocultamos la ventana principal
		this.miVentanaPrincipal.setVisible(false);
		//Modificamos la especificacion del tablero de acuerdo a los parametros de entrada
		this.numFilas = numFilas;
		this.numColumnas = numColumnas;
		this.porcentajeTrufas = porcentajeTrufas;
		this.miJugador1.setNombreJugador(nombrejugador1);
		setJugador();
		nuevoJuego();
		//Mostramos el tablero en pantalla
		this.miTableroJuego.setVisible(true);
		
	}
	
	//Limpieza de elementos de la pantalla al iniciar un nuevo juego
	public void limpiezaElementos() {
		if(this.tablero!= null) {
			for (int i = 0; i < this.tablero.length;i++) {
				for (int j = 0; j < this.tablero[i].length; j++) {
					if(this.tablero[i][j] != null) {
							this.miTableroJuego.getContentPane().remove(this.tablero[i][j]);
					}
				}
					
			}
		}
	}
		
	//Generar la matriz de JButtons para el juego
	public void cargarControles() {
		//Crear un Jpanel para alojar la matriz de botones del juego
		this.contentPaneTablero = new JPanel();
		this.contentPaneTablero.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.tablero = new JButton[this.numFilas][this.numColumnas];
		//Seleccionamos GridLayout para acomodar los elementos dentro de la matriz de manera automatica
		this.contentPaneTablero.setLayout(new GridLayout(this.numFilas, this.numColumnas, 1, 1)); 
			
		//Recorrer cada posicion de nuestro Tablero para crear dentro de este un objeto Jbutton
				for(int i = 0; i < this.tablero.length; i++) {
					for (int j = 0; j < this.tablero[i].length; j++) {
						//Asignarle valores a nuestro objeto JButton
						this.tablero[i][j] = new JButton();
						this.tablero[i][j].setName(i + "," + j);  // Darle un nombre al boton
			            this.tablero[i][j].setBorder(null);  // Notones sin borde
			            // Retornar las posiciones de numFila y numColumna
			            int nf = i;
			            int nc = j;
			            // Añadimos un evento a cada boton
			            this.tablero[i][j].addActionListener(e -> clicCasilla(nf,nc));
			            
			            // Añadimos el boton a la matriz de botones
			            contentPaneTablero.add(this.tablero[i][j]);
			                
					}
				}
					//seteamos la ventana para que se vea completa sin importar la cantidad de casillas
					//this.setSize(this.tablero[this.numFilas-1][this.numColumnas-1].getX() + this.tablero[this.numFilas-1][this.numColumnas-1].getWidth()+30,
					//this.tablero[this.numFilas][this.numColumnas].getY() + this.tablero[this.numFilas][this.numColumnas].getHeight()+70);
	}
		
	//evento al clickear una casilla para mostrarla en consola
	private void clicCasilla(int numFila, int numColumna) {
		    System.out.println("Boton clickeado en posicion: (" + numFila + ", " + numColumna + ")");
		    this.tableroModelo.casillaSeleccionada(numFila, numColumna);
	}
		 
	//Metodo para crear un Nuevo Juego()
		public void nuevoJuego() {
			this.miJugador1.setTiempoAcumulado(0); //Seteamos el tiempo en 0
			this.miJugador1.setPuntuacion(0);;     //Seteamos el puntaje en 0
			this.cantidadHabilidadEliminarTrufas = 3; //Seteamos las cargas de habilidad en 3
			this.cantidadHabilidadRevelarTrufas = 2;  //Seteamos las cargas de habilidad en 2
			//limpiezaElementos();
			cargarControles(); //Ejecutamos el método que carga la matriz de botones
			crearTableroModelo(); //Creamos la matriz tablero de objetos casilla
			this.miTableroJuego.setTablero(contentPaneTablero); //Ingresamos el Jpanel del tablero del juego dentro de la vista
			this.miTableroJuego.startTimer();  //Iniciamos el timer
		}
		
		
		private void crearTableroModelo() {
			//Crea el tablero del juego
			tableroModelo = new Tablero(this.numFilas ,this.numColumnas, this.porcentajeTrufas);
			
			//Metodo que valida si se ha generado el evento de perdida (Clickear sobre una trufa)
			tableroModelo.setEventoPierdeJuego( (t) -> {
				for ( Casilla casillaConTrufa : t) {
					int fila = casillaConTrufa.getFila();
					int columna = casillaConTrufa.getColumna();
					//Si se pierde el juego, se coloca un asterisco sobre las casillas con trufa
					this.tablero[fila][columna].setText("*");
				}
				// Mostrar el cuadro de confirmación con un mensaje
	            int respuesta = JOptionPane.showConfirmDialog(this.contentPaneTablero, "Perdiste. ¿Quieres reintentarlo?", "Juego Terminado", JOptionPane.YES_NO_OPTION);
	            
	            // Verificar cuál fue la opción seleccionada
	            if (respuesta == JOptionPane.YES_OPTION) {
	                System.out.println("Jugador aceptó.");
	                //Si el jugador acepta, se inicia un nuevo juego
	                nuevoJuego();
	            } else {
	                System.out.println("Jugador canceló.");
	                //Si el jugador cancela, se vuelve al menu principal
	                VolverMenu();
	            }
			});
			//Metodo que valida si se ha generado el evento de victoria(no hay casillas sin trufa sin activar)
			tableroModelo.setEventoGanaJuego( (t) -> {
				for ( Casilla casillaConTrufa : t) {
					int fila = casillaConTrufa.getFila();
					int columna = casillaConTrufa.getColumna();
					//Cuando no quedan casillas sin trufa por activar, se coloca una carita feliz sobre las casillas con trufa
					this.tablero[fila][columna].setText(":)");
				}
				// Mostrar el cuadro de confirmación con un mensaje
			    int respuesta = JOptionPane.showConfirmDialog(this.contentPaneTablero, "Ganaste. ¿Quieres reintentarlo?", "Juego Terminado", JOptionPane.YES_NO_OPTION);
			    // Verificar cuál fue la opción seleccionada
			    if (respuesta == JOptionPane.YES_OPTION) {
			        System.out.println("Jugador aceptó.");
			        //Si el jugador acepta, se inicia un nuevo juego
			        nuevoJuego();
			    } else {
			        System.out.println("Jugador canceló.");
			      //Si el jugador cancela, se vuelve al menu principal
			        VolverMenu();
			    }
			});
			//Se activa el evento cuando se clickea una casilla
			tableroModelo.setEventoDescubrirCasilla( (t) -> {
				Casilla casilla = t;
				//Se deshabilita el boton
				this.tablero[casilla.getFila()][casilla.getColumna()].setEnabled(false);
				//Se muestran la cantidad de pistas alrededor de la casilla
				this.tablero[casilla.getFila()][casilla.getColumna()].setText(casilla.getPistas()==0?"":casilla.getPistas()+"");
			});
			//Mostrar el tablero en consola
			tableroModelo.imprimirTableroConsola();
			
		}
		
		//metodo para la habilidad de revelar las trufas envenenadas durante un breve momento
		public void habilidadRevelarTrufas() {
			if (this.cantidadHabilidadRevelarTrufas > 0) {	
				List<Casilla> trufasEnvenenadas = new LinkedList<>();
				trufasEnvenenadas = this.tableroModelo.getTrufasEnvenenadas();
				List<Casilla> trufasARevelar = new LinkedList<>(trufasEnvenenadas);
				System.out.println(trufasEnvenenadas);
			
				//Crear el timer durante 6  segundos que revele las casillas envenenadas
			
				//evento que revela las casillas con trufa
				TimerTask revelaTrufas = new TimerTask() {
					public void run() {
						for (Casilla casilla : trufasARevelar) {
							tablero[casilla.getFila()][casilla.getColumna()].setText("*");
						}
						System.out.println("Se revelan las trufas por 6 segundos");
					}
				};
				//evento que oculta las casillas
				TimerTask ocultaTrufas = new TimerTask() {
					public void run() {
						for (Casilla casilla : trufasARevelar) {
							tablero[casilla.getFila()][casilla.getColumna()].setText("");
						}
						System.out.println("Se ocultan las trufas por 6 segundos");
					}
				};
				Timer timer = new Timer();
				//Activamos el evento de revelar trufas
				timer.schedule(revelaTrufas,1);
				//despues de 6 segundos,volvemos a ocultar las trufas
				timer.schedule(ocultaTrufas,6000);
				this.cantidadHabilidadRevelarTrufas--;
			}else {
				System.out.println("No hay mas cargas de la habilidad disponible");
			}
		}
		
		//Metodo para volver al menu principal
		public void VolverMenu() {
			//Ocultamos la ventana del tablero
			this.miTableroJuego.setVisible(false);
			//Mostramos la ventana del menu principal
			this.miVentanaPrincipal.setVisible(true);	
		}
		
		//Habilidad que Activa una casilla que contenga una Trufa que no este activada con anterioridad
		public void habilidadEliminaTrufa() {
			if (this.cantidadHabilidadEliminarTrufas > 0) {
				List<Casilla> trufasEnvenenadas = new LinkedList<>();
				trufasEnvenenadas = this.tableroModelo.getTrufasEnvenenadas();
			
				// Validamos que aun existan cargas de la habilidad
				while(true) {
					Random random = new Random();
					int index = random.nextInt(trufasEnvenenadas.size());  // Genera un índice aleatorio
					Casilla trufaAleatoria = trufasEnvenenadas.get(index);  // Obtiene el elemento en ese índice
					// Validamos que la casilla no se haya revelado anteriormente
					if (!trufaAleatoria.isDescubierta()) {
						trufaAleatoria.setDescubierta(true);
						this.tablero[trufaAleatoria.getFila()][trufaAleatoria.getColumna()].setEnabled(false);
						this.tablero[trufaAleatoria.getFila()][trufaAleatoria.getColumna()].setText("*");
						// Imprimir el elemento seleccionado
						System.out.println("Trufa deshabilitada en posicion: ["+ trufaAleatoria.getFila() +"] [" + trufaAleatoria.getColumna() + "]");
					
						// Reducimos en 1 el numero de cargas de la habilidad
						this.cantidadHabilidadEliminarTrufas--;
						break;
					}
				}
				}else {
					System.out.println("No hay mas cargas de la habilidad disponible");
				}  	
		}
			
		//Setear el nombre del jugador en la ventana
		 public void setJugador() {
		        this.miTableroJuego.setNombreJugador(this.miJugador1.getNombreJugador());;
		 }
}
