/**
 * 
 */
package tp.pr4.mv.cpu;

import tp.pr4.mv.Constantes;

/**
 * @author Javier Druet
 * @author Alvaro Asenjo
 * 
 */

/**
 * Implementa la pila de operandos donde se van apilando elementos de tipo
 * entero. Las instrucciones que modican la pila de operandos son PUSH n, POP,
 * FLIP, LOAD y las operaciones aritméticas.
 * 
 */

public class OperandStack {
	// ATRIBUTOS DE LA CLASE PILA
	private int[] pila;
	private int contador = 0;

	public OperandStack() {
		// CONSTRUCTORA DE LA CLASE PILA
		this.pila = new int[Constantes.DIMENSIONPILA];
	}

	private void ampliarPila() {
		// AUMENTAMOS EL TAMANIO DE LA PILA AÑADIENDO 10 ESPACIOS MÁS.
		int[] pilaaux = new int[this.pila.length + 10];

		for (int i = 0; i < pila.length; i++) {
			pilaaux[i] = this.pila[i];
		}
		this.pila = pilaaux;

	}

	public boolean guardarEntero(int n) {
		// GUARDAMOS EL ENTERO EN LA 1ª POSICION DE LA PILA QUE ESTE VACIA, SI
		// NO LA HAY
		// AUMENTAMOS EL ESPACIO DE LA PILA Y VOLVEMOS A GUARDAR.
		boolean guardado = false;
		if (this.contador < this.pila.length) {
			this.pila[this.contador] = n;
			guardado = true;
			this.contador++;
		}

		if (!guardado) {
			ampliarPila();
			guardarEntero(n);
		}

		return guardado;
	}

	public void eliminarCima() {
		this.pila[this.contador - 1] = -1;
		this.contador--;
	}

	public int getCima() {
		// DEVUELVE EL VALOR DE LA CIMA
		int aux = 0;
		if (this.contador >= 1) {
			aux = this.pila[this.contador - 1];
		}
		return aux;
	}

	public int getSubCima() {
		// DEVUELVE EL VALOR DE LA SUBCIMA
		return this.pila[this.contador - 2];
	}

	public int getContador() {
		return this.contador;
	}

	public void intercambioCima() {
		// INTERCAMBIA EL VALOR DE LA CIMA POR EL DE LA SUBCIMA
		int aux = getCima();
		this.pila[this.contador - 1] = getSubCima();
		this.pila[this.contador - 2] = aux;
	}

	public String devolverCima() {
		int cima = this.getCima();
		return Integer.toString(cima);
	}

	public String toString() {
		// DEVUELVE UN STRING CON LA LISTA DE VALORES QUE HAY EN LA PILA
		String cadena = "Pila de operandos: ";
		if (contador == 0) {
			cadena = cadena + "<vacia>";
		} else {
			for (int i = 0; i < this.contador; i++) {
				cadena = cadena + this.pila[i] + " ";

			}
		}
		return cadena;
	}

}
