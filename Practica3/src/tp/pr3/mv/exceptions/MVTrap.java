package tp.pr3.mv.exceptions;

/**
 * @author Javier Druet
 * @author Álvaro Asenjo
 * 
 */

//Excepción de ejecución de la propia máquina virtual, cuando alguna instrucción falla al ejecutarse.
public class MVTrap extends Exception {
	private static final long serialVersionUID = 1L;

	// constructora por defecto de una excepcion de tipo MVTrap
	public MVTrap() {
		super();
	}

	// constructora con mensaje de una excepcion de tipo MVTrap
	public MVTrap(String mensaje) {
		super(mensaje);
	}

	// constructora con un mensaje y una excepcion de tipo MVTrap
	public MVTrap(String mensaje, Throwable e) {
		super(mensaje, e);
	}

	// constructora con una excepcion de una excepcion de tipo MVTrap
	public MVTrap(Throwable e) {
		super(e);
	}

}
