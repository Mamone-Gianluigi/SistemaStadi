package it.unisa.prog2.progetto.core.discount;

import java.io.Serializable;
import java.util.GregorianCalendar;
import it.unisa.prog2.progetto.core.*;

/**
 * Mantiene le informazioni circa gli sconti in base al giorno della settimana.
 * Il primo giorno della settimana che viene preso in considerazione è la Domenica, di conseguenza la numerazione partità da lì.
 *
 */
public class ScontoGiorno extends PoliticaDiSconto implements Serializable {

	// 0 domenica - 6 sabato
	/**
	 * Crea un nuovo sconto a seconda del giorno della settimana che conserva i seguenti parametri:
	 * @param dayOfWeek il giorno della settimana in cui vale lo sconto (Precondizione: dayOfWeek != null && dayOfWeek != "")
	 * @param percentualeSconto la percentuale di sconto (Precondizione: percentualeSconto > 0)
	 */
	public ScontoGiorno(String dayOfWeek,double percentualeSconto) {
		super(percentualeSconto);
		if(dayOfWeek.equals("Dom"))
			this.dayOfWeek=0;
		else if(dayOfWeek.equals("Lun"))
			this.dayOfWeek=1;
		else if(dayOfWeek.equals("Mar"))
			this.dayOfWeek=2;
		else if(dayOfWeek.equals("Mer"))
			this.dayOfWeek=3;
		else if(dayOfWeek.equals("Gio"))
			this.dayOfWeek=4;
		else if(dayOfWeek.equals("Ven"))
			this.dayOfWeek=5;
		else if(dayOfWeek.equals("Sab"))
			this.dayOfWeek=6;
	}

	/**
	 * Restituisce il giorno della settimana in cui vale lo sconto
	 * @return il giorno della settimana
	 */
	public int getDayOfWeek() {
		return dayOfWeek;
	}
	
	/**
	 * Setta il giorno della settimana in cui vale lo sconto
	 * @param dayOfWeek il giorno della settimana (Precondizione: dayOfWeek != null && dayOfWeek != "")
	 */
	public void setDayOfWeek(String dayOfWeek) {
		if(dayOfWeek.equals("Dom"))
			this.dayOfWeek=0;
		else if(dayOfWeek.equals("Lun"))
			this.dayOfWeek=1;
		else if(dayOfWeek.equals("Mar"))
			this.dayOfWeek=2;
		else if(dayOfWeek.equals("Mer"))
			this.dayOfWeek=3;
		else if(dayOfWeek.equals("Gio"))
			this.dayOfWeek=4;
		else if(dayOfWeek.equals("Ven"))
			this.dayOfWeek=5;
		else if(dayOfWeek.equals("Sab"))
			this.dayOfWeek=6;
	}

	/**
	 * Verifica se lo sconto può essere applicato ad un acquisto effettuato da un utente 
	 * tenendo conto del giorno della settimana in cui avverrà l'evento calcistico relativo all'acquisto effettuato dall'utente.
	 * @param a l'acquisto effettuato dall'utente (Precondizione: a!= null)
	 * @return la percentuale di sconto
	 */
	public double getSconto(Acquisto a) {
		Double sconto =0.0;
		int dayOfWeek=a.getEventoCalcistico().
			getDataInizioEventoCalcistico().
			get(GregorianCalendar.DAY_OF_WEEK)-1;
		System.out.println("aa"+dayOfWeek+" ---"+this.dayOfWeek);
		if(dayOfWeek==(this.dayOfWeek))
			sconto =getPercentualeSconto();
		return sconto;
	}	
	
	/**
	 * Il metodo restituisce lo stato dell'oggetto sotto forma di stringa.
	 * Sovrascrive il toString() della classe Object.
	 * @return Una stringa rappresentante lo stato dell'oggetto
	 */
	public String toString () {
		return super.toString() + "[dayOfWeek=" + dayOfWeek+ "]";
	}
	
	/**
	 * Verifica se due oggetti di tipo ScontoGiorno sono uguali; nello specifico si confrontano:
	 * l'oggetto su cui è stato invocato il metodo e l'oggetto passato come parametro esplicito
	 * @param other L'oggetto da confrontare (Precondizione: other != null)
	 * @return True se gli oggetti sono uguali, False altrimenti
	 */
	public boolean equals (Object other) {
		
		if (!super.equals(other))
			return false;
		ScontoGiorno o=(ScontoGiorno)other;
		return dayOfWeek==o.dayOfWeek;
	}
	
	/**
	 * Crea e restituisce una copia dell'oggetto su cui è stato invocato il metodo
	 * @return L'oggetto clonato
	 */
	public ScontoGiorno clone () {
		return (ScontoGiorno)super.clone();
	}
	
	private static final long serialVersionUID = 1L;
	private int dayOfWeek;

}
