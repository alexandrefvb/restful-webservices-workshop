package br.com.tqi.enquete.exception;

/**
 * Exception que informa que uma enquete não pode ser removida pois está ativa.
 */
public class EnqueteAtivaException extends Exception {

	private static final long serialVersionUID = 1L;

	public EnqueteAtivaException() {
		super("A enquete informada está ativa e não pode ser removida.");
	}
}
