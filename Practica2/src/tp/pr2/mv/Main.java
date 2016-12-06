/**
 * 
 */
package tp.pr2.mv;

import java.util.Scanner;

import tp.pr2.mv.command.CommandInterpreter;
import tp.pr2.mv.command.CommandParser;
import tp.pr2.mv.cpu.CPU;
import tp.pr2.mv.cpu.ProgramMV;
import tp.pr2.mv.instructions.Instruction;
import tp.pr2.mv.instructions.InstructionParser;

/**
 @author Javier Druet
 @author Álvaro Asenjo 
 */

/**
 * Es la clase que contiene el método main de la aplicación.
 */

public class Main {

	// Metodo principal de ejecución, mientras que no se ponga HALT, la
	// aplicacion seguira funcionando.

	public static void main(String[] args) {
		boolean terminado = false;
		boolean terminado2 = false;
		CPU procesador = new CPU();
		ProgramMV programa = new ProgramMV();

		System.out.println("Introduce el programa fuente");
		while (!terminado) {
			// RECIBIMOS LA INSTRUCCION ESCRITA POR EL USUARIO
			// System.out.print("> ");
			InstructionParser lectorinstruc = new InstructionParser();
			Scanner lectura = new Scanner(System.in);
			String leido = lectura.nextLine();
			@SuppressWarnings("static-access")
			Instruction instruccion = lectorinstruc.parse(leido);

			if (instruccion != null) {
				programa.addInstruction(instruccion);
			} else if (!leido.equalsIgnoreCase("END")) {
				System.out.println("Error: Instruccion incorrecta");
			}
			// SI LA INSTRUCCION NO SE ENTIENDE, DA ERROR, CASO CONTRARIO SE
			// GUARDA EN EL PROGRAMA

			if (leido.equalsIgnoreCase("END")) {
				terminado = true;
			} else { // parsear la instruccion
			}
		}

		System.out.println("El programa introducido es:");
		System.out.println(programa.toString());

		procesador.loadProgram(programa);

		// MOSTRAR EL PROGRAMA SI EXISTE
		// RECIBIMOS LA INSTRUCCION ESCRITA POR EL USUARIO
		while (!terminado2) {
			// System.out.print("> ");
			CommandParser lectorcomando = new CommandParser();
			Scanner lectura1 = new Scanner(System.in);
			String comandoleido = lectura1.nextLine();
			@SuppressWarnings("static-access")
			CommandInterpreter comando = lectorcomando
					.parseCommand(comandoleido);
			CommandInterpreter.configureCommandInterpreter(procesador);
			// SI LA INSTRUCCION NO SE ENTIENDE, DA ERROR, CASO CONTRARIO SE
			// EJECUTA
			if (comando != null) {
				comando.executeCommand();
				// procesador.step();
				// System.out.println(procesador);
				terminado2 = procesador.programaTerminado();
			} else {
				System.out.println("No entiendo el comando.");
			}
		}
	}
}
