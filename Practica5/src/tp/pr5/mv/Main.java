package tp.pr5.mv;

import java.io.IOException;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import tp.pr5.mv.cpu.CPU;
import tp.pr5.mv.cpu.ProgramMV;
import tp.pr5.mv.exceptions.ASMSyntaxErrorException;
import tp.pr5.mv.stategyOut.ArchivoLog;
import tp.pr5.mv.stategyOut.ConsoleOut;
import tp.pr5.mv.stategyOut.FileOut;
import tp.pr5.mv.stategyOut.NullOut;
import tp.pr5.mv.stategyOut.OutMethod;
import tp.pr5.mv.stategyOut.WindowOutStrategy;
import tp.pr5.mv.strategyIn.ConsoleIn;
import tp.pr5.mv.strategyIn.FileIn;
import tp.pr5.mv.strategyIn.InMethod;
import tp.pr5.mv.strategyIn.NullIn;
import tp.pr5.mv.strategyIn.WindowInStrategy;
import tp.pr5.mv.views.ConsoleBatch;
import tp.pr5.mv.views.ConsoleInteractive;
import tp.pr5.mv.views.WindowMain;

/**
 * @author Javier Druet
 * @author Alvaro Asenjo
 * 
 */

/**
 * Es la clase que contiene el método main de la aplicación.
 */

public class Main {
	// Atributo que almacena las opciones posibles del programa.
	static Options opciones = new Options();

	@SuppressWarnings({ "unused" })
	public static void main(String[] args) throws IOException {
		// CREAMOS UN CONTADOR QUE IRA RECORRIENDO LOS ARGUMENTOS.
		int contador = 0;
		// CREAMOS UN BOOLEANO QUE INDICA SI HAY QUE CARGAR UN FICHERO ASM
		boolean cargarASM = false;
		// CREAMOS UNA CPU Y UN PROGRAMA QUE SE IRAN ACTUALIZANDO.
		CPU procesador;
		ProgramMV programa = new ProgramMV();
		// CREAMOS TRES "PUNTEROS" ENTEROS QUE SEÑALAN A LA POSICION DE LOS
		// ARGUMENTOS a, i y o.
		int posicionA = 0;
		int posicionI = 0;
		int posicionO = 0;
		int posicionL = 0;

		// CREAMOS TRES BOOLEANOS QUE INDICARAN SI HAY ARCHIVOS DE TIPO IN, OUT
		// Y SI LOS ARGUMENTOS SON CORRECTOS
		boolean archivoI = false;
		boolean archivoO = false;
		boolean archivoL = false;
		boolean correcto = false;
		
		ArchivoLog archivo = null;

		// CREAMOS UN METODO DE ENTRADA Y UNO DE SALIDA INICIALIZADOS A NULL
		InMethod metodoE = null;
		OutMethod metodoS = null;

		// llamamos a la funcion que carga las posibles opciones.
		opciones = OptionsMain.loadOptions();
		// Creamos un parseador de opciones.
		CommandLineParser parser = new BasicParser();

		try {
			// Parseamos las posibles opciones.
			CommandLine cmd = parser.parse(opciones, args);
			String[] modo = cmd.getOptionValues("m");

			// Si no hay modo de ejecucion, por defecto ponemos el modo batch.
			if (modo == null) {
				modo = new String[1];
				modo[0] = "batch";
			}

			// Vamos a apuntar donde se situan cada una de las opciones en los
			// argumentos para poder tratar bien los fallos.
			for (int i = 0; i < args.length; i++) {
				if (args[i].equalsIgnoreCase("-a")
						|| (args[i].equalsIgnoreCase("--asm"))) {
					posicionA = i;
				} else if (args[i].equalsIgnoreCase("-i")
						|| (args[i].equalsIgnoreCase("--in"))) {
					posicionI = i;
				} else if (args[i].equalsIgnoreCase("-o")
						|| (args[i].equalsIgnoreCase("--out"))) {
					posicionO = i;
				} else if (args[i].equalsIgnoreCase("-l")
						|| (args[i].equalsIgnoreCase("--log"))) {
					posicionL = i;
				}
				
			}

			if (cmd.hasOption("m")) {
				contador = contador + 2;
			}
			// COMPROBAMOS QUE TODOS LOS ARGUMENTOS QUE HA METIDO EL USUARIO
			// SEAN CORRECTOS
			correcto = OptionsMain.comprobarArgumentosCorrectos(contador, cmd,
					args, posicionA, posicionI, posicionO, posicionL);

			// COMPROBAMOS QUE HAYA UNA OPCION I y/o UNA OPCION
			// O.
			archivoI = OptionsMain.comprobarOpcionI(args, cmd, posicionI,
					contador);
			archivoO = OptionsMain.comprobarOpcionO(args, cmd, posicionO,
					contador);
			archivoL = OptionsMain.comprobarOpcionO(args, cmd, posicionL,
					contador);

			// COMPROBAMOS QUE METODOS DE ENTRADA Y SALIDA SE HAN INTRODUCIDO.
			metodoE = inicializarMetodoE(args, metodoE, archivoI, posicionI);
			metodoS = inicializarMetodoO(args, metodoS, archivoO, posicionO);

			// Si existe la opcion h, impriremos por pantalla la ayuda;
			if (cmd.hasOption("h")) {
				OptionsMain.imprimeAyuda(opciones);
				// Si es otra opcion, pasamos a ejecutarla

				// COMPROBAMOS SI HAY MAS DE UN ARGUMENTO INTRODUCIDO
			} else if (args.length >= 1) {
				if (cmd.hasOption("m")) {
					contador++;
					// SI EL MODO DE EJECUCION ES INTERACTIVO, EJECUTAMOS EL
					// PROGRAMA NORMAL DE LAS ANTERIORES PRACTICAS.
					if (modo[0].equalsIgnoreCase("interactive")) {
						contador++;

						// Comprobamos que todos los argumentos introducidos
						// sean correctos.
						if (correcto) {
							// COMPROBAMOS SI HAY METODOS DE ENTRADA O DE
							// SALIDA, SI NO LOS HAY, UTILIZAREMOS LOS METODOS
							// NULOS PUES SI NO SE PODRIAN CONFUNDIR.
							if (metodoE == null) {
								metodoE = new NullIn();
							}
							if (metodoS == null) {
								metodoS = new NullOut();
							}

							procesador = new CPU(metodoE, metodoS);

							//Si se ha introducido un archivo batch, el modo interactivo lo cargara.
							if (cmd.hasOption("a")) {
								archivo = new ArchivoLog(args[posicionL + 1]);
								ConsoleInteractive consola = new ConsoleInteractive(
										procesador, args[posicionA + 1], true, archivo);
							//Si no se ha introducido, cargaremos el modo batch normal.
							} else {
								ConsoleInteractive consola = new ConsoleInteractive(
										procesador, null, false, archivo);
							}

						}
						//¿Sobra este else?
						else {
							// SI EL USUARIO HA ESCRITO MODO BATCH COMO
							// ARGUMENTO
							// PERO NO HA ESPECIFICADO FICHERO ASM.
							throw new IOException(
									"Uso incorrecto: Fichero ASM no especificado."
											+ Constantes.LINE_SEPARATOR
											+ "Use -h|--help para más detalles.");
						}

						// SI EL MODO DE EJECUCION ES WINDOW, EJECUTAMOS EL
						// NUEVO PROGRAMA DESDE LA VENTANA.
					} else if (modo[0].equalsIgnoreCase("window")) {
						if (archivoI && archivoO) {
							// Si tenemos los dos archivos, ejecutamos la
							// practica con entrada y salida.
							procesador = new CPU(new WindowInStrategy(metodoE),
									new WindowOutStrategy(args[posicionO + 1]));
						} else if (archivoI && !archivoO) {
							// Si solo tenemos el archivo de entrada, cancelamos
							// el panel salida (no guardara un archivo de texto)
							procesador = new CPU(new WindowInStrategy(metodoE),
									new WindowOutStrategy(null));
						} else if (!archivoI && archivoO) {
							// Si solo tenemos el archivo de salida, cancelamos
							// el panel de entrada y sus funciones.
							procesador = new CPU(new WindowInStrategy(null),
									new WindowOutStrategy(args[posicionO + 1]));
						} else {
							// Si no tenemos ningun archivo ejecutamos la
							// ventana sin ninguna de los dos paneles de entrada
							// o salida.
							procesador = new CPU(new WindowInStrategy(null),
									new WindowOutStrategy(null));
						}
						try {
							// intentamos abrir el archivo que sigue al
							// argumento -a.
							if (cmd.hasOption("a")) {
								contador++;
								posicionA++;
								programa = ASMLoader
										.loadASMFile(args[posicionA]);
								procesador.loadProgram(programa);
							} else
								throw new IOException(
										"Uso incorrecto: Fichero ASM no especificado."
												+ Constantes.LINE_SEPARATOR
												+ "Use -h|--help para más detalles.");

							// Si no hay ningun error en la lectura del
							// programa, pasamos a la ejecucion del
							// programa.

							// Creamos la ventana con la interfaz y la CPU
							// inicializada.
							WindowMain ventana = new WindowMain(procesador);

						} catch (IOException e) {
							System.err.println(e.getMessage());
						} catch (ASMSyntaxErrorException f) {
							System.err.println(f.getMessage());
							System.exit(2);
						}

						// SI ES MODO BATCH, EJECUTAMOS Y CARGAMOS EL FICHERO
						// ASM
					} else if (modo[0].equalsIgnoreCase("batch")) {
						cargarASM = true;
						contador++;
					} else {
						// SI SE HA ESPECIFICADO UN MODO DE EJECUCION QUE NO
						// EXISTE, SALTA UNA EXCEPCION.
						throw new IOException(
								"Uso incorrecto: Modo incorrecto (parametro -m|--mode)"
										+ Constantes.LINE_SEPARATOR
										+ "Use -h|--help para más detalles.");
					}

				} else {
					// SI NO HAY MODO DE EJECUCION, ENTENDEMOS POR DEFECTO EL
					// MODO BATCH:
					cargarASM = true;
				}

				if (cargarASM) {
					if (correcto) {
						// COMPROBAMOS SI HAY METODOS DE ENTRADA O DE SALIDA, SI
						// NO LOS HAY, UTILIZAREMOS LOS METODOS POR DEFECTO DE
						// CONSOLA EN EL MODO BATCH
						if (metodoE == null) {
							metodoE = new ConsoleIn();
						}
						if (metodoS == null) {
							metodoS = new ConsoleOut();
						}

						procesador = new CPU(metodoE, metodoS);
						// SI EXISTE UNA OPCION A CON ARCHIVO ASM, ENTRAMOS Y
						// ABRIMOS EL ARCHIVO
						if (cmd.hasOption("a")) {
							archivo = new ArchivoLog(args[posicionL + 1]);
							ConsoleBatch consola = new ConsoleBatch(procesador,
									args[posicionA + 1], archivo);
							// Si se ha especificado modo batch, pero no ha
							// introducido nombre de archivo.
						} else {
							throw new IOException(
									"Uso incorrecto: Fichero ASM no especificado."
											+ Constantes.LINE_SEPARATOR
											+ "Use -h|--help para más detalles.");
						}

					} else {
						// SI EL USUARIO HA ESCRITO MODO BATCH COMO ARGUMENTO
						// PERO NO HA ESPECIFICADO FICHERO ASM.
						throw new IOException(
								"Uso incorrecto: Fichero ASM no especificado."
										+ Constantes.LINE_SEPARATOR
										+ "Use -h|--help para más detalles.");
					}

				}

				// SI NO SE HA ESPECIFICADO NINGUN ARGUMENTO PARA LA EJECUCION
				// DE LA PRACTICA, LANZAREMOS UNA EXCEPCION.
			} else {
				throw new IOException(
						"Uso incorrecto: Fichero ASM no especificado."
								+ Constantes.LINE_SEPARATOR
								+ "Use -h|--help para más detalles.");

			}

		} catch (IOException e) {
			// SI EXISTE CUALQUIER EXCEPCION DE APERTURA DE ARCHIVOS, SE MUESTRA
			// EL ERROR
			System.err.println(e.getMessage());
			System.exit(1);
		} catch (ParseException f) {
			System.err.println("Uso incorrecto: " + f.getMessage());
		} finally {
			// SI SE HA ABIERTO UN ARCHIVO DE SALIDA, ANTES DE TERMINAR EL
			// PROGRAMA, SE CIERRA EL ARCHIVO PARA QUE SE GUARDE.
			if (archivoO) {
				metodoS.cerrarArchivo();
			}
		}

	}

	private static InMethod inicializarMetodoE(String[] args, InMethod metodoE,
			boolean archivoI, int posicionI) {

		if (archivoI) {
			metodoE = new FileIn(args[posicionI + 1]);
		} else {
			metodoE = null;
		}
		return metodoE;
	}

	private static OutMethod inicializarMetodoO(String[] args,
			OutMethod metodoS, boolean archivoO, int posicionO) {

		if (archivoO) {
			metodoS = new FileOut(args[posicionO + 1]);
		} else {
			metodoS = null;
		}
		return metodoS;
	}

}
