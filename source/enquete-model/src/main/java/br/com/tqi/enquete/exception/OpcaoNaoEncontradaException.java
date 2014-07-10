package br.com.tqi.enquete.exception;

public class OpcaoNaoEncontradaException extends Exception {

	private static final long serialVersionUID = 1L;

	public OpcaoNaoEncontradaException() {
		super("A opção informada é inválida!");
	}
}
