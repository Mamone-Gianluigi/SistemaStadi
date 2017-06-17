package it.unisa.prog2.progetto.core;

import java.io.Serializable;
import java.util.ArrayList;
import it.unisa.prog2.progetto.exceptions.*;

/**
 * Mantiene le informazioni circa un settore di uno stadio; nel dettaglio conserva:
 * il nome del settore, il numero di file dei posti e il numero dei posti che contiene ogni fila. 
 * Conserva quindi anche la capienza corrispondente al numero di posti in totale all'intenro del settore e la lista di posti presenti.
 * In più ciascun oggetto di tipo Settore è fornito di due interi che ci indicano, 
 * uno il numero di posti prenotati e l'altro il numero di posti venduti.
 */
public class Settore implements Cloneable,Serializable{ 

	/**
	 * Crea un nuovo settore, il quale contiene il nome, il numero di file, il numero dei posti per fila, 
	 * la capienza, la lista dei posti presenti, il numero di posti prenotati e il numero di posti venduti.
	 * @param nomeSettore il nome del settore (Precondizione: nomeSettore != null && nomeSettore != "")
	 * @param numeroFile il numero di file (Precondizione: numeroFile != null && numeroFile != "")
	 * @param numeroPostiFila il numero di posti per fila (Precondizione: numeroPostiFila != null && numeroPostiFila != "")
	 * @throws DatiNonValidiException
	 */
	public Settore(String nomeSettore,String numeroFile,String numeroPostiFila) throws DatiNonValidiException{
		
		if (nomeSettore.equals("") || numeroFile.equals("")
				|| numeroPostiFila.equals("")) 
			throw new DatiNonValidiException("Tutti i campi sono obbligatori!");
		
		
		this.nomeSettore=nomeSettore;
		this.numeroFile=Integer.parseInt(numeroFile);
		this.numeroPostiFila=Integer.parseInt(numeroPostiFila);
		capienza=this.numeroFile * this.numeroPostiFila;
		settore=new ArrayList<Posto>();
		riempiSettore();
		postiPrenotati = 0;
		postiVenduti = 0;
	}
		
	/**
	 * Restituisce il nome del settore
	 * @return il nome del settore
	 */
	public String getNomeSettore() {
		return nomeSettore;
	}

	/**
	 * Setta il nome del settore
	 * @param nomeSettore il nome del settore (Precondizione: nomeSettore != null && nomeSettore != "")
	 */
	public void setNomeSettore(String nomeSettore) {
		this.nomeSettore = nomeSettore;
	}

	/**
	 * Restituisce il numero di file presenti nel settore
	 * @return il numero di file
	 */
	public int getNumeroFile() {
		return numeroFile;
	}

	/**
	 * Aggiunge delle file al settore aumentandone quindi la capienza
	 * @param numero il numero di file da aggiungere (Precondizione: numero>0)
	 */
	public void aggiungiFile(int numero){
		numeroFile = numeroFile + numero;	
		capienza=numeroFile*numeroPostiFila;
		settore.clear();
		riempiSettore();
	}

	/**
	 * Sottrae delle file al settore diminuendone quindi la capienza
	 * @param numero il numero di file da sottrarre (Precondzione: numero>0)
	 */
	public void diminuisciFile(int numero) {
		this.numeroFile = numeroFile - numero;	
		capienza=numeroFile*numeroPostiFila;
		settore.clear();
		riempiSettore();
	}

	/**
	 * Restituisce il numero di posti che contiene una fila
	 * @return il numero di posti per fila
	 */
	public int getNumeroPostiFila() {
		return numeroPostiFila;
	}

	/**
	 * Aggiunge dei posti a ogni fila del settore aumentandone quindi la capienza
	 * @param numero di posti da aggiungere per ogni fila (Precondizione: numero > 0)
	 */
	public void aggiungiPostiFila(int numero){
		numeroPostiFila = numeroPostiFila + numero;	
		capienza=numeroFile*numeroPostiFila;
		settore.clear();
		riempiSettore();
	}

	/**
	 * Sottrae dei posti a ogni fila del settore diminuendone quindi la capienza
	 * @param numero di posti da sottrarre per ogni fila (Precondizione: numero > 0)
	 */
	public void diminuisciPostiFila(int numero){
		this.numeroPostiFila = numeroPostiFila - numero;	
		capienza=numeroFile*numeroPostiFila;
		settore.clear();
		riempiSettore();
	}

	/**
	 * Restituisce la capienza del settore
	 * @return la capienza
	 */
	public int getCapienza() {
		return capienza;
	}
	
	/**
	 *Restituisce il numero di posti prenotati all'interno del settore 
	 * @return il numero di posti prenotati
	 */
	public int getPostiPrenotati() {
		return postiPrenotati;
	}
	
	/**
	 * Restituisce un booleano che ci indica se un posto risulta prenotato oppure no
	 * @param numero il numero del posto da controllare (Precondizione: numero > 0)
	 * @param lettera la lettera del posto da controllare (Precondizione: lettera>=65 && lettera <=90)
	 * @return True se il posto risulta prenotato, False altrimenti
	 */
	public boolean isPostoPrenotato(int numero , char lettera){
		int a=lettera-'A';
		Posto p = settore.get((numeroPostiFila*a)+numero-1);
			return p.isPrenotato();
	}

	/**
	 * Setta un posto presente nella lista dei posti del settore come prenotato aumentando quindi il numero di posti prenotati
	 * @param numero il numero del posto da prenotare (Precondizione: numero > 0)
	 * @param lettera la lettera del posto da prenotare (Precondizione: lettera>=65 && lettera <=90)
	 * @throws PostoIndisponibileException
	 */
	public void aggiungiPostoPrenotato(int numero , char lettera) {
		postiPrenotati = postiPrenotati + 1;
		int a=lettera-'A';
		settore.get((numeroPostiFila*a)+numero-1).setPrenotato(true);
	}
	
	/**
	 * Setta a disponibile un posto, presente nella lista dei posti del settore,
	 * precedentemente prenotato diminuendo così il numero di posti prenotati
	 * @param numero il numero del posto da rendere disponibile (Precondizione: numero > 0)
	 * @param lettera la lettera del posto da rendere disponibile (Precondizione: lettera>=65 && lettera <=90)
	 */
	public void rimuoviPostoPrenotato(int numero , char lettera) {
		postiPrenotati = postiPrenotati - 1;
		int a=lettera-'A';
		settore.get((numeroPostiFila*a)+numero-1).setDisponibile(true);
	}


	/**
	 * Restituisce il numero di posti venduti all'interno del settore
	 * @return il numero di posti venduti
	 */
	public int getPostiVenduti() {
		return postiVenduti;
	}
	
	/**
	 * Restituisce un booleano che ci indica se un posto risulta venduto oppure no
	 * @param numero il numero del posto da controllare (Precondizione: numero > 0)
	 * @param lettera la lettera del posto da controllare (Precondizione: lettera>=65 && lettera <=90)
	 * @return True se il posto risulta venduto, False altrimenti
	 */
	public boolean isPostoVenduto(int numero , char lettera){
		int a=lettera-'A';
		Posto p = settore.get((numeroPostiFila*a)+numero-1);
		return p.isVenduto();
	}

	/**
	 * Setta un posto presente nella lista dei posti del settore come venduto aumentando quindi il numero di posti venduti
	 * @param numero il numero del posto da vendere (Precondizione: numero > 0)
	 * @param lettera la lettera del posto da vendere (Precondizione: lettera>=65 && lettera <=90)
	 * @throws PostoIndisponibileException
	 */
	public void aggiungiPostoVenduto(int numero , char lettera) {
		postiVenduti = postiVenduti + 1;
		int a=lettera-'A';
		settore.get((numeroPostiFila*a)+numero-1).setVenduto(true);
	
	
	}
	
	/**
	 * Restituisce il numero dei posti disponibili all'interno del settore
	 * @return il numero di posti disponibili
	 */
	public int getPostiDisponibili(){
		return capienza - postiPrenotati - postiVenduti;
	}

	/**
	 * Restituisce un booleano che ci indica se un posto risulta disponibile oppure no
	 * @param numero il numero del posto da controllare (Precondizione: numero > 0)
	 * @param lettera la lettera del posto da controllare (Precondizione: lettera>=65 && lettera <=90)
	 * @return True se il posto risulta disponibile, False altrimenti
	 */
	public boolean isPostoDisponibile(int numero , char lettera){
		int a=lettera-'A';
		Posto p = settore.get((numeroPostiFila*a)+numero-1);
			return p.isDisponibile();
	}

	/**
	 * Riempie la lista dei posti del settore a seconda del numero di file e del numero di posti per fila 
	 */
	public void riempiSettore(){
		for(int i=0; i<numeroFile; i++){
			char let = (char)(65+i);
			for(int j=0; j<numeroPostiFila; j++)
				settore.add(new Posto(j+1,let));
		}
	}
	
	/**
	 * Stampa tutti i posti presenti nel settore in modo da avere per ogni rigo tutti i posti di una fila 
	 */
	public void stampa(){
		for(int i=0; i<capienza; i++){
			if(i%numeroPostiFila==0)
				System.out.println("");
			Posto a =settore.get(i);
			System.out.print(a.getNumero());
			System.out.print(a.getLettera()+"   ");
		}
	}
	
	/**
	 * Restituisce la lista dei posti prensenti nel settore
	 * @return la lista dei posti
	 */
	public ArrayList<Posto> getSettore() {
		return settore;
	}
	
	/**
	 * Setta la lista dei posti prensenti nel settore
	 * @param settore la lista di posti (Precondizione: settore!=null)
	 */
	public void setSettore(ArrayList<Posto> settore) {
		this.settore = settore;
	}

	/**
	 * Il metodo restituisce lo stato dell'oggetto sotto forma di stringa.
	 * Sovrascrive il toString() della classe Object.
	 * @return Una stringa rappresentante lo stato dell'oggetto
	 */
	public String toString() {	
		String posti="";
		for (Posto p:settore){
			posti=posti+", \n"+p;
		}
		return getClass().getName()+ "[nomeSettore=" + nomeSettore + ", numeroPostiFila=" + numeroPostiFila
				+ ", numeroFile=" + numeroFile + ", capienza=" + capienza + ", postiPrenotati=" + 
				postiPrenotati+ ", postiVenduti=" + postiVenduti+posti+"]";
	}

	/**
	 * Verifica se due oggetti di tipo Settore sono uguali; nello specifico si confrontano:
	 * l'oggetto su cui è stato invocato il metodo e l'oggetto passato come parametro esplicito
	 * @param obj L'oggetto da confrontare (Precondizione: obj != null)
	 * @return True se gli oggetti sono uguali, False altrimenti
	 */
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Settore o = (Settore) obj;
		return capienza==o.capienza && numeroFile == o.numeroFile &&
				numeroPostiFila == o.numeroPostiFila;
	}
	
	/**
	 * Crea e restituisce una copia dell'oggetto su cui è stato invocato il metodo
	 * @return L'oggetto clonato
	 */
	public Object clone() throws CloneNotSupportedException{
		try
		{
			Settore s=(Settore) super.clone();
			ArrayList<Posto> x=new ArrayList<Posto>();
			for(Posto p:settore)
				x.add((Posto) p.clone());
			s.settore=x;
			return s;
		}
		catch (CloneNotSupportedException e)
		{
			return null;
		}
	}
	
	private String nomeSettore;
	private int numeroFile;
	private int numeroPostiFila;
	private int capienza;
	private int postiPrenotati;
	private int postiVenduti;
	private static final long serialVersionUID = 1L;
	private ArrayList<Posto> settore;
}