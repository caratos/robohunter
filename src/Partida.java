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
public abstract class Partida {
	public static int MAX_ESTAMINA = 5;
	public static int MAX_TURNOS = 30;

	protected Robot jugador1;
	protected Robot jugador2;

	protected Robot winner;

	/**
	 * @param jugador1
	 * @param jugador2
	 */
	public Partida(Robot jugador1, Robot jugador2) {
		super();
		this.jugador1 = jugador1;
		this.jugador2 = jugador2;

	}

	public abstract void jugar();

	public abstract void imprimirResultado();
}
