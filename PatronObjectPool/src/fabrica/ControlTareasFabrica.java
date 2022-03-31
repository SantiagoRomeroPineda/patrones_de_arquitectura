/*
 * Asignatura: Patrones de Diseño de Software
 * Patrón Creacional - > Object Pool
 * Tipo de Clase: Java
 * Esta clase hereda de InterfaceFabrica y sobrescribe el tipo genérico por ControlTareas, lo cual nos dice que el método createNew creará objetos de este tipo.
 */
package fabrica;
import administracion.ControlTareas;

/**
 *
 * @author Fabrizio Bolaño
 */
public class ControlTareasFabrica implements InterfaceFabrica<ControlTareas> {

	public ControlTareas createNew(){
		return new ControlTareas();
	}



}