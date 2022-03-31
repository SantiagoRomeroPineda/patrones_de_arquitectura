/*
 * Asignatura: Patrones de Diseño de Software
 * Patrón Creacional - > Object Pool
 * Tipo de Clase: Main
 * Clase Principal.
 */
package patronobjectpool;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import administracion.ControlTareas;
import fabrica.ControlTareasFabrica;
import implementacion.PoolHilos;
import implementacion.ManejoExcepciones;

/**
 /**
 *
 * @author Fabrizio Bolaño
 */
public class PatronObjectPoolMain {

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {

		final PoolHilos pool = new PoolHilos(2, 6, 1200*100, new ControlTareasFabrica());

		for (int c = 0; c < 10; c++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						ControlTareas task = pool.getObject();
						task.execute();
						pool.releaceObject(task);
					} catch (ManejoExcepciones e) {
						System.out.println("Error ==> " + e.getMessage());
						e.printStackTrace();
					}
				}
			}).start();
		}

		try {
			System.in.read();
			System.out.println(pool);
		} catch (Exception e) {
			System.out.println("Out disponible");
		}
	}

}
