/*
 * Asignatura: Patrones de Diseño de Software
 * Patrón Creacional - > Object Pool
 * Tipo de Clase: Java
 * Representa el ConcretePoolableObject de nuestro modelo de componentes.
 * Crea las instancias que finalmente administre el Object Pool.
 */


package administracion;
import administracion.InterfaceGestion;

/**
 *
 * @author Fabrizio Bolaño
 */
public class ControlTareas implements InterfaceGestion {
	private int eventos;
	private static int invalidar;
	private static int contador;


	public void execute() {
		int c = ++contador ;
		eventos++;
		System.out.println("Ejecución ==> " + c);
		try {
			Thread.sleep(5000);
		} catch (Exception e) {
		}
		System.out.println("Fin de la Ejecución ==> " + c);
	}

	@Override
	public boolean validate() {
		return eventos < 2;
	}

	@Override
	public void invalidate() {
		invalidar++;
		System.out.println("Invalidar contador ==> " + invalidar);
	}

}