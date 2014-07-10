package br.com.tqi.enquete;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.com.tqi.enquete.exception.EnqueteAtivaException;
import br.com.tqi.enquete.exception.EnqueteInvalidaException;
import br.com.tqi.enquete.exception.EnqueteNaoEncontradaException;

/**
 * Repositório de enquetes. É um singleton responsável pelo armazenamento e
 * obtenção das enquetes disponíveis.
 */
public class EnqueteRepository {

	private static EnqueteRepository instance = new EnqueteRepository();

	private List<Enquete> enquetes = new ArrayList<Enquete>();

	private Long nextId = 1L;

	private EnqueteRepository() {
	}

	/**
	 * @return Repositório de enquetes
	 */
	public static EnqueteRepository getInstance() {
		return instance;
	}

	/**
	 * @return Lista contendo todas as enquetes registradas.
	 */
	public List<Enquete> findAll() {
		return Collections.unmodifiableList(this.enquetes);
	}

	/**
	 * Obtém a enquete com o id informado.
	 * 
	 * @param id
	 *            Indentificador da enquete.
	 * 
	 * @return Enquete com o id informado.
	 * 
	 * @throws EnqueteNaoEncontradaException
	 *             Caso não seja encontrada uma enquete com o id informado.
	 */
	public Enquete find(Long id) throws EnqueteNaoEncontradaException {
		for (Enquete e : this.enquetes) {
			if (e.getId().equals(id)) {
				return e;
			}
		}
		throw new EnqueteNaoEncontradaException();
	}

	/**
	 * Registra uma enquete com os dados informados atribuindo um id para a
	 * mesma.
	 * 
	 * @param enquete
	 *            Enquete a ser registrada.
	 * @throws EnqueteInvalidaException
	 *             Caso a enquete esteja inválida.
	 */
	public void create(Enquete enquete) throws EnqueteInvalidaException {
		enquete.validate();
		enquete.setId(nextId());
		this.enquetes.add(enquete);
	}

	/**
	 * Remove uma enquete pelo id
	 * 
	 * @param id
	 *            Identificador único da enquete
	 * @throws EnqueteNaoEncontradaException
	 *             Caso não seja encontrada uma enquete com o id informado.
	 * @throws EnqueteAtivaException
	 *             Caso a enquete com o id informado esteja ativa.
	 */
	public void delete(Long id) throws EnqueteNaoEncontradaException,
			EnqueteAtivaException {
		Enquete e = find(id);
		if (e.isActive()) {
			throw new EnqueteAtivaException();
		}
		this.enquetes.remove(e);
	}

	/*
	 * Cria um id único para enquetes
	 */
	private synchronized Long nextId() {
		return this.nextId++;
	}

}
