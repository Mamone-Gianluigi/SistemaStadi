package it.unisa.prog2.progetto.core;


import java.io.Serializable;
import java.text.*;
import java.util.*;
import it.unisa.prog2.progetto.core.interfaces.*;
import it.unisa.prog2.progetto.exceptions.*;


/**
 * Mantiene le informazioni circa un evento calcistico; nel dettaglio conserva:
 * la partita insieme a stadio, anno, mese, giorno, ora e minuti in cui avverrà la partita.
 * Inoltre conserva il prezzo reativo ad ogni posto dello stadio in considerazione.
 * In più ciascun oggetto di tipo EventoCalcistico è fornito di una stringa che ci indica,
 * in base all'orario, la fascia giornaliera in cui si terrà la partita.
 */
public class EventoCalcistico implements Cloneable , Serializable,Comparatore{

	
	/**
	 * Crea un Evento Calcistico che contiene prezzo, fascia giornaliera e i seguenti parametri:
	 * @param partita la partita (Precondizione: partita != null)
	 * @param stadio lo stadio (Precondizione: stadio != null)
	 * @param anno l'anno (Precondizione: anno!=null && anno != "")
	 * @param mese il mese (Precondizione: mese != null && mese != "")
	 * @param giorno il giorno (Precondizione: giorno != null && giorno != "")
	 * @param ora l'ora(Precondizione: ora != null && ora != "")
	 * @param minuti i minuti (Precondizione: minuti != null && minuti != "")
	 * @throws DatiNonValidiException
	 */
	public EventoCalcistico(Partita partita,Stadio stadio,int anno,int mese,int giorno ,int ora,int minuti) throws DatiNonValidiException{
			
		this.partita=partita;
		this.stadio=stadio;
		dataOraInizioEventoCalcistico = 
				new GregorianCalendar(anno,(mese-1),giorno,ora,minuti,00);		
	
		this.prezzo=stadio.getPrezzo();
		calcolaFascia(ora);
	}
	
	/**
	 * Calcola la fascia giornaliera in base all'orario in cui si terrà la partita
	 * @param ora l'ora (Precondizione: ora > 7 && ora < 22)
	 */
	public void calcolaFascia(int ora){
		if(   (ora>7) && (ora<14) )// prima alle 8.00 e l'ultima alle 13.59
			fasciaGiornaliera="Mattina";
		else if (   (ora>=14) && (ora<19) )// prima alle 14.00 e l'ultima alle 18.59
			fasciaGiornaliera="Pomeriggio";
		else if (   (ora>=19) && (ora<22) )// prima alle 19.00 e l'ultima alle 21.59
			fasciaGiornaliera="Sera";			
	}
	
	
	/**
	 * Restituisce la partita
	 * @return la partita
	 */
	public Partita getPartita() {
		return partita;
	}
	
	/**
	 * Setta la partita
	 * @param partita la partita (Precondizione: partita != null)
	 */
	public void setPartita(Partita partita) {
		this.partita = partita;
	}
	
	/**
	 * Restituisce lo stadio
	 * @return lo stadio
	 */
	public Stadio getStadio() {
		return stadio;
	}
	
	/**
	 * Setta lo stadio
	 * @param stadio lo stadio (Precondizione: stadio!=null)
	 */
	public void setStadio(Stadio stadio) {
		this.stadio = stadio;
	}
	
	/**
	 * Restituisce la data dell'evento calcistico
	 * @return la data dell'evento calcistico
	 */
	public GregorianCalendar getDataInizioEventoCalcistico() {
		return dataOraInizioEventoCalcistico;
	}

	/**
	 * Restituisce la data dell'evento calcistico
	 * @param dataOraInizioEventoCalcistico la data dell'evento calcistico (Precondizione: dataOraInizioEventoCalcistico != null)
	 */
	public void setDataOraInizioEventoCalcistico(GregorianCalendar dataOraInizioEventoCalcistico) {
		this.dataOraInizioEventoCalcistico = dataOraInizioEventoCalcistico;
	}

	
	
	/**
	 * Restituisce la fascia giornaliera
	 * @return la fascia giornaliera
	 */
	public String getFasciaGiornaliera() {
		return fasciaGiornaliera;
	}

	
	/**
	 * Stampa l'anno,il mese e il giorno dell'evento cacistico
	 */
	public void stampaDataEventoCalcistico(){
		SimpleDateFormat sdf =new SimpleDateFormat("dd/MM/yyyy");
		System.out.println( sdf.format(dataOraInizioEventoCalcistico.getTime()) );

	}
	
	/**
	 * Stampa l'ora e i minuti in cui inizia l'evento calcistico
	 */
	public void stampaOraInizioEventoCalcistico(){
		SimpleDateFormat sdf =new SimpleDateFormat("HH:mm");
		System.out.println( sdf.format(dataOraInizioEventoCalcistico.getTime()) );
	}
	
	
	/**
	 * Restituisce il prezzo relativo ai posti dello stadio
	 * @return il prezzo
	 */
	public double getPrezzo() {
		return prezzo;
	}

	
	/**
	 * Restituisce un intero che sarà: 
	 * 0 se l'evento calcistico inizierà nello stesso momento dell'evento calcistico passato come parametro
	 * un valore inferiore a 0 se l'evento calcistico inizierà prima dell'eveto calcistico passato come parametro
	 * un valore maggiore di 0 se l'evento calcistico inizierà dopo l'evento calcistico passato come parametro
	 * 
	 * @param o L'oggetto da confrontare (Precondizione: o != null)
	 * @return un intero
	 */
	public int compareToData(Object o) {
	 
		EventoCalcistico e=(EventoCalcistico) o;
		return dataOraInizioEventoCalcistico.compareTo(e.dataOraInizioEventoCalcistico);
	}

	/**
	 * Restituisce un intero che sarà:
	 * 1 se la squadra dell'evento calcistico passato come parametro precede lessicograficamente la squadra dell'evento calcistico
	 * -1 se la squadra dell'evento calcistico precede lessicograficamente la squadra dell'evento calcistico passato come parametro
	 * 0 altrimenti
	 * @param o l'oggetto da confrontare (Precondizione: o != null)
	 * @return un intero
	 */
	public int compareToLessico(Object o) {
		EventoCalcistico e=(EventoCalcistico) o;
		
		String a=partita.compara();
		String b=e.partita.compara();

		System.out.println(a+" - "+ b);
		
		if(a.compareTo(b)>0)
			return 1;
		else if (a.compareTo(b)<0)
			return -1;
		else 
			return 0;
		
	}
	
	/**
	 * Restituisce un intero
	 * @param o un oggetto
	 * @return 0
	 */
	public int compareToId(Object o) {
		return 0;
	}

	/**
	 * Restituisce un intero
	 * @param o un oggetto
	 * @return 0
	 */
	public int compareToCapienza(Object o) {
		return 0;
	}
	
	
	/**
	 * Il metodo restituisce lo stato dell'oggetto sotto forma di stringa.
	 * Sovrascrive il toString() della classe Object.
	 * @return Una stringa rappresentante lo stato dell'oggetto
	 */
	public String toString() {
			SimpleDateFormat sdf =new SimpleDateFormat("dd/MM/yyyy-HH:mm:ss"); 
			String a=sdf.format(dataOraInizioEventoCalcistico.getTime());
			return getClass().getName()+ "[dataInizioEventoCalcistico="
					+ a +
					", partita=" + partita + ", stadio=" + stadio  + 
					", prezzo=" + prezzo  +
					", fasciaGiornaliera=" + fasciaGiornaliera  +"]";
		}
	
	
		/**
		 * Verifica se due oggetti di tipo EventoCalcistico sono uguali; nello specifico si confrontano:
		 * l'oggetto su cui è stato invocato il metodo e l'oggetto passato come parametro esplicito
		 * @param obj L'oggetto da confrontare (Precondizione: obj != null)
		 * @return True se gli oggetti sono uguali, False altrimenti
		 */
		public boolean equals(Object obj) {
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			EventoCalcistico o = (EventoCalcistico) obj;
			return partita.equals(o.partita) && stadio.equals(o.stadio) &&
			     dataOraInizioEventoCalcistico.equals(o.dataOraInizioEventoCalcistico)
			     && fasciaGiornaliera.equals(o.fasciaGiornaliera)
			     && prezzo==o.prezzo;			
		}
		
		
		/**
		 * Crea e restituisce una copia dell'oggetto su cui è stato invocato il metodo
		 * @return L'oggetto clonato
		 */
		public Object clone() throws CloneNotSupportedException{
			try
			{
				EventoCalcistico e=(EventoCalcistico)super.clone();
				e.partita=(Partita)partita.clone();
				e.stadio=(Stadio) stadio.clone();
				e.dataOraInizioEventoCalcistico=(GregorianCalendar) dataOraInizioEventoCalcistico.clone();
				return e;
			}
			catch(CloneNotSupportedException e)
			{
				return null;
			}
		}
	private Partita partita;
	private Stadio stadio;
	private double prezzo;
	private GregorianCalendar dataOraInizioEventoCalcistico;
	private String fasciaGiornaliera;
	private static final long serialVersionUID = 1L;
	
}
