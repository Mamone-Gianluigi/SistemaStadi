package it.unisa.prog2.progetto.core;

import java.io.Serializable;

/**
 * Mantiene le informazioni circa una partita; nel dettaglio conserva:
 * la squadra che giocherà in casa, la squadra ospite, la lega e il campionato.
 */
public class Partita implements Cloneable, Serializable{

	/**
	 * Crea una nuova partita, la quale contiene la squadra che gioocherà in casa, la squadra ospite, la lega e il campionato.
	 * @param squadraCasa la squadra che gioca in casa (Precondizione: squadraCasa != null && squadraCasa != "")
	 * @param squadraOspite la squadra ospite (Precondizione: squadraOspite != null && squadraOspite != "")
	 * @param campionato il campionato (Precondizione: campionato != null && campionato != "")
	 * @param lega la lega (Precondizione: lega != null && lega != "")
	 */
	public Partita(String squadraCasa ,String squadraOspite, String campionato,String lega) {
		
		this.squadraCasa=squadraCasa;
		this.squadraOspite=squadraOspite;
		this.campionato=campionato;
		this.lega=lega;
	}

	/**
	 * Restituisce la squadra che gioca in casa
	 * @return la squadra casa
	 */
	public String getSquadraCasa() {
		return squadraCasa;
	}
	
	/**
	 * Setta la squadra che gioca in casa
	 * @param squadraCasa la squadra che gioca in casa (Precondizione: squadraCasa != null && squadraCasa != "")
	 */
	public void setSquadraCasa(String squadraCasa) {
		this.squadraCasa = squadraCasa;
	}

	/**
	 * Restituisce la squadra ospite
	 * @return la squadra ospite
	 */
	public String getSquadraOspite() {
		return squadraOspite;
	}

	/**
	 * Setta la squadra ospite
	 * @param squadraOspite la squadra ospite (Precondizione: squadraOspite != null && squadraOspite != "")
	 */
	public void setSquadraOspite(String squadraOspite) {
		this.squadraOspite = squadraOspite;
	}

	/**
	 * Restituisce il campionato relativo alla partita
	 * @return il campionato
	 */
	public String getCampionato() {
		return campionato;
	}

	/**
	 * Setta la lega relativa alla partita
	 * @param lega la lega (Precondizione: lega != null && lega != "")
	 */
	public void setLega(String lega) {
		this.lega = lega;
	}
	
	
	/**
	 * Restituisce la lega relativa alla partita
	 * @return la lega
	 */
	public String getLega() {
		return lega;
	}

	/**
	 * Setta il campionato relativo alla partita
	 * @param campionato il campionato (Precondizione: campionato != null && campionato != "")
	 */
	public void setCampionato(String campionato) {
		this.campionato = campionato;
	}

	
	/**
	 * Verifica se due oggetti di tipo Partita sono uguali; nello specifico si confrontano:
	 * l'oggetto su cui è stato invocato il metodo e l'oggetto passato come parametro esplicito
	 * @param obj L'oggetto da confrontare (Precondizione: obj != null)
	 * @return True se gli oggetti sono uguali, False altrimenti
	 */
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Partita o = (Partita) obj;
		return campionato.equals(o.campionato) && squadraCasa.equals(o.squadraCasa) && 
				squadraOspite.equals(o.squadraOspite) && lega.equals(o.lega);
	}

	
	/**
	 * Il metodo restituisce lo stato dell'oggetto sotto forma di stringa.
	 * Sovrascrive il toString() della classe Object.
	 * @return Una stringa rappresentante lo stato dell'oggetto
	 */
	public String toString() {
		return getClass().getName()+"[squadraCasa=" + squadraCasa + ", squadraOspite=" + squadraOspite
				+ ", campionato=" + campionato + ", lega" + lega + "]";
	}
	
	
	/**
	 * Crea e restituisce una copia dell'oggetto su cui è stato invocato il metodo
	 * @return L'oggetto clonato
	 */
	public Object clone() throws CloneNotSupportedException{
		try
		{
			return (Partita) super.clone();
		}
		catch(CloneNotSupportedException e)
		{
			return null;
		}
	}

	
	/**
	 * Restituisce la squadra che lessicograficamente precede l'altra
	 * @return la squadra casa o la squadra ospite
	 */
	public String compara() {
		if( squadraCasa.compareTo(squadraOspite)>0)
			return squadraOspite;
		else if (squadraCasa.compareTo(squadraOspite)<0)
			return squadraCasa;
		else
			return null;
	}
	
	private String squadraCasa;
	private String squadraOspite;
	private String campionato;
	private String lega;
	private static final long serialVersionUID = 1L;
	
}
