package com.ftninformatika.jwd.model;

import javax.persistence.*;

@Entity
public class Rezervacija {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch=FetchType.EAGER)
    private Linija linija;

    public Rezervacija() {
        super();
    }

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

	public Linija getLinija() {
		return linija;
	}

	public void setLinija(Linija linija) {
		this.linija = linija;
		if (!linija.getRezervacije().contains(this)) {
			linija.getRezervacije().add(this);
		}
	}
}