package it.unisa.prog2.progetto.collaudoClassi;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import it.unisa.prog2.progetto.core.*;
import it.unisa.prog2.progetto.core.discount.PoliticaDiSconto;
import it.unisa.prog2.progetto.exceptions.DatiNonValidiException;
import it.unisa.prog2.progetto.exceptions.PostoIndisponibileException;

public class SistemaStadiTester {

	public static void main(String[] args) throws DatiNonValidiException, CloneNotSupportedException, PostoIndisponibileException {
		SistemaStadi sistema=new SistemaStadi("Osvaldo");
		
		Partita partita=new Partita("Avellino","Napoli","Italiano","Serie A");
		Settore tribuna1 = new Settore("Tribuna Montevergine","5","10");
		Stadio stadio=new Stadio("nome","città",100,tribuna1);
		

		EventoCalcistico e=new EventoCalcistico(partita,
				stadio,2006,12,25,10,30);
		sistema.aggiungiEventoCalcistico(partita,stadio,"2005","11","10","15","30");
		
		sistema.stampaEventiCalcistici();
		
		try{
			sistema.aggiungiEventoCalcistico(partita,stadio,"2006","12","25","11","33");
		}
		catch(DatiNonValidiException e1){
			return ;
		}
		System.out.println("Seconda stampa");
		sistema.stampaEventiCalcistici();
		System.out.print(sistema.getEventiCalcistici().get(0).getDataInizioEventoCalcistico().get(GregorianCalendar.HOUR_OF_DAY)+" - ");
		System.out.println(sistema.getEventiCalcistici().get(0).getDataInizioEventoCalcistico().get(GregorianCalendar.MINUTE));
		System.out.print(sistema.getEventiCalcistici().get(1).getDataInizioEventoCalcistico().get(GregorianCalendar.HOUR_OF_DAY));
		System.out.println(" - "+sistema.getEventiCalcistici().get(1).getDataInizioEventoCalcistico().get(GregorianCalendar.MINUTE));
	

		System.out.println("Sconti : ");
		
	
	
		sistema.aggiungiPoliticaSconto("Bambino","7");
		sistema.aggiungiPoliticaSconto("Studente","9");
		sistema.aggiungiPoliticaSconto("Martedì","8");
		sistema.aggiungiPoliticaSconto("Mattina","9");
		
		ArrayList<PoliticaDiSconto> politiche=sistema.getPoliticheSconto();
		System.out.println(politiche.size());

		
		Utente ut=sistema.getUtenteCorrente();
		
		sistema.acquista(new Acquisto(ut,e,tribuna1,new Posto(1,'A')));
		
		System.out.println(ut);
		ArrayList<Acquisto> acquisti=ut.getStoricoAcquisti();
		System.out.println(acquisti.size());
		
	
		
	}

}
