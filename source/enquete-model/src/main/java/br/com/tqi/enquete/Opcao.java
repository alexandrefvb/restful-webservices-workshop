package br.com.tqi.enquete;

import static br.com.tqi.enquete.exception.EnqueteInvalidaException.throwIf;
import br.com.tqi.enquete.exception.EnqueteInvalidaException;

/**
 * Opção de uma enquete.
 */
public class Opcao {

    private String texto;

    private long votos;

    /**
     * @return Texto da opção.
     */
    public String getTexto() {
	return this.texto;
    }

    /**
     * @return Número de votos que a opção possui.
     */
    public long getVotos() {
	return this.votos;
    }

    /**
     * Realiza um voto na opção.
     */
    public synchronized void vote() {
	this.votos++;
    }

    /**
     * Valida a opção da enquete.
     * 
     * @throws EnqueteInvalidaException
     *             Caso a opção esteja inválida.
     */
    public void validate() throws EnqueteInvalidaException {
	throwIf(this.texto == null || "".equals(this.texto.trim()),
		"Uma das opções foi informada sem texto ou com o texto em branco.");
	this.texto = this.texto.trim();
	// Zera o número de votos da opção (caso o cliente tenha informado um
	// valor diferente de zero)
	this.votos = 0L;
    }
}
