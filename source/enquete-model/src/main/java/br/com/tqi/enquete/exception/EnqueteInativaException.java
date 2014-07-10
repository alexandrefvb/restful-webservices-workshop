package br.com.tqi.enquete.exception;

/**
 * Exception que informa que uma enquete não pode ser removida pois está ativa.
 */
public class EnqueteInativaException extends Exception {

	private static final long serialVersionUID = 1L;

	public EnqueteInativaException() {
		super("Não é possível votar em uma enquete inativa.");
	}
}
