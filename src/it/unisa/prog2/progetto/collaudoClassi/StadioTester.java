package it.unisa.prog2.progetto.collaudoClassi;

import it.unisa.prog2.progetto.core.*;
import it.unisa.prog2.progetto.exceptions.*;

public class StadioTester {

	public static void main(String[] args) throws CloneNotSupportedException, DatiNonValidiException{
		
		@SuppressWarnings("unused")
		Settore a2 = new Settore("Tribuna Montevergine","5","10");
		Settore a1 = new Settore("Tribuna","5","10");
		Stadio a=new Stadio("nome","città",10,a1);
		
		System.out.println("CAPIENZA STADIO : ");
		System.out.println(a.getCapienza());
		System.out.println("NOME STADIO : ");
		System.out.println(a.getNomeStadio());
		
		
		System.out.println("TOSTRING STADIO : ");
		System.out.println("\n"+a+"\n");
		
		Stadio e=(Stadio) a.clone();
		System.out.println("CAPIENZA STADIO RESTITUITO DAL CLONE: ");
		System.out.println(e.getCapienza());
		System.out.println("NOME STADIO RESTITUITO DAL CLONE: ");
		System.out.println(e.getNomeStadio());
		System.out.println("EQUALS FRA I DUE STADI : "+a.equals(e));
		
		e.setNomeStadio("dddddd");

		System.out.println("EQUALS FRA I DUE STADI DOPO AVER CAMBIATO NOME: "+a.equals(e));
		
		


	}

}
