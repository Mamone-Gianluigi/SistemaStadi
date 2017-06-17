package it.unisa.prog2.progetto.graphics;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.swing.*;
import javax.swing.Timer;
import javax.swing.border.*;
import it.unisa.prog2.progetto.core.*;
import it.unisa.prog2.progetto.exceptions.*;

/**
 * Il frame di default che verr√† visualizzato in caso di errori, registrazione, ecc.
 * La classe crea soltanto un frame con sfondo verde e un rettangolo bianco al centro del frame.
 * Nel rettangolo bianco verranno inseriti i dati (messaggio d'errore, form per la registrazione, ecc...)
 */
public class UtenteFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private SistemaStadi sistema;
	private static String fileName = "sistemaStadi.dat";
	private URL ometto = getClass().getResource("/it/unisa/prog2/progetto/image/ometto.png");
	private URL sport = getClass().getResource("/it/unisa/prog2/progetto/image/sport.png");
	private URL country = getClass().getResource("/it/unisa/prog2/progetto/image/country.png");
	private URL avellino = getClass().getResource("/it/unisa/prog2/progetto/image/avellino.png");

	private URL imgURLOK = getClass().getResource("/it/unisa/prog2/progetto/image/ok.png");
	private URL imgURLnonOK = getClass().getResource("/it/unisa/prog2/progetto/image/image.png");
	private DefaultFrame def;
	private JFrame eventiCalcisticiSettimana; 
	private JFrame eventiCalcisticiStadio; 
	private JFrame acquistaBiglietto; 
	private JFrame scegliPosto; 
	private JFrame acquistaCredito; 
	private JFrame storicoAcquisti;
	private JFrame prenotaBiglietto;
	private JFrame storicoPrenotazioni;
	private JFrame eventiCronologico;
	private JFrame eventiId;
	private JFrame eventiLessico;
	private Timer t;
	private PostoColorato ppp;

	public UtenteFrame (SistemaStadi siste) {
		
		this.sistema=siste;
		
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(new Point((dimension.width - getSize().width) / 2-350, 
		(dimension.height - getSize().height) / 2 -250));
		
		setSize(700,500);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setUndecorated(true);
		setLayout(new BorderLayout());
		
		JMenuBar menu=creaMenuBarIniziale();
		add(menu,BorderLayout.NORTH);
		
		JPanel pannelloTitolo=new JPanel();
		JLabel descrizione1=new JLabel("Benvenuto ");
		descrizione1.setFont(new Font("Georgia", Font.ITALIC, 20));
		descrizione1.setForeground(Color.BLACK);
		String nome=sistema.getUtenteCorrente().getNome();
		JLabel descrizione2=new JLabel(nome);
		descrizione2.setFont(new Font("Georgia", Font.ITALIC, 22));
		descrizione2.setForeground(Color.RED);
		pannelloTitolo.add(descrizione1);
		pannelloTitolo.add(descrizione2);
		
		JPanel pannelloCredito=new JPanel();
		JLabel credito=new JLabel("Il tuo credito Ë : ");
		credito.setFont(new Font("Georgia", Font.ITALIC, 20));
		credito.setForeground(Color.BLACK);
		DecimalFormat df = new DecimalFormat("#.##"); // 2 cifre decimali

		String s = df.format(sistema.getUtenteCorrente().getCredito());
		
		JLabel descrizione3=new JLabel(s+"  Ä");
		descrizione3.setFont(new Font("Georgia", Font.ITALIC, 22));
		descrizione3.setForeground(Color.BLUE);
		pannelloCredito.add(credito);
		pannelloCredito.add(descrizione3);
		
		JPanel titoloCredito=new JPanel();
		titoloCredito.setLayout(new GridLayout(2,1));
		titoloCredito.add(pannelloTitolo);
		titoloCredito.add(pannelloCredito);
		
		JPanel bottoni=new JPanel();
		bottoni.setLayout(new GridLayout(7,1));
		JPanel pannelloAcquisto=new JPanel();
		JButton acquisto=new JButton("Acquista biglietto");
		acquisto.setFont(new Font("Georgia", Font.ITALIC, 18));
		acquisto.setForeground(Color.BLACK);
		pannelloAcquisto.add(acquisto);
		JPanel pannelloPrenotazione=new JPanel();
		JButton prenotazione=new JButton("Prenota biglietto");
		prenotazione.setFont(new Font("Georgia", Font.ITALIC, 18));
		prenotazione.setForeground(Color.BLACK);
		pannelloPrenotazione.add(prenotazione);
		JPanel pannelloAcquistaCredit=new JPanel();
		JButton acquistaCredi=new JButton("Acquista credito");
		acquistaCredi.setFont(new Font("Georgia", Font.ITALIC, 18));
		acquistaCredi.setForeground(Color.BLACK);
		pannelloAcquistaCredit.add(acquistaCredi);
		bottoni.add(pannelloAcquisto);
		bottoni.add(pannelloPrenotazione);
		bottoni.add(pannelloAcquistaCredit);
		JLabel la=new JLabel();
		la.setIcon(new ImageIcon(country));
		la.setHorizontalAlignment(JLabel.CENTER);
		
		JLabel la1=new JLabel();
		la1.setIcon(new ImageIcon(sport));
		la1.setHorizontalAlignment(JLabel.CENTER);
		
		JLabel la2=new JLabel();
		la2.setIcon(new ImageIcon(avellino));
		la2.setHorizontalAlignment(JLabel.CENTER);
		
		JLabel la3=new JLabel();
		la3.setIcon(new ImageIcon(ometto));
		la3.setHorizontalAlignment(JLabel.CENTER);
		
		bottoni.add(la);
		bottoni.add(la1);
		bottoni.add(la2);
		bottoni.add(la3);
		JLabel infoCarrello =new JLabel("Carrello delle prenotazioni in corso");
		infoCarrello.setFont(new Font("Georgia", Font.PLAIN, 18));
		infoCarrello.setHorizontalAlignment(JLabel.CENTER);
		infoCarrello.setForeground(Color.BLACK);
		
		JList<String> listaPolitiche;
		DefaultListModel<String> listModel;
		listModel = new DefaultListModel<>();
		listaPolitiche = new JList<String>(listModel);
		listaPolitiche.setLayout(null);	
		JScrollPane scrollPane = new JScrollPane(listaPolitiche,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		listModel.removeAllElements();
		listaPolitiche.setFont(new Font("Georgia", Font.PLAIN, 18));
		listaPolitiche.setForeground(Color.BLACK);
		System.out.println("cia");
		System.out.println("carrello : "+sistema.getCarrelloUtenteCorrente().size());
		for(Prenotazione pre: sistema.getCarrelloUtenteCorrente()){
			SimpleDateFormat sdf =new SimpleDateFormat("dd/MM/yyyy");
			String a1=sdf.format(pre.getDataPrenotazione().getTime());
			SimpleDateFormat sdf1 =new SimpleDateFormat("HH:mm:ss");
			String a2=sdf1.format(pre.getDataPrenotazione().getTime());
			SimpleDateFormat sdf2 =new SimpleDateFormat("dd/MM/yyyy-HH:mm:ss");
			String a3=sdf2.format(pre.getScadenza().getTime());
		
			String a="Stadio:"+pre.getEventoCalcistico().getStadio().getNomeStadio()
						+"-SquadraCasa:"+pre.getEventoCalcistico().getPartita().getSquadraCasa()
						+"-SquadraOspite:"+pre.getEventoCalcistico().getPartita().getSquadraOspite()
						+"-SquadraOspite:"+pre.getEventoCalcistico().getPartita().getSquadraOspite()
						+"Settore:"+pre.getSettore().getNomeSettore()
						+"Posto:"+pre.getPosto().getLettera()+"-"+pre.getPosto().getNumero()
						+"Inizio:"+a1+"-"+a2
						+"Scadenza:"+a3;
			listModel.addElement(a);
		}
		//System.out.println("Lista : "+listModel.size());
		JPanel bottoniCarrello= new JPanel();
		JButton buttonAcquista=new JButton("Acquista");
		buttonAcquista.setFont(new Font("Georgia", Font.PLAIN, 18));
		buttonAcquista.setForeground(Color.BLACK);
		JButton buttonSvuota=new JButton("Svuota carrello");
		buttonSvuota.setFont(new Font("Georgia", Font.PLAIN, 18));
		buttonSvuota.setForeground(Color.BLACK);
		JButton buttonElimina=new JButton("Elimina");
		buttonElimina.setFont(new Font("Georgia", Font.PLAIN, 18));
		buttonElimina.setForeground(Color.BLACK);
		bottoniCarrello.add(buttonSvuota);
		bottoniCarrello.add(buttonAcquista);
		bottoniCarrello.add(buttonElimina);
		
		if(listModel.size()==0){
			buttonAcquista.setVisible(false);
			buttonSvuota.setVisible(false);
			buttonElimina.setVisible(false);
		}
		else{
			buttonAcquista.setVisible(true);
			buttonSvuota.setVisible(true);
			buttonElimina.setVisible(true);
		}

		JPanel pannelloCarrello=new JPanel();
		pannelloCarrello.setLayout(new BorderLayout());
		TitledBorder a5=new TitledBorder(new EtchedBorder(),"Carrello prenotazioni");
		a5.setTitleFont(new Font("Georgia", Font.ITALIC, 13));
		a5.setTitleColor(Color.BLACK);
		pannelloCarrello.setBorder(a5);
		pannelloCarrello.add(infoCarrello,BorderLayout.NORTH);
		pannelloCarrello.add(scrollPane,BorderLayout.CENTER);
		pannelloCarrello.add(bottoniCarrello,BorderLayout.SOUTH);
		
		JPanel pannello=new JPanel();
		pannello.setLayout(new BorderLayout());
		pannello.add(titoloCredito,BorderLayout.NORTH);
		pannello.add(bottoni,BorderLayout.WEST);
		pannello.add(pannelloCarrello,BorderLayout.CENTER);
		add(pannello);
		
		class CountDown implements ActionListener {
			public void actionPerformed(ActionEvent event){
				if(sistema.getCarrelloUtenteCorrente()!=null){
					int i=sistema.getCarrelloUtenteCorrente().size();
					sistema.controllaScadenze(); 
					int j=sistema.getCarrelloUtenteCorrente().size();
					System.out.print("prima : "+i+" - dopo : "+j);
			
					listModel.removeAllElements();
					for(Prenotazione pre: sistema.getCarrelloUtenteCorrente()){
						SimpleDateFormat sdf =new SimpleDateFormat("dd/MM/yyyy");
						String a1=sdf.format(pre.getDataPrenotazione().getTime());
						SimpleDateFormat sdf1 =new SimpleDateFormat("HH:mm:ss");
						String a2=sdf1.format(pre.getDataPrenotazione().getTime());
						SimpleDateFormat sdf2 =new SimpleDateFormat("dd/MM/yyyy-HH:mm:ss");
						String a3=sdf2.format(pre.getScadenza().getTime());
						
						String a="Stadio:"+pre.getEventoCalcistico().getStadio().getNomeStadio()
							+"-SquadraCasa:"+pre.getEventoCalcistico().getPartita().getSquadraCasa()
							+"-SquadraOspite:"+pre.getEventoCalcistico().getPartita().getSquadraOspite()
							+"-SquadraOspite:"+pre.getEventoCalcistico().getPartita().getSquadraOspite()
							+"Settore:"+pre.getSettore().getNomeSettore()
							+"Posto:"+pre.getPosto().getLettera()+"-"+pre.getPosto().getNumero()
							+"Inizio:"+a1+"-"+a2
							+"Scadenza:"+a3;
							listModel.addElement(a);
					}
				}
			}
		}
		
		ActionListener listener = new CountDown();
		//t = new Timer(300000, listener); 
		t = new Timer(30000, listener); 
		t.start();
		
		class AcquistaBigliettoListener implements ActionListener {
			public void actionPerformed (ActionEvent e) {
				setVisible(false);
				try{
					acquistaBiglietto=acquistaBiglietto();
					acquistaBiglietto.setVisible(true);
				} catch (DatiNonValidiException e1) {
					setVisible(false);
					def=new DefaultFrame(new JLabel(e1.getMessage()),
							"Errore",650,new ImageIcon(imgURLnonOK),
							new UtenteFrame(sistema));
					def.setVisible(true);
				}
			}
		}
		class AcquistaCreditoListener implements ActionListener {
			public void actionPerformed (ActionEvent e) {
				setVisible(false);
				acquistaCredito=acquistaCredito();
				acquistaCredito.setVisible(true);
			}
		}
		
		class PrenotaBigliettoListener implements ActionListener {
			public void actionPerformed (ActionEvent e) {
				setVisible(false);
				try {
					prenotaBiglietto=prenotaBiglietto();
					prenotaBiglietto.setVisible(true);
				} catch (DatiNonValidiException e1) {
					setVisible(false);
					def=new DefaultFrame(new JLabel(e1.getMessage()),
							"Errore",650,new ImageIcon(imgURLnonOK),
							new UtenteFrame(sistema));
					def.setVisible(true);
				}
				
				
			}
		}
		
		class AcquistaListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				try
				{
					if (listModel.size() > 0) { 
						int index;
						index=listaPolitiche.getSelectedIndex();
						System.out.println(index);
						System.out.println("ciao");
						try {
							sistema.acquistaPrenotazione(index);
						} catch (PostoIndisponibileException e1) {
							return;
						}
						
						listModel.removeElementAt(index);
						
						if(listModel.size()==0){
							buttonAcquista.setVisible(false);
							buttonSvuota.setVisible(false);
							buttonElimina.setVisible(false);
						}
						else{
							buttonAcquista.setVisible(true);
							buttonSvuota.setVisible(true);
							buttonElimina.setVisible(true);
						}
						setVisible(false);
						def=new DefaultFrame(new JLabel("La prenotazione Ë stata acquistata"),
								"Prenotazione Acquistata",450,new ImageIcon(imgURLOK),
								new UtenteFrame(sistema));
						def.setVisible(true);
					}
				}
				catch(ArrayIndexOutOfBoundsException e1)
				{
					setVisible(false);
					def=new DefaultFrame(new JLabel("Selezionare una prenotazione"),
							"Selezionare prenotazione",450,new ImageIcon(imgURLnonOK),
							new UtenteFrame(sistema));
					def.setVisible(true);
				} 
				catch (DatiNonValidiException e1) 
				{
					setVisible(false);
					def=new DefaultFrame(new JLabel(e1.getMessage()),
						"Errore",450,new ImageIcon(imgURLnonOK),
						new UtenteFrame(sistema));
					def.setVisible(true);
				}
				
			}
		}	
		
		class SvuotaCarrelloListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				try{
					sistema.acquistaTutteLePrenotazioni();
					System.out.println("fatto");
					listModel.removeAllElements();
					dispose();
					def=new DefaultFrame(new JLabel("Tutte le prenotazioni sono state acquistate"),
						"Prenotazioni acquistate",450,new ImageIcon(imgURLOK),
						new UtenteFrame(sistema));
					def.setVisible(true);
				}
				catch (DatiNonValidiException | PostoIndisponibileException e1) 
				{
					setVisible(false);
					def=new DefaultFrame(new JLabel(e1.getMessage()),
						"Errore",450,new ImageIcon(imgURLnonOK),
						new UtenteFrame(sistema));
					def.setVisible(true);
				}
			}
		}	
		
		class EliminaListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				try
				{
					if (listModel.size() > 0) { 
						int index=listaPolitiche.getSelectedIndex();
				
						sistema.rimuoviPrenotazione(index);
						listModel.removeElementAt(index);
						if(listModel.size()==0){
							buttonAcquista.setVisible(false);
							buttonSvuota.setVisible(false);
							buttonElimina.setVisible(false);
						}
						else{
							buttonAcquista.setVisible(true);
							buttonSvuota.setVisible(true);
							buttonElimina.setVisible(true);
						}
						setVisible(false);
						def=new DefaultFrame(new JLabel("La prenotazione Ë stata eliminata"),
								"Prenotazione eliminata",450,new ImageIcon(imgURLOK),
								new UtenteFrame(sistema));
						def.setVisible(true);
					}
				}
				catch(ArrayIndexOutOfBoundsException e1)
				{
					setVisible(false);
					def=new DefaultFrame(new JLabel("Selezionare una prenotazione"),
							"Selezionare prenotazione",450,new ImageIcon(imgURLnonOK),
							new UtenteFrame(sistema));
					def.setVisible(true);
				} 				
			}
		}
		
		buttonElimina.addActionListener(new EliminaListener());
		buttonAcquista.addActionListener(new AcquistaListener());
		buttonSvuota.addActionListener(new SvuotaCarrelloListener());
		prenotazione.addActionListener(new PrenotaBigliettoListener());
		acquistaCredi.addActionListener(new AcquistaCreditoListener());
		acquisto.addActionListener(new AcquistaBigliettoListener());
	}
	
	private JMenuBar creaMenuBarIniziale () {
		JMenuBar barra = new JMenuBar();
		JMenu file;
		JMenu visualizza;

		file = new JMenu("File");
		file.setFont(new Font("Georgia", Font.ITALIC, 13));
		file.setForeground(Color.BLACK);
		visualizza = new JMenu("Visualizza");
		visualizza.setFont(new Font("Georgia", Font.ITALIC, 13));
		visualizza.setForeground(Color.BLACK);
	
		JMenuItem exit = new JMenuItem("Esci");
		JMenuItem logOut = new JMenuItem("LogOut");
		exit.setForeground(Color.BLACK);
		exit.setFont(new Font("Georgia", Font.ITALIC, 13));
		logOut.setFont(new Font("Georgia", Font.ITALIC, 13));
		logOut.setForeground(Color.BLACK);
		file.add(logOut);
		file.add(exit);
		
		JMenuItem eventiCalcisticiDiUnaStadio = new JMenuItem("Eventi calcistici di uno Stadio");
		eventiCalcisticiDiUnaStadio.setForeground(Color.BLACK);
		eventiCalcisticiDiUnaStadio.setFont(new Font("Georgia", Font.ITALIC, 13));	
		JMenuItem eventiCalcisticiDiUnaSettimana = new JMenuItem("Eventi calcistici di una Settimana");
		eventiCalcisticiDiUnaSettimana.setForeground(Color.BLACK);
		eventiCalcisticiDiUnaSettimana.setFont(new Font("Georgia", Font.ITALIC, 13));
		JMenu eventiCalcisticiNonIniziati=new JMenu("Eventi calcistici non iniziati");
		eventiCalcisticiNonIniziati.setForeground(Color.BLACK);
		eventiCalcisticiNonIniziati.setFont(new Font("Georgia", Font.ITALIC, 13));
		JMenuItem eventiCalcisticiNonIniziatiCronologici = new JMenuItem("In ordine cronologico");
		eventiCalcisticiNonIniziatiCronologici.setForeground(Color.BLACK);
		eventiCalcisticiNonIniziatiCronologici.setFont(new Font("Georgia", Font.ITALIC, 13));
		JMenuItem eventiCalcisticiId = new JMenuItem("In ordine crescende rispetto agli Id degli stadi");
		eventiCalcisticiId.setForeground(Color.BLACK);
		eventiCalcisticiId.setFont(new Font("Georgia", Font.ITALIC, 13));
		JMenuItem eventiCalcisticiLessico = new JMenuItem("In ordine lessicografico crescente rispetto al nome delle squadre");
		eventiCalcisticiLessico.setForeground(Color.BLACK);
		eventiCalcisticiLessico.setFont(new Font("Georgia", Font.ITALIC, 13));
		JMenuItem storicoAc = new JMenuItem("Storico degli acquisti");
		storicoAc.setForeground(Color.BLACK);
		storicoAc.setFont(new Font("Georgia", Font.ITALIC, 13));
		JMenuItem storicoPre = new JMenuItem("Storico delle prenotazioni");
		storicoPre.setForeground(Color.BLACK);
		storicoPre.setFont(new Font("Georgia", Font.ITALIC, 13));
		visualizza.add(eventiCalcisticiDiUnaStadio);
		visualizza.add(eventiCalcisticiDiUnaSettimana);
		eventiCalcisticiNonIniziati.add(eventiCalcisticiNonIniziatiCronologici);
		eventiCalcisticiNonIniziati.add(eventiCalcisticiId);
		eventiCalcisticiNonIniziati.add(eventiCalcisticiLessico);
		visualizza.add(eventiCalcisticiNonIniziati);
		visualizza.add(storicoAc);
		visualizza.add(storicoPre);
		
		barra.add(file);
		barra.add(visualizza);
		
		class LogOutListener implements ActionListener {	
			public void actionPerformed(ActionEvent e) {
					t.stop();
					sistema.logout();
					dispose();
					new FrameIniziale(sistema).setVisible(true);
					
					
			}
		}
		
		class ExitListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				FileOutputStream out;
				ObjectOutputStream outStream;
				try {
					out = new FileOutputStream(fileName);
					outStream = new ObjectOutputStream(out);
					
					outStream.writeObject(sistema); //
					out.close();
					outStream.close();
				} 
				catch (IOException e1) {}
				finally {
					System.exit(0);
				}
			}
		}
		
		class EventiCalcisticiDiUnaStadio implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				try {
					eventiCalcisticiStadio=eventiCalcisticiDiUnoStadio();
					eventiCalcisticiStadio.setVisible(true);
				} catch (DatiNonValidiException e1) {
					dispose();
					def=new DefaultFrame(new JLabel(e1.getMessage()),
							"Errore",650,
							new ImageIcon(imgURLnonOK),
							new UtenteFrame(sistema));
					def.setVisible(true);
				}
				
			}
		}
		
		class EventiCalcisticiDiUnaSettimanaListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				try {
					eventiCalcisticiSettimana=eventiCalcisticiPerSettimana();
					eventiCalcisticiSettimana.setVisible(true);
				} catch (DatiNonValidiException e1) {
					dispose();
					def=new DefaultFrame(new JLabel(e1.getMessage()),
							"Errore",650,
							new ImageIcon(imgURLnonOK),
							new UtenteFrame(sistema));
					def.setVisible(true);
				}
				
			}
		}
		
		class CronologicoListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				try 
				{
					try {
						eventiCronologico=eventiCalcisticiNonIniziatiCronolo();
						eventiCronologico.setVisible(true);
					}
					catch (DatiNonValidiException e1) {
						dispose();
						def=new DefaultFrame(new JLabel(e1.getMessage()),
								"Errore",650,
								new ImageIcon(imgURLnonOK),
								new UtenteFrame(sistema));
						def.setVisible(true);
					}
				} catch (CloneNotSupportedException e1) {
					return;
				}

			}
		}
		
		class IdListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				try 
				{
					eventiId=eventiCalcisticiNonIniziatiId();
					eventiId.setVisible(true);
				} catch (CloneNotSupportedException e1) {
					return;
				} catch (DatiNonValidiException e1) {
					dispose();
					def=new DefaultFrame(new JLabel(e1.getMessage()),
							"Errore",650,
							new ImageIcon(imgURLnonOK),
							new UtenteFrame(sistema));
					def.setVisible(true);
				}
				
			}
		}
		
		class LessicoListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				try 
				{
					try {
						eventiLessico=eventiCalcisticiNonIniziatiLessico();
						eventiLessico.setVisible(true);
					} catch (DatiNonValidiException e1) {
						dispose();
						def=new DefaultFrame(new JLabel(e1.getMessage()),
								"Errore",650,
								new ImageIcon(imgURLnonOK),
								new UtenteFrame(sistema));
						def.setVisible(true);
					}
				} catch (CloneNotSupportedException e1) {
					return;
				}
				
			}
		}
		
		class StoricoAcquistiListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				try {
					storicoAcquisti=visualizzaAcquisti();
					storicoAcquisti.setVisible(true);
				} catch (DatiNonValidiException e1) {
					dispose();
					def=new DefaultFrame(new JLabel(e1.getMessage()),
							"Errore",650,
							new ImageIcon(imgURLnonOK),
							new UtenteFrame(sistema));
					def.setVisible(true);
				}
				
			}
		}
		
		class StoricoPrenotazioniListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				try {
					storicoPrenotazioni=visualizzaPrenotazioni();
					storicoPrenotazioni.setVisible(true);
				} catch (DatiNonValidiException e1) {
					dispose();
					def=new DefaultFrame(new JLabel(e1.getMessage()),
							"Errore",650,
							new ImageIcon(imgURLnonOK),
							new UtenteFrame(sistema));
					def.setVisible(true);
				}
				
			}
		}
		
		eventiCalcisticiNonIniziatiCronologici.addActionListener(new CronologicoListener());
		eventiCalcisticiId.addActionListener(new IdListener());
		eventiCalcisticiLessico.addActionListener(new LessicoListener());
		storicoPre.addActionListener(new StoricoPrenotazioniListener());
		storicoAc.addActionListener(new StoricoAcquistiListener());
		eventiCalcisticiDiUnaStadio.addActionListener(new EventiCalcisticiDiUnaStadio());
		eventiCalcisticiDiUnaSettimana.addActionListener(new EventiCalcisticiDiUnaSettimanaListener());
		exit.addActionListener(new ExitListener());
		logOut.addActionListener(new LogOutListener());
		
		
		return barra;
	}
	
	public JFrame acquistaBiglietto() throws DatiNonValidiException{
		
		
		if(sistema.getEventiCalcistici().size()==0)
			throw new DatiNonValidiException("Non ci sono eventi calcistici nel "+sistema.getNome());
		else if(sistema.getEventiNonIniziati().size()==0)
			throw new DatiNonValidiException("Non ci sono eventi calcistici non ancora iniziati nel "+sistema.getNome());
		else{
		
			JFrame acquiBiglietto=new JFrame();		
			
			Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
			acquiBiglietto.setLocation(new Point((dimension.width - 
					acquiBiglietto.getSize().width) / 2-200, 
			(dimension.height - acquiBiglietto.getSize().height) / 2 -150));
			
			acquiBiglietto.setSize(400,300);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			acquiBiglietto.setResizable(false);
			acquiBiglietto.setLayout(new BorderLayout());
			acquiBiglietto.setUndecorated(true);
			
			JLabel scegli =new JLabel("Scegli evento calcistico");
			scegli.setFont(new Font("Georgia", Font.PLAIN, 18));
			scegli.setHorizontalAlignment(JLabel.CENTER);
			scegli.setForeground(Color.BLACK);
			
			JComboBox<String> eventiCombo = new JComboBox<String>();
			eventiCombo.setFont(new Font("Georgia", Font.PLAIN, 18));
			ArrayList<EventoCalcistico> eventi=sistema.getEventiNonIniziati();
			for(int i=0; i<eventi.size(); i++){
				
				SimpleDateFormat sdf =new SimpleDateFormat("dd/MM/yyyy-HH:mm:ss");
				String a=sdf.format(eventi.get(i).getDataInizioEventoCalcistico().getTime());
				
				eventiCombo.addItem(
						(i+1)+"-"+eventi.get(i).getPartita().getSquadraCasa()
						+"-"+eventi.get(i).getPartita().getSquadraOspite()+"-"
						+a);
			}
			eventiCombo.setSelectedItem(null);
			
			JPanel sopra =new JPanel();
			sopra.add(scegli);
			sopra.add(eventiCombo);
			
			
			TitledBorder t=new TitledBorder(new EtchedBorder(), "Seleziona evento calcistico");
			t.setTitleFont(new Font("Georgia", Font.ITALIC, 13));
			t.setTitleColor(Color.BLACK);
			sopra.setBorder(t);
			
			JPanel pannello=new JPanel();
			pannello.setLayout(new GridLayout(3,1));
			
			TitledBorder t1=new TitledBorder(new EtchedBorder(), "Acquista biglietto");
			t1.setTitleFont(new Font("Georgia", Font.ITALIC, 13));
			t1.setTitleColor(Color.BLACK);
			pannello.setBorder(t1);
			
			JRadioButton sett1Button = new JRadioButton();
			JRadioButton sett2Button = new JRadioButton();
			JRadioButton sett3Button = new JRadioButton();
			JRadioButton sett4Button = new JRadioButton();
			JRadioButton sett5Button = new JRadioButton();
			JRadioButton sett6Button = new JRadioButton();
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
				
			JPanel riga2=new JPanel();
			JLabel label2=new JLabel("Scegli settore");
			label2.setFont(new Font("Georgia", Font.PLAIN, 18));
			label2.setForeground(Color.BLACK);
			riga2.add(label2);
			riga2.add(sett1Button);
			riga2.add(sett2Button);
			riga2.add(sett3Button);
			riga2.add(sett4Button);
			riga2.add(sett5Button);
			riga2.add(sett6Button);
			
			sett1Button.setVisible(false);
			sett2Button.setVisible(false);
			sett3Button.setVisible(false);
			sett4Button.setVisible(false);
			sett5Button.setVisible(false);
			sett6Button.setVisible(false);
			riga2.setVisible(false);
			
			JPanel riga3=new JPanel();
			JButton posto=new JButton("Scegli posto");
			posto.setFont(new Font("Georgia", Font.ITALIC, 18));
			posto.setForeground(Color.BLACK);
			
			posto.setVisible(false);
			
			pannello.add(sopra);
			pannello.add(riga2);
			pannello.add(riga3);
			JButton indietro = new JButton("Indietro");
			indietro.setFont(new Font("Georgia", Font.PLAIN, 18));
			indietro.setForeground(Color.BLACK);
	
			JPanel a1=new JPanel();
			JPanel a2=new JPanel();
			a1.add(posto);
			a2.add(indietro);
			
			riga3.setLayout(new GridLayout(2,1));
			riga3.add(a1);
			riga3.add(a2);
	
			acquiBiglietto.add(pannello);
			
			class IndietroListener implements ActionListener {
				public void actionPerformed (ActionEvent e) {				
					acquistaBiglietto.dispose();
					setVisible(true);
				}
			}
			
			class EventiComboListener implements ActionListener {
				public void actionPerformed (ActionEvent e) {				
					
					String riga11 = (String) eventiCombo.getSelectedItem();
					int j=riga11.indexOf("-");
					String num1=riga11.substring(0,j);
					int numero1 =Integer.parseInt(num1);
					
					EventoCalcistico eve=eventi.get(numero1-1);
					
					if(eve.getStadio().getSettori().size()==1){
						ButtonGroup group = new ButtonGroup();
						sett1Button.setText(eve.getStadio().getSettori().get(0).getNomeSettore());
						group.add(sett1Button);
						sett1Button.setVisible(true);
						sett2Button.setVisible(false);
						sett3Button.setVisible(false);
						sett4Button.setVisible(false);
						sett5Button.setVisible(false);
						sett6Button.setVisible(false);
						group.clearSelection();
					}
					else if(eve.getStadio().getSettori().size()==2){
						ButtonGroup group = new ButtonGroup();
						sett1Button.setText(eve.getStadio().getSettori().get(0).getNomeSettore());
						sett2Button.setText(eve.getStadio().getSettori().get(1).getNomeSettore());
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
					else if(eve.getStadio().getSettori().size()==3){
						ButtonGroup group = new ButtonGroup();
						sett1Button.setText(eve.getStadio().getSettori().get(0).getNomeSettore());
						sett2Button.setText(eve.getStadio().getSettori().get(1).getNomeSettore());
						sett3Button.setText(eve.getStadio().getSettori().get(2).getNomeSettore());
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
					else if(eve.getStadio().getSettori().size()==4){
						ButtonGroup group = new ButtonGroup();
						sett1Button.setText(eve.getStadio().getSettori().get(0).getNomeSettore());
						sett2Button.setText(eve.getStadio().getSettori().get(1).getNomeSettore());
						sett3Button.setText(eve.getStadio().getSettori().get(2).getNomeSettore());
						sett4Button.setText(eve.getStadio().getSettori().get(3).getNomeSettore());
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
					else if(eve.getStadio().getSettori().size()==5){
						ButtonGroup group = new ButtonGroup();
						sett1Button.setText(eve.getStadio().getSettori().get(0).getNomeSettore());
						sett2Button.setText(eve.getStadio().getSettori().get(1).getNomeSettore());
						sett3Button.setText(eve.getStadio().getSettori().get(2).getNomeSettore());
						sett4Button.setText(eve.getStadio().getSettori().get(3).getNomeSettore());
						sett5Button.setText(eve.getStadio().getSettori().get(4).getNomeSettore());
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
					else if(eve.getStadio().getSettori().size()==6){
						ButtonGroup group = new ButtonGroup();
						sett1Button.setText(eve.getStadio().getSettori().get(0).getNomeSettore());
						sett2Button.setText(eve.getStadio().getSettori().get(1).getNomeSettore());
						sett3Button.setText(eve.getStadio().getSettori().get(2).getNomeSettore());
						sett4Button.setText(eve.getStadio().getSettori().get(3).getNomeSettore());
						sett5Button.setText(eve.getStadio().getSettori().get(4).getNomeSettore());
						sett6Button.setText(eve.getStadio().getSettori().get(5).getNomeSettore());
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
					TitledBorder a5=new TitledBorder(new EtchedBorder(), "Scegli settore dello stadio : "+eve.getStadio().getNomeStadio());
					a5.setTitleFont(new Font("Georgia", Font.ITALIC, 13));
					a5.setTitleColor(Color.BLACK);
					riga2.setBorder(a5);
					riga2.setVisible(true);
					posto.setVisible(false);
									
				}
			}
			
			class SelezionaSettoreListener implements ActionListener {
				public void actionPerformed (ActionEvent e) {				
					posto.setVisible(true);
				}
			}
			
			class ScegliPostoListener implements ActionListener {
				public void actionPerformed (ActionEvent e) {	
				
					String riga11 = (String) eventiCombo.getSelectedItem();
					int j=riga11.indexOf("-");
					String num1=riga11.substring(0,j);
					int numero1 =Integer.parseInt(num1);
					
					EventoCalcistico eve=eventi.get(numero1-1);
				
					
					if (sett1Button.isSelected()){
						acquistaBiglietto.setVisible(false);
						try {
							scegliPosto=visualizzaPosti(eve,eve.getStadio().getSettori().get(0),true);
						} catch (CloneNotSupportedException e1) {
							return;
						}
						scegliPosto.setVisible(true);
					}
					else if (sett2Button.isSelected()){
						acquistaBiglietto.setVisible(false);
						try {
							scegliPosto=visualizzaPosti(eve,eve.getStadio().getSettori().get(1),true);
						} catch (CloneNotSupportedException e1) {
							return;
						}
						scegliPosto.setVisible(true);
					}
					else if (sett3Button.isSelected()){
						acquistaBiglietto.setVisible(false);
						try {
							scegliPosto=visualizzaPosti(eve,eve.getStadio().getSettori().get(2),true);
						} catch (CloneNotSupportedException e1) {
							return;
						}
						scegliPosto.setVisible(true);
					}
					else if (sett4Button.isSelected()){
						acquistaBiglietto.setVisible(false);
						try {
							scegliPosto=visualizzaPosti(eve,eve.getStadio().getSettori().get(3),true);
						} catch (CloneNotSupportedException e1) {
							return;
						}
						scegliPosto.setVisible(true);
					}
					else if (sett5Button.isSelected()){
						acquistaBiglietto.setVisible(false);
						try {
							scegliPosto=visualizzaPosti(eve,eve.getStadio().getSettori().get(4),true);
						} catch (CloneNotSupportedException e1) {
							return;
						}
						scegliPosto.setVisible(true);
					}
					else if (sett6Button.isSelected()){
						acquistaBiglietto.setVisible(false);
						try {
							scegliPosto=visualizzaPosti(eve,eve.getStadio().getSettori().get(5),true);
						} catch (CloneNotSupportedException e1) {
							return;
						}
						scegliPosto.setVisible(true);
					}
				}
			}
					
			
			eventiCombo.addActionListener(new EventiComboListener());
			sett1Button.addActionListener(new SelezionaSettoreListener());
			sett2Button.addActionListener(new SelezionaSettoreListener());
			sett3Button.addActionListener(new SelezionaSettoreListener());
			sett4Button.addActionListener(new SelezionaSettoreListener());
			sett5Button.addActionListener(new SelezionaSettoreListener());
			sett6Button.addActionListener(new SelezionaSettoreListener());
			posto.addActionListener(new ScegliPostoListener());
			indietro.addActionListener(new IndietroListener());
			return acquiBiglietto;
		}
	}
	
	public JFrame prenotaBiglietto() throws DatiNonValidiException{
		
		if(sistema.getEventiCalcistici().size()==0)
			throw new DatiNonValidiException("Non ci sono eventi calcistici nel "+sistema.getNome());
		else if(sistema.getEventiPrenotabili().size()==0)
			throw new DatiNonValidiException("Non ci sono eventi calcistici prenotabili nel "+sistema.getNome());
		else{
		
			
			JFrame preBiglietto=new JFrame();		
			
			Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
			preBiglietto.setLocation(new Point((dimension.width - 
					preBiglietto.getSize().width) / 2-250, 
			(dimension.height - preBiglietto.getSize().height) / 2 -200));
			
			preBiglietto.setSize(500,400);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			preBiglietto.setResizable(false);
			preBiglietto.setLayout(new BorderLayout());
			preBiglietto.setUndecorated(true);
			
			JLabel scegli =new JLabel("Scegli evento calcistico");
			scegli.setFont(new Font("Georgia", Font.PLAIN, 18));
			scegli.setHorizontalAlignment(JLabel.CENTER);
			scegli.setForeground(Color.BLACK);
			
			JComboBox<String> eventiCombo = new JComboBox<String>();
			eventiCombo.setFont(new Font("Georgia", Font.PLAIN, 18));
			ArrayList<EventoCalcistico> eventi=sistema.getEventiPrenotabili();
			for(int i=0; i<eventi.size(); i++){
				
				SimpleDateFormat sdf =new SimpleDateFormat("dd/MM/yyyy-HH:mm:ss");
				String a=sdf.format(eventi.get(i).getDataInizioEventoCalcistico().getTime());
				
				eventiCombo.addItem(
						(i+1)+"-"+eventi.get(i).getPartita().getSquadraCasa()
						+"-"+eventi.get(i).getPartita().getSquadraOspite()+"-"
						+a);
			}
			eventiCombo.setSelectedItem(null);
			
			JPanel sopra =new JPanel();
			sopra.add(scegli);
			sopra.add(eventiCombo);
			
			
			TitledBorder t=new TitledBorder(new EtchedBorder(), "Seleziona evento calcistico");
			t.setTitleFont(new Font("Georgia", Font.ITALIC, 13));
			t.setTitleColor(Color.BLACK);
			sopra.setBorder(t);
			
			JPanel pannello=new JPanel();
			pannello.setLayout(new GridLayout(3,1));
			
			TitledBorder t1=new TitledBorder(new EtchedBorder(), "Prenota biglietto");
			t1.setTitleFont(new Font("Georgia", Font.ITALIC, 13));
			t1.setTitleColor(Color.BLACK);
			pannello.setBorder(t1);
			
			JRadioButton sett1Button = new JRadioButton();
			JRadioButton sett2Button = new JRadioButton();
			JRadioButton sett3Button = new JRadioButton();
			JRadioButton sett4Button = new JRadioButton();
			JRadioButton sett5Button = new JRadioButton();
			JRadioButton sett6Button = new JRadioButton();
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
				
			JPanel riga2=new JPanel();
			JLabel label2=new JLabel("Scegli settore");
			label2.setFont(new Font("Georgia", Font.PLAIN, 18));
			label2.setForeground(Color.BLACK);
			riga2.add(label2);
			riga2.add(sett1Button);
			riga2.add(sett2Button);
			riga2.add(sett3Button);
			riga2.add(sett4Button);
			riga2.add(sett5Button);
			riga2.add(sett6Button);
			
			sett1Button.setVisible(false);
			sett2Button.setVisible(false);
			sett3Button.setVisible(false);
			sett4Button.setVisible(false);
			sett5Button.setVisible(false);
			sett6Button.setVisible(false);
			riga2.setVisible(false);
			
			JPanel riga3=new JPanel();
			JButton posto=new JButton("Scegli posto");
			posto.setFont(new Font("Georgia", Font.ITALIC, 18));
			posto.setForeground(Color.BLACK);
			
			posto.setVisible(false);
			
			pannello.add(sopra);
			pannello.add(riga2);
			pannello.add(riga3);
			JButton indietro = new JButton("Indietro");
			indietro.setFont(new Font("Georgia", Font.PLAIN, 18));
			indietro.setForeground(Color.BLACK);
	
			JPanel a1=new JPanel();
			JPanel a2=new JPanel();
			a1.add(posto);
			a2.add(indietro);
			
			riga3.setLayout(new GridLayout(2,1));
			riga3.add(a1);
			riga3.add(a2);
	
			preBiglietto.add(pannello);
			
			class IndietroListener implements ActionListener {
				public void actionPerformed (ActionEvent e) {				
					prenotaBiglietto.dispose();
					setVisible(true);
				}
			}
			
			class EventiComboListener implements ActionListener {
				public void actionPerformed (ActionEvent e) {				
					
					String riga11 = (String) eventiCombo.getSelectedItem();
					int j=riga11.indexOf("-");
					String num1=riga11.substring(0,j);
					int numero1 =Integer.parseInt(num1);
					
					EventoCalcistico eve=eventi.get(numero1-1);
					
					if(eve.getStadio().getSettori().size()==1){
						ButtonGroup group = new ButtonGroup();
						sett1Button.setText(eve.getStadio().getSettori().get(0).getNomeSettore());
						group.add(sett1Button);
						sett1Button.setVisible(true);
						sett2Button.setVisible(false);
						sett3Button.setVisible(false);
						sett4Button.setVisible(false);
						sett5Button.setVisible(false);
						sett6Button.setVisible(false);
						group.clearSelection();
					}
					else if(eve.getStadio().getSettori().size()==2){
						ButtonGroup group = new ButtonGroup();
						sett1Button.setText(eve.getStadio().getSettori().get(0).getNomeSettore());
						sett2Button.setText(eve.getStadio().getSettori().get(1).getNomeSettore());
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
					else if(eve.getStadio().getSettori().size()==3){
						ButtonGroup group = new ButtonGroup();
						sett1Button.setText(eve.getStadio().getSettori().get(0).getNomeSettore());
						sett2Button.setText(eve.getStadio().getSettori().get(1).getNomeSettore());
						sett3Button.setText(eve.getStadio().getSettori().get(2).getNomeSettore());
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
					else if(eve.getStadio().getSettori().size()==4){
						ButtonGroup group = new ButtonGroup();
						sett1Button.setText(eve.getStadio().getSettori().get(0).getNomeSettore());
						sett2Button.setText(eve.getStadio().getSettori().get(1).getNomeSettore());
						sett3Button.setText(eve.getStadio().getSettori().get(2).getNomeSettore());
						sett4Button.setText(eve.getStadio().getSettori().get(3).getNomeSettore());
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
					else if(eve.getStadio().getSettori().size()==5){
						ButtonGroup group = new ButtonGroup();
						sett1Button.setText(eve.getStadio().getSettori().get(0).getNomeSettore());
						sett2Button.setText(eve.getStadio().getSettori().get(1).getNomeSettore());
						sett3Button.setText(eve.getStadio().getSettori().get(2).getNomeSettore());
						sett4Button.setText(eve.getStadio().getSettori().get(3).getNomeSettore());
						sett5Button.setText(eve.getStadio().getSettori().get(4).getNomeSettore());
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
					else if(eve.getStadio().getSettori().size()==6){
						ButtonGroup group = new ButtonGroup();
						sett1Button.setText(eve.getStadio().getSettori().get(0).getNomeSettore());
						sett2Button.setText(eve.getStadio().getSettori().get(1).getNomeSettore());
						sett3Button.setText(eve.getStadio().getSettori().get(2).getNomeSettore());
						sett4Button.setText(eve.getStadio().getSettori().get(3).getNomeSettore());
						sett5Button.setText(eve.getStadio().getSettori().get(4).getNomeSettore());
						sett6Button.setText(eve.getStadio().getSettori().get(5).getNomeSettore());
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
					TitledBorder a5=new TitledBorder(new EtchedBorder(), "Scegli settore dello stadio : "+eve.getStadio().getNomeStadio());
					a5.setTitleFont(new Font("Georgia", Font.ITALIC, 13));
					a5.setTitleColor(Color.BLACK);
					riga2.setBorder(a5);
					riga2.setVisible(true);
					posto.setVisible(false);
									
				}
			}
			
			class SelezionaSettoreListener implements ActionListener {
				public void actionPerformed (ActionEvent e) {				
					posto.setVisible(true);
				}
			}
			
			class ScegliPostoListener implements ActionListener {
				public void actionPerformed (ActionEvent e) {	
				
					String riga11 = (String) eventiCombo.getSelectedItem();
					int j=riga11.indexOf("-");
					String num1=riga11.substring(0,j);
					int numero1 =Integer.parseInt(num1);
					
					EventoCalcistico eve=eventi.get(numero1-1);
					if (sett1Button.isSelected()){
						prenotaBiglietto.setVisible(false);
						try {
							scegliPosto=visualizzaPosti(eve,eve.getStadio().getSettori().get(0),false);
						} catch (CloneNotSupportedException e1) {
							return;
						}
						scegliPosto.setVisible(true);
					}
					else if (sett2Button.isSelected()){
						prenotaBiglietto.setVisible(false);
						try {
							scegliPosto=visualizzaPosti(eve,eve.getStadio().getSettori().get(1),false);
						} catch (CloneNotSupportedException e1) {
							return ;
						}
						scegliPosto.setVisible(true);
					}
					else if (sett3Button.isSelected()){
						prenotaBiglietto.setVisible(false);
						try {
							scegliPosto=visualizzaPosti(eve,eve.getStadio().getSettori().get(2),false);
						} catch (CloneNotSupportedException e1) {
							return ;
						}
						scegliPosto.setVisible(true);
					}
					else if (sett4Button.isSelected()){
						prenotaBiglietto.setVisible(false);
						try {
							scegliPosto=visualizzaPosti(eve,eve.getStadio().getSettori().get(3),false);
						} catch (CloneNotSupportedException e1) {
							return ;
						}
						scegliPosto.setVisible(true);
					}
					else if (sett5Button.isSelected()){
						prenotaBiglietto.setVisible(false);
						try {
							scegliPosto=visualizzaPosti(eve,eve.getStadio().getSettori().get(4),false);
						} catch (CloneNotSupportedException e1) {
							return ;
						}
						scegliPosto.setVisible(true);
					}
					else if (sett6Button.isSelected()){
						prenotaBiglietto.setVisible(false);
						try {
							scegliPosto=visualizzaPosti(eve,eve.getStadio().getSettori().get(5),false);
						} catch (CloneNotSupportedException e1) {
							return ;
						}
						scegliPosto.setVisible(true);
					}
				}
			}
					
			
			eventiCombo.addActionListener(new EventiComboListener());
			sett1Button.addActionListener(new SelezionaSettoreListener());
			sett2Button.addActionListener(new SelezionaSettoreListener());
			sett3Button.addActionListener(new SelezionaSettoreListener());
			sett4Button.addActionListener(new SelezionaSettoreListener());
			sett5Button.addActionListener(new SelezionaSettoreListener());
			sett6Button.addActionListener(new SelezionaSettoreListener());
			posto.addActionListener(new ScegliPostoListener());
			indietro.addActionListener(new IndietroListener());
			return preBiglietto;
		}
	}
	
	public JFrame visualizzaPosti(EventoCalcistico evento,Settore settore,boolean acquisto) throws CloneNotSupportedException{
		
			JFrame posti=new JFrame();
			
			int x=0;
			int y=650;
			if(settore.getNumeroPostiFila()<=7)
				x=400;
			else
				x=650;
			
			Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
			posti.setLocation(new Point((dimension.width - 
					posti.getSize().width) / 2-(x/2), 
			(dimension.height - posti.getSize().height) / 2 -(y/2)));
			
			posti.setSize(x,y);
			posti.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			posti.setResizable(false);
			posti.setUndecorated(true);
			
			JPanel pannello=new JPanel();
			pannello.setLayout(new BorderLayout());
			
			ArrayList<JPanel> bottoni=new ArrayList<JPanel>();
			
			JLabel labelTitolo= new JLabel(settore.getNomeSettore());
			labelTitolo.setFont(new Font("Georgia", Font.PLAIN, 18));
			labelTitolo.setHorizontalAlignment(JLabel.CENTER);
			labelTitolo.setForeground(Color.BLACK);
			
			JPanel legenda=new JPanel();
			Posto p3=new Posto(0,'C');
			PostoColorato verde=new PostoColorato("",p3);
			Posto p1=new Posto(0,'C');
			p1.setPrenotato(true);
			PostoColorato giallo=new PostoColorato("",p1);
			Posto p2=new Posto(0,'C');
			p2.setVenduto(true);
			PostoColorato rosso=new PostoColorato("",p2);
			JLabel p4=new JLabel("DISPONIBILE");
			p4.setFont(new Font("Georgia", Font.PLAIN, 12));
			p4.setForeground(Color.BLACK);
			
			JLabel p5=new JLabel("PRENOTATO");
			p5.setFont(new Font("Georgia", Font.PLAIN, 12));
			p5.setForeground(Color.BLACK);
			
			JLabel p6=new JLabel("VENDUTO");
			p6.setFont(new Font("Georgia", Font.PLAIN, 12));
			p6.setForeground(Color.BLACK);
			
			legenda.add(verde);
			legenda.add(p4);
			legenda.add(giallo);
			legenda.add(p5);
			legenda.add(rosso);
			legenda.add(p6);
			
			JPanel panelTitolo= new JPanel();
			panelTitolo.setLayout(new GridLayout(2,1));
			panelTitolo.add(labelTitolo);
			panelTitolo.add(legenda);
			
			JButton indietro= new JButton("Indietro");
			indietro.setFont(new Font("Georgia", Font.PLAIN, 18));
			indietro.setHorizontalAlignment(JLabel.CENTER);
			indietro.setForeground(Color.BLACK);
			
			JButton conferma= new JButton("Conferma");
			conferma.setFont(new Font("Georgia", Font.PLAIN, 18));
			conferma.setHorizontalAlignment(JLabel.CENTER);
			conferma.setForeground(Color.BLACK);
		
			ArrayList<Posto> post=new ArrayList<Posto>();
			ArrayList<Acquisto> acqui=new ArrayList<Acquisto>();
			ArrayList<Prenotazione> pre=new ArrayList<Prenotazione>();
			
			class CliccaListener implements MouseListener {

				public void mouseClicked(MouseEvent e) {}
				public void mousePressed(MouseEvent e) {
					PostoColorato bottone= (PostoColorato) e.getSource();
					
					Posto po=bottone.getPosto();
						if(acquisto) {
							Acquisto aa=null;
							try {
								aa=new Acquisto(sistema.getUtenteCorrente(), evento, settore, po);
								//settore.aggiungiPostoVenduto(po.getNumero(),po.getLettera());
								po.setVenduto(true);
								bottone.setPosto(po);
								acqui.add(aa);
								conferma.setVisible(true);
							} catch (PostoIndisponibileException e1) {
								boolean ok=false;
								int i;
								for(i=0; i<acqui.size(); i++)
									if(acqui.get(i).getPosto().equals(po)){
										ok=true;
										break;
									}
								if(ok==false){
									System.out.println("eerrr");
									scegliPosto.setVisible(false);
									def=new DefaultFrame(new JLabel(e1.getMessage()),
									"Errore",450,new ImageIcon(imgURLnonOK),
									scegliPosto);
									def.setVisible(true);
								}
								else{
									System.out.println("111");
								//	int a=po.getLettera()-'A';
								//  int numero =po.getNumero();
									//settore.getSettore().get((settore.getNumeroPostiFila()*a)+numero-1).setDisponibile(true);;
									po.setDisponibile(true);
									bottone.setPosto(po);
									acqui.remove(i);
									if(acqui.size()==0)
										conferma.setVisible(false);
									else
										conferma.setVisible(true);
								}
							}
						}
						else{
							Prenotazione p=null;
							try {
								p=new Prenotazione(sistema.getUtenteCorrente(),
									evento,settore,po);
								po.setPrenotato(true);
								bottone.setPosto(po);
								pre.add(p);
								conferma.setVisible(true);
							} catch (PostoIndisponibileException e1) {
								boolean ok=false;
								int i;
								for(i=0; i<pre.size(); i++)
									if(pre.get(i).getPosto().equals(po)){
										ok=true;
										break;
									}
								if(ok==false){
									scegliPosto.setVisible(false);
									def=new DefaultFrame(new JLabel(e1.getMessage()),
									"Errore",450,new ImageIcon(imgURLnonOK),
									scegliPosto);
									def.setVisible(true);
								}
								else{
									po.setDisponibile(true);
									bottone.setPosto(po);
									pre.remove(i);
									if(pre.size()==0)
										conferma.setVisible(false);
									else
										conferma.setVisible(true);
								}
								
							}
						}
				}
				public void mouseReleased(MouseEvent e) {}
				public void mouseEntered(MouseEvent e) {
					PostoColorato bottone= (PostoColorato) e.getSource();
					//bottone.setFont(new Font("Georgia", Font.BOLD, 18));
					bottone.setForeground(Color.BLUE);
				}
				public void mouseExited(MouseEvent e) {
					PostoColorato bottone= (PostoColorato) e.getSource();
					//bottone.setFont(new Font("Georgia", Font.PLAIN, 18));
					bottone.setForeground(Color.BLACK);
				}
			}
		
			
			
			JPanel postiPanel=new JPanel();
			postiPanel.setLayout(
					new GridLayout(settore.getNumeroFile(),
							settore.getNumeroPostiFila()));
		
			JScrollPane scrollPane = new JScrollPane(postiPanel,
					JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
					JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
			
			
			for(Posto p:settore.getSettore())
				post.add((Posto) p.clone());
			
			for(int i=0;i<post.size(); i++){
				Posto p=post.get(i);
			/**PostoColorato ppp=new PostoColorato(
				   Character.toString(post.get(i).getLettera())+
				   "-"+post.get(i).getNumero(),p);
				
				*/
				ppp=new PostoColorato(
				   Character.toString(post.get(i).getLettera())+
				   "-"+post.get(i).getNumero(),p);
				
				ppp.setFont(new Font("Georgia", Font.PLAIN, 18));
				ppp.setHorizontalAlignment(JLabel.CENTER);
				ppp.setForeground(Color.BLACK);
				JPanel pa=new JPanel();
				pa.add(ppp);
			
				ppp.addMouseListener( new CliccaListener());	
				bottoni.add(pa);
			}
			
			for(int i=0;i<settore.getCapienza();i++){
				postiPanel.add(bottoni.get(i));
			}
			
			
			
			
			
			
			
			JPanel panelIndietro= new JPanel();
			
			panelIndietro.add(indietro);
			panelIndietro.add(conferma);
			
			conferma.setVisible(false);
			
			
			
			class IndietroListener implements ActionListener{
				public void actionPerformed(ActionEvent event) {
					posti.dispose();
					new UtenteFrame(sistema).setVisible(true);		
			
				}
			}
			
			class ConfermaListener implements ActionListener{
				public void actionPerformed(ActionEvent event) {
					
					
					
				if(acqui.size()==1)	
					try {
						System.out.println("ciao2");
						sistema.acquista(acqui.get(0));
						
						def=new DefaultFrame(new JLabel("Acquisto effettuato "),
							"Acquisto effettuato",390,
							new ImageIcon(imgURLOK),
							new UtenteFrame(sistema));
						def.setVisible(true);
						scegliPosto.dispose();
					} 
					catch (DatiNonValidiException e1) {
						scegliPosto.dispose();
						def=new DefaultFrame(new JLabel(e1.getMessage()),
							"Errore",390,
							new ImageIcon(imgURLnonOK),
							new UtenteFrame(sistema));
						def.setVisible(true);
					}
				else if (acqui.size()>1)
					try{
						sistema.acquistaPiuBiglietti(acqui);
						scegliPosto.dispose();
							def=new DefaultFrame(new JLabel("Acquisto effettuato "),
								"Acquisto effettuato",390,
								new ImageIcon(imgURLOK),
								new UtenteFrame(sistema));
							def.setVisible(true);
						} 
					catch (DatiNonValidiException e1) {
						scegliPosto.dispose();
						def=new DefaultFrame(new JLabel(e1.getMessage()),
							"Errore",390,
							new ImageIcon(imgURLnonOK),
							new UtenteFrame(sistema));
						def.setVisible(true);
					}
				else if(pre.size()==1){
						System.out.println("ciao11");
						sistema.prenota(pre.get(0));
						
						scegliPosto.dispose();
						def=new DefaultFrame(new JLabel("Prenotazione effettuata "),
								"Prenotazione effettuata",390,
							new ImageIcon(imgURLOK),
							new UtenteFrame(sistema));
						def.setVisible(true);
				}
				else if(pre.size()>1){
					sistema.prenotaPiuBiglietti(pre);
					scegliPosto.dispose();
					def=new DefaultFrame(new JLabel("Prenotazioni effettuate "),
						"Prenotazioni effettuate",390,
						new ImageIcon(imgURLOK),
						new UtenteFrame(sistema));	
					def.setVisible(true);
				}
				else
					conferma.setVisible(false);
				
					
					
					
			}
		}
			
			
			
			conferma.addActionListener(new ConfermaListener());
			indietro.addActionListener(new IndietroListener());
			pannello.add(panelTitolo,BorderLayout.NORTH);
			pannello.add(scrollPane,BorderLayout.CENTER);
			pannello.add(panelIndietro,BorderLayout.SOUTH);
			posti.add(pannello);
			
			
			
		
		
			return posti;
	}
	
	public JFrame acquistaCredito(){
		JFrame acquistaCredito=new JFrame();		
		
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		acquistaCredito.setLocation(new Point((dimension.width - 
				acquistaCredito.getSize().width) / 2-200, 
		(dimension.height - acquistaCredito.getSize().height) / 2 -70));
		
		acquistaCredito.setSize(400,140);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		acquistaCredito.setResizable(false);
		acquistaCredito.setLayout(new BorderLayout());
		acquistaCredito.setUndecorated(true);
		
		JLabel scegli2 =new JLabel("Ciao ");
		scegli2.setFont(new Font("Georgia", Font.PLAIN, 18));
		scegli2.setHorizontalAlignment(JLabel.CENTER);
		scegli2.setForeground(Color.BLACK);
		
		JLabel scegli =new JLabel(sistema.getUtenteCorrente().getNome());
		scegli.setFont(new Font("Georgia", Font.PLAIN, 18));
		scegli.setHorizontalAlignment(JLabel.CENTER);
		scegli.setForeground(Color.RED);
		
		JLabel scegli1 =new JLabel(", di quando vuoi ricaricare ?");
		scegli1.setFont(new Font("Georgia", Font.PLAIN, 18));
		scegli1.setHorizontalAlignment(JLabel.CENTER);
		scegli1.setForeground(Color.BLACK);
		
		JPanel pannello=new JPanel();
		pannello.setLayout(new BorderLayout());
		
		JPanel sopra=new JPanel();
		sopra.add(scegli2);
		sopra.add(scegli);
		sopra.add(scegli1);		
		
		
		
		pannello.add(sopra,BorderLayout.NORTH);

		
		JPanel bottoni=new JPanel();
		JButton annulla =new JButton("Annulla");
		annulla.setFont(new Font("Georgia", Font.PLAIN, 18));
		annulla.setHorizontalAlignment(JLabel.CENTER);
		annulla.setForeground(Color.BLACK);
		
		JButton conferma =new JButton("Conferma");
		conferma.setFont(new Font("Georgia", Font.PLAIN, 18));
		conferma.setHorizontalAlignment(JLabel.CENTER);
		conferma.setForeground(Color.BLACK);
		
		
		bottoni.add(annulla);
		bottoni.add(conferma);
		
		TitledBorder a9=new TitledBorder(new EtchedBorder(),"Ricarica");
		a9.setTitleFont(new Font("Georgia", Font.ITALIC, 13));
		a9.setTitleColor(Color.BLACK);
		pannello.setBorder(a9);
		pannello.add(bottoni,BorderLayout.SOUTH);

		
		
		JLabel nuovoPrezzo=new JLabel("Inserisci credito ");
		JTextField prezzoField=new JTextField(5);
		
		nuovoPrezzo.setFont(new Font("Georgia", Font.PLAIN, 18));
		nuovoPrezzo.setHorizontalAlignment(JLabel.CENTER);
		nuovoPrezzo.setForeground(Color.BLACK);
		
		prezzoField.setFont(new Font("Georgia", Font.PLAIN, 18));
		prezzoField.setHorizontalAlignment(JLabel.CENTER);
		prezzoField.setForeground(Color.BLACK);
		
		JPanel centro=new JPanel();
		
		centro.add(nuovoPrezzo);
		centro.add(prezzoField);
		pannello.add(centro,BorderLayout.CENTER);
		
		acquistaCredito.add(pannello);			
		
		
		
		class ConfermaListener implements ActionListener {
			public void actionPerformed (ActionEvent e) {
				
				try {
					sistema.aggiungiCredito(prezzoField.getText());
					acquistaCredito.dispose();
					def=new DefaultFrame(new JLabel("Il credito Ë stato aggiunto"),
							"Ricarica effettuata",450,new ImageIcon(imgURLOK),
							new UtenteFrame(sistema));
					def.setVisible(true);
				} catch (DatiNonValidiException e1) {
					acquistaCredito.setVisible(false);
					def=new DefaultFrame(new JLabel(e1.getMessage()),
							"Ricarica non effettuata",450,new ImageIcon(imgURLnonOK),
							acquistaCredito);
					def.setVisible(true);
				}
				
			}
		}
		
		class AnnullaListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				acquistaCredito.dispose();
				setVisible(true);		
			}
		}
		
		conferma.addActionListener(new ConfermaListener());
		
		annulla.addActionListener(new AnnullaListener());
		return acquistaCredito;
	}

	public JFrame eventiCalcisticiDiUnoStadio ()throws DatiNonValidiException{
			
		if(sistema.getEventiCalcistici().size()==0)
			throw new DatiNonValidiException("Non ci sono eventi calcistici nel "+sistema.getNome());
		else{
			setVisible(false);
		
			JFrame visualizzaEventiCalcisticiSettimana=new JFrame();		
			Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
			visualizzaEventiCalcisticiSettimana.setLocation(new Point((dimension.width - 
					visualizzaEventiCalcisticiSettimana.getSize().width) / 2-400, 
			(dimension.height - visualizzaEventiCalcisticiSettimana.getSize().height) / 2 -200));
			visualizzaEventiCalcisticiSettimana.setSize(800,400);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			visualizzaEventiCalcisticiSettimana.setResizable(false);
			visualizzaEventiCalcisticiSettimana.setLayout(new BorderLayout());
			visualizzaEventiCalcisticiSettimana.setUndecorated(true);
			
			
			JPanel bottoni=new JPanel();
			JButton indietro =new JButton("Indietro");
			indietro.setFont(new Font("Georgia", Font.PLAIN, 18));
			indietro.setHorizontalAlignment(JLabel.CENTER);
			indietro.setForeground(Color.BLACK);
			JButton indietro1 =new JButton("Indietro");
			indietro1.setFont(new Font("Georgia", Font.PLAIN, 18));
			indietro1.setHorizontalAlignment(JLabel.CENTER);
			indietro1.setForeground(Color.BLACK);
			JLabel scegli =new JLabel("Scegli Stadio");
			scegli.setFont(new Font("Georgia", Font.PLAIN, 18));
			scegli.setHorizontalAlignment(JLabel.CENTER);
			scegli.setForeground(Color.BLACK);
			JComboBox<String> stadioCombo = new JComboBox<String>();
			stadioCombo.setFont(new Font("Georgia", Font.PLAIN, 18));
			ArrayList<Stadio> stadi=sistema.getStadi();
			for(int i=0; i<stadi.size(); i++){
				stadioCombo.addItem((i+1)+"-"+stadi.get(i).getNomeStadio()+"-"+
						stadi.get(i).getCitt‡Stadio()+"-"+stadi.get(i).getCapienza());
			}
			stadioCombo.setSelectedItem(null);
			bottoni.add(indietro);
			bottoni.add(scegli);
			bottoni.add(stadioCombo);
			bottoni.add(indietro1);
			indietro1.setVisible(false);
			
			JPanel pannello = new JPanel();
			pannello.setLayout(null);
			JScrollPane scroll = new JScrollPane(pannello,
					JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
					JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
			visualizzaEventiCalcisticiSettimana.add(scroll);
			pannello.setLayout(new BorderLayout());
			pannello.add(bottoni,BorderLayout.NORTH);
			JPanel pannello1=new JPanel();
			pannello.add(pannello1,BorderLayout.CENTER);
			
			JLabel l1=new JLabel(" SquadraCasa ");
			JLabel l2=new JLabel(" SquadraOspite ");
			JLabel l3=new JLabel(" Stadio ");
			JLabel l4=new JLabel(" Prezzo "); 
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
			
			JPanel rigaTitoli=new JPanel();
			rigaTitoli.setLayout(new GridLayout(1,6));		
			rigaTitoli.add(l1);
			rigaTitoli.add(l2);
			rigaTitoli.add(l3);
			rigaTitoli.add(l4);
			rigaTitoli.add(l6);
			rigaTitoli.add(l0);
		
			TitledBorder t=new TitledBorder(new EtchedBorder(), "Eventi calcistici di uno stadio");
			t.setTitleFont(new Font("Georgia", Font.ITALIC, 13));
			t.setTitleColor(Color.BLACK);
			pannello.setBorder(t);
			
			class IndietroListener implements ActionListener {
				public void actionPerformed(ActionEvent e) {
					eventiCalcisticiStadio.dispose();
					setVisible(true);
				}
			}
			
			class StadioComboListener implements ActionListener {
				public void actionPerformed(ActionEvent e) {
					
					String riga11 = (String) stadioCombo.getSelectedItem();
					int j=riga11.indexOf("-");
					String num1=riga11.substring(0,j);
					int numero1 =Integer.parseInt(num1);
			
					Stadio stadio=sistema.getStadi().get(numero1-1);
					ArrayList<EventoCalcistico> eventi=new ArrayList<EventoCalcistico>();
				
					try {
						eventi = sistema.getEventiCalcisticiDiUnoStadio(stadio);
						
						TitledBorder t=new TitledBorder(new EtchedBorder(), "Eventi calcistici dello stadio "+stadio.getNomeStadio());
						t.setTitleFont(new Font("Georgia", Font.ITALIC, 13));
						t.setTitleColor(Color.BLACK);
						pannello1.setBorder(t);
					
						scegli.setVisible(false);
						indietro.setVisible(false);
						stadioCombo.setVisible(false);
						indietro1.setVisible(true);
						pannello1.setLayout(new GridLayout(eventi.size()+1,1));
						
						pannello.setVisible(true);
						pannello1.setVisible(true);
						rigaTitoli.setVisible(true);
						pannello1.add(rigaTitoli);
						
						int i;
						for(i=0; i<eventi.size(); i++){
							EventoCalcistico ev=eventi.get(i);
							System.out.println(eventi.size());
							JPanel rigaEventi=new JPanel();
							rigaEventi.setLayout(new GridLayout(1,6));
				
							SimpleDateFormat sdf =new SimpleDateFormat("dd/MM/yyyy");
							String a=sdf.format(ev.getDataInizioEventoCalcistico().getTime());
				
							SimpleDateFormat sdf1 =new SimpleDateFormat("HH:mm:ss");
							String a1=sdf1.format(ev.getDataInizioEventoCalcistico().getTime());
							JLabel l7=new JLabel(ev.getPartita().getSquadraCasa());
							JLabel l8=new JLabel(ev.getPartita().getSquadraOspite());
							JLabel l9=new JLabel(ev.getStadio().getNomeStadio());
							
							DecimalFormat df = new DecimalFormat("#.##"); 

							String s = df.format(ev.getStadio().getPrezzo());
							JLabel l10=new JLabel(s+" Ä"); //poi verr‡ eliminta
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
							rigaEventi.add(l7);
							rigaEventi.add(l8);
							rigaEventi.add(l9);
							rigaEventi.add(l10);
							rigaEventi.add(l12);
							rigaEventi.add(l13);
							pannello1.add(rigaEventi);
							scroll.setVisible(true);
							pannello.setVisible(true);
							pannello1.setVisible(true);
							rigaEventi.setVisible(true);
							rigaTitoli.setVisible(true);
						}
					}
					catch(DatiNonValidiException e1){
						eventiCalcisticiStadio.setVisible(false);
						stadioCombo.setSelectedItem(null);
						def=new DefaultFrame(new JLabel(e1.getMessage()),
								"Errore",550,
								new ImageIcon(imgURLnonOK),
							    eventiCalcisticiStadio);
						def.setVisible(true);
					}
				
				}
			}
		
			class Indietro1Listener implements ActionListener {
				public void actionPerformed(ActionEvent e) {
					eventiCalcisticiStadio.dispose();
					try {
						eventiCalcisticiStadio=eventiCalcisticiDiUnoStadio();
						eventiCalcisticiStadio.setVisible(true);
					} catch (DatiNonValidiException e1) {
						return;
					}
					
				}
			}	
		
			indietro1.addActionListener(new Indietro1Listener());
			stadioCombo.addActionListener(new StadioComboListener());
			indietro.addActionListener(new IndietroListener());
			return visualizzaEventiCalcisticiSettimana;
		}
	}

	public JFrame eventiCalcisticiPerSettimana () throws DatiNonValidiException{
		
		
		if(sistema.getEventiCalcistici().size()==0)
			throw new DatiNonValidiException("Non ci sono eventi calcistici nel "+sistema.getNome());
		else{
		
			setVisible(false);
		
			JFrame visualizzaEventiCalcisticiSettimana=new JFrame();		
			
			Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
			visualizzaEventiCalcisticiSettimana.setLocation(new Point((dimension.width - 
					visualizzaEventiCalcisticiSettimana.getSize().width) / 2-400, 
			(dimension.height - visualizzaEventiCalcisticiSettimana.getSize().height) / 2 -200));
			visualizzaEventiCalcisticiSettimana.setSize(800,400);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			visualizzaEventiCalcisticiSettimana.setResizable(false);
			visualizzaEventiCalcisticiSettimana.setLayout(new BorderLayout());
			visualizzaEventiCalcisticiSettimana.setUndecorated(true);
			
			JPanel bottoni=new JPanel();
			JButton indietro =new JButton("Indietro");
			indietro.setFont(new Font("Georgia", Font.PLAIN, 18));
			indietro.setHorizontalAlignment(JLabel.CENTER);
			indietro.setForeground(Color.BLACK);
			
		
			JButton indietro1 =new JButton("Indietro");
			indietro1.setFont(new Font("Georgia", Font.PLAIN, 18));
			indietro1.setHorizontalAlignment(JLabel.CENTER);
			indietro1.setForeground(Color.BLACK);
			
			JLabel anno =new JLabel("Anno");
			anno.setFont(new Font("Georgia", Font.PLAIN, 18));
			anno.setHorizontalAlignment(JLabel.CENTER);
			anno.setForeground(Color.BLACK);
			JLabel mese =new JLabel("Mese");
			mese.setFont(new Font("Georgia", Font.PLAIN, 18));
			mese.setHorizontalAlignment(JLabel.CENTER);
			mese.setForeground(Color.BLACK);
			JLabel giorno =new JLabel("Giorno");
			giorno.setFont(new Font("Georgia", Font.PLAIN, 18));
			giorno.setHorizontalAlignment(JLabel.CENTER);
			giorno.setForeground(Color.BLACK);
			JTextField annoField =new JTextField(4);
			annoField.setFont(new Font("Georgia", Font.PLAIN, 18));
			annoField.setHorizontalAlignment(JLabel.CENTER);
			annoField.setForeground(Color.BLACK);
			JTextField meseField =new JTextField(4);
			meseField.setFont(new Font("Georgia", Font.PLAIN, 18));
			meseField.setHorizontalAlignment(JLabel.CENTER);
			meseField.setForeground(Color.BLACK);
			JTextField giornoField =new JTextField(4);
			giornoField.setFont(new Font("Georgia", Font.PLAIN, 18));
			giornoField.setHorizontalAlignment(JLabel.CENTER);
			giornoField.setForeground(Color.BLACK);
			JButton avanti =new JButton("Avanti");
			avanti.setFont(new Font("Georgia", Font.PLAIN, 18));
			avanti.setHorizontalAlignment(JLabel.CENTER);
			avanti.setForeground(Color.BLACK);
			
			bottoni.add(indietro);
			bottoni.add(anno);
			bottoni.add(annoField);
			bottoni.add(mese);
			bottoni.add(meseField);
			bottoni.add(giorno);
			bottoni.add(giornoField);
			bottoni.add(avanti);
			bottoni.add(indietro1);
			indietro1.setVisible(false);
			
			class IndietroListener implements ActionListener {
				public void actionPerformed(ActionEvent e) {
					eventiCalcisticiSettimana.dispose();
					setVisible(true);
				}
			}	
			
			JPanel pannello = new JPanel();
			pannello.setLayout(null);
			JScrollPane scroll = new JScrollPane(pannello,
					JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
					JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
			
			visualizzaEventiCalcisticiSettimana.add(scroll);
			
			pannello.setLayout(new BorderLayout());
			pannello.add(bottoni,BorderLayout.NORTH);
			JPanel pannello1=new JPanel();
			pannello.add(pannello1,BorderLayout.CENTER);
			
			JLabel l1=new JLabel(" SquadraCasa ");
			JLabel l2=new JLabel(" SquadraOspite ");
			JLabel l3=new JLabel(" Stadio ");
			JLabel l4=new JLabel(" Prezzo"); 
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
			
			JPanel rigaTitoli=new JPanel();
			rigaTitoli.setLayout(new GridLayout(1,6));		
			rigaTitoli.add(l1);
			rigaTitoli.add(l2);
			rigaTitoli.add(l3);
			rigaTitoli.add(l4);
			rigaTitoli.add(l6);
			rigaTitoli.add(l0);
			
			TitledBorder t=new TitledBorder(new EtchedBorder(), "Eventi calcistici di una settimana");
			t.setTitleFont(new Font("Georgia", Font.ITALIC, 13));
			t.setTitleColor(Color.BLACK);
			pannello.setBorder(t);
		
			
			class AvantiListener implements ActionListener {
				public void actionPerformed(ActionEvent e) {
			
					ArrayList<EventoCalcistico> eventi=new ArrayList<EventoCalcistico>();
					try {
						eventi = sistema.getEventiCalcisticiSettimana(
						annoField.getText(),
						meseField.getText(),
						giornoField.getText());
						
						GregorianCalendar ini=new GregorianCalendar(
								Integer.parseInt(annoField.getText())
								,Integer.parseInt(meseField.getText())-1,
								Integer.parseInt(giornoField.getText()));
						SimpleDateFormat sdf =new SimpleDateFormat("dd/MM/yyyy");
						String inizio=sdf.format(ini.getTime());
			
						ini.add(GregorianCalendar.DATE,+7);
						inizio=inizio + " al "+sdf.format(ini.getTime());
						
						TitledBorder t=new TitledBorder(new EtchedBorder(), "Eventi calcistici in programma dal "+inizio);
						t.setTitleFont(new Font("Georgia", Font.ITALIC, 13));
						t.setTitleColor(Color.BLACK);
						pannello1.setBorder(t);
						
						avanti.setVisible(false);
						anno.setVisible(false);
						mese.setVisible(false);
						giorno.setVisible(false);
						annoField.setVisible(false);
						meseField.setVisible(false);
						giornoField.setVisible(false);
						indietro.setVisible(false);
						indietro1.setVisible(true);
						
						pannello1.setLayout(new GridLayout(eventi.size()+1,1));
					
						pannello.setVisible(true);
						pannello1.setVisible(true);
						rigaTitoli.setVisible(true);
						
						pannello1.add(rigaTitoli);
						
						int i;
						for(i=0; i<eventi.size(); i++){
							EventoCalcistico ev=eventi.get(i);
							System.out.println(eventi.size());
							JPanel rigaEvento=new JPanel();
							rigaEvento.setLayout(new GridLayout(1,6));
				
							SimpleDateFormat sdf2 =new SimpleDateFormat("dd/MM/yyyy");
							String a=sdf2.format(ev.getDataInizioEventoCalcistico().getTime());
				
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
				
							pannello1.add(rigaEvento);
							scroll.setVisible(true);
							pannello.setVisible(true);
							pannello1.setVisible(true);
							rigaEvento.setVisible(true);
							rigaTitoli.setVisible(true);
						}
					}
					catch (DatiNonValidiException e1) {
						eventiCalcisticiSettimana.setVisible(false);
						def=new DefaultFrame(new JLabel(e1.getMessage()),
								"Errore",550,
								new ImageIcon(imgURLnonOK),
							    eventiCalcisticiSettimana);
						def.setVisible(true);
					}
				}
			}
			
			class Indietro1Listener implements ActionListener {
				public void actionPerformed(ActionEvent e) {
					
					pannello1.setVisible(false);
					pannello1.removeAll();
					indietro1.setVisible(false);
					avanti.setVisible(true);
					anno.setVisible(true);
					mese.setVisible(true);
					giorno.setVisible(true);
					annoField.setVisible(true);
					meseField.setVisible(true);
					giornoField.setVisible(true);
					indietro.setVisible(true);
					eventiCalcisticiSettimana.setVisible(true);
				}
			}	
			indietro1.addActionListener(new Indietro1Listener());
			avanti.addActionListener(new AvantiListener());
			indietro.addActionListener(new IndietroListener());
			return visualizzaEventiCalcisticiSettimana;
		}
	}

	public JFrame eventiCalcisticiNonIniziatiCronolo() throws CloneNotSupportedException, DatiNonValidiException {
		
		if(sistema.getEventiCalcistici().size()==0)
			throw new DatiNonValidiException("Non ci sono eventi calcistici nel "+sistema.getNome());
		else if(sistema.getEventiNonIniziati().size()==0)
			throw new DatiNonValidiException("Non ci sono eventi calcistici non ancora iniziati nel "+sistema.getNome());
		else{
		
			setVisible(false);
		
			JFrame visualizzaEventiCalcisticiPerDataCro=new JFrame();		
			Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
			visualizzaEventiCalcisticiPerDataCro.setLocation(new Point((dimension.width - 
					visualizzaEventiCalcisticiPerDataCro.getSize().width) / 2-400, 
			(dimension.height - visualizzaEventiCalcisticiPerDataCro.getSize().height) / 2 -200));
			visualizzaEventiCalcisticiPerDataCro.setSize(800,400);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			visualizzaEventiCalcisticiPerDataCro.setResizable(false);
			visualizzaEventiCalcisticiPerDataCro.setLayout(new BorderLayout());
			visualizzaEventiCalcisticiPerDataCro.setUndecorated(true);
			JPanel bottoni=new JPanel();
			JButton indietro =new JButton("Indietro");
			indietro.setFont(new Font("Georgia", Font.PLAIN, 18));
			indietro.setHorizontalAlignment(JLabel.CENTER);
			indietro.setForeground(Color.BLACK);
			bottoni.add(indietro);
			
			JPanel pannello = new JPanel();
			pannello.setLayout(null);
			JScrollPane scroll = new JScrollPane(pannello,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
			visualizzaEventiCalcisticiPerDataCro.add(scroll);
			pannello.setLayout(new BorderLayout());
			pannello.add(bottoni,BorderLayout.NORTH);
			TitledBorder t=new TitledBorder(new EtchedBorder(), "Eventi calcistici");
			t.setTitleFont(new Font("Georgia", Font.ITALIC, 13));
			t.setTitleColor(Color.BLACK);
			pannello.setBorder(t);
			
			JPanel pannello1=new JPanel();
			TitledBorder t1=new TitledBorder(new EtchedBorder(), "Eventi calcistici non iniziati in ordine cronologico");
			t1.setTitleFont(new Font("Georgia", Font.ITALIC, 13));
			t1.setTitleColor(Color.BLACK);
			pannello1.setBorder(t1);
			
			JPanel rigoTitoli=new JPanel();
			rigoTitoli.setLayout(new GridLayout(1,6));
			JLabel l1=new JLabel(" SquadraCasa ");
			JLabel l2=new JLabel(" SquadraOspite ");
			JLabel l3=new JLabel(" Stadio ");
			JLabel l4=new JLabel(" Prezzo ");
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
			rigoTitoli.add(l1);
			rigoTitoli.add(l2);
			rigoTitoli.add(l3);
			rigoTitoli.add(l4);
			rigoTitoli.add(l6);
			rigoTitoli.add(l0);
			pannello1.add(rigoTitoli);
			
			ArrayList<EventoCalcistico> eventi;
			eventi = sistema.getEventiCalcisticiNonIniziatiOrdineCronologico();
			pannello1.setLayout(new GridLayout(eventi.size()+1,1));
			pannello.add(pannello1,BorderLayout.CENTER);
			for(int i=0; i<eventi.size(); i++){
				EventoCalcistico ev=eventi.get(i);

				JPanel rigoEvento=new JPanel();
				rigoEvento.setLayout(new GridLayout(1,7));
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
				rigoEvento.add(l7);
				rigoEvento.add(l8);
				rigoEvento.add(l9);
				rigoEvento.add(l10);
				rigoEvento.add(l12);
				rigoEvento.add(l13);
				pannello1.add(rigoEvento);
			}
			
			
			class IndietroListener implements ActionListener {
				public void actionPerformed(ActionEvent e) {
					visualizzaEventiCalcisticiPerDataCro.dispose();
					setVisible(true);
				}
			}	
			indietro.addActionListener(new IndietroListener());
			return visualizzaEventiCalcisticiPerDataCro;
		}
	}
	
	public JFrame eventiCalcisticiNonIniziatiId() throws CloneNotSupportedException, DatiNonValidiException {
		
		if(sistema.getEventiCalcistici().size()==0)
			throw new DatiNonValidiException("Non ci sono eventi calcistici nel "+sistema.getNome());
		else if(sistema.getEventiNonIniziati().size()==0)
			throw new DatiNonValidiException("Non ci sono eventi calcistici non ancora iniziati nel "+sistema.getNome());
		else{
			setVisible(false);
		
			JFrame visualizzaEventiCalcisticiId=new JFrame();		
			
			Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
			visualizzaEventiCalcisticiId.setLocation(new Point((dimension.width - 
					visualizzaEventiCalcisticiId.getSize().width) / 2-400, 
			(dimension.height - visualizzaEventiCalcisticiId.getSize().height) / 2 -200));
			visualizzaEventiCalcisticiId.setSize(800,400);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			visualizzaEventiCalcisticiId.setResizable(false);
			visualizzaEventiCalcisticiId.setLayout(new BorderLayout());
			visualizzaEventiCalcisticiId.setUndecorated(true);
			
			JPanel bottoni=new JPanel();
			JButton indietro =new JButton("Indietro");
			indietro.setFont(new Font("Georgia", Font.PLAIN, 18));
			indietro.setHorizontalAlignment(JLabel.CENTER);
			indietro.setForeground(Color.BLACK);
			bottoni.add(indietro);
			
			
			
			
			JPanel pannello = new JPanel();
			pannello.setLayout(null);
			JScrollPane scroll = new JScrollPane(pannello,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
			visualizzaEventiCalcisticiId.add(scroll);
			pannello.setLayout(new BorderLayout());
			pannello.add(bottoni,BorderLayout.NORTH);
			
			TitledBorder t=new TitledBorder(new EtchedBorder(), "Eventi calcistici");
			t.setTitleFont(new Font("Georgia", Font.ITALIC, 13));
			t.setTitleColor(Color.BLACK);
			pannello.setBorder(t);
			
			JPanel pannello1=new JPanel();
			TitledBorder t1=new TitledBorder(new EtchedBorder(), "Eventi calcistici non iniziati per Id stadio");
			t1.setTitleFont(new Font("Georgia", Font.ITALIC, 13));
			t1.setTitleColor(Color.BLACK);
			pannello1.setBorder(t1);
			
			JPanel rigoTitoli=new JPanel();
			rigoTitoli.setLayout(new GridLayout(1,6));
			JLabel l1=new JLabel(" SquadraCasa ");
			JLabel l2=new JLabel(" SquadraOspite ");
			JLabel l3=new JLabel(" Stadio ");
			JLabel l4=new JLabel(" Prezzo ");
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
			rigoTitoli.add(l1);
			rigoTitoli.add(l2);
			rigoTitoli.add(l3);
			rigoTitoli.add(l4);
			rigoTitoli.add(l6);
			rigoTitoli.add(l0);
			pannello1.add(rigoTitoli);
			
			ArrayList<EventoCalcistico> eventi;
			try {
				eventi = sistema.getEventiCalcisticiNonIniziatiPerId();
				pannello1.setLayout(new GridLayout(eventi.size()+1,1));
				pannello.add(pannello1,BorderLayout.CENTER);
				for(int i=0; i<eventi.size(); i++){
					EventoCalcistico ev=eventi.get(i);
	
					JPanel interno1=new JPanel();
					interno1.setLayout(new GridLayout(1,7));
					SimpleDateFormat sdf =new SimpleDateFormat("dd/MM/yyyy");
					String a=sdf.format(ev.getDataInizioEventoCalcistico().getTime());
					SimpleDateFormat sdf1 =new SimpleDateFormat("HH:mm:ss");
					String a1=sdf1.format(ev.getDataInizioEventoCalcistico().getTime());
					JLabel l7=new JLabel(ev.getPartita().getSquadraCasa());
					JLabel l8=new JLabel(ev.getPartita().getSquadraOspite());
					JLabel l9=new JLabel(ev.getStadio().getNomeStadio());
					
					DecimalFormat df = new DecimalFormat("#.##"); // 2 cifre decimali

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
					interno1.add(l7);
					interno1.add(l8);
					interno1.add(l9);
					interno1.add(l10);
					interno1.add(l12);
					interno1.add(l13);
					pannello1.add(interno1);
				}
			} 
			catch (DatiNonValidiException e1) {
				visualizzaEventiCalcisticiId.dispose();
				def=new DefaultFrame(new JLabel(e1.getMessage()),
						"Errore",340,new ImageIcon(imgURLnonOK),
						new UtenteFrame(sistema));
				def.setVisible(true);
			}
			
			class IndietroListener implements ActionListener {
				public void actionPerformed(ActionEvent e) {
					visualizzaEventiCalcisticiId.dispose();
					setVisible(true);
				}
			}	
			
			indietro.addActionListener(new IndietroListener());
			return visualizzaEventiCalcisticiId;
		}
	}
	
	public JFrame eventiCalcisticiNonIniziatiLessico() throws CloneNotSupportedException, DatiNonValidiException {
		
		if(sistema.getEventiCalcistici().size()==0)
			throw new DatiNonValidiException("Non ci sono eventi calcistici nel "+sistema.getNome());
		else if(sistema.getEventiNonIniziati().size()==0)
			throw new DatiNonValidiException("Non ci sono eventi calcistici non ancora iniziati nel "+sistema.getNome());
		else{
			setVisible(false);
		
			JFrame visualizzaEventiCalcisticiLessico=new JFrame();		
			Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
			visualizzaEventiCalcisticiLessico.setLocation(new Point((dimension.width - 
					visualizzaEventiCalcisticiLessico.getSize().width) / 2-400, 
			(dimension.height - visualizzaEventiCalcisticiLessico.getSize().height) / 2 -200));
			visualizzaEventiCalcisticiLessico.setSize(800,400);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			visualizzaEventiCalcisticiLessico.setResizable(false);
			visualizzaEventiCalcisticiLessico.setLayout(new BorderLayout());
			visualizzaEventiCalcisticiLessico.setUndecorated(true);
		
			JPanel bottoni=new JPanel();
			JButton indietro =new JButton("Indietro");
			indietro.setFont(new Font("Georgia", Font.PLAIN, 18));
			indietro.setHorizontalAlignment(JLabel.CENTER);
			indietro.setForeground(Color.BLACK);
			bottoni.add(indietro);
			
			JPanel pannello = new JPanel();
			pannello.setLayout(null);
			JScrollPane scroll = new JScrollPane(pannello,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
			visualizzaEventiCalcisticiLessico.add(scroll);
			pannello.setLayout(new BorderLayout());
			pannello.add(bottoni,BorderLayout.NORTH);
			
			TitledBorder t=new TitledBorder(new EtchedBorder(), "Eventi calcistici");
			t.setTitleFont(new Font("Georgia", Font.ITALIC, 13));
			t.setTitleColor(Color.BLACK);
			pannello.setBorder(t);
			
			JPanel pannello1=new JPanel();
			
			TitledBorder t1=new TitledBorder(new EtchedBorder(), "Eventi calcistici non iniziati in ordine lessicografico");
			t1.setTitleFont(new Font("Georgia", Font.ITALIC, 13));
			t1.setTitleColor(Color.BLACK);
			pannello1.setBorder(t1);
			
			JPanel rigoTitoli=new JPanel();
			rigoTitoli.setLayout(new GridLayout(1,6));
			JLabel l1=new JLabel(" SquadraCasa ");
			JLabel l2=new JLabel(" SquadraOspite ");
			JLabel l3=new JLabel(" Stadio ");
			JLabel l4=new JLabel(" Prezzo "); //poi verr‡ eliminta
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
			rigoTitoli.add(l1);
			rigoTitoli.add(l2);
			rigoTitoli.add(l3);
			rigoTitoli.add(l4);
			rigoTitoli.add(l6);
			rigoTitoli.add(l0);
			pannello1.add(rigoTitoli);
			
			ArrayList<EventoCalcistico> eventi;
			try {
				eventi = sistema.getEventiCalcisticiNonIniziatiPerLessico();
				pannello1.setLayout(new GridLayout(eventi.size()+1,1));
				pannello.add(pannello1,BorderLayout.CENTER);
				for(int i=0; i<eventi.size(); i++){
					EventoCalcistico ev=eventi.get(i);
					JPanel rigoEvento=new JPanel();
					rigoEvento.setLayout(new GridLayout(1,7));
					SimpleDateFormat sdf =new SimpleDateFormat("dd/MM/yyyy");
					String a=sdf.format(ev.getDataInizioEventoCalcistico().getTime());
					SimpleDateFormat sdf1 =new SimpleDateFormat("HH:mm:ss");
					String a1=sdf1.format(ev.getDataInizioEventoCalcistico().getTime());
					JLabel l7=new JLabel(ev.getPartita().getSquadraCasa());
					JLabel l8=new JLabel(ev.getPartita().getSquadraOspite());
					JLabel l9=new JLabel(ev.getStadio().getNomeStadio());
					
					DecimalFormat df = new DecimalFormat("#.##"); // 2 cifre decimali

					String s = df.format(ev.getStadio().getPrezzo());
					JLabel l10=new JLabel(s+" Ä"); //poi verr‡ eliminta
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
					rigoEvento.add(l7);
					rigoEvento.add(l8);
					rigoEvento.add(l9);
					rigoEvento.add(l10);
					rigoEvento.add(l12);
					rigoEvento.add(l13);
			
				
					pannello1.add(rigoEvento);
				}
			} 
			catch (DatiNonValidiException e1) {
				visualizzaEventiCalcisticiLessico.dispose();
				def=new DefaultFrame(new JLabel(e1.getMessage()),
						"Errore",340,new ImageIcon(imgURLnonOK),
						new UtenteFrame(sistema));
				def.setVisible(true);
			}
			
			class IndietroListener implements ActionListener {
				public void actionPerformed(ActionEvent e) {
					visualizzaEventiCalcisticiLessico.dispose();
					setVisible(true);
				}
			}	
			
			indietro.addActionListener(new IndietroListener());
			return visualizzaEventiCalcisticiLessico;
		}
	}

	public JFrame visualizzaAcquisti () throws DatiNonValidiException {
		
		if(sistema.getStoricoAcquisti().size()==0)
			throw new DatiNonValidiException(sistema.getUtenteCorrente().getNome() +" non hai effettuato nessun acquisto nel "+sistema.getNome());
		else{
		
			setVisible(false);
		
			JFrame visualizzaAcui=new JFrame();		
			Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
			visualizzaAcui.setLocation(new Point((dimension.width - 
					visualizzaAcui.getSize().width) / 2-400, 
			(dimension.height - visualizzaAcui.getSize().height) / 2 -200));
			visualizzaAcui.setSize(800,400);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			visualizzaAcui.setResizable(false);
			visualizzaAcui.setLayout(new BorderLayout());
			visualizzaAcui.setUndecorated(true);
			
			JPanel bottoni=new JPanel();
			JButton indietro =new JButton("Indietro");
			indietro.setFont(new Font("Georgia", Font.PLAIN, 18));
			indietro.setHorizontalAlignment(JLabel.CENTER);
			indietro.setForeground(Color.BLACK);
			JLabel le =new JLabel("Ecco lo storico dei tuoi acquisti");
			le.setFont(new Font("Georgia", Font.PLAIN, 18));
			le.setHorizontalAlignment(JLabel.CENTER);
			le.setForeground(Color.BLACK);
			bottoni.add(indietro);
			bottoni.add(le);		
		
			JPanel pannello = new JPanel();
			pannello.setLayout(null);
			JScrollPane scroll = new JScrollPane(pannello,
					JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
					JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
			visualizzaAcui.add(scroll);
			pannello.setLayout(new BorderLayout());
			pannello.add(bottoni,BorderLayout.NORTH);
			JPanel pannello1=new JPanel();
			TitledBorder t=new TitledBorder(new EtchedBorder(), "Acquisti effettuati");
			t.setTitleFont(new Font("Georgia", Font.ITALIC, 13));
			t.setTitleColor(Color.BLACK);
			pannello1.setBorder(t);
			
			ArrayList<Acquisto> acquisti = sistema.getStoricoAcquisti();
			pannello1.setLayout(new GridLayout(acquisti.size()+1,1));
			pannello.add(pannello1,BorderLayout.CENTER);
			JPanel rigoTitoli=new JPanel();
			rigoTitoli.setLayout(new GridLayout(1,9));
			JLabel l1=new JLabel(" Stadio ");
			JLabel l2=new JLabel(" SquadraCasa ");
			JLabel l3=new JLabel(" SquadraOspite ");
			JLabel l4=new JLabel(" DataInizio"); //poi verr‡ eliminta
			JLabel l6=new JLabel(" OraInizio");
			JLabel l0=new JLabel(" Settore ");
			JLabel l11=new JLabel(" Posto ");
			JLabel l12=new JLabel(" Prezzo ");
			JLabel l13=new JLabel(" Emesso il ");
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
			l11.setFont(new Font("Georgia", Font.PLAIN, 14));
			l11.setForeground(Color.BLACK);
			l11.setHorizontalAlignment(JLabel.CENTER);
			l12.setFont(new Font("Georgia", Font.PLAIN, 14));
			l12.setForeground(Color.BLACK);
			l12.setHorizontalAlignment(JLabel.CENTER);
			l13.setFont(new Font("Georgia", Font.PLAIN, 14));
			l13.setForeground(Color.BLACK);
			l13.setHorizontalAlignment(JLabel.CENTER);
			rigoTitoli.add(l1);
			rigoTitoli.add(l2);
			rigoTitoli.add(l3);
			rigoTitoli.add(l4);
			rigoTitoli.add(l6);
			rigoTitoli.add(l0);
			rigoTitoli.add(l11);
			rigoTitoli.add(l12);
			rigoTitoli.add(l13);
			pannello1.add(rigoTitoli);
			
			for(int i=acquisti.size()-1; i>=0; i--){
				Acquisto acq=acquisti.get(i);
		
				JPanel rigoAcqui=new JPanel();
				rigoAcqui.setLayout(new GridLayout(1,9));
				SimpleDateFormat sdf =new SimpleDateFormat("dd/MM/yyyy");
				String a=sdf.format(acq.getDataAcquisto().getTime());
				SimpleDateFormat sdf1 =new SimpleDateFormat("HH:mm:ss");
				String a1=sdf1.format(acq.getDataAcquisto().getTime());
				String b1=sdf.format(acq.getEventoCalcistico().getDataInizioEventoCalcistico().getTime());
				String b2=sdf1.format(acq.getEventoCalcistico().getDataInizioEventoCalcistico().getTime());
				JLabel l7=new JLabel(acq.getEventoCalcistico().getStadio().getNomeStadio());
				JLabel l8=new JLabel(acq.getEventoCalcistico().getPartita().getSquadraCasa());
				JLabel l9=new JLabel(acq.getEventoCalcistico().getPartita().getSquadraOspite());
				JLabel l10=new JLabel(b1); 
				JLabel l122=new JLabel(b2);
				JLabel l133=new JLabel(acq.getSettore().getNomeSettore());
				JLabel l14=new JLabel(acq.getPosto().getLettera()+"-"+acq.getPosto().getNumero());
				
				
				DecimalFormat df = new DecimalFormat("#.##"); 

				String s = df.format(acq.getPrezzoAcquisto());
				JLabel l15=new JLabel(s +" Ä");
				JLabel l16=new JLabel(a+"-"+a1);		
				l7.setFont(new Font("Georgia", Font.PLAIN, 14));
				l7.setForeground(Color.BLACK);
				l8.setFont(new Font("Georgia", Font.PLAIN, 14));
				l8.setForeground(Color.BLACK);
				l9.setFont(new Font("Georgia", Font.PLAIN, 14));
				l9.setForeground(Color.BLACK);
				l10.setFont(new Font("Georgia", Font.PLAIN, 14));
				l10.setForeground(Color.BLACK);
				l122.setFont(new Font("Georgia", Font.PLAIN, 14));
				l122.setForeground(Color.BLACK);
				l133.setFont(new Font("Georgia", Font.PLAIN, 14));
				l133.setForeground(Color.BLACK);
				l14.setFont(new Font("Georgia", Font.PLAIN, 14));
				l14.setForeground(Color.BLACK);
				l7.setHorizontalAlignment(JLabel.CENTER);
				l8.setHorizontalAlignment(JLabel.CENTER);
				l9.setHorizontalAlignment(JLabel.CENTER);
				l10.setHorizontalAlignment(JLabel.CENTER);
				l12.setHorizontalAlignment(JLabel.CENTER);
				l13.setHorizontalAlignment(JLabel.CENTER);
				l14.setHorizontalAlignment(JLabel.CENTER);
				l133.setHorizontalAlignment(JLabel.CENTER);
				l122.setHorizontalAlignment(JLabel.CENTER);
				l15.setFont(new Font("Georgia", Font.PLAIN, 14));
				l15.setForeground(Color.BLACK);
				l15.setHorizontalAlignment(JLabel.CENTER);
				l16.setFont(new Font("Georgia", Font.PLAIN, 14));
				l16.setForeground(Color.BLACK);
				l16.setHorizontalAlignment(JLabel.CENTER);
				rigoAcqui.add(l7);
				rigoAcqui.add(l8);
				rigoAcqui.add(l9);
				rigoAcqui.add(l10);
				rigoAcqui.add(l122);
				rigoAcqui.add(l133);
				rigoAcqui.add(l14);
				rigoAcqui.add(l15);
				rigoAcqui.add(l16);
				pannello1.add(rigoAcqui);
			}
			
			class IndietroListener implements ActionListener {
				public void actionPerformed(ActionEvent e) {
					visualizzaAcui.dispose();
					setVisible(true);
				}
			}	
			
			indietro.addActionListener(new IndietroListener());
			return visualizzaAcui;
		}
	}

	public JFrame visualizzaPrenotazioni () throws DatiNonValidiException {
		
		if(sistema.getStoricoPrenotazioni().size()==0)
			throw new DatiNonValidiException(sistema.getUtenteCorrente().getNome() +" non hai effettuato nessuna prenotazione "+sistema.getNome());
		else{
		
			setVisible(false);
		
			JFrame visualizzaPre=new JFrame();		
			Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
			visualizzaPre.setLocation(new Point((dimension.width - 
					visualizzaPre.getSize().width) / 2-400, 
			(dimension.height - visualizzaPre.getSize().height) / 2 -200));
			visualizzaPre.setSize(800,400);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			visualizzaPre.setResizable(false);
			visualizzaPre.setLayout(new BorderLayout());
			visualizzaPre.setUndecorated(true);
		
			JPanel bottoni=new JPanel();
			JButton indietro =new JButton("Indietro");
			indietro.setFont(new Font("Georgia", Font.PLAIN, 18));
			indietro.setHorizontalAlignment(JLabel.CENTER);
			indietro.setForeground(Color.BLACK);
			JLabel le =new JLabel("Ecco lo storico delle tue prenotazioni");
			le.setFont(new Font("Georgia", Font.PLAIN, 18));
			le.setHorizontalAlignment(JLabel.CENTER);
			le.setForeground(Color.BLACK);
			bottoni.add(indietro);
			bottoni.add(le);		
			
			JPanel pannello = new JPanel();
			pannello.setLayout(null);
			JScrollPane scroll = new JScrollPane(pannello,
					JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
					JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
			visualizzaPre.add(scroll);
			pannello.setLayout(new BorderLayout());
			pannello.add(bottoni,BorderLayout.NORTH);
			JPanel pannello1=new JPanel();
		
			TitledBorder t=new TitledBorder(new EtchedBorder(), "Prenotazioni effettuati");
			t.setTitleFont(new Font("Georgia", Font.ITALIC, 13));
			t.setTitleColor(Color.BLACK);
			pannello1.setBorder(t);
			
			ArrayList<Prenotazione> prenotazioni = sistema.getStoricoPrenotazioni();
		
			pannello1.setLayout(new GridLayout(prenotazioni.size()+1,1));
			pannello.add(pannello1,BorderLayout.CENTER);
			
			JPanel rigoTitoli=new JPanel();
			rigoTitoli.setLayout(new GridLayout(1,9));
			JLabel l1=new JLabel(" Stadio ");
			JLabel l2=new JLabel(" SquadraCasa ");
			JLabel l3=new JLabel(" SquadraOspite ");
			JLabel l4=new JLabel(" DataInizio"); 
			JLabel l6=new JLabel(" OraInizio");
			JLabel l0=new JLabel(" Settore ");
			JLabel l11=new JLabel(" Posto ");
			JLabel l12=new JLabel(" Emesso il ");
			JLabel l13=new JLabel(" Stato ");
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
			l11.setFont(new Font("Georgia", Font.PLAIN, 14));
			l11.setForeground(Color.BLACK);
			l11.setHorizontalAlignment(JLabel.CENTER);
			l12.setFont(new Font("Georgia", Font.PLAIN, 14));
			l12.setForeground(Color.BLACK);
			l12.setHorizontalAlignment(JLabel.CENTER);
			l13.setFont(new Font("Georgia", Font.PLAIN, 14));
			l13.setForeground(Color.BLACK);
			l13.setHorizontalAlignment(JLabel.CENTER);
			rigoTitoli.add(l1);
			rigoTitoli.add(l2);
			rigoTitoli.add(l3);
			rigoTitoli.add(l4);
			rigoTitoli.add(l6);
			rigoTitoli.add(l0);
			rigoTitoli.add(l11);
			rigoTitoli.add(l12);
			rigoTitoli.add(l13);
			pannello1.add(rigoTitoli);
			
			for(int i=prenotazioni.size()-1; i>=0; i--){
				Prenotazione pre=prenotazioni.get(i);
		
				JPanel rigoPrenotazione=new JPanel();
				rigoPrenotazione.setLayout(new GridLayout(1,9));
				SimpleDateFormat sdf =new SimpleDateFormat("dd/MM/yyyy");
				String a=sdf.format(pre.getDataPrenotazione().getTime());
				SimpleDateFormat sdf1 =new SimpleDateFormat("HH:mm:ss");
				String a1=sdf1.format(pre.getDataPrenotazione().getTime());
				String b1=sdf.format(pre.getEventoCalcistico().getDataInizioEventoCalcistico().getTime());
				String b2=sdf1.format(pre.getEventoCalcistico().getDataInizioEventoCalcistico().getTime());
				JLabel l7=new JLabel(pre.getEventoCalcistico().getStadio().getNomeStadio());
				JLabel l8=new JLabel(pre.getEventoCalcistico().getPartita().getSquadraCasa());
				JLabel l9=new JLabel(pre.getEventoCalcistico().getPartita().getSquadraOspite());
				JLabel l10=new JLabel(b1); 
				JLabel l122=new JLabel(b2);
				JLabel l133=new JLabel(pre.getSettore().getNomeSettore());
				JLabel l14=new JLabel(pre.getPosto().getLettera()+"-"+pre.getPosto().getNumero());
				JLabel l15=new JLabel(a+"-"+a1);
				JLabel l16=new JLabel(pre.getStato());
				l7.setFont(new Font("Georgia", Font.PLAIN, 14));
				l7.setForeground(Color.BLACK);
				l8.setFont(new Font("Georgia", Font.PLAIN, 14));
				l8.setForeground(Color.BLACK);
				l9.setFont(new Font("Georgia", Font.PLAIN, 14));
				l9.setForeground(Color.BLACK);
				l10.setFont(new Font("Georgia", Font.PLAIN, 14));
				l10.setForeground(Color.BLACK);
				l122.setFont(new Font("Georgia", Font.PLAIN, 14));
				l122.setForeground(Color.BLACK);
				l133.setFont(new Font("Georgia", Font.PLAIN, 14));
				l133.setForeground(Color.BLACK);
				l14.setFont(new Font("Georgia", Font.PLAIN, 14));
				l14.setForeground(Color.BLACK);
				l7.setHorizontalAlignment(JLabel.CENTER);
				l8.setHorizontalAlignment(JLabel.CENTER);
				l9.setHorizontalAlignment(JLabel.CENTER);
				l10.setHorizontalAlignment(JLabel.CENTER);
				l12.setHorizontalAlignment(JLabel.CENTER);
				l13.setHorizontalAlignment(JLabel.CENTER);
				l14.setHorizontalAlignment(JLabel.CENTER);
				l133.setHorizontalAlignment(JLabel.CENTER);
				l122.setHorizontalAlignment(JLabel.CENTER);
				l15.setFont(new Font("Georgia", Font.PLAIN, 14));
				l15.setForeground(Color.BLACK);
				l15.setHorizontalAlignment(JLabel.CENTER);
				l16.setFont(new Font("Georgia", Font.PLAIN, 14));
				l16.setForeground(Color.BLACK);
				l16.setHorizontalAlignment(JLabel.CENTER);
				rigoPrenotazione.add(l7);
				rigoPrenotazione.add(l8);
				rigoPrenotazione.add(l9);
				rigoPrenotazione.add(l10);
				rigoPrenotazione.add(l122);
				rigoPrenotazione.add(l133);
				rigoPrenotazione.add(l14);
				rigoPrenotazione.add(l15);
				rigoPrenotazione.add(l16);
				pannello1.add(rigoPrenotazione);
			}
			
			class IndietroListener implements ActionListener {
				public void actionPerformed(ActionEvent e) {
					visualizzaPre.dispose();
					setVisible(true);
				}
			}	
		
			indietro.addActionListener(new IndietroListener());
			return visualizzaPre;
		}
	}

}
