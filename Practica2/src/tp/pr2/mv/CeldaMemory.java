package tp.pr2.mv;

/**
 * @author Javier Druet
 * @author Álvaro Asenjo
 * 
 */

public class CeldaMemory {

	private int valor;
	private int posicion;
	private boolean usado;

	public CeldaMemory() {
		// constructora de una celda de memoria con valores sin inicializar.
		this.valor = -1;
		this.posicion = -1;
		this.usado = false;
	}

	public CeldaMemory(int valor, int posicion) {
		// constructora de una celda de memoria con valores inicializados.
		this.valor = valor;
		this.posicion = posicion;
		this.usado = true;
	}

	public void celdaUsada() {
		// Funcion que permite saber si la celda esta inicializada o no.
		this.usado = true;
	}

	public void setPosicion(int posicion) {
		// Funcion que permite definir la posicion en la que se guarda el valor.
		if (posicion >= 0) {
			this.posicion = posicion;
		}
	}

	public void setValor(int valor) {
		// funcion que perminte definir el valor de una posicion de memoria
		// especifica.
		this.valor = valor;
	}

	public boolean guardado() {
		return this.usado;
	}

	public int getValor() {
		return this.valor;
	}

	public int getPosicion() {
		return this.posicion;
	}

	public String toString() {
		String cadena = "[" + this.posicion + "]:" + this.valor;
		return cadena;
	}
}
