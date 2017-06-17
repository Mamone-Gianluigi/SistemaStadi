package it.unisa.prog2.progetto.core.interfaces;

/**
 *  L'interfaccia impone di gestire il confronto fra due Stadio in 
 *  base all'identificativo e alla capienza e il confronto fra 
 *  due EventiCalcistici in base alla data di inizio e in ordine 
 *  lessicografico .
 */
public interface Comparatore {
	
	/**
	 * Restituisce un intero che sarà:
	 * 0 se lo stadio passato come parametro ha un identificativo uguale all'identificativo del paramentro implicito
	 * 1 se lo stadio passato come parametro ha un identificativo minore all'identificativo del parametro implicito
	 * -1 altrimenti
	 * @return id Stadio
	 */
	public int compareToId(Object o);
	
	/**
	 * Restituisce un intero che sarà:
	 * 0 se lo stadio passato come parametro ha una capienza uguale alla capienza del parametro implicito
	 * 1 se lo stadio passato come parametro ha una capienza minore alla capienza del parametro implicito
	 * -1 altrimenti
	 * @return un intero
	 */
	public int compareToCapienza(Object o);
	
	/**
	 * Restituisce un intero che sarà: 
	 * 0 se l'evento calcistico del parametro implicito inizierà nello stesso momento dell'evento calcistico passato come parametro
	 * un valore inferiore a 0 se l'evento calcistico del parametro implicito inizierà prima dell'eveto calcistico passato come parametro
	 * un valore maggiore di 0 se l'evento calcistico del parametro implicito inizierà dopo l'evento calcistico passato come parametro

	 * @return un intero
	 */
	public int compareToData(Object o);
	
	/**
	 * Restituisce un intero che sarà:
	 * 1 se la squadra dell'evento calcistico passato come parametro precede lessicograficamente la squadra del parametro implicito
	 * -1 se la squadra dell'evento calcistico del parametro implicito precede lessicograficamente la squadra dell'evento calcistico passato come parametro
	 * 0 altrimenti
	 * @return un intero
	 */
	public int compareToLessico(Object o);
}
