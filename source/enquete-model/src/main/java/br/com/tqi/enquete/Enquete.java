package br.com.tqi.enquete;

import static br.com.tqi.enquete.exception.EnqueteInvalidaException.throwIf;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import br.com.tqi.enquete.exception.EnqueteInativaException;
import br.com.tqi.enquete.exception.EnqueteInvalidaException;
import br.com.tqi.enquete.exception.OpcaoNaoEncontradaException;

/**
 * Uma enquete possui uma pergunta e um conjunto de opções que podem ser
 * votadas. Ao ser criada uma enquete recebe um identificador único.
 */
public class Enquete {

    private Long id;

    private String pergunta;

    private List<Opcao> opcoes;

    private Date inicio;

    private Date fim;

    /**
     * @return Identificador único da enquete.
     */
    public Long getId() {
	return this.id;
    }

    /**
     * @return Pergunta da enquete.
     */
    public String getPergunta() {
	return this.pergunta;
    }

    /**
     * Encontra a opção com o texto informado para votar na mesma.
     * 
     * @param texto
     *            Texto correspondente à opção
     * @return Opção correspondente ao texto.
     * @throws OpcaoNaoEncontradaException
     *             Caso a opção não exista na enquete.
     * @throws EnqueteInativaException
     *             Caso a enquete esteja inativa.
     */
    public Opcao findOpcaoToVote(String texto)
	    throws OpcaoNaoEncontradaException, EnqueteInativaException {
	if (!isActive()) {
	    throw new EnqueteInativaException();
	}
	return findOpcao(texto);
    }

    /**
     * Encontra uma opção com o texto informado.
     * 
     * @param texto
     *            Texto da opção a ser encontrada.
     * @return Opção correspondente ao texto informado.
     * @throws OpcaoNaoEncontradaException
     *             Caso a opção não seja encontrada.
     */
    public Opcao findOpcao(String texto) throws OpcaoNaoEncontradaException {
	for (Opcao o : this.opcoes) {
	    if (o.getTexto().equals(texto)) {
		return o;
	    }
	}
	throw new OpcaoNaoEncontradaException();
    }

    /**
     * @return Lista de opções da enquete
     */
    public List<Opcao> getOpcoes() {
	return Collections.unmodifiableList(this.opcoes);
    }

    /**
     * @return Data de início da enquete.
     */
    public Date getInicio() {
	return this.inicio;
    }

    /**
     * @return Data de fim da enquete.
     */
    public Date getFim() {
	return fim;
    }

    /**
     * Atribui o id de uma enquete. É utilizado pelo EnqueteRepository para
     * atribuir o id de uma enquete quando a mesma é criada.
     * 
     * @param id
     *            Identificador único da enquete.
     */
    void setId(Long id) {
	this.id = id;
    }

    /**
     * Valida os dados da enquete. É chamado antes de criar uma enquete para
     * validar se os dados da mesma estão corretos.
     * 
     * @throws EnqueteInvalidaException
     *             Caso os dados estejam incorretos.
     */
    public void validate() throws EnqueteInvalidaException {
	throwIf(this.pergunta == null || "".equals(this.pergunta.trim()),
		"A pergunta da enquete n\u00e3o foi informada ou est\u00e1 em branco.");
	this.pergunta = this.pergunta.trim();
	throwIf(this.opcoes == null || this.opcoes.size() < 2,
		"A enquete deve ter pelo menos duas op\u00e7\u00f5es.");
	throwIf(this.inicio == null,
		"A data de in\u00edcio da enquete n\u00e3o foi informada.");
	throwIf(this.fim == null,
		"A data de fim da enquete n\u00e3o foi informada.");

	for (Opcao o : this.opcoes) {
	    o.validate();
	}
    }

    public boolean isActive() {
	Date d = new Date();
	return d.after(this.inicio) && d.before(this.fim);
    }
}
