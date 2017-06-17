package it.unisa.prog2.progetto.graphics;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.*;
import it.unisa.prog2.progetto.core.discount.*;
import it.unisa.prog2.progetto.core.*;
import it.unisa.prog2.progetto.exceptions.*;

/**
 * Il pannello che verr‡ aperto nel momento in cui un amministratore effettua il login
 */
public class AmministratoreFrame extends JFrame {
	
	/**
	 * Crea il pannello di lavoro di un utente amministratore, mostrando la finestra di benvenuto
	 * come schermata iniziale insieme ad una barra dei menu principali.
	 * @param sistema Il sistema con i dati relativi all'applicazione (account registrati, eventi calcistici...)
	 */
	public AmministratoreFrame (SistemaStadi sistema) {
	
		this.sistema=sistema;
		
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(new Point((dimension.width - getSize().width) / 2-250, 
		(dimension.height - getSize().height) / 2 -250));
		
		setSize(500,500);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false); // Il frame non deve essere ridimensionabile
		setUndecorated(true);
		setLayout(new BorderLayout());
		//men˘
		menu=creaMenuBarIniziale();
		add(menu,BorderLayout.NORTH);
		//pannello benvenuto utente
		descrizione=new JPanel();
		JLabel descrizione1=new JLabel("Benvenuto ");
		descrizione1.setFont(new Font("Georgia", Font.ITALIC, 20));
		descrizione1.setForeground(Color.BLACK);
		
		String nome=sistema.getAdminCorrente().getNome();
		JLabel descrizione2=new JLabel(nome);
		descrizione2.setFont(new Font("Georgia", Font.ITALIC, 22));
		descrizione2.setForeground(Color.RED);
		descrizione.add(descrizione1);
		descrizione.add(descrizione2);
		
		JPanel pannello=new JPanel();
		pannello.add(descrizione);
		add(pannello);
	}
	
	/**
	 * Crea la barra dei men˘ che verr‡ visualizzata quando un amministratore ha effettuato l'accesso.
	 * E' caratterizzata dai menu: File, Gestione, Inserisci e Visualizza
	 */
	private JMenuBar creaMenuBarIniziale () {
		JMenuBar barra = new JMenuBar();
		JMenu file;
		JMenu gestione;
		JMenu inserisci;
		JMenu visualizza;
		

		file = new JMenu("File");
		file.setFont(new Font("Georgia", Font.ITALIC, 13));
		file.setForeground(Color.BLACK);
		
		gestione = new JMenu("Gestione");
		gestione.setFont(new Font("Georgia", Font.ITALIC, 13));
		gestione.setForeground(Color.BLACK);
		
		inserisci = new JMenu("Inserisci");
		inserisci.setFont(new Font("Georgia", Font.ITALIC, 13));
		inserisci.setForeground(Color.BLACK);
		
		visualizza = new JMenu("Visualizza");
		visualizza.setFont(new Font("Georgia", Font.ITALIC, 13));
		visualizza.setForeground(Color.BLACK);

		JMenuItem exit = new JMenuItem("Esci"); // item del menu File
		exit.setForeground(Color.BLACK);
		exit.setFont(new Font("Georgia", Font.ITALIC, 13));
		JMenuItem logOut = new JMenuItem("LogOut"); // item del menu File
		logOut.setFont(new Font("Georgia", Font.ITALIC, 13));
		logOut.setForeground(Color.BLACK);
		file.add(logOut);
		file.add(exit);
		
		JMenuItem aggiungiAmministratore = new JMenuItem("Aggiungi amministratore"); //item del menu Gestione
		aggiungiAmministratore.setForeground(Color.BLACK);
		aggiungiAmministratore.setFont(new Font("Georgia", Font.ITALIC, 13));
		gestione.add(aggiungiAmministratore);
		JMenuItem modificaCapienzaStadio = new JMenuItem("Modifica capienza stadio"); // item del menu Gestione
		modificaCapienzaStadio.setForeground(Color.BLACK);
		modificaCapienzaStadio.setFont(new Font("Georgia", Font.ITALIC, 13));
		gestione.add(modificaCapienzaStadio);
		JMenu politicaSconto = new JMenu("Politiche di sconto"); // submenu del menu Gestione
		politicaSconto.setForeground(Color.BLACK);
		politicaSconto.setFont(new Font("Georgia", Font.ITALIC, 13));
		JMenuItem inserisciPolitica=new JMenuItem("Inserisci Politica di Sconto"); // item del submenu "Inserisci Politica di sconto" del menu Gestione
		inserisciPolitica.setForeground(Color.BLACK);
		inserisciPolitica.setFont(new Font("Georgia", Font.ITALIC, 13));
		JMenuItem rimuoviPolitica=new JMenuItem("Rimuovi Politica di Sconto"); // item del submenu "Inserisci Politica di sconto" del menu Gestione
		rimuoviPolitica.setForeground(Color.BLACK);
		rimuoviPolitica.setFont(new Font("Georgia", Font.ITALIC, 13));
		politicaSconto.add(inserisciPolitica);
		politicaSconto.add(rimuoviPolitica);
		gestione.add(politicaSconto);
		JMenuItem modificaPrezzoStad = new JMenuItem("Modifica prezzo stadio"); // item del menu Gestione
		modificaPrezzoStad.setForeground(Color.BLACK);
		modificaPrezzoStad.setFont(new Font("Georgia", Font.ITALIC, 13));
		gestione.add(modificaPrezzoStad);
		
		JMenuItem partita = new JMenuItem("Partita"); //item del menu Inserisci
		partita.setForeground(Color.BLACK);
		partita.setFont(new Font("Georgia", Font.ITALIC, 13));
		inserisci.add(partita);
		JMenuItem stadio = new JMenuItem("Stadio"); //item del menu Inserisci
		stadio.setForeground(Color.BLACK);
		stadio.setFont(new Font("Georgia", Font.ITALIC, 13));
		inserisci.add(stadio);
		JMenuItem eventoCalcistico = new JMenuItem("Evento calcististico"); //item del menu Inserisci
		eventoCalcistico.setForeground(Color.BLACK);
		eventoCalcistico.setFont(new Font("Georgia", Font.ITALIC, 13));
		inserisci.add(eventoCalcistico);
		
		JMenu incasso = new JMenu("Incasso"); // submenu del menu Visualizza
		incasso.setForeground(Color.BLACK);
		incasso.setFont(new Font("Georgia", Font.ITALIC, 13));
		JMenuItem incassoTota=new JMenuItem("Visualizza l'incasso totale"); // item del submenu "Incasso" del menu Visualizza
		incassoTota.setForeground(Color.BLACK);
		incassoTota.setFont(new Font("Georgia", Font.ITALIC, 13));
		JMenuItem incassoStad=new JMenuItem("Visualizza l'incasso di uno stadio"); //item del submenu "Incasso" del menu Visualizza
		incassoStad.setForeground(Color.BLACK);
		incassoStad.setFont(new Font("Georgia", Font.ITALIC, 13));
		incasso.add(incassoTota);
		incasso.add(incassoStad);
		visualizza.add(incasso);
		JMenuItem eventiCalcisticiPerCapienzaStadi = new JMenuItem("Eventi calcistici per capienza stadi"); // item del menu Visualizza
		eventiCalcisticiPerCapienzaStadi.setForeground(Color.BLACK);
		eventiCalcisticiPerCapienzaStadi.setFont(new Font("Georgia", Font.ITALIC, 13));
		visualizza.add(eventiCalcisticiPerCapienzaStadi);
		JMenuItem eventiCalcisticiPerData = new JMenuItem("Eventi calcistici in ordine cronologico"); // item del menu Visualizza
		eventiCalcisticiPerData.setForeground(Color.BLACK);
		eventiCalcisticiPerData.setFont(new Font("Georgia", Font.ITALIC, 13));
		visualizza.add(eventiCalcisticiPerData);
		
		
		barra.add(file);
		barra.add(gestione);
		barra.add(inserisci);
		barra.add(visualizza);
		
		/**
		 * Il listener della voce LogOut del menu File. 
		 * Se viene selezionata questa voce viene effettuato il logout dell'account corrente, ritornando alla finestra iniziale
		 * e chiudendo quella corrente
		 */
		class LogOutListener implements ActionListener {	
			public void actionPerformed(ActionEvent e) {
					sistema.logout();
					new FrameIniziale(sistema).setVisible(true);
					dispose();
			}
		}
		
		/**
		 * Il listener della voce Esci del menu File.
		 * Se viene selezionata questa voce il programma viene terminato.
		 */
		class ExitListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				FileOutputStream out;
				ObjectOutputStream outStream;
				try {
					out = new FileOutputStream(fileName);
					outStream = new ObjectOutputStream(out);
					outStream.writeObject(sistema); // Scriviamo sul file lo stato attuale del sistema
					
					// Infine chiudiamo i flussi
					out.close();
					outStream.close();
				} 
				catch (IOException e1) {}
				finally {
					System.exit(0);
				}
			}
		}	
		
		/**
		 * Il listener della voce Aggiungi Amministratore del menu Gestione.
		 * Se viene selezionata questa voce viene visualizzata la finestra inerente la registrazione di un nuovo amministratore.
		 */
		class AggiungiAmministratoreListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				registraAmministratore = registraAmministratore();
				registraAmministratore.setVisible(true);
			}
		}
		
		/**
		 * Il listener della voce  Modifica capienza Stadio del menu Gestione.
		 * Se viene selezionata questa voce viene visualizzata la finestra inerente la modifica della capienza degli stadi.
		 * Se perÚ non sono presenti stadi allora viene visualizzato un messaggio di errore.
		 */
		class ModificaCapienzaStadioListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				try {
					modificaCapienza= modificaCapienza();
					modificaCapienza.setVisible(true);	
				} catch (DatiNonValidiException e1) {
					def=new DefaultFrame(new JLabel(e1.getMessage()),
						"Errore",370,
						new ImageIcon(imgURLnonOK),
						new AmministratoreFrame(sistema));
					def.setVisible(true);
				}
				setVisible(false);
			}
		}
		
		/**
		 * Il listener della voce Inserisci Politica di Sconto del submenu Politiche di Sconto del menu Gestione.
		 * Se viene selezionata questa voce viene visualizzata la finestra inerente l'inserimento di politiche di sconto.
		 */
		class InserisciPoliticheScontoListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				inserisciPoliticaSconto= inserisciPoliticheSconto();
				inserisciPoliticaSconto.setVisible(true);		
				setVisible(false);
			}
		}
		
		/**
		 * Il listener della voce Rimuovi Politica di Sconto del submenu Politiche di Sconto del menu Gestione.
		 * Se viene selezionata questa voce viene visualizzata la finestra inerente la rimozione di politiche di sconto.
		 * Se non sono presenti politiche di sconto allora viene visualizzato un messaggio di errore.
		 */
		class RimuoviPoliticheScontoListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				try {
					rimuoviPoliticaSconto= rimuoviPoliticheSconto();
					rimuoviPoliticaSconto.setVisible(true);		
					
				} catch (DatiNonValidiException e1) {
					def=new DefaultFrame(new JLabel(e1.getMessage()),
							"Errore",500,
							new ImageIcon(imgURLnonOK),
							new AmministratoreFrame(sistema));
					def.setVisible(true);
				}
				setVisible(false);
			}
		}
		/**
		 * Il listener della voce Modifica prezzo stadio del menu Gestione.
		 * Se viene selezionata questa voce viene visualizzata la finestra inerente la modifica dei prezzi degli stadi.
		 * Se non sono presenti stadi allora viene visualizzato un messaggio di errore.
		 *
		 */
		class ModificaPrezzoStadioListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				try {
					modificaPrezzoStadio=modificaPrezzoStadio();
					modificaPrezzoStadio.setVisible(true);	
				} catch (DatiNonValidiException e1) {
					def=new DefaultFrame(new JLabel(e1.getMessage()),
							"Errore",370,
							new ImageIcon(imgURLnonOK),
							new AmministratoreFrame(sistema));
					def.setVisible(true);
				}
				setVisible(false);
			}
		}
		
		/**
		 * Il listener della voce Partita del menu Inserisci.
		 * Se viene selezionata questa voce viene visualizzata la finestra inerente l'inserimento di una nuova partita.
		 *
		 */
		class InserisciPartitaListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				JFrame f=inserisciPartita();
				f.setVisible(true);
				setVisible(false);
			}
		}
		
		/**
		 * Il listener della voce Stadio del menu Inserisci.
		 * Se viene selezionata questa voce viene visualizzata la finestra inerente l'inserimento di un nuovo stadio.
		 *
		 */
		class InserisciStadioListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				inserisciStadio=inserisciStadio();
				inserisciStadio.setVisible(true);
				setVisible(false);
			}
		}
		
		/**
		 * Il listener della voce Evento Calcistico del menu Inserisci.
		 * Se viene selezionata questa voce viene visualizzata la finestra inerente l'inserimento di un evento calcistico.
		 * Se non sono presenti partite e/o stadi allora viene visualizzato un messaggio di errore.
		 *
		 */
		class InserisciEventoCalcisticoListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				try {
					inserisciEventoCalcistico=inserisciEvento();
					inserisciEventoCalcistico.setVisible(true);
				} catch (DatiNonValidiException e1) {
					def=new DefaultFrame(new JLabel(e1.getMessage()),
						"Errore",390,
						new ImageIcon(imgURLnonOK),
						new AmministratoreFrame(sistema));
					def.setVisible(true);
				}
				setVisible(false);
			}
		}
		
		/**
		 * Il listener della voce Visualizza l'incasso totale del submenu Incasso del menu Visualizza.
		 * Se viene selezionata questa voce viene visualizzata la finestra inerente l'incasso totale.
		 */
		class IncassoTotaleListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				incassoTotale=incassoTotale();
				incassoTotale.setVisible(true);		
				setVisible(false);
			}
		}
		
		/**
		 * Il listener della voce Visualizza l'incasso di uno stadio del submenu Incasso del menu Visualizza.
		 * Se viene selezionata questa voce viene visualizzata la finestra inerente la visualizzazzione degli incassi degli stadi.
		 * Se non sono presenti stadi allora viene visualizzato un messaggio di errore.
		 *
		 */
		class IncassoStadioListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				try {
					incassoStadio=incassoStadio();
					incassoStadio.setVisible(true);	
				} catch (DatiNonValidiException e1) {
					def=new DefaultFrame(new JLabel(e1.getMessage()),
							"Errore",370,
							new ImageIcon(imgURLnonOK),
							new AmministratoreFrame(sistema));
					def.setVisible(true);
				}
				setVisible(false);
			}
		}
		
		/**
		 * Il listener della voce Eventi calcistici per capienza stadi del menu Visualizza.
		 * Se viene selezionata questa voce viene visualizzata la finestra inerente la visualizzazzione 
		 * degli eventi calcistici ordinati secondo la capienza degli stadi.
		 * Se non sono presenti eventi calcistici allora viene visualizzato un messaggio di errore.
		 */
		class VisualizzaEventiPerCapienzaStadioListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				try 
				{
					try 
					{
						visualizzaEventiCalcisticiPerCapienza= eventiCalcisticiPerCapienza();
						visualizzaEventiCalcisticiPerCapienza.setVisible(true);
					} 
					catch (DatiNonValidiException e1) {
						def=new DefaultFrame(new JLabel(e1.getMessage()),
							"Errore",370,
							new ImageIcon(imgURLnonOK),
							new AmministratoreFrame(sistema));	
						def.setVisible(true);
					}
				}
				catch (CloneNotSupportedException e1) {
					return;
				}
				setVisible(false);
			}
		}
		
		/**
		 * Il listener della voce Eventi calcistici in ordine cronologico del menu Visualizza.
		 * Se viene selezionata questa voce viene visualizzata la finestra inerente la visualizzazzione 
		 * degli eventi calcistici in base a un ordine cronologico.
		 * Se non sono presenti eventi calcistici allora viene visualizzato un messaggio di errore.
		 */
		class VisualizzaEventiPerDataListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				try 
				{
					try 
					{
						visualizzaEventiCalcisticiPerData= eventiCalcisticiPerData();
						visualizzaEventiCalcisticiPerData.setVisible(true);
					} 
					catch (DatiNonValidiException e1) 
					{
						def=new DefaultFrame(new JLabel(e1.getMessage()),
							"Errore",370,
							new ImageIcon(imgURLnonOK),
							new AmministratoreFrame(sistema));	
						def.setVisible(true);
					}
				} 
				catch (CloneNotSupportedException e1) {
					return;
				}
				setVisible(false);
			}
		}
		
		incassoStad.addActionListener(new IncassoStadioListener());
		incassoTota.addActionListener(new IncassoTotaleListener());
		rimuoviPolitica.addActionListener(new RimuoviPoliticheScontoListener());
		inserisciPolitica.addActionListener(new InserisciPoliticheScontoListener());
		modificaPrezzoStad.addActionListener(new ModificaPrezzoStadioListener());
		modificaCapienzaStadio.addActionListener(new ModificaCapienzaStadioListener());
		eventoCalcistico.addActionListener(new InserisciEventoCalcisticoListener());
		stadio.addActionListener(new InserisciStadioListener());
		partita.addActionListener(new InserisciPartitaListener());
		exit.addActionListener(new ExitListener());
		logOut.addActionListener(new LogOutListener());
		aggiungiAmministratore.addActionListener(new AggiungiAmministratoreListener());
		eventiCalcisticiPerData.addActionListener(new VisualizzaEventiPerDataListener());
		eventiCalcisticiPerCapienzaStadi.addActionListener(new VisualizzaEventiPerCapienzaStadioListener());
		
		return barra;
	}
	
	/**
	 * Crea il form per la registrazione di un nuovo amministratore
	 */
	private JFrame registraAmministratore() {
		final JFrame frame=new JFrame();	// crea il frame per la registrazione dell'amministratore	
		
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(new Point((dimension.width - frame.getSize().width) / 2-175, 
		(dimension.height - frame.getSize().height) / 2 -135));
		
		frame.setSize(350,270);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLayout(new BorderLayout()); // settiamo il frame della registrazione del nuovo amministratore come layout a bordi
		frame.setUndecorated(true);
		
		JPanel pannello=new JPanel();
		pannello.setLayout(new BorderLayout()); // creiamo e settiamo un pannello come layout a bordi
		JLabel label =new JLabel("Inserisci i dati richiesti");
		label.setFont(new Font("Georgia", Font.PLAIN, 18));
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setForeground(Color.BLACK);
		pannello.add(label,BorderLayout.NORTH); // inseriamo a nord del layout a bordi del pannello come titolo "inserisci i dati richiesti"
	
		JPanel dati = new JPanel(); // creiamo un pannello di dati in cui inserire le label e i text field riguardanti le generalit‡ del nuovo amministartore
		JLabel nome = new JLabel("Nome*");
		JLabel cognome = new JLabel("Cognome*");
		JLabel username = new JLabel("Username*");
		JLabel password = new JLabel("Password*");
		final JTextField nomeField = new JTextField(8);
		final JTextField cognomeField = new JTextField(8);
		final JTextField usernameField = new JTextField(8);
		final JTextField passwordField = new JTextField(8);
		nome.setFont(new Font("Georgia", Font.PLAIN, 18));
		cognome.setFont(new Font("Georgia", Font.PLAIN, 18));
		username.setFont(new Font("Georgia", Font.PLAIN, 18));
		password.setFont(new Font("Georgia", Font.PLAIN, 18));
		nomeField.setFont(new Font("Georgia", Font.PLAIN, 18));
		cognomeField.setFont(new Font("Georgia", Font.PLAIN, 18));
		usernameField.setFont(new Font("Georgia", Font.PLAIN, 18));
		passwordField.setFont(new Font("Georgia", Font.PLAIN, 18));
		nome.setHorizontalAlignment(JLabel.CENTER);
		cognome.setHorizontalAlignment(JLabel.CENTER);
		username.setHorizontalAlignment(JLabel.CENTER);
		password.setHorizontalAlignment(JLabel.CENTER);
		nomeField.setHorizontalAlignment(JLabel.CENTER);
		cognomeField.setHorizontalAlignment(JLabel.CENTER);
		usernameField.setHorizontalAlignment(JLabel.CENTER);
		passwordField.setHorizontalAlignment(JLabel.CENTER);
		nome.setForeground(Color.BLACK);
		cognome.setForeground(Color.BLACK);
		username.setForeground(Color.BLACK);
		password.setForeground(Color.BLACK);
		nomeField.setForeground(Color.BLACK);
		cognomeField.setForeground(Color.BLACK);
		usernameField.setForeground(Color.BLACK);
		passwordField.setForeground(Color.BLACK);
		dati.setLayout(new GridLayout(4, 2)); // settiamo il pannello dati come layout a griglia
		//inseriamo le label e i relativi text field
		dati.add(nome);
		dati.add(nomeField);
		dati.add(cognome);
		dati.add(cognomeField);
		dati.add(username);
		dati.add(usernameField);
		dati.add(password);
		dati.add(passwordField);
		
		JLabel l=new JLabel("*campi obbligatori  "); 
		l.setFont(new Font("Georgia", Font.PLAIN, 13));
		l.setHorizontalAlignment(JLabel.RIGHT);
		l.setForeground(Color.BLACK);
		
		JPanel bottoni= new JPanel(); // creiamo un pannello bottoni dove inseriremo i button necessari
		JButton buttonConferma=new JButton("Conferma");
		buttonConferma.setFont(new Font("Georgia", Font.PLAIN, 18));
		buttonConferma.setForeground(Color.BLACK);
		JButton buttonAnnulla=new JButton("Annulla");
		buttonAnnulla.setFont(new Font("Georgia", Font.PLAIN, 18));
		buttonAnnulla.setForeground(Color.BLACK);
		// inseriamo i due button nel pannello bottoni
		bottoni.add(buttonAnnulla);
		bottoni.add(buttonConferma);
		
		JPanel registrazioneUtente=new JPanel(); 
		registrazioneUtente.setLayout(new BorderLayout()); // creiamo un pannello registrazioneUtente e lo settiamo come layout a bordi
		TitledBorder a=new TitledBorder(new EtchedBorder(), "Registrazione Amministratore");
		a.setTitleFont(new Font("Georgia", Font.ITALIC, 13));
		a.setTitleColor(Color.BLACK);
		registrazioneUtente.setBorder(a);
		// inseriamo nel pannello registrazioneUtente a nord la label "campi obbligatori" (spostata a destra), al centro il pannello dati, e a sud i button
		registrazioneUtente.add(l,BorderLayout.NORTH);
		registrazioneUtente.add(dati,BorderLayout.CENTER);
		registrazioneUtente.add(bottoni,BorderLayout.SOUTH);
		
		//inseriamo nel panel pannello al centro il panel registrazioneUtente
		pannello.add(registrazioneUtente, BorderLayout.CENTER);
		//Aggiungiamo al centro del frame il panel pannello
		frame.add(pannello,BorderLayout.CENTER);
		
		/**
		 * Il listener per il pulsante Annulla. 
		 * Se l'amministratore non vuole confermare i dati inseriti, selezionando il pulsante Annulla, 
		 * la finestra riguardante la registrazione del nuovo amministretore si chiude 
		 * ritornando alla schermate iniziale dell'amministratore.
		 *
		 */
		class AnnullaListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				setVisible(true);
			}
		}

		/**
		 * Il listener per il pulsante Conferma. 
		 * Se l'amministratore vuole confermare i dati inseriti e procedere dunque con la registrazione, preme il tasto Conferma. 
		 * Il listener passa i dati inseriti al sistema e, se sono corretti, procede con la registrazione dell'amministratore
		 * presentando un messaggio di registrazione riuscita.
		 * Altrimenti, se i dati inseriti non sono corretti, viene visualizzato un messaggio di errore.
		 *
		 */
		class ConfermaListener implements ActionListener {
			public void actionPerformed (ActionEvent e) {
				try 
				{
					sistema.creaAmministratore(nomeField.getText(),cognomeField.getText(),
							usernameField.getText(),passwordField.getText());
					registraAmministratore.dispose();
					def=new DefaultFrame(new JLabel("La registrazione Ë andata a buon fine"),
						"Registrazione riuscita",390,
						new ImageIcon(imgURLOK),
						new AmministratoreFrame(sistema));	
					def.setVisible(true);
				}
				catch (DatiNonValidiException e1)
				{
					registraAmministratore.setVisible(false);
					def=new DefaultFrame(new JLabel(e1.getMessage()),
							"Registrazione non riuscita",430,
							new ImageIcon(imgURLnonOK),
							registraAmministratore);	
					def.setVisible(true);
				}	
			}
		}
		
		buttonConferma.addActionListener(new ConfermaListener());
		buttonAnnulla.addActionListener(new AnnullaListener());
		return frame;
	}
	
	/**
	 * Crea il form per l'inserimento di una nuova partita
	 */
	private JFrame inserisciPartita() {
		final JFrame inserisciPartita=new JFrame();	// creiamo il frame del'inserimento partita	
		
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		inserisciPartita.setLocation(new Point((dimension.width - 
				inserisciPartita.getSize().width) / 2-175, 
		(dimension.height - inserisciPartita.getSize().height) / 2 -135));
		
		inserisciPartita.setSize(350,270);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		inserisciPartita.setResizable(false);
		inserisciPartita.setLayout(new BorderLayout()); // settiamo il frame come layout a bordi
		inserisciPartita.setUndecorated(true);
		
		JPanel pannello=new JPanel();
		pannello.setLayout(new BorderLayout()); // creiamo un panel pannello e lo settiamo come layout a bordi
		
		JLabel label =new JLabel("Inserisci i dati richiesti");
		label.setFont(new Font("Georgia", Font.PLAIN, 18));
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setForeground(Color.BLACK);
		
		pannello.add(label,BorderLayout.NORTH); // inseriamo a nord del pannello la label "inserisci i dati richiesti" (spostata al centro)
		
		JPanel dati = new JPanel(); // creiamo un panel dati in cui inseriremo le label e i text field riguardanti le informazione della nuova partita da inserire
		JLabel squadraCasa = new JLabel("squadraCasa*");
		JLabel squadraOspite = new JLabel("squadraOspite*");
		JLabel campionato= new JLabel("campionato*");
		JLabel lega = new JLabel("lega*");
		final JTextField squadraCasaField = new JTextField(8);
		final JTextField squadraOspiteField = new JTextField(8);
		final JTextField campionatoField = new JTextField(8);
		final JTextField legaField = new JTextField(8);
		squadraCasa.setFont(new Font("Georgia", Font.PLAIN, 18));
		squadraOspite.setFont(new Font("Georgia", Font.PLAIN, 18));
		campionato.setFont(new Font("Georgia", Font.PLAIN, 18));
		lega.setFont(new Font("Georgia", Font.PLAIN, 18));
		squadraCasaField.setFont(new Font("Georgia", Font.PLAIN, 18));
		squadraOspiteField.setFont(new Font("Georgia", Font.PLAIN, 18));
		campionatoField.setFont(new Font("Georgia", Font.PLAIN, 18));
		legaField.setFont(new Font("Georgia", Font.PLAIN, 18));
		squadraCasa.setHorizontalAlignment(JLabel.CENTER);
		squadraOspite.setHorizontalAlignment(JLabel.CENTER);
		campionato.setHorizontalAlignment(JLabel.CENTER);
		lega.setHorizontalAlignment(JLabel.CENTER);
		squadraCasaField.setHorizontalAlignment(JLabel.CENTER);
		squadraOspiteField.setHorizontalAlignment(JLabel.CENTER);
		campionatoField.setHorizontalAlignment(JLabel.CENTER);
		legaField.setHorizontalAlignment(JLabel.CENTER);
		squadraCasa.setForeground(Color.BLACK);
		squadraOspite.setForeground(Color.BLACK);
		campionato.setForeground(Color.BLACK);
		lega.setForeground(Color.BLACK);
		squadraCasaField.setForeground(Color.BLACK);
		squadraOspiteField.setForeground(Color.BLACK);
		campionatoField.setForeground(Color.BLACK);
		legaField.setForeground(Color.BLACK);
		dati.setLayout(new GridLayout(4, 2)); // settiamo il pannello dati come layout a griglia
		// inseriamo le lebel e i textfield corrispondenti nel pannello dati
		dati.add(squadraCasa);
		dati.add(squadraCasaField);
		dati.add(squadraOspite);
		dati.add(squadraOspiteField);
		dati.add(campionato);
		dati.add(campionatoField);
		dati.add(lega);
		dati.add(legaField);
		
		
		JLabel l=new JLabel("*campi obbligatori  ");
		l.setFont(new Font("Georgia", Font.PLAIN, 13));
		l.setHorizontalAlignment(JLabel.RIGHT);
		l.setForeground(Color.BLACK);
		
		JPanel bottoni= new JPanel(); // criamo un panel bottoni dove inseriremo i button 
		JButton buttonConferma=new JButton("Conferma");
		buttonConferma.setFont(new Font("Georgia", Font.PLAIN, 18));
		buttonConferma.setForeground(Color.BLACK);
		JButton buttonAnnulla=new JButton("Annulla");
		buttonAnnulla.setFont(new Font("Georgia", Font.PLAIN, 18));
		buttonAnnulla.setForeground(Color.BLACK);
		// inseriamo i button Annulla e Conferma nel panel bottoni
		bottoni.add(buttonAnnulla);
		bottoni.add(buttonConferma);
		
		JPanel inserisciPar=new JPanel();
		inserisciPar.setLayout(new BorderLayout()); // creiamo un panel inserisciPar e lo settiamo come layout a bordi
		TitledBorder a=new TitledBorder(new EtchedBorder(), "Inserisci Partita");
		a.setTitleFont(new Font("Georgia", Font.ITALIC, 13));
		a.setTitleColor(Color.BLACK);
		inserisciPar.setBorder(a);
		//inseriamo nel panel inserisciPar a nord la label "campi obbligatori" (spostata a destra), al centro il panel dati, a sud il panel bottoni
		inserisciPar.add(l,BorderLayout.NORTH);
		inserisciPar.add(dati,BorderLayout.CENTER);
		inserisciPar.add(bottoni,BorderLayout.SOUTH);
		//Aggiungiamo al centro del panel pannello, il panel inserisciPar 
		pannello.add(inserisciPar, BorderLayout.CENTER);
		//Aggiungiamo al centro del frame il panel pannello
		inserisciPartita.add(pannello,BorderLayout.CENTER);
		
		/**
		 * Il listener per il pulsante Annulla. 
		 * Se l'amministratore non vuole confermare i dati inseriti, selezionando il pulsante Annulla, 
		 * la finestra riguardante l'inserimento della nuova partita si chiude 
		 * ritornando alla schermate iniziale dell'amministratore.
		 *
		 */
		class AnnullaListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				inserisciPartita.dispose();
				setVisible(true);
			}
		}
	
		/**
		 * Il listener per il pulsante Conferma. 
		 * Se l'amministratore vuole confermare i dati inseriti e procedere dunque con l'inserimento della nuova partita, preme il tasto Conferma. 
		 * Il listener passa i dati inseriti al sistema e, se sono corretti, procede con l'inserimento della partita,
		 * presentando un messaggio di inserimento corretto della partita.
		 * Altrimenti, se i dati inseriti non sono corretti, viene visualizzato un messaggio di errore.
		 *
		 */
		class ConfermaListener implements ActionListener {
			public void actionPerformed (ActionEvent e) {
				try 
				{	
					sistema.aggiungiPartita(squadraCasaField.getText(),
							squadraOspiteField.getText(),campionatoField.getText(),
							legaField.getText());
					inserisciPartita.dispose();
					def=new DefaultFrame(new JLabel("La partita Ë stata inserita correttamente"),
							"Partita inserita",390,
							new ImageIcon(imgURLOK),
							new AmministratoreFrame(sistema));
					def.setVisible(true);
				}
				catch (DatiNonValidiException e1)
				{
					inserisciPartita.setVisible(false);
					def=new DefaultFrame(new JLabel(e1.getMessage()),
							"Partita non inserita",390,
							new ImageIcon(imgURLnonOK),
							inserisciPartita);
					def.setVisible(true);
				}
			}
		}
		
		buttonConferma.addActionListener(new ConfermaListener());
		buttonAnnulla.addActionListener(new AnnullaListener());
		return inserisciPartita;
	}
	
	/**
	 * Crea il form per l'inserimento di un nuovo stadio
	 */
	private JFrame inserisciStadio() {
			
		final JFrame inserisciStadio=new JFrame();		// creiamo il frame inserisciStadio
			
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		inserisciStadio.setLocation(new Point((dimension.width - 
				inserisciStadio.getSize().width) / 2-180, 
		(dimension.height - inserisciStadio.getSize().height) / 2 -170));
			
		inserisciStadio.setSize(360, 340);
		inserisciStadio.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		inserisciStadio.setResizable(false);
		inserisciStadio.setLayout(new BorderLayout()); //settiamo il frame come layout a bordi
		inserisciStadio.setUndecorated(true);
			
		final ArrayList<Settore> sett=new ArrayList<>();
			
		JPanel pannello=new JPanel(); 
		pannello.setLayout(new BorderLayout());// creiamo il panel pannello e lo settiamo come layout a bordi
			
		JLabel label =new JLabel("Inserisci i dati richiesti");
		label.setFont(new Font("Georgia", Font.PLAIN, 18));
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setForeground(Color.BLACK);
			
		pannello.add(label,BorderLayout.NORTH); // inseriamo a nord del panel pannello la label "Inserisci i dati richiesti" (sposta al centro)
			
		JPanel dati = new JPanel(); // crea un panel dati in cui inserire le label e i text field associati riguardanti le informazioni sull'inserimento di un nuovo stadio
		JLabel nomeStadio = new JLabel("nomeStadio*");
		JLabel citt‡Stadio = new JLabel("citt‡Stadio*");
		JLabel prezzo = new JLabel("prezzo*");
		JLabel nomeSettore = new JLabel("nomeSettore*");
		JLabel numeroFile = new JLabel("numeroFile*");
		JLabel numeroPostiFila = new JLabel("numeroPostiFila*");
		final JTextField nomeStadioField=new JTextField(10);			
		final JTextField citt‡StadioField=new JTextField(10);
		final JTextField prezzoField=new JTextField(10);
		final JTextField nomeSettoreField=new JTextField(10);			
		final JTextField numeroFileField=new JTextField(10);			
		final JTextField numeroPostiFilaField=new JTextField(10);
		nomeStadio.setFont(new Font("Georgia", Font.PLAIN, 18));
		citt‡Stadio.setFont(new Font("Georgia", Font.PLAIN, 18));
		nomeStadioField.setFont(new Font("Georgia", Font.PLAIN, 18));
		citt‡StadioField.setFont(new Font("Georgia", Font.PLAIN, 18));
		nomeSettore.setFont(new Font("Georgia", Font.PLAIN, 18));
		numeroFile.setFont(new Font("Georgia", Font.PLAIN, 18));
		numeroPostiFila.setFont(new Font("Georgia", Font.PLAIN, 18));
		nomeSettoreField.setFont(new Font("Georgia", Font.PLAIN, 18));
		numeroFileField.setFont(new Font("Georgia", Font.PLAIN, 18));
		numeroPostiFilaField.setFont(new Font("Georgia", Font.PLAIN, 18));
		prezzo.setFont(new Font("Georgia", Font.PLAIN, 18));
		prezzoField.setFont(new Font("Georgia", Font.PLAIN, 18));
		nomeStadio.setHorizontalAlignment(JLabel.CENTER);
		citt‡Stadio.setHorizontalAlignment(JLabel.CENTER);			
		nomeStadioField.setHorizontalAlignment(JLabel.CENTER);
		citt‡StadioField.setHorizontalAlignment(JLabel.CENTER);
		nomeSettore.setHorizontalAlignment(JLabel.CENTER);
		numeroFile.setHorizontalAlignment(JLabel.CENTER);
		numeroPostiFila.setHorizontalAlignment(JLabel.CENTER);
		nomeSettoreField.setHorizontalAlignment(JLabel.CENTER);
		numeroFileField.setHorizontalAlignment(JLabel.CENTER);
		numeroPostiFilaField.setHorizontalAlignment(JLabel.CENTER);
		prezzo.setHorizontalAlignment(JLabel.CENTER);
		prezzoField.setHorizontalAlignment(JLabel.CENTER);
		nomeStadio.setForeground(Color.BLACK);
		citt‡Stadio.setForeground(Color.BLACK);
		nomeStadioField.setForeground(Color.BLACK);
		citt‡StadioField.setForeground(Color.BLACK);
		nomeSettore.setForeground(Color.BLACK);
		numeroFile.setForeground(Color.BLACK);
		numeroPostiFila.setForeground(Color.BLACK);
		nomeSettoreField.setForeground(Color.BLACK);
		numeroFileField.setForeground(Color.BLACK);
		numeroPostiFilaField.setForeground(Color.BLACK);
		prezzo.setForeground(Color.BLACK);
		prezzoField.setForeground(Color.BLACK);
			
		dati.setLayout(new GridLayout(6, 2)); // settiamo il panel dati come layout a griglia
		// inseriamo nel panel dati le label e i text field riguardanti l'inserimento del nuovo stadio
		dati.add(nomeStadio);
		dati.add(nomeStadioField);
		dati.add(citt‡Stadio);
		dati.add(citt‡StadioField);
		dati.add(prezzo);
		dati.add(prezzoField);
		dati.add(nomeSettore);
		dati.add(nomeSettoreField);
		dati.add(numeroFile);
		dati.add(numeroFileField);
		dati.add(numeroPostiFila);
		dati.add(numeroPostiFilaField);
			
			
		JPanel inserisci=new JPanel(); // creiamo il panel inserisci in cui inseriremo una label e un button
		final JButton aggiungi=new JButton("Aggiungi Settore");
		aggiungi.setFont(new Font("Georgia", Font.PLAIN, 18));
		aggiungi.setForeground(Color.BLACK);
		final JLabel lab=new JLabel(); // crea una label che terr‡ conto del numero dei settori presenti
		lab.setFont(new Font("Georgia", Font.PLAIN, 18));
		lab.setForeground(Color.BLACK);
		// inseriamo nel panel inserisci la label e il button
		inserisci.add(lab);
		inserisci.add(aggiungi);
				
		
		JLabel l=new JLabel("*campi obbligatori  ");
		l.setFont(new Font("Georgia", Font.PLAIN, 13));
		l.setHorizontalAlignment(JLabel.RIGHT);
		l.setForeground(Color.BLACK);
			
		JPanel bottoni= new JPanel(); // creiamo il panel bottoni dove inseriremo due button
		JButton buttonConferma=new JButton("Conferma");
		buttonConferma.setFont(new Font("Georgia", Font.PLAIN, 18));
		buttonConferma.setForeground(Color.BLACK);
		JButton buttonAnnulla=new JButton("Annulla");
		buttonAnnulla.setFont(new Font("Georgia", Font.PLAIN, 18));
		buttonAnnulla.setForeground(Color.BLACK);
		// inseriamo nel panel bottoni i button Annulla e Conferma
		bottoni.add(buttonAnnulla);
		bottoni.add(buttonConferma);
			
		JPanel inserisciStad=new JPanel(); 
		inserisciStad.setLayout(new BorderLayout()); // creiamo il panel inserisciStad e lo settiamo come layout a bordi
		TitledBorder a1=new TitledBorder(new EtchedBorder(), "Inserisci Stadio");
		a1.setTitleFont(new Font("Georgia", Font.ITALIC, 13));
		a1.setTitleColor(Color.BLACK);
		inserisciStad.setBorder(a1);
		// inseriamo nel panel inserisciStad a nord la label "campi obbligatori" e a sud il panel dati	
		inserisciStad.add(l,BorderLayout.NORTH);
		inserisciStad.add(dati,BorderLayout.CENTER);
		
		JPanel sud=new JPanel();
		sud.setLayout(new GridLayout(2,1)); // creiamo il panel sud e lo settiamo come griglia
		// inseriamo nel panel sud, il panel inserisci (label e button Aggiungi Settore) e il panel bottoni (button Conferma e Annulla)
		sud.add(inserisci);
		sud.add(bottoni);
		// inseriamo a sud del panel inserisciStad, il panel sud
		inserisciStad.add(sud,BorderLayout.SOUTH);
		// inseriamo al centro del panel pannello, il panel inserisciStad	
		pannello.add(inserisciStad, BorderLayout.CENTER);
		// inseriamo al centro del panel inserisciStadio, il panel pannello
		inserisciStadio.add(pannello,BorderLayout.CENTER);
		
		/**
		 * Il listener per il pulsante Annulla. 
		 * Se l'amministratore non vuole confermare i dati inseriti, selezionando il pulsante Annulla, 
		 * la finestra riguardante l'inserimento del nuovo stadio si chiude 
		 * ritornando alla schermate iniziale dell'amministratore.
		 *
		 */
		class AnnullaListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				inserisciStadio.dispose();
				setVisible(true);
			}
		}
		
		/**
		 * Il listener per il pulsante Conferma. 
		 * Se l'amministratore vuole confermare i dati inseriti e procedere dunque con l'inserimento del nuovo stadio, preme il tasto Conferma. 
		 * Il listener passa i dati inseriti al sistema e, se sono corretti, procede con l'inserimento dello stadio,
		 * presentando un messaggio di inserimento corretto dello stadio.
		 * Altrimenti, se i dati inseriti non sono corretti, viene visualizzato un messaggio di errore.
		 *
		 */
		class ConfermaListener implements ActionListener {
			public void actionPerformed (ActionEvent e) {						
				try
				{
					inserisciStadio.setVisible(false);
						
					sistema.aggiungiStadio(nomeStadioField.getText(),
							citt‡StadioField.getText(),prezzoField.getText(),
							nomeSettoreField.getText(),
							numeroFileField.getText(),
							numeroPostiFilaField.getText());
								
					int i=sistema.getStadi().size()-1;
					for (Settore ss:sett)
						sistema.aggiungiSettoreStadio(sistema.getStadi().get(i),
							ss.getNomeSettore(),""+ss.getNumeroFile(),
							""+ss.getNumeroPostiFila());
					def=new DefaultFrame(new JLabel(
						"Stadio inserito correttamente"),"Stadio inserito",390,
						new ImageIcon(imgURLOK),
						new AmministratoreFrame(sistema));	
					def.setVisible(true);
				}
				catch (DatiNonValidiException e1)
				{
					inserisciStadio.setVisible(false);
					def=new DefaultFrame(new JLabel(
						e1.getMessage()),"Stadio non inserito",390,
						new ImageIcon(imgURLnonOK),
						inserisciStadio());	
					def.setVisible(true);
				}		
			}
		}	
		
		/**
		 *  Il listener per il pulsante Aggiungi Settore. 
		 * Se l'amministratoe ha inserito tutti i dati e vuole aggiungere un altro settore, selezionando il pulsante Aggiungi 
		 * viene visualizzata la schermata riguardante l'inserimento di un nuovo settore.
		 *
		 */
		class AggiungiListener implements ActionListener {
			public void actionPerformed (ActionEvent e) {	
				inserisciStadio.setVisible(false);
				inserisciSettore(sett,aggiungi,lab).setVisible(true);						
			}
		}
		aggiungi.addActionListener(new AggiungiListener());
		buttonConferma.addActionListener(new ConfermaListener());
		buttonAnnulla.addActionListener(new AnnullaListener());
		return inserisciStadio;
	}

	/**
	 * Crea il form per l'inserimento di un nuovo evento calcistico
	 */
	private JFrame inserisciEvento() throws DatiNonValidiException {
		
		if(sistema.getStadi().size()==0 && sistema.getPartite().size()==0)
			throw new DatiNonValidiException("Non sono presenti nÈ stadi e nÈ partite");
		else if (sistema.getStadi().size()==0 && sistema.getPartite().size()!=0)
			throw new DatiNonValidiException("Non sono presenti stadi");
		else if (sistema.getStadi().size()!=0 && sistema.getPartite().size()==0)
			throw new DatiNonValidiException("Non sono presenti partite");	
		else{
			final JFrame inserisciEvento=new JFrame();	// crea il frame solo nel caso in cui nel sistema siano presenti partite e stadi	
		
			Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
			inserisciEvento.setLocation(new Point((dimension.width - 
					inserisciEvento.getSize().width) / 2-300, 
			(dimension.height - inserisciEvento.getSize().height) / 2 -150));
			
			inserisciEvento.setSize(600,300);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			inserisciEvento.setResizable(false);
			inserisciEvento.setLayout(new BorderLayout());
			inserisciEvento.setUndecorated(true);
			
			final JComboBox<String> partiteCombo = new JComboBox<String>();
			partiteCombo.setFont(new Font("Georgia", Font.PLAIN, 18));
			ArrayList<Partita> partite=sistema.getPartite();
			for(int i=0; i<partite.size(); i++){
				Partita p=partite.get(i);
				partiteCombo.addItem((i+1)+"-"+p.getSquadraCasa()+
						"-"+p.getSquadraOspite()+"-"+p.getCampionato()+"-"+p.getLega());
			}
			final JComboBox<String> stadioCombo = new JComboBox<String>();
			stadioCombo.setFont(new Font("Georgia", Font.PLAIN, 18));
			ArrayList<Stadio> stadi=sistema.getStadi();
			for(int i=0; i<stadi.size(); i++){
				Stadio s=stadi.get(i);
				stadioCombo.addItem((i+1)+"-"+s.getNomeStadio()+"-"+s.getCitt‡Stadio()+"-"+s.getCapienza());
			}
			
			JPanel pannello=new JPanel();
			pannello.setLayout(new BorderLayout());
			
			JLabel label =new JLabel("Inserisci i dati richiesti");
			label.setFont(new Font("Georgia", Font.PLAIN, 18));
			label.setHorizontalAlignment(JLabel.CENTER);
			label.setForeground(Color.BLACK);
			
			pannello.add(label,BorderLayout.NORTH);
			
			JPanel datiPartitaStadio = new JPanel();
			datiPartitaStadio.setLayout(new GridLayout(2,1));
			JLabel partita = new JLabel("partita*");
			JLabel stadio = new JLabel("stadio*");
			partita.setFont(new Font("Georgia", Font.PLAIN, 18));
			stadio.setFont(new Font("Georgia", Font.PLAIN, 18));
			stadio.setHorizontalAlignment(JLabel.CENTER);
			partita.setForeground(Color.BLACK);
			stadio.setForeground(Color.BLACK);				
			datiPartitaStadio.setLayout(new GridLayout(2, 1));
			JPanel partitaPanel=new JPanel();
			partitaPanel.add(partita);
			partitaPanel.add(partiteCombo);
			JPanel stadioPanel=new JPanel();
			stadioPanel.add(stadio);
			stadioPanel.add(stadioCombo);	
			datiPartitaStadio.add(partitaPanel);
			datiPartitaStadio.add(stadioPanel);
			
			JPanel dataPanel = new JPanel();
			JLabel giorno = new JLabel("giorno*");
			JLabel mese = new JLabel("mese*");
			JLabel anno = new JLabel("anno*");
			final JTextField giornoField = new JTextField(2);	
			final JTextField meseField = new JTextField(2);	
			final JTextField annoField = new JTextField(4);	
			giorno.setFont(new Font("Georgia", Font.PLAIN, 18));
			giorno.setForeground(Color.BLACK);
			mese.setFont(new Font("Georgia", Font.PLAIN, 18));
			mese.setForeground(Color.BLACK);
			anno.setFont(new Font("Georgia", Font.PLAIN, 18));
			anno.setForeground(Color.BLACK);
			giornoField.setFont(new Font("Georgia", Font.PLAIN, 18));	
			giornoField.setForeground(Color.BLACK);
			meseField.setFont(new Font("Georgia", Font.PLAIN, 18));	
			meseField.setForeground(Color.BLACK);
			annoField.setFont(new Font("Georgia", Font.PLAIN, 18));	
			annoField.setForeground(Color.BLACK);
			dataPanel.add(giorno);
			dataPanel.add(giornoField);
			dataPanel.add(mese);
			dataPanel.add(meseField);
			dataPanel.add(anno);
			dataPanel.add(annoField);
			
			JPanel oraPanel = new JPanel();
			JLabel ora = new JLabel("Ora*");
			JLabel minuti= new JLabel("Minuti*");
			final JTextField oraField = new JTextField(2);	
			final JTextField minutiField = new JTextField(2);	
			ora.setFont(new Font("Georgia", Font.PLAIN, 18));
			ora.setForeground(Color.BLACK);
			minuti.setFont(new Font("Georgia", Font.PLAIN, 18));
			minuti.setForeground(Color.BLACK);
			oraField.setFont(new Font("Georgia", Font.PLAIN, 18));	
			oraField.setForeground(Color.BLACK);
			minutiField.setFont(new Font("Georgia", Font.PLAIN, 18));	
			minutiField.setForeground(Color.BLACK);
			oraPanel.add(ora);
			oraPanel.add(oraField);
			oraPanel.add(minuti);
			oraPanel.add(minutiField);
			
			JPanel dataOraPanel =new JPanel();
			dataOraPanel.setLayout(new GridLayout(2,1));
			dataOraPanel.add(dataPanel);
			dataOraPanel.add(oraPanel);
		
			JPanel centro=new JPanel();
			centro.setLayout(new GridLayout(2,1));
			centro.add(datiPartitaStadio);
			centro.add(dataOraPanel);
			
			JLabel l=new JLabel("*campi obbligatori  ");
			l.setFont(new Font("Georgia", Font.PLAIN, 13));
			l.setHorizontalAlignment(JLabel.RIGHT);
			l.setForeground(Color.BLACK);
			
			
			JPanel bottoni= new JPanel();
			JButton buttonConferma=new JButton("Conferma");
			buttonConferma.setFont(new Font("Georgia", Font.PLAIN, 18));
			buttonConferma.setForeground(Color.BLACK);
			
			JButton buttonAnnulla=new JButton("Annulla");
			buttonAnnulla.setFont(new Font("Georgia", Font.PLAIN, 18));
			buttonAnnulla.setForeground(Color.BLACK);
			bottoni.add(buttonAnnulla);
			bottoni.add(buttonConferma);
			
			
			JPanel inserisciEve=new JPanel();
			inserisciEve.setLayout(new BorderLayout());
			TitledBorder a1=new TitledBorder(new EtchedBorder(), "Inserisci Evento Calcistico");
			a1.setTitleFont(new Font("Georgia", Font.ITALIC, 13));
			a1.setTitleColor(Color.BLACK);
			inserisciEve.setBorder(a1);
			
			inserisciEve.add(l,BorderLayout.NORTH);
			inserisciEve.add(centro,BorderLayout.CENTER);
			inserisciEve.add(bottoni,BorderLayout.SOUTH);
			
			pannello.add(inserisciEve, BorderLayout.CENTER);
			inserisciEvento.add(pannello,BorderLayout.CENTER);
					
			/**
			 * Il listener per il pulsante Annulla. 
			 * Se l'amministratore non vuole confermare i dati inseriti, selezionando il pulsante Annulla, 
			 * la finestra riguardante l'inserimento di un nuovo evento calcistico si chiude 
			 * ritornando alla schermate iniziale dell'amministratore.			 
			 *
			 */
			class AnnullaListener implements ActionListener {
				public void actionPerformed(ActionEvent e) {
					inserisciEvento.dispose();
					setVisible(true);
				}
			}
			
			/**
			 * Il listener per il pulsante Conferma. 
			 * Se l'amministratore vuole confermare i dati inseriti e procedere dunque con l'inserimento del nuovo evento calcistico, preme il tasto Conferma. 
			 * Il listener passa i dati inseriti al sistema e, se sono corretti, procede con l'inserimento dello stadio,
			 * presentando un messaggio di inserimento corretto dello stadio.
			 * Altrimenti, se i dati inseriti non sono corretti, viene visualizzato un messaggio di errore.
			 */
			class ConfermaListener implements ActionListener {
				public void actionPerformed (ActionEvent e) {
					try
					{
						//prelevo sia per l'item del menu a tendina della partita che per l'item del menu a tendina dello stadio, il numero 
						//corrispondente alla posizione nella lista e che equivale al numero-1 della lista di partite e della lista di stadi del sistema
						String riga = (String) partiteCombo.getSelectedItem();
						int i=riga.indexOf("-");
						String num=riga.substring(0,i);
						int numero =Integer.parseInt(num);					
						ArrayList<Partita> partite=new ArrayList<Partita>();
						
						partite=sistema.getPartite();
					
						String riga1 = (String) stadioCombo.getSelectedItem();
						int j=riga1.indexOf("-");
						String num1=riga1.substring(0,j);
						int numero1 =Integer.parseInt(num1);
						ArrayList<Stadio> stadi =new ArrayList<Stadio>();
						stadi=sistema.getStadi();
						
						setVisible(false); 
						sistema.aggiungiEventoCalcistico(partite.get(numero-1),
							stadi.get(numero1-1),
							annoField.getText(),meseField.getText(),
							giornoField.getText(),oraField.getText(),
							minutiField.getText());
						inserisciEvento.setVisible(false);
						def=new DefaultFrame(new JLabel("Evento Calcistico inserito"),
								"Evento calcistico inserito",450,new ImageIcon(imgURLOK),
								new AmministratoreFrame(sistema));
						def.setVisible(true);
					}
					catch (DatiNonValidiException e1)
					{
						inserisciEventoCalcistico.setVisible(false);
						def=new DefaultFrame(new JLabel(e1.getMessage()),
								"Errore",650,new ImageIcon(imgURLnonOK),
								inserisciEventoCalcistico);
						def.setVisible(true);
					} catch (CloneNotSupportedException e1) {
						return;
					}
				}
			}
			buttonConferma.addActionListener(new ConfermaListener());
			buttonAnnulla.addActionListener(new AnnullaListener());
			return inserisciEvento;
			}
		}

	/**
	 * Crea il form per visualizzare gli eventi calcistici ordinati a seconda della capienza degli stadi
	 */
	public JFrame eventiCalcisticiPerCapienza () throws CloneNotSupportedException, DatiNonValidiException{
		
		setVisible(false);
			
		JFrame visualizzaEventiCalcisticiPerCapi=new JFrame();
			
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		visualizzaEventiCalcisticiPerCapi.setLocation(new Point((dimension.width - 
			visualizzaEventiCalcisticiPerCapi.getSize().width) / 2-400, 
				(dimension.height - visualizzaEventiCalcisticiPerCapi.getSize().height) / 2 -200));
				
		visualizzaEventiCalcisticiPerCapi.setSize(800,400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		visualizzaEventiCalcisticiPerCapi.setResizable(false);
		visualizzaEventiCalcisticiPerCapi.setLayout(new BorderLayout());
		visualizzaEventiCalcisticiPerCapi.setUndecorated(true);
				
		JPanel bottoni=new JPanel();
		JButton indietro =new JButton("Indietro");
		indietro.setFont(new Font("Georgia", Font.PLAIN, 18));
		indietro.setHorizontalAlignment(JLabel.CENTER);
		indietro.setForeground(Color.BLACK);
		bottoni.add(indietro);
				
		JPanel pannello = new JPanel();
		pannello.setLayout(null);
		JScrollPane scroll = new JScrollPane(pannello,
			JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
			JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
				
		visualizzaEventiCalcisticiPerCapi.add(scroll);
		pannello.setLayout(new BorderLayout());
		pannello.add(bottoni,BorderLayout.NORTH);
				
		TitledBorder t=new TitledBorder(new EtchedBorder(), "Eventi calcistici");
		t.setTitleFont(new Font("Georgia", Font.ITALIC, 13));
		t.setTitleColor(Color.BLACK);
		pannello.setBorder(t);
				
		JPanel pannelloEventi=new JPanel();
		TitledBorder t2=new TitledBorder(new EtchedBorder(), "Eventi calcistici per capienza stadi");
		t2.setTitleFont(new Font("Georgia", Font.ITALIC, 13));
		t2.setTitleColor(Color.BLACK);
		pannelloEventi.setBorder(t2);
	
		ArrayList<EventoCalcistico> eventi = sistema.getEventiCalcisticiPerCapienza();
		pannelloEventi.setLayout(new GridLayout(eventi.size()+1,1));
		pannello.add(pannelloEventi,BorderLayout.CENTER);
				
		JPanel rigaTitoli=new JPanel();
		rigaTitoli.setLayout(new GridLayout(1,6));
		JLabel l1=new JLabel(" SquadraCasa ");
		JLabel l2=new JLabel(" SquadraOspite ");
		JLabel l3=new JLabel(" Stadio ");
		JLabel l4=new JLabel(" Capienza "); //poi verr‡ eliminta
		JLabel l6=new JLabel(" Data ");
		JLabel l0=new JLabel(" Ora ");	
		l1.setFont(new Font("Georgia", Font.PLAIN, 14));
		l1.setHorizontalAlignment(JLabel.CENTER);
		l1.setForeground(Color.BLACK);
		l2.setHorizontalAlignment(JLabel.CENTER);
		l2.setFont(new Font("Georgia", Font.PLAIN, 14));
		l2.setForeground(Color.BLACK);
		l3.setFont(new Font("Georgia", Font.PLAIN, 14));
		l3.setHorizontalAlignment(JLabel.CENTER);
		l3.setForeground(Color.BLACK);
		l4.setFont(new Font("Georgia", Font.PLAIN, 14));
		l4.setHorizontalAlignment(JLabel.CENTER);
		l4.setForeground(Color.BLACK);
		l6.setFont(new Font("Georgia", Font.PLAIN, 14));
		l6.setForeground(Color.BLACK);
		l6.setHorizontalAlignment(JLabel.CENTER);
		l0.setFont(new Font("Georgia", Font.PLAIN, 14));
		l0.setForeground(Color.BLACK);
		l0.setHorizontalAlignment(JLabel.CENTER);	
		rigaTitoli.add(l1);
		rigaTitoli.add(l2);
		rigaTitoli.add(l3);
		rigaTitoli.add(l4);
		rigaTitoli.add(l6);
		rigaTitoli.add(l0);	
		pannelloEventi.add(rigaTitoli);
				
		for(int i=eventi.size()-1; i>=0; i--){
			EventoCalcistico ev=eventi.get(i);

			JPanel rigaEvento=new JPanel();
			rigaEvento.setLayout(new GridLayout(1,6));
			SimpleDateFormat sdf =new SimpleDateFormat("dd/MM/yyyy");
			String a=sdf.format(ev.getDataInizioEventoCalcistico().getTime());
			SimpleDateFormat sdf1 =new SimpleDateFormat("HH:mm:ss");
			String a1=sdf1.format(ev.getDataInizioEventoCalcistico().getTime());
			JLabel l7=new JLabel(ev.getPartita().getSquadraCasa());
			JLabel l8=new JLabel(ev.getPartita().getSquadraOspite());
			JLabel l9=new JLabel(ev.getStadio().getNomeStadio());
			JLabel l10=new JLabel(""+ev.getStadio().getCapienza()); //poi verr‡ eliminta
			JLabel l12=new JLabel(a);
			JLabel l13=new JLabel(a1);
			l7.setFont(new Font("Georgia", Font.PLAIN, 14));
			l7.setForeground(Color.BLACK);
			l8.setFont(new Font("Georgia", Font.PLAIN, 14));
			l8.setForeground(Color.BLACK);
			l9.setFont(new Font("Georgia", Font.PLAIN, 14));
			l9.setForeground(Color.BLACK);
			l10.setFont(new Font("Georgia", Font.PLAIN, 14));
			l10.setForeground(Color.BLACK);
			l12.setFont(new Font("Georgia", Font.PLAIN, 14));
			l12.setForeground(Color.BLACK);
			l13.setFont(new Font("Georgia", Font.PLAIN, 14));
			l13.setForeground(Color.BLACK);
			l7.setHorizontalAlignment(JLabel.CENTER);
			l8.setHorizontalAlignment(JLabel.CENTER);
			l9.setHorizontalAlignment(JLabel.CENTER);
			l10.setHorizontalAlignment(JLabel.CENTER);
			l12.setHorizontalAlignment(JLabel.CENTER);
			l13.setHorizontalAlignment(JLabel.CENTER);
			rigaEvento.add(l7);
			rigaEvento.add(l8);
			rigaEvento.add(l9);
			rigaEvento.add(l10);
			rigaEvento.add(l12);
			rigaEvento.add(l13);
					
			pannelloEventi.add(rigaEvento);
		}
		
		/**
		 * Il listener del pulsante Indietro. 
		 * Selezionando il pulsante Indietro, la finestra riguardante la visualizzazione degli eventi calcistici per capienza stadi 
		 * si chiude, ritornando alla schermate iniziale dell'amministratore.			
		 *
		 */
		class IndietroListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				visualizzaEventiCalcisticiPerCapienza.dispose();
				setVisible(true);
			}
		}
		indietro.addActionListener(new IndietroListener());	
		return visualizzaEventiCalcisticiPerCapi;
	}
	
	/**
	 * Crea il form per la modifica della capienza degli stadi
	 */
	private JFrame modificaCapienza() throws DatiNonValidiException {
		
		if(sistema.getStadi().size()==0)	
			throw new DatiNonValidiException("Non sono presenti stadi");
		else {
			final JFrame modificaCapienza=new JFrame(); // crea il frame solo se sono presenti stadi nel sistema
			Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
			modificaCapienza.setLocation(new Point((dimension.width - 
					modificaCapienza.getSize().width) / 2-375, 
			(dimension.height - modificaCapienza.getSize().height) / 2 -275));
			modificaCapienza.setSize(750,550);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			modificaCapienza.setResizable(false);
			modificaCapienza.setUndecorated(true);
			
			final JPanel pannello=new JPanel();
			
			TitledBorder a2=new TitledBorder(new EtchedBorder(), "Modifica capienza stadio ");
			a2.setTitleFont(new Font("Georgia", Font.ITALIC, 13));
			a2.setTitleColor(Color.BLACK);
			pannello.setBorder(a2);
			pannello.setLayout(new GridLayout(6,1));
			modificaCapienza.add(pannello);
			
			final JPanel scegliStadio=new JPanel();
			final JPanel scegliSettore=new JPanel();
			final JPanel scegliAzione=new JPanel();
			final JPanel filePosti=new JPanel();
			final JPanel aggiungiSettore=new JPanel();
			
			JLabel label1 =new JLabel("Seleziona stadio: ");
			label1.setFont(new Font("Georgia", Font.PLAIN, 18));
			label1.setHorizontalAlignment(JLabel.CENTER);
			label1.setForeground(Color.BLACK);		
		
			final JComboBox<String> stadioCombo = new JComboBox<String>();
			stadioCombo.setFont(new Font("Georgia", Font.PLAIN, 18));
			ArrayList<Stadio> stadi=sistema.getStadi();
			for(int i=0; i<stadi.size(); i++){
				stadioCombo.addItem((i+1)+"-"+stadi.get(i).getNomeStadio()+"-"+
						stadi.get(i).getCitt‡Stadio()+"-"+stadi.get(i).getCapienza());
			}
			stadioCombo.setSelectedItem(null);
	
			TitledBorder a5=new TitledBorder(new EtchedBorder(), "Stadio "+stadi.get(0).getNomeStadio());
			a5.setTitleFont(new Font("Georgia", Font.ITALIC, 13));
			a5.setTitleColor(Color.BLACK);
			scegliStadio.setBorder(a5);
			scegliStadio.add(label1);
			scegliStadio.add(stadioCombo);
			
			JLabel label2 =new JLabel("Seleziona settore: ");
			label2.setFont(new Font("Georgia", Font.PLAIN, 18));
			label2.setHorizontalAlignment(JLabel.CENTER);
			label2.setForeground(Color.BLACK);		
			final JRadioButton sett1Button = new JRadioButton();
			final JRadioButton sett2Button = new JRadioButton();
			final JRadioButton sett3Button = new JRadioButton();
			final JRadioButton sett4Button = new JRadioButton();
			final JRadioButton sett5Button = new JRadioButton();
			final JRadioButton sett6Button = new JRadioButton();
			sett1Button.setFont(new Font("Georgia", Font.PLAIN, 15));
			sett1Button.setForeground(Color.BLACK);
			sett2Button.setFont(new Font("Georgia", Font.PLAIN, 15));
			sett2Button.setForeground(Color.BLACK);
			sett3Button.setFont(new Font("Georgia", Font.PLAIN, 15));
			sett3Button.setForeground(Color.BLACK);
			sett4Button.setFont(new Font("Georgia", Font.PLAIN, 15));
			sett4Button.setForeground(Color.BLACK);
			sett5Button.setFont(new Font("Georgia", Font.PLAIN, 15));
			sett5Button.setForeground(Color.BLACK);
			sett6Button.setFont(new Font("Georgia", Font.PLAIN, 15));
			sett6Button.setForeground(Color.BLACK);
			scegliSettore.add(label2);
			scegliSettore.add(sett1Button);
			scegliSettore.add(sett2Button);
			scegliSettore.add(sett3Button);
			scegliSettore.add(sett4Button);
			scegliSettore.add(sett5Button);
			scegliSettore.add(sett6Button);
			sett1Button.setVisible(false);
			sett2Button.setVisible(false);
			sett3Button.setVisible(false);
			sett4Button.setVisible(false);
			sett5Button.setVisible(false);
			sett6Button.setVisible(false);
			scegliSettore.setVisible(false);
			
			JLabel scegli =new JLabel("Scegli");
			scegli.setFont(new Font("Georgia", Font.PLAIN, 18));
			scegli.setForeground(Color.BLACK);
			final JRadioButton eliminaButton = new JRadioButton("Elimina");
			final JRadioButton aumentaButton = new JRadioButton("Aumenta capienza");
			JRadioButton diminuisciButton = new JRadioButton("Riduci capienza");
			eliminaButton.setFont(new Font("Georgia", Font.PLAIN, 15));
			eliminaButton.setForeground(Color.BLACK);
			aumentaButton.setFont(new Font("Georgia", Font.PLAIN, 15));
			aumentaButton.setForeground(Color.BLACK);
			diminuisciButton.setFont(new Font("Georgia", Font.PLAIN, 15));
			diminuisciButton.setForeground(Color.BLACK);
			final ButtonGroup group = new ButtonGroup();
			group.add(eliminaButton);
			group.add(aumentaButton);
			group.add(diminuisciButton);
			scegliAzione.add(scegli);
			scegliAzione.add(eliminaButton);
			scegliAzione.add(aumentaButton);
			scegliAzione.add(diminuisciButton);
			scegliAzione.setVisible(false);
			
			final JLabel file=new JLabel();
			final JTextField fileField=new JTextField(3);
			final JLabel posti=new JLabel();
			final JTextField postiField = new JTextField(3);
			JLabel campi=new JLabel("                                       "
					+ "                                                                                           "
					+ "                                                                  "
					+ "     *campi obbligatori");
			file.setFont(new Font("Georgia", Font.PLAIN, 15));
			file.setForeground(Color.BLACK);
			fileField.setFont(new Font("Georgia", Font.PLAIN, 18));
			fileField.setForeground(Color.BLACK);
			posti.setFont(new Font("Georgia", Font.PLAIN, 15));
			posti.setForeground(Color.BLACK);
			postiField.setFont(new Font("Georgia", Font.PLAIN, 18));
			postiField.setForeground(Color.BLACK);
			campi.setFont(new Font("Georgia", Font.PLAIN, 12));
			campi.setForeground(Color.BLACK);
			
			filePosti.add(campi);
			filePosti.add(file);
			filePosti.add(fileField);
			filePosti.add(posti);
			filePosti.add(postiField);
			filePosti.setVisible(false);
			
			JButton buttonAggiungiSettore=new JButton("Aggiungi Settore");
			buttonAggiungiSettore.setFont(new Font("Georgia", Font.PLAIN, 18));
			buttonAggiungiSettore.setForeground(Color.BLACK);
			aggiungiSettore.add(buttonAggiungiSettore);
			aggiungiSettore.setVisible(false);
			
			JPanel bottoni= new JPanel();
			final JButton buttonConferma=new JButton("Conferma");
			buttonConferma.setFont(new Font("Georgia", Font.PLAIN, 18));
			buttonConferma.setForeground(Color.BLACK);
			JButton buttonIndietro=new JButton("Indietro");
			buttonIndietro.setFont(new Font("Georgia", Font.PLAIN, 18));
			buttonIndietro.setForeground(Color.BLACK);
			bottoni.add(buttonIndietro);
			bottoni.add(buttonConferma);
			buttonConferma.setVisible(false);
			
			pannello.add(scegliStadio);
			pannello.add(scegliSettore);
			pannello.add(scegliAzione);
			pannello.add(filePosti);	
			pannello.add(aggiungiSettore);
			pannello.add(bottoni);
		
			/**
			 * Il listener della combo box stadioCombo presente nel panel della scelta dello stadio.
			 * Selezionando un item relativo ad uno stadio viene visualizzato solo ed esclusivamente il panel relativo alla scelta del settore.
			 * Se lo stadio presenta un numero di settore minore a sei, allora verr‡ visualizzato il panel contenente il button che permette l'aggiunta di altri settori.
			 * Il titled Border riguardante il panel contenente la combo box viene modificato a seconda dello stadio selezionato cosÏ come anche il titled border del panel relativo alla scelta dei settori.
			 * Verranno visualizzati tanti radio button quanti sono i settori presenti nello stadio settando vicino i relativi nomi dati.
			 */
			
			class stadioComboListener implements ActionListener {
				public void actionPerformed (ActionEvent e) {
					scegliSettore.setVisible(true);
					scegliAzione.setVisible(false);
					filePosti.setVisible(false);
					buttonConferma.setVisible(false);
					//consideriamo la posizione dello stadio all'interno della lista della combo box che equivarr‡ allo stadio della stessa posizione-1 all'interno della lista di stadi del sistema
					String riga11 = (String) stadioCombo.getSelectedItem();
					int j=riga11.indexOf("-");
					String num1=riga11.substring(0,j);
					int numero1 =Integer.parseInt(num1);
					
					stadio=sistema.getStadi().get(numero1-1);
												
					if(stadio.getSettori().size()==6)
						aggiungiSettore.setVisible(false);
					else
						aggiungiSettore.setVisible(true);
						
					TitledBorder a5=new TitledBorder(new EtchedBorder(), "Stadio "+stadio.getNomeStadio());
					a5.setTitleFont(new Font("Georgia", Font.ITALIC, 13));
					a5.setTitleColor(Color.BLACK);
					scegliStadio.setBorder(a5);

					if(stadio.getSettori().size()==1){
						ButtonGroup group = new ButtonGroup();
						sett1Button.setText(stadio.getSettori().get(0).getNomeSettore());
						
						group.add(sett1Button);
						group.clearSelection(); // fa in modo che nessun radio button sia selezionato inizialmente 
						sett1Button.setVisible(true);
						sett2Button.setVisible(false);
						sett3Button.setVisible(false);
						sett4Button.setVisible(false);
						sett5Button.setVisible(false);
						sett6Button.setVisible(false);
					}
					else if(stadio.getSettori().size()==2){
						ButtonGroup group = new ButtonGroup();
						sett1Button.setText(stadio.getSettori().get(0).getNomeSettore());
						sett2Button.setText(stadio.getSettori().get(1).getNomeSettore());
						group.add(sett1Button);
						group.add(sett2Button);
						sett1Button.setVisible(true);
						sett2Button.setVisible(true);
						sett3Button.setVisible(false);
						sett4Button.setVisible(false);
						sett5Button.setVisible(false);
						sett6Button.setVisible(false);
						group.clearSelection();
					}
					else if(stadio.getSettori().size()==3){
						ButtonGroup group = new ButtonGroup();
						sett1Button.setText(stadio.getSettori().get(0).getNomeSettore());
						sett2Button.setText(stadio.getSettori().get(1).getNomeSettore());
						sett3Button.setText(stadio.getSettori().get(2).getNomeSettore());
						group.add(sett1Button);
						group.add(sett2Button);
						group.add(sett3Button);
						sett1Button.setVisible(true);
						sett2Button.setVisible(true);
						sett3Button.setVisible(true);
						sett4Button.setVisible(false);
						sett5Button.setVisible(false);
						sett6Button.setVisible(false);
						group.clearSelection();
					}
					else if(stadio.getSettori().size()==4){
						ButtonGroup group = new ButtonGroup();
						sett1Button.setText(stadio.getSettori().get(0).getNomeSettore());
						sett2Button.setText(stadio.getSettori().get(1).getNomeSettore());
						sett3Button.setText(stadio.getSettori().get(2).getNomeSettore());
						sett4Button.setText(stadio.getSettori().get(3).getNomeSettore());
						group.add(sett1Button);
						group.add(sett2Button);
						group.add(sett3Button);
						group.add(sett4Button);
						sett1Button.setVisible(true);
						sett2Button.setVisible(true);
						sett3Button.setVisible(true);
						sett4Button.setVisible(true);
						sett5Button.setVisible(false);
						sett6Button.setVisible(false);
						group.clearSelection();
					}
					else if(stadio.getSettori().size()==5){
						ButtonGroup group = new ButtonGroup();
						sett1Button.setText(stadio.getSettori().get(0).getNomeSettore());
						sett2Button.setText(stadio.getSettori().get(1).getNomeSettore());
						sett3Button.setText(stadio.getSettori().get(2).getNomeSettore());
						sett4Button.setText(stadio.getSettori().get(3).getNomeSettore());
						sett5Button.setText(stadio.getSettori().get(4).getNomeSettore());
						group.add(sett1Button);
						group.add(sett2Button);
						group.add(sett3Button);
						group.add(sett4Button);
						group.add(sett5Button);
						sett1Button.setVisible(true);
						sett2Button.setVisible(true);
						sett3Button.setVisible(true);
						sett4Button.setVisible(true);
						sett5Button.setVisible(true);
						sett6Button.setVisible(false);
						group.clearSelection();
					}
					else if(stadio.getSettori().size()==6){
						ButtonGroup group = new ButtonGroup();
						sett1Button.setText(stadio.getSettori().get(0).getNomeSettore());
						sett2Button.setText(stadio.getSettori().get(1).getNomeSettore());
						sett3Button.setText(stadio.getSettori().get(2).getNomeSettore());
						sett4Button.setText(stadio.getSettori().get(3).getNomeSettore());
						sett5Button.setText(stadio.getSettori().get(4).getNomeSettore());
						sett6Button.setText(stadio.getSettori().get(5).getNomeSettore());
						group.add(sett1Button);
						group.add(sett2Button);
						group.add(sett3Button);
						group.add(sett4Button);
						group.add(sett5Button);
						group.add(sett6Button);
						sett1Button.setVisible(true);
						sett2Button.setVisible(true);
						sett3Button.setVisible(true);
						sett4Button.setVisible(true);
						sett5Button.setVisible(true);
						sett6Button.setVisible(true);
						group.clearSelection();
					}
					TitledBorder a1=new TitledBorder(new EtchedBorder(), "Settori dello stadio "+stadio.getNomeStadio());
					a1.setTitleFont(new Font("Georgia", Font.ITALIC, 13));
					a1.setTitleColor(Color.BLACK);
					scegliSettore.setBorder(a1);		
					scegliSettore.setBorder(a1);
					scegliSettore.setVisible(true);
					pannello.setVisible(true);
				}
			}
			
			/**
			 * Il listener dei radio button corrispondenti ai settori presenti nel panel della scelta del settore.
			 * Se selezionato un settore il titled border del panel riguardante la scelta dell'azione da fare su quel settore viene settato in base al nome del settore, al numero di file e al numero di posti per fila.
			 * Verranno inoltre visualizzati tre radio button. In particolare il radio button "Elimina" potr‡ essere visualizzato solo se i settori presenti nello stadio siano almeno due.
			 */
			class SelezionaSettoreListener implements ActionListener {
				public void actionPerformed (ActionEvent e) {
						group.clearSelection();
					if (sett1Button.isSelected()){	
						TitledBorder a5=new TitledBorder(new EtchedBorder(), 
							"Operazione sul settore "+stadio.getSettori().get(0).getNomeSettore()+
							"[File="+stadio.getSettori().get(0).getNumeroFile()+"]"+"[Posti per fila="
									+stadio.getSettori().get(0).getNumeroPostiFila()+"]");
						a5.setTitleFont(new Font("Georgia", Font.ITALIC, 13));
						a5.setTitleColor(Color.BLACK);	
						scegliAzione.setBorder(a5);
					}
					else if (sett2Button.isSelected()){		
						TitledBorder a5=new TitledBorder(new EtchedBorder(), 
								"Operazione sul settore "+stadio.getSettori().get(1).getNomeSettore()+
								"[File="+stadio.getSettori().get(1).getNumeroFile()+"]"+"[Posti per fila="
										+stadio.getSettori().get(1).getNumeroPostiFila()+"]");
						a5.setTitleFont(new Font("Georgia", Font.ITALIC, 13));
						a5.setTitleColor(Color.BLACK);
						scegliAzione.setBorder(a5);
					}
					else if (sett3Button.isSelected()){		
						TitledBorder a5=new TitledBorder(new EtchedBorder(), 
								"Operazione sul settore "+stadio.getSettori().get(2).getNomeSettore()+
								"[File="+stadio.getSettori().get(2).getNumeroFile()+"]"+"[Posti per fila="
										+stadio.getSettori().get(2).getNumeroPostiFila()+"]");
						a5.setTitleFont(new Font("Georgia", Font.ITALIC, 13));
						a5.setTitleColor(Color.BLACK);
						scegliAzione.setBorder(a5);
					}
					else if (sett4Button.isSelected()){
						TitledBorder a5=new TitledBorder(new EtchedBorder(), 
								"Operazione sul settore "+stadio.getSettori().get(3).getNomeSettore()+
								"[File="+stadio.getSettori().get(3).getNumeroFile()+"]"+"[Posti per fila="
										+stadio.getSettori().get(3).getNumeroPostiFila()+"]");
						a5.setTitleFont(new Font("Georgia", Font.ITALIC, 13));
						a5.setTitleColor(Color.BLACK);
						scegliAzione.setBorder(a5);
					}
					else if (sett5Button.isSelected()){
						TitledBorder a5=new TitledBorder(new EtchedBorder(), 
								"Operazione sul settore "+stadio.getSettori().get(4).getNomeSettore()+
								"[File="+stadio.getSettori().get(4).getNumeroFile()+"]"+"[Posti per fila="
										+stadio.getSettori().get(4).getNumeroPostiFila()+"]");
						a5.setTitleFont(new Font("Georgia", Font.ITALIC, 13));
						a5.setTitleColor(Color.BLACK);
						scegliAzione.setBorder(a5);
					}
					else if (sett6Button.isSelected()){
						TitledBorder a5=new TitledBorder(new EtchedBorder(), 
								"Operazione sul settore "+stadio.getSettori().get(5).getNomeSettore()+
								"[File="+stadio.getSettori().get(5).getNumeroFile()+"]"+"[Posti per fila="
										+stadio.getSettori().get(5).getNumeroPostiFila()+"]");
						a5.setTitleFont(new Font("Georgia", Font.ITALIC, 13));
						a5.setTitleColor(Color.BLACK);
						scegliAzione.setBorder(a5);
					}
					if(stadio.getSettori().size()==1)
						eliminaButton.setVisible(false);
					else
						eliminaButton.setVisible(true);
					scegliAzione.setVisible(true);
					filePosti.setVisible(false);
				}
			}
			
			/**
			 * Il listener dei radio button presenti nel panel della scelta da fare su quel settore.
			 * Se viene selezionato il radio button "Elimina", viene visualizzato il pulsante "Conferma"-
			 * Se viene selezionato il radio button "Aumenta Capienza" o "Riduci Capienza", 
			 * viene visualizzato il pulsante "Conferma" e il panel riguardante la modifica del numero di posti e di file 
			 * (il testo delle label sar‡ settato a seconda del radio button selezionato).
			 *
			 */
			class SelezionaSceltaListener implements ActionListener {
				public void actionPerformed (ActionEvent e) {
					if (eliminaButton.isSelected()){
						filePosti.setVisible(false);
						buttonConferma.setVisible(true);
					}
					else{
						if (aumentaButton.isSelected()){
							buttonConferma.setVisible(true);
							file.setText("File da aggiungere*");
							posti.setText("Posti da aggiungere in una fila*");
						}
						else{
							file.setText("File da rimuovere*");
							posti.setText("Posti da rimuovere in una fila*");
							buttonConferma.setVisible(true);
						}	
						filePosti.setVisible(true);				
					}
				}
			}
			
			/**
			 * Il listener del pulsante Indietro.
			 * Se l'amministratore non vuole confermare i dati inseriti o semplicemente non vuole effettuare alcuna modifica,
			 * selezionando il pulsante indietro chiude la schermata riguardante la modifica della capienza degli stadi 
			 * aprendo la schermata iniziale dell'amministatore.
			 *
			 */
			class IndietroListener implements ActionListener {
				public void actionPerformed(ActionEvent e) {
					modificaCapienza.dispose();
					setVisible(true);
				}
			}
		
			stadioCombo.addActionListener(new stadioComboListener());
			sett1Button.addActionListener(new SelezionaSettoreListener());
			sett2Button.addActionListener(new SelezionaSettoreListener());
			sett3Button.addActionListener(new SelezionaSettoreListener());
			sett4Button.addActionListener(new SelezionaSettoreListener());
			sett5Button.addActionListener(new SelezionaSettoreListener());
			sett6Button.addActionListener(new SelezionaSettoreListener());
			eliminaButton.addActionListener(new SelezionaSceltaListener());
			aumentaButton.addActionListener(new SelezionaSceltaListener());
			diminuisciButton.addActionListener(new SelezionaSceltaListener());
			
			/**
			 * Il listener del pulsante Conferma.
			 * Se l'amministratore ha selezionato il radio button "Elimina", selezionando il pulsante Conferma rimuove 
			 * il relativo settore e viene visualizzato un messaggio che conferma l'eliminazione del settore.
			 * Altrimenti se l'amministartore ha selezione "Aumenta Capienza" o "Riduci Capienza", selezionando il pulsante Conferma modifica
			 * la capienza del relativo settore e viene visualizzato un messaggio che conferma la modifica del settore.
			 */
			class ConfermaListener implements ActionListener {
				public void actionPerformed (ActionEvent e) {
					if (eliminaButton.isSelected()){
						if (sett1Button.isSelected()){
							try {
								sistema.rimuoviSettoreStadio(stadio,stadio.getSettori().get(0));
							} catch (DatiNonValidiException e1) {
								return;
							}
							System.out.println("eliminato il 1");
						}
						else if (sett2Button.isSelected()){
							try {
								sistema.rimuoviSettoreStadio(stadio,stadio.getSettori().get(1));
							} catch (DatiNonValidiException e1) {
								return;
							}
							System.out.println("eliminato il 2");
						}
						else if (sett3Button.isSelected()){
							try {
								sistema.rimuoviSettoreStadio(stadio,stadio.getSettori().get(2));
							} catch (DatiNonValidiException e1) {
								return;
							}
							System.out.println("eliminato il 3");
						}
						else if (sett4Button.isSelected()){
							try {
								sistema.rimuoviSettoreStadio(stadio,stadio.getSettori().get(3));
							} catch (DatiNonValidiException e1) {
								return;
							}
							System.out.println("eliminato il 4");
						}
						else if (sett5Button.isSelected()){
							try {
								sistema.rimuoviSettoreStadio(stadio,stadio.getSettori().get(4));
							} catch (DatiNonValidiException e1) {
								return;
							}
							System.out.println("eliminato il 5");
						}
						else if (sett6Button.isSelected()){
							try {
								sistema.rimuoviSettoreStadio(stadio,stadio.getSettori().get(5));
							} catch (DatiNonValidiException e1) {
								return;
							}
							System.out.println("eliminato il 6");
						}
						modificaCapienza.dispose();
						try {
							def=new DefaultFrame(new JLabel("Il settore Ë stato eliminato"),
								"Settore eliminato",340,new ImageIcon(imgURLOK),
								modificaCapienza());
							def.setVisible(true);
						} catch (DatiNonValidiException e1) {
							return ;
						}
					}		
					else {
						String n_file=fileField.getText();
						String n_posti=postiField.getText();
						try{
							if (aumentaButton.isSelected()){						
								if (sett1Button.isSelected()){
									sistema.addFileSettore(stadio,stadio.getSettori().get(0),n_file);
									sistema.addPostiPerFilaSettore(stadio,stadio.getSettori().get(0),n_posti);
								}
								else if (sett2Button.isSelected()){
									sistema.addFileSettore(stadio,stadio.getSettori().get(1),n_file);
									sistema.addPostiPerFilaSettore(stadio,stadio.getSettori().get(1),n_posti);
								}
								else if (sett3Button.isSelected()){
									sistema.addFileSettore(stadio,stadio.getSettori().get(2),n_file);
									sistema.addPostiPerFilaSettore(stadio,stadio.getSettori().get(2),n_posti);
								}
								else if (sett4Button.isSelected()){
									sistema.addFileSettore(stadio,stadio.getSettori().get(3),n_file);
									sistema.addPostiPerFilaSettore(stadio,stadio.getSettori().get(3),n_posti);
								}
								else if (sett5Button.isSelected()){
									sistema.addFileSettore(stadio,stadio.getSettori().get(4),n_file);
									sistema.addPostiPerFilaSettore(stadio,stadio.getSettori().get(4),n_posti);
								}
								else if (sett6Button.isSelected()){
									sistema.addFileSettore(stadio,stadio.getSettori().get(5),n_file);
									sistema.addPostiPerFilaSettore(stadio,stadio.getSettori().get(5),n_posti);
								}
								modificaCapienza.dispose();
								try {
									def=new DefaultFrame(new JLabel("Il settore Ë "
										+ "stato modificato"),
										"Settore modificato",340,
										new ImageIcon(imgURLOK),
										modificaCapienza());
									def.setVisible(true);
								} catch (DatiNonValidiException e1) {
									return ;
								}	
							}
							else{
								if (sett1Button.isSelected()){
									sistema.diminuisciFileSettore(stadio,stadio.getSettori().get(0),n_file);
									sistema.diminiusciPostiPerFilaSettore(stadio,stadio.getSettori().get(0),n_posti);
								}
								else if (sett2Button.isSelected()){
									sistema.diminuisciFileSettore(stadio,stadio.getSettori().get(1),n_file);
									sistema.diminiusciPostiPerFilaSettore(stadio,stadio.getSettori().get(1),n_posti);
								}
								else if (sett3Button.isSelected()){
									sistema.diminuisciFileSettore(stadio,stadio.getSettori().get(2),n_file);
									sistema.diminiusciPostiPerFilaSettore(stadio,stadio.getSettori().get(2),n_posti);
								}
								else if (sett4Button.isSelected()){
									sistema.diminuisciFileSettore(stadio,stadio.getSettori().get(3),n_file);
									sistema.diminiusciPostiPerFilaSettore(stadio,stadio.getSettori().get(3),n_posti);
								}
								else if (sett5Button.isSelected()){
									sistema.diminuisciFileSettore(stadio,stadio.getSettori().get(4),n_file);
									sistema.diminiusciPostiPerFilaSettore(stadio,stadio.getSettori().get(4),n_posti);
								}
								else if (sett6Button.isSelected()){
									sistema.diminuisciFileSettore(stadio,stadio.getSettori().get(5),n_file);
									sistema.diminiusciPostiPerFilaSettore(stadio,stadio.getSettori().get(5),n_posti);
								}
								modificaCapienza.dispose();
								def=new DefaultFrame(new JLabel("Il settore Ë "
									+ "stato modificato"),
									"Settore modificato",340,
									new ImageIcon(imgURLOK),
									modificaCapienza());
								def.setVisible(true);
							}
						}
						catch (DatiNonValidiException e1) {
							modificaCapienza.setVisible(false);;
							def=new DefaultFrame(new JLabel(e1.getMessage()),
									"Settore eliminato",340,new ImageIcon(imgURLnonOK),
									modificaCapienza);
							def.setVisible(true);
							
						}
					}
				}	
			}
			
			/**
			 * Il listener del pulsante Aggiungi Settore.
			 * Se selezionato chiude la schermata riguardante la modifica della capienza dello stadio, 
			 * aprendo la schermata relativa all'inserimento di un settore.
			 */
			class AggiungiSettoreListener implements ActionListener {
				public void actionPerformed (ActionEvent e) {
						modificaCapienza.setVisible(false);
						inserisciSettore(null,null,null).setVisible(true);
				}
			}			
						
			buttonAggiungiSettore.addActionListener(new AggiungiSettoreListener());
			buttonConferma.addActionListener(new ConfermaListener());
			buttonIndietro.addActionListener(new IndietroListener());
			return modificaCapienza;
		}
	}
	
	/**
	 * Crea il form per l'inserimento di un settore.
	 */
	private JFrame inserisciSettore(final ArrayList<Settore> sett,final JButton agg,final JLabel lab) {
		final JFrame inserisciSettore=new JFrame();
		
		if(sett==null && agg==null && lab==null)
			setVisible(false);
		else
			inserisciStadio.setVisible(false);
		
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		inserisciSettore.setLocation(new Point((dimension.width - 
				inserisciSettore.getSize().width) / 2-160, 
		(dimension.height - inserisciSettore.getSize().height) / 2 -120));
		
		inserisciSettore.setSize(320, 240);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		inserisciSettore.setResizable(false);
		inserisciSettore.setUndecorated(true);
		inserisciSettore.setLayout(new BorderLayout());
		
		JPanel pannello=new JPanel();
		pannello.setLayout(new BorderLayout());
		
		JLabel label =new JLabel("Inserisci i dati richiesti");
		label.setFont(new Font("Georgia", Font.PLAIN, 18));
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setForeground(Color.BLACK);
		
		pannello.add(label,BorderLayout.NORTH);
		
		JPanel dati = new JPanel();
		JLabel nomeSettore = new JLabel("nomeSettore*");
		JLabel numeroFile = new JLabel("numeroFile*");
		JLabel numeroPostiFila = new JLabel("numeroPostiFila*");
		final JTextField nomeSettoreField = new JTextField(8);
		final JTextField numeroFileField = new JTextField(8);
		final JTextField numeroPostiFilaField = new JTextField(8);
		nomeSettore.setFont(new Font("Georgia", Font.PLAIN, 18));
		numeroFile.setFont(new Font("Georgia", Font.PLAIN, 18));
		numeroPostiFila.setFont(new Font("Georgia", Font.PLAIN, 18));
		nomeSettoreField.setFont(new Font("Georgia", Font.PLAIN, 18));
		numeroFileField.setFont(new Font("Georgia", Font.PLAIN, 18));
		numeroPostiFilaField.setFont(new Font("Georgia", Font.PLAIN, 18));	
		nomeSettore.setHorizontalAlignment(JLabel.CENTER);
		numeroFile.setHorizontalAlignment(JLabel.CENTER);
		numeroPostiFila.setHorizontalAlignment(JLabel.CENTER);
		nomeSettoreField.setHorizontalAlignment(JLabel.CENTER);
		numeroFileField.setHorizontalAlignment(JLabel.CENTER);
		numeroPostiFilaField.setHorizontalAlignment(JLabel.CENTER);
		nomeSettore.setForeground(Color.BLACK);
		numeroFile.setForeground(Color.BLACK);
		numeroPostiFila.setForeground(Color.BLACK);
		nomeSettoreField.setForeground(Color.BLACK);
		numeroFileField.setForeground(Color.BLACK);
		numeroPostiFilaField.setForeground(Color.BLACK);
		dati.setLayout(new GridLayout(3, 2));
		dati.add(nomeSettore);
		dati.add(nomeSettoreField);
		dati.add(numeroFile);
		dati.add(numeroFileField);
		dati.add(numeroPostiFila);
		dati.add(numeroPostiFilaField);
			
		
		JLabel l=new JLabel("*campi obbligatori  ");
		l.setFont(new Font("Georgia", Font.PLAIN, 13));
		l.setHorizontalAlignment(JLabel.RIGHT);
		l.setForeground(Color.BLACK);
		
		JPanel bottoni= new JPanel();
		JButton buttonConferma=new JButton("Conferma");
		buttonConferma.setFont(new Font("Georgia", Font.PLAIN, 18));
		buttonConferma.setForeground(Color.BLACK);
		JButton buttonAnnulla=new JButton("Annulla");
		buttonAnnulla.setFont(new Font("Georgia", Font.PLAIN, 18));
		buttonAnnulla.setForeground(Color.BLACK);
		bottoni.add(buttonAnnulla);
		bottoni.add(buttonConferma);
		
		JPanel inserisciSetto=new JPanel();
		inserisciSetto.setLayout(new BorderLayout());
		TitledBorder a=new TitledBorder(new EtchedBorder(), "Inserisci Settore");
		a.setTitleFont(new Font("Georgia", Font.ITALIC, 13));
		a.setTitleColor(Color.BLACK);
		inserisciSetto.setBorder(a);
		inserisciSetto.add(l,BorderLayout.NORTH);
		inserisciSetto.add(dati,BorderLayout.CENTER);
		inserisciSetto.add(bottoni,BorderLayout.SOUTH);

		pannello.add(inserisciSetto, BorderLayout.CENTER);
		inserisciSettore.add(pannello,BorderLayout.CENTER);
		
		/**
		 * Il listener per il pulsante Annulla. 
		 * Se l'amministratore non vuole confermare i dati inseriti, selezionando il pulsante Annulla, 
		 * la finestra riguardante l'inserimento di un nuovo settore si chiude 
		 * ritornando alla schermata precedente che potrebbe essere quella riguardante la modifica della capienza degli stadi 
		 * o quella dell'inserimento di uno stadio.		
		 *
		 */
		class AnnullaListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				if(sett==null && agg==null && lab==null){
					inserisciSettore.dispose();
					modificaCapienza.setVisible(true);
				}
				else{
					inserisciSettore.dispose();
					inserisciStadio.setVisible(true);		
				}		
			}
		}
	
		/**
		 * Il listener per il pulsante Conferma. 
		 * Se l'amministratore vuole confermare i dati inseriti e procedere dunque con l'inserimento del nuovo settore, preme il tasto Conferma. 
		 * Il listener passa i dati inseriti al sistema e, se sono corretti, procede con l'inserimento del nuovo settore 
		 * (a seconda della schermata che ha permesso la visualizzazione della schermata dell'inserimento del nuovo settore),
		 * presentando un messaggio di inserimento corretto del settore.
		 * Altrimenti, se i dati inseriti non sono corretti, viene visualizzato un messaggio di errore.
		 *
		 */
		class ConfermaListener implements ActionListener {
			public void actionPerformed (ActionEvent e) {					
				if(sett==null && agg==null && lab==null){
					try {
						Settore s1=new Settore(
								nomeSettoreField.getText(),
									numeroFileField.getText(),
										numeroPostiFilaField.getText());
						stadio.addSettore(s1);
						inserisciSettore.setVisible(false);
						def=new DefaultFrame(new JLabel("Il settore Ë stato "
								+ "inserito correttamente"),
								"Settore inserito",390,
								new ImageIcon(imgURLOK),
							    modificaCapienza());
						def.setVisible(true);
					}
					catch (DatiNonValidiException e1){
						inserisciSettore.setVisible(false);
						def=new DefaultFrame(new JLabel(e1.getMessage()),
							"Settore non inserito",390,
							new ImageIcon(imgURLnonOK),
						    inserisciSettore(null,null,null));
						def.setVisible(true);
					}
				}				
				else{
					try{
						Settore s=new Settore(
							nomeSettoreField.getText(),
								numeroFileField.getText(),
									numeroPostiFilaField.getText());
						sett.add(s);
						if(sett.size()==5)
							agg.setVisible(false);
							inserisciSettore.dispose();
							lab.setText("Numero Settori:"+(sett.size()+1));
						def=new DefaultFrame(new JLabel("Il settore Ë stato "
							+ "inserito correttamente"),
							"Settore inserito",390,
							new ImageIcon(imgURLOK),
							inserisciStadio);
						def.setVisible(true);
					}
					catch (DatiNonValidiException e1)
					{
						inserisciSettore.setVisible(false);
						def=new DefaultFrame(new JLabel(e1.getMessage()),
							"Settore non inserito",390,
							new ImageIcon(imgURLnonOK),
						    inserisciSettore(sett,agg,lab));
						def.setVisible(true);
					}
				}	
			}			
		}
		buttonConferma.addActionListener(new ConfermaListener());
		buttonAnnulla.addActionListener(new AnnullaListener());
		return inserisciSettore;
	}
	
	/**
	 * Crea il form per la modifica del prezzo degli stadi
	 */
	public JFrame modificaPrezzoStadio() throws DatiNonValidiException{
		
		if(sistema.getStadi().size()==0)
			throw new DatiNonValidiException("Non sono presenti stadi");
		else {
			JFrame modPrezzoStadio=new JFrame();		
			
			Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
			modPrezzoStadio.setLocation(new Point((dimension.width - 
					modPrezzoStadio.getSize().width) / 2-200, 
			(dimension.height - modPrezzoStadio.getSize().height) / 2 -118));
			
			modPrezzoStadio.setSize(400,236);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			modPrezzoStadio.setResizable(false);
			modPrezzoStadio.setLayout(new BorderLayout());
			modPrezzoStadio.setUndecorated(true);
			
			JLabel scegli =new JLabel("Scegli Stadio");
			scegli.setFont(new Font("Georgia", Font.PLAIN, 18));
			scegli.setHorizontalAlignment(JLabel.CENTER);
			scegli.setForeground(Color.BLACK);
			
			final JComboBox<String> stadioCombo = new JComboBox<String>();
			stadioCombo.setFont(new Font("Georgia", Font.PLAIN, 18));
			ArrayList<Stadio> stadi=sistema.getStadi();
			for(int i=0; i<stadi.size(); i++){
				stadioCombo.addItem((i+1)+"-"+stadi.get(i).getNomeStadio()+"-"+
						stadi.get(i).getCitt‡Stadio()+"-"+stadi.get(i).getCapienza());
			}
			stadioCombo.setSelectedItem(null);
			
			JPanel pannello=new JPanel();
			pannello.setLayout(new BorderLayout());
			TitledBorder a9=new TitledBorder(new EtchedBorder(),"Modifica prezzo stadio");
			a9.setTitleFont(new Font("Georgia", Font.ITALIC, 13));
			a9.setTitleColor(Color.BLACK);
			pannello.setBorder(a9);
			
			JPanel scegliStadio=new JPanel();
			scegliStadio.add(scegli);
			scegliStadio.add(stadioCombo);
			
			TitledBorder a5=new TitledBorder(new EtchedBorder(),"Seleziona stadio");
			a5.setTitleFont(new Font("Georgia", Font.ITALIC, 13));
			a5.setTitleColor(Color.BLACK);
			scegliStadio.setBorder(a5);
			pannello.add(scegliStadio,BorderLayout.NORTH);
			
			JPanel bottoni=new JPanel();
			JButton annulla =new JButton("Annulla");
			annulla.setFont(new Font("Georgia", Font.PLAIN, 18));
			annulla.setHorizontalAlignment(JLabel.CENTER);
			annulla.setForeground(Color.BLACK);
			final JButton conferma =new JButton("Conferma");
			conferma.setFont(new Font("Georgia", Font.PLAIN, 18));
			conferma.setHorizontalAlignment(JLabel.CENTER);
			conferma.setForeground(Color.BLACK);
			bottoni.add(annulla);
			bottoni.add(conferma);
			conferma.setVisible(false);
			
			final JLabel prezzo=new JLabel();
			JLabel nuovoPrezzo=new JLabel("Nuovo Prezzo*");
			JLabel campi=new JLabel("                                   "
					+ "                                         "
					+ "    *campi obbligatori");
			final JTextField prezzoField=new JTextField(5);
			prezzo.setFont(new Font("Georgia", Font.PLAIN, 18));
			prezzo.setHorizontalAlignment(JLabel.CENTER);
			prezzo.setForeground(Color.BLACK);
			nuovoPrezzo.setFont(new Font("Georgia", Font.PLAIN, 18));
			nuovoPrezzo.setHorizontalAlignment(JLabel.CENTER);
			nuovoPrezzo.setForeground(Color.BLACK);
			prezzoField.setFont(new Font("Georgia", Font.PLAIN, 18));
			prezzoField.setHorizontalAlignment(JLabel.CENTER);
			prezzoField.setForeground(Color.BLACK);
			campi.setForeground(Color.BLACK);
			campi.setFont(new Font("Georgia", Font.PLAIN, 12));
			
			final JPanel panelNuovoPrezzo=new JPanel();
			panelNuovoPrezzo.add(prezzo);
			panelNuovoPrezzo.add(campi);
			panelNuovoPrezzo.add(nuovoPrezzo);
			panelNuovoPrezzo.add(prezzoField);
			panelNuovoPrezzo.setVisible(false);
		
			pannello.add(panelNuovoPrezzo,BorderLayout.CENTER);
			pannello.add(bottoni,BorderLayout.SOUTH);
			
			modPrezzoStadio.add(pannello);	
			
			/**
			 * Il listener della combo box stadioCombo presente nel panel della scelta dello stadio.
			 * Selezionando un item relativo ad uno stadio viene visualizzato il panel relativo all'inserimento del nuovo prezzo.
			 * A seconda dello stadio selezionato il titled border del panel relativo all'inserimento del nuovo prezzo viene modificato.
			 * Verr‡ visualizzato inoltre il pulsante Conferma.
			*/
			class stadioComboListener implements ActionListener {
				public void actionPerformed (ActionEvent e) {				
					
					String riga11 = (String) stadioCombo.getSelectedItem();
					int j=riga11.indexOf("-");
					String num1=riga11.substring(0,j);
					int numero1 =Integer.parseInt(num1);
					
					stadio=sistema.getStadi().get(numero1-1);
					
					prezzo.setText("Il prezzo attuale dello stadio Ë : "+stadio.getPrezzo());
					
					TitledBorder a5=new TitledBorder(new EtchedBorder(), "Nuovo prezzo stadio "+stadio.getNomeStadio());
					a5.setTitleFont(new Font("Georgia", Font.ITALIC, 13));
					a5.setTitleColor(Color.BLACK);
					panelNuovoPrezzo.setBorder(a5);
					panelNuovoPrezzo.setVisible(true);
					conferma.setVisible(true);
					
				}
			}
			/**
			 * Il listener per il pulsante Conferma. 
			 * Se l'amministratore vuole confermare i dati inseriti e procedere dunque con la modifica del prezzo dello stadio, preme il tasto Conferma. 
			 * Il listener passa i dati inseriti al sistema e, se sono corretti, procede con la modifica del prezzo,
			 * presentando un messaggio di conferma della modifica del prezzo dello stadio.
			 * Altrimenti, se i dati inseriti non sono corretti, viene visualizzato un messaggio di errore.
			 *
			 *
			 */
			class ConfermaListener implements ActionListener {
				public void actionPerformed (ActionEvent e) {
					
					try {
						sistema.setPrezzoStadio(stadio,prezzoField.getText());
						modificaPrezzoStadio.dispose();
						def=new DefaultFrame(new JLabel("Il prezzo dello stadio Ë stato modificato "),
								"Prezzo modificato",450,new ImageIcon(imgURLOK),
								new AmministratoreFrame(sistema));
						def.setVisible(true);
					} catch (DatiNonValidiException e1) {
						modificaPrezzoStadio.setVisible(false);
						def=new DefaultFrame(new JLabel(e1.getMessage()),
								"Prezzo non modificato",450,new ImageIcon(imgURLnonOK),
								modificaPrezzoStadio);
						def.setVisible(true);
					}
				}
			}
			
			/**
			 * Il listener per il pulsante Annulla. 
			 * Se l'amministratore non vuole confermare i dati inseriti, selezionando il pulsante Annulla, 
			 * la finestra riguardante la modifica del prezzo dello stadio si chiude 
			 * ritornando alla schermate iniziale dell'amministratore.
			 */
			class AnnullaListener implements ActionListener {
				public void actionPerformed(ActionEvent e) {
						modificaPrezzoStadio.dispose();
						setVisible(true);		
				}
			}
			
			conferma.addActionListener(new ConfermaListener());
			stadioCombo.addActionListener(new stadioComboListener());
			annulla.addActionListener(new AnnullaListener());
			return modPrezzoStadio;
		}
	}
	
	/**
	 * Crea il form per l'inserimento di una politica di sconto.
	 */
	public JFrame inserisciPoliticheSconto(){
		JFrame inserisciPolitia=new JFrame();		
	
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		inserisciPolitia.setLocation(new Point((dimension.width - 
			inserisciPolitia.getSize().width) / 2-260, 
				(dimension.height - inserisciPolitia.getSize().height) / 2 -140));
		inserisciPolitia.setSize(520,280);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		inserisciPolitia.setResizable(false);
		inserisciPolitia.setLayout(new BorderLayout());
		inserisciPolitia.setUndecorated(true);
		
		JPanel pannello=new JPanel();
		pannello.setLayout(new GridLayout(4,1));
		TitledBorder a5=new TitledBorder(new EtchedBorder(),"Inserisci politica di sconto");
		a5.setTitleFont(new Font("Georgia", Font.ITALIC, 13));
		a5.setTitleColor(Color.BLACK);
		pannello.setBorder(a5);	
		
		JLabel scegli =new JLabel("Scegli ");
		scegli.setFont(new Font("Georgia", Font.PLAIN, 18));
		scegli.setHorizontalAlignment(JLabel.CENTER);
		scegli.setForeground(Color.BLACK);
			
		final JComboBox<String> politicheCombo = new JComboBox<String>();
		politicheCombo.setFont(new Font("Georgia", Font.PLAIN, 18));
		politicheCombo.addItem("1-Sconto per categoria");
		politicheCombo.addItem("2-Sconto per giorno");
		politicheCombo.addItem("3-Sconto per fascia giornaliera");
		politicheCombo.setSelectedItem(null);
			
		JPanel scegliPolitica=new JPanel();
		scegliPolitica.add(scegli);
		scegliPolitica.add(politicheCombo);
		TitledBorder a15=new TitledBorder(new EtchedBorder(),"Seleziona politica di sconto");
		a15.setTitleFont(new Font("Georgia", Font.ITALIC, 13));
		a15.setTitleColor(Color.BLACK);
		scegliPolitica.setBorder(a15);
			
		final JPanel caratteristicaPolitica=new JPanel();
		final JLabel la=new JLabel();
		la.setFont(new Font("Georgia", Font.PLAIN, 20));
		la.setForeground(Color.BLACK);
		
		final JRadioButton scelta1 = new JRadioButton();
		final JRadioButton scelta2 = new JRadioButton();
		final JRadioButton scelta3 = new JRadioButton();
		final JRadioButton scelta4 = new JRadioButton();
		final JRadioButton scelta5 = new JRadioButton();
		final JRadioButton scelta6 = new JRadioButton();
		final JRadioButton scelta7 = new JRadioButton();
		scelta1.setFont(new Font("Georgia", Font.PLAIN, 15));
		scelta1.setForeground(Color.BLACK);
		scelta2.setFont(new Font("Georgia", Font.PLAIN, 15));
		scelta2.setForeground(Color.BLACK);
		scelta3.setFont(new Font("Georgia", Font.PLAIN, 15));
		scelta3.setForeground(Color.BLACK);
		scelta4.setFont(new Font("Georgia", Font.PLAIN, 15));
		scelta4.setForeground(Color.BLACK);
		scelta5.setFont(new Font("Georgia", Font.PLAIN, 15));
		scelta5.setForeground(Color.BLACK);
		scelta6.setFont(new Font("Georgia", Font.PLAIN, 15));
		scelta6.setForeground(Color.BLACK);
		scelta7.setFont(new Font("Georgia", Font.PLAIN, 15));
		scelta7.setForeground(Color.BLACK);
		caratteristicaPolitica.add(la);
		caratteristicaPolitica.add(scelta1);
		caratteristicaPolitica.add(scelta2);
		caratteristicaPolitica.add(scelta3);
		caratteristicaPolitica.add(scelta4);
		caratteristicaPolitica.add(scelta5);
		caratteristicaPolitica.add(scelta6);
		caratteristicaPolitica.add(scelta7);
		scelta1.setVisible(false);
		scelta2.setVisible(false);
		scelta3.setVisible(false);
		scelta4.setVisible(false);
		scelta5.setVisible(false);
		scelta6.setVisible(false);
		scelta7.setVisible(false);
		caratteristicaPolitica.setVisible(false);
			
		JLabel percentuale=new JLabel("Percentuale di sconto*");
		final JTextField percentualeField=new JTextField(5);
		JLabel campi=new JLabel("                                       "
				+ "                                               "
				+ "                                      *campi obbligatori");
		percentuale.setFont(new Font("Georgia", Font.PLAIN, 18));
		percentuale.setHorizontalAlignment(JLabel.CENTER);
		percentuale.setForeground(Color.BLACK);
		percentualeField.setFont(new Font("Georgia", Font.PLAIN, 18));
		percentualeField.setHorizontalAlignment(JLabel.CENTER);
		percentualeField.setForeground(Color.BLACK);
		campi.setFont(new Font("Georgia", Font.PLAIN, 12));
		campi.setForeground(Color.BLACK);
			
		final JPanel panelSconto=new JPanel();
		panelSconto.add(campi);
		panelSconto.add(percentuale);
		panelSconto.add(percentualeField);
		panelSconto.setVisible(false);
			
		final JPanel bottoni=new JPanel();
		JButton annulla =new JButton("Annulla");
		annulla.setFont(new Font("Georgia", Font.PLAIN, 18));
		annulla.setHorizontalAlignment(JLabel.CENTER);
		annulla.setForeground(Color.BLACK);
		final JButton conferma =new JButton("Conferma");
		conferma.setFont(new Font("Georgia", Font.PLAIN, 18));
		conferma.setHorizontalAlignment(JLabel.CENTER);
		conferma.setForeground(Color.BLACK);
		bottoni.add(annulla);
		bottoni.add(conferma);
			
		pannello.add(scegliPolitica);
		pannello.add(caratteristicaPolitica);
		pannello.add(panelSconto);
		pannello.add(bottoni);
		caratteristicaPolitica.setVisible(false);
		panelSconto.setVisible(false);
		conferma.setVisible(false);
		
		/**
		 * Il listener per il pulsante Conferma.
		 * Se l'amministratore vuole confermare i dati inseriti e procedere dunque con l'inserimento della politica di sconto, preme il tasto Conferma. 
		 * Il listener passa i dati inseriti al sistema e, se sono corretti, procede con l'inserimento della politica di sconto
		 * presentando un messaggio di conferma di inserimento della politica di sconto.
		 * Altrimenti, se i dati inseriti non sono corretti, viene visualizzato un messaggio di errore.
		 *
		 */
		class ConfermaListener implements ActionListener {
			public void actionPerformed (ActionEvent e) {
				//consideriamo la posizione della politica di sconto all'interno della lista della combo box
				String riga11 = (String) politicheCombo.getSelectedItem();
				int j=riga11.indexOf("-");
				String num1=riga11.substring(0,j);
				int numero1 =Integer.parseInt(num1);
					
				String percentuale=percentualeField.getText();
				// a seconda della posizione della politica di sconto scelta controlleremo il radio button selezionato	
				if(numero1==1){
					if (scelta1.isSelected()){
						try 
						{
							sistema.aggiungiPoliticaSconto(percentuale,scelta1.getText()); 	
							inserisciPoliticaSconto.dispose();
							def=new DefaultFrame(new JLabel("La politica di sconto Ë stata inserita"),
									"Politica sconto inserita",450,new ImageIcon(imgURLOK),
									new AmministratoreFrame(sistema));
							def.setVisible(true);
						}
						catch (DatiNonValidiException e1) {
							inserisciPoliticaSconto.setVisible(false);
							def=new DefaultFrame(new JLabel(e1.getMessage()),
									"Politica sconto non inserita",450,new ImageIcon(imgURLnonOK),
									inserisciPoliticaSconto);
							def.setVisible(true);
						}
					}
					else if (scelta2.isSelected()){
						try 
						{
							sistema.aggiungiPoliticaSconto(percentuale,scelta2.getText()); 	
							inserisciPoliticaSconto.dispose();
							def=new DefaultFrame(new JLabel("La politica di sconto Ë stata inserita"),
								"Politica sconto inserita",450,new ImageIcon(imgURLOK),
									new AmministratoreFrame(sistema));
							def.setVisible(true);
						} 
						catch (DatiNonValidiException e1) {
							inserisciPoliticaSconto.setVisible(false);
							def=new DefaultFrame(new JLabel(e1.getMessage()),
								"Politica sconto non inserita",450,new ImageIcon(imgURLnonOK),
								inserisciPoliticaSconto);
							def.setVisible(true);
						}
					}
					else if (scelta3.isSelected()){
						try 
						{
							sistema.aggiungiPoliticaSconto(percentuale,scelta3.getText()); 	
							inserisciPoliticaSconto.dispose();
							def=new DefaultFrame(new JLabel("La politica di sconto Ë stata inserita"),
								"Politica sconto inserita",450,new ImageIcon(imgURLOK),
								new AmministratoreFrame(sistema));
							def.setVisible(true);
						} 
						catch (DatiNonValidiException e1) {
							inserisciPoliticaSconto.setVisible(false);
							def=new DefaultFrame(new JLabel(e1.getMessage()),
								"Politica sconto non inserita",450,new ImageIcon(imgURLnonOK),
								inserisciPoliticaSconto);
							def.setVisible(true);
						}
						}
					else if (scelta4.isSelected()){
						try 
						{
							sistema.aggiungiPoliticaSconto(percentuale,scelta4.getText()); 	
							inserisciPoliticaSconto.dispose();
							def=new DefaultFrame(new JLabel("La politica di sconto Ë stata inserita"),
									"Politica sconto inserita",450,new ImageIcon(imgURLOK),
									new AmministratoreFrame(sistema));
							def.setVisible(true);
						} 
						catch (DatiNonValidiException e1) {
							inserisciPoliticaSconto.setVisible(false);
							def=new DefaultFrame(new JLabel(e1.getMessage()),
								"Politica sconto non inserita",450,new ImageIcon(imgURLnonOK),
								inserisciPoliticaSconto);
							def.setVisible(true);
						}
					}
				}
				else if(numero1==2){
					if (scelta1.isSelected()){
						try 
						{
							sistema.aggiungiPoliticaSconto(percentuale,scelta1.getText()); 	
							inserisciPoliticaSconto.dispose();
							def=new DefaultFrame(new JLabel("La politica di sconto Ë stata inserita"),
								"Politica sconto inserita",450,new ImageIcon(imgURLOK),
								new AmministratoreFrame(sistema));
							def.setVisible(true);
						} 
						catch (DatiNonValidiException e1) {
							inserisciPoliticaSconto.setVisible(false);
							def=new DefaultFrame(new JLabel(e1.getMessage()),
									"Politica sconto non inserita",450,new ImageIcon(imgURLnonOK),
									inserisciPoliticaSconto);
							def.setVisible(true);
						}
					}
					else if (scelta2.isSelected()){
						try 
						{
							sistema.aggiungiPoliticaSconto(percentuale,scelta2.getText()); 	
							inserisciPoliticaSconto.dispose();
							def=new DefaultFrame(new JLabel("La politica di sconto Ë stata inserita"),
								"Politica sconto inserita",450,new ImageIcon(imgURLOK),
								new AmministratoreFrame(sistema));
							def.setVisible(true);
						} 
						catch (DatiNonValidiException e1) {
							inserisciPoliticaSconto.setVisible(false);
							def=new DefaultFrame(new JLabel(e1.getMessage()),
									"Politica sconto non inserita",450,new ImageIcon(imgURLnonOK),
									inserisciPoliticaSconto);
							def.setVisible(true);
						}
					}
					else if (scelta3.isSelected()){
						try 
						{
							sistema.aggiungiPoliticaSconto(percentuale,scelta3.getText()); 	
							inserisciPoliticaSconto.dispose();
							def=new DefaultFrame(new JLabel("La politica di sconto Ë stata inserita"),
								"Politica sconto inserita",450,new ImageIcon(imgURLOK),
								new AmministratoreFrame(sistema));
							def.setVisible(true);
						} 
						catch (DatiNonValidiException e1) {
							inserisciPoliticaSconto.setVisible(false);
							def=new DefaultFrame(new JLabel(e1.getMessage()),
								"Politica sconto non inserita",450,new ImageIcon(imgURLnonOK),
								inserisciPoliticaSconto);
							def.setVisible(true);
						}
					}
					else if (scelta4.isSelected()){
						try 
						{
							sistema.aggiungiPoliticaSconto(percentuale,scelta4.getText()); 	
							inserisciPoliticaSconto.dispose();
							def=new DefaultFrame(new JLabel("La politica di sconto Ë stata inserita"),
								"Politica sconto inserita",450,new ImageIcon(imgURLOK),
								new AmministratoreFrame(sistema));
							def.setVisible(true);
						} 
						catch (DatiNonValidiException e1) {
							inserisciPoliticaSconto.setVisible(false);
							def=new DefaultFrame(new JLabel(e1.getMessage()),
								"Politica sconto non inserita",450,new ImageIcon(imgURLnonOK),
								inserisciPoliticaSconto);
							def.setVisible(true);
						}
					}
					else if (scelta5.isSelected()){
						try 
						{
							sistema.aggiungiPoliticaSconto(percentuale,scelta5.getText()); 	
							inserisciPoliticaSconto.dispose();
							def=new DefaultFrame(new JLabel("La politica di sconto Ë stata inserita"),
								"Politica sconto inserita",450,new ImageIcon(imgURLOK),
								new AmministratoreFrame(sistema));
							def.setVisible(true);
						} 
						catch (DatiNonValidiException e1) {
							inserisciPoliticaSconto.setVisible(false);
							def=new DefaultFrame(new JLabel(e1.getMessage()),
									"Politica sconto non inserita",450,new ImageIcon(imgURLnonOK),
									inserisciPoliticaSconto);
							def.setVisible(true);
						}
					}
					else if (scelta6.isSelected()){
						try 
						{
							sistema.aggiungiPoliticaSconto(percentuale,scelta6.getText()); 	
							inserisciPoliticaSconto.dispose();
							def=new DefaultFrame(new JLabel("La politica di sconto Ë stata inserita"),
								"Politica sconto inserita",450,new ImageIcon(imgURLOK),
									new AmministratoreFrame(sistema));
							def.setVisible(true);
						} 
						catch (DatiNonValidiException e1) {
							inserisciPoliticaSconto.setVisible(false);
							def=new DefaultFrame(new JLabel(e1.getMessage()),
									"Politica sconto non inserita",450,new ImageIcon(imgURLnonOK),
									inserisciPoliticaSconto);
							def.setVisible(true);
						}
					}
					else if (scelta7.isSelected()){
						try 
						{
							sistema.aggiungiPoliticaSconto(percentuale,scelta7.getText()); 	
							inserisciPoliticaSconto.dispose();
							def=new DefaultFrame(new JLabel("La politica di sconto Ë stata inserita"),
								"Politica sconto inserita",450,new ImageIcon(imgURLOK),
								new AmministratoreFrame(sistema));
							def.setVisible(true);
						} 
						catch (DatiNonValidiException e1) {
							inserisciPoliticaSconto.setVisible(false);
							def=new DefaultFrame(new JLabel(e1.getMessage()),
									"Politica sconto non inserita",450,new ImageIcon(imgURLnonOK),
									inserisciPoliticaSconto);
							def.setVisible(true);
						}
					}	
				}
				else{
					if (scelta1.isSelected()){
						try 
						{
							sistema.aggiungiPoliticaSconto(percentuale,scelta1.getText()); 	
							inserisciPoliticaSconto.dispose();
							def=new DefaultFrame(new JLabel("La politica di sconto Ë stata inserita"),
								"Politica sconto inserita",450,new ImageIcon(imgURLOK),
								new AmministratoreFrame(sistema));
							def.setVisible(true);
						} 
						catch (DatiNonValidiException e1) {
							inserisciPoliticaSconto.setVisible(false);
							def=new DefaultFrame(new JLabel(e1.getMessage()),
								"Politica sconto non inserita",450,new ImageIcon(imgURLnonOK),
								inserisciPoliticaSconto);
							def.setVisible(true);
						}
					}
					else if (scelta2.isSelected()){
						try 
						{
							sistema.aggiungiPoliticaSconto(percentuale,scelta2.getText()); 	
							inserisciPoliticaSconto.dispose();
							def=new DefaultFrame(new JLabel("La politica di sconto Ë stata inserita"),
								"Politica sconto inserita",450,new ImageIcon(imgURLOK),
								new AmministratoreFrame(sistema));
							def.setVisible(true);
						} 
						catch (DatiNonValidiException e1) {
							inserisciPoliticaSconto.setVisible(false);
							def=new DefaultFrame(new JLabel(e1.getMessage()),
									"Politica sconto non inserita",450,new ImageIcon(imgURLnonOK),
									inserisciPoliticaSconto);
							def.setVisible(true);
						}
					}
					else if (scelta3.isSelected()){
						try 
						{
							sistema.aggiungiPoliticaSconto(percentuale,scelta3.getText()); 	
							inserisciPoliticaSconto.dispose();
							def=new DefaultFrame(new JLabel("La politica di sconto Ë stata inserita"),
								"Politica sconto inserita",450,new ImageIcon(imgURLOK),
								new AmministratoreFrame(sistema));
							def.setVisible(true);
						} 
						catch (DatiNonValidiException e1) {
							inserisciPoliticaSconto.setVisible(false);
							def=new DefaultFrame(new JLabel(e1.getMessage()),
								"Politica sconto non inserita",450,new ImageIcon(imgURLnonOK),
								inserisciPoliticaSconto);
							def.setVisible(true);
						}
					}
				}
			}
		}
		
		/**
		 * Il listener della combo box politicheCombo presente nel panel della scelta della politica di sconto.
		 * Selezionando un item relativo ad una politica di sconto viene visualizzato il panel caratteristicaPolitica relativo ai radio button che descrivono la politica di sconto.
		 * A seconda della politica di sconto selezionata il titled border del panel caratteristicaPolitica varia a seconda della politica di sconto.
		 * Verr‡ visualizzato inoltre il pulsante Conferma.
		 *
		 */
		class ScontoComboListener implements ActionListener {
			public void actionPerformed (ActionEvent e) {				
					
				String riga11 = (String) politicheCombo.getSelectedItem();
				int j=riga11.indexOf("-");
				String num1=riga11.substring(0,j);
				int numero1 =Integer.parseInt(num1);
					
				scelta1.setVisible(true);
				scelta2.setVisible(true);
				scelta3.setVisible(true);
				caratteristicaPolitica.setVisible(true);
				panelSconto.setVisible(false);
				conferma.setVisible(true);
					
				if(numero1==1){
					TitledBorder a5=new TitledBorder(new EtchedBorder(),"Seleziona categoria");
					a5.setTitleFont(new Font("Georgia", Font.ITALIC, 13));
					a5.setTitleColor(Color.BLACK);
					caratteristicaPolitica.setBorder(a5);
						
					la.setText(" Categoria  ");
					scelta1.setText("Bambino");
					scelta2.setText("Studente");
					scelta3.setText("Pensionato");
					scelta4.setText("Adulto");
					scelta4.setVisible(true);
					scelta5.setVisible(false);
					scelta6.setVisible(false);
					scelta7.setVisible(false);
					ButtonGroup group = new ButtonGroup();
					group.add(scelta1);
					group.add(scelta2);
					group.add(scelta3);
					group.add(scelta4);
					group.clearSelection();
				}
				else if(numero1==2){
					TitledBorder a5=new TitledBorder(new EtchedBorder(),"Seleziona giorno");
					a5.setTitleFont(new Font("Georgia", Font.ITALIC, 13));
					a5.setTitleColor(Color.BLACK);
					caratteristicaPolitica.setBorder(a5);
						
					la.setText(" Giorno  ");
						
					scelta1.setText("Dom");
					scelta2.setText("Lun");
					scelta3.setText("Mar");
					scelta4.setText("Mer");
					scelta5.setText("Gio");
					scelta6.setText("Ven");
					scelta7.setText("Sab");
					ButtonGroup group = new ButtonGroup();
					group.add(scelta1);
					group.add(scelta2);
					group.add(scelta3);
					group.add(scelta4);
					group.add(scelta5);
					group.add(scelta6);
					group.add(scelta7);
					scelta4.setVisible(true);
					scelta5.setVisible(true);
					scelta6.setVisible(true);
					scelta7.setVisible(true);
					group.clearSelection();
				}
				else{
					TitledBorder a5=new TitledBorder(new EtchedBorder(),"Seleziona fascia giornaliera");
					a5.setTitleFont(new Font("Georgia", Font.ITALIC, 13));
					a5.setTitleColor(Color.BLACK);
					la.setText(" Fascia giornaliera  ");
					caratteristicaPolitica.setBorder(a5);
					scelta1.setText("Mattina");
					scelta2.setText("Pomeriggio");
					scelta3.setText("Sera");
					scelta4.setVisible(false);
					scelta5.setVisible(false);
					scelta6.setVisible(false);
					scelta7.setVisible(false);
					ButtonGroup group = new ButtonGroup();
					group.add(scelta1);
					group.add(scelta2);
					group.add(scelta3);
					group.clearSelection();
				}	
			}
		}
		
		/**
		 * Il listener dei radio button.
		 * Selezionato un radio button relativo ad una politica di sconto, 
		 * viene visualizzato il panel panelSconto dove Ë possibile inserire la percentuale di sconto.
		 *
		 */
		class SelezionaSceltaListener implements ActionListener {
			public void actionPerformed (ActionEvent e) {
				
				bottoni.setVisible(true);
				panelSconto.setVisible(true);
			}
		}
		
		/**
		 * Il listener per il pulsante Annulla. 
		 * Se l'amministratore non vuole confermare i dati inseriti, selezionando il pulsante Annulla, 
		 * la finestra riguardante l'inserimento di una nuova politica di sconto si chiude 
		 * ritornando alla schermate iniziale dell'amministratore.
		 *
		 *
		 */
		class AnnullaListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				inserisciPoliticaSconto.dispose();
				setVisible(true);		
			}
		}
			
		annulla.addActionListener(new AnnullaListener());
		conferma.addActionListener(new ConfermaListener());
		scelta1.addActionListener(new SelezionaSceltaListener());
		scelta2.addActionListener(new SelezionaSceltaListener());
		scelta3.addActionListener(new SelezionaSceltaListener());
		scelta4.addActionListener(new SelezionaSceltaListener());
		scelta5.addActionListener(new SelezionaSceltaListener());
		scelta6.addActionListener(new SelezionaSceltaListener());
		scelta7.addActionListener(new SelezionaSceltaListener());
		politicheCombo.addActionListener(new ScontoComboListener());
			
		inserisciPolitia.add(pannello);
		return inserisciPolitia;
	}
	
	/**
	 * Crea il form per la rimozione di una politica di sconto
	 */
	public JFrame rimuoviPoliticheSconto () throws DatiNonValidiException{
		
		if(sistema.getPoliticheSconto().size()==0)
			throw new DatiNonValidiException("Non sono presenti politiche di sconto");
		else{
			final JFrame rimuoviSconto=new JFrame(); // il frame viene creato solo se sono presenti politiche di sconto nel sistema		
				
			Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
			rimuoviSconto.setLocation(new Point((dimension.width - 
				rimuoviSconto.getSize().width) / 2-200, 
				(dimension.height - rimuoviSconto.getSize().height) / 2 -110));
			rimuoviSconto.setSize(400,220);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			rimuoviSconto.setResizable(false);
			rimuoviSconto.setLayout(new BorderLayout());
			rimuoviSconto.setUndecorated(true);
				
			JLabel scegli =new JLabel("Seleziona politica di sconto da eliminare");
			scegli.setFont(new Font("Georgia", Font.PLAIN, 18));
			scegli.setHorizontalAlignment(JLabel.CENTER);
			scegli.setForeground(Color.BLACK);
				
			final JList<String> listaPolitiche;
			final DefaultListModel<String> listModel;
			listModel = new DefaultListModel<>();
			listaPolitiche = new JList<String>(listModel);
			listaPolitiche.setLayout(null);	
				
				
			JScrollPane scrollPane = new JScrollPane(listaPolitiche,
			JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
			JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
				
				
					
			JPanel pannello=new JPanel();
			pannello.setLayout(new BorderLayout());
			pannello.add(scegli,BorderLayout.NORTH);
			pannello.add(scrollPane,BorderLayout.CENTER);
				
			rimuoviSconto.add(pannello);
			TitledBorder a5=new TitledBorder(new EtchedBorder(),"Rimuovi politica di sconto");
			a5.setTitleFont(new Font("Georgia", Font.ITALIC, 13));
			a5.setTitleColor(Color.BLACK);
			pannello.setBorder(a5);
				
			listModel.removeAllElements();
			listaPolitiche.setFont(new Font("Georgia", Font.PLAIN, 18));
			listaPolitiche.setForeground(Color.BLACK);
				
			for(PoliticaDiSconto politica: sistema.getPoliticheSconto())
				if(politica instanceof ScontoCategoria){
					ScontoCategoria c=(ScontoCategoria) politica;
					String a="Politica Sconto : categoria - Categoria "
							+ ": "+c.getCategoria()+" - Percentuale "
								+ "di sconto"+c.getPercentualeSconto();
					listModel.addElement(a);
				}
				else if(politica instanceof ScontoGiorno){
					ScontoGiorno c=(ScontoGiorno) politica;
					int g=c.getDayOfWeek();
					String gio=null;
					if(g==0)
						gio="Domomenica";
					else if(g==1)
						gio="LunedÏ";
					else if(g==2)
						gio="MartedÏ";
					else if(g==3)
						gio="MercoledÏ";
					else if(g==4)
						gio="GiovedÏ";
					else if(g==5)
						gio="VenerdÏ";
					else if(g==6)
						gio="Sabato";
						
					String a="Politica Sconto : giorno - Giorno : "
							+ gio +" - Percentuale "
								+ "di sconto"+c.getPercentualeSconto();
					listModel.addElement(a);
				}
				else if(politica instanceof ScontoOrario){
					ScontoOrario c=(ScontoOrario) politica;
					String a="Politica Sconto : fascia giornaliera - "
						+ "Fascia giornaliera "
						+ ": "+c.getFasciaGiornaliera()+" - Percentuale "
							+ "di sconto : "+c.getPercentualeSconto();
				listModel.addElement(a);
			}
				
			JPanel bottoni= new JPanel();
			final JButton buttonConferma=new JButton("Conferma");
			buttonConferma.setFont(new Font("Georgia", Font.PLAIN, 18));
			buttonConferma.setForeground(Color.BLACK);
			JButton buttonIndietro=new JButton("Indietro");
			buttonIndietro.setFont(new Font("Georgia", Font.PLAIN, 18));
			buttonIndietro.setForeground(Color.BLACK);
			bottoni.add(buttonIndietro);
			bottoni.add(buttonConferma);	
			pannello.add(bottoni, BorderLayout.SOUTH);
			
			if(sistema.getPoliticheSconto().size()==0)
				buttonConferma.setVisible(false);
			else
				buttonConferma.setVisible(true);
			
			/**
			 * Il listener per il pulsante Conferma. 
			 * Se l'amministratore vuole confermare di rimuovere la politica di sconto selezionata dalla lista, preme il tasto Conferma. 
			 * Il listener passa l'indice della politica di sconto al sistema affinchË la elimini.
			 * Altrimenti, se non si seleziona alcuna politica di sconto e viene premuto il pulsante Conferma, viene visualizzato un messaggio di errore.
			 *
			 */
			class ConfermaListener implements ActionListener{
				public void actionPerformed(ActionEvent e){
					try{
						if (listModel.size() > 0) { 
							int index = listaPolitiche.getSelectedIndex();
							sistema.rimuoviPoliticaSconto(index);
							listModel.removeElementAt(index);
							if(listModel.size()==0)
								buttonConferma.setVisible(false);
						}
					}
					catch(ArrayIndexOutOfBoundsException e1){
						rimuoviPoliticaSconto.setVisible(false);
						def=new DefaultFrame(new JLabel("Selezionare una politica di sconto da eliminare"),
							"Selezionare politica sconto",450,new ImageIcon(imgURLnonOK),
							rimuoviPoliticaSconto);
						def.setVisible(true);
					}
				}
			}
			
			/**
			 * Il listene per il il pulsante Indietro.
			 * Se l'amministratore non vuole confermare di rimuovere una politica, selezionando il pulsante Indietro, 
			 * la finestra riguardante la la rimozione della politica di sconto si chiude 
			 * ritornando alla schermate iniziale dell'amministratore.
			 *
			 */
			class IndietroListener implements ActionListener {
				public void actionPerformed(ActionEvent e) {
					rimuoviSconto.dispose();
					setVisible(true);		
				}
			}
				
			buttonConferma.addActionListener(new ConfermaListener());
			buttonIndietro.addActionListener(new IndietroListener());
			return rimuoviSconto;
		}
	}
	
	/**
	 * Crea il form per la visualizzazione di eventi calcistici ordinati cronologicamente
	 */
	public JFrame eventiCalcisticiPerData () throws CloneNotSupportedException, DatiNonValidiException{
			
		setVisible(false);
		
		final JFrame visualizzaEventiCalcisticiPerData=new JFrame();		
			
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		visualizzaEventiCalcisticiPerData.setLocation(new Point((dimension.width - 
			visualizzaEventiCalcisticiPerData.getSize().width) / 2-400, 
			(dimension.height - visualizzaEventiCalcisticiPerData.getSize().height) / 2 -200));
			
		visualizzaEventiCalcisticiPerData.setSize(800,400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		visualizzaEventiCalcisticiPerData.setResizable(false);
		visualizzaEventiCalcisticiPerData.setLayout(new BorderLayout());
		visualizzaEventiCalcisticiPerData.setUndecorated(true);
			
		JPanel bottoni=new JPanel();
		JButton indietro =new JButton("Indietro");
		indietro.setFont(new Font("Georgia", Font.PLAIN, 18));
		indietro.setHorizontalAlignment(JLabel.CENTER);
		indietro.setForeground(Color.BLACK);
		bottoni.add(indietro);
			
		JPanel pannello = new JPanel();
		pannello.setLayout(null);
		JScrollPane scroll = new JScrollPane(pannello,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		visualizzaEventiCalcisticiPerData.add(scroll);
		pannello.setLayout(new BorderLayout());
			
		pannello.add(bottoni,BorderLayout.NORTH);
			
		TitledBorder t=new TitledBorder(new EtchedBorder(), "Eventi calcistici");
		t.setTitleFont(new Font("Georgia", Font.ITALIC, 13));
		t.setTitleColor(Color.BLACK);
		pannello.setBorder(t);
			
		JPanel pannelloEventi=new JPanel();
		TitledBorder t1=new TitledBorder(new EtchedBorder(), "Eventi calcistici in ordine cronologico");
		t1.setTitleFont(new Font("Georgia", Font.ITALIC, 13));
		t1.setTitleColor(Color.BLACK);
		pannelloEventi.setBorder(t1);
		ArrayList<EventoCalcistico> eventi = sistema.getEventiCalcisticiPerData();
			
		pannelloEventi.setLayout(new GridLayout(eventi.size()+1,1));
		pannello.add(pannelloEventi,BorderLayout.CENTER);
			
		JPanel rigaTitol=new JPanel();
		rigaTitol.setLayout(new GridLayout(1,6));
			
		JLabel l1=new JLabel(" SquadraCasa ");
		JLabel l2=new JLabel(" SquadraOspite ");
		JLabel l3=new JLabel(" Stadio ");
		JLabel l4=new JLabel(" Prezzo "); //
		JLabel l6=new JLabel(" Data ");
		JLabel l0=new JLabel(" Ora ");
		l1.setFont(new Font("Georgia", Font.PLAIN, 14));
		l1.setHorizontalAlignment(JLabel.CENTER);
		l1.setForeground(Color.BLACK);
		l2.setHorizontalAlignment(JLabel.CENTER);
		l2.setFont(new Font("Georgia", Font.PLAIN, 14));
		l2.setForeground(Color.BLACK);
		l3.setFont(new Font("Georgia", Font.PLAIN, 14));
		l3.setHorizontalAlignment(JLabel.CENTER);
		l3.setForeground(Color.BLACK);
		l4.setFont(new Font("Georgia", Font.PLAIN, 14));
		l4.setHorizontalAlignment(JLabel.CENTER);
		l4.setForeground(Color.BLACK);
		l6.setFont(new Font("Georgia", Font.PLAIN, 14));
		l6.setForeground(Color.BLACK);
		l6.setHorizontalAlignment(JLabel.CENTER);
		l0.setFont(new Font("Georgia", Font.PLAIN, 14));
		l0.setForeground(Color.BLACK);
		l0.setHorizontalAlignment(JLabel.CENTER);
		rigaTitol.add(l1);
		rigaTitol.add(l2);
		rigaTitol.add(l3);
		rigaTitol.add(l4);
		rigaTitol.add(l6);
		rigaTitol.add(l0);
		pannelloEventi.add(rigaTitol);
			
		for(int i=eventi.size()-1; i>=0; i--){
			EventoCalcistico ev=eventi.get(i);
			JPanel rigaEvento=new JPanel();
			rigaEvento.setLayout(new GridLayout(1,7));
			SimpleDateFormat sdf =new SimpleDateFormat("dd/MM/yyyy");
			String a=sdf.format(ev.getDataInizioEventoCalcistico().getTime());
			SimpleDateFormat sdf1 =new SimpleDateFormat("HH:mm:ss");
			String a1=sdf1.format(ev.getDataInizioEventoCalcistico().getTime());
				
			JLabel l7=new JLabel(ev.getPartita().getSquadraCasa());
			JLabel l8=new JLabel(ev.getPartita().getSquadraOspite());
			JLabel l9=new JLabel(ev.getStadio().getNomeStadio());
			
			DecimalFormat df = new DecimalFormat("#.##"); 

			String s = df.format(ev.getStadio().getPrezzo());
			JLabel l10=new JLabel(s+" Ä"); 
			JLabel l12=new JLabel(a);
			JLabel l13=new JLabel(a1);
			l7.setFont(new Font("Georgia", Font.PLAIN, 14));
			l7.setForeground(Color.BLACK);
			l8.setFont(new Font("Georgia", Font.PLAIN, 14));
			l8.setForeground(Color.BLACK);
			l9.setFont(new Font("Georgia", Font.PLAIN, 14));
			l9.setForeground(Color.BLACK);
			l10.setFont(new Font("Georgia", Font.PLAIN, 14));
			l10.setForeground(Color.BLACK);
			l12.setFont(new Font("Georgia", Font.PLAIN, 14));
			l12.setForeground(Color.BLACK);
			l13.setFont(new Font("Georgia", Font.PLAIN, 14));
			l13.setForeground(Color.BLACK);
			l7.setHorizontalAlignment(JLabel.CENTER);
			l8.setHorizontalAlignment(JLabel.CENTER);
			l9.setHorizontalAlignment(JLabel.CENTER);
			l10.setHorizontalAlignment(JLabel.CENTER);
			l12.setHorizontalAlignment(JLabel.CENTER);
			l13.setHorizontalAlignment(JLabel.CENTER);
			rigaEvento.add(l7);
			rigaEvento.add(l8);
			rigaEvento.add(l9);
			rigaEvento.add(l10);
			rigaEvento.add(l12);
			rigaEvento.add(l13);	
			pannelloEventi.add(rigaEvento);
		}
		
		/**
		 * Il listener per il pulsante Indietro.  
		 * Selezionando il pulsante Indietro, la finestra riguardante gli eventi calcistici ordinati cronologicamente si chiude 
		 * ritornando alla schermate iniziale dell'amministratore.
		 *
		 */
		class IndietroListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				visualizzaEventiCalcisticiPerData.dispose();
				setVisible(true);
			}
		}	
		indietro.addActionListener(new IndietroListener());
		
		return visualizzaEventiCalcisticiPerData;
	}

	/**
	 * Crea il form per la visualizzazione dell'incasso totale
	 */
	public JFrame incassoTotale (){	
		setVisible(false);
		final JFrame incasso=new JFrame();		
			
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		incasso.setLocation(new Point((dimension.width - 
				incasso.getSize().width) / 2-300, 
		(dimension.height - incasso.getSize().height) / 2 -125));
		incasso.setSize(600,250);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		incasso.setResizable(false);
		incasso.setLayout(new BorderLayout());
		incasso.setUndecorated(true);
				
		JPanel descrizioneRigo1=new JPanel();
		JLabel descrizione1=new JLabel("L'incasso totale del ");
		descrizione1.setFont(new Font("Georgia", Font.ITALIC, 30));
		descrizione1.setForeground(Color.BLACK);
		JLabel descrizione2=new JLabel(sistema.getNome());
		descrizione2.setFont(new Font("Georgia", Font.ITALIC, 35));
		descrizione2.setForeground(Color.RED);
		JLabel descrizione3=new JLabel(" Ë : ");
		descrizione3.setFont(new Font("Georgia", Font.ITALIC, 30));
		descrizione3.setForeground(Color.BLACK);
		descrizioneRigo1.add(descrizione1);
		descrizioneRigo1.add(descrizione2);
		descrizioneRigo1.add(descrizione3);
		
		JPanel bottoni=new JPanel();
		JButton indietro =new JButton("Indietro");
		indietro.setFont(new Font("Georgia", Font.PLAIN, 18));
		indietro.setHorizontalAlignment(JLabel.CENTER);
		indietro.setForeground(Color.BLACK);
		bottoni.add(indietro);	
			
		JPanel descrizioneRigo2=new JPanel();
		JLabel descrizione4=new JLabel("  "+sistema.getIncassoTotale()+"  ");
		descrizione4.setFont(new Font("Georgia", Font.BOLD, 80));
		descrizione4.setForeground(Color.BLUE);
		JLabel descrizione5=new JLabel("Ä ");
		descrizione5.setFont(new Font("Georgia", Font.ITALIC, 60));
		descrizione5.setForeground(Color.BLACK);
		descrizioneRigo2.add(descrizione4);
		descrizioneRigo2.add(descrizione5);
			
		JPanel pannello = new JPanel();
		pannello.setLayout(new BorderLayout());
		pannello.add(bottoni,BorderLayout.SOUTH);
		pannello.add(descrizioneRigo2,BorderLayout.CENTER);
		pannello.add(descrizioneRigo1, BorderLayout.NORTH);
		TitledBorder t=new TitledBorder(new EtchedBorder(), "Incasso totale");
		t.setTitleFont(new Font("Georgia", Font.ITALIC, 13));
		t.setTitleColor(Color.BLACK);
		pannello.setBorder(t);
		incasso.add(pannello);
		
		/**
		 * Il listener per il pulsante Indietro.  
		 * Selezionando il pulsante Indietro, la finestra riguardante l'incasso totale si chiude 
		 * ritornando alla schermate iniziale dell'amministratore.
		 *
		 */
		class IndietroListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				incasso.dispose();
				setVisible(true);
			}
		}	
			
		indietro.addActionListener(new IndietroListener());
		return incasso;
	}
	
	/**
	 * Crea il form per la visualizzazione dell'incasso degli stadi
	 */
	public JFrame incassoStadio() throws DatiNonValidiException{
		final JFrame getIncassoStadio=new JFrame();		
			
		if(sistema.getStadi().size()==0)
			throw new DatiNonValidiException("Non sono presenti stadi");
		else{
			Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
			getIncassoStadio.setLocation(new Point((dimension.width - 
				getIncassoStadio.getSize().width) / 2-350, 
				(dimension.height - getIncassoStadio.getSize().height) / 2 -175));
			
			getIncassoStadio.setSize(700,350);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			getIncassoStadio.setResizable(false);
			getIncassoStadio.setLayout(new BorderLayout());
			getIncassoStadio.setUndecorated(true);
			
			JLabel scegli =new JLabel("Scegli Stadio");
			scegli.setFont(new Font("Georgia", Font.PLAIN, 18));
			scegli.setHorizontalAlignment(JLabel.CENTER);
			scegli.setForeground(Color.BLACK);
			
			final JComboBox<String> stadioCombo = new JComboBox<String>();
			stadioCombo.setFont(new Font("Georgia", Font.PLAIN, 18));
			ArrayList<Stadio> stadi=sistema.getStadi();
			for(int i=0; i<stadi.size(); i++){
				stadioCombo.addItem((i+1)+"-"+stadi.get(i).getNomeStadio()+"-"+
				stadi.get(i).getCitt‡Stadio()+"-"+stadi.get(i).getCapienza());
			}
			stadioCombo.setSelectedItem(null);
			
			JPanel pannello=new JPanel();
			pannello.setLayout(new BorderLayout());
			
			JPanel scegliStadio=new JPanel();
			scegliStadio.add(scegli);
			scegliStadio.add(stadioCombo);
			
			TitledBorder a5=new TitledBorder(new EtchedBorder(),"Seleziona stadio");
			a5.setTitleFont(new Font("Georgia", Font.ITALIC, 13));
			a5.setTitleColor(Color.BLACK);
			scegliStadio.setBorder(a5);
			
			pannello.add(scegliStadio,BorderLayout.NORTH);
			
			
			JPanel descrizioneRigo1=new JPanel();
			JLabel descrizione1=new JLabel("L'incasso totale dello stadio ");
			descrizione1.setFont(new Font("Georgia", Font.ITALIC, 30));
			descrizione1.setForeground(Color.BLACK);
			final JLabel descrizione2=new JLabel();
			descrizione2.setFont(new Font("Georgia", Font.ITALIC, 35));
			descrizione2.setForeground(Color.RED);
			JLabel descrizione3=new JLabel(" Ë : ");
			descrizione3.setFont(new Font("Georgia", Font.ITALIC, 30));
			descrizione3.setForeground(Color.BLACK);
			descrizioneRigo1.add(descrizione1);
			descrizioneRigo1.add(descrizione2);
			descrizioneRigo1.add(descrizione3);
				
			JPanel descrizioneRigo2=new JPanel();
			final JLabel descrizione4=new JLabel();
			descrizione4.setFont(new Font("Georgia", Font.BOLD, 80));
			descrizione4.setForeground(Color.BLUE);
			JLabel descrizione5=new JLabel("Ä ");
			descrizione5.setFont(new Font("Georgia", Font.ITALIC, 60));
			descrizione5.setForeground(Color.BLACK);
				
			descrizioneRigo2.add(descrizione4);
			descrizioneRigo2.add(descrizione5);
			
			final JPanel pannelloIncasso=new JPanel();
			pannelloIncasso.setLayout(new GridLayout(2,1));
			pannelloIncasso.add(descrizioneRigo1);
			pannelloIncasso.add(descrizioneRigo2);
			pannelloIncasso.setVisible(false);
			pannello.add(pannelloIncasso,BorderLayout.CENTER);	
				
			final JPanel bottoni=new JPanel();
			JButton indietro =new JButton("Indietro");
			indietro.setFont(new Font("Georgia", Font.PLAIN, 18));
			indietro.setHorizontalAlignment(JLabel.CENTER);
			indietro.setForeground(Color.BLACK);
			bottoni.add(indietro);
			
			TitledBorder a9=new TitledBorder(new EtchedBorder(),"Incasso stadio");
			a9.setTitleFont(new Font("Georgia", Font.ITALIC, 13));
			a9.setTitleColor(Color.BLACK);
			pannello.setBorder(a9);
			pannello.add(bottoni,BorderLayout.SOUTH);
				
			getIncassoStadio.add(pannello);			
			
			/**
			 * Il listener della combo box stadioCombo del panel riguardante la scelta dello stadio.
			 * Selezionando un item relativo ad uno stadio viene visualizzato il panel relativo all'incasso dello stadio.
			 * A seconda dello stadio selezionato il titled border del panel dell'incasso dello stadio varia a seconda dello stadio.
			 *
			 */
			class stadioComboListener implements ActionListener {
				public void actionPerformed (ActionEvent e) {				
					String riga11 = (String) stadioCombo.getSelectedItem();
					int j=riga11.indexOf("-");
					String num1=riga11.substring(0,j);
					int numero1 =Integer.parseInt(num1);
						
					stadio=sistema.getStadi().get(numero1-1);
						
					descrizione2.setText(stadio.getNomeStadio());	
					DecimalFormat df = new DecimalFormat("#.##");

					String s = df.format(sistema.getIncassoStadio(stadio));
					descrizione4.setText("  "+s+"  ");
					TitledBorder a5=new TitledBorder(new EtchedBorder(), "Incasso stadio "+stadio.getNomeStadio());
					a5.setTitleFont(new Font("Georgia", Font.ITALIC, 13));
					a5.setTitleColor(Color.BLACK);
					pannelloIncasso.setBorder(a5);
					pannelloIncasso.setVisible(true);
					bottoni.setVisible(true);
						
				}
			}
			
			/**
			 * Il listener per il pulsante Indietro.  
			 * Selezionando il pulsante Indietro, la finestra riguardante l'incasso dello stadio si chiude 
			 * ritornando alla schermate iniziale dell'amministratore.
			 */
			class IndietroListener implements ActionListener {
				public void actionPerformed(ActionEvent e) {
					getIncassoStadio.dispose();
					setVisible(true);		
				}
			}
			
			stadioCombo.addActionListener(new stadioComboListener());
			indietro.addActionListener(new IndietroListener());
			return getIncassoStadio;
		}
	}
		
		

		
	private static final long serialVersionUID = 1L;
	private SistemaStadi sistema;
	private static String fileName = "sistemaStadi.dat";
	private URL imgURLOK = getClass().getResource("/it/unisa/prog2/progetto/image/ok.png");
	private URL imgURLnonOK = getClass().getResource("/it/unisa/prog2/progetto/image/image.png");
	
	
	private JMenuBar menu;
	private DefaultFrame def;
	private JPanel descrizione; 
	private JFrame registraAmministratore;
	private JFrame incassoStadio;
	private JFrame incassoTotale;
	private JFrame inserisciStadio;
	private JFrame inserisciEventoCalcistico;
	private JFrame visualizzaEventiCalcisticiPerCapienza;
	private JFrame visualizzaEventiCalcisticiPerData;
	private JFrame modificaPrezzoStadio;
	private JFrame modificaCapienza;
	private JFrame inserisciPoliticaSconto;
	private JFrame rimuoviPoliticaSconto;
	private Stadio stadio;
}
