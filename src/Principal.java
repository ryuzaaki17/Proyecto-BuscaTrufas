import co.edu.poli.controlador.Coordinador;
import co.edu.poli.modelo.Jugador;
import co.edu.poli.modelo.Tablero;
import co.edu.poli.vista.TableroJuego;
import co.edu.poli.vista.VentanaPrincipal;

public class Principal {

    public static void main(String[] args) {
        // Este es el punto de entrada principal de la aplicación, 
        // donde se crea y configura todo el flujo de la aplicación Modelo-Vista-Controlador (MVC).
        
        // Paso 1: Crear instancias del modelo, vista y controlador
        
        // Crear el controlador que gestionará las interacciones entre las vistas y los modelos
        Coordinador miCoordinador = new Coordinador();

        // Crear las vistas. Estas son las ventanas donde se interactúa con el usuario
        VentanaPrincipal miVentanaPrincipal = new VentanaPrincipal();
        TableroJuego miTableroJuego = new TableroJuego();

        // Crear los modelos que contienen la lógica del juego y los datos
        // Inicializamos el tablero y los jugadores
        // Tablero(0,0,0) podría ser un lugar para especificar el tamaño del tablero, las trufas, etc.
        Tablero miTablero = new Tablero(0,0,0);
        Jugador miJugador1 = new Jugador("Jugador 1", 1, 0, 0); // Jugador 1 con nombre, y valores iniciales de puntaje, tiempo, etc.
        Jugador miJugador2 = new Jugador("Jugador 2", 1, 0, 0); // Jugador 2 con valores iniciales similares al Jugador 1

        // Paso 2: Establecer la comunicación entre las vistas, el controlador y los modelos

        // Se establece el Coordinador para cada componente (modelo y vista)
        miVentanaPrincipal.setCoordinador(miCoordinador);  // La vista de la ventana principal conoce al controlador
        miTableroJuego.setCoordinador(miCoordinador);  // La vista del tablero conoce al controlador
        miTablero.setCoordinador(miCoordinador);  // El modelo del tablero conoce al controlador
        miJugador1.setCoordinador(miCoordinador);  // El Jugador 1 conoce al controlador
        miJugador2.setCoordinador(miCoordinador);  // El Jugador 2 conoce al controlador

        // Paso 3: El controlador configura las vistas y los modelos

        // Configuración final del controlador con las vistas y modelos relevantes
        miCoordinador.setVentanaPrincipal(miVentanaPrincipal);  // El controlador conoce la ventana principal
        miCoordinador.setTablero(miTablero);  // El controlador conoce el tablero de juego
        miCoordinador.setJugador1(miJugador1);  // El controlador conoce al Jugador 1
        miCoordinador.setJugador2(miJugador2);  // El controlador conoce al Jugador 2
        miCoordinador.setTableroJuego(miTableroJuego);  // El controlador conoce la vista del tablero de juego
        
        // Paso 4: Mostrar la ventana principal

        // Hacemos visible la ventana principal para que el usuario pueda interactuar
        miVentanaPrincipal.setVisible(true);
    }

}
