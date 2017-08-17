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
public abstract class Robot {

	public static enum Direccion {
		NORTE, SUR, ESTE, OESTE
	}

	protected int posF;
	protected int posC;
	protected Direccion direccion;
	private boolean disparo;
	private int estamina;

	private final Tablero tablero = Tablero.getInstance();

	/**
	 * Constructor vacio.
	 */
	public Robot() {
	}

	/**
	 * 
	 * Constructor que recibe todos los datos por parámetros.
	 * 
	 * @param nombre
	 * @param posF
	 * @param posC
	 * @param sexo
	 * @param direccion
	 * @param tablero
	 */
	public Robot(int posF, int posC, Direccion direccion) {
		super();
		this.posF = posF;
		this.posC = posC;
		this.direccion = direccion;
	}

	public void irNorte() {
		if (estamina > 0 && tablero.esValido(posF - 1)) {
			posF--;
		}
		estamina--;
	}

	public void irSur() {
		if (estamina > 0 && tablero.esValido(posF + 1)) {
			posF++;
		}
		estamina--;
	}

	public void irEste() {
		if (estamina > 0 && tablero.esValido(posC + 1)) {
			posC++;
		}
		estamina--;
	}

	public void irOeste() {
		if (estamina > 0 && tablero.esValido(posC - 1)) {
			posC--;
		}
		estamina--;
	}

	public void girarIzquierda() {
		switch (direccion) {
		case NORTE:
			direccion = Direccion.OESTE;
			break;
		case SUR:
			direccion = Direccion.ESTE;
			break;
		case ESTE:
			direccion = Direccion.NORTE;
			break;
		case OESTE:
			direccion = Direccion.SUR;
			break;
		}
		estamina--;
	}

	public void girarDerecha() {
		switch (direccion) {
		case NORTE:
			direccion = Direccion.ESTE;
			break;
		case SUR:
			direccion = Direccion.OESTE;
			break;
		case ESTE:
			direccion = Direccion.SUR;
			break;
		case OESTE:
			direccion = Direccion.NORTE;
			break;
		}
		estamina--;
	}

	/**
	 * 
	 * @return verdadero si el objetivo está a no más de 3 pasos en frente tuyo.
	 */
	public boolean objetivoEnMira() {
		switch (direccion) {
		case NORTE:
			return objetivoEnMira(posF, posC, -1, 0);
		case SUR:
			return objetivoEnMira(posF, posC, 1, 0);
		case ESTE:
			return objetivoEnMira(posF, posC, 0, 1);
		case OESTE:
			return objetivoEnMira(posF, posC, 0, -1);

		default:
			break;
		}
		return false;
	}

	private boolean objetivoEnMira(int fila, int columna, int despFila, int despColumna) {
		int k = 3;

		Robot oponente = null;
		if (tablero.getJugador1() == this) {
			oponente = tablero.getJugador2();
		} else {
			oponente = tablero.getJugador1();
		}

		while (k > 0 && tablero.esValido(fila + despFila) && tablero.esValido(columna + despColumna)) {
			fila += despFila;
			columna += despColumna;

			if (oponente.getPosF() == fila && oponente.getPosC() == columna) {
				return true;
			}

			k--;
		}
		return false;
	}

	public void dispara() {
		this.disparo = true;
	}

	public void recarga() {
		this.disparo = false;
	}

	public boolean disparoEnSuTurno() {
		return disparo;
	}

	public boolean tieneEstamina() {
		return estamina > 0;
	}

	public void recibeEstamina(int estamina) {
		this.estamina = estamina;
	}

	/**
	 * Retorna el rostro del jugador. Debe ser de 2x2. Por ejemplo: ************
	 * * @@ **** ^^ ***** oO ***** && ********** Xx ****************************
	 * * v ***** @ ****** # ****** p *********** " ****************************
	 * 
	 * 
	 * @return
	 */
	public abstract Character[][] getFace();

	/**
	 * Estrategia del jugador.
	 */
	public abstract void estrategia();

	/**
	 * @return Una breve descripcion del robot (maximo 50 caracteres).
	 */
	public abstract String getDescripcion();

	/**
	 * @return posF - la posicion fila
	 */
	public int getPosF() {
		return posF;
	}

	/**
	 * Cambia la posición fila.
	 * 
	 * @param posF
	 */
	public void setPosF(int posF) {
		this.posF = posF;
	}

	/**
	 * @return posC - la posicion columna
	 */
	public int getPosC() {
		return posC;
	}

	/**
	 * Cambia la posicion columna
	 * 
	 * @param posC
	 */
	public void setPosC(int posC) {
		this.posC = posC;
	}

	/***
	 * @return direccion - NORTE/SUR/ESTE/OESTE
	 */
	public Direccion getDireccion() {
		return direccion;
	}

	/**
	 * Cambia la direccion.
	 * 
	 * @param direccion
	 */
	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}

	@Override
	public String toString() {
		return this.getClass().getName() + " :" + getDescripcion();
	}

	/**
	 * @return la representacion del rostro, en forma de 2 líneas.
	 */
	public String[] toString2() {
		String[] respuesta = new String[2];
		Character[][] face = getFace();

		switch (direccion) {
		case SUR:
			respuesta[0] = "" + face[0][0] + face[0][1];
			respuesta[1] = "" + face[1][0] + face[1][1];
			break;
		case NORTE:
			respuesta[0] = "" + face[1][0] + face[1][1];
			respuesta[1] = "" + face[0][0] + face[0][1];
			break;
		case ESTE:
			respuesta[0] = "" + face[0][1] + face[1][1];
			respuesta[1] = "" + face[0][0] + face[1][0];
			break;
		case OESTE:
			respuesta[0] = "" + face[1][0] + face[0][0];
			respuesta[1] = "" + face[1][1] + face[0][1];
			break;
		}
		return respuesta;
	}

}
