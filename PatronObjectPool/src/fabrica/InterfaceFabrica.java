/*
 * Asignatura: Patrones de Diseño de Software
 * Patrón Creacional - > Object Pool
 * Tipo de Clase: Interface
 * Fabrica responsable de crear los objetos.
 */
package fabrica;
import administracion.InterfaceGestion;

/**
 *
 * @author Fabrizio Bolaño
 */
public interface InterfaceFabrica<T extends InterfaceGestion> {
	public T createNew();
}
