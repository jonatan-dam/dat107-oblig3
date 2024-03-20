package no.hvl.dat107;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import no.hvl.dat107.dao.AnsattDAO;
import no.hvl.dat107.dao.AvdelingDAO;
import no.hvl.dat107.dao.ProsjektDAO;
import no.hvl.dat107.entity.Ansatt;
import no.hvl.dat107.entity.Avdeling;
import no.hvl.dat107.entity.Prosjekt;

public class Meny {
	private AnsattDAO ansattDAO;
	private AvdelingDAO avdelingDAO;
	private ProsjektDAO prosjektDAO;
	
	public Meny() {
		ansattDAO = new AnsattDAO();
		avdelingDAO = new AvdelingDAO();
		prosjektDAO = new ProsjektDAO();
	}
	
	public void start() {
		uendeligLokke();
	}
	
	public void uendeligLokke() {
		
		
		Scanner tastatur = new Scanner(System.in);
		
		System.out.println("\nHva ønsker du å gjøre? Tast: " + "\n" +
		
							"Handlinger på ansatt: \n" +
							"1: Søk etter ansatt ved ansattID" + "\n" +
							"2: Søk etter ansatt ved brukernavn" + "\n" +
							"3: Generer en liste av alle ansatte" + "\n" +
							"4: Oppdater en ansatt sin lønn" + "\n" +
							"5: Oppdater en ansatt sin stilling" + "\n" +
							"6: Legg til en ny ansatt" + "\n\n" +
							"7: Føre timer for ansatt på prosjekt" + "\n" + 
							
							"Handlinger på avdeling: \n" +
							"8: Finn avdeling med ID" + "\n" + 
							"9: List alle ansatte i en avdeling" + "\n" + 
							"10: Oppdatere avdeling på en ansatt" + "\n" + 
							"11: Opprett en ny avdeling" + "\n\n" + 
							
							"Handlinger på prosjekt: \n" +
							"12: Legg til et nytt prosjekt" + "\n" + 
							"13: Skriv ut all info om prosjektet" + "\n" + 
							"14: Søk etter prosjekt ved ID" + "\n" + 
							"15: Skriv ut all informasjon om et prosjekt" + "\n\n" + 
							
							"0: Avslutt programmet" + "\n");
		
		int nyttValg = Integer.parseInt(tastatur.nextLine());
		Ansatt ansA = null;
		Avdeling avdA = null;
		Prosjekt proA = null;
		Avdeling Oslo = avdelingDAO.finnAvdelingMedId(1);
		Avdeling Stavanger = avdelingDAO.finnAvdelingMedId(2);
		Avdeling Bergen = avdelingDAO.finnAvdelingMedId(3);
		int ansattID;
		int avdelingID;
		int prosjektID;
		int deltagelsesID;
		int timer;
		String avdelingsnavn;
		BigDecimal lonn;
		String stilling;
		
		
		Ansatt nyA = new Ansatt("KR", "Kai", "Ronni", LocalDate.now(), "Daredevil", BigDecimal.valueOf(300000), Oslo);
		Prosjekt nyP = new Prosjekt("Nytt prosjekt", "Beskrivelse av et nytt prosjekt");
		
		
		switch(nyttValg) { 
		case 0:
			System.exit(0);
			break;
		case 1:
			System.out.println("Tast inn ansattID på den ansatte du ønsker å søke på: ");
			ansattID = Integer.parseInt(tastatur.nextLine());
			ansA = ansattDAO.finnAnsattMedId(ansattID);
			ansA.skrivUt();
			break;
		case 2:
			System.out.println("Tast inn brukernavn på den ansatte du ønsker å søke på: ");
			String brukernavn = tastatur.nextLine();
			ansA = ansattDAO.finnAnsattMedBrukernavn(brukernavn);
			ansA.skrivUt();
			break;
		case 3:
			List<Ansatt> ansatte = ansattDAO.listAlleAnsatte();
			System.out.println(ansatte.toString());
			break;
		case 4:
			System.out.println("Tast inn ID på den ansatte du ønsker å endre lønnen til: ");
			ansattID = Integer.parseInt(tastatur.nextLine());
			System.out.println("Tast inn ny lønn: ");
			lonn = BigDecimal.valueOf(Double.parseDouble(tastatur.nextLine()));
			ansattDAO.oppdaterAnsattLonn(ansattID, lonn);
			System.out.println("Ny lønn til ansattID" + ansattID + "er " + lonn);
			break;
		case 5:
			System.out.println("Tast inn ID på den ansatte du ønsker å endre stillingen til: ");
			ansattID = Integer.parseInt(tastatur.nextLine());
			System.out.println("Tast inn ny stilling: ");
			stilling = tastatur.nextLine();
			ansattDAO.oppdaterAnsattStilling(ansattID, stilling);
			System.out.println("Ny stilling til ansa=tID" + ansattID + "er " + stilling);
			break;
		case 6:
			ansattDAO.leggTilAnsatt(nyA);
			nyA.skrivUt();
			System.out.println("er lagt til");
			break;
		case 7:
			System.out.println("Tast inn deltagelsesID på prosjektdeltagelsen du ønsker å føre timer på");
			deltagelsesID = Integer.parseInt(tastatur.nextLine());
			System.out.println("Tast inn antall timer");
			timer = Integer.parseInt(tastatur.nextLine());
			ansattDAO.foereTimerPaaProsjekt(deltagelsesID, timer);
			break;
		case 8:
			System.out.println("Tast inn avdelingsID på avdelingen du ønsker å søke på: ");
			avdelingID = Integer.parseInt(tastatur.nextLine());
			avdA = avdelingDAO.finnAvdelingMedId(avdelingID);
			avdA.skrivUt();
			break;
		case 9:
			System.out.println("Tast inn avdelingsID på avdelingen du ønsker å se ansatte: ");
			avdelingID = Integer.parseInt(tastatur.nextLine());
			avdelingDAO.listAlleAnsatte(avdelingID);
			break;
		case 10:
			System.out.println("Tast inn ansattID på den ansatte du ønsker å endre avdelingen til: ");
			ansattID = Integer.parseInt(tastatur.nextLine());
			System.out.println("Tast inn avdelingID på avdelingen du ønsker å overføre den ansatte til: ");
			avdelingID = Integer.parseInt(tastatur.nextLine());
			ansattDAO.endreAvdeling(ansattID, avdelingID);
			break;
		case 11:
			System.out.println("Tast inn avdelingsnavn på den nye avdelingen: ");
			avdelingsnavn = tastatur.nextLine();
			System.out.println("Tast inn ansattID på den ansatte du ønsker å gjøre til sjef på avdeling " + avdelingsnavn + ": ");
			ansattID = Integer.parseInt(tastatur.nextLine());
			avdelingDAO.lagreNyAvdeling(avdelingsnavn, ansattID);
			break;
		case 12:
			prosjektDAO.leggTilProsjekt(nyP);
			nyP.skrivUt();
			System.out.println("er lagt til");
			break;
		case 13:
			System.out.println("Tast inn prosjektetID til prosjektet du ønsker å skrive ut: ");
			prosjektID = Integer.parseInt(tastatur.nextLine());
			prosjektDAO.skrivUtAlt(prosjektID);
			break;
		case 14:
			System.out.println("Tast inn prosjektetID til prosjektet du ønsker å lete etter: ");
			prosjektID = Integer.parseInt(tastatur.nextLine());
			proA = prosjektDAO.finnProsjektMedId(prosjektID);
			proA.skrivUt();
			break;
		case 15:
			System.out.println("Tast inn prosjektID på prosjektet du ønsker å skrive ut: ");
			prosjektID = Integer.parseInt(tastatur.nextLine());
			prosjektDAO.skrivUtAlt(prosjektID);
		default:
			uendeligLokke();
		}
		
		uendeligLokke();
		tastatur.close();
	}
}
