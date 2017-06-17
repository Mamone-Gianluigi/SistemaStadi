package it.unisa.prog2.progetto.core.discount;

import java.io.Serializable;

import it.unisa.prog2.progetto.core.Acquisto;

/**
 * La classe astratta implementa il concetto di politica di sconto.
 */
public abstract class PoliticaDiSconto implements Cloneable, Serializable {
	
	
	/**
	 * Crea un nuovo oggetto di tipo Politica di Sconto che conserva il seguente parametro:
	 * @param percentualeSconto La percentuale di sconto (Precondizione: percentualeSconto > 0)
	 */
	public PoliticaDiSconto (double percentualeSconto) {
		this.percentualeSconto = percentualeSconto;
	}
	
	/**
	 * Restituisce la percentuale di sconto
	 * @return La percentuale di sconto
	 */
	public double getPercentualeSconto () {
		return percentualeSconto;
	}
	
	/**
	 * Setta la percentuale di sconto
	 * @param percentualeSconto la percentuale di sconto (Precondizione: percentualeSconto > 0)
	 */
	public void setPercentualeSconto(double percentualeSconto) {
		this.percentualeSconto = percentualeSconto;
	}

	/**
	 * Il metodo permette di calcolare uno sconto da applicare al momento di un acquisto
	 * @param a l'acquisto su cui calcolare lo sconto (Precondizione: a != null)
	 * @return la percentuale di sconto
	 */
	public abstract double getSconto(Acquisto a);
	
	
	/**
	 * Il metodo restituisce lo stato dell'oggetto sotto forma di stringa.
	 * Sovrascrive il toString() della classe Object.
	 * @return Una stringa rappresentante lo stato dell'oggetto
	 */
	public String toString () {
		return this.getClass().getName() + "[percentualeSconto=" + percentualeSconto + "]";
	}
	
	
	/**
	 * Verifica se due oggetti di tipo PoliticaDiSconto sono uguali; nello specifico si confrontano:
	 * l'oggetto su cui è stato invocato il metodo e l'oggetto passato come parametro esplicito
	 * @param other L'oggetto da confrontare (Precondizione: other != null)
	 * @return True se gli oggetti sono uguali, False altrimenti
	 */
	public boolean equals (Object other) {
		if (other == null) 
			return false;
		if (getClass() != other.getClass())
			return false;
		
		PoliticaDiSconto o = (PoliticaDiSconto)other;
		return (percentualeSconto==o.percentualeSconto);
	}
	
	/**
	 * Crea e restituisce una copia dell'oggetto su cui è stato invocato il metodo
	 * @return L'oggetto clonato
	 */
	public PoliticaDiSconto clone () {
		try {
			return (PoliticaDiSconto)super.clone();
		}
		catch (CloneNotSupportedException e) {
			return null;
		}
	}
	
	private static final long serialVersionUID = 1L;
	private double percentualeSconto;
}
