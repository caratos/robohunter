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
public class PartidaMultiple extends Partida {

	public static final int MAX_ENFRENTAMIENTOS = 100;

	private int victoriasJugador1;
	private int victoriasJugador2;
	private int empates;

	/**
	 * @param jugador1
	 * @param jugador2
	 */
	public PartidaMultiple(Robot jugador1, Robot jugador2) {
		super(jugador1, jugador2);
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Partida#jugar()
	 */
	@Override
	public void jugar() {
		for (int i = 0; i < MAX_ENFRENTAMIENTOS; i++) {
			unJuego();
		}
	}

	private void unJuego() {
		for (int turnos = 0; turnos < MAX_TURNOS; turnos++) {
			if (jugador1.disparoEnSuTurno() && jugador1.objetivoEnMira()) {
				if (jugador2.disparoEnSuTurno() && jugador2.objetivoEnMira()) {
					winner = null;
				} else {
					winner = jugador1;
				}
			} else if (jugador2.disparoEnSuTurno() && jugador2.objetivoEnMira()) {
				winner = jugador2;
			}

			if (winner == null) {
				jugador1.recarga();
				jugador2.recarga();
			} else {
				if (winner == jugador1) {
					victoriasJugador1++;
				} else {
					victoriasJugador2++;
				}
				return;
			}

			jugador1.recibeEstamina(MAX_ESTAMINA);
			jugador2.recibeEstamina(MAX_ESTAMINA);
			jugador1.estrategia();
			jugador2.estrategia();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Partida#imprimirResultado()
	 */
	@Override
	public void imprimirResultado() {
		UtilsJuego.limpiarPantalla();

		System.out.println("Despues de  " + MAX_ENFRENTAMIENTOS + " enfrentamientos: ");
		System.out.println("Jugador 1 -> " + jugador1.toString());
		System.out.println("Jugador 2 -> " + jugador2.toString());
		System.out.println("");
		System.out.println("Jugador 1: " + victoriasJugador1 + " juegos vencidos.");
		System.out.println("Jugador 2: " + victoriasJugador2 + " juegos vencidos.");
		System.out.println("Empates : " + empates);
		System.out.println("");
		if (victoriasJugador1 > victoriasJugador2) {
			winner = jugador1;
			System.out.println("Jugador 1 gana!");
		} else if (victoriasJugador2 > victoriasJugador1) {
			winner = jugador2;
			System.out.println("Jugador 2 gana!");
		} else {
			winner = null;
			System.out.println("¡EMPATE!");
		}

	}

	/**
	 * @return el ganador del enfrentamiento
	 */
	public Robot getWinner() {
		return winner;
	}

}
