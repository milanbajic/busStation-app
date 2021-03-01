package com.ftninformatika.jwd.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
public class Linija {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int brojMesta;

    @Column
    private double cenaKarte;

    @Column
    private String vremePolaska;

    @Column
    private String destinacija;

    @ManyToOne(fetch=FetchType.EAGER)
    private Prevoznik prevoznik;

    @OneToMany(mappedBy = "linija", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Rezervacija> rezervacije = new ArrayList<>();

    public Linija() {
        super();
    }

	public Linija(Long id, int brojMesta, double cenaKarte, String vremePolaska, String destinacija) {
		super();
		this.id = id;
		this.brojMesta = brojMesta;
		this.cenaKarte = cenaKarte;
		this.vremePolaska = vremePolaska;
		this.destinacija = destinacija;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

	public int getBrojMesta() {
		return brojMesta;
	}

	public void setBrojMesta(int brojMesta) {
		this.brojMesta = brojMesta;
	}

	public double getCenaKarte() {
		return cenaKarte;
	}

	public void setCenaKarte(double cenaKarte) {
		this.cenaKarte = cenaKarte;
	}

	public String getVremePolaska() {
		return vremePolaska;
	}

	public void setVremePolaska(String vremePolaska) {
		this.vremePolaska = vremePolaska;
	}

	public String getDestinacija() {
		return destinacija;
	}

	public void setDestinacija(String destinacija) {
		this.destinacija = destinacija;
	}

	public Prevoznik getPrevoznik() {
		return prevoznik;
	}

	public void setPrevoznik(Prevoznik prevoznik) {
		this.prevoznik = prevoznik;
		if(!prevoznik.getLinije().contains(this)) {
			prevoznik.getLinije().add(this);
		}
	}
	
	public List<Rezervacija> getRezervacije() {
		return rezervacije;
	}

	public void setRezervacija(List<Rezervacija> rezervacije) {
		this.rezervacije = rezervacije;
	}
	
	public void dodajRezervaciji(Rezervacija rezervacija) {
		if (rezervacija.getLinija() != this) {
			rezervacija.setLinija(this);
		}
		rezervacije.add(rezervacija);
	}
}