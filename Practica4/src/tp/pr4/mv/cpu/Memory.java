package tp.pr4.mv.cpu;

import tp.pr4.mv.Constantes;

/**
 * @author Javier Druet
 * @author Alvaro Asenjo
 * 
 */

/**
 * Representa la memoria de la maquina. Recuerda que la memoria de la máquina es
 * ilimitada, y se puede escribir en cualquier dirección mayor o igual que 0.
 * Esto signica que cuando se almacena o se lee en cualquier dirección no se
 * generará ningún error de ejecución a menos que sea provocado por la memoria
 * de la máquina física externa, en cuyo caso no haremos nada. Para poder
 * visualizar la memoria por pantalla necesitamos saber qué posiciones de
 * memoria han sido utilizadas. Sólo consideraremos que una posición de memoria
 * ha sido utilizada si se ha escrito en ella, es decir se ha ejecutado alguna
 * instrucción STORE sobre esa posición.
 */

public class Memory {

	private CeldaMemory memoria[];
	private int contador;

	public Memory() {
		// constructora de la clase memoria, que crea un array de 100 celdas de
		// memoria.
		this.memoria = new CeldaMemory[Constantes.DIMENSIONMEMORIA];
		this.contador = 0;
	}

	// método para guardar un valor en una posicion especifica de la memoria
	// "ilimitada".
	public boolean store(int posicion, int valor) {
		boolean guardado = false;
		boolean existe = false;
		// recorremos la memoria ya existente para comprobar si ya se ha
		// guardado algo en la posicion.
		if (posicion >= 0) {
			for (int i = 0; i < this.memoria.length; i++) {
				if (this.memoria[i] != null) {
					// si existe, le cambiamos el valor a la posicion de
					// memoria.
					if (this.memoria[i].getPosicion() == posicion) {
						this.memoria[i].setValor(valor);
						this.memoria[i].celdaUsada();
						guardado = true;
						existe = true;
					}
				}
			}
			// si no existe, creamos una nueva celda con las caracteristicas
			// dadas.
			if (!existe) {
				this.memoria[contador] = new CeldaMemory(valor, posicion);
				contador++;
				guardado = true;
			}

		} else {
			guardado = false;
		}

		// ORDENAMOS LA MEMORIA PARA QUE ESTE DE MENOR A MAYOR POSICION.
		for (int i = this.contador; i >= 1; i--) {
			if (this.contador > 1) {
				while ((i >= 2)
						&& (this.memoria[i - 1].getPosicion() < this.memoria[i - 2]
								.getPosicion())) {
					CeldaMemory aux = this.memoria[i - 2];
					this.memoria[i - 2] = this.memoria[i - 1];
					this.memoria[i - 1] = aux;
					i--;
				}

			}
		}

		return guardado;
	}

	// método para cargar un valor de una direccion de memoria especifica.
	public int load(int posicion) {
		boolean encontrado = false;
		int cargado = -9999;
		// recorremos toda la memoria busando la posicion dada por el usuario.
		for (int i = 0; i < this.contador; i++) {
			// si la encuentra, devolvera el valor.
			if (!encontrado) {
				if ((posicion >= 0) && (this.memoria[contador - 1] != null)) {
					if (this.memoria[i].getPosicion() == posicion) {
						cargado = this.memoria[i].getValor();
						encontrado = true;
					}
				}
			}
		}
		return cargado;
	}

	public int devolverPosicion(int posicion) {
		return this.memoria[posicion].getPosicion();
	}

	public int devolverValor(int posicion) {
		return this.memoria[posicion].getValor();
	}

	public String toString() {
		String cadena = "";

		if (this.contador == 0) {
			cadena = cadena + "<vacia>";
		} else {
			for (int i = 0; i < this.contador; i++) {
				cadena = cadena + "[" + this.memoria[i].getPosicion() + "]:"
						+ this.memoria[i].getValor() + " ";
			}
		}
		cadena = "Memoria: " + cadena;
		return cadena;
	}

	public int getContador() {
		return this.contador;
	}
}
