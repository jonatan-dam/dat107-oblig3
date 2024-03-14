package no.hvl.dat107;

public class Main {

	public static void main(String[] args) {
		
		AnsattDAO ansattDAO = new AnsattDAO();
		
		Ansatt a = ansattDAO.finnAnsattMedId(2);
		
		System.out.println(a.toString());
		

	}
	
	

}
