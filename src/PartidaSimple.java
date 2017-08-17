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
public class PartidaSimple extends Partida implements Runnable {
	public static int MILIS_REFRESH = 1000; // Milisegundos

	/**
	 * @param jugador1
	 * @param jugador2
	 */
	public PartidaSimple(Robot jugador1, Robot jugador2) {
		super(jugador1, jugador2);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {

		for (int turnos = 1; turnos <= MAX_TURNOS && winner == null; turnos++) {
			UtilsJuego.limpiarPantalla();

			System.out.println(UtilsJuego.getCabecera(jugador1, jugador2));

			System.out.println("Turno " + turnos + "/" + MAX_TURNOS);
			try {
				jugar();

				System.out.println(Tablero.getInstance().toString());

				Thread.sleep(MILIS_REFRESH);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Partida#jugar()
	 */
	@Override
	public void jugar() {
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
			// System.out.println(Tablero.getInstance().toString());
			if (winner == jugador1) {
				System.out.println("GANO JUGADOR 1 - " + winner.getClass().getName());
			} else {
				System.out.println("GANO JUGADOR 2 - " + winner.getClass().getName());
			}
			return;
		}

		jugador1.recibeEstamina(MAX_ESTAMINA);
		jugador2.recibeEstamina(MAX_ESTAMINA);
		jugador1.estrategia();
		jugador2.estrategia();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Partida#imprimirResultado()
	 */
	@Override
	public void imprimirResultado() {
		System.out.println("El ganador es: " + winner.toString());
	}
}
