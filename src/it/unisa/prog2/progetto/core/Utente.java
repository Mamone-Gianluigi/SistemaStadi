package it.unisa.prog2.progetto.core;

import java.util.ArrayList;
import it.unisa.prog2.progetto.exceptions.*;

/**
 * Implementa il concetto di utente dotato di nome, cognome, username, password, 
 * categoria a cui appartiene (es: studente), l'identificativo, il credito inizialmente pari a 0, 
 * una lista degli acquisti effettuati, una lista delle prenotazioni effettuate e il carrelo, ovvero una lista delle prenotazioni in corso.
 */
public class Utente extends Account implements Cloneable {
	
	/**
	 * Costruisce un utente che conserva il credito,una lista degli acquisti, una lista delle prenotazioni, 
	 * una lista delle prenotazioni in corso e i seguenti parametri:
	 * @param nome nome (Precondizione: nome != null && nome != "")
	 * @param cognome cognome (Precondizione: cognome != null && cognome != "")
	 * @param username username (Precondizione: username != null && username != "")
	 * @param password password (Precondizione: password != null && password != "")
	 * @param categoria categoria (Precondizione: categoria != null && categoria != "")
	 * @param id identificativo (Precondizione: id> 0)
	 * @throws DatiNonValidiException 
	 */
	public Utente(String nome, String cognome,String username, String password, String categoria ,int id) throws DatiNonValidiException {

		super(nome, cognome, username, password, id);
		this.credito = 0;
		storicoAcquisti = new ArrayList<Acquisto>();
		storicoPrenotazioni = new ArrayList<Prenotazione>();
		carrello=new ArrayList<Prenotazione>();
		this.categoria=categoria;
	}

	/**
	 * Restituisce lo storico degli acquisti dell'utente
	 * @return storico degli acquisti
	 */	
	@SuppressWarnings("unchecked")
	public ArrayList<Acquisto> getStoricoAcquisti() {
		return (ArrayList<Acquisto>)storicoAcquisti.clone();
	}

	/**
	 * Permette di aggiungere un acquisto allo storico degli acquisti dell'utente
	 * @param a acquisto da aggiungere allo storico (Precondizione: a != null)
	 */
	public void addAcquisto(Acquisto a) {
		storicoAcquisti.add(a);
	}

	/**
	 * Restituisce la lista delle prenotazioni effettuate dall'utente
	 * @return lo storico delle prenotazioni
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<Prenotazione> getStoricoPrenotazioni() {
		return (ArrayList<Prenotazione>)storicoPrenotazioni.clone();
	}

	/**
	 * Permette di aggiungere una prenotazione alla lista delle prenotazioni effettuate dall'utente
	 * @param p la prenotazione da aggiungere allo storico delle prenotazioni (Precondizione: p!=null)
	 */
	public void addPrenotazione(Prenotazione p) {
		storicoPrenotazioni.add(p);
	}

	/**
	 * Restituisce il carrello, ovvero la lista delle prenotazioni in corso
	 * @return il carrelo
	 */
	public ArrayList<Prenotazione> getCarrello() {
		return carrello;
	}

	/**
	 * Setta la lista delle prenotazioni in corso
	 * @param c il carrello (Precondizione: c!= null)
	 */
	public void setCarrello(ArrayList<Prenotazione> c) {
		carrello=c;
	}

	/**
	 * Restituisce il credito dell'utente
	 * @return credito
	 */
	public double getCredito() {
		return credito;
	}

	/**
	 * Permette di aggiungere una quantità di credito
	 * @param creditoDaAggiungere credito da aggiungere (Precondizione: creditoDaAggiungere > 0)
	 */
	public void addCredito(double creditoDaAggiungere) {
		credito =credito+creditoDaAggiungere;
	}

	/**
	 * Permette di scalare una quantità  di credito
	 * @param creditoDaScalare credito da scalare (Precondizione: creditoDaScalare > 0)
	 */
	public void scalaCredito(double creditoDaScalare) {
		credito=credito - creditoDaScalare;
	}

	/**
	 * Restituisce la categoria a cui appartiene l'utenete
	 * @return la categoria
	 */
	public String getCategoria() {
		return categoria;
	}

	/**
	 * Setta la categoria a cui deve appartenere l'utente
	 * @param categoria la categoria (Precondizione: categoria != null && categoria != "")
	 */
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	/**
	 * Il metodo restituisce lo stato dell'oggetto sotto forma di stringa.
	 * Sovrascrive il toString() della classe Object.
	 * @return Una stringa rappresentante lo stato dell'oggetto
	 */
	public String toString() {
		return super.toString() + "[credito= " + credito + 
				"categoria= " + categoria + 
				"]";
	}

	/**
	 * Verifica se due oggetti di tipo Utente sono uguali; nello specifico si confrontano:
	 * l'oggetto su cui è stato invocato il metodo e l'oggetto passato come parametro esplicito
	 * @param other L'oggetto da confrontare (Precondizione: other != null)
	 * @return True se gli oggetti sono uguali, False altrimenti
	 */
	public boolean equals (Object other) {
		if (!super.equals(other))
			return false;
		Utente o = (Utente)other;
		return credito == o.credito && 
				categoria.equals(o.categoria); 
	}

	/**
	 * Clona un oggetto utente
	 * @return un altro oggetto Utente avente lo stesso stato dell'oggetto corrente
	 */
	
	
	public Object clone() throws CloneNotSupportedException{
		try{
			Utente cloned = (Utente) super.clone();
			ArrayList<Acquisto> x=new ArrayList<Acquisto>();
			for(Acquisto a:storicoAcquisti)
				try {
					x.add((Acquisto)a.clone());
				} catch (CloneNotSupportedException e) {
					return null;
				}
			cloned.storicoAcquisti = x;
			
			ArrayList<Prenotazione> x1=new ArrayList<Prenotazione>();
			for(Prenotazione p:storicoPrenotazioni)
				try {
					x1.add((Prenotazione)p.clone());
				} catch (CloneNotSupportedException e) {
					return null;
				}
			cloned.storicoPrenotazioni = x1;
		
		
			ArrayList<Prenotazione> x2=new ArrayList<Prenotazione>();
			for(Prenotazione pr:carrello)
				try {
					x2.add((Prenotazione)pr.clone());
				} catch (CloneNotSupportedException e) {
					return null;
				}
			cloned.carrello = x2;
			
			return cloned;
		}
		catch (CloneNotSupportedException e) {
			return null;
		}
		
	}
	
	/*public Object clone() {
		
		
		
		
		
		return cloned;
	}
*/
	private static final long serialVersionUID = 1L;
	private ArrayList<Acquisto> storicoAcquisti;
	private ArrayList<Prenotazione> storicoPrenotazioni;
	private ArrayList<Prenotazione> carrello;
	private double credito;
	private String categoria;
	
}
