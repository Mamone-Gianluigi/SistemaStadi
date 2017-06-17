package it.unisa.prog2.progetto.core;

import java.io.Serializable;

/** La classe implementa il concetto di Account di un'applicazione informatica
 * ovvero di un'entità  che interagisce con il sistema, caratterizzata da un identificativo
 * univoco, da un nome, da un cognome, da un username e da una password 
 */
public abstract class Account implements Cloneable , Serializable {

	/** Crea un Account sulla base dei seguenti parametri
	 * @param nome il nome (Precondizione: nome != null && nome != "")
	 * @param cognome il cognome (Precondizione: cognome != null && cognome != "")
	 * @param username l'username (Precondizione: username != null && username != "")
	 * @param password la password (Precondizione: password != null && password != "")
	 * @param id l'identificativo (Precondizione: id > 0)
	 */
	public Account(String nome, String cognome, String username, String password, int id) {
		this.nome = nome;
		this.cognome = cognome;
		this.username = username;
		this.password = password;
		this.id = id;
	}

	/**
	 * Restituisce l'identificativo univoco dell'utente
	 * @return L'id dell'utente
	 */
	public int getId() {
		return id;
	}

	/**
	 * Restituisce il nome del proprietario dell'account
	 * @return Il nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Restituisce il cognome del proprietario dell'account
	 * @return Il cognome
	 */
	public String getCognome() {
		return cognome;
	}

	/**
	 * Restituisce l'username dell'account
	 * @return username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Restituisce la password dell'account
	 * @return password dell'account
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Setta l'identificativo univoco dell'utente
	 * @param id l'identificativo(Precondizione: id > 0)
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Setta il nome del proprietario dell'account
	 * @param nome il nome(Precondizione: nome != null && nome != "")
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Setta il cognome del proprietario dell'account
	 * @param cognome il cognome (Precondizione: cognome != null && cognome != "")
	 */
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	/**
	 * Setta l'username dell'account
	 * @param username l'username (Precondizione: username != null && username != "")
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Setta la password dell'account
	 * @param nuovaPassword la nuova password (Precondizione: nuovaPassword != null && nuovaPassword != "")
	 */
	public void setPassword(String nuovaPassword) {
		this.password = nuovaPassword;
	}

	/**
	 * Il metodo restituisce lo stato dell'oggetto sotto forma di stringa.
	 * Sovrascrive il toString() della classe Object.
	 * @return Una stringa rappresentante lo stato dell'oggetto
	 */
	public String toString() {
		return this.getClass().getName() + " [id=" + id + ", nome=" + nome + ", cognome=" + cognome
				+ ", username=" + username + ", password=" + password + "]";
	}
	
	/**
	 * Verifica se due oggetti di tipo Account sono uguali; nello specifico si confrontano:
	 * l'oggetto su cui è stato invocato il metodo e l'oggetto passato come parametro esplicito
	 * @param other L'oggetto da confrontare (Precondizione: other != null)
	 * @return True se gli oggetti sono uguali, False altrimenti
	 */
	public boolean equals (Object other) {
		if (other == null) 
			return false;
		if (getClass() != other.getClass())
			return false;
		Account o = (Account)other;
		return (id==o.id && nome.equals(o.nome) && cognome.equals(o.cognome) 
					&& username.equals(o.username));
	}

	/**
	 * Crea e restituisce una copia dell'oggetto su cui è stato invocato il metodo
	 * @return L'oggetto clonato
	 */
	public Object clone() throws CloneNotSupportedException {
		try {
			Account copia = (Account)super.clone(); 
			return copia;
		}
		catch (CloneNotSupportedException e){
			// Non facciamo nulla; restituiamo null
			return null;
		}
	}
	
	private int id;
	private String nome;
	private String cognome;
	private String username;
	private String password;
	private static final long serialVersionUID = 1L;

}
