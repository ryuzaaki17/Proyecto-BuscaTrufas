package co.edu.poli.vista;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.border.EmptyBorder;

public class VentanaPrincipal extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JPanel Menu;
    private JPanel configuracionPanel; // El JPanel que se mostrará cuando se seleccione "Un Jugador"
    private JPanel configuracionPanelDosJugadores; // Panel para la opción de Dos Jugadores
    private JTextField filasTextField;
    private JTextField columnasTextField;
    private JSlider porcentajeSlider;
    private JLabel lblPorcentajeValor; // Etiqueta para mostrar el porcentaje en tiempo real
    private JTextField nombreJugadorTextField;
    private JTextField nombreJugador1TextField;
    private JTextField nombreJugador2TextField;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    VentanaPrincipal frame = new VentanaPrincipal();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public VentanaPrincipal() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 400); // Aumenté el tamaño para ajustarse a los nuevos componentes
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Ventana de opciones principal
        Menu = new JPanel();
        Menu.setLocation(0, 0);
        Menu.setLayout(null);
        Menu.setVisible(true);
        Menu.setBounds(0, 0, 450, 400); // Ajustamos el tamaño del panel

        // Botón "Un Jugador"
        JButton btnUnJugador = new JButton("Un Jugador");
        btnUnJugador.setBounds(50, 50, 150, 30);
        btnUnJugador.addActionListener(e -> mostrarConfiguracionPanelUnJugador()); // Muestra el panel de configuración de Un Jugador
        Menu.add(btnUnJugador);

        // Botón "Dos Jugadores"
        JButton btnDosJugadores = new JButton("Dos Jugadores");
        btnDosJugadores.setBounds(50, 100, 150, 30);
        btnDosJugadores.addActionListener(e -> mostrarConfiguracionPanelDosJugadores()); // Muestra el panel de configuración de Dos Jugadores
        Menu.add(btnDosJugadores);

        // Botón "Salir"
        JButton btnSalir = new JButton("Salir");
        btnSalir.setBounds(50, 150, 150, 30);
        btnSalir.addActionListener(e -> System.exit(0)); // Cierra la aplicación
        Menu.add(btnSalir);

        contentPane.add(Menu);

        // Panel de configuración para "Un Jugador" (inicialmente no visible)
        configuracionPanel = new JPanel();
        configuracionPanel.setBounds(0, 0, 450, 400);
        configuracionPanel.setLayout(null);
        configuracionPanel.setVisible(false);

        JLabel lblNombreJugador = new JLabel("Nombre del Jugador:");
        lblNombreJugador.setBounds(50, 50, 150, 30);
        configuracionPanel.add(lblNombreJugador);

        nombreJugadorTextField = new JTextField();
        nombreJugadorTextField.setBounds(200, 50, 150, 30);
        configuracionPanel.add(nombreJugadorTextField);
        nombreJugadorTextField.setColumns(10);

        JLabel lblFilas = new JLabel("Filas:");
        lblFilas.setBounds(50, 100, 80, 30);
        configuracionPanel.add(lblFilas);

        filasTextField = new JTextField();
        filasTextField.setBounds(150, 100, 100, 30);
        configuracionPanel.add(filasTextField);
        filasTextField.setColumns(10);

        JLabel lblColumnas = new JLabel("Columnas:");
        lblColumnas.setBounds(50, 150, 80, 30);
        configuracionPanel.add(lblColumnas);

        columnasTextField = new JTextField();
        columnasTextField.setBounds(150, 150, 100, 30);
        configuracionPanel.add(columnasTextField);
        columnasTextField.setColumns(10);

        JLabel lblPorcentaje = new JLabel("Porcentaje:");
        lblPorcentaje.setBounds(50, 200, 80, 30);
        configuracionPanel.add(lblPorcentaje);

        porcentajeSlider = new JSlider();
        porcentajeSlider.setBounds(150, 200, 200, 30);
        porcentajeSlider.setMinimum(5); // Establecer el valor mínimo en 5
        porcentajeSlider.setMaximum(25); // Establecer el valor máximo en 25
        porcentajeSlider.setValue(5);
        configuracionPanel.add(porcentajeSlider);

        // Etiqueta para mostrar el valor del porcentaje
        lblPorcentajeValor = new JLabel("5%");
        lblPorcentajeValor.setBounds(360, 200, 50, 30);
        configuracionPanel.add(lblPorcentajeValor);

        // Agregar un ChangeListener al JSlider
        porcentajeSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                int porcentaje = porcentajeSlider.getValue();
                lblPorcentajeValor.setText(porcentaje + "%"); // Actualizar la etiqueta con el valor del slider
            }
        });

        JButton btnAceptarUnJugador = new JButton("Aceptar");
        btnAceptarUnJugador.setBounds(50, 250, 100, 30);
        btnAceptarUnJugador.addActionListener(e -> {
            try {
                String nombre = nombreJugadorTextField.getText();
                int filas = Integer.parseInt(filasTextField.getText());
                int columnas = Integer.parseInt(columnasTextField.getText());
                int porcentaje = porcentajeSlider.getValue();
                JOptionPane.showMessageDialog(null, "Configuración aceptada para " + nombre + ": Filas: " + filas + ", Columnas: " + columnas + ", Porcentaje: " + porcentaje);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Por favor, ingrese valores válidos.");
            }
        });
        configuracionPanel.add(btnAceptarUnJugador);

        JButton btnCancelarUnJugador = new JButton("Cancelar");
        btnCancelarUnJugador.setBounds(170, 250, 100, 30);
        btnCancelarUnJugador.addActionListener(e -> ocultarConfiguracionPanel());
        configuracionPanel.add(btnCancelarUnJugador);

        contentPane.add(configuracionPanel);

        // Panel de configuración para "Dos Jugadores" (inicialmente no visible)
        configuracionPanelDosJugadores = new JPanel();
        configuracionPanelDosJugadores.setBounds(0, 0, 450, 400);
        configuracionPanelDosJugadores.setLayout(null);
        configuracionPanelDosJugadores.setVisible(false);

        JLabel lblNombreJugador1 = new JLabel("Nombre Jugador 1:");
        lblNombreJugador1.setBounds(50, 50, 150, 30);
        configuracionPanelDosJugadores.add(lblNombreJugador1);

        nombreJugador1TextField = new JTextField();
        nombreJugador1TextField.setBounds(200, 50, 150, 30);
        configuracionPanelDosJugadores.add(nombreJugador1TextField);
        nombreJugador1TextField.setColumns(10);

        JLabel lblNombreJugador2 = new JLabel("Nombre Jugador 2:");
        lblNombreJugador2.setBounds(50, 100, 150, 30);
        configuracionPanelDosJugadores.add(lblNombreJugador2);

        nombreJugador2TextField = new JTextField();
        nombreJugador2TextField.setBounds(200, 100, 150, 30);
        configuracionPanelDosJugadores.add(nombreJugador2TextField);
        nombreJugador2TextField.setColumns(10);

        JLabel lblFilas2 = new JLabel("Filas:");
        lblFilas2.setBounds(50, 150, 80, 30);
        configuracionPanelDosJugadores.add(lblFilas2);

        JTextField filas2TextField = new JTextField();
        filas2TextField.setBounds(150, 150, 100, 30);
        configuracionPanelDosJugadores.add(filas2TextField);
        filas2TextField.setColumns(10);

        JLabel lblColumnas2 = new JLabel("Columnas:");
        lblColumnas2.setBounds(50, 200, 80, 30);
        configuracionPanelDosJugadores.add(lblColumnas2);

        JTextField columnas2TextField = new JTextField();
        columnas2TextField.setBounds(150, 200, 100, 30);
        configuracionPanelDosJugadores.add(columnas2TextField);
        columnas2TextField.setColumns(10);

        JLabel lblPorcentaje2 = new JLabel("Porcentaje:");
        lblPorcentaje2.setBounds(50, 250, 80, 30);
        configuracionPanelDosJugadores.add(lblPorcentaje2);

        JSlider porcentajeSlider2 = new JSlider();
        porcentajeSlider2.setBounds(150, 250, 200, 30);
        porcentajeSlider2.setMinimum(5); // Establecer el valor mínimo en 5
        porcentajeSlider2.setMaximum(25); // Establecer el valor máximo en 25
        porcentajeSlider2.setValue(5);
        configuracionPanelDosJugadores.add(porcentajeSlider2);

        JLabel lblPorcentajeValor2 = new JLabel("5%");
        lblPorcentajeValor2.setBounds(360, 250, 50, 30);
        configuracionPanelDosJugadores.add(lblPorcentajeValor2);

        porcentajeSlider2.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                int porcentaje = porcentajeSlider2.getValue();
                lblPorcentajeValor2.setText(porcentaje + "%");
            }
        });

        JButton btnAceptarDosJugadores = new JButton("Aceptar");
        btnAceptarDosJugadores.setBounds(50, 300, 100, 30);
        btnAceptarDosJugadores.addActionListener(e -> {
            try {
                String nombre1 = nombreJugador1TextField.getText();
                String nombre2 = nombreJugador2TextField.getText();
                int filas = Integer.parseInt(filas2TextField.getText());
                int columnas = Integer.parseInt(columnas2TextField.getText());
                int porcentaje = porcentajeSlider2.getValue();
                JOptionPane.showMessageDialog(null, "Configuración aceptada para: " + nombre1 + " y " + nombre2 + ": Filas: " + filas + ", Columnas: " + columnas + ", Porcentaje: " + porcentaje);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Por favor, ingrese valores válidos.");
            }
        });
        configuracionPanelDosJugadores.add(btnAceptarDosJugadores);

        JButton btnCancelarDosJugadores = new JButton("Cancelar");
        btnCancelarDosJugadores.setBounds(170, 300, 100, 30);
        btnCancelarDosJugadores.addActionListener(e -> ocultarConfiguracionPanelDosJugadores());
        configuracionPanelDosJugadores.add(btnCancelarDosJugadores);

        contentPane.add(configuracionPanelDosJugadores);
    }

    // Muestra el panel de configuración de "Un Jugador"
    private void mostrarConfiguracionPanelUnJugador() {
        Menu.setVisible(false); // Ocultamos el menú
        configuracionPanel.setVisible(true); // Mostramos el panel de un jugador
    }

    // Oculta el panel de configuración de "Un Jugador"
    private void ocultarConfiguracionPanel() {
        configuracionPanel.setVisible(false);
        Menu.setVisible(true);
    }

    // Muestra el panel de configuración de "Dos Jugadores"
    private void mostrarConfiguracionPanelDosJugadores() {
        Menu.setVisible(false); // Ocultamos el menú
        configuracionPanelDosJugadores.setVisible(true); // Mostramos el panel de dos jugadores
    }

    // Oculta el panel de configuración de "Dos Jugadores"
    private void ocultarConfiguracionPanelDosJugadores() {
        configuracionPanelDosJugadores.setVisible(false);
        Menu.setVisible(true);
    }
}
