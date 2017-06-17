package it.unisa.prog2.progetto.collaudoClassi;

import java.util.GregorianCalendar;

import it.unisa.prog2.progetto.core.*;
import it.unisa.prog2.progetto.exceptions.*;

public class EventoCalcisticoTester {

	public static void main(String[] args) throws DatiNonValidiException, CloneNotSupportedException {
		
		Partita partita=new Partita("Avellino","Napoli","Italiano","Serie A");
		Settore tribuna2 = new Settore("Tribuna","5","10");
		Stadio stadio=new Stadio("nome","città",3,tribuna2);
		
		EventoCalcistico e=new EventoCalcistico(partita,stadio,2004,12,3,19,00);
		System.out.println("PARTITA EVENTO CALCISTICO");
		System.out.println(e.getPartita());
		System.out.println("STADIO EVENTO CALCISTICO");
		System.out.println(e.getStadio().getNomeStadio());
		System.out.println("FASCIA GIORNALIERA EVENTO CALCISTICO");
		System.out.println(e.getFasciaGiornaliera());
		System.out.println("DATA EVENTO CALCISTICO");
		e.stampaDataEventoCalcistico();
		System.out.println("ORA EVENTO CALCISTICO");
		e.stampaOraInizioEventoCalcistico();
		
		EventoCalcistico e1=(EventoCalcistico) e.clone();
	
		System.out.println(" EVENTO CALCISTICO OTTENUTO DAL CLONE");
		System.out.println(e1);
		
		System.out.println("EQUALS");
		System.out.println(e.equals(e1));
		
		GregorianCalendar r= new GregorianCalendar(2016,GregorianCalendar.MAY,24,12,15,00);	
			
		
		e1.setDataOraInizioEventoCalcistico(r);
		
		System.out.println("EQUALS DOPO MODIFICA DATA");
		System.out.println(e.equals(e1));
		
	}

}
