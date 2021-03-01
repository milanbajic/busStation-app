package com.ftninformatika.jwd.support;

import com.ftninformatika.jwd.model.Rezervacija;
import com.ftninformatika.jwd.web.dto.RezervacijaDTO;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RezervacijaToRezervacijaDto implements Converter<Rezervacija, RezervacijaDTO> {

    @Override
    public RezervacijaDTO convert(Rezervacija rezervacija) {

        RezervacijaDTO rezervacijaDto = new RezervacijaDTO();
        rezervacijaDto.setId(rezervacija.getId());
        rezervacijaDto.setLinijaId(rezervacija.getLinija().getId());

        return rezervacijaDto;
    }

    public List<RezervacijaDTO> convert(List<Rezervacija> rezervacije) {

        List<RezervacijaDTO> rezervacijeDto = new ArrayList<>();
        for (Rezervacija rezervacija : rezervacije) {
        	rezervacijeDto.add(convert(rezervacija));
        }
        return rezervacijeDto;
    }
}