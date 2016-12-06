package tp.pr5.mv.views;

import java.io.IOException;
import java.util.Scanner;

import tp.pr5.mv.ASMLoader;
import tp.pr5.mv.Constantes;
import tp.pr5.mv.command.CommandInterpreter;
import tp.pr5.mv.command.CommandParser;
import tp.pr5.mv.controllers.ConsoleController;
import tp.pr5.mv.cpu.CPU;
import tp.pr5.mv.cpu.ProgramMV;
import tp.pr5.mv.exceptions.ASMSyntaxErrorException;
import tp.pr5.mv.exceptions.MVTrap;
import tp.pr5.mv.ins.Instruction;
import tp.pr5.mv.ins.InstructionParser;
import tp.pr5.mv.observers.ConsoleObserver;
import tp.pr5.mv.observers.MemoryObserver;
import tp.pr5.mv.observers.OperandStackObserver;
import tp.pr5.mv.stategyOut.ArchivoLog;

public class ConsoleInteractive implements OperandStackObserver,
		MemoryObserver, ConsoleObserver {

	// private CPU procesador;
	private String nombreArchivo;
	private boolean existeASM = false;
	private ConsoleController controlador;
	ArchivoLog archivo;

	public ConsoleInteractive(CPU procesador, String nombre, boolean asm, ArchivoLog archivo)
			throws IOException {
		this.nombreArchivo = nombre;
		this.existeASM = asm;
		this.controlador = new ConsoleController(procesador);
		this.controlador.addObs(this);
		this.archivo = archivo;
		inicializar();
	}

	@SuppressWarnings("static-access")
	public int inicializar() throws IOException {
		boolean terminado = false;
		boolean terminado2 = false;
		ProgramMV programa = new ProgramMV();

		if (existeASM) {
			try {
				// intentamos abrir el archivo que sigue al
				// argumento -a.
				programa = ASMLoader.loadASMFile(nombreArchivo);

				// Mostramos el programa por pantalla

			} catch (IOException e) {
				throw new IOException(
						"Error al acceder al fichero de entrada ("
								+ nombreArchivo + ")"
								+ Constantes.LINE_SEPARATOR
								+ "Use -h|--help para más detalles.");
			} catch (ASMSyntaxErrorException f) {
				System.err.println(f.getMessage());
				archivo.writeConsola(f.getMessage());
				System.exit(2);
			}
		} else {
			imprimirInicio("Introduce el programa fuente: ");
			archivo.writeConsola("Introduce el programa fuente: ");
			Scanner lectura = new Scanner(System.in);

			while (!terminado) {
				// RECIBIMOS LA INSTRUCCION ESCRITA POR EL
				// USUARIO
				imprimirSimbolo();
				InstructionParser lectorinstruc = new InstructionParser();
				String leido = lectura.nextLine();
				Instruction instruccion = null;
				// INTENTAMOS PARSEAR LA INSTRUCCION, QUE SI
				// FALLA, SALTARA UNA EXCEPCION.
				try {
					instruccion = lectorinstruc.parse(leido);
				} catch (MVTrap e) {
					mostrarError(e.getMessage());
				}
				if (instruccion != null) {
					programa.addInstruction(instruccion);
				}
				// SI LA INSTRUCCION NO SE ENTIENDE, DA
				// ERROR,
				// CASO
				// CONTRARIO SE
				// GUARDA EN EL PROGRAMA

				if (leido.equalsIgnoreCase("END")) {
					terminado = true;
				}
			}
		}

		// MOSTRAR EL PROGRAMA SI EXISTE
		imprimirInicio("El programa introducido es:");
		archivo.writeConsola("El programa introducido es:");
		imprimirInicio(programa.toString());
		archivo.writeConsola(programa.toString());

		controlador.loadProgram(programa);

		// RECIBIMOS LA INSTRUCCION ESCRITA POR EL USUARIO
		while (!terminado2) {
			imprimirSimbolo();
			CommandParser lectorcomando = new CommandParser();
			Scanner lectura1 = new Scanner(System.in);
			String comandoleido = lectura1.nextLine();
			CommandInterpreter comando = lectorcomando
					.parseCommand(comandoleido);

			// CADA VEZ QUE LEES UN COMANDO, LE PASAS UNA
			// CPU INICIALIZADA

			controlador.configurarComando();

			// SI LA INSTRUCCION NO SE ENTIENDE, DA ERROR,
			// CASO CONTRARIO SE EJECUTA
			if (comando != null) {
				try {
					terminado2 = controlador.ejecutarComando(comando);
				} catch (MVTrap e) {
					mostrarError(e.getMessage());
				}
			} else {
				mostrarError("No entiendo ese comando.");
			}
		}
		archivo.cerrarArchivo();

		return 0;
	}

	private void imprimirSimbolo() {
		System.out.print(">");
		archivo.writeConsola(">");
	}

	@Override
	public void memoriaCambiada() {
		// TODO Auto-generated method stub

	}

	@Override
	public void addCelda(int posicion, int valor) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actualizarPrograma() {
		// TODO Auto-generated method stub

	}

	@Override
	public void actualizarTextoEntrada() {
		// TODO Auto-generated method stub

	}

	@Override
	public void actualizarTextoSalida() {
		// TODO Auto-generated method stub

	}

	@Override
	public void maquinaParada() {
		// TODO Auto-generated method stub

	}

	@Override
	public void addCima(String n) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteCima() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pilaCambiada() {
	}

	@Override
	public void imprimirInicio(String frase) {
		System.out.println(frase);
		archivo.writeConsola(frase);
	}

	@Override
	public void imprimirConsola(String info) {
		System.out.println(info);
		archivo.writeConsola(info);
	}

	@Override
	public void mostrarError(String message) {
		System.err.println(message);
		archivo.writeConsola(message);
	}

	@Override
	public void lanzarHebraActualizadora() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pararMaquina() {
		// TODO Auto-generated method stub
		
	}

}
