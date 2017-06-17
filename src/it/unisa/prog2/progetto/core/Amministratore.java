package it.unisa.prog2.progetto.core;

/**
 * Implementa il concetto di un amministratore di sistema,
 * caratterizzato da nome, cognome, username e password e identificativo univoco.
 */

public class Amministratore extends Account  {
	
	/**
	 * Crea un ammnistratore con i seguenti parametri
	 * @param nome il nome (Precondizione: nome != null && nome != "")
	 * @param cognome il cognome (Precondizione: cognome != null && cognome != "")
	 * @param username l'username (Precondizione: username != null && username != "")
	 * @param password la password (Precondizione: password != null && password != "")
	 * @param id l'identificativo (Precondizione: id > 0)
	 */
	public Amministratore(String nome, String cognome, String username, String password, int id) {
		super(nome, cognome, username, password, id);
	}
	
	/**
	 * Restituisce lo stato dell'oggetto sotto forma di stringa
	 * @return una stringa rappresentante lo stato dell'oggetto
	 */
	public String toString() {
		return super.toString();
	}

	/**
	 * Confronta un amministratore con un altro oggetto
	 * @param other l'oggetto con cui confrontare
	 * @return true se sono uguali, false altrimenti
	 */
	public boolean equals(Object other) {
		return super.equals(other);
	}

	/**
	 * Clona un amministratore
	 * @return un altro oggetto Amministratore avente lo stato identico all'amministratore corrente
	 */
	public Object clone() throws CloneNotSupportedException{
		try{
			return (Amministratore) super.clone();
		}
		catch (CloneNotSupportedException e)
		{
			return null;
		}
	}
	
	private static final long serialVersionUID = 1L;
}
