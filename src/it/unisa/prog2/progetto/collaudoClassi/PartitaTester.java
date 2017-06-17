package it.unisa.prog2.progetto.collaudoClassi;

import it.unisa.prog2.progetto.core.*;

public class PartitaTester {

	public static void main(String[] args) throws CloneNotSupportedException {
		
		Partita partita=new Partita("Avellino","Napoli","Italiano","Serie A");

		
		System.out.println("NOME SQUADRA CASA : ");
		
		System.out.println(partita.getSquadraCasa());
		
		System.out.println("NOME SQUADRA OSPITE : ");
		
		System.out.println(partita.getSquadraOspite());
		
		System.out.println("COMPARAZIONE : ");
		System.out.println(partita.compara());
		
		System.out.println("TOSTRING : ");
		
		System.out.println(partita.getSquadraCasa());
		System.out.println(partita.toString());
		
		
		
	}
	
}
