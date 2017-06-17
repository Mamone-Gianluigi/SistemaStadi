package it.unisa.prog2.progetto.collaudoClassi;

import it.unisa.prog2.progetto.core.Settore;

public class SettoreTester {

	public static void main(String[] args) {
	try 
		{
			Settore a = new Settore("Tribuna Montevergine","5","10");
			a.riempiSettore();
			
			System.out.println("CAPIENZA DEL SETTORE :");
			System.out.println("capienza : "+a.getCapienza());
			a.aggiungiPostoPrenotato(2,'C');
			a.aggiungiPostoVenduto(5,'C');
			System.out.println("NUMERO POSTI PRENOTATI - VENDUTI E DISPONIBILI "
					+ "DOPO UNA PRENOTAZIONE E UNA VENDITA :");
			System.out.println("posti Venduti : "+a.getPostiVenduti());
			System.out.println("posti Prenotati : "+a.getPostiPrenotati());
			System.out.println("posti Disponibili : "+a.getPostiDisponibili());
			
			System.out.println("STAMPA SETTORE :");
			a.stampa();
			
			System.out.println("STAMPA SETTORE DOPO AVER AGGIUNTO POSTI PER FILA :");
			a.aggiungiPostiFila(8);
			a.stampa();
			
			System.out.println("STAMPA SETTORE DOPO AVER DIMINUITO POSTI PER FILA :");
			a.diminuisciPostiFila(5);
			a.stampa();

		
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
