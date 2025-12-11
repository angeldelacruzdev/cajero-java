package exceptions;

public class FondosInsuficientesException extends Exception {

	private static final long serialVersionUID = 1969295425277897206L;

	public FondosInsuficientesException(String mensaje) {
        super(mensaje);
    }

}
