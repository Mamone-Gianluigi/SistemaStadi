package it.unisa.prog2.progetto.graphics;

import java.awt.*;
import java.awt.event.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URL;

import javax.swing.*;
import javax.swing.border.*;
import it.unisa.prog2.progetto.core.*;
import it.unisa.prog2.progetto.exceptions.*;

/**
 * Il frame che verrà mostrato al momento dell'apertura dell'applicazione, il quale permette agli utenti
 * di effettuare l'accesso al sistema o di registrarsi se non si possiede già un account
 */
public class FrameIniziale extends JFrame{
	
	/**
	 * Crea un nuovo frame mostrando un pannello riguardante il login di un utente, un pannello riguardante la registrazione di un nuovo utente
	 * e una barra dei menu principali.
	 * @param siste il sistema con i dati relativi all'applicazione (account registrati, eventi calcistici...)
	 */
	public FrameIniziale(SistemaStadi siste) {
		
		this.sistema=siste;
		
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(new Point((dimension.width - getSize().width) / 2-150, 
		(dimension.height - getSize().height) / 2 -140));
		setLayout(new BorderLayout());
		setSize(300,280);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setUndecorated(true);
	
		JMenuBar menu=creaMenuBarIniziale();
		add(menu,BorderLayout.NORTH);
		
		JPanel pannelloBenvenuto=new JPanel();
		//JLabel descrizione1=new JLabel("Benvenuti al ");
		//descrizione1.setFont(new Font("Georgia", Font.ITALIC, 20));
		//descrizione1.setForeground(Color.BLACK);
		//JLabel descrizione2=new JLabel(sistema.getNome());
		//descrizione2.setFont(new Font("Georgia", Font.ITALIC, 22));
		//descrizione2.setForeground(Color.RED);
		//pannelloBenvenuto.add(descrizione1);
		//pannelloBenvenuto.add(descrizione2);
		
		
		
		JLabel de=new JLabel();
		de.setIcon(new ImageIcon(img));
		pannelloBenvenuto.add(de);
		
		JPanel userPass=new JPanel();
		userPass.setLayout(new GridLayout(2,2));
		JLabel username=new JLabel("Username");
		JLabel password=new JLabel("Password");
		final JTextField tusername=new JTextField(8);
		final JPasswordField tpassword=new JPasswordField(8);
		tpassword.setToolTipText("*");
		username.setForeground(Color.BLACK);
		tusername.setForeground(Color.BLACK);
		password.setForeground(Color.BLACK);
		tpassword.setForeground(Color.BLACK);
		username.setFont(new Font("Georgia", Font.PLAIN, 18));
		tusername.setFont(new Font("Georgia", Font.PLAIN, 18));
		password.setFont(new Font("Georgia", Font.PLAIN, 18));
		tpassword.setFont(new Font("Georgia", Font.PLAIN, 18));
		username.setHorizontalAlignment(JLabel.CENTER);
		tusername.setHorizontalAlignment(JLabel.CENTER);
		password.setHorizontalAlignment(JLabel.CENTER);
		tpassword.setHorizontalAlignment(JLabel.CENTER);
		userPass.add(username);
		userPass.add(tusername);
		userPass.add(password);
		userPass.add(tpassword);
		
		JPanel bottoni= new JPanel();
		JButton buttonAccedi=new JButton("Accedi");
		buttonAccedi.setFont(new Font("Georgia", Font.PLAIN, 18));
		buttonAccedi.setForeground(Color.BLACK);	
		bottoni.add(buttonAccedi);
		
		JPanel pannelloLoginUtente=new JPanel();
		TitledBorder a=new TitledBorder(new EtchedBorder(), "Login Utente");
		a.setTitleColor(Color.BLACK);
		a.setTitleFont(new Font("Georgia", Font.ITALIC, 13));
		pannelloLoginUtente.setBorder(a);
		pannelloLoginUtente.add(userPass);
		pannelloLoginUtente.add(bottoni);
	
		JPanel pannelLoginDescrizione=new JPanel();
		pannelLoginDescrizione.setLayout(new BorderLayout());
		pannelLoginDescrizione.add(pannelloBenvenuto,BorderLayout.NORTH);
		pannelLoginDescrizione.add(pannelloLoginUtente,BorderLayout.CENTER);		
		
		JPanel pannelloRegistrazione=new JPanel();				
		JLabel messaggio = new JLabel("Sei un nuovo utente?");
		messaggio.setForeground(Color.BLACK);
		final JLabel registratiOra = new JLabel("Registrati ora");
		registratiOra.setForeground(Color.BLACK);
		messaggio.setFont(new Font("Georgia", Font.PLAIN, 18));
		registratiOra.setFont(new Font("Georgia", Font.PLAIN, 18));
		pannelloRegistrazione.add(messaggio);
		pannelloRegistrazione.add(registratiOra);
		TitledBorder a1=new TitledBorder(new EtchedBorder(), "Registrazione Utente");
		a1.setTitleColor(Color.BLACK);
		a1.setTitleFont(new Font("Georgia", Font.ITALIC, 13));
		pannelloRegistrazione.setBorder(a1);
		
		add(pannelLoginDescrizione,BorderLayout.CENTER);
		add(pannelloRegistrazione,BorderLayout.SOUTH);
		
		/**
		 * Il listener per il pulsante Accedi.
		 * Se l'utente vuole confermare i dati inseriti e procedere dunque con l'accesso, preme il tasto Accedi. 
		 * Il listener passa i dati inseriti al sistema e, se sono corretti, presenta un messaggio di accesso riuscito.
		 * Altrimenti, se i dati inseriti non sono corretti, viene visualizzato un messaggio di errore.
		 * 
		 */
		class AccediListener implements ActionListener {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				try
				{
					sistema.loginUtente(tusername.getText(),tpassword.getText());
					dispose();   
					uteLog=new UtenteFrame(sistema);
					def=new DefaultFrame(new JLabel("Accesso riuscito"),
							"Accesso riuscito",340,new ImageIcon(imgURLOK),
						 	uteLog); 
					def.setVisible(true);
				} 
				catch (DatiNonValidiException e1)
				{	  	
					setVisible(false);
					def=new DefaultFrame(new JLabel(e1.getMessage()),
							"Errore",340,new ImageIcon(imgURLnonOK),
							new FrameIniziale(sistema));
					def.setVisible(true);
				}
			}
		}
		
		/**
		 * Serve a creare un link tra la scritta "Registrati ora" (per la registrazione) e il form di registrazione.
		 * Quando il mouse viene cliccato sulla scritta, viene aperto il form per la registrazione di un nuovo
		 * utente.
		 *
		 */
		class RegistratiOraListener implements MouseListener {

			public void mouseClicked(MouseEvent e) {
				creaUtente=creaUtente();
				creaUtente.setVisible(true);
				setVisible(false);
			}
			public void mousePressed(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {}

			/**
			 * Evidenzia la scritta quando l'utente va con il mouse su di essa
			 */
			public void mouseEntered(MouseEvent e) {
				registratiOra.setForeground(Color.BLUE);
			}
			/**
			 * Riporta la scritta al suo colore originale quando l'utente sposta il mouse
			 */
			public void mouseExited(MouseEvent e) {
				registratiOra.setForeground(Color.BLACK);
			}
		}
		buttonAccedi.addActionListener(new AccediListener());
		registratiOra.addMouseListener(new RegistratiOraListener());
	}
	
	/**
	 * Costruisce la barra dei menù iniziale, dove sono presenti soltanto il menù file, per l'uscita dal programma
	 * e il menù Amministratore per l'accesso degli amministratori
	 */
	private JMenuBar creaMenuBarIniziale () {
		JMenuBar barra = new JMenuBar();
		JMenu file;
		JMenu amministratore;

		file = new JMenu("File");
		file.setFont(new Font("Georgia", Font.ITALIC, 13));
		file.setForeground(Color.BLACK);
		
		amministratore = new JMenu("Amministratore");
		amministratore.setFont(new Font("Georgia", Font.ITALIC, 13));
		amministratore.setForeground(Color.BLACK);

		JMenuItem exit = new JMenuItem("Esci");
		JMenuItem loginAmministratore = new JMenuItem("Login amministratore");
		exit.setFont(new Font("Georgia", Font.ITALIC, 13));
		exit.setForeground(Color.BLACK);
		loginAmministratore.setFont(new Font("Georgia", Font.ITALIC, 13));
		loginAmministratore.setForeground(Color.BLACK);
		file.add(exit);
		amministratore.add(loginAmministratore);
		
		barra.add(file);
		barra.add(amministratore);
	
		/**
		 * Il listener per la voce "Login amministratori" del menu Amministratore. Se premuto, apre un frame in cui un amministratore
		 * può inserire i dati per effettuare l'accesso (username e password)
		 */
		class LoginAmministratoreListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				loginAmministratore().setVisible(true);
				setVisible(false);
			}
		}
		
		/**
		 * Il listener della voce Esci del menu File. Se viene selezionata questa voce il programma viene terminato
		 *
		 */
		class ExitListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				FileOutputStream out;
				ObjectOutputStream outStream;
			
				try {
					out = new FileOutputStream(fileName);
					outStream = new ObjectOutputStream(out);
					
					outStream.writeObject(sistema); // Scriviamo sul file lo stato attuale del sistema
					out.close();
					outStream.close();
				} 
				catch (IOException e1) {}
				finally {
					System.exit(0);
				}
			}
		}
		loginAmministratore.addActionListener(new LoginAmministratoreListener());
		exit.addActionListener(new ExitListener());
		return barra;
	}
	
	/**
	 * Crea il form per la registrazione di un nuovo utente
	 */
	private JFrame creaUtente() {
		final JFrame creaUtent=new JFrame();
		
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		creaUtent.setLocation(new Point((dimension.width - creaUtent.getSize().width) / 2-165, 
		(dimension.height - creaUtent.getSize().height) / 2 -135));
		
		creaUtent.setSize(350,270);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		creaUtent.setResizable(false);
		creaUtent.setLayout(new BorderLayout());
		creaUtent.setUndecorated(true);
		
		JPanel pannello=new JPanel();
		pannello.setLayout(new BorderLayout());
		
		JLabel label =new JLabel("Inserisci i dati richiesti");
		label.setFont(new Font("Georgia", Font.PLAIN, 18));
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setForeground(Color.BLACK);
		
		JLabel l=new JLabel("*campi obbligatori  ");
		l.setFont(new Font("Georgia", Font.PLAIN, 13));
		l.setHorizontalAlignment(JLabel.RIGHT);
		l.setForeground(Color.BLACK);
		
		JPanel dati = new JPanel();
		JLabel nome = new JLabel("Nome*");
		JLabel cognome = new JLabel("Cognome*");
		JLabel username = new JLabel("Username*");
		JLabel password = new JLabel("Password*");
		JLabel categoria = new JLabel("Categoria*");
		final JTextField nomeField = new JTextField(8);
		final JTextField cognomeField = new JTextField(8);
		final JTextField usernameField = new JTextField(8);
		final JTextField passwordField = new JTextField(3);
		nome.setFont(new Font("Georgia", Font.PLAIN, 18));
		cognome.setFont(new Font("Georgia", Font.PLAIN, 18));
		username.setFont(new Font("Georgia", Font.PLAIN, 18));
		password.setFont(new Font("Georgia", Font.PLAIN, 18));
		categoria.setFont(new Font("Georgia", Font.PLAIN, 18));
		nomeField.setFont(new Font("Georgia", Font.PLAIN, 18));
		cognomeField.setFont(new Font("Georgia", Font.PLAIN, 18));
		usernameField.setFont(new Font("Georgia", Font.PLAIN, 18));
		passwordField.setFont(new Font("Georgia", Font.PLAIN, 18));
		nome.setHorizontalAlignment(JLabel.CENTER);
		cognome.setHorizontalAlignment(JLabel.CENTER);
		username.setHorizontalAlignment(JLabel.CENTER);
		password.setHorizontalAlignment(JLabel.CENTER);
		categoria.setHorizontalAlignment(JLabel.CENTER);
		nomeField.setHorizontalAlignment(JLabel.CENTER);
		cognomeField.setHorizontalAlignment(JLabel.CENTER);
		usernameField.setHorizontalAlignment(JLabel.CENTER);
		passwordField.setHorizontalAlignment(JLabel.CENTER);
		nome.setForeground(Color.BLACK);
		cognome.setForeground(Color.BLACK);
		username.setForeground(Color.BLACK);
		password.setForeground(Color.BLACK);
		categoria.setForeground(Color.BLACK);
		nomeField.setForeground(Color.BLACK);
		cognomeField.setForeground(Color.BLACK);
		usernameField.setForeground(Color.BLACK);
		passwordField.setForeground(Color.BLACK);
		
	
		final JComboBox<String> categoriaCombo = new JComboBox<String>();
		categoriaCombo.setFont(new Font("Georgia", Font.PLAIN, 18));
		categoriaCombo.setForeground(Color.BLACK);
		categoriaCombo.addItem("Bambino");
		categoriaCombo.addItem("Studente");
		categoriaCombo.addItem("Pensionato");
		categoriaCombo.addItem("Adulto");
		categoriaCombo.setSelectedItem(null);
		
		dati.setLayout(new GridLayout(5, 2));
		dati.add(nome);
		dati.add(nomeField);
		dati.add(cognome);
		dati.add(cognomeField);
		dati.add(username);
		dati.add(usernameField);
		dati.add(password);
		dati.add(passwordField);
		dati.add(categoria);
		dati.add(categoriaCombo);
	
		JPanel bottoni= new JPanel();
		JButton buttonConferma=new JButton("Conferma");
		buttonConferma.setFont(new Font("Georgia", Font.PLAIN, 18));
		buttonConferma.setForeground(Color.BLACK);
		JButton buttonAnnulla=new JButton("Annulla");
		buttonAnnulla.setFont(new Font("Georgia", Font.PLAIN, 18));
		buttonAnnulla.setForeground(Color.BLACK);
		bottoni.add(buttonAnnulla);
		bottoni.add(buttonConferma);
		
		JPanel registrazioneUtente=new JPanel();
		registrazioneUtente.setLayout(new BorderLayout());
		TitledBorder a=new TitledBorder(new EtchedBorder(), "Registrazione Utente");
		a.setTitleFont(new Font("Georgia", Font.ITALIC, 13));
		a.setTitleColor(Color.BLACK);
		registrazioneUtente.setBorder(a);
		registrazioneUtente.add(l,BorderLayout.NORTH);
		registrazioneUtente.add(dati,BorderLayout.CENTER);
		registrazioneUtente.add(bottoni,BorderLayout.SOUTH);
		
		pannello.add(label,BorderLayout.NORTH);
		pannello.add(registrazioneUtente, BorderLayout.CENTER);
		creaUtent.add(pannello,BorderLayout.CENTER);
		
		/**
		 * Il listener per il pulsante Conferma. 
		 * Se l'utente vuole confermare i dati inseriti e procedere dunque con la registrazione, preme il tasto Conferma. 
		 * Il listener passa i dati inseriti al sistema e, se sono corretti, procede con la registrazione dell'utente
		 * presentando un messaggio di registrazione riuscita.
		 * Altrimenti, se i dati inseriti non sono corretti, viene visualizzato un messaggio di errore.
		 *
		 */
		class ButtonConfermaListener implements ActionListener {
			public void actionPerformed (ActionEvent e) {
				try 
				{
					sistema.creaUtente(nomeField.getText(),cognomeField.getText(),
							usernameField.getText(),passwordField.getText()
							,(String)categoriaCombo.getSelectedItem());
					creaUtent.dispose();
					def=new DefaultFrame(new JLabel("Registrazione riuscita"),
							"Registrazione riuscita",340,new ImageIcon(imgURLOK),
							new FrameIniziale(sistema));
					def.setVisible(true);
				}
				catch (DatiNonValidiException e1)
				{
					creaUtent.setVisible(false);
					def=new DefaultFrame(new JLabel(e1.getMessage()),
							"Errore",650,new ImageIcon(imgURLnonOK),
							creaUtent);
					def.setVisible(true);
				}
			}
		}
		
		/**
		 * Il listener per il pulsante Annulla. 
		 * Se l'utente non vuole confermare i dati inseriti, selezionando il pulsante Annulla, 
		 * la finestra riguardante la registrazione del nuovo utente si chiude 
		 * ritornando alla schermate iniziale.
		 *
		 */
		class ButtonAnnullaListener implements ActionListener {
			public void actionPerformed (ActionEvent e) {
				creaUtent.dispose();
				setVisible(true);
			}
		}
		buttonConferma.addActionListener(new ButtonConfermaListener());
		buttonAnnulla.addActionListener(new ButtonAnnullaListener());
		
		return creaUtent;
	}
	
	/**
	 * Crea un frame con i campi per il login di un amministratore
	 */
	private JFrame loginAmministratore(){
		final JFrame loginAmministra=new JFrame();
		
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		loginAmministra.setLocation(new Point((dimension.width - loginAmministra.getSize().width) / 2-175, 
		(dimension.height - loginAmministra.getSize().height) / 2 -100));
		
		loginAmministra.setLayout(new BorderLayout());
		loginAmministra.setSize(350,200);
		loginAmministra.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		loginAmministra.setResizable(false);
		loginAmministra.setUndecorated(true);
		
		loginAmministra.setLayout(new BorderLayout());
		
		JLabel l =new JLabel("Inserisci i dati richiesti");
		l.setFont(new Font("Georgia", Font.PLAIN, 18));
		l.setHorizontalAlignment(JLabel.CENTER);
		l.setForeground(Color.BLACK);
		
		JLabel l1=new JLabel("                                         *campi obbligatori");
		l1.setFont(new Font("Georgia", Font.PLAIN, 13));
		
		l1.setForeground(Color.BLACK);
		
		JPanel datiAmm=new JPanel();
		datiAmm.setLayout(new GridLayout(2,2));
		JLabel usernameAmm=new JLabel("Username");
		JLabel passwordAmm=new JLabel("Password");
		final JTextField tusernameAmm=new JTextField(8);
		final JPasswordField tpasswordAmm=new JPasswordField(8);
		tpasswordAmm.setToolTipText("*");
		usernameAmm.setForeground(Color.BLACK);
		tusernameAmm.setForeground(Color.BLACK);
		passwordAmm.setForeground(Color.BLACK);
		tpasswordAmm.setForeground(Color.BLACK);
		usernameAmm.setFont(new Font("Georgia", Font.PLAIN, 18));
		tusernameAmm.setFont(new Font("Georgia", Font.PLAIN, 18));
		passwordAmm.setFont(new Font("Georgia", Font.PLAIN, 18));
		tpasswordAmm.setFont(new Font("Georgia", Font.PLAIN, 18));
		usernameAmm.setHorizontalAlignment(JLabel.CENTER);
		tusernameAmm.setHorizontalAlignment(JLabel.CENTER);
		passwordAmm.setHorizontalAlignment(JLabel.CENTER);
		tpasswordAmm.setHorizontalAlignment(JLabel.CENTER);
		datiAmm.add(usernameAmm);
		datiAmm.add(tusernameAmm);
		datiAmm.add(passwordAmm);
		datiAmm.add(tpasswordAmm);
		
		JPanel bottoni=new JPanel();
		JButton buttonLogin=new JButton("Accedi");
		buttonLogin.setFont(new Font("Georgia", Font.PLAIN, 18));
		buttonLogin.setForeground(Color.BLACK);
		JButton buttonAnnulla=new JButton("Annulla");
		buttonAnnulla.setFont(new Font("Georgia", Font.PLAIN, 18));
		buttonAnnulla.setForeground(Color.BLACK);
		bottoni.add(buttonAnnulla);
		bottoni.add(buttonLogin);
		
		JPanel loginAmmPanel= new JPanel();
		TitledBorder a=new TitledBorder(new EtchedBorder(), "Login Amministratore");
		a.setTitleColor(Color.BLACK);
		a.setTitleFont(new Font("Georgia", Font.ITALIC, 13));
		loginAmmPanel.setBorder(a);
		loginAmmPanel.add(l1);
		loginAmmPanel.add(datiAmm);
		loginAmmPanel.add(bottoni);
		
		JPanel pannello=new JPanel();
		pannello.setLayout(new BorderLayout());
		pannello.add(l,BorderLayout.NORTH);
		pannello.add(loginAmmPanel,BorderLayout.CENTER);
		loginAmministra.add(pannello,BorderLayout.CENTER);
		
		/**
		 * Il listener per il pulsante Annulla. 
		 * Se l'amministratore non vuole confermare i dati inseriti, selezionando il pulsante Annulla, 
		 * la finestra riguardante il login dell'amministratore si chiude 
		 * ritornando alla schermate iniziale.
		 *
		 */
		class AnnullaListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				loginAmministra.dispose();
				setVisible(true);
			}
		}
		
		/**
		 * Il listener per il pulsante Accedi. 
		 * Se l'amministratore vuole confermare i dati inseriti e procedere dunque con l'accesso, preme il tasto Accedi. 
		 * Il listener passa i dati inseriti al sistema e, se sono corretti, procede con l'accesso dell'amministratore
		 * presentando un messaggio di accesso riuscito.
		 * Altrimenti, se i dati inseriti non sono corretti, viene visualizzato un messaggio di errore.
		 *
		 */
		class LoginListener implements ActionListener {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				try 
				{
					sistema.loginAmministratore(tusernameAmm.getText(),tpasswordAmm.getText());
					logAmm=new AmministratoreFrame(sistema);
					
					loginAmministra.dispose();
					def=new DefaultFrame(new JLabel("Accesso riuscito"),
							"Accesso riuscito",340,new ImageIcon(imgURLOK),
							logAmm);
					def.setVisible(true);
				}
				catch (DatiNonValidiException e1)
				{ 
					loginAmministra.dispose();
					def=new DefaultFrame(new JLabel(e1.getMessage()),
							"Errore",340,new ImageIcon(imgURLnonOK),
							loginAmministratore());
					def.setVisible(true);
				}
			}
		}
		
		buttonAnnulla.addActionListener(new AnnullaListener());
		buttonLogin.addActionListener(new LoginListener());
		return loginAmministra;
	}

	private static final long serialVersionUID = 1L;
	private SistemaStadi sistema;
	private static String fileName = "sistemaStadi.dat";
	private JFrame creaUtente;
	private AmministratoreFrame logAmm;
	private UtenteFrame uteLog;
	private DefaultFrame def;
	private URL imgURLOK = getClass().getResource("/it/unisa/prog2/progetto/image/ok.png");
	private URL imgURLnonOK = getClass().getResource("/it/unisa/prog2/progetto/image/image.png");
	private URL img = getClass().getResource("/it/unisa/prog2/progetto/image/Logocountry.png");

}
