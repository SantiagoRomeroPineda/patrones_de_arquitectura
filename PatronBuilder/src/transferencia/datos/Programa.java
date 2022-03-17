package transferencia.datos;

public class Programa {

	private String nombre;
	private int numeroSemestres;

	public Programa() {}

	public Programa(final String nombre, final int numeroSemestres) {
		this.nombre = nombre;
		this.numeroSemestres = numeroSemestres;
	}

	public String getNombre() {

		return nombre;
	}

	public void setNombre(final String nombre) {

		this.nombre = nombre;
	}

	public int getNumeroSemestres() {

		return numeroSemestres;
	}

	public void setNumeroSemestres(final int numeroSemestres) {

		this.numeroSemestres = numeroSemestres;
	}

	@Override public String toString() {

		return "Programa{" +
				"nombre='" + nombre + '\'' +
				", numeroSemestres=" + numeroSemestres +
				'}';
	}
}
