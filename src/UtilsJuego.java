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
public class UtilsJuego {

	public static String getCabecera(Robot jugador1, Robot jugador2) {
		StringBuilder builder = new StringBuilder();
		builder.append(" --------------------------------------\n");
		builder.append("|                                      |\n");
		builder.append("|                  vs                  |\n");
		builder.append("|                                      |\n");
		builder.append("|   JUGADOR 1             JUGADOR 2    |\n");
		builder.append(" --------------------------------------\n");

		Character[][] face1 = jugador1.getFace();
		Character[][] face2 = jugador2.getFace();

		builder.setCharAt(46, face1[0][0]);
		builder.setCharAt(47, face1[0][1]);
		builder.setCharAt(87, face1[1][0]);
		builder.setCharAt(88, face1[1][1]);

		builder.setCharAt(68, face2[0][0]);
		builder.setCharAt(69, face2[0][1]);
		builder.setCharAt(109, face2[1][0]);
		builder.setCharAt(110, face2[1][1]);

		return builder.toString();

	}

	/**
	 * Limpia la pantalla
	 */
	public static void limpiarPantalla() {
		System.out.print("\033[H\033[2J");
	}

}
