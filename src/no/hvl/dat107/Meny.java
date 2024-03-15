package no.hvl.dat107;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Meny {
	private AnsattDAO ansattDAO;
	private AvdelingDAO avdelingDAO;
	
	public Meny() {
		ansattDAO = new AnsattDAO();
		avdelingDAO = new AvdelingDAO();
	}
	
	public void start() {
		uendeligLokke();
	}
	
	public void uendeligLokke() {
		
		
		Scanner tastatur = new Scanner(System.in);
		
		System.out.println("\nHva ønsker du å gjøre? Tast: " + "\n" +
							"1: Søk etter ansatt ved ansattID" + "\n" +
							"2: Søk etter ansatt ved brukernavn" + "\n" +
							"3: Generer en liste av alle ansatte" + "\n" +
							"4: Oppdater en ansatt sin lønn" + "\n" +
							"5: Oppdater en ansatt sin stilling" + "\n" +
							"6: Legg til en ny ansatt" + "\n" +
							"7: Finn avdeling med ID" + "\n" + 
							"0: Avslutt programmet" + "\n");
		
		int nyttValg = Integer.parseInt(tastatur.nextLine());
		Ansatt ansA = null;
		Avdeling avdA = null;
		int ansattID;
		int avdelingID;
		BigDecimal lonn;
		String stilling;
		
		
		Ansatt nyA = new Ansatt("KR", "Kai", "Ronni", LocalDate.now(), "Daredevil", BigDecimal.valueOf(300000));
		
		
		switch(nyttValg) {
		case 0:
			System.exit(0);
			break;
		case 1:
			System.out.println("Tast inn ansattID på den ansatte du ønsker å søke på: ");
			ansattID = Integer.parseInt(tastatur.nextLine());
			ansA = ansattDAO.finnAnsattMedId(ansattID);
			System.out.println(ansA.toString());
			break;
		case 2:
			System.out.println("Tast inn brukernavn på den ansatte du ønsker å søke på: ");
			String brukernavn = tastatur.nextLine();
			ansA = ansattDAO.finnAnsattMedBrukernavn(brukernavn);
			System.out.println(ansA.toString());
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
			System.out.println("Ansatt: \n" + nyA.toString() + "\ner lagt til");
			break;
		case 7:
			System.out.println("Tast inn avdelingsID på avdelingen du ønsker å søke på: ");
			avdelingID = Integer.parseInt(tastatur.nextLine());
			avdA = avdelingDAO.finnAvdelingMedId(avdelingID);
			System.out.println(avdA.toString());
			break;
		default:
			uendeligLokke();
		}
		
		uendeligLokke();
		tastatur.close();
	}
}
