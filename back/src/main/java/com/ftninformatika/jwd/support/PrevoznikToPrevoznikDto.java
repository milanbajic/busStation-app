package com.ftninformatika.jwd.support;

import com.ftninformatika.jwd.model.Prevoznik;
import com.ftninformatika.jwd.web.dto.PrevoznikDTO;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PrevoznikToPrevoznikDto implements Converter<Prevoznik, PrevoznikDTO> {

    @Override
    public PrevoznikDTO convert(Prevoznik prevoznik) {

        PrevoznikDTO prevoznikDto = new PrevoznikDTO();
        prevoznikDto.setId(prevoznik.getId());
        prevoznikDto.setAdresa(prevoznik.getAdresa());
        prevoznikDto.setNaziv(prevoznik.getNaziv());
        prevoznikDto.setPib(prevoznik.getPib());

        return prevoznikDto;
    }

    public List<PrevoznikDTO> convert(List<Prevoznik> prevoznici) {

        List<PrevoznikDTO> prevozniciDto = new ArrayList<>();
        for (Prevoznik prevoznik : prevoznici) {
        	prevozniciDto.add(convert(prevoznik));
        }
        return prevozniciDto;
    }
}