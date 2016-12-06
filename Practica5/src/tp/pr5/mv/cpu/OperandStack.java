/**
 * 
 */
package tp.pr5.mv.cpu;

import java.util.ArrayList;

import tp.pr5.mv.Constantes;
import tp.pr5.mv.observers.ObserverMV;

/**
 * @author Javier Druet
 * @author Alvaro Asenjo
 * 
 */

/**
 * Implementa la pila de operandos donde se van apilando elementos de tipo
 * entero. Las instrucciones que modifican la pila de operandos son PUSH n, POP,
 * FLIP, LOAD y las operaciones aritmeticas.
 * 
 */

public class OperandStack {
	// ATRIBUTOS DE LA CLASE PILA
	private int[] pila;
	private int contador = 0;
	private ArrayList<ObserverMV> vistas;

	public OperandStack() {
		// CONSTRUCTORA DE LA CLASE PILA
		this.pila = new int[Constantes.DIMENSIONPILA];
		vistas = new ArrayList<ObserverMV>();
	}

	public void addObs(ObserverMV vista) {
		vistas.add(vista);
	}

	private void ampliarPila() {
		// AUMENTAMOS EL TAMANIO DE LA PILA ANIADIENDO 10 ESPACIOS MAS.
		int[] pilaaux = new int[this.pila.length + 10];

		for (int i = 0; i < pila.length; i++) {
			pilaaux[i] = this.pila[i];
		}
		this.pila = pilaaux;

	}

	public boolean guardarEntero(int n) {
		// GUARDAMOS EL ENTERO EN LA PRIMERA POSICION DE LA PILA QUE ESTE VACIA,
		// SI
		// NO LA HAY
		// AUMENTAMOS EL ESPACIO DE LA PILA Y VOLVEMOS A GUARDAR.
		boolean guardado = false;
		if (this.contador < this.pila.length) {
			this.pila[this.contador] = n;
			guardado = true;
			this.contador++;
			for (ObserverMV o : vistas)
				o.addCima(String.valueOf(n));

			if (this.contador == 1 && guardado) {
				for (ObserverMV o : vistas)
					o.pilaCambiada();
			}
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
		for (ObserverMV o : vistas)
			o.deleteCima();
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
