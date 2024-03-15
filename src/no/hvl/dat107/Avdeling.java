package no.hvl.dat107;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;


@Entity
@Table(schema = "dat107_oblig3")
public class Avdeling {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int avdeling_id;
	
	private String avdelingsnavn;
	
	@OneToOne // Betegner forholdet / kardinaliteten mellom sjef og avdeling
	@JoinColumn(name = "sjef_id") // Også nødvendig for å sette sjef_id som FK
	private Ansatt sjef; // Som refererer til den ansatte som er sjef for avdelingen
	
	@OneToMany(mappedBy = "avdeling", fetch = FetchType.EAGER) // Betegner forholdet / kardinaliteten mellom ansatt og avdeling
	private List<Ansatt> ansatte;
	
	

	public int getAvdeling_id() {
		return avdeling_id;
	}



	public void setAvdeling_id(int avdeling_id) {
		this.avdeling_id = avdeling_id;
	}



	public String getAvdelingsnavn() {
		return avdelingsnavn;
	}



	public void setAvdelingsnavn(String avdelingsnavn) {
		this.avdelingsnavn = avdelingsnavn;
	}



	public Ansatt getSjef() {
		return sjef;
	}



	public void setSjef(Ansatt sjef) {
		this.sjef = sjef;
	}



	public List<Ansatt> getAnsatte() {
		return ansatte;
	}



	public void setAnsatte(List<Ansatt> ansatte) {
		this.ansatte = ansatte;
	}



	@Override
	public String toString() {
		return "Avdeling: [avdeling_id = " + avdeling_id + ", Avdelingsnavn = " + avdelingsnavn + ", sjef = " + sjef + "]";
	}
	
	


}
