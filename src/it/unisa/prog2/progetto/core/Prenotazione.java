package it.unisa.prog2.progetto.core;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import it.unisa.prog2.progetto.exceptions.PostoIndisponibileException;

/**
 * Mantiene le informazioni circa una prenotazione effettuato dall'utente; nel dettaglio conserva:
 * l'utente, il posto prenotato per un determinato evento calcistico,il settore in cui si trova il posto,l'evento calcistico,
 * la data in cui è stato prenotato, la data in cui inizierà la partita e 
 * la data in cui scadrà la prenotazione (12 ore prima dell'inizio della partita).
 * In più ciascun oggetto di tipo Prenotazione è fornito di una stringa che ci indica lo stato attuale della prenotazione. 
 * Inizialmente impostata a "in corso", la prenotazione potrà poi alla fine risultare come annullata,scaduta o conclusa come stato.
 */
public class Prenotazione implements Serializable,Cloneable
{
	
	/**
	 * Crea una nuova prenotazione, la quale contiene il posto prenotato, il settore, l'utente, l'evento calcistico, 
	 * la data di prenotazione, la data di inizio partita, la data di scadenza della prenotazione e lo stato della prenotazione.
	 * La creazione della nuova prenotazione potrà avvenire solo nel caso in cui il posto non risulta già prenotato o venduto.
	 * @param utente l'utente (Precondizione: utente!=null)
	 * @param eventoCalcistico l'evento calcistico (Precondizione: eventoCalcistico!=null)
	 * @param settore il settore (Precondizione: settore!=null)
	 * @param posto il posto (Precondizione: posto!=null)
	 * @throws PostoIndisponibileException
	 */
	public Prenotazione(Utente utente,EventoCalcistico eventoCalcistico,Settore settore,Posto posto) throws PostoIndisponibileException
	{
		if(posto.isVenduto()==true || posto.isPrenotato()==true)
			throw new PostoIndisponibileException("Posto non disponibile");
		else{
			this.utente=utente;
			this.eventoCalcistico=eventoCalcistico;
			dataPrenotazione=new GregorianCalendar();
			this.posto=posto;
			this.settore=settore;
			this.stato="In corso";
		}	
		GregorianCalendar inizio=eventoCalcistico.getDataInizioEventoCalcistico();	
		scadenza=(GregorianCalendar)inizio.clone();	
		scadenza.add(GregorianCalendar.HOUR,-12);
	}
		
	
	
	/**
	 * Restituisce l'utente che ha effettuato la prenotazione
	 * @return l'utente
	 */
	public Utente getUtente() 
	{
		return utente;
	}

	
	/**
	 * Setta l'utente che ha effettuato la prenotazione
	 * @param utente l'utente (Precondizione: utente!=null)
	 */
	public void setUtente(Utente utente)
	{
		this.utente = utente;
	}


	/**
	 * Restituisce l'evento calcistico relativo al posto prenotato
	 * @return l'evento calcistico
	 */
	public EventoCalcistico getEventoCalcistico()
	{
		return eventoCalcistico;
	}


	/**
	 * Restituisce la data in cui è avvenuto la prenotazione
	 * @return la data di prenotazione
	 */
	public GregorianCalendar getDataPrenotazione() 
	{
		return dataPrenotazione;
	}


	/**
	 * Restituisce il posto prenotato
	 * @return il posto
	 */
	public Posto getPosto() 
	{
		return posto;
	}


	
	/**
	 * Setta il posto prenotato
	 * @param posto il posto (Precondizione: posto!=null)
	 */
	public void setPosto(Posto posto) {
		this.posto = posto;
	}



	/**
	 * Restituisce il settore in cui si trova il posto prenotato
	 * @return il settore
	 */
	public Settore getSettore() {
		return settore;
	}



	/**
	 * Setta il settore in cui si trova il posto prenotato
	 * @param settore il settore (Precondizione: settore!=null)
	 */
	public void setSettore(Settore settore) {
		this.settore = settore;
	}


	
	/**
	 * Restituisce lo stato attuale della prenotazione
	 * @return lo stato
	 */
	public String getStato() {
		return stato;
	}
	

	/**
	 * Setta lo stato della prenotazione
	 * @param stato lo stato (Precondizione: stato=="Annullata" || stato =="Scaduta" || stato == "Conclusa")
	 */
	//oppure precondizione che inserisco solo 3 parole
	public void setStato(String stato){
		if(ANNULLATA.equals(stato))
			this.stato = stato;
		else if(SCADUTA.equals(stato))
			this.stato = stato;
		else if(CONCLUSA.equals(stato))
			this.stato = stato;
	}


	/**
	 * Restituisce la data di scadenza della prenotazione
	 * @return la data di scadenza
	 */
	public GregorianCalendar getScadenza() {
		return scadenza;
	}



	/**
	 * Restituisce la stringa rappresentante la parola "Annullata"
	 * @return la stringa "Annullata"
	 */
	public static String getAnnullata() {
		return ANNULLATA;
	}


	
	/**
	 * Restituisce la stringa rappresentante la parola "Scaduta"
	 * @return la stringa "Scaduta"
	 */
	public static String getScaduta() {
		return SCADUTA;
	}



	/**
	 * Restituisce la stringa rappresentante la parola "Conclusa"
	 * @return la stringa "Conclusa"
	 */
	public static String getConclusa() {
		return CONCLUSA;
	}
	

	/**
	 * Il metodo restituisce lo stato dell'oggetto sotto forma di stringa.
	 * Sovrascrive il toString() della classe Object.
	 * @return Una stringa rappresentante lo stato dell'oggetto
	 */
	public String toString()
	{
		
		SimpleDateFormat sdf =new SimpleDateFormat("dd/MM/yyyy-HH:mm");
		return getClass().getName()+"[Utente=" + utente + ", EventoCalcistico="
				+ eventoCalcistico + ", dataPrenotazione=" +
				sdf.format(dataPrenotazione.getTime()) + 
				", posto=" + posto + ", settore=" + settore+
				", stato=" + stato + ",scadenza=" +
				sdf.format(scadenza.getTime()) +"]";
	}
	

	/**
	 * Verifica se due oggetti di tipo Prenotazione sono uguali; nello specifico si confrontano:
	 * l'oggetto su cui è stato invocato il metodo e l'oggetto passato come parametro esplicito
	 * @param obj L'oggetto da confrontare (Precondizione: obj != null)
	 * @return True se gli oggetti sono uguali, False altrimenti
	 */
	public boolean equals(Object obj)
	{
		if(obj==null)
			return false;
		if(getClass()!=obj.getClass())
			return false;
		Prenotazione p=(Prenotazione)obj;
		return ( utente.equals(p.utente) && 
				eventoCalcistico.equals(p.eventoCalcistico) &&
				dataPrenotazione.equals(p.dataPrenotazione)  &&
				posto.equals(p.posto) && 
				settore.equals(p.settore) 
				&&  stato.equals(p.stato)  
				&& scadenza.equals(p.scadenza));
				
	}
	
	/**
	 * Crea e restituisce una copia dell'oggetto su cui è stato invocato il metodo
	 * @return L'oggetto clonato
	 * @throws CloneNotSupportedException
	 */
	public Prenotazione clone() throws CloneNotSupportedException
	{
		try
		{
			Prenotazione pre=(Prenotazione)super.clone();
			pre.utente=(Utente) utente.clone();
			pre.eventoCalcistico=(EventoCalcistico) eventoCalcistico.clone();
			pre.dataPrenotazione=(GregorianCalendar) dataPrenotazione.clone();
			pre.settore=(Settore) settore.clone();
			pre.posto=(Posto) posto.clone();
			pre.scadenza=(GregorianCalendar) scadenza.clone();
			return pre;
		}
		catch(CloneNotSupportedException e)
		{
			return null;
		}
	}
	
	private static final long serialVersionUID = 1L;
	private Utente utente;
	private EventoCalcistico eventoCalcistico;
	private GregorianCalendar dataPrenotazione;
	private Posto posto;
	private Settore settore;
	private String stato;
	private GregorianCalendar scadenza;
	public static final String ANNULLATA="Annullata";
	public static final String SCADUTA="Scaduta";
	public static final String CONCLUSA="Conclusa";

}
