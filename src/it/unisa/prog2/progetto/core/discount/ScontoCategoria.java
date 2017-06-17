package it.unisa.prog2.progetto.core.discount;

import java.io.Serializable;
import it.unisa.prog2.progetto.core.*;

/**
 * Mantiene le informazioni circa gli sconti per una categoria di clienti
 */
public class ScontoCategoria extends PoliticaDiSconto implements Serializable {


	/**
	 * Crea un nuovo sconto per categoria che conserva i seguenti parametri:
	 * @param categoria la categoria del cliente (Precondizione: categoria != null && categoria != "")
	 * @param sconto la percentuale di sconto (Precondizione: sconto > 0)
	 */
	public ScontoCategoria(String categoria,double sconto) {
				super(sconto);
				this.categoria=categoria;
	}	
	
	/**
	 * Restituisce la categoria a cui appartiene un utente
	 * @return la categoria
	 */
	public String getCategoria() {
		return categoria;
	}
	
	/**
	 * Setta la categoria a cui appartiene un utente
	 * @param categoria la categoria (Precondizione: categoria != null && categoria != "")
	 */
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	
	
	 /**
	 * Verifica se lo sconto può essere applicato ad un acquisto effettuato da un utente 
	 * tenendo conto della categoria a cui appartiene l'utente
	 * @param a l'acquisto effettuato dall'utente (Precondizione: a!= null)
	 * @return la percentuale di sconto
	 */
	public double getSconto(Acquisto a) {
		Double sconto =0.0;
		String categoria=a.getUtente().getCategoria();
		if(categoria.equals(this.categoria))
				sconto =getPercentualeSconto();
		return sconto;
	}
	
	/**
	 * Il metodo restituisce lo stato dell'oggetto sotto forma di stringa.
	 * Sovrascrive il toString() della classe Object.
	 * @return Una stringa rappresentante lo stato dell'oggetto
	 */
	public String toString () {
		return super.toString() + "[categoria=" + categoria + "]";
	}
	
	/**
	 * Verifica se due oggetti di tipo ScontoCategoria sono uguali; nello specifico si confrontano:
	 * l'oggetto su cui è stato invocato il metodo e l'oggetto passato come parametro esplicito
	 * @param other L'oggetto da confrontare (Precondizione: other != null)
	 * @return True se gli oggetti sono uguali, False altrimenti
	 */
	public boolean equals (Object other) {
		
		if (!super.equals(other))
			return false;
		ScontoCategoria o=(ScontoCategoria)other;
		return categoria.equals(o.categoria);
	}
	
	/**
	 * Crea e restituisce una copia dell'oggetto su cui è stato invocato il metodo
	 * @return L'oggetto clonato
	 */
	public ScontoCategoria clone () {
		return (ScontoCategoria)super.clone();
	}

	private static final long serialVersionUID = 1L;
	private String categoria;
}
