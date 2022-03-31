/*
 * Asignatura: Patrones de Diseño de Software
 * Patrón Creacional - > Object Pool
 * Tipo de Clase: Java
 * Clase para realizar el manejo de excepciones.
 */
package implementacion;

/**
 *
 * @author Fabrizio Bolaño
 */
public class ManejoExcepciones extends Exception {

	public ManejoExcepciones(String message) {
		super(message);
	}
}