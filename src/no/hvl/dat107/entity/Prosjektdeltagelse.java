package no.hvl.dat107.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(schema = "dat107_oblig3")
public class Prosjektdeltagelse {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int deltagelse_id;
	
	private int timer = 0;
	private String rolle;
	
	@ManyToOne
	@JoinColumn(name="ansatt_id")
	private Ansatt ansatt;
	
	
	@ManyToOne
	@JoinColumn(name="prosjekt_id")
	private Prosjekt prosjekt;
	
	public Prosjektdeltagelse() {}
	
	public Prosjektdeltagelse(Ansatt ansatt, Prosjekt prosjekt, String rolle) {
		this.ansatt = ansatt;
		this.prosjekt = prosjekt;
		this.rolle = rolle;
		ansatt.leggTilProsjektdeltagelse(this);
		prosjekt.leggTilProsjektdeltagelse(this);
	}

	public void skrivUt() {
		ansatt.skrivUt();
		System.out.println("Rolle: " + rolle + "\n" + 
							"Timer: " + timer + "\n");		
	}
	
	
	public int getDeltagelse_id() {
		return deltagelse_id;
	}

	public void setDeltagelse_id(int deltagelse_id) {
		this.deltagelse_id = deltagelse_id;
	}

	public int getTimer() {
		return timer;
	}

	public void setTimer(int timer) {
		this.timer = timer;
	}

	public String getRolle() {
		return rolle;
	}

	public void setRolle(String rolle) {
		this.rolle = rolle;
	}

	public Ansatt getAnsatt() {
		return ansatt;
	}

	public void setAnsatt(Ansatt ansatt) {
		this.ansatt = ansatt;
	}

	public Prosjekt getProsjekt() {
		return prosjekt;
	}

	public void setProsjekt(Prosjekt prosjekt) {
		this.prosjekt = prosjekt;
	}

	@Override
	public String toString() {
		return "Prosjektdeltagelse [deltagelses_id=" + deltagelse_id + ", timer=" + timer + ", rolle=" + rolle
				+ ", ansatt=" + ansatt + ", prosjekt=" + prosjekt + "]";
	}
	
	
}
