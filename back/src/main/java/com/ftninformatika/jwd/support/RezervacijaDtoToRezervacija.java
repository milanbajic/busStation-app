package com.ftninformatika.jwd.support;

import com.ftninformatika.jwd.model.Rezervacija;
import com.ftninformatika.jwd.model.Linija;
import com.ftninformatika.jwd.service.RezervacijaService;
import com.ftninformatika.jwd.service.LinijaService;
import com.ftninformatika.jwd.web.dto.RezervacijaDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RezervacijaDtoToRezervacija implements Converter<RezervacijaDTO,Rezervacija> {

	@Autowired
	private RezervacijaService rezervacijaService;

    @Autowired
    private LinijaService linijaService;

    @Override
    public Rezervacija convert(RezervacijaDTO rezervacijaDto) {

		Linija linija = null;
		if (rezervacijaDto.getLinijaId() != null) {
			linija = linijaService.one(rezervacijaDto.getLinijaId()).get();
		}
		if (linija!=null) {
			Long id = rezervacijaDto.getId();
			Rezervacija rezervacija = id == null ? new Rezervacija() : rezervacijaService.one(id).get();
			if (rezervacija != null) {
				rezervacija.setId(rezervacijaDto.getId());
				rezervacija.setLinija(linija);
			}
			return rezervacija;
		} else {
			throw new IllegalStateException("Trying to attach to non-existant entities.");
		}
    }
}