package tp.pr4.mv;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;

/**
 * @author Javier Druet
 * @author Alvaro Asenjo
 * 
 */

public class OptionsMain {

	// Metodo que imprimira la informacion de la ayuda de las diferentes
	// opciones.
	public static void imprimeAyuda(Options opciones) {
		HelpFormatter ayuda = new HelpFormatter();
		ayuda.printHelp(
				Main.class.getCanonicalName()
						+ " [-a <asmfile>] [-h] [-i <infile>] [-m <mode>] [-o <outfile>]",
				opciones);
		System.exit(0);

	}

	// METODO QUE CARGARA LAS OPCIONES PARA PARSEAR LOS ARGUMENTOS QUE EL
	// USUARIO HAYA INTRODUCIDO.
	static Options loadOptions() {

		Options opciones = new Options();

		// Creamos la opcion HELP
		Option help = new Option("h", "help", false, "Muestra esta ayuda");

		// Creamos la opcion ASM
		OptionBuilder.withArgName("asmfile");
		OptionBuilder.withValueSeparator(' ');
		OptionBuilder.hasOptionalArgs(2);
		OptionBuilder.withLongOpt("asm");
		OptionBuilder
				.withDescription("Fichero con el codigo en ASM del programa a ejecutar. Obligatorio en modo batch.");
		Option asmfile = OptionBuilder.create("a");

		// Creamos la opcion IN
		OptionBuilder.withArgName("infile");
		OptionBuilder.withValueSeparator(' ');
		OptionBuilder.hasOptionalArgs(2);
		OptionBuilder.withLongOpt("in");
		OptionBuilder.withDescription("Entrada del programa de la maquina-p.");
		Option infile = OptionBuilder.create("i");

		// Creamos la opcion OUT
		OptionBuilder.withArgName("outfile");
		OptionBuilder.withValueSeparator(' ');
		OptionBuilder.hasOptionalArgs(2);
		OptionBuilder.withLongOpt("out");
		OptionBuilder
				.withDescription("Fichero donde se guarda la salida del programa de la maquina-p.");
		Option outfile = OptionBuilder.create("o");

		// Creamos la opcion MODE
		OptionBuilder.withArgName("mode");
		OptionBuilder.withValueSeparator(' ');
		OptionBuilder.hasOptionalArgs(2);
		OptionBuilder.withLongOpt("mode");
		OptionBuilder
				.withDescription("Modo de funcionamiento (batch | interactive | window). Por defecto, batch.");
		Option mode = OptionBuilder.create("m");

		// Guardamos todas las posibles opciones creadas.
		opciones.addOption(asmfile);
		opciones.addOption(infile);
		opciones.addOption(outfile);
		opciones.addOption(mode);
		opciones.addOption(help);

		return opciones;
	}

	// METODO QUE COMPRUEBA QUE TODOS LOS ARGUMENTOS QUE SE INTRODUCEN, LLEVAN
	// SU CORRESPONDIENTE NOMBRE DE ARCHIVO.
	public static boolean comprobarArgumentosCorrectos(int contador,
			CommandLine cmd, String[] args, int posicionA, int posicionI,
			int posicionO) throws IOException {
		boolean aSMtratado = false;
		boolean iNtratado = false;
		boolean oUTtratado = false;

		boolean correcto = true;
		while (contador < args.length) {
			// COMPROBAMOS QUE EL ARGUMENTO ASM VA SEGUIDO DE UN NOMBRE DE
			// ARCHIVO.
			if (cmd.hasOption("a") && (!aSMtratado)) {
				posicionA++;
				contador++;
				if ((posicionA < args.length)
						&& (!args[posicionA].equalsIgnoreCase("-a"))
						&& (!args[posicionA].equalsIgnoreCase("--asm"))
						&& (!args[posicionA].equalsIgnoreCase("-o"))
						&& (!args[posicionA].equalsIgnoreCase("--out"))
						&& (!args[posicionA].equalsIgnoreCase("-i"))
						&& (!args[posicionA].equalsIgnoreCase("--in"))) {
					// si el argumento siguiente a "-a" es diferente a otro
					// argumento, sera un archivo.
					contador++;
					aSMtratado = true;
				} else {
					throw new IOException(
							"Uso incorrecto: Missing argument for option: a"
									+ Constantes.LINE_SEPARATOR
									+ "Use -h|--help para más detalles.");
				}
				// COMPROBAMOS QUE EL ARGUMENTO OUT VA SEGUIDO DE UN NOMBRE DE
				// ARCHIVO.
			} else if (cmd.hasOption("o") && (!oUTtratado)) {
				posicionO++;
				contador++;
				if ((posicionO < args.length)
						&& (!args[posicionO].equalsIgnoreCase("-a"))
						&& (!args[posicionO].equalsIgnoreCase("--asm"))
						&& (!args[posicionO].equalsIgnoreCase("-o"))
						&& (!args[posicionO].equalsIgnoreCase("--out"))
						&& (!args[posicionO].equalsIgnoreCase("-i"))
						&& (!args[posicionO].equalsIgnoreCase("--in"))) {
					// si el argumento siguiente a "-o" es diferente a otro
					// argumento, sera un archivo.
					contador++;
					oUTtratado = true;
				} else {
					throw new IOException(
							"Uso incorrecto: Missing argument for option: o"
									+ Constantes.LINE_SEPARATOR
									+ "Use -h|--help para más detalles.");
				}
				// COMPROBAMOS QUE EL ARGUMENTO IN VA SEGUIDO DE UN NOMBRE DE
				// ARCHIVO.
			} else if (cmd.hasOption("i") && (!iNtratado)) {
				posicionI++;
				contador++;
				if ((posicionI < args.length)
						&& (!args[posicionI].equalsIgnoreCase("-a"))
						&& (!args[posicionI].equalsIgnoreCase("--asm"))
						&& (!args[posicionI].equalsIgnoreCase("-o"))
						&& (!args[posicionI].equalsIgnoreCase("--out"))
						&& (!args[posicionI].equalsIgnoreCase("-i"))
						&& (!args[posicionI].equalsIgnoreCase("--in"))) {
					// si el argumento siguiente a "-i" es diferente a otro
					// argumento, sera un archivo.
					contador++;
					iNtratado = true;
				} else {
					throw new IOException(
							"Uso incorrecto: Missing argument for option: i"
									+ Constantes.LINE_SEPARATOR
									+ "Use -h|--help para más detalles.");
				}
				// SI NO ES NINGUN ARGUMENTO DE TIPO A, I U O, DARA ERROR.
			} else {
				contador++;
				correcto = false;
			}
		}
		// Devuelve true si todos los argumentos son correctos y tienen sus
		// respectivos archivos.
		return correcto;
	}

	// METODO QUE CARGA LA OPCION I DE LOS ARGUMENTOS, Y PRUEBA A ABRIR EL
	// ARCHIVO PARA COMPROBAR SI EXISTE.
	public static boolean comprobarOpcionI(String[] args, CommandLine cmd,
			int posicionI, int contador) throws IOException {
		boolean archivo = false;
		if (cmd.hasOption("i")) {
			posicionI++;
			contador++;
			if (args[posicionI] != null) {
				contador++;
				try {
					// Probamos a abrir el archivo de I, si da error, no abrirar
					// y se mostrara excepcion.
					FileInputStream file = new FileInputStream(args[posicionI]);
					file.close();
					archivo = true;
				} catch (IOException e) {
					throw new IOException(
							"Error al acceder al fichero de entrada ("
									+ args[posicionI] + ")"
									+ Constantes.LINE_SEPARATOR
									+ "Use -h|--help para más detalles.");
				}
				// SI TIENE OPCION I, PERO NO VA SEGUIDO DE UN ARCHIVO DE
				// ENTRADA, DARA ERROR.
			} else {
				throw new IOException(
						"Uso incorrecto: Missing argument for option: i"
								+ Constantes.LINE_SEPARATOR
								+ "Use -h|--help para más detalles.");
			}

		}

		return archivo;

	}

	// METODO QUE CARGA LA OPCION O DE LOS ARGUMENTOS, Y PRUEBA A ABRIR EL
	// ARCHIVO PARA COMPROBAR SI EXISTE.
	public static boolean comprobarOpcionO(String[] args, CommandLine cmd,
			int posicionO, int contador) throws IOException {
		boolean archivo = false;
		if (cmd.hasOption("o")) {
			contador++;
			posicionO++;
			if (args[posicionO] != null) {
				contador++;
				try {
					// Probamos a abrir el archivo de O, si da error, no abrirar
					// y se mostrara excepcion.
					FileOutputStream file = new FileOutputStream(
							args[posicionO]);
					file.close();
					archivo = true;
				} catch (IOException e) {
					throw new IOException(
							"Error al acceder al fichero de salida ("
									+ args[posicionO] + ")"
									+ Constantes.LINE_SEPARATOR
									+ "Use -h|--help para más detalles.");
				}
				// SI TIENE OPCION O, PERO NO VA SEGUIDO DE UN ARCHIVO DE
				// SALIDA, DARA ERROR.
			} else {
				throw new IOException(
						"Uso incorrecto: Missing argument for option: o"
								+ Constantes.LINE_SEPARATOR
								+ "Use -h|--help para más detalles.");
			}

		}
		return archivo;
	}
}
