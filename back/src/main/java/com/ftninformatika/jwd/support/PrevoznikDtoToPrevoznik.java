package com.ftninformatika.jwd.support;

import com.ftninformatika.jwd.model.Prevoznik;
import com.ftninformatika.jwd.service.PrevoznikService;
import com.ftninformatika.jwd.web.dto.PrevoznikDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PrevoznikDtoToPrevoznik implements Converter<PrevoznikDTO,Prevoznik> {
    
    @Autowired
    private PrevoznikService prevoznikService;

    @Override
    public Prevoznik convert(PrevoznikDTO prevoznikDto) {

		Long id = prevoznikDto.getId();
		Prevoznik prevoznik = id == null ? new Prevoznik() : prevoznikService.one(id).get();
		if (prevoznik != null) {
			prevoznik.setId(prevoznikDto.getId());
			prevoznik.setNaziv(prevoznikDto.getNaziv());
			prevoznik.setAdresa(prevoznikDto.getAdresa());
			prevoznik.setPib(prevoznikDto.getPib());
		}
		return prevoznik;
    }
}