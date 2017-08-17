import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * CalebSoft

 * @author Carlos E. A. Torres
 * @email catencio@episunsa.edu.pe

 * Copyright 2016.
 */

/**
 * @author carlos
 *
 */
public class Juego {
	private static Scanner sc = new Scanner(System.in);

	private static List<Robot> jugadores = new ArrayList<>();

	public static void initJugadores() {
		jugadores.add(new Descompuesto());
	}

	public static void main(String[] args) {

		initJugadores();

		Robot jugador1 = null;
		Robot jugador2 = null;
		Tablero tablero = null;

		int opcion = printMenu();
		while (opcion != 0) {
			switch (opcion) {
			case 1:
				System.out.println("Escoga el Jugador1:  ");
				jugador1 = escojaJugador();

				System.out.println("Escoga el Jugador2:  ");
				jugador2 = escojaJugador();

				if (jugador1 == jugador2) {
					Class jugadorClass = jugador1.getClass();
					try {
						jugador2 = (Robot) jugadorClass.newInstance();
					} catch (InstantiationException | IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				tablero = Tablero.getInstance();
				tablero.colocaJugadores(jugador1, jugador2);

				PartidaSimple partidaSimple = new PartidaSimple(jugador1, jugador2);
				(new Thread(partidaSimple)).start();

				break;
			case 2:
				System.out.println("Escoga el Jugador1:  ");
				jugador1 = escojaJugador();

				System.out.println("Escoga el Jugador2:  ");
				jugador2 = escojaJugador();

				if (jugador1 == jugador2) {
					Class jugadorClass = jugador1.getClass();
					try {
						jugador2 = (Robot) jugadorClass.newInstance();
					} catch (InstantiationException | IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				tablero = Tablero.getInstance();
				tablero.colocaJugadores(jugador1, jugador2);

				PartidaMultiple partidaMultiple = new PartidaMultiple(jugador1, jugador2);
				partidaMultiple.jugar();
				partidaMultiple.imprimirResultado();

				break;
			case 3:
				PartidaTorneo partidaTorneo = new PartidaTorneo(jugadores);
				partidaTorneo.jugar();
				partidaTorneo.imprimirResultados();
				break;
			}

			System.out.println("Digite algo para continuar");
			sc.next();
			opcion = printMenu();
		}
	}

	/**
	 * Este metodo escoje un jugador. Tiene cuidado de escoger apenas un jugador
	 * de la lista.
	 * 
	 * @return
	 */
	private static Robot escojaJugador() {
		UtilsJuego.limpiarPantalla();
		int opcion = 0;
		do {
			for (int i = 1; i <= jugadores.size(); i++) {
				System.out.println(i + " -> " + jugadores.get(i - 1).toString());
			}
			try {
				System.out.print("Opcion: ");
				opcion = sc.nextInt();
				if (opcion < 1 || opcion > jugadores.size()) {
					System.err.println("Selecciona un jugador valido");
					opcion = 0;
				}
			} catch (Exception ex) {
				System.err.println("Selecciona un jugador valido");
			}
		} while (opcion == 0);

		return jugadores.get(opcion - 1);

	}

	/**
	 * Imprimir el Menu, retornar el valor que el usuario ecogio
	 * 
	 * @return
	 */
	public static int printMenu() {
		UtilsJuego.limpiarPantalla();
		System.out.println("********************************");
		System.out.println("      Juego de cacería          ");
		System.out.println("********************************");
		System.out.println("Ingrese la modalidad: ");
		System.out.println("1 -> Versus");
		System.out.println("2 -> Simulacion enfrentamiento");
		System.out.println("3 -> Torneo");
		System.out.println("0 -> Salir");
		System.out.print("\nOpción: ");

		boolean valorCorrecto = true;
		int opcion = 0;
		try {
			opcion = sc.nextInt();
			if (opcion < 0 || opcion > 3) {
				valorCorrecto = false;
			}
		} catch (Exception ex) {
			System.err.println("Debe ingresar un valor correcto");
		}
		if (!valorCorrecto) {
			return printMenu();
		}
		return opcion;

	}
}
