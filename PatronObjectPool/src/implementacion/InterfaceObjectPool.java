/*
 * Asignatura: Patrones de Diseño de Software
 * Patrón Creacional - > Object Pool
 * Tipo de Clase: Interface
 * Interface para establecer las operaciones mínimas que deberán tener los ObjectPool.
 */
package implementacion;
import administracion.InterfaceGestion;

/**
 *
 * @author Fabrizio Bolaño
 */
public interface InterfaceObjectPool<T extends InterfaceGestion> {
	public T getObject() throws ManejoExcepciones;
	public void releaceObject(T pooledObject);
}