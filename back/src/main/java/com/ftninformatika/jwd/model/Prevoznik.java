package com.ftninformatika.jwd.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
public class Prevoznik {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String naziv;

    @Column
    private String adresa;

    @Column(unique = true, nullable = false)
    private String pib;

    @OneToMany(mappedBy = "prevoznik", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Linija> linije = new ArrayList<>();

    public Prevoznik() {}

	public Prevoznik(Long id, String naziv, String adresa, String pib) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.adresa = adresa;
		this.pib = pib;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getPib() {
		return pib;
	}

	public void setPib(String pib) {
		this.pib = pib;
	}

	public List<Linija> getLinije() {
		return linije;
	}

	public void setLinije(List<Linija> linije) {
		this.linije = linije;
	}
	
	public void dodajLinije(Linija linija) {
		if (linija.getPrevoznik() != this) {
			linija.setPrevoznik(this);
		}
		linije.add(linija);
	}
}