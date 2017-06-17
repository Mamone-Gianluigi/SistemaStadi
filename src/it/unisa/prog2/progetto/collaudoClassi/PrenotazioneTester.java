package it.unisa.prog2.progetto.collaudoClassi;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import it.unisa.prog2.progetto.core.*;
import it.unisa.prog2.progetto.exceptions.DatiNonValidiException;
import it.unisa.prog2.progetto.exceptions.PostoIndisponibileException;

public class PrenotazioneTester {

	public static void main(String[] args) throws CloneNotSupportedException, DatiNonValidiException, PostoIndisponibileException{
		
		Partita partita=new Partita("Avellino","Napoli","Italiano","Serie A");
		Settore tribuna1 = new Settore("Tribuna Montevergine","5","10");
		@SuppressWarnings("unused")
		Settore tribuna2 = new Settore("Tribuna","5","10");
		Stadio stadio=new Stadio("nome","città",5,tribuna1);
		
		EventoCalcistico e=new EventoCalcistico(partita,stadio,2005,1,1,12,11);
		
		System.out.println("TOSTRING DELLA PRENOTAZIONE : \n"+e);
		
		
		Prenotazione p = new Prenotazione(null,e,tribuna1,new Posto(1,'A'));
		SimpleDateFormat sdf =new SimpleDateFormat("dd/MM/yyyy-HH:mm:ss");
		@SuppressWarnings("unused")
		String a=sdf.format(p.getEventoCalcistico().getDataInizioEventoCalcistico().getTime());
		//System.out.println(a);
		
		
		GregorianCalendar scadenza=p.getScadenza();
		SimpleDateFormat sdf1 =new SimpleDateFormat("dd/MM/yyyy-HH:mm:ss");
		@SuppressWarnings("unused")
		String a1=sdf1.format(scadenza.getTime());
		
		//System.out.println(a1);
		}

}
