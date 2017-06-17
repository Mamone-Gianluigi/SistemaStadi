package it.unisa.prog2.progetto.graphics;

import java.awt.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JButton;
import it.unisa.prog2.progetto.core.*;

/**
 * Crea un pulsante che rappresenta il posto di un settore 
 */
public class PostoColorato extends JButton {
		
	/**
	 * Crea un nuovo pulsante arancio o azzurro sfumato
	 * @param text Il testo del pulsante
	 * @param post Il posto che vogliamo rappresentare e colorare
	 */
	public PostoColorato (String text,Posto post) {
		super(text);
		posto=post;
		// Prima togliamo al pulsante il riempimento grigio di default...
		setBorderPainted(false);
		setContentAreaFilled(false);
		// ...e poi gli togliamo l'evidenzazione blu iniziale 
		setFocusPainted(false);
	}
	
	/**
	 * Riscrive il paintComponent di JButton per dare al pulsante il riempimento sfumato e il contorno arancio
	 */
	public void paintComponent (Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		
		if(posto.isDisponibile()) 
			g2.setColor(Color.GREEN);
		else if(posto.isVenduto())
			g2.setColor(Color.RED);
		else
			g2.setColor(Color.YELLOW);

		g2.fillRect(0, 0, getWidth(), getHeight());
		g2.setColor(Color.BLACK);
		g2.setStroke(new BasicStroke(2));
		g2.drawRect(0, 0, getWidth(), getHeight());
		// Richiamiamo il paintComponent originale per stampare il testo al centro del pulsante
		super.paintComponent(g);
	}

	public Posto getPosto() {
		return posto;
	}

	public void setPosto(Posto posto) {
		this.posto = posto;
		
	}
	

	private static final long serialVersionUID = 1L;
	private Posto posto;

}
