package tp.pr5.mv.cpu;

import tp.pr5.mv.Constantes;
import tp.pr5.mv.ins.Instruction;

/**
 * @author Javier Druet
 * @author Alvaro Asenjo
 * 
 */

/**
 * Es la clase que define el programa donde se guardaran las instrucciones para
 * su futura ejecución.
 */

public class ProgramMV {

	private Instruction programa[];
	private int contador = 0;

	public ProgramMV() {
		this.programa = new Instruction[Constantes.DIMENSIONMEMORIA];
	}

	// Metodo que agrega una instruccion al array de Instructions.
	public void addInstruction(Instruction instruccion) {
		// si el contador del programa se va a pasar de la dimension del array,
		// lo ampliamos.
		if (this.contador >= this.programa.length - 1) {
			ampliarPrograma();
		}

		// despues de ampliar si era necesario, introducimos la instruccion.
		Instruction instruc = instruccion;
		programa[this.contador] = instruc;
		contador++;
	}

	private void ampliarPrograma() {
		// AUMENTAMOS EL TAMANIO DE LA PILA ANIADIENDO 100 ESPACIOS MÁS.
		Instruction[] programaux = new Instruction[this.programa.length + 100];

		for (int i = 0; i < programa.length; i++) {
			programaux[i] = this.programa[i];
		}

		this.programa = programaux;
	}

	// Crea un string con la informacion de la instruccion.
	public String escribirInstruction(int contador) {
		return "Comienza la ejecucion de "
				+ this.programa[contador - 1].toString();
	}

	// comprueba si existe una instruccion en la posicion del contador.
	protected boolean existInstruction(int contador) {
		boolean existe = false;
		if (this.programa[contador] != null) {
			existe = true;
		}
		return existe;
	}

	// Devuelve una instruccion en la posicion correspondiente.
	public Instruction InstructionExecute(int posicion) {
		return programa[posicion];
	}

	public int getContador() {
		return this.contador;
	}

	public String getInstruction(int contador) {
		return this.programa[contador].toString();
	}

	public String toString() {
		String cadena = "";
		for (int i = 0; i < this.contador; i++) {
			cadena = cadena + i + ": " + programa[i].toString()
					+ Constantes.LINE_SEPARATOR;
		}
		return cadena;
	}

}
