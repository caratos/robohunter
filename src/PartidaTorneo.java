import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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
public class PartidaTorneo {

	private List<Robot> jugadores;
	private Map<Class, Integer> victoriasMap;

	/**
	 * Constructor recibe la lista de jugadores.
	 * 
	 * @param jugadores
	 */
	public PartidaTorneo(List<Robot> jugadores) {
		super();
		this.jugadores = jugadores;
		victoriasMap = new HashMap<>();
	}

	/**
	 * Simulacion del torneo.
	 */
	public void jugar() {

		for (int i = 0; i < jugadores.size(); i++) {
			for (int j = i + 1; j < jugadores.size(); j++) {
				PartidaMultiple partidaMultiple = new PartidaMultiple(jugadores.get(i), jugadores.get(j));
				partidaMultiple.jugar();

				if (partidaMultiple.getWinner() != null) {
					registraVictoria(partidaMultiple.getWinner());
				}
			}
		}
	}

	public void imprimirResultados() {
		UtilsJuego.limpiarPantalla();

		System.out.println("Los resultados: ");
		List<Entry<Class, Integer>> frecuencia = ordenarPorNumeroVictorias();
		for (int i = 0; i < frecuencia.size(); i++) {
			String nombreJugador = frecuencia.get(i).getKey().getName();
			int victorias = frecuencia.get(i).getValue();

			System.out.println(nombreJugador + " " + victorias + " victorias");
		}
		System.out.println("");
		System.out.println("El absolutor ganador es: " + frecuencia.get(0).getKey().getName());
	}

	private List<Entry<Class, Integer>> ordenarPorNumeroVictorias() {
		List<Entry<Class, Integer>> frecuencia = new ArrayList<>(victoriasMap.entrySet());
		Collections.sort(frecuencia, new Comparator<Entry<Class, Integer>>() {

			@Override
			public int compare(Entry<Class, Integer> o1, Entry<Class, Integer> o2) {
				return o1.getValue() - o2.getValue();
			}
		});

		return frecuencia;
	}

	/**
	 * Registra una victoria
	 * 
	 * @param victorias
	 * @param winner
	 */
	private void registraVictoria(Robot winner) {
		if (!victoriasMap.containsKey(winner.getClass())) {
			victoriasMap.put(winner.getClass(), 0);
		}
		int victoriasRecuperadas = victoriasMap.get(winner.getClass());
		victoriasMap.put(winner.getClass(), victoriasRecuperadas + 1);
	}

}
