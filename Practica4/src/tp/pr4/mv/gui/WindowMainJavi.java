package tp.pr4.mv.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.TitledBorder;

import tp.pr4.mv.cpu.CPU;
import tp.pr4.mv.ins.memory.Pop;
import tp.pr4.mv.ins.memory.Push;
import tp.pr4.mv.ins.memory.Store;

public class WindowMain extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private CPU maquina;

	private JPanel ventanaBotones = new JPanel();
	private JPanel ventanaPrograma = new JPanel();
	private JPanel informacion = new JPanel();
	private JPanel datos = new JPanel();
	private JPanel pila = new JPanel();
	private JPanel memoria = new JPanel();
	private JPanel archivos = new JPanel();
	private JPanel entrada = new JPanel();
	private JPanel salida = new JPanel();
	private JPanel instruccionesPila = new JPanel();
	private JPanel instruccionesPila2 = new JPanel();
	private JPanel instruccionesMemoria = new JPanel();
	private JPanel instruccionesMemoria2 = new JPanel();
	private JPanel panelTabla = new JPanel();

	private JButton botonStep = new JButton();
	private JButton botonRun = new JButton();
	private JButton botonStop = new JButton();
	private JButton botonPush = new JButton("Push");
	private JButton botonPop = new JButton("Pop");
	private JButton botonWrite = new JButton("Write");

	private JTextArea programa = new JTextArea(0, 20);
	private JTextArea infoEntrada = new JTextArea(0, 20);
	private JTextArea infoSalida = new JTextArea(0, 20);

	private JTextField numeroPila = new JTextField(7);
	private JTextField numeroPosicion = new JTextField(7);
	private JTextField numeroValor = new JTextField(7);

	private JLabel valorPila = new JLabel("Valor: ");
	private JLabel valorPosicion = new JLabel("Pos: ");
	private JLabel valorValor = new JLabel("Val: ");

	private TablaMemoria tabla = new TablaMemoria();
	private ListaPila listapila = new ListaPila();

	public WindowMain(CPU maquina) {
		super("Maquina virtual de TP");
		this.maquina = maquina;
		this.maquina.setModoVentana();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(1200, 700);
		this.setLocationRelativeTo(null);
		this.setLayout(new BorderLayout());
		crearInterfaz();
		this.setVisible(true);
		actualizarInterfaz();
	}

	private void crearInterfaz() {

		// Creamos el panel de los botones.
		ventanaBotones.setLayout(new FlowLayout());
		this.botonStep.addActionListener(new StepClass(this.maquina));
		ventanaBotones.add(botonStep);
		this.botonStep.setIcon(new ImageIcon(WindowMain.class
				.getResource("/img/step.png")));
		this.botonRun.addActionListener(new RunClass(this.maquina));
		ventanaBotones.add(botonRun);
		this.botonRun.setIcon(new ImageIcon(WindowMain.class
				.getResource("/img/run.png")));
		this.botonStop.addActionListener(new QuitClass(this.maquina));
		ventanaBotones.add(botonStop);
		this.botonStop.setIcon(new ImageIcon(WindowMain.class
				.getResource("/img/exit.png")));
		ventanaBotones.setBorder(new TitledBorder(null, "Acciones",
				TitledBorder.LEFT, TitledBorder.TOP, null, null));

		// Agregamos el panel a la ventana principal.
		this.add(ventanaBotones, BorderLayout.NORTH);

		// Creamos el panel del programa.
		ventanaPrograma.setLayout(new BorderLayout());
		ventanaPrograma.setBorder(new TitledBorder(null, "Programa",
				TitledBorder.LEFT, TitledBorder.TOP, null, null));
		programa.setEditable(false);
		//Lo vamos a agregar a un ScrollPanel por si es muy grande.
		JScrollPane panel = new JScrollPane(programa);
		panel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		panel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		ventanaPrograma.add(panel);
		//Cargamos el programa.
		cargarPrograma();
		// Agregamos el panel del programa a la ventana principal.
		this.add(ventanaPrograma, BorderLayout.WEST);

		// Creamos el panel de la informacion que contendra la PILA y la MEMORIA.
		informacion.setLayout(new GridLayout(2, 1));

		datos.setLayout(new GridLayout(1, 2));

		//Definimos el panel de la pila.
		pila.setLayout(new BorderLayout());
		JScrollPane panel2 = new JScrollPane(this.listapila);
		panel2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		panel2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		pila.add(panel2, BorderLayout.NORTH);
		
		//Definimos las instrucciones posibles referentes a la pila y agregamos sus botones.
		instruccionesPila.setLayout(new FlowLayout());
		instruccionesPila.add(valorPila);
		instruccionesPila.add(numeroPila);
		botonPush.addActionListener(new PushClass(this.maquina));
		botonPop.addActionListener(new PopClass(this.maquina));
		instruccionesPila.add(botonPush);
		instruccionesPila2.add(botonPop);

		pila.add(instruccionesPila, BorderLayout.CENTER);
		pila.add(instruccionesPila2, BorderLayout.SOUTH);

		pila.setBorder(new TitledBorder(null, "Pila de operandos",
				TitledBorder.LEFT, TitledBorder.TOP, null, null));
		
		//Comenzamos a definir el cuadro de Memoria.
		memoria.setBorder(new TitledBorder(null, "Memoria de la maquina",
				TitledBorder.LEFT, TitledBorder.TOP, null, null));
		memoria.setLayout(new BorderLayout());

		//Agregamos un panel con las instrucciones de memoria.
		instruccionesMemoria.setLayout(new FlowLayout());
		instruccionesMemoria.add(valorPosicion);
		instruccionesMemoria.add(numeroPosicion);
		instruccionesMemoria.add(valorValor);
		instruccionesMemoria.add(numeroValor);

		botonWrite.addActionListener(new WriteClass(this.maquina));
		instruccionesMemoria2.add(botonWrite);

		panelTabla.setLayout(new BorderLayout());
		
		//Agregamos al panel las partes de la memoria ya creadas, y el panel lo agregamos al panel de Memoria.
		panelTabla.add(instruccionesMemoria, BorderLayout.NORTH);
		panelTabla.add(instruccionesMemoria2, BorderLayout.SOUTH);
		memoria.add(tabla);
		memoria.add(panelTabla, BorderLayout.SOUTH);

		//Agregamos la memoria completa al panel que contiene pila + memoria.
		datos.add(pila);
		datos.add(memoria);
		informacion.add(datos);

		//Creamos dos textAreas que contendran la entrada y la salida.
		archivos.setLayout(new GridLayout(2, 1));
		entrada.setBorder(new TitledBorder(null, "Entrada del programa-p",
				TitledBorder.LEFT, TitledBorder.TOP, null, null));
		salida.setBorder(new TitledBorder(null, "Salida del programa-p",
				TitledBorder.LEFT, TitledBorder.TOP, null, null));

		//Scroll panel para la entrada, por si contiene mucho texto.
		JScrollPane panel3 = new JScrollPane(infoEntrada);
		infoEntrada.setEditable(false);
		panel3.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		panel3.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		entrada.setLayout(new BorderLayout());
		this.maquina.definirTexto(infoEntrada);
		entrada.add(panel3);

		//Scroll panel para la salida, por si contiene mucho texto.
		JScrollPane panel4 = new JScrollPane(infoSalida);
		infoSalida.setEditable(false);
		panel4.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		panel4.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		salida.setLayout(new BorderLayout());
		salida.add(panel4);

		//Agregamos los scrollpanel al panel grande Archivos.
		archivos.add(entrada);
		archivos.add(salida);
		
		//Agregamos la informacion completa a la ventana principal.
		informacion.add(archivos);
		this.add(informacion);

	}
	
	//Metodo para mostrar un error por pantalla
	private void mostrarError(String mensaje){
		JOptionPane
		.showMessageDialog(null, mensaje);
	}
	
	//Metodo que carga el programa y lo carga en el panel de programa
	private void cargarPrograma() {
		String[] programaEnString = this.maquina.devolverPrograma();

		if (programaEnString != null) {
			for (int i = 0; i < programaEnString.length; i++) {
				this.programa.append(programaEnString[i]);
			}
		} else {
			mostrarError("El programa cargado esta vacio, se cierra la aplicacion.");
			System.exit(2);
		}
	}

	//Metodo para actualizar toda la interfaz.
	private void actualizarInterfaz() {
		programa.setText("");
		cargarPrograma();
		
		//Actualizamos la tabla de la memoria con los nuevos datos de la cpu(su memoria).
		tabla.actualizarTabla(this.maquina);
		//Actualizamos la lista de la pila con los nuevos datos de la cpu(su pila).
		this.listapila.agregar(this.maquina.devolverPila());
		//Redefinimos el texto de entrada y salida por si se han ejecutado instrucciones IN o Out;
		this.maquina.definirTexto(infoEntrada);
		this.maquina.definirTextoSalida(infoSalida);
		
		//Si se ha terminado la ejecucion del programa, procedemos al cierre de la interaccion.
		if (maquina.isQuit()) {
			programa.setBackground(Color.GRAY);
			botonRun.setEnabled(false);
			botonStep.setEnabled(false);
			botonWrite.setEnabled(false);
			botonPush.setEnabled(false);
			botonPop.setEnabled(false);
			this.maquina.cerrarArchivo();
		}
		
		this.update(getGraphics());
	}

	// DEFINIMOS EL FUNCIONAMIENTO DEL BOTON Step
	private class StepClass implements ActionListener {
		private CPU cpu;

		public StepClass(CPU programa) {
			this.cpu = programa;
		}

		public void actionPerformed(ActionEvent e) {
			// EJECUTAMOS LA INSTRUCCION
			cpu.step();

			if (!cpu.isQuit()) {
				actualizarInterfaz();
			} else {
				botonRun.setEnabled(false);
				botonStep.setEnabled(false);
				actualizarInterfaz();

			}
		}

	}

	// DEFINIMOS EL FUNCIONAMIENTO DEL BOTON Quit
	private class QuitClass implements ActionListener {
		private CPU programa;

		public QuitClass(CPU programa) {
			this.programa = programa;
		}

		public void actionPerformed(ActionEvent e) {
			String[] botones = { "Si", "No" };
			int seleccion = JOptionPane.showOptionDialog(null,
					"¿Seguro que quieres salir?", "Atención",
					JOptionPane.YES_NO_OPTION, 0, null, botones, botones);
			// SI == SALIR, CC == CANCELAR SALIDA
			if (seleccion == 0) {
				programa.cerrarArchivo();
				programa.detenerMaquina();
				System.exit(0);
			}
			actualizarInterfaz();
		}
	}

	// DEFINIMOS EL FUNCIONAMIENTO DEL BOTON Run
	private class RunClass implements ActionListener {
		private CPU cpu;

		public RunClass(CPU programa) {
			this.cpu = programa;
		}

		public void actionPerformed(ActionEvent e) {
			//Mientras que no haya errores se ejecuta el Run, cuando salta excepcion, se detiene.
			boolean noError = true;
			while ((!cpu.isQuit()) && (noError)) {
				noError = cpu.step();

				if (!cpu.isQuit()) {
					actualizarInterfaz();
				}
			}

			actualizarInterfaz();
		}
	}

	// DEFINIMOS EL FUNCIONAMIENTO DEL BOTON Push
	private class PushClass implements ActionListener {
		private CPU cpu;

		public PushClass(CPU programa) {
			this.cpu = programa;
		}

		public void actionPerformed(ActionEvent e) {
			String numero = numeroPila.getText();
			boolean noError = true;
			if ((!cpu.isQuit()) && (noError)) {
				try {
					//Mientras no se intente meter un numero en blanco.
					if (!numero.equalsIgnoreCase("")) {
						Integer.parseInt(numero);
						noError = cpu.step(new Push(numero));

					} else {
						mostrarError("Debe introduir un número en la casilla Valor.");
					}
				} catch (Exception f) {
					//Si se introduce un numero no parseable, salta excepcion.
					mostrarError("Debe introduir un número sin caracteres.");
					numeroPila.setText("");
				}

				if (!cpu.isQuit()) {
					// ACTUALIZAMOS LA INTERFAZ
					actualizarInterfaz();
				}
			}

		}
	}

	// DEFINIMOS EL FUNCIONAMIENTO DEL BOTON Pop
	private class PopClass implements ActionListener {
		private CPU cpu;

		public PopClass(CPU programa) {
			this.cpu = programa;
		}

		public void actionPerformed(ActionEvent e) {
			boolean noError = true;
			if ((!cpu.isQuit()) && (noError)) {
				noError = cpu.step(new Pop());
			}

			if (!cpu.isQuit()) {
				actualizarInterfaz();
			}
		}

	}

	// DEFINIMOS EL FUNCIONAMIENTO DEL BOTON Write
	private class WriteClass implements ActionListener {
		private CPU cpu;

		public WriteClass(CPU programa) {
			this.cpu = programa;
		}

		public void actionPerformed(ActionEvent e) {
			//Creamos strings con los numeros introducidos por el usuario.
			String stringPosicion = numeroPosicion.getText();
			String stringValor = numeroValor.getText();
			boolean noError = true;
			if ((!cpu.isQuit()) && (noError)) {
				try {
					//Si los strings no estan vacios, continua.
					if ((!stringPosicion.equalsIgnoreCase(""))
							&& (!stringValor.equalsIgnoreCase(""))) {
						//Parseamos la posicion, para comprobar si es negativa.
						int posicion = Integer.parseInt(stringPosicion);
						Integer.parseInt(stringValor);
						
						//Si no es negativa, continuamos.
						if (posicion >= 0) {
							cpu.step(new Push(stringValor));
							cpu.step(new Store(stringPosicion));
						} else {
							mostrarError("Debe introduir un número mayor que 0 en la casilla Posicion.");
							numeroPosicion.setText("");
						}

					} else {
						mostrarError("Debe introduir un número en la casilla Posicion y otro en Valor.");
					}
				} catch (Exception f) {
					mostrarError("Debe introduir un número sin caracteres.");
					numeroPosicion.setText("");
					numeroValor.setText("");
				}

				if (!cpu.isQuit()) {
					actualizarInterfaz();
				}
			}

		}

	}

}