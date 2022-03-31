/*
 * Asignatura: Patrones de Diseño de Software
 * Patrón Creacional - > Object Pool
 * Tipo de Clase: Java
 * Clase abstracta que implementa la interface ConcreteObjectPool y servirá como base para crear ConcreteObjectPool.
 */
package implementacion;
import administracion.*;
import fabrica.*;
import java.util.Calendar;
import java.util.Stack;
import java.util.UUID;

/**
 *
 * @author Fabrizio Bolaño
 */
public class AbstraccionObjectPool<T extends InterfaceGestion>
		implements InterfaceObjectPool<T>{

	private final int minInstances;
	private final int maxInstances;
	private final int waitTime;

	private final InterfaceFabrica<T> poolableObjectFactory;

	private final Stack<PooledObjectStatus<T>> fullStack = new Stack<>();
	private final Stack<PooledObjectStatus<T>> useStack = new Stack<>();
	private final Stack<PooledObjectStatus<T>> freeStack = new Stack<>();

	public AbstraccionObjectPool(int minInstances,
								 int maxInstances, int waitTime
			, InterfaceFabrica<T> poolableObjectFactory) {
		System.out.println("=========== STARTING ============");
		this.minInstances = minInstances;
		this.maxInstances = maxInstances;
		this.waitTime = waitTime;
		this.poolableObjectFactory = poolableObjectFactory;
		initPool();
		System.out.println("=========== FINISH ============");
		System.out.println();
	}


	private void initPool() {
		for (int c = fullStack.size(); c < minInstances; c++) {
			PooledObjectStatus<T> createNewPooledObject
					= createNewPooledObject();
			freeStack.push(createNewPooledObject);
		}
	}

	private static class PooledObjectStatus<T> {
		boolean used;
		UUID uuid;
		T pooledObject;

		public PooledObjectStatus(T pooledObject) {
			this.used = false;
			this.uuid = UUID.randomUUID();
			this.pooledObject = pooledObject;
		}
	}
	private T getInternalObject() throws ManejoExcepciones {
		synchronized (freeStack) {
			if (!freeStack.isEmpty()) {
				PooledObjectStatus<T> first = this.freeStack.pop();
				first.used = true;
				System.out.println("Provisioning Object > "
										   + first.uuid.toString());
				useStack.push(first);
				return first.pooledObject;
			}
			synchronized (fullStack) {
				if (fullStack.size() < maxInstances) {
					PooledObjectStatus<T> returnObject
							= createNewPooledObject();
					returnObject.used = true;
					System.out.println("Provisioning Object > "
											   + returnObject.uuid.toString());
					useStack.push(returnObject);
					return returnObject.pooledObject;
				} else {
					return null;
				}
			}
		}
	}

	@Override
	public T getObject() throws ManejoExcepciones {
		T internalObject = getInternalObject();
		if (internalObject != null) {
			return internalObject;
		}
		return waitForResource();
	}

	private T waitForResource() throws ManejoExcepciones {
		Calendar future = Calendar.getInstance();
		future.add(Calendar.MILLISECOND, waitTime);
		do {
			PooledObjectStatus<T> returnObject = null;
			synchronized (freeStack) {
				if (!freeStack.isEmpty() && !freeStack.peek().used) {
					returnObject = freeStack.pop();
					returnObject.used = true;
					useStack.push(returnObject);
					System.out.println("Provisioning Object > "
											   + returnObject.uuid.toString());
					return returnObject.pooledObject;
				}
			}

			if (returnObject == null || returnObject.used) {
				if (waitTime != 0 && System.currentTimeMillis()
						>= future.getTimeInMillis()) {
					throw new ManejoExcepciones("Tiempo de espera agotado");
				}
				try {
					Thread.sleep(1000);
				} catch (Exception e) {
				}
			}

		} while (true);
	}

	private PooledObjectStatus<T> createNewPooledObject() {
		T newObject = poolableObjectFactory.createNew();
		PooledObjectStatus<T> pooled = new PooledObjectStatus<>(newObject);
		fullStack.push(pooled);
		System.out.println("New PoolableObject{UUID=" + pooled.uuid.toString()
								   + ", poolSize=" + fullStack.size() + "}");
		return pooled;
	}

	@Override
	public void releaceObject(T pooledObject) {
		for (PooledObjectStatus<T> item : this.fullStack) {
			if (item.pooledObject == pooledObject) {
				if (pooledObject.validate()) {
					freeStack.push(item);
					useStack.remove(item);
					item.used = false;
					System.out.println("Object returned > "
											   + item.uuid.toString());
					return;
				} else {
					System.out.println("Object Invalidate ==> "
											   + item.uuid.toString());
					pooledObject.invalidate();
					fullStack.remove(item);
					useStack.remove(item);
					synchronized(freeStack){
						initPool();
					}
					return;
				}
			}
		}
	}

	@Override
	public String toString() {
		return "AbstractObjectPool ==> currentSize > '"
				+fullStack.size()+"', " + "free > '"
				+freeStack.size()+"', used > '"+useStack.size()+"'";
	}


}