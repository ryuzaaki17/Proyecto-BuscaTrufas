package co.edu.poli.vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;  // Importar Timer
import javax.swing.border.EmptyBorder;

import co.edu.poli.controlador.Coordinador;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class TableroJuego extends JFrame {

    private static final long serialVersionUID = 1L;
    private Coordinador miCoordinador;    
    private JLabel nombreJugadorLabel;
    private JLabel tiempoLabel;
    private JButton habRevelarTrufas, habEliminarTrufa;
    private JPanel panelCentro;  // Referencia al panel central

    private Timer timer;  // Timer para el cronómetro
    private int segundos;  // Variable para llevar la cuenta del tiempo en segundos

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

    public TableroJuego() {
        setTitle("BuscaTrufas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        getContentPane().setLayout(new BorderLayout());

        // Inicialización del tiempo
        segundos = 0;

        // Configurar el Timer para que se ejecute cada segundo (1000 ms)
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarTiempo();  // Actualiza el tiempo en la interfaz
            }
        });

        // Menú
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu mnNewMenu = new JMenu("Juego");
        menuBar.add(mnNewMenu);

        JMenuItem menuJuegoNuevo = new JMenuItem("Juego Nuevo");
        menuJuegoNuevo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                miCoordinador.nuevoJuego();  // Inicia un nuevo juego
                startTimer();  // Inicia el cronómetro
            }
        });
        mnNewMenu.add(menuJuegoNuevo);

        JMenuItem menuVolverMenu = new JMenuItem("Volver al menu");
        menuVolverMenu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                miCoordinador.VolverMenu();  // Vuelve al menú
            }
        });
        mnNewMenu.add(menuVolverMenu);

        // Panel superior
        JPanel panelSuperior = new JPanel();
        panelSuperior.setLayout(new FlowLayout(FlowLayout.LEFT));
        nombreJugadorLabel = new JLabel("Jugador: ");
        tiempoLabel = new JLabel("Tiempo: 00:00");
        panelSuperior.add(nombreJugadorLabel);
        panelSuperior.add(tiempoLabel);
        getContentPane().add(panelSuperior, BorderLayout.NORTH);

        // Panel derecho
        JPanel panelDerecho = new JPanel();
        panelDerecho.setLayout(new GridLayout(2, 1));
        habRevelarTrufas = new JButton("Revelar \r\nTrufas");
        habRevelarTrufas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                miCoordinador.habilidadRevelarTrufas();  // Activa la habilidad de revelar trufas
            }
        });
        habEliminarTrufa = new JButton("Eliminar\r\nTrufa");
        habEliminarTrufa.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                miCoordinador.habilidadEliminaTrufa();  // Activa la habilidad de eliminar una trufa aleatoria
            }
        });
        panelDerecho.add(habRevelarTrufas);
        panelDerecho.add(habEliminarTrufa);
        getContentPane().add(panelDerecho, BorderLayout.EAST);

        // Panel central (inicializado correctamente)
        panelCentro = new JPanel(new BorderLayout()); // Usando BorderLayout para que los componentes se ajusten automáticamente
        panelCentro.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(panelCentro, BorderLayout.CENTER);
    }

    // Este método se llama cada segundo por el Timer
    public void actualizarTiempo() {
        segundos++;  // Incrementa los segundos
        int minutos = segundos / 60;
        int seg = segundos % 60;
        String tiempoFormateado = String.format("%02d:%02d", minutos, seg);
        tiempoLabel.setText("Tiempo: " + tiempoFormateado);  // Actualiza la etiqueta de tiempo
    }

    // Método para iniciar el cronómetro (Timer)
    public void startTimer() {
        segundos = 0;  // Resetea el contador de segundos
        timer.start();  // Inicia el cronómetro
    }

    public void setTiempo(String tiempo) {
        tiempoLabel.setText("Tiempo: " + tiempo);
    }

    public void setNombreJugador(String nombre) {
        nombreJugadorLabel.setText("Jugador: " + nombre);
    }

    public void setCoordinador(Coordinador miCoordinador) {
        this.miCoordinador = miCoordinador;
    }

    public void setTablero(JPanel contentPaneTablero) {
        // Limpiar el panelCentro antes de agregar nuevos componentes
        panelCentro.removeAll();  // Elimina todos los componentes existentes

        // Ajustar el tamaño preferido para el panelCentro (opcional)
        panelCentro.setPreferredSize(new Dimension(300, 300)); // Puedes ajustar el tamaño como desees

        // Agregar el nuevo tablero al panelCentro
        panelCentro.add(contentPaneTablero, BorderLayout.CENTER);

        // Volver a calcular el layout y redibujar el panel
        panelCentro.revalidate();
        panelCentro.repaint();
    }
}
