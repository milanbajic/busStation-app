package com.ftninformatika.jwd.service;

import java.util.List;
import java.util.Optional;

import com.ftninformatika.jwd.model.Rezervacija;
import com.ftninformatika.jwd.web.dto.RezervacijaDTO;

public interface RezervacijaService {

	Optional<Rezervacija> one(Long id);

    List<Rezervacija> findAll();

    Rezervacija save(RezervacijaDTO rezervacijaDto);

    List<Rezervacija> findByLinijaId(Long id);
}