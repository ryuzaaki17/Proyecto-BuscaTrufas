package co.edu.poli.vista;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class VentanaUnJugador extends JFrame {
    // ... otros atributos ...

    public VentanaUnJugador(JPanel tablero) {
        // ... código existente ...

        // Crear panel para la información del juego
        JPanel panelInfo = new JPanel(new FlowLayout());
        JLabel lblJugador = new JLabel("Jugador: ");
        JLabel lblTiempo = new JLabel("Tiempo: 0s");
        JButton btnReiniciar = new JButton("Reiniciar");
        JButton btnBandera = new JButton("Bandera");
        panelInfo.add(lblJugador);
        panelInfo.add(lblTiempo);
        panelInfo.add(btnReiniciar);
        panelInfo.add(btnBandera);

        Container panelJuego = null;
		// Agregar paneles al panel principal
        panelJuego.add(panelInfo, BorderLayout.NORTH);
        panelJuego.add(tablero, BorderLayout.CENTER);

        // ... resto del código ...

        // Iniciar el contador de tiempo
        Timer timer = new Timer(1000, e -> {
            // Incrementar el tiempo y actualizar el label
        });
        timer.start();
    }
}