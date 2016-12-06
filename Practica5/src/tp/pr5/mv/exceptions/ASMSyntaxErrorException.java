package tp.pr5.mv.exceptions;

/**
 * @author Javier Druet
 * @author Alvaro Asenjo
 * 
 */

// Excepcion de la maquina virtual que da error al intentar parsear diferentes
// instrucciones de un archivo de texto asm.
public class ASMSyntaxErrorException extends Throwable {

	private static final long serialVersionUID = 1L;

	// constructora por defecto de una excepcion de tipo ASMSyntaxError
	public ASMSyntaxErrorException() {
		super();
	}

	// constructora con mensaje de una excepcion de tipo ASMSyntaxError
	public ASMSyntaxErrorException(String mensaje) {
		super(mensaje);
	}

	// constructora con una excepcion de una excepcion de tipo ASMSyntaxError
	public ASMSyntaxErrorException(Throwable args) {
		super(args);
	}
}
