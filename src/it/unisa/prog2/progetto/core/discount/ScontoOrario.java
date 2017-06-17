package it.unisa.prog2.progetto.core.discount;

import java.io.Serializable;
import it.unisa.prog2.progetto.core.*;

/**
 * Mantiene le informazioni circa gli sconti in base all'orario dell'inizio dell'evento calcistico.
 * A seconda dell'orario vengono considerate tre fasce giornaliere: mattina,pomeriggio e sera.
 *
 */
public class ScontoOrario extends PoliticaDiSconto implements Serializable {
	
	/**
	 * Crea un nuovo sconto a seconda dell'orario che conserva i seguenti parametri:
	 * @param fasciaGiornaliera la fascia giornaliera (Precondizione: fasciaGiornaliera != null && fasciaGiornaliera != "")
	 * @param percentualeSconto la percentuale di sconto (Precondizione: percentualeSconto > 0)
	 */
	public ScontoOrario(String fasciaGiornaliera ,double percentualeSconto) {
		super(percentualeSconto);
		this.fasciaGiornaliera=fasciaGiornaliera;
	}
	
	/**
	 * Restituisce la fascia giornaliere in cui vale lo sconto
	 * @return fasciaGiornaliera
	 */
	public String getFasciaGiornaliera() {
		return fasciaGiornaliera;
	}
	
	/**
	 * Setta la fascia giornaliere in cui vale lo sconto
	 * @param fasciaGiornaliera la fascia giornaliera (Precondizione: fasciaGiornaliera != null && fasciaGiornaliera != "")
	 */
	public void setFasciaGiornaliera(String fasciaGiornaliera) {
		this.fasciaGiornaliera = fasciaGiornaliera;
	}

	/**
	 * Verifica se lo sconto può essere applicato ad un acquisto effettuato da un utente 
	 * tenendo conto della fascia giornaliera in cui avverrà l'evento calcistico relativo all'acquisto effettuato dall'utente.
	 * @param a l'acquisto effettuato dall'utente (Precondizione: a!= null)
	 * @return la percentuale di sconto
	 */
	public double getSconto(Acquisto a) {
		Double sconto =0.0;
		
		String fasciaGiornaliera =a.getEventoCalcistico().
				getFasciaGiornaliera();
		if(fasciaGiornaliera.equals(this.fasciaGiornaliera))
			sconto =getPercentualeSconto();
		return sconto;
	}
	
	/**
	 * Il metodo restituisce lo stato dell'oggetto sotto forma di stringa.
	 * Sovrascrive il toString() della classe Object.
	 * @return Una stringa rappresentante lo stato dell'oggetto
	 */
	public String toString () {
		return super.toString() + "[fasciaGiornaliera=" + fasciaGiornaliera+ "]";
	}
	
	/**
	 * Verifica se due oggetti di tipo ScontoOrario sono uguali; nello specifico si confrontano:
	 * l'oggetto su cui è stato invocato il metodo e l'oggetto passato come parametro esplicito
	 * @param other L'oggetto da confrontare (Precondizione: other != null)
	 * @return True se gli oggetti sono uguali, False altrimenti
	 */
	public boolean equals (Object other) {
		
		if (!super.equals(other))
			return false;
		ScontoOrario o=(ScontoOrario)other;
		return fasciaGiornaliera.equals(o.fasciaGiornaliera);
	}

	/**
	 * Crea e restituisce una copia dell'oggetto su cui è stato invocato il metodo
	 * @return L'oggetto clonato
	 */
	public ScontoOrario clone () {
		return (ScontoOrario)super.clone();
	}


	private static final long serialVersionUID = 1L;
	private String fasciaGiornaliera;
}