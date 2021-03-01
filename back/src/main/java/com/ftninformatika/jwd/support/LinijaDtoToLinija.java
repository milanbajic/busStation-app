package com.ftninformatika.jwd.support;

import com.ftninformatika.jwd.model.Linija;
import com.ftninformatika.jwd.model.Prevoznik;
import com.ftninformatika.jwd.service.LinijaService;
import com.ftninformatika.jwd.service.PrevoznikService;
import com.ftninformatika.jwd.web.dto.LinijaDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class LinijaDtoToLinija implements Converter<LinijaDTO,Linija> {

    @Autowired
    private LinijaService linijaService;

    @Autowired
    private PrevoznikService prevoznikService;

    @Override
    public Linija convert(LinijaDTO linijaDto) {

		Prevoznik prevoznik = null;
		if (linijaDto.getPrevoznikId() != null) {
			prevoznik = prevoznikService.one(linijaDto.getPrevoznikId()).get();
		}
		if (prevoznik!=null) {
			Long id = linijaDto.getId();
			Linija linija = id == null ? new Linija() : linijaService.one(id).get();
			if (linija != null) {
				linija.setId(linijaDto.getId());
				linija.setBrojMesta(linijaDto.getBrojMesta());
				linija.setCenaKarte(linijaDto.getCenaKarte());
				linija.setVremePolaska(linijaDto.getVremePolaska());
				linija.setDestinacija(linijaDto.getDestinacija());
				linija.setPrevoznik(prevoznik);
			}
			return linija;
		} else {
			throw new IllegalStateException("Trying to attach to non-existant entities.");
		}
    }
}