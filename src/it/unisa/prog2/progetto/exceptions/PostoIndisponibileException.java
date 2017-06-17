package it.unisa.prog2.progetto.exceptions;

/**
 * L'eccezione viene lanciata nel caso in cui un posto non è disponibile
 */
public class PostoIndisponibileException extends Exception {
	private static final long serialVersionUID = 1L;
	
	/**
	 * Crea una nuova eccezione PostoIndisponibileException
	 */
	public PostoIndisponibileException () {
		super("Il posto non è disponibile");
	}

	/**
	 * Crea una nuova eccezione PostoIndisponibileException stampando il testo indicato
	 * @param mex Il testo da stampare (Precondizione: mex != null)
	 */
	public PostoIndisponibileException (String mex) {
		super(mex);
	}
}
