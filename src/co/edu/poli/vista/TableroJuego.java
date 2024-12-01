package co.edu.poli.vista;

import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.function.Consumer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import co.edu.poli.controlador.Coordinador;
import co.edu.poli.modelo.Casilla;
import co.edu.poli.modelo.Tablero;
import javax.swing.JToolBar;
import javax.swing.JList;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;

public class TableroJuego extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Coordinador miCoordinador;	
	private int numFilas = 6;
	private int numColumnas = 6;
	private int porcentajeTrufas = 20;
	private JButton [][] tablero ;
	private Tablero tableroModelo;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TableroJuego frame = new TableroJuego();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TableroJuego() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Juego");
		menuBar.add(mnNewMenu);
		
		JMenuItem menuJuegoNuevo = new JMenuItem("Juego Nuevo");
		
		//evento para crear un nuevo juego desde el menu
		menuJuegoNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nuevoJuego();
			}
		});
		mnNewMenu.add(menuJuegoNuevo);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		nuevoJuego();
	}
	
	//Metodo para crear un Nuevo Juego()
	public void nuevoJuego() {
		limpiezaElementos();
		cargarControles(contentPane);
		crearTableroModelo();
		habilidadRevelarTrufas();
		repaint();
	}
	
	//Limpieza de elementos de la pantalla al iniciar un nuevo juego
	public void limpiezaElementos() {
		if(this.tablero!= null) {
			for (int i = 0; i < this.tablero.length;i++) {
				for (int j = 0; j < this.tablero[i].length; j++) {
					if(this.tablero[i][j] != null) {
						getContentPane().remove(this.tablero[i][j]);
					}
				}
				
			}
		}
	}
	
	
	//metodo para setear el objeto coordinador dentro de la clase
	public void setCoordinador(Coordinador miCoordinador) 	{
		this.setMiCoordinador(miCoordinador);
	}
	/**
	 * Create the frame.
	 * @return 
	 */
	
	public void VentanaPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 900);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		cargarControles(contentPane);
		
	}

	private void cargarControles(JPanel contentPane) {
	    this.tablero = new JButton[this.numFilas][this.numColumnas];
		// Set the layout manager to GridLayout for automatic positioning
	    contentPane.setLayout(new GridLayout(this.numFilas, this.numColumnas, 1, 1)); // Adjust spacing as needed
		int posicionX=25;
		int posicionY=25;
	    int anchoBoton=30;
	    int altoBoton=30;
		
		//recorrer cada posicion de nuestro Tablero para crear dentro de este un objeto Jbutton
				for(int i = 0; i < this.tablero.length; i++) {
					
					for (int j = 0; j < this.tablero[i].length; j++) {
						
						//asignarle valores a nuestro objeto JButton
						this.tablero[i][j] = new JButton();
						this.tablero[i][j].setName(i + "," + j);  // Optional: Set button names
		                this.tablero[i][j].setBorder(null);  // Optional: Remove button border
		                // Add functionality to the button (e.g., handle clicks)
		                int nf = i;
		                int nc = j;
						this.tablero[i][j].addActionListener(e -> clicCasilla(nf,nc));
		             // Removed manual positioning logic - handled by GridLayout
		                contentPane.add(this.tablero[i][j]);
		                
					}
				}
				//seteamos la ventana para que se vea completa sin importar la cantidad de casillas
				//this.setSize(this.tablero[this.numFilas-1][this.numColumnas-1].getX() + this.tablero[this.numFilas-1][this.numColumnas-1].getWidth()+30,
				//this.tablero[this.numFilas][this.numColumnas].getY() + this.tablero[this.numFilas][this.numColumnas].getHeight()+70);
	}
	
	//mover al controlador despues
	
	//metodo que valida si se ha generado el evento de perdida (Clickear sobre una trufa)
	private void crearTableroModelo() {
		tableroModelo = new Tablero(numFilas ,numColumnas, porcentajeTrufas);
		//evento al perder el juego
		tableroModelo.setEventoPierdeJuego( (t) -> {
			for ( Casilla casillaConTrufa : t) {
				int fila = casillaConTrufa.getFila();
				int columna = casillaConTrufa.getColumna();
				this.tablero[fila][columna].setText("*");
			}
		});
		//evento al ganar el juego
				tableroModelo.setEventoGanaJuego( (t) -> {
					for ( Casilla casillaConTrufa : t) {
						int fila = casillaConTrufa.getFila();
						int columna = casillaConTrufa.getColumna();
						this.tablero[fila][columna].setText(":)");
					}
				});
		//evento al activar una casilla
		tableroModelo.setEventoDescubrirCasilla( (t) -> {
			Casilla casilla = t;
			this.tablero[casilla.getFila()][casilla.getColumna()].setEnabled(false);
			this.tablero[casilla.getFila()][casilla.getColumna()].setText(casilla.getPistas()==0?"":casilla.getPistas()+"");
		});
		tableroModelo.imprimirTableroConsola();
		
	}
	
	
	//metodo para la habilidad de revelar las trufas envenenadas durante un breve momento
	private void habilidadRevelarTrufas() {
		List<Casilla> trufasEnvenenadas = new LinkedList<>();
		trufasEnvenenadas = this.tableroModelo.getTrufasEnvenenadas();
		List<Casilla> trufasARevelar = new LinkedList<>(trufasEnvenenadas);
		System.out.println(trufasEnvenenadas);
		//final List<Casilla> trufasARevelar = new LinkedList<>(trufasEnvenenadas); // Copia de la lista
		
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
		timer.schedule(revelaTrufas,1);
		timer.schedule(ocultaTrufas,6000);
		
	}
	
	//evento al clickear una casilla para mostrarla en consola
	 private void clicCasilla(int numFila, int numColumna) {
	        System.out.println("Boton clickeado en posicion: (" + numFila + ", " + numColumna + ")");
	        this.tableroModelo.casillaSeleccionada(numFila, numColumna);
	 }

	public void setMiCoordinador(Coordinador miCoordinador) {
		this.miCoordinador = miCoordinador;
	}

}
