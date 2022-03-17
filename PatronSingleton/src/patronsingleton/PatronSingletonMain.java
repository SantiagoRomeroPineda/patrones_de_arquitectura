/*
 * Asignatura: Patrones de Diseño de Software
 * Patrón Creacional - > Singleton
 * Tipo de Clase: Main()
 */
package patronsingleton;
/**
 *
 * @author Fabrizio Bolaño
 */


class SingletonThreadInstance1 extends Thread {

	@Override
	public void run() {
		ParametrizacionSingleton singleton = ParametrizacionSingleton.getInstance();
		System.out.println(singleton);
		singleton.setNombreAplicacion("Patron Creacional Singleton 1");
		System.out.println("Singleton ==> " + singleton);
	}

}

class SingletonThreadInstance2 extends Thread {

	@Override
	public void run() {
		ParametrizacionSingleton singleton = ParametrizacionSingleton.getInstance();
		System.out.println(singleton);
		singleton.setNombreAplicacion("Patron Creacional Singleton 2");
		System.out.println("Singleton ==> " + singleton);
	}

}



public class PatronSingletonMain {
	/*public static void main(String[] args) {
		//Configuración de Propiedades para el Modulo No->1
		ParametrizacionSingleton singletonA = ParametrizacionSingleton.getInstance();
		//Configuración de Propiedades para el Modulo No->1
		ParametrizacionSingleton singletonB = ParametrizacionSingleton.getInstance();
		System.out.println(singletonA);
		System.out.println(singletonB);
		System.out.println("Igual referencia ==> " + (singletonA == singletonB));
		singletonA.setNombreAplicacion("Patron Creacional Singleton");
		singletonB.setNumeroVersion("2.0x");
		System.out.println("SingletonA ==> " + singletonA);
		System.out.println("SingletonB ==> " + singletonB);
		singletonA = null;
		singletonB = null;
		singletonA = ParametrizacionSingleton.getInstance();
		System.out.println("singletonA ==> " + singletonA);
	}*/
	public static void main(String[] args) {
		Thread t1 = new SingletonThreadInstance1();
		Thread t2 = new SingletonThreadInstance2();
		t1.start();
		t2.start();
	}

}
