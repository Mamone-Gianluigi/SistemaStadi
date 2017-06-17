package it.unisa.prog2.progetto.core;

import java.io.Serializable;
import java.util.*;

import it.unisa.prog2.progetto.exceptions.PostoIndisponibileException;

/**
 * Mantiene le informazioni circa un acquisto effettuato dall'utente; nel dettaglio conserva:
 * l'utente, il posto acquistato per un determinato evento calcistico,il settore in cui si trova il posto,l'evento calcistico, 
 * la data in cui è stato acquistato e il prezzo del posto basato sullo stadio in cui avverrà la partita.
 */
public class Acquisto implements Cloneable , Serializable{
	
	/**
	 * Crea un nuovo acquisto, il quale contiene il posto acquistato, il settore, l'evento calcistico, l'utente, la data d'acquisto e il prezzo.
	 * La creazione del nuovo acquisto potrà avvenire solo nel caso in cui il posto non risulta già prenotato o venduto.
	 * @param utente l'utente (Precondizione: utente!=null)
	 * @param eventoCalcistico l'evento calcistico (Precondizione: eventoCalcistico!= null)
	 * @param settore il settore (Precondizione: settore!=null)
	 * @param posto il posto(Precondizione: posto!=null)
	 * @throws PostoIndisponibileException 
	 */
	public Acquisto(Utente utente,EventoCalcistico eventoCalcistico,Settore settore,Posto posto) throws PostoIndisponibileException
	{
		if(posto.isVenduto()==true || posto.isPrenotato()==true)
			throw new PostoIndisponibileException("Posto non disponibile");
		else{
			this.utente=utente;
			this.eventoCalcistico=eventoCalcistico;
			this.dataAcquisto=new GregorianCalendar();
			this.prezzoAcquisto=eventoCalcistico.getStadio().getPrezzo();
			this.settore=settore;
			this.posto=posto;
		}
	}
	
	
	/**
	 * Restituisce l'utente che ha effettuato l'acquisto
	 * @return l'utente
	 */
	public Utente getUtente()
	{
		return utente;
	}



	/**
	 * Setta l'utente che ha effettuato l'acquisto
	 * @param utente l'utente(Precondizione: utente!=null)
	 */
	public void setUtente(Utente utente) 
	{
		this.utente = utente;
	}



	/**
	 * Restituisce l'evento calcistico relativo al posto acquistato
	 * @return l'evento calcistico
	 */
	public EventoCalcistico getEventoCalcistico() 
	{
		return eventoCalcistico;
	}



	/**
	 * Setta l'evento calcistico relativo al posto acquistato
	 * @param eventoCalcistico l'evento calcistico (Precondizione: eventoCalcistico!=null)
	 */
	public void setEventoCalcistico(EventoCalcistico eventoCalcistico)
	{
		this.eventoCalcistico = eventoCalcistico;
	}



	/**
	 * Restituisce il settore in cui si trova il posto acquistato
	 * @return il settore
	 */
	public Settore getSettore() {
		return settore;
	}



	/**
	 * Setta il settore in cui si trova il posto acquistato
	 * @param settore il settore (Precondizione: settore!=null)
	 */
	public void setSettore(Settore settore) {
		this.settore = settore;
	}



	/**
	 * Restituisce il posto acquistato
	 * @return il posto
	 */
	public Posto getPosto() {
		return posto;
	}



	/**
	 * Setta il posto acquistato
	 * @param posto il posto (Precondizione: posto!=null)
	 */
	public void setPosto(Posto posto) {
		this.posto = posto;
	}



	/**
	 * Restituisce il prezzo relativo all'acquisto
	 * @return il prezzo
	 */
	public double getPrezzoAcquisto()
	{
		return prezzoAcquisto;
	}
	
	
	/**
	 * Setta il prezzo relativo all'acquisto
	 * @param prezzoAcquisto il prezzo (Precondizione: prezzoAcquisto != null)
	 */
	public void setPrezzoAcquisto(double prezzoAcquisto)
	{
		this.prezzoAcquisto=prezzoAcquisto;
	}
	
	
	/**
	 * Restituisce la data in cui è avvenuto l'acquisto	
	 * @return la data d'acquisto
	 */
	public GregorianCalendar getDataAcquisto()
	{
		return dataAcquisto;
	}
	
	
	
	/**
	 * Setta la data in cui è avvenuto l'acquisto
	 * @param dataAcquisto la data d'acquisto (Precondizione dataAcquisto!=null)
	 */
	public void setDataAcquisto(GregorianCalendar dataAcquisto)
	{
		this.dataAcquisto=dataAcquisto;
	}
	
	
	/**
	 * Restituisce una stringa rappresentate la data in cui è avvenuto l' acquisto che comprende anno,mese,giorno,ora e minuti
	 * @return una stringa rappresentate la data d'acquisto
	 */
	private String stampaData()
	{
		int giorno=dataAcquisto.get(Calendar.DAY_OF_MONTH);
		int mese=dataAcquisto.get(Calendar.MONTH);
		int anno=dataAcquisto.get(Calendar.YEAR);
		int ora=dataAcquisto.get(Calendar.HOUR);
		int minuti=dataAcquisto.get(Calendar.MINUTE);
		String dataOra=giorno+"/"+mese+"/"+anno+" "+ora+":"+minuti;
		return dataOra;
	}
	
	/**
	 * Il metodo restituisce lo stato dell'oggetto sotto forma di stringa.
	 * Sovrascrive il toString() della classe Object.
	 * @return Una stringa rappresentante lo stato dell'oggetto
	 */
	public String toString()
	{
		return getClass().getName()+"[Utente:"+utente.toString()+"\n"
				+ " EventoCalcistico:"+eventoCalcistico.toString()+"\n"
						+ " Settore:"+settore.toString()+"\n"
							+ " Posto:"+posto.toString()+"\n"
						+ " Data Acquisto: " + stampaData()
						+ " Prezzo Acquisto: " + prezzoAcquisto;
	}

	
	/**
	 * Verifica se due oggetti di tipo Acquisto sono uguali; nello specifico si confrontano:
	 * l'oggetto su cui è stato invocato il metodo e l'oggetto passato come parametro esplicito
	 * @param obj L'oggetto da confrontare (Precondizione: obj != null)
	 * @return True se gli oggetti sono uguali, False altrimenti
	 */
	public boolean equals(Object obj) 
	{
		if(obj==null)
			return false;
		if(obj.getClass()!=getClass())
			return false;
		Acquisto a=(Acquisto) obj;
		return ( (utente.equals(a.utente)) && 
				(eventoCalcistico.equals(a.eventoCalcistico)) && 
				(settore.equals(a.settore)) &&
				(posto.equals(a.posto)) && 
				(dataAcquisto.equals(a.dataAcquisto)))
				&& (prezzoAcquisto==(a.prezzoAcquisto));
	}

	

	/**
	 * Crea e restituisce una copia dell'oggetto su cui è stato invocato il metodo
	 * @return L'oggetto clonato
	 * @throws CloneNotSupportedException
	 */
	public Acquisto clone() throws CloneNotSupportedException
	{
		try
		{
			Acquisto cloned=(Acquisto)super.clone();
			cloned.utente=(Utente) utente.clone();
			cloned.eventoCalcistico=(EventoCalcistico)eventoCalcistico.clone();
			cloned.dataAcquisto=(GregorianCalendar)dataAcquisto.clone();
			cloned.settore= (Settore)settore.clone();
			cloned.posto= (Posto) posto.clone();
			return cloned;
		}catch(CloneNotSupportedException e)
		{
			return null;
		}
	}

	
	
	private Utente utente;
	private EventoCalcistico eventoCalcistico;
	private Settore settore;
	private Posto posto;
	private GregorianCalendar dataAcquisto;
	private double prezzoAcquisto;
	private static final long serialVersionUID = 1L;
}
