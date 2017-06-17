package it.unisa.prog2.progetto.core;

import java.io.Serializable;

/**
 * Mantiene le informazioni circa un posto di un settore di uno stadio; nel dettaglio conserva:
 * il numero e la lettera del posto che lo identificano all'interno di un determinato settore.
 * In più ciascun oggetto di tipo Posto è fornito di tre variabili booleane che ci indicano se il posto è 
 * disponibile, prenotato e venduto. 
 */
public class Posto implements Cloneable , Serializable{

	/**
	 * Crea un nuovo acquisto, il quale contiene tre variabili booleane che indicano la 
	 * disponibilità,la vendibilità e la prenotabilità delposto e inoltre contiene i seguenti parametri:
	 * @param numero il numero (Precondizione: numero>0)
	 * @param lettera la lettera (Precondizione: lettera>=65 && lettera<=90)
	 */
	public Posto(int numero,char lettera) {
		this.numero=numero;
		this.lettera=lettera;
		this.disponibile=true;
		this.prenotato=false;
		this.venduto=false;
		
	}

	/**
	 * Restituisce il numero del posto
	 * @return il numero
	 */
	public int getNumero() {
		return numero;
	}
	
	/**
	 * Setta il numero del posto
	 * @param numero il numero (Precondizione: numero > 0)
	 */
	public void setNumero(int numero) {
		this.numero = numero;
	}
	
	/**
	 * Restituisce la lettera del posto
	 * @return la lettera
	 */
	public char getLettera() {
		return lettera;
	}
	
	/**
	 * Setta la lettera del posto
	 * @param lettera la lettera (Precondizione: lettera>=65 && lettera<=90)
	 */
	public void setLettera(char lettera) {
		this.lettera = lettera;
	}
	
	/**
	 * Restiuisce un booleano che ci indica la disponibilità del posto
	 * @return True se è disponibile, False altrimenti
	 */
	public boolean isDisponibile() {
		return disponibile;
	}
	
	/**
	 * Restiuisce un booleano che ci indica la vendibilità del posto
	 * @return True se è stato venduto, False altrimenti
	 */
	public boolean isVenduto() {
		return venduto;
	}

	/**
	 * Restiuisce un booleano che ci indica la prenotabilità del posto
	 * @return True se è stato prenotato, False altrimenti
	 */
	public boolean isPrenotato() {
		return prenotato;
	}

	
	/**
	 * Setta la variabile booleana disponibile a seconda del parametro passato che indicherà se il posto è disponibile o no.
	 * Le restanti variabili booleane verranno settate al not del parametro passato.
	 * @param set il set che indica la disponibilità del posto
	 */
	public void setDisponibile(boolean set) {
		disponibile =set;
		prenotato=!set;
		venduto=!set;
	}
	
	/**
	 * Setta la variabile booleana prenotato a seconda del parametro passato che indicherà se il posto è prenotato o no.
	 * Le restanti variabili booleane verranno settate al not del parametro passato.
	 * @param set il set che indica la prenotabilità del posto
	 */
	public void setPrenotato(boolean set) {
		prenotato = set;
		venduto=!set;
		disponibile=!set;
	}
	
	/**
	 * Setta la variabile booleana venduto a seconda del parametro passato che indicherà se il posto è venduto o no.
	 * Le restanti variabili booleane verranno settate al not del parametro passato.
	 * @param set il set che indica la vendibilità del posto
	 */
	public void setVenduto(boolean set) {
		venduto = set;
		disponibile=!set;
		prenotato=!set;
	}
	
	/**
	 * Il metodo restituisce lo stato dell'oggetto sotto forma di stringa.
	 * Sovrascrive il toString() della classe Object.
	 * @return Una stringa rappresentante lo stato dell'oggetto
	 */
	public String toString() {
		return getClass().getName()+"[numero="+ numero +", lettera=" + lettera +", disponibile=" +
				disponibile + ", prenotato=" + prenotato +", venduto=" + venduto + "]";
	}

	
	/**
	 * Verifica se due oggetti di tipo Posto sono uguali; nello specifico si confrontano:
	 * l'oggetto su cui è stato invocato il metodo e l'oggetto passato come parametro esplicito
	 * @param obj L'oggetto da confrontare (Precondizione: obj != null)
	 * @return True se gli oggetti sono uguali, False altrimenti
	 */
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Posto o = (Posto) obj;
		return numero==o.numero && lettera==o.lettera && disponibile==o.disponibile 
				&& prenotato==o.prenotato && venduto==o.venduto;
	}
	
	/**
	 * Crea e restituisce una copia dell'oggetto su cui è stato invocato il metodo
	 * @return L'oggetto clonato
	 * @throws CloneNotSupportedException
	 */
	public Object clone() throws CloneNotSupportedException{
		try
		{
			return (Posto) super.clone();
		}
		catch(CloneNotSupportedException e)
		{
			return null;
		}
		
	}

	private int numero;
	private char lettera;
	private boolean disponibile;
	private boolean prenotato;
	private boolean venduto;
	private static final long serialVersionUID = 1L;

}
