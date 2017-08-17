import java.util.ArrayList;
import java.util.Random;

/**
 * CalebSoft

 * @author Carlos E. A. Torres
 * @email catencio@episunsa.edu.pe

 * Copyright 2016.
 */

/**
 * 
 * Esta clase almacenará
 * 
 * @author carlos
 *
 */
public class Tablero {
	private static final int TAMANHO = 5;
	private static Tablero instance;
	private Robot jugador1;
	private Robot jugador2;

	/**
	 * @return la única istancia de tablero
	 */
	public static Tablero getInstance() {
		if (instance == null) {
			instance = new Tablero();
		}
		return instance;
	}

	public Robot getJugador1() {
		return jugador1;
	}

	public Robot getJugador2() {
		return jugador2;
	}

	public void colocaJugadores(Robot jugador1, Robot jugador2) {
		clear();

		Random rd = new Random();
		jugador1.setPosF(rd.nextInt(TAMANHO));
		jugador1.setPosC(rd.nextInt(TAMANHO));
		colocaDireccionAleatoria(jugador1);
		this.jugador1 = jugador1;

		ArrayList<Integer[]> posiciones = new ArrayList<>();
		for (int i = 0; i < TAMANHO; i++) {
			for (int j = 0; j < TAMANHO; j++) {
				if (!(i == jugador1.posF && j == jugador1.posC)) {
					posiciones.add(new Integer[] { i, j });
				}
			}
		}

		Integer[] pos = posiciones.get(rd.nextInt(posiciones.size()));
		jugador2.setPosF(pos[0]);
		jugador2.setPosC(pos[1]);
		colocaDireccionAleatoria(jugador2);
		this.jugador2 = jugador2;

	}

	private void colocaDireccionAleatoria(Robot jugador) {
		Random rd = new Random();
		switch (rd.nextInt(4)) {
		case 0:
			jugador.direccion = Robot.Direccion.NORTE;
			break;
		case 1:
			jugador.direccion = Robot.Direccion.SUR;
			break;
		case 2:
			jugador.direccion = Robot.Direccion.ESTE;
			break;
		case 3:
			jugador.direccion = Robot.Direccion.OESTE;
			break;
		}
	}

	/***
	 * Retorna el Tablero en su forma String.
	 */
	public String toString() {
		StringBuilder build = new StringBuilder();

		String[] faceFrom1 = (jugador1 == null ? null : jugador1.toString2());
		String[] faceFrom2 = (jugador2 == null ? null : jugador2.toString2());

		for (int j = 0; j < 2 * TAMANHO + 4; j++) {
			build.append('=');
		}
		build.append('\n');

		for (int i = 0; i < TAMANHO; i++) {

			build.append("| ");
			for (int j = 0; j < TAMANHO; j++) {
				if (jugador1 != null && i == jugador1.posF && j == jugador1.posC) {
					build.append(faceFrom1[0]);
				} else if (jugador2 != null && i == jugador2.posF && j == jugador2.posC) {
					build.append(faceFrom2[0]);
				} else {
					build.append("  ");
				}
			}
			build.append(" |\n");

			build.append("| ");
			for (int j = 0; j < TAMANHO; j++) {
				if (jugador1 != null && i == jugador1.posF && j == jugador1.posC) {
					build.append(faceFrom1[1]);
				} else if (jugador2 != null && i == jugador2.posF && j == jugador2.posC) {
					build.append(faceFrom2[1]);
				} else {
					build.append("  ");
				}
			}

			build.append(" |\n");
		}

		for (int j = 0; j < 2 * TAMANHO + 4; j++) {
			build.append('=');
		}
		build.append('\n');
		return build.toString();
	}

	/**
	 * Retorna si el valor ingresado se encuentra entre 0 y el TAMANHO del
	 * tablero.
	 * 
	 * @param i
	 * @return
	 */
	public boolean esValido(int i) {
		return i >= 0 && i < TAMANHO;
	}

	/**
	 * Borra las referencias a sus dos jugadores.
	 */
	public void clear() {
		jugador1 = null;
		jugador2 = null;
	}
}
