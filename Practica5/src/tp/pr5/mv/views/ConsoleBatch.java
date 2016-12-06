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
import tp.pr5.mv.observers.ConsoleObserver;
import tp.pr5.mv.observers.MemoryObserver;
import tp.pr5.mv.observers.OperandStackObserver;
import tp.pr5.mv.stategyOut.ArchivoLog;

public class ConsoleBatch implements OperandStackObserver, MemoryObserver,
		ConsoleObserver {

	private String nombreArchivo;
	private ConsoleController controlador;

	public ConsoleBatch(CPU procesador, String nombre, ArchivoLog archivo) throws IOException {
		this.nombreArchivo = nombre;
		this.controlador = new ConsoleController(procesador);
		this.controlador.addObs(this);
		inicializar();
	}

	public void inicializar() throws IOException {
		ProgramMV programa = new ProgramMV();

		try {
			// intentamos abrir el archivo que sigue al
			// argumento -a.
			programa = ASMLoader.loadASMFile(this.nombreArchivo);

			// Mostramos el programa por pantalla
			System.out.println("El programa introducido es:");
			System.out.println(programa.toString());
			controlador.loadProgram(programa);

			controlador.run();

		} catch (IOException e) {
			throw new IOException("Error al acceder al fichero de entrada ("
					+ this.nombreArchivo + ")" + Constantes.LINE_SEPARATOR
					+ "Use -h|--help para más detalles.");
		} catch (ASMSyntaxErrorException f) {
			System.err.println(f.getMessage());
			System.exit(2);
		}
		

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
	}

	@Override
	public void imprimirConsola(String info) {
		System.out.println(info);
	}

	@Override
	public void mostrarError(String message) {
		System.err.println(message);

		if (!this.controlador.isQuit()) {
			controlador.configurarComando();
			System.out.print(">");
			CommandParser lectorcomando = new CommandParser();
			Scanner lectura1 = new Scanner(System.in);
			String comandoleido = lectura1.nextLine();
			@SuppressWarnings("static-access")
			CommandInterpreter comando = lectorcomando
					.parseCommand(comandoleido);

			if (comando != null) {
				try {
					controlador.ejecutarComando(comando);
					controlador.run();
				} catch (MVTrap e) {
					mostrarError(e.getMessage());
				}
			} else {
				mostrarError("No entiendo ese comando.");
			}
		}
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