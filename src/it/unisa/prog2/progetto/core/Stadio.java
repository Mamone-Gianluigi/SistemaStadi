package it.unisa.prog2.progetto.core;

import java.io.Serializable;
import java.util.*;
import it.unisa.prog2.progetto.exceptions.*;
import it.unisa.prog2.progetto.core.interfaces.*;

/**
 * Mantiene le informazioni circa uno stadio; nel dettaglio conserva:
 * un identificativo progressivo,il nome, la citt‡ in cui si trova, il prezzo di ogni posto, 
 * la capienza data da tutti i posti presenti. In pi˘ uno stadio conserva una lista di settori e 
 * alla creazione dello stesso stadio deve essere presente almeno un settore all'interno di tale lista.
 */
public class Stadio  implements Cloneable , Serializable , Comparatore{

	/**
	 * Crea un nuovo stadio, il quale contiene un identificativo, il nome, la citt‡, il prezzo di ogni posto, 
	 * un settore e la lista di settori.
	 * @param nomeStadio il nome dello stadio (Precondizione: nomeStadio != null && nomeStadio != "")
	 * @param citt‡Stadio la citt‡ in cui si trova lo stadio (Precondizione: citt‡Stadio != null && citt‡Stadio != "")
	 * @param prezzo il prezzo di ogni posto dello stadio (Precondizione: prezzo > 0) 
	 * @param s il settore presente nello stadio (Precondizione: s!=null)
	 */
	public Stadio(String nomeStadio,String citt‡Stadio,double prezzo,Settore s){
		count=count+1;	
		id=count;	
		this.nomeStadio=nomeStadio;
		this.citt‡Stadio=citt‡Stadio;
		settori=new ArrayList<Settore>();
		settori.add(s);
		capienza=s.getCapienza();
		this.prezzo=prezzo;
	}
	
	
	
	/**
	 * Restituisce l'identificativo dello stadio
	 * @return l'id
	 */
	public int getId() {
		return id;
	}



	/**
	 * Restituisce il nome dello stadio
	 * @return il nome dello stadio
	 */
	public String getNomeStadio() {
		return nomeStadio;
	}



	/**
	 * Setta il nome dello stadio
	 * @param nomeStadio il nome dello stadio (Precondizione: nomeStadio != null && nomeStadio != "")
	 */
	public void setNomeStadio(String nomeStadio) {
		this.nomeStadio = nomeStadio;
	}



	/**
	 * Restituisce la capienza dello stadio data dal numero di posti
	 * @return la capienza
	 */
	public int getCapienza() {
		return capienza;
	}



	/**
	 * Aggiunge delle file di posti ad un determinato settore presente nella lista dei settori dello stadio
	 * @param s il settore a cui aggiungere le file (Precondizione: s!=null)
	 * @param numero il numero di file da aggiungere (Precondizione: numero > 0)
	 */
	public void aggiungiFileSettore(Settore s,int numero){
		for(int i=0; i<settori.size(); i++)
			if(settori.get(i).equals(s)){
				capienza=capienza-settori.get(i).getCapienza();
				settori.get(i).aggiungiFile(numero);
				capienza=capienza+settori.get(i).getCapienza();
				break;
		}
	}



	/**
	 * Aggiunge dei posti per ogni fila di un determinato settore presente nella lista dei settori dello stadio
	 * @param s il settore a cui aggiungere i posti per fila (Precondondizione: s!=null)
	 * @param numero il numero di posti da aggiungere ad ogni fila (Precondizione: numero > 0)
	 */
	public void aggiungiPostiFilaSettore(Settore s,int numero){
		for(int i=0; i<settori.size(); i++)
			if(settori.get(i).equals(s)){
				capienza=capienza-settori.get(i).getCapienza();
				settori.get(i).aggiungiPostiFila(numero);
				capienza=capienza+settori.get(i).getCapienza();
				break;
		}
	}



	/**
	 * Sottrae dei posti per ogni fila di un determinato settore presente nella lista dei settori dello stadio
	 * @param settore il settore a cui sottrarre i posti per fila (Precondondizione: settore!=null)
	 * @param numero il numero di posti da sottrarre ad ogni fila (Precondizione: numero > 0)
	 */
	public void diminuisciPostiFilaSettore(Settore settore,int numero){
			
		for(int i=0; i<settori.size(); i++)
			if(settori.get(i).equals(settore)){
				capienza=capienza-settori.get(i).getCapienza();
				settori.get(i).diminuisciPostiFila(numero);	
				capienza=capienza+settori.get(i).getCapienza();
				break;
		}
	}




	/**
	 * Sottrae delle file di posti ad un determinato settore presente nella lista dei settori dello stadio
	 * @param settore il settore a cui sottrarre le file (Precondizione: settore!=null)
	 * @param numero il numero di file da sottrarre (Precondizione: numero > 0)
	 */
	public void diminuisciFileSettore(Settore settore,int numero){
		for(int i=0; i<settori.size(); i++)
			if(settori.get(i).equals(settore)){
				capienza=capienza-settori.get(i).getCapienza();
				settori.get(i).diminuisciFile(numero);
				capienza=capienza+settori.get(i).getCapienza();
				break;
		}
	}



	/**
	 * Restituisce il prezzo di ogni posto dello stadio
	 * @return il prezzo
	 */
	public double getPrezzo() {
		return prezzo;
	}


	/**
	 * Setta il prezzo di ogni posto dello stadio
	 * @param prezzo il prezzo di ogni posto (Precondizione: prezzo > 0)
	 */
	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}



	/**
	 * Restituisce la citt‡ in cui si trova lo stadio
	 * @return la citt‡ dello stadio
	 */
	public String getCitt‡Stadio() {
		return citt‡Stadio;
	}
	
	/**
	 * Restituisce la lista di settori dello stadio
	 * @return la lista di settori
	 */
	public ArrayList<Settore> getSettori() {
		return settori;
	}
	
	/**
	 * Aggiunge un settore alla lista di settori dello stadio aumentandone quindi anche la capienza
	 * @param s il settore da aggiungere (Precondizione: s!=null)
	 * @throws DatiNonValidiException
	 */
	public void addSettore(Settore s) throws DatiNonValidiException{
		settori.add(s);
		capienza=capienza+s.getCapienza();
	}

	/**
	 * Sottrae un settore dalla lista di settori dello stadio diminuendone quindi anche la capienza
	 * @param s il settore da sottrarre (Precondizione: s!=null)
	 */
	public void rimuoviSettore(Settore s){
		for(int i=0; i<settori.size(); i++)
			if(settori.get(i).equals(s)){
				capienza=capienza-settori.get(i).getCapienza();
				settori.remove(i);
				break;
		}
	}
	
	/**
	 * Restituisce un intero che sar‡:
	 * 0 se lo stadio passato come parametro ha un identificativo uguale all'identificativo dello stadio
	 * 1 se lo stadio passato come parametro ha un identificativo minore all'identificativo dello stadio
	 * -1 altrimenti
	 * @param o l'oggetto da confrontare (Precondizione: o != null)
	 * @return un intero
	 */
	public int compareToId(Object o) {
		Stadio oo =(Stadio) o;
		if ( id==oo.id)
			return 0;
		else if(id >oo.id)
			return 1;
		else 
			return -1;
		
	}

	
	/**
	 * Restituisce un intero che sar‡:
	 * 0 se lo stadio passato come parametro ha una capienza uguale alla capienza dello stadio
	 * 1 se lo stadio passato come parametro ha una capienza minore alla capienza dello stadio
	 * -1 altrimenti
	 * @param o l'oggetto da confrontare (Precondizione: o != null)
	 * @return un intero
	 */
	public int compareToCapienza(Object o) {
		Stadio oo =(Stadio) o;
		if ( capienza==oo.capienza)
			return 0;
		else if(capienza >oo.capienza)
			return 1;
		else 
			return -1;
	}
	
	/**
	 * Restituisce un intero
	 * @param o un oggetto
	 * @return 0
	 */
	public int compareToData(Object o) {
		return 0;
	}

	/**
	 * Restituisce un intero
	 * @param o un oggetto
	 * @return 0
	 */
	public int compareToLessico(Object o) {
		return 0;
	}
	
	/**
	 * Il metodo restituisce lo stato dell'oggetto sotto forma di stringa.
	 * Sovrascrive il toString() della classe Object.
	 * @return Una stringa rappresentante lo stato dell'oggetto
	 */
	public String toString() {
		String x="";
		for(Settore p:settori)
			x=x+", \n"+p;
		return getClass().getName()+"[id=" + id + ", nomeStadio=" + nomeStadio + ", capienza=" 
				+ capienza + ", citt‡Stadio=" + citt‡Stadio + "prezzo= " + prezzo + x+ "]";
	}



	/**
	 * Verifica se due oggetti di tipo Stadio sono uguali; nello specifico si confrontano:
	 * l'oggetto su cui Ë stato invocato il metodo e l'oggetto passato come parametro esplicito
	 * @param obj L'oggetto da confrontare (Precondizione: obj != null)
	 * @return True se gli oggetti sono uguali, False altrimenti
	 */
	public boolean equals(Object obj){
	
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Stadio o = (Stadio) obj;
		if(o.capienza == capienza &&
				nomeStadio.equals(o.nomeStadio) &&
				citt‡Stadio.equals(o.citt‡Stadio) &&
				prezzo== o.prezzo &&
				settori.size()== o.settori.size()){
			
			ArrayList<Settore> temp=new ArrayList<Settore>();
			
			
			for(Settore s:o.settori){
				Settore r;
				try {
					r = (Settore)s.clone();
					temp.add(r);
				} catch (CloneNotSupportedException e) {
					e.printStackTrace();
				}
				
			}
			
		
			boolean ok=true;
			int i;
			for(Settore s:settori){
				for(i=0; i<temp.size(); i++){
					if(s.equals(temp.get(i))){
						temp.remove(i);
						break;
					}
				}
			}
			if(temp.size()!=0)
				ok=false;
			return ok;
		}
		else
			return false;
	}



	/**
	 * Crea e restituisce una copia dell'oggetto su cui Ë stato invocato il metodo
	 * @return L'oggetto clonato
	 */
	public Object clone() throws CloneNotSupportedException{
		try
		{
			Stadio stadio=(Stadio) super.clone();
			ArrayList<Settore> x=new ArrayList<Settore>();
			for(Settore s:settori)
				x.add((Settore)s.clone());
			stadio.settori=x;
			return stadio;
		}
		catch(CloneNotSupportedException e)
		 {
			return null;
		}
	}


	private static int count;
	private final int id;
	private String nomeStadio;
	private int capienza;
	private double prezzo;
	private String citt‡Stadio;
	ArrayList<Settore> settori;
	private static final long serialVersionUID = 1L;

	
	

	
	
	
	

}
