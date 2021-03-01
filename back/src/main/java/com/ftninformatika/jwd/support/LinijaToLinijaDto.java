package com.ftninformatika.jwd.support;

import com.ftninformatika.jwd.model.Linija;
import com.ftninformatika.jwd.web.dto.LinijaDTO;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class LinijaToLinijaDto implements Converter<Linija, LinijaDTO> {

    @Override
    public LinijaDTO convert(Linija linija) {

        LinijaDTO linijaDto = new LinijaDTO();

        linijaDto.setId(linija.getId());
        linijaDto.setBrojMesta(linija.getBrojMesta());
        linijaDto.setCenaKarte(linija.getCenaKarte());
        linijaDto.setVremePolaska(linija.getVremePolaska());
        linijaDto.setDestinacija(linija.getDestinacija());

        linijaDto.setPrevoznikId(linija.getPrevoznik().getId());
        linijaDto.setPrevoznikNaziv(linija.getPrevoznik().getNaziv());

        return linijaDto;
    }

    public List<LinijaDTO> convert(List<Linija> linije) {

        List<LinijaDTO> linijeDto = new ArrayList<>();
        for(Linija linija : linije) {
        	linijeDto.add(convert(linija));
        }
        return linijeDto;
    }
}