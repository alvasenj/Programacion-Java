package tp.pr5.mv.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;

import tp.pr5.mv.controllers.WindowController;
import tp.pr5.mv.cpu.CPU;
import tp.pr5.mv.gui.ListaPila;
import tp.pr5.mv.gui.TablaMemoria;
import tp.pr5.mv.observers.CPUObserver;
import tp.pr5.mv.observers.MemoryObserver;
import tp.pr5.mv.observers.ObserverMV;
import tp.pr5.mv.observers.OperandStackObserver;

public class WindowMain extends JFrame implements ActionListener,
		OperandStackObserver, MemoryObserver, CPUObserver, ObserverMV, Runnable {

	private static final long serialVersionUID = 1L;

	private CPU maquina;

	private WindowController controlador;

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
	private JPanel barraInferior = new JPanel();

	private JButton botonStep = new JButton();
	private JButton botonRun = new JButton();
	private JButton botonStop = new JButton();
	private JButton botonLoad = new JButton();
	private JButton botonReset = new JButton();
	private JButton botonPush = new JButton("Push");
	private JButton botonPop = new JButton("Pop");
	private JButton botonWrite = new JButton("Write");
	private JButton botonPause = new JButton("Pause");
	private JButton botonBreak = new JButton("BreakPoint");

	private JTextArea programa = new JTextArea(0, 20);
	private JTextArea infoEntrada = new JTextArea(0, 20);
	private JTextArea infoSalida = new JTextArea(0, 20);

	private JTextField numeroPila = new JTextField(7);
	private JTextField numeroPosicion = new JTextField(7);
	private JTextField numeroValor = new JTextField(7);
	private JTextField numeroBreak = new JTextField(7);

	private JLabel valorPila = new JLabel("Valor: ");
	private JLabel valorPosicion = new JLabel("Pos: ");
	private JLabel valorValor = new JLabel("Val: ");
	private JLabel instrucEjecutadas = new JLabel(
			"Núm. Instrucciones ejecutadas: 0  ");
	private JLabel memoriaModif = new JLabel("Memoria modificada  ");
	private JLabel pilaModif = new JLabel("Pila modificada");
	private JLabel parada = new JLabel("Máquina parada");

	private TablaMemoria tabla = new TablaMemoria();
	private ListaPila listapila = new ListaPila();

	private JCheckBox caja1 = new JCheckBox();
	private JCheckBox caja2 = new JCheckBox();

	private int contador = 0;
	private boolean runExecution = false;
	private Thread hebra;
	private Runnable hebraActualizar;
	
	public WindowMain(CPU maquina) {
		super("Maquina virtual de TP");
		this.maquina = maquina;
		this.controlador = new WindowController(maquina);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(1200, 700);
		this.setLocationRelativeTo(null);
		this.setLayout(new BorderLayout());
		crearInterfaz();
		this.setVisible(true);
		
		hebraActualizar = new Runnable() {
			public void run() {
				actualizarPrograma();
				cambiosTexto();
			}
		};
	}

	private void crearInterfaz() {

		// Creamos el panel de los botones.
		ventanaBotones.setLayout(new FlowLayout());
		this.botonStep.addActionListener(this);
		this.botonStep.setText("Step");
		ventanaBotones.add(botonStep);
		java.net.URL url = null;
		url = WindowMain.class.getResource("/img/step.png");
		if (url != null) {
			this.botonStep.setIcon(new ImageIcon(WindowMain.class
					.getResource("/img/step.png")));
		}

		this.botonRun.setText("Run");
		this.botonRun.addActionListener(this);
		ventanaBotones.add(botonRun);
		url = null;
		url = WindowMain.class.getResource("/img/run.png");
		if (url != null) {
			this.botonRun.setIcon(new ImageIcon(WindowMain.class
					.getResource("/img/run.png")));
		}

		this.botonPause.setText("Pause");
		this.botonPause.addActionListener(this);
		ventanaBotones.add(botonPause);
		url = null;
		url = WindowMain.class.getResource("/img/pause.png");
		if (url != null) {
			this.botonPause.setIcon(new ImageIcon(WindowMain.class
					.getResource("/img/pause.png")));
		}
		
		this.botonLoad.setText("Load");
		this.botonLoad.addActionListener(this);
		ventanaBotones.add(botonLoad);
		url = null;
		url = WindowMain.class.getResource("/img/load.png");
		if (url != null) {
			this.botonLoad.setIcon(new ImageIcon(WindowMain.class
					.getResource("/img/load.png")));
		}
		
		this.botonReset.setText("Reset");
		this.botonReset.addActionListener(this);
		ventanaBotones.add(botonReset);
		url = null;
		url = WindowMain.class.getResource("/img/reset.png");
		if (url != null) {
			this.botonReset.setIcon(new ImageIcon(WindowMain.class
					.getResource("/img/reset.png")));
		}

		this.botonStop.setText("Exit");
		this.botonStop.addActionListener(this);
		ventanaBotones.add(botonStop);
		url = null;
		url = WindowMain.class.getResource("/img/exit.png");
		if (url != null) {
			this.botonStop.setIcon(new ImageIcon(WindowMain.class
					.getResource("/img/exit.png")));
		}
		ventanaBotones.setBorder(new TitledBorder(null, "Acciones",
				TitledBorder.LEFT, TitledBorder.TOP, null, null));

		// Agregamos el panel a la ventana principal.
		this.add(ventanaBotones, BorderLayout.NORTH);

		// Creamos el panel del programa.
		ventanaPrograma.setLayout(new BorderLayout());
		ventanaPrograma.setBorder(new TitledBorder(null, "Programa",
				TitledBorder.LEFT, TitledBorder.TOP, null, null));
		programa.setEditable(false);
		// Lo vamos a agregar a un ScrollPanel por si es muy grande.
		JScrollPane panel = new JScrollPane(programa);
		panel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		panel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		ventanaPrograma.add(panel);
		// Cargamos el programa.
		cargarPrograma();
		// Agregamos el panel del programa a la ventana principal.
		this.add(ventanaPrograma, BorderLayout.WEST);

		// Creamos el panel de la informacion que contendra la PILA y la
		// MEMORIA.
		informacion.setLayout(new GridLayout(2, 1));

		datos.setLayout(new GridLayout(1, 2));

		// Definimos el panel de la pila.
		pila.setLayout(new BorderLayout());
		JScrollPane panel2 = new JScrollPane(this.listapila);
		panel2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		panel2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		pila.add(panel2, BorderLayout.NORTH);

		// Definimos las instrucciones posibles referentes a la pila y agregamos
		// sus botones.
		instruccionesPila.setLayout(new FlowLayout());
		instruccionesPila.add(valorPila);
		instruccionesPila.add(numeroPila);
		
		botonPush.setText("Push");
		botonPush.addActionListener(this);
		instruccionesPila.add(botonPush);
		
		botonPop.setText("Pop");
		botonPop.addActionListener(this);
		instruccionesPila2.add(botonPop);

		botonBreak.setText("BreakPoint");
		botonBreak.addActionListener(this);
		instruccionesPila.add(botonBreak);
		instruccionesPila.add(numeroBreak);
		
		pila.add(instruccionesPila, BorderLayout.CENTER);
		pila.add(instruccionesPila2, BorderLayout.SOUTH);

		pila.setBorder(new TitledBorder(null, "Pila de operandos",
				TitledBorder.LEFT, TitledBorder.TOP, null, null));

		// Comenzamos a definir el cuadro de Memoria.
		memoria.setBorder(new TitledBorder(null, "Memoria de la maquina",
				TitledBorder.LEFT, TitledBorder.TOP, null, null));
		memoria.setLayout(new BorderLayout());

		// Agregamos un panel con las instrucciones de memoria.
		instruccionesMemoria.setLayout(new FlowLayout());
		instruccionesMemoria.add(valorPosicion);
		instruccionesMemoria.add(numeroPosicion);
		instruccionesMemoria.add(valorValor);
		instruccionesMemoria.add(numeroValor);

		botonWrite.setText("Write");
		botonWrite.addActionListener(this);
		instruccionesMemoria2.add(botonWrite);

		panelTabla.setLayout(new BorderLayout());

		// Agregamos al panel las partes de la memoria ya creadas, y el panel lo
		// agregamos al panel de Memoria.
		panelTabla.add(instruccionesMemoria, BorderLayout.NORTH);
		panelTabla.add(instruccionesMemoria2, BorderLayout.SOUTH);
		memoria.add(tabla);
		memoria.add(panelTabla, BorderLayout.SOUTH);

		// Agregamos la memoria completa al panel que contiene pila + memoria.
		datos.add(pila);
		datos.add(memoria);
		informacion.add(datos);

		// Creamos dos textAreas que contendran la entrada y la salida.
		archivos.setLayout(new GridLayout(2, 1));
		entrada.setBorder(new TitledBorder(null, "Entrada del programa-p",
				TitledBorder.LEFT, TitledBorder.TOP, null, null));
		salida.setBorder(new TitledBorder(null, "Salida del programa-p",
				TitledBorder.LEFT, TitledBorder.TOP, null, null));

		// Scroll panel para la entrada, por si contiene mucho texto.
		JScrollPane panel3 = new JScrollPane(infoEntrada);
		infoEntrada.setEditable(false);
		panel3.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		panel3.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		entrada.setLayout(new BorderLayout());
		this.maquina.definirTexto(infoEntrada);
		entrada.add(panel3);

		// Scroll panel para la salida, por si contiene mucho texto.
		JScrollPane panel4 = new JScrollPane(infoSalida);
		infoSalida.setEditable(false);
		panel4.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		panel4.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		salida.setLayout(new BorderLayout());
		salida.add(panel4);

		// Agregamos los scrollpanel al panel grande Archivos.
		archivos.add(entrada);
		archivos.add(salida);

		// Agregamos la informacion completa a la ventana principal.
		informacion.add(archivos);
		this.add(informacion);

		// Agregamos la barra inferior de la nueva practica donde añadiremos los
		// estados de la maquina.
		this.caja1.setEnabled(false);
		this.caja2.setEnabled(false);
		barraInferior.setLayout(new FlowLayout());
		barraInferior.add(this.instrucEjecutadas);
		barraInferior.add(caja1);
		barraInferior.add(memoriaModif);
		barraInferior.add(caja2);
		barraInferior.add(pilaModif);
		this.add(barraInferior, BorderLayout.SOUTH);

		this.controlador.addObs(this);
		
		if(programa.getText().equalsIgnoreCase("")){
			maquinaParada();
			programa.setBackground(Color.GRAY);
			JOptionPane.showMessageDialog(null, "El programa cargado esta vacio");
		}
	}

	// PRACTICA 5 - Definimos el funcionamiento de los botones mediante los
	// controladores.
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Step")) {
			this.controlador.stepButton();
			cambiosTexto();
		}
		if (e.getActionCommand().equals("Run")) {
			hebra = new Thread(this);
			hebra.start();
		}
		if (e.getActionCommand().equals("Pause")) {
			activarBotones();
			if (hebra != null) {
				hebra.interrupt();
			}

		}
		
		if (e.getActionCommand().equals("Reset")) {
			this.controlador.reset();
			this.controlador.addObs(this);
			this.tabla.reset();
			this.listapila.reset();
			this.actualizarPrograma();

		}
		
		if (e.getActionCommand().equals("Push")) {
			String numero = numeroPila.getText();
			try {
				// Mientras no se intente meter un numero en blanco.
				if (!numero.equalsIgnoreCase("")) {
					@SuppressWarnings("unused")
					int prueba = Integer.parseInt(numero);
					this.controlador.addCima(numero);
					this.numeroPila.setText("");
				} else {
					mostrarError("Debe introduir un número en la casilla Valor.");
				}
			} catch (Exception f) {
				// Si se introduce un numero no parseable, salta excepcion.
				mostrarError("Debe introduir un número sin caracteres.");
				numeroPila.setText("");
			}
		}
		
		if (e.getActionCommand().equals("Load")) {
			int numero = tabla.devolverNumero();
			try {
				// Mientras no se intente meter un numero en blanco.
				if (numero != -9999) {
					this.controlador.addCima(String.valueOf(numero));
					this.numeroPila.setText("");
				} else {
					mostrarError("Debe introduir un número en la casilla Valor.");
				}
			} catch (Exception f) {
				// Si se introduce un numero no parseable, salta excepcion.
				mostrarError("Debe introduir un número sin caracteres.");
				numeroPila.setText("");
			}
		}
		if (e.getActionCommand().equals("Pop")) {
			this.controlador.deleteCima();
		}
		if (e.getActionCommand().equals("Write")) {
			// Creamos strings con los numeros introducidos por el usuario.
			String stringPosicion = numeroPosicion.getText();
			String stringValor = numeroValor.getText();
			try {
				// Si los strings no estan vacios, continua.
				if ((!stringPosicion.equalsIgnoreCase(""))
						&& (!stringValor.equalsIgnoreCase(""))) {
					// Parseamos la posicion, para comprobar si es negativa.
					int posicion = Integer.parseInt(stringPosicion);
					// Parseamos tambien el valor para ver que no contenga
					// caracteres.
					Integer.parseInt(stringValor);

					// Si no es negativa, continuamos.
					if (posicion >= 0) {
						this.controlador.addCelda(stringPosicion, stringValor);
						numeroValor.setText("");
						numeroPosicion.setText("");
					} else {
						mostrarError("Debe introduir un número mayor que 0 en la casilla Posicion.");
						numeroValor.setText("");
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

		}
		if (e.getActionCommand().equals("BreakPoint")) {
			String valor = this.numeroBreak.getText();
			int numero = Integer.parseInt(valor);
			
			try{
				if(numero >= 0){
					this.controlador.breakPoint(numero);
				} else {
					mostrarError("Debe introduir un número mayor que 0 en la casilla BreakPoint.");
					numeroValor.setText("");
					numeroPosicion.setText("");
				}
				
			}catch(Exception f){
				mostrarError("Debe introduir un número sin caracteres.");
				numeroPila.setText("");
			}
			
		}
		if (e.getActionCommand().equals("Exit")) {
			this.controlador.quitButton();
		}

	}

	// Metodo para mostrar un error por pantalla
	public void mostrarError(String mensaje) {
		JOptionPane.showMessageDialog(null, mensaje);
		this.runExecution = false;
		if(this.controlador.isQuit()){
			maquinaParada();
		} else {
		activarBotones();}
	}

	// Metodo que carga el programa y lo carga en el panel de programa
	private void cargarPrograma() {
		this.controlador.actualizarPrograma(programa);
	}

	// METODOD PARA CAMBIAR EL TEXTO DE SALIDA Y ENTRADA.
	private void cambiosTexto() {
		this.controlador.definirTexto(infoEntrada);
		this.controlador.definirTextoSalida(infoSalida);
	}

	// METODOS DEL OBSERVADOR DE OPERANDSTACK
	public void addCima(String n) {
		listapila.addElement(n);
	}

	// METODOS DEL OBSERVADOR DE OPERANDSTACK
	public void deleteCima() {
		listapila.deleteElement();
	}

	// METODOS DEL OBSERVADOR DE OPERANDSTACK
	public void pilaCambiada() {
		if (!this.caja2.isSelected()) {
			this.caja2.setSelected(true);

		}
	}

	// METODOS DEL OBSERVADOR DE MEMORY
	public void addCelda(int posicion, int valor) {
		tabla.addCelda(posicion, valor);
	}

	// METODOS DEL OBSERVADOR DE MEMORY
	public void memoriaCambiada() {
		if (!this.caja1.isSelected()) {
			this.caja1.setSelected(true);
			this.caja1.setEnabled(false);
		}
	}

	// METODOS DEL OBSERVADOR DE CPU
	public void actualizarTextoEntrada() {
		this.controlador.definirTexto(infoEntrada);
	}

	// METODOS DEL OBSERVADOR DE CPU
	public void actualizarTextoSalida() {
		this.controlador.definirTextoSalida(infoSalida);
	}

	// METODOS DEL OBSERVADOR DE CPU
	public void actualizarPrograma() {
		this.controlador.actualizarPrograma(programa);
		this.contador++;
		this.instrucEjecutadas.setText("Núm. Instrucciones ejecutadas: "
				+ contador + "  ");
	}

	// METODOS DEL OBSERVADOR DE CPU
	public void maquinaParada() {
		programa.setBackground(Color.GRAY);
		botonRun.setEnabled(false);
		botonStep.setEnabled(false);
		botonWrite.setEnabled(false);
		botonPush.setEnabled(false);
		botonPop.setEnabled(false);
		botonPause.setEnabled(false);
		this.maquina.cerrarArchivo();

		if (!this.parada.getText().equalsIgnoreCase("Maquina Parada")) {
			this.parada.setText("Maquina Parada");
			parada.setForeground(Color.RED);
			this.barraInferior.add(parada, 0);
			this.barraInferior.updateUI();
		}
	}

	public void imprimirConsola(String info) {
		// NO USADO EN ESTE MODO DE EJECUCION
	}

	private void desactivarBotones() {
		botonRun.setEnabled(false);
		botonStep.setEnabled(false);
		botonWrite.setEnabled(false);
		botonPush.setEnabled(false);
		botonPop.setEnabled(false);
	}

	private void activarBotones() {
		botonRun.setEnabled(true);
		botonStep.setEnabled(true);
		botonWrite.setEnabled(true);
		botonPush.setEnabled(true);
		botonPop.setEnabled(true);
	}

	@Override
	public void run() {
		runExecution = true;
		desactivarBotones();
		while (runExecution) {
			if (runExecution) {
				try {
					Thread.sleep(220);
					controlador.stepButton();
				} catch (InterruptedException e) {
					runExecution = false;
				}
			}
		}

		runExecution = false;
	}

	@Override
	public void lanzarHebraActualizadora() {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(this.hebraActualizar);
	}

	@Override
	public void pararMaquina() {
		// TODO Auto-generated method stub
		if(runExecution == true){
			runExecution = false;
		} else runExecution = true;
		activarBotones();
	}

}