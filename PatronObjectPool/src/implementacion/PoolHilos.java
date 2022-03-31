/*
 * Asignatura: Patrones de Diseño de Software
 * Patrón Creacional - > Object Pool
 * Tipo de Clase: Java
 * Esta clase representa un Object Pool terminado.
 * Hereda de AbstraccionObjectPool lo que indica que ya tiene toda la funcionalidad por default para crear un pool.
 */
package implementacion;
import administracion.ControlTareas;
import fabrica.InterfaceFabrica;
/**
 *
 * @author Fabrizio Bolaño
 */
public class PoolHilos extends AbstraccionObjectPool<ControlTareas> {
	public PoolHilos(int minInstances, int maxInstances, int waitTime,
					 InterfaceFabrica<ControlTareas> poolableObjectFactory) {
		super(minInstances, maxInstances, waitTime, poolableObjectFactory);
	}
}