package com.ftninformatika.jwd.service.impl;

import com.ftninformatika.jwd.model.Rezervacija;
import com.ftninformatika.jwd.model.Linija;
import com.ftninformatika.jwd.repository.RezervacijaRepository;
import com.ftninformatika.jwd.repository.LinijaRepository;
import com.ftninformatika.jwd.service.RezervacijaService;
import com.ftninformatika.jwd.support.RezervacijaDtoToRezervacija;
import com.ftninformatika.jwd.web.dto.RezervacijaDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JpaRezervacijaService implements RezervacijaService {

	@Autowired
	private RezervacijaRepository rezervacijaRepository;

	@Autowired
	private LinijaRepository linijaRepository;

	@Autowired
	private RezervacijaDtoToRezervacija toRezervacija;

	@Override
	public Optional<Rezervacija> one(Long id) {
		return rezervacijaRepository.findById(id);
	}

	@Override
	public List<Rezervacija> findAll() {
		return rezervacijaRepository.findAll();
	}

	@Override
	public List<Rezervacija> findByLinijaId(Long id) {
		return rezervacijaRepository.findByLinijaId(id);
	}

	@Override
	public Rezervacija save(RezervacijaDTO rezervacijaDto) {

		Rezervacija rezervacija = toRezervacija.convert(rezervacijaDto);
		Long linijaId = rezervacija.getLinija().getId();

		List<Rezervacija> sveRezervacije = rezervacijaRepository.findByLinijaId(linijaId);
		Linija linija = linijaRepository.getOne(linijaId);

		int ukupanBrojRezervacija = 0;

		for (Rezervacija r: sveRezervacije) {
			ukupanBrojRezervacija++;
		}

		int slobodnaMesta = rezervacija.getLinija().getBrojMesta();

		System.out.println("******************************");
		System.out.println("Ukupan broj rezervacija: " + ukupanBrojRezervacija);
		System.out.println("Ukupno slobodnih mesta: " + slobodnaMesta);
		System.out.println("******************************");

		if (slobodnaMesta != 0) {
			linija.setBrojMesta(linija.getBrojMesta() - 1);
			linijaRepository.save(linija);
			return rezervacija;
		} else {
			return null;
		}
	}
}