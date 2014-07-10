package br.com.tqi.enquete.exception;

public class EnqueteNaoEncontradaException extends Exception {

	private static final long serialVersionUID = 1L;

	public EnqueteNaoEncontradaException() {
		super("Enquete não encontrada!");
	}

}
