package no.hvl.dat107.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(schema = "dat107_oblig3")
public class Prosjekt {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int prosjekt_id;
	
	
	private String navn;
	private String beskrivelse;
	
	@OneToMany(mappedBy="prosjekt")
	private List<Prosjektdeltagelse> deltagelser;
	
	public Prosjekt() {}
	
	public Prosjekt(String navn, String beskrivelse) {
		this.navn = navn;
		this.beskrivelse = beskrivelse;
	}
	
	public void leggTilProsjektdeltagelse(Prosjektdeltagelse prosjektdeltagelse) {
        deltagelser.add(prosjektdeltagelse);
    }

    public void fjernProsjektdeltagelse(Prosjektdeltagelse prosjektdeltagelse) {
        deltagelser.remove(prosjektdeltagelse);
    }
    
    
    
    public int getId() {
    	return prosjekt_id;
    }
    
    public String getNavn() {
    	return navn;
    }
    
    public String getBeskrivelse() {
    	return beskrivelse;
    }
    
    public void setBeskrivelse(String nyBeskrivelse) {
    	this.beskrivelse = nyBeskrivelse;
    }
    
    public List<Prosjektdeltagelse> getDeltagelser(){
    	return deltagelser;
    }
    
    public void skrivUt() {
    	String ut = "Prosjekt: \n" + 
    				"Id: " + prosjekt_id + "\n" +
    				"Navn: " + navn + "\n" +
    				"Beskrivelse: " + beskrivelse + "\n";
    	System.out.println(ut);
    }
}
