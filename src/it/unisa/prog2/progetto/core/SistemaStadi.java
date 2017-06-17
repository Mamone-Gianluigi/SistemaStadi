package it.unisa.prog2.progetto.core;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import it.unisa.prog2.progetto.core.discount.*;
import it.unisa.prog2.progetto.exceptions.*;

/**
 * La classe Sistema si propone di realizzare un gruppo di acquisto, in cui sono presenti: 
 * - amministratori atti al caricamento e alla gestione degli stadi,delle partite,delle politiche di sconto e tuttò ciò che riguarda un evento calcistico;
 * - utenti registrati i quali possono consultare tutto ciò che riguarda gli eventi calcistici ed eventualmente acquistare o prenotare i posti relativi ad un determinato evento calcistico. 
 */
public class SistemaStadi implements Serializable {

	/**
	 * Crea un sistema stadi conservandone il nome e inizializzando la lista degli eventi calcisistici, la lista degli stadi, la lista delle partie,
	 * la lista delle politiche di sconto attive, la lista del carrello contenente le prenotazioni in corso e 
	 * infine la lista degli account registrati. 
	 * Di default verranno inseriti due account Amministratore. 
	 * Per il primo account amministratore, le credenziali saranno: username: admin - password: pass.
	 * Per il secondo account amministratore, le credenziali saranno; username: anto - password: mazz.  
	 * L'inserimento di questi due account iniziali è fondamentale per la creazione di altri account Amministratore. 
	 * Infatti, per ovvi motivi, non tutti gli utenti possono registrarsi in qualità di Amministratore; la maggior parte di essi
	 * utilizza il Sistema per consultare gli eventi calcistici ed eventualmente acquistarne o prenotarne i relativi posti, 
	 * non deve dunque inserire e/o gestire gli stadi,le partite,le politiche di sconto e tuttò ciò che riguarda un evento calcistico. 
	 * Tali compiti sono esclusivi degli amministratori. Quindi è anche compito di questi ultimi decidere chi debba gestire tutto ciò. 
	 * Perciò la creazione di un nuovo Amministratore può essere fatta solo da un utente che abbia effettuato il login come Amministratore.
	 * Non essendoci al momento della creazione del sistema nessun utente registrato, per permettere la creazione di nuovi amministratori, 
	 * ne vengono inseriti due di default.
	 * @param nome il nome del sistema (Precondizione: nome != null && nome != "")
	 */
	public SistemaStadi(String nome){
		this.nome=nome;
		
		nextAccountId = nextAccountId+1;
		account = new ArrayList<Account>();
		Amministratore defaultAdmin= new Amministratore("Gianluigi","Mamone","admin",
				"pass",nextAccountId);
		Amministratore defaultAdmin1= new Amministratore("Antonio","Mazzocca","anto",
				"mazz",nextAccountId);
		account.add(defaultAdmin);
		account.add(defaultAdmin1);
		corrente = null;
		eventiCalcistici= new ArrayList<EventoCalcistico>();
		stadi = new ArrayList<Stadio>();
		partite = new ArrayList<Partita>();
		carrello=new ArrayList<Prenotazione>();
		politicheSconto=new ArrayList<PoliticaDiSconto>();
		politicheSconto=new ArrayList<PoliticaDiSconto>();
		System.out.println("non è null");
		
		/**class CountDown implements ActionListener {
			public void actionPerformed(ActionEvent event){
				if(corrente!=null)
					if(corrente instanceof Utente){
						controllaScadenze();
						System.out.println("ciao");
					}
			}
		}
	
		ActionListener listener = new CountDown();
		Timer t = new Timer(1000, listener); 
		t.start();
		*/
		
	}
	
		
	
	/**
	 * Restituisce il nome del sistema
	 * @return il nome (Precondizione: nome != null && nome != "")
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Setta il nome del sistema
	 * @param nome il nome del sistema (Precondizione: nome != null && nome != "")
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Permette di svuotare il carrello, rinizializzando la lista delle prenotazioni in corso
	 */
	public void svuotaCarrello() {
		carrello = new ArrayList<Prenotazione>();
	}
	
	/**
	 * Setta il carrello dell'utente corrente
	 */
	public void aggiornaCarrelloUtenteCorrente() {
		Utente u=(Utente) corrente;
		u.setCarrello(carrello);
	}
	
	/**
	 * Restituisce il carrello dell'utente corrente
	 * @return il carrello
	 */
	public ArrayList<Prenotazione> getCarrelloUtenteCorrente() {
		if (corrente instanceof Utente){
			Utente u=(Utente) corrente;
			return u.getCarrello();
		}
		else
			return null;
	
	}

	/**
	 * Crea un nuovo account Amministratore, solo se consentito, aggiungendolo alla lista degli account registrati.
	 * Ricordiamo che se si vuole creare un nuovo account Amministratore, l'utente che lo crea deve a sua volta essere un Amministratore.
	 * @param nome il nome (Precondizione: nome != null && nome != "")
	 * @param cognome il cognome (Precondizione: cognome != null && cognome != "")
	 * @param user l'username (Precondizione: user != null && user != "")
	 * @param pass la password (Precondizione: pass != null && pass != "")
	 * @throws DatiNonValidiException
	 */
	public void creaAmministratore(String nome, String cognome, String user,String pass) throws DatiNonValidiException {
		
		// Vediamo se l'utente non ha tralasciato campi
		if (nome.equals("") || cognome.equals("") || user.equals("")
				|| pass.equals("")) 
			throw new DatiNonValidiException("Tutti i campi sono obbligatori!");
	
		// Poi vediamo se c'è già un altro account con lo stesso username
		for (Account e : account) 
			if (e.getUsername().equals(user))
				throw new DatiNonValidiException
					("Esiste già un account con lo stesso username");

		// L'utente non ha tralasciato campi, quindi possiamo procedere con la creazione dell'account
		if (corrente instanceof Amministratore){ // Se l'utente che sta creando l'account è amministratore
			nextAccountId=nextAccountId+1;
			account.add(new Amministratore(nome, cognome, user, pass, nextAccountId));
		}
		
		//Altrimenti non creiamo nulla
		return; 
	}
	
	/**
	 * Crea un nuovo account Utente, solo se consentito, aggiungendolo alla lista degli account registrati.
	 * @param nome il nome (Precondizione: nome != null && nome != "")
	 * @param cognome il cognome (Precondizione: cognome != null && cognome != "")
	 * @param user l'username (Precondizione: user != null && user != "")
	 * @param pass la password (Precondizione: pass != null && pass != "")
	 * @param categoria la categoria (Precondizione: categoria != null && categoria != "")
	 * @throws DatiNonValidiException
	 */
	public void creaUtente(String nome, String cognome,String user,String pass,String categoria) throws DatiNonValidiException {
		
		// Vediamo se l'utente non ha tralasciato campi
		if (nome.equals("") || cognome.equals("")|| user.equals("") 
				|| pass.equals("") || categoria==null)
			throw new DatiNonValidiException("Tutti i campi sono obbligatori");
		
		// Poi vediamo se c'è già un altro account con lo stesso username
		for (Account e : account)
			if (e.getUsername().equals(user)) 
				throw new DatiNonValidiException(
						"Esiste già  un account con lo stesso username");
		
		// L'utente non ha tralasciato campi, quindi possiamo procedere con la creazione dell'account
		nextAccountId=nextAccountId+1;
		account.add(new Utente(nome,cognome,user,pass,categoria,nextAccountId));
	}
	
	/**
	 * Effettua il login di un amministratore se i dati sono corretti
	 * @param user l'username (Precondizione: user != null && user != "")
	 * @param pass la password (Precondizione: pass != null && pass != "")
	 * @throws DatiNonValidiException
	 */
	public void loginAmministratore(String user, String pass) throws DatiNonValidiException {
		
		if (user.equals("") || pass.equals(""))
			throw new DatiNonValidiException("Tutti i campi sono obbligatori");
		
		for (Account e : account)
			if (e.getUsername().equals(user) && e.getPassword().equals(pass) && e instanceof Amministratore) {
				corrente = e;
				return;
			}
		throw new DatiNonValidiException();
	}

	/**
	 * Effettua il login di un utente se i dati sono corretti
	 * @param user l'username (Precondizione: user != null && user != "")
	 * @param pass la password (Precondizione: pass != null && pass != "")
	 * @throws DatiNonValidiException
	 */
	public void loginUtente(String user, String pass) throws DatiNonValidiException {
		
		if (user.equals("") || pass.equals(""))
			throw new DatiNonValidiException("Tutti i campi sono obbligatori");
		
		for (Account e : account)
			if (e.getUsername().equals(user) && e.getPassword().equals(pass)
					&& e instanceof Utente) {
				corrente = e;
				carrello=((Utente) e).getCarrello();
				return;
			}
		throw new DatiNonValidiException();
	}

	/**
	 * Effettua il logout di un account, aggiornando il carrello dell'utente corrente
	 */
	public void logout() {
		if(corrente instanceof Utente){
			aggiornaCarrelloUtenteCorrente();
			svuotaCarrello();
		}
		corrente = null;
	}

	/**
	 * Restituisce l'utente corrente se c'è, altrimenti null
	 * @return l'utente corrente
	 * @throws CloneNotSupportedException 
	 */
	public Utente getUtenteCorrente()  {
		if (corrente instanceof Utente)
			return (Utente) corrente;
		else
			return null;
	}
	
	/**
	 * Restituisce l'amministratore corrente se c'è, altrimenti null
	 * @return l'amministratore corrente
	 * @throws CloneNotSupportedException 
	 */
	public Amministratore getAdminCorrente() {
		if(corrente instanceof Amministratore) {
			return (Amministratore) corrente;
		}
		else
			return null;
	}
	
	/**
	 * Aggiunge un nuovo stadio alla lista degli stadi
	 * @param nome il nome dello stadio (Precondizione: nome != null && nome != "")
	 * @param città la città in cui si trova lo stadio (Precondizione: città != null && città != "")
	 * @param prezzo il prezzo di ogni posto dello stadio (Precondizione: prezzo != null && prezzo != "")
	 * @param nomeSett il nome del primo settore dello stadio (Precondizione: nomeSett != null && nomeSett != "")
	 * @param numFi il numero di file del primo settore dello stadio (Precondizione: numFil != null && numFil != "")
	 * @param numPosFil il numero di posti per ogni fila del primo dettore dello stadio (Precondizione: numPosFil!= null && numPosFil != "")
	 * @throws DatiNonValidiException
	 */
	public void aggiungiStadio(String nome,String città,String prezzo,String nomeSett,String numFi,String numPosFil) throws DatiNonValidiException{
		if (nome.equals("") || città.equals("") || prezzo.equals("") || nomeSett.equals("") ||
				numFi.equals("") || numPosFil.equals("")) 
			throw new DatiNonValidiException("Tutti i campi sono obbligatori!");
		stadi.add(new Stadio(nome,città,Double.parseDouble(prezzo),new Settore(nomeSett,numFi,numPosFil)));
	}
	
	/**
	 * Rimuove un determinato stadio dalla lista degli stadi
	 * @param stadio lo stadio da rimuovere (Precondizione; stadio!=null)
	 */
	public void rimuoviStadio(Stadio stadio){
		for(Stadio s:stadi)
			if(s.equals(stadio))
				stadi.remove(s);
	}
	
	/**
	 * Setta il prezzo di ogni posto per un determinato stadio
	 * @param s lo stadio (Precondizione: s!=null)
	 * @param prezz il prezzo di ogni posto (Precondizione: prezz != null && prezz != "")
	 * @throws DatiNonValidiException
	 */
	public void setPrezzoStadio(Stadio s,String prezz) throws DatiNonValidiException{
		if(prezz.equals("")) 
			throw new DatiNonValidiException("Tutti i campi sono obbligatori!");
		for(int i=0; i<stadi.size(); i++)
			if(stadi.get(i).equals(s))
				stadi.get(i).setPrezzo(Double.parseDouble(prezz));
	}


	/**
	 * Aggiunge, se consentito, un nuovo settore ad un determinato stadio della lista degli stadi 
	 * che non ha raggiunto il limite massimo di 6 settori
	 * @param stad lo stadio a cui aggiungere il settore (Precondizione: stad!=null)
	 * @param nome il nome del settore da aggiungere (Precondizione: nome != null && nome != "")
	 * @param numeroFile il numero di file del settore (Precondizione: numeroFile != null && numeroFile != "")
	 * @param numeroPostiFila il numero di posti per ogni fila del settore (Precondizione: numeroPostiFila != null && numeroPostiFila != "")
	 * @throws DatiNonValidiException
	 */
	public void aggiungiSettoreStadio(Stadio stad,String nome,String numeroFile,String numeroPostiFila) throws DatiNonValidiException{
		if (nome.equals("") || numeroFile.equals("")
				|| numeroPostiFila.equals("")) 
			throw new DatiNonValidiException("Tutti i campi sono obbligatori!");
		
		Settore s=new Settore(nome,numeroFile,numeroPostiFila);
		for(int i=0; i<stadi.size(); i++)
			if(stadi.get(i).equals(stad)){
				if(stadi.get(i).getSettori().size()>=6)
					throw new DatiNonValidiException("Raggiunto il numero massimo di settori");
				stadi.get(i).addSettore(s);	
			}
	}
	
	/**
	 * Rimuove, se consentito, un settore di un determinato stadio della lista degli stadi.
	 * Non sarà possibile rimuovere un settore di uno stadio nel caso in cui lo stadio ne ha solo uno.
	 * @param stad lo stadio (Precondizione: stad!=null)
	 * @param s il settore da rimuovere (Precondizione: s!=null)
	 * @throws DatiNonValidiException
	 */
	public void rimuoviSettoreStadio(Stadio stad,Settore s) throws DatiNonValidiException {
			for(int i=0; i<stadi.size(); i++)
				if(stadi.get(i).equals(stad)){
					if(stadi.get(i).getSettori().size()<=1)
						throw new DatiNonValidiException("Impossibile eliminare il settore");
					stadi.get(i).rimuoviSettore(s);	
				}
		}
	
	/**
	 * Aggiunge, se consentito, una partita alla lista delle partite
	 * @param squadraCasa la squadra che gioca in casa (Precondizione: squadraCasa != null && squadraCasa != "")
	 * @param squadraOspite la squadra ospite (Precondizione: squadraOspite != null && squadraOspite != "")
	 * @param lega la lega (Precondizione: lega != null && lega != "")
	 * @param campionato il campionato (Precondizione: campionato != null && campionato != "")
	 * @throws DatiNonValidiException
	 */
	public void aggiungiPartita(String squadraCasa ,String squadraOspite,String lega,String campionato) throws DatiNonValidiException{
		
		if (squadraCasa.equals("") || squadraOspite.equals("")|| 
				campionato.equals("") || lega.equals(""))
			throw new DatiNonValidiException("Tutti i campi sono obbligatori");
		partite.add(new Partita(squadraCasa, squadraOspite, campionato, lega));
	}
	
	/**
	 * Rimuove una determinata partita dalla lista delle partite
	 * @param partita la partita da rimuovere (Precondizione: partita!=null)
	 */
	public void rimuoviPartita(Partita partita){
		for(Partita p:partite)
			if(p.equals(partita))
				partite.remove(p);
	}
	
	
	/**
	 * Aggiunge, se consentito, un evento calcistico alla lista degli eventi calcistici
	 * @param partita la partita dell'evento calcistico da aggiungere (Precondizione: partita !=null)
	 * @param stadio lo stadio dell'evento calcistico da aggiungere (Precondizione: stadio!=null)
	 * @param anno l'anno dell'evento calcistico (Precondizione: anno != null && anno != "")
	 * @param mese il mese dell'evento calcistico (Precondizione: mese != null && mese!= "")
	 * @param giorno il giorno dell'evento calcistico (Precondizione: giorno != null && giorno != "")
	 * @param ora l'ora dell'evento calcistico (Precondizione: ora != null && ora != "")
	 * @param minuti i minuti dell'evento calcistico(Precondizione: minuti != null && minuti != "")
	 * @throws DatiNonValidiException
	 * @throws CloneNotSupportedException
	 */
	public void aggiungiEventoCalcistico(Partita partita, Stadio stadio, String anno, String mese, String giorno, String ora, String minuti) throws DatiNonValidiException, CloneNotSupportedException{
		
		if (anno.equals("") ||mese.equals("") 
				||giorno.equals("") || ora.equals("") || minuti.equals(""))
			throw new DatiNonValidiException("Tutti i campi sono obbligatori");
		
		if (Integer.parseInt(ora)<8 || Integer.parseInt(ora)>=22)
			throw new DatiNonValidiException("Impossibile inserire evento calcistico in tale orario");
		
		
		EventoCalcistico e=new EventoCalcistico(partita, stadio,Integer.parseInt(anno),Integer.parseInt(mese),Integer.parseInt(giorno),Integer.parseInt(ora),Integer.parseInt(minuti));
		boolean ok=controllaEventoCalcistico(e);
		if(ok==false)
			throw new DatiNonValidiException("L'evento calcistico si sovrappone ad un altro evento in programma");
		else
			eventiCalcistici.add(e);
	}
	
	/**
	 * Controlla se un determinato evento calcistico può essere aggiunto alla lista degli eventi calcistici.
	 * In particolare controlla, nel caso in cui la lista di eventi calcistici non risulta vuota, 
	 * se l'evento calcistico si sovrappone con l'orario con gli altri eventi calcistici presenti nella lista 
	 * catterizzati dallo stesso stadio e dalla stessa data in cui avviene l'evento. 
	 * Naturalmente se nella lista degli eventi calcistici non sono presenti eventi caratterizzati dallo stesso stadio e dalla stessa data di inizio evento, 
	 * l'evento calcistico passato come parametro potrà essere inserito nella lista degli eventi calcistici senza effettuare alcun controllo sull'orario
	 * @param e l'evento calcistico da controllare (Precondizione: e!=null)
	 * @return True se l'evento calcistico può essere aggiunto alla lista, False altrimenti
	 * @throws CloneNotSupportedException
	 */
	public boolean controllaEventoCalcistico(EventoCalcistico e) throws CloneNotSupportedException{
		if(eventiCalcistici.size()==0)
			return true;
		else 
		{
			Stadio s=e.getStadio();
			
			int a=e.getDataInizioEventoCalcistico().get(GregorianCalendar.YEAR);
			int m = e.getDataInizioEventoCalcistico().get(GregorianCalendar.MONTH);
			int g = e.getDataInizioEventoCalcistico().get(GregorianCalendar.DATE);
			int ora=e.getDataInizioEventoCalcistico().get(GregorianCalendar.HOUR_OF_DAY);
			int minuti=e.getDataInizioEventoCalcistico().get(GregorianCalendar.MINUTE);
	
		
			ArrayList<EventoCalcistico> eventiStadio=new ArrayList<EventoCalcistico>();
			for (EventoCalcistico ev:eventiCalcistici){
				Stadio st=ev.getStadio();
				if(st.equals(s)){
					int anno = ev.getDataInizioEventoCalcistico().get(GregorianCalendar.YEAR);
					if(anno==a){
						int mese = ev.getDataInizioEventoCalcistico().get(GregorianCalendar.MONTH);
						if(mese==m){
							int giorno = ev.getDataInizioEventoCalcistico().get(GregorianCalendar.DATE);
							if(giorno==g)
								eventiStadio.add(ev);
						}
					}
				}
				
			}
			
			if(eventiStadio.size()==0)
				return true;
			else{
				eventiStadio.add(e);
				
				@SuppressWarnings("unchecked")
				ArrayList<EventoCalcistico> aaa=ArrayListSort.sort(eventiStadio,ArrayListSort.DATA);
				/*for(EventoCalcistico ev:aaa){
					System.out.print(ev.getPartita().getSquadaCasa()+" - "+ev.getPartita().getSquadraOspite()+" - "+ev.getStadio().getNomeStadio()+" - "+ev.getStadio().getCapienza());
					
					System.out.print("----"+ev.getDataInizioEventoCalcistico().get(GregorianCalendar.HOUR)+"  ---- ");
					System.out.print(ev.getDataInizioEventoCalcistico().get(GregorianCalendar.MINUTE)+" \n");
				}*/
				if(eventiStadio.size()==2){
				
					int oraPre = aaa.get(0).getDataInizioEventoCalcistico().get(GregorianCalendar.HOUR_OF_DAY)+2;
					int minPre = aaa.get(0).getDataInizioEventoCalcistico().get(GregorianCalendar.MINUTE);
					int oraSuc = aaa.get(1).getDataInizioEventoCalcistico().get(GregorianCalendar.HOUR_OF_DAY);
					int minSuc = aaa.get(1).getDataInizioEventoCalcistico().get(GregorianCalendar.MINUTE);	
				
					if(oraPre<=oraSuc && minPre<=minSuc)
						return true;
					else 
						return false;
				}
				else{
									
					
					if(aaa.get(0).equals(e)){
						int oraSuc = aaa.get(1).getDataInizioEventoCalcistico().get(GregorianCalendar.HOUR_OF_DAY);
						int minSuc = aaa.get(1).getDataInizioEventoCalcistico().get(GregorianCalendar.MINUTE);
					
						if(ora+2<=oraSuc && minuti<=minSuc)
							return true;
					}
					if(aaa.get(aaa.size()-1).equals(e)){
						System.out.println("ecco");
						int oraPre = aaa.get(aaa.size()-2).getDataInizioEventoCalcistico().get(GregorianCalendar.HOUR_OF_DAY)+2;
						int minPre = aaa.get(aaa.size()-2).getDataInizioEventoCalcistico().get(GregorianCalendar.MINUTE);
						int oraSuc = aaa.get(aaa.size()-1).getDataInizioEventoCalcistico().get(GregorianCalendar.HOUR_OF_DAY);
						int minSuc = aaa.get(aaa.size()-1).getDataInizioEventoCalcistico().get(GregorianCalendar.MINUTE);	
				
						if(oraPre<=oraSuc && minPre<=minSuc)
							return true;
					}
					boolean ok=false;
					int i;
					for (i=1; i<aaa.size()-1; i++){
						if(aaa.get(i).equals(e)){
							int oraPre = aaa.get(i-1).getDataInizioEventoCalcistico().get(GregorianCalendar.HOUR_OF_DAY)+2;
							int minPre = aaa.get(i-1).getDataInizioEventoCalcistico().get(GregorianCalendar.MINUTE);
							int oraSuc = aaa.get(i+1).getDataInizioEventoCalcistico().get(GregorianCalendar.HOUR_OF_DAY)+2;
							int minSuc = aaa.get(i+1).getDataInizioEventoCalcistico().get(GregorianCalendar.MINUTE);
					
							if(oraPre<=ora && minPre<=minuti)
								if(oraSuc>=ora && minSuc>=minuti){
									ok=true;
									break;
								}	
						}	
					}
					return ok;
				}				
			}
		}		
	}
	
	/**
	 * Rimuove un determinato evento calcistico dalla lista degli eventi calcistici
	 * @param e l'evento calcistico da rimuovere (Precondizione: e!=null)
	 */
	public void rimuoviEventoCalcistico(EventoCalcistico e){
		for (EventoCalcistico ev:eventiCalcistici)
			if(ev.equals(e))
				eventiCalcistici.remove(ev);
	}
	
	/**
	 * Restituisce la lista degli acquisti effettuati dall'utente corrente
	 * @return lista degli acquisti dell'utente
	 */
	public ArrayList<Acquisto> getStoricoAcquisti() {
		if (corrente instanceof Utente)
			return ((Utente) corrente).getStoricoAcquisti();
		return null;
	}
	
	/**
	 * Restituisce la lista delle prenotazioni effettuate dall'utente corrente
	 * @return lista delle prenotazioni effettuate dall'utente
	 */
	public ArrayList<Prenotazione> getStoricoPrenotazioni() {
		if (corrente instanceof Utente)
			return ((Utente) corrente).getStoricoPrenotazioni();
		return null;
	}
	
	
	/**
	 * Restituisce la lista degli eventi calcistici
	 * @return la lista degli eventi calcistici
	 */
	public ArrayList<EventoCalcistico> getEventiCalcistici() {
		return eventiCalcistici;
	}

	/**
	 * Setta la lista degli eventi calcistici
	 * @param eventiCalcistici la lista degli eventi calcistici (Precondizione: eventiCalcistici!=null)
	 */
	public void setEventiCalcistici(ArrayList<EventoCalcistico> eventiCalcistici) {
		this.eventiCalcistici = eventiCalcistici;
	}

	/**
	 * Restituisce la lista delle partite
	 * @return la lista delle partite
	 */
	public ArrayList<Partita> getPartite() {
		return partite;
	}

	/**
	 * Setta la lista delle partite
	 * @param partite la lista delle partite (Precondizione: partite!=null)
	 */
	public void setPartite(ArrayList<Partita> partite) {
		this.partite = partite;
	}

	/**
	 * Restituisce la lista degli stadi
	 * @return la lista degli stadi
	 */
	public ArrayList<Stadio> getStadi() {
		return stadi;
	}

	/**
	 * Setta la lista degli stadi
	 * @param stadi la lista degli stadi (Precondizione: stadi!=null)
	 */
	public void setStadi(ArrayList<Stadio> stadi) {
		this.stadi = stadi;
	}

	
	/**
	 * Restituisce la lista degli account registrati
	 * @return la lista degli account
	 */
	public ArrayList<Account> getAccount() {
		return account;
	}

	/**
	 * Setta la lista degli account registrati
	 * @param account la lista degli account (Precondizione: account != null)
	 */
	public void setAccount(ArrayList<Account> account) {
		this.account = account;
	}
	
	/**
	 * Stampa le informazioni riguardanti la squadra che gioca in casa, la squadra ospite, il nome dello stadio e la capienza dello stadio 
	 * di tutti gli eventi calcistici presenti nella lista degli eventi calcistici
	 * @throws CloneNotSupportedException
	 */
	public void stampaEventiCalcistici() throws CloneNotSupportedException{
		
	
			for(EventoCalcistico ev:eventiCalcistici)
				System.out.println(ev.getPartita().getSquadraCasa()+" - "+ev.getPartita().getSquadraOspite()+" - "+ev.getStadio().getNomeStadio()+" - "+ev.getStadio().getCapienza());
	}
	
	/**
	 * Stampa le informazioni riguardanti il nome e la capienza di tutti gli stadi presenti nella lista degli stadi in base alla loro capienza
	 * @throws CloneNotSupportedException
	 */
	public void stampaStadiCapienza() throws CloneNotSupportedException{
		@SuppressWarnings("unchecked")
		ArrayList<Stadio> stad=ArrayListSort.sort(stadi,ArrayListSort.CAPIENZA);

		for(Stadio s:stad)
			System.out.println(s.getNomeStadio()+" - "+s.getCapienza()+" - ");
	}
	
	/**
	 * Stampa le informazioni riguardanti la squadra che gioca in casa,la squadra ospite, il nome dello stadio, la capienza dello stadio e la data di inizio
	 * di tutti gli eventi calcistici presenti nella lista degli eventi calcistici, in base alla loro data di inizio.
	 * @throws CloneNotSupportedException
	 */
	public void stampaEventiCalcisticiData() throws CloneNotSupportedException{
		@SuppressWarnings("unchecked")
		ArrayList<EventoCalcistico> ev=ArrayListSort.sort(eventiCalcistici,ArrayListSort.DATA);

		

		for(EventoCalcistico e:ev){
			System.out.print(e.getPartita().getSquadraCasa()+
					" - "+e.getPartita().getSquadraOspite()+" - "
					+e.getStadio().getNomeStadio()+" - "
					+e.getStadio().getCapienza());
			SimpleDateFormat sdf =new SimpleDateFormat("dd/MM/yyyy-HH:mm:ss");
			String a=sdf.format(e.getDataInizioEventoCalcistico().getTime());
			System.out.println(" - "+a);
		}
	}
	
	/**
	 * Stampa le informazioni riguardanti la squadra che gioca in casa,la squadra ospite, il nome dello stadio e la capienza dello stadio
	 * di tutti gli eventi calcistici presenti nella lista degli eventi calcistici, in base alla capienza dello stadio relativo ad ogni evento.
	 * @throws CloneNotSupportedException
	 * @throws DatiNonValidiException
	 */
	public void stampaEventiCalcisticiPerCapienza() throws CloneNotSupportedException, DatiNonValidiException {
		
	
		@SuppressWarnings("unchecked")
		ArrayList<Stadio> stad=ArrayListSort.sort(stadi,ArrayListSort.CAPIENZA);
		for(Stadio s:stad) 
			for(EventoCalcistico e:eventiCalcistici)
				if(e.getStadio().equals(s))
					System.out.println(e.getPartita().getSquadraCasa()+
							" - "+e.getPartita().getSquadraOspite()+" - "
							+e.getStadio().getNomeStadio()+" - "
							+e.getStadio().getCapienza());
	
	}
	
	/**
	 * Stampa le informazioni riguardanti la squadra che gioca in casa,la squadra ospite, il nome dello stadio e la capienza dello stadio 
	 * di tutti gli eventi calcistici caratterizzati dallo stadio passato come parametro presenti nella lista degli eventi calcistici. 
	 * @param s lo stadio (Precondizione: s!=null)
	 */
	public void stampaEventiCalcisticiDiUnoStadio(Stadio s) {
		
		for(EventoCalcistico e:eventiCalcistici)
			if(e.getStadio().equals(s))
				System.out.println(e.getPartita().getSquadraCasa()+
						" - "+e.getPartita().getSquadraOspite()+" - "
						+e.getStadio().getNomeStadio()+" - "
						+e.getStadio().getCapienza());
	
	}
	
	/**
	 * Aggiunge, se consentito, una politica di sconto alla lista di politiche di sconto. 
	 * Poiché ogni politica di sconto presente nella lista delle politiche di sconto è considerata attiva 
	 * è necessario fare un controllo affinché non si abbiano politiche di sconto uguali contemporaneamente attive.
	 * @param percentuale la percentuale di sconto (Precondizione: percentuale != null && percentuale != "")
	 * @param o la stringa che caratterizza la politica di sconto (Precondizione: o != null && o != "")
	 * @throws DatiNonValidiException
	 */
	public void aggiungiPoliticaSconto(String percentuale,String o) throws DatiNonValidiException{
		
		if(o.equals("") || percentuale.equals(""))
			throw new DatiNonValidiException("Tutti i campi sono obbligatori");
		else{
		     if(o.equals("Bambino") || o.equals("Studente") || 
		 		o.equals("Pensionato") ||o.equals("Adulto")){
		    	 ScontoCategoria c=new ScontoCategoria(o,Double.parseDouble(percentuale));
		    	 if(c instanceof ScontoCategoria){
		            for(PoliticaDiSconto ss:politicheSconto)
		            	if(ss instanceof ScontoCategoria)
		            		if(c.getCategoria().equals(((ScontoCategoria) ss).getCategoria()))
		            			throw new DatiNonValidiException("Politica di sconto già presente");
		    	 }
		    	 politicheSconto.add(c);
		     }
		else if(o.equals("Dom") || o.equals("Lun") || o.equals("Mar") || o.equals("Mer")
				||o.equals("Gio") || o.equals("Ven") || o.equals("Sab")){	
			ScontoGiorno g=new ScontoGiorno(o,Double.parseDouble(percentuale));
			if(g instanceof ScontoGiorno){
				for(PoliticaDiSconto ss:politicheSconto)
					if(ss instanceof ScontoGiorno)
						if(g.getDayOfWeek()==(((ScontoGiorno)ss).getDayOfWeek()))
							throw new DatiNonValidiException("Politica di sconto già presente");
			}
				politicheSconto.add(g);
		}
		else if(o.equals("Mattina") || o.equals("Pomeriggio") || o.equals("Sera")){
					ScontoOrario or=new ScontoOrario(o,Double.parseDouble(percentuale));
					if(or instanceof ScontoOrario){
						for(PoliticaDiSconto ss:politicheSconto)
							if(ss instanceof ScontoOrario)
								if(or.getFasciaGiornaliera().equals(((ScontoOrario) ss).getFasciaGiornaliera()))
									throw new DatiNonValidiException("Politica di sconto già presente");
					}
					politicheSconto.add(or);
				}
		}
	}
	
	
	/**
	 * Rimuove una determinata politica di sconto in base alla sua posizione nella lista delle politiche di sconto
	 * @param i l'indice relativo alla politica di sconto da rimuovere (Precondizione: i>=0)
	 */
	public void rimuoviPoliticaSconto(int i){
		
			politicheSconto.remove(i);		
	}

	/**
	 * Restituisce la lista delle politiche di sconto
	 * @return la lista delle politiche di sconto
	 */
	public ArrayList<PoliticaDiSconto> getPoliticheSconto(){
		return politicheSconto;
	}

	
	
	/**
	 * Se consentito, fa effetturare un acquisto all'utente corrente scalandogli il credito, 
	 * aggiungendo l'acquisto alla lista degli acquisti dell'utente e facendo risultare il relativo posto venduto.
	 * Nel caso siano attive una o più politiche di sconto nel momento dell'acquisto, 
	 * tutte potranno essere valide nel decrementare il prezzo di partenza e solo in seguito a ciò 
	 * verrà effettuato il controllo del credito dell'utente corrente per verificare la sua possibilità di acquisto.
	 * @param aq l'acquisto effettutato dall'utente (Precondizione: aq !=null)
	 * @throws DatiNonValidiException
	 */
	public void acquista(Acquisto aq) 
			throws DatiNonValidiException{		
		
		if (corrente instanceof Utente){
			double sconto=0.0;
			for (PoliticaDiSconto pol:politicheSconto)
				sconto = sconto+ pol.getSconto(aq);	
			double nuovoPrezzo=aq.getEventoCalcistico().getPrezzo()-((aq.getEventoCalcistico().getPrezzo()/100)*sconto);
			aq.setPrezzoAcquisto(nuovoPrezzo);		
			if(((Utente) corrente).getCredito()<aq.getPrezzoAcquisto())
				throw new DatiNonValidiException("Credito insufficiente");
			else{
				((Utente) corrente).scalaCredito(aq.getPrezzoAcquisto());
		 		((Utente) corrente).addAcquisto(aq);                  
				aq.getSettore().aggiungiPostoVenduto(aq.getPosto().getNumero(), aq.getPosto().getLettera());	
			}
		}
		else
			return ;
	}
	
	/**
	 * Permette all'utente corrente di effettuare una prenotazione 
	 * aggiungendola alla lista delle prenotazioni in corso ovvero il carrello e facendo risultare il relativo posto prenotato.
	 * @param p la prenotazione effettuata dall'utente (Precondizione: p!= null)
	 */
	public void prenota(Prenotazione p){	
		if (corrente instanceof Utente){
			carrello.add(p);               
			p.getSettore().aggiungiPostoPrenotato(p.getPosto().getNumero(), p.getPosto().getLettera());
		}
		else{
			return;
		}
	}

	/**
	 * Se consentito, fa effetturare più acquisti all'utente corrente scalandogli il credito, 
	 * aggiungendo gli acquisti alla lista degli acquisti dell'utente e facendo risultare i relativi posti venduti.
	 * Nel caso siano attive una o più politiche di sconto nel momento dell'acquisto, 
	 * tutte saranno valide nel decrementare il prezzo di partenza di ogni acquisto e solo in seguito a ciò 
	 * verrà effettuato il controllo del credito dell'utente corrente per verificare la sua possibilità di acquisto.
	 * @param acqui la lista degli acquisti effettuati dall'utente (Precondizione acqui != null)
	 * @throws DatiNonValidiException
	 */
	public void acquistaPiuBiglietti(ArrayList<Acquisto> acqui) throws DatiNonValidiException{		
		
		if (corrente instanceof Utente){
		
			for(Acquisto aq:acqui){
				double sconto=0.0;
				for (PoliticaDiSconto pol:politicheSconto)
					sconto = sconto+pol.getSconto(aq);
				double nuovoPrezzo=aq.getEventoCalcistico().getPrezzo()-((aq.getEventoCalcistico().getPrezzo()/100)*sconto);
				aq.setPrezzoAcquisto(nuovoPrezzo);
			}	
			double prezzoTotale=0.0;
			for(Acquisto a:acqui)
				prezzoTotale=prezzoTotale+a.getPrezzoAcquisto();
			if(((Utente) corrente).getCredito()<prezzoTotale)
				throw new DatiNonValidiException("Credito insufficiente");
			else
				for(Acquisto a:acqui){
					((Utente) corrente).scalaCredito(a.getPrezzoAcquisto());
					((Utente) corrente).addAcquisto(a);
					a.getSettore().aggiungiPostoVenduto(a.getPosto().getNumero(), a.getPosto().getLettera());	
				}
		}
		else{
			return;
		}
	}
	
	/**
	 * Permette all'utente corrente di effettuare più prenotazioni 
	 * aggiungendole alla lista delle prenotazioni in corso ovvero il carrello e facendo risultare i relativi posti prenotati.
	 * @param pre la lista delle prenotazioni effettuate dall'utente (Precondizione: pre != null)
	 */
	public void prenotaPiuBiglietti(ArrayList<Prenotazione> pre) {		
		
		if (corrente instanceof Utente){
			for(Prenotazione pr:pre){
				carrello.add(pr);
				pr.getSettore().aggiungiPostoPrenotato(pr.getPosto().getNumero(), pr.getPosto().getLettera());
			}
		}
		else{
			return;
		}
	}
	
	/**
	 * Permette all'utente corrente di procedere ad un determinato acquisto in seguito ad una prenotazione, 
	 * scalandogli il credito, aggiungendo l'acquisto alla lista degli acquisti dell'utente, 
	 * modificando lo stato della prenotazione a "Conclusa",cosicchè poi venga aggiunta alla lista delle prenotazioni dell'utente, 
	 * facendo risultare il relativo posto venduto e infine rimuovendo la prenotazione dalla lista delle prenotazioni in corso. 
	 * Prima di procedere all'acquisto, viene ricalcolato il prezzo in base a tutte le politiche di sconto attive in quel momento e applicabili,
	 * in più viene aggiunto 1 euro per aver usufruito del servizio della prenotazione. 
	 * Solo in seguito a ciò verrà effettuato il controllo del credito dell'utente corrente per verificare la sua possibilità di acquisto.
	 * @param i l'indice relativo alla prenotazione in corso da rimuovere in modo da procere poi all'acquisto (Precondizione: i>=0)
	 * @throws DatiNonValidiException
	 * @throws PostoIndisponibileException
	 */
	public void acquistaPrenotazione(int i) throws DatiNonValidiException ,PostoIndisponibileException{		
		
		if (corrente instanceof Utente){
			Prenotazione pre=carrello.get(i);
			pre.getPosto().setDisponibile(true);
			
			Acquisto aq;
			
			aq = new Acquisto((Utente)corrente,
				pre.getEventoCalcistico(),pre.getSettore(),pre.getPosto());
		
			
			double sconto=0.0;
			for (PoliticaDiSconto pol:politicheSconto)
				sconto = sconto + pol.getSconto(aq);
			double nuovoPrezzo=pre.getEventoCalcistico().getPrezzo()-((pre.getEventoCalcistico().getPrezzo()/100)*sconto);
			aq.setPrezzoAcquisto(nuovoPrezzo+1);
			
			if(((Utente) corrente).getCredito()<aq.getPrezzoAcquisto())
				throw new DatiNonValidiException("Credito insufficiente");
			else{
				((Utente) corrente).scalaCredito(aq.getPrezzoAcquisto());
				((Utente) corrente).addAcquisto(aq);      
				 pre.setStato(Prenotazione.CONCLUSA);
				((Utente) corrente).addPrenotazione(pre);
				aq.getSettore().aggiungiPostoVenduto(aq.getPosto().getNumero(), aq.getPosto().getLettera());	
				carrello.remove(i);
			}
			
		}
		else{
			System.out.println("non sono utente");
			return;
		}
	}
	
	/**
	 * Permette all'utente corrente di procedere a più acquisti in seguito a delle prenotazioni, 
	 * scalandogli il credito, aggiungendo gli acquisti alla lista degli acquisti dell'utente, 
	 * modificando lo stato delle prenotazioni a "Conclusa",cosicchè poi vengano aggiunte alla lista delle prenotazioni dell'utente, 
	 * facendo risultare i relativi posto venduto e infine rimuovendo le prenotazioni dalla lista delle prenotazioni in corso. 
	 * Prima di procedere all'acquisto, viene ricalcolato, per ogni acquisto, il prezzo in base a tutte le politiche di sconto attive in quel momento e applicabili,
	 * in più viene aggiunto 1 euro per aver usufruito del servizio della prenotazione. 
	 * Solo in seguito a ciò verrà effettuato il controllo del credito dell'utente corrente per verificare la sua possibilità di acquisto.
	 * @throws DatiNonValidiException
	 * @throws PostoIndisponibileException
	 */
	public void acquistaTutteLePrenotazioni() throws DatiNonValidiException, PostoIndisponibileException{		
		
		if(carrello.size()==0)
			throw new DatiNonValidiException("Non sono presenti prenotazioni nel carrello");
		else{
			if (corrente instanceof Utente){
				ArrayList<Acquisto> temp=new ArrayList<Acquisto>();
				for(Prenotazione pre:carrello){
					pre.getPosto().setDisponibile(true);
					Acquisto aq= new Acquisto((Utente)corrente,
							pre.getEventoCalcistico(),pre.getSettore(),pre.getPosto());
					double sconto=0.0;
					for (PoliticaDiSconto pol:politicheSconto)
						sconto = sconto +pol.getSconto(aq);
					System.out.println("Iniziale"+aq.getPrezzoAcquisto());
					System.out.println("Sconto" + sconto);
					double nuovoPrezzo=pre.getEventoCalcistico().getPrezzo()-((pre.getEventoCalcistico().getPrezzo()/100)*sconto);
					aq.setPrezzoAcquisto(nuovoPrezzo+1);	
					System.out.println("prezzo" + aq.getPrezzoAcquisto());
					temp.add(aq);
				}
			
				double prezzoTotale=0.0;
				for(Acquisto aaa:temp)
					prezzoTotale=prezzoTotale+aaa.getPrezzoAcquisto();
				if(((Utente) corrente).getCredito()<prezzoTotale)
					throw new DatiNonValidiException("Credito insufficiente");
				else{
					for(Acquisto aaa:temp){
						((Utente) corrente).scalaCredito(aaa.getPrezzoAcquisto());
						((Utente) corrente).addAcquisto(aaa);
					}
					for(Prenotazione pre:carrello){
						pre.setStato(Prenotazione.CONCLUSA);
						((Utente) corrente).addPrenotazione(pre);      
						pre.getSettore().aggiungiPostoVenduto(pre.getPosto().getNumero(), pre.getPosto().getLettera());
					
					}
					while(carrello.size()>0)
						carrello.remove(0);	
	
				}
			}
			else{
				return;
			}
		}
	}
	
	/**
	 * Permette all'utente corrente di aumentare il suo credito
	 * @param creditoDaAggiungere il credito da aggiungere (Precondizione: creditoDaAggiungere != null && creditoDaAggiungere != "")
	 * @throws DatiNonValidiException
	 */
	public void aggiungiCredito(String creditoDaAggiungere) throws DatiNonValidiException {
		if(creditoDaAggiungere.equals(""))
			throw new DatiNonValidiException("Tutti i campi sono obbligatori");
		if(Double.parseDouble(creditoDaAggiungere)<=0)
			throw new DatiNonValidiException("Valore non valido");
		if(corrente instanceof Utente)
			((Utente) corrente).addCredito(Double.parseDouble(creditoDaAggiungere));
	}


	/**
	 * Permette di rimuovere una determinata prenotazione dalla lista delle prenotazioni in corso.
	 * Lo stato della prenotazione così viene settato a "Annullata" e la prenotazione viene aggiunta alla lista delle prenotazioni dell'utente.
	 * @param i l'indice relativo alla prenotazione in corso da rimuovere (Precondizione: i>=0)
	 */
	public void rimuoviPrenotazione(int i){
		Prenotazione p=carrello.get(i);
		p.getSettore().rimuoviPostoPrenotato(p.getPosto().getNumero()
				,p.getPosto().getLettera());
		carrello.remove(i);
		p.setStato(Prenotazione.ANNULLATA);
		((Utente)corrente).addPrenotazione(p);
		
	}
	
	/**
	 * Resituisce la lista degli stadi ordinati in modo crescente in base al loro identificativo
	 * @return la lista degli stadi ordinata per id
	 * @throws CloneNotSupportedException
	 */
	public ArrayList<Stadio> getStadiId() throws CloneNotSupportedException{
		@SuppressWarnings("unchecked")
		ArrayList<Stadio> stad=ArrayListSort.sort(stadi,ArrayListSort.ID);
		return stad;
	}
	
	/**
	 * Restituisce l'incasso totale andando a controllare tutti gli acquisti di tutti gli utenti.
	 * @return l'incasso totale
	 */
	public double getIncassoTotale(){
		double incasso=0.0;
		for(Account a:account)
			if(a instanceof Utente)
				for(Acquisto ac:((Utente) a).getStoricoAcquisti())
					incasso=incasso+ac.getPrezzoAcquisto();
		
		return incasso;
	}
	
	/**
	 * Restituisce l'incasso totale di un determinato stadio andando a controllare 
	 * tutti gli acquisti relativi a quello stadio di tutti gli utenti
	 * @param s lo stadio (Precondizione: s!= null)
	 * @return l'incasso totale dello stadio
	 */
	public double getIncassoStadio(Stadio s) {
		
		double incasso=0.0;
		for(Stadio stad:stadi)
			if(stad.equals(s))
				for(Account a:account)
					if(a instanceof Utente)
						for(Acquisto ac:((Utente) a).getStoricoAcquisti())
							if(ac.getEventoCalcistico().getStadio().equals(s))
								incasso=incasso+ac.getPrezzoAcquisto();
	
		return incasso;
		
	}
	
	/**
	 * Aggiunge delle file ad un determinato settore di un determinato stadio
	 * @param stad lo stadio (Precondizione: stad !=null)
	 * @param sett il settore a cui aggiungere le file (Precondizione: sett !=null)
	 * @param n_file il numero di file da aggiungere nel settore (Precondizione: n_file != null && n_file != "")
	 * @throws DatiNonValidiException
	 */
	public void addFileSettore(Stadio stad,Settore sett,String n_file) throws DatiNonValidiException{
		if(n_file.equals(""))
			throw new DatiNonValidiException("Tutti i campi sono obbligatori");
		else
			for(Stadio s:stadi)
				if(s.equals(stad))
					s.aggiungiFileSettore(sett,Integer.parseInt(n_file));
	}
	
	/**
	 * Aggiunge dei posti per ogni fila di un determinato settore di un determinato stadio
	 * @param stad lo stadio (Precondizione: stad != null)
	 * @param sett il settore a cui aggiungere i posti per ogni sua fila (Precondizione: sett != null)
	 * @param n_posti il numero dei posti da aggiungere ad ogni fila del settore (Precondizione: n_posti != null && n_posti != "")
	 * @throws DatiNonValidiException
	 */
	public void addPostiPerFilaSettore(Stadio stad,Settore sett,String n_posti) throws DatiNonValidiException{
		if(n_posti.equals(""))
			throw new DatiNonValidiException("Tutti i campi sono obbligatori");
		else
			for(Stadio s:stadi)
				if(s.equals(stad))
					s.aggiungiPostiFilaSettore(sett,Integer.parseInt(n_posti));
	}
	
	/**
	 * Sottrae delle file da un determinato settore di un determinato stadio
	 * @param stad lo stadio (Precondizione: stad != null)
	 * @param sett il settore da cui sottrarre le file (Precondizione: sett!=null)
	 * @param n_file il numero di file da sottrare al settore (Precondizione: n_file != null && n_file != "")
	 * @throws DatiNonValidiException
	 */
	public void diminuisciFileSettore(Stadio stad,Settore sett,String n_file) throws DatiNonValidiException{
		if(n_file.equals(""))
			throw new DatiNonValidiException("Tutti i campi sono obbligatori");
		else
			for(Stadio s:stadi)
				if(s.equals(stad))
					s.diminuisciFileSettore(sett,Integer.parseInt(n_file));
	}
	
	/**
	 * Sottrae dei posti per ogni fila di un determinato settore di un determinato stadio
	 * @param stad lo stadio (Precondizione: stad != null)
	 * @param sett il settore da cui sottrarre i posti per ogni fila (Precondizione: sett != null)
	 * @param n_posti il numero di posti da sottrarre ad ogni fila del settore (Precondizione: n_posti != null && n_posti != "")
	 * @throws DatiNonValidiException
	 */
	public void diminiusciPostiPerFilaSettore(Stadio stad,Settore sett,String n_posti) throws DatiNonValidiException{
		if(n_posti.equals(""))
			throw new DatiNonValidiException("Tutti i campi sono obbligatori");
		else
			for(Stadio s:stadi)
				if(s.equals(stad))
					s.diminuisciPostiFilaSettore(sett,Integer.parseInt(n_posti));
	}
	

	/**
	 * Controlla se ogni prenotazione in corso risulta scaduta o no.
	 * In caso affermativo rende disponibile il posto precedentemente prenotato, 
	 * setta lo stato della prenotazione a "Scaduta" aggiungendola alla lista delle prenotazioni dell'utente corrente e infine
	 * rimuove la prenotazione dalla lista delle prenotazioni in corso.
	 */
	public 	void controllaScadenze(){
		
		for(int i=0; i<carrello.size(); i++){
			Prenotazione p=carrello.get(i);
			GregorianCalendar data=new GregorianCalendar();
			GregorianCalendar scadenza=p.getScadenza();
			if(scadenza.before(data)){
				Posto post=p.getPosto();
				Settore sett=p.getSettore();
				sett.rimuoviPostoPrenotato(post.getNumero(),post.getLettera());
			
				p.setStato(Prenotazione.SCADUTA);
				((Utente) corrente).addPrenotazione(p);
			
				carrello.remove(i);
				
			}
		}
	}
	
	/**
	 * Restituisce la lista degli eventi calcistici non ancora iniziati
	 * @return la lista degli eventi calcistici non iniziati
	 */
	public ArrayList<EventoCalcistico> getEventiNonIniziati(){
		
		ArrayList<EventoCalcistico> ev=new ArrayList<EventoCalcistico>();
		
		for (EventoCalcistico e:eventiCalcistici)
			if(e.getDataInizioEventoCalcistico().after(new GregorianCalendar()))
				ev.add(e);
		
		return ev;
	}



	/**
	 * Restituisce la lista degli eventi calcistici che possono essere ancora prenotati, ovvero non scaduti.
	 * Ricordiamo che la prenotazione scade 12 ore prima dell'inizio dell'evento calcistico.
	 * @return la lista degli eventi calcisitic prenotabili
	 */
	public ArrayList<EventoCalcistico> getEventiPrenotabili(){
		
		ArrayList<EventoCalcistico> ev=new ArrayList<EventoCalcistico>();
		GregorianCalendar dataOggi=new GregorianCalendar();
		dataOggi.add(GregorianCalendar.HOUR,+12);
		for (EventoCalcistico e:eventiCalcistici)
			if (e.getDataInizioEventoCalcistico().after(dataOggi))
				ev.add(e);
		
		return ev;
	}



	/**
	 * Restituisce la lista degli eventi calcistici caratterizzati da un determinato stadio
	 * @param s lo stadio (Precondizione: s!=null)
	 * @return la lista degli eventi calcistici di uno stadio
	 * @throws DatiNonValidiException
	 */
	public ArrayList<EventoCalcistico> getEventiCalcisticiDiUnoStadio(Stadio s) throws DatiNonValidiException {
		
		System.out.println(eventiCalcistici.size()+" " +stadi.size()+" " +partite.size());
	
		ArrayList<EventoCalcistico> ev=new ArrayList<EventoCalcistico>();
			
		for(EventoCalcistico e:eventiCalcistici)
			if(e.getStadio().equals(s))
				ev.add(e);
		
		if(ev.size()==0)
			throw new DatiNonValidiException("Non ci sono eventi calcistici nello stadio "+s.getNomeStadio());
		else
			return ev;
	}

	/**
	 * Restituisce la lista degli eventi calcistici di una determinata settimana a partire da un determinato giorno
	 * @param ann l'anno (Precondizione: ann != null && ann != "") 
	 * @param mes il mese (Precondizione: mese != null && mese != "")
	 * @param inizio il giorno (Precondizione: inizio != null && inizio != "")
	 * @return la lista degli eventi calcistici di una determinata settimana
	 * @throws DatiNonValidiException
	 */
	public ArrayList<EventoCalcistico> getEventiCalcisticiSettimana(String ann,String mes, String inizio) throws DatiNonValidiException {
	
		if (ann.equals("") || mes.equals("") || inizio.equals("") ) 
			throw new DatiNonValidiException("Tutti i campi sono obbligatori!");
		else{
			int anno=Integer.parseInt(ann);
			int mese=Integer.parseInt(mes)-1;
			int giornoInizio=Integer.parseInt(inizio);
			int giornoFine=giornoInizio+7;
		
			ArrayList<EventoCalcistico> eventi=new ArrayList<EventoCalcistico>();
		
			for (EventoCalcistico ev:eventiCalcistici){
				int annoEv=ev.getDataInizioEventoCalcistico().get(GregorianCalendar.YEAR);
				if(annoEv==anno){
					int meseEv=ev.getDataInizioEventoCalcistico().get(GregorianCalendar.MONTH);
					if(meseEv==mese){
						int giornoEv=ev.getDataInizioEventoCalcistico().get(GregorianCalendar.DATE);
							if(giornoEv>=giornoInizio && giornoEv<=giornoFine)
								eventi.add(ev);
					}
				}	
			}
			
			if(eventi.size()==0)
				throw new DatiNonValidiException("Non sono presenti eventi calcistici in questa settimana ");
			else
				return eventi;
		}
	}
	
	/**
	 * Restituisce la lista degli eventi calcistici non ancora iniziati in ordine cronologico
	 * @return la lista degli eventi calcistici non iniziati in ordin cronologico
	 * @throws CloneNotSupportedException
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<EventoCalcistico> getEventiCalcisticiNonIniziatiOrdineCronologico() throws CloneNotSupportedException{	
		ArrayList<EventoCalcistico> eventi=new ArrayList<EventoCalcistico>();
		eventi=ArrayListSort.sort(getEventiNonIniziati(),ArrayListSort.DATA);
		return eventi;
	}

	/**
	 * Restituisce la lista degli eventi calcistici non ancora iniziati in ordine crescente rispetto all’identificativo di stadio
	 * @return la lista degli eventi calcistici non iniziati in ordine in base all'id degli stadi
	 * @throws CloneNotSupportedException
	 * @throws DatiNonValidiException
	 */
	public ArrayList<EventoCalcistico> getEventiCalcisticiNonIniziatiPerId() throws CloneNotSupportedException, DatiNonValidiException{
		
		 
		if(getEventiNonIniziati().size()==0)
			throw new DatiNonValidiException("Non ci sono eventi calcistici non ancora iniziati");
		else
		{	
			ArrayList<Stadio> stad=getStadiId();
			ArrayList<EventoCalcistico> eventi=new ArrayList<EventoCalcistico>();
			for(Stadio s:stad) 
				for(EventoCalcistico ev:getEventiNonIniziati())
				if(ev.getStadio().equals(s))
					eventi.add((EventoCalcistico) ev.clone());
			return eventi;
		}
	}


	/**
	 * Restituisce la lista degli eventi calcistici non ancora iniziati in ordine lessicografico crescente rispetto al nome delle squadre che si affrontano
	 * @return la lista degli eventi calcistici non ancora iniziati in ordine lessicografico crescente
	 * @throws CloneNotSupportedException
	 * @throws DatiNonValidiException
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<EventoCalcistico> getEventiCalcisticiNonIniziatiPerLessico() throws CloneNotSupportedException, DatiNonValidiException{
		 
		ArrayList<EventoCalcistico> eventi=new ArrayList<EventoCalcistico>();
		
		eventi=ArrayListSort.sort(getEventiNonIniziati(),ArrayListSort.LESSICO);
		
		if(eventi.size()==0)
			throw new DatiNonValidiException("Non ci sono eventi calcistici non ancora iniziati");
		else
			return eventi;
	}


	/**
	 * Restituisce la lista di tutti gli eventi calcistici in ordine cronologico
	 * @return la lista di tutti gli eventi calcistici in ordine cronologico
	 * @throws CloneNotSupportedException
	 * @throws DatiNonValidiException
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<EventoCalcistico> getEventiCalcisticiPerData() throws CloneNotSupportedException, DatiNonValidiException{
		
		if(eventiCalcistici.size()==0)
			throw new DatiNonValidiException("Non sono presenti eventi calcistici");
		else{
			ArrayList<EventoCalcistico> eventi=new ArrayList<EventoCalcistico>();
			eventi=ArrayListSort.sort(eventiCalcistici,ArrayListSort.DATA);
			return eventi;
		}
	}


	/**
	 * Restituisce la lista di tutti gli eventi calcistici in ordine crescente rispetto alla capienza di stadio
	 * @return la lista di tutti gli eventi calcistici in ordine crescente rispetto alla capienza di stadio
	 * @throws CloneNotSupportedException
	 * @throws DatiNonValidiException
	 */
	public ArrayList<EventoCalcistico> getEventiCalcisticiPerCapienza() throws CloneNotSupportedException, DatiNonValidiException{
	
		if(eventiCalcistici.size()==0)
			throw new DatiNonValidiException("Non sono presenti eventi calcistici");
		else{
			@SuppressWarnings("unchecked")
			ArrayList<Stadio> stad=ArrayListSort.sort(stadi,ArrayListSort.CAPIENZA);
			ArrayList<EventoCalcistico> eventi=new ArrayList<EventoCalcistico>();
			for(Stadio s:stad) 
				for(EventoCalcistico ev:eventiCalcistici)
					if(ev.getStadio().equals(s))
						eventi.add((EventoCalcistico) ev.clone());
			
			return eventi;
		}
	}
	
	private String nome;
	private ArrayList<Account> account;
	private int nextAccountId;
	private Account corrente;
	private ArrayList<EventoCalcistico> eventiCalcistici;
	private ArrayList<Prenotazione> carrello;
	private ArrayList<Partita> partite;
	private ArrayList<Stadio> stadi;
	private ArrayList<PoliticaDiSconto> politicheSconto;
	private static final long serialVersionUID = 1L;
	
	
}
