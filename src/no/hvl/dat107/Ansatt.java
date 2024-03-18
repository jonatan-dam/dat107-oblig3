package no.hvl.dat107;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(schema = "dat107_oblig3")
public class Ansatt {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ansatt_id;
	
	private String brukernavn;
	private String fornavn;
	private String etternavn;
	private LocalDate ansettelsesdato;
	private String stilling;
	private BigDecimal maanedsloonn;
	
	
	@ManyToOne // Betegner forholdet / kardinaliteten mellom ansatt og avdeling
	@JoinColumn(name = "avdeling_id") // Også nødvendig for å sette avdeling_id som FK
	private Avdeling avdeling; // Som refererer til avdelingen den ansatte jobber i
	
	
	public Ansatt() {}
	
	public Ansatt(String brukernavn, String fornavn, String etternavn, 
			      LocalDate ansettelsesdato, String stilling, BigDecimal maanedsloonn, Avdeling avdeling) {
		
		this.brukernavn = brukernavn;
		this.fornavn = fornavn;
		this.etternavn = etternavn;
		this.ansettelsesdato = ansettelsesdato; 
		this.stilling = stilling;
		this.maanedsloonn = maanedsloonn;
		this.avdeling = avdeling;
		
	}

	public int getAnsatt_id() {
		return ansatt_id;
	}

	public void setAnsatt_id(int ansatt_id) {
		this.ansatt_id = ansatt_id;
	}

	public String getBrukernavn() {
		return brukernavn;
	}

	public void setBrukernavn(String brukernavn) {
		this.brukernavn = brukernavn;
	}

	public String getFornavn() {
		return fornavn;
	}

	public void setFornavn(String fornavn) {
		this.fornavn = fornavn;
	}

	public String getEtternavn() {
		return etternavn;
	}

	public void setEtternavn(String etternavn) {
		this.etternavn = etternavn;
	}

	public LocalDate getAnsettelsesdato() {
		return ansettelsesdato;
	}

	public void setAnsettelsesdato(LocalDate ansettelsesdato) {
		this.ansettelsesdato = ansettelsesdato;
	}

	public String getStilling() {
		return stilling;
	}

	public void setStilling(String stilling) {
		this.stilling = stilling;
	}

	public BigDecimal getMaanedsloonn() {
		return maanedsloonn;
	}

	public void setMaanedsloonn(BigDecimal maanedsloonn) {
		this.maanedsloonn = maanedsloonn;
	}
	
	public Avdeling getAvdeling() {
		return avdeling;
	}
	
	public void setAvdeling(Avdeling avdeling) {
		this.avdeling = avdeling;
	}

	@Override
	public String toString() {
		return "Ansatt [ansatt_id=" + ansatt_id + ", brukernavn=" + brukernavn + ", fornavn=" + fornavn + ", etternavn="
				+ etternavn + ", ansettelsesdato=" + ansettelsesdato + ", stilling=" + stilling + ", maanedsloonn="
				+ maanedsloonn + ", avdeling=" + avdeling.getAvdelingsnavn() + "]";
	}
	
	
}
