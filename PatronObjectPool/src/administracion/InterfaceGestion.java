/*
 * Asignatura: Patrones de Diseño de Software
 * Patrón Creacional - > Object Pool
 * Tipo de Clase: Interface
 * Interface que todos los objetos del pool deberán implementar.
 * Se utiliza para reconocer los objetos que podrán ser gestionados por el ObjectPool.
 */
package administracion;

/**
 *
 * @author Fabrizio Bolaño
 */
public interface InterfaceGestion {
	public boolean validate();
	public void invalidate();
}