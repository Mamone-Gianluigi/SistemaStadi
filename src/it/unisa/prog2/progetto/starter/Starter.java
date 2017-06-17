package it.unisa.prog2.progetto.starter;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import javax.swing.JFrame;

import it.unisa.prog2.progetto.core.*;
import it.unisa.prog2.progetto.exceptions.*;
import it.unisa.prog2.progetto.graphics.FrameIniziale;



public class Starter {
	
	//private static FrameIniziale inizio;
	
	public static void main(String[] args) throws IOException, DatiNonValidiException, CloneNotSupportedException, ClassNotFoundException {
		SistemaStadi sistema;
		
		File file = new File("sistemaStadi.dat");
		FileInputStream in;
		ObjectInputStream inStream;
		
		if (file.exists()) {
			System.out.println("--");
			in = new FileInputStream(file);
			inStream = new ObjectInputStream(in);

			sistema = (SistemaStadi)inStream.readObject();

			in.close();
			inStream.close();
		}
		else
			sistema = new SistemaStadi("Country Sport");
			
		/*sistema.stampaEventiCalcisticiPerCapienza();
		System.out.println("");
		sistema.stampaEventiCalcisticiData();
		System.out.println("");
		
		*/
		
		JFrame inizio=new FrameIniziale(sistema);
		inizio.setVisible(true);
		
		//sistema.stampaStadiCapienza();
	}
}
