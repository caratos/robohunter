import java.util.Random;

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
public class Descompuesto extends Robot {

	/**
		 * 
		 */
	public Descompuesto() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Jugador#getFace()
	 */
	@Override
	public Character[][] getFace() {
		Character[][] face = new Character[2][2];
		face[0] = new Character[] { '@', '@' };
		face[1] = new Character[] { ' ', 'v' };

		return face;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Jugador#estrategia()
	 */
	@Override
	public void estrategia() {
		Random rd = new Random();
		while (tieneEstamina()) {
			switch (rd.nextInt(4)) {
			case 0:
				irNorte();
				break;
			case 1:
				irSur();
				break;
			case 2:
				irEste();
				break;
			case 3:
				irOeste();
				break;
			}

			if (objetivoEnMira()) {
				dispara();
			}

			switch (rd.nextInt(2)) {
			case 0:
				girarIzquierda();
				break;
			case 1:
				girarDerecha();
				break;
			}

			if (objetivoEnMira()) {
				dispara();
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Jugador#getDescripcion()
	 */
	@Override
	public String getDescripcion() {
		return "Este robot está descompuesto, todos sus movimientos son aleatorios.";
	}

}
