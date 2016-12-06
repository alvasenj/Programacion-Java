package tp.pr2.mv.cpu;

import tp.pr2.mv.instructions.Instruction;
import tp.pr2.mv.Constantes;

/**
 @author Javier Druet
 @author Álvaro Asenjo 
 */

/**
 * Es la clase que define el programa donde se guardaran las instrucciones para
 * su futura ejecución.
 */

public class ProgramMV {

	private Instruction programa[];
	int contador = 0;

	public ProgramMV() {
		this.programa = new Instruction[Constantes.DIMENSIONMEMORIA];
	}

	public void addInstruction(Instruction instruccion) {
		if (this.contador >= this.programa.length - 1) {
			ampliarPrograma();
		}
		Instruction instruc = instruccion;
		programa[this.contador] = instruc;
		contador++;
	}

	private void ampliarPrograma() {
		// AUMENTAMOS EL TAMAÑO DE LA PILA AÑADIENDO 100 ESPACIOS MÁS.
		Instruction[] programaux = new Instruction[this.programa.length + 100];

		for (int i = 0; i < programa.length; i++) {
			programaux[i] = this.programa[i];
		}

		this.programa = programaux;
	}

	public String escribirInstruction(int contador) {
		return "Comienza la ejecucion de "
				+ this.programa[contador - 1].toString();
	}

	protected boolean existInstruction(int contador) {
		boolean existe = false;
		if (this.programa[contador] != null) {
			existe = true;
		}
		return existe;
	}

	public Instruction InstructionExecute(int posicion) {
		return programa[posicion];
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
