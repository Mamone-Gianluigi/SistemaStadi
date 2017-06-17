package it.unisa.prog2.progetto.core;

import java.io.Serializable;
import java.util.*;
import it.unisa.prog2.progetto.core.interfaces.*;


/**
 * La classe ArrayListSort ordina una lista di oggetti che possono essere di tipo Stadio o EventoCalcistico 
 * in base al tipo di ordinamento che si vuole avere.
 */
public class ArrayListSort implements Serializable {
	
	private static final long serialVersionUID = 1L;
	public static final String DATA="ordine cronologico";
	public static final String LESSICO="ordine lessicografico";
	public static final String CAPIENZA="capienza";
	public static final String ID="id";

	/**
	 * Ordina gli elementi di un ArrayList
	 * @param v l'ArrayList da ordinare (Precondizione: v!= null)
	 * @param tipo il tipo di ordinamento (Precondizione: tipo == "ordine cronologico" || tipo == "ordine lessicografico" || tipo == "capienza" || tipo == "id")
	 * @return l'ArrayList ordinato
	 * @throws CloneNotSupportedException
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static ArrayList sort(ArrayList v,String tipo) throws CloneNotSupportedException {

		/*
		 * Controlla se l'ArrayList passato contiene oggetti di tipo Stadio.
		 * Se li contiene, provvede a clonare ogni oggetto dell'array v per poi inserirli in un altro ArrayList v1
		 */
		ArrayList v1=new ArrayList();
		if(v.get(0) instanceof Stadio){
			v1=new ArrayList<Stadio>();
			for(int i=0; i<v.size(); i++){
				Stadio s=(Stadio)v.get(i);
				Stadio clone=(Stadio)s.clone();
				v1.add(clone);
			}
		}
		
		/*
		 * Controlla se l'ArrayList passato contiene oggetti di tipo EventoCalcistico.
		 * Se li contiene, provvede a clonare ogni oggetto dell'array v per poi inserirli in un altro ArrayList v1
		 */
		else if(v.get(0) instanceof EventoCalcistico){
			v1=new ArrayList<EventoCalcistico>();
			for(int i=0; i<v.size(); i++){
				EventoCalcistico s=(EventoCalcistico)v.get(i);
				EventoCalcistico clone=(EventoCalcistico)s.clone();
				v1.add(clone);
			}
		}
	
		/*
		 * Attraverso i metodi statici getSmallest ed exchange, ordina l'ArrayList v1
		 */
		int k;
		int n = v1.size();
		k = 0;
		while (k != n-1) {
			int j = getSmallest(v1,k,tipo);
			exchange(v1, k, j);
			k++;
		}
		return v1;
	}
	
	/**
	 * Attraverso l'interfaccia Comparatore, a seconda del tipo di ordinamento restituisce di volta in volta al chiamante l'elemento più piccolo
	 * @param v1 l'ArrayList contentente gli elementi da ordinare (Precondizione: v1 != null)
	 * @param k l'indice dell'ArrayList che permette la ricerca dell'elemento più piccolo(Precondizione: k>=0)
	 * @param tipo il tipo di ordinamento (Precondizione: tipo == "ordine cronologico" || tipo == "ordine lessicografico" || tipo == "capienza" || tipo == "id")
	 * @return la posizione dell'elemento più piccolo a seconda del tipo di ordinamento
	 */
	@SuppressWarnings("rawtypes")
	public static int getSmallest(ArrayList v1, int k,String tipo) {
		// funziona su qualunque implem. di Comparable
		if (v1==null || v1.size()==k)
		return -1;
		int i;
		int small = k;
		i = k+1;
		if (tipo.equals( ArrayListSort.DATA)){
			while (i != v1.size()) {
				Comparatore current = (Comparatore) v1.get(i);
				Comparatore smallest = (Comparatore) v1.get(small);
				if (current.compareToData(smallest) < 0)
					small = i;
				i++;
			}
		}
		else if (tipo.equals( ArrayListSort.CAPIENZA)){
			while (i != v1.size()) {
				Comparatore current = (Comparatore) v1.get(i);
				Comparatore smallest = (Comparatore) v1.get(small);
				if (current.compareToCapienza(smallest) < 0)
					small = i;
				i++;
			}
		}
		else if (tipo.equals( ArrayListSort.LESSICO)){
			while (i != v1.size()) {
				Comparatore current = (Comparatore) v1.get(i);
				Comparatore smallest = (Comparatore) v1.get(small);
				if (current.compareToLessico(smallest) < 0)
					small = i;
				i++;
			}
		}
		else if (tipo.equals( ArrayListSort.ID)){
			while (i != v1.size()) {
				Comparatore current = (Comparatore) v1.get(i);
				Comparatore smallest = (Comparatore) v1.get(small);
				if (current.compareToId(smallest) < 0)
					small = i;
				i++;
			}
		}
		return small;
		}
	
	/**
	 * Scambia l'elemento più piccolo trovato in posizione j con con l'elemento in posizione k
	 * @param v1 l'ArrayList contente gli oggetti da ordinare (Precondizione: v1 != null)
	 * @param k indice dell'ArrayList relativo alla posizione in cui inserire l'elemento più piccolo trovato (Precondizione: k>=0)
	 * @param j indice dell'ArrayList relativo alla posizione in cui è presente l'elemento più piccolo
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void exchange(ArrayList v1, int k, int j){
		Comparatore temp1 = (Comparatore) v1.get(k);
		Comparatore temp2 = (Comparatore) v1.get(j);
		v1.set(k, temp2);
		v1.set(j, temp1);
		/*
		v.add(k,temp2);
		v.add(j,temp1);
		v.remove(k+1);
		v.remove(j+1);
		*/
	}
}