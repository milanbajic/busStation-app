package com.ftninformatika.jwd.support;

import com.ftninformatika.jwd.model.Korisnik;
import com.ftninformatika.jwd.web.dto.KorisnikDTO;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class KorisnikToKorisnikDto implements Converter<Korisnik, KorisnikDTO>{

    @Override
    public KorisnikDTO convert(Korisnik korisnik) {

        KorisnikDTO korisnikDTO = new KorisnikDTO();
        korisnikDTO.setId(korisnik.getId());
        korisnikDTO.seteMail(korisnik.geteMail());
        korisnikDTO.setIme(korisnik.getIme());
        korisnikDTO.setPrezime(korisnik.getPrezime());
        korisnikDTO.setKorisnickoIme(korisnik.getKorisnickoIme());

        return korisnikDTO;
    }

    public List<KorisnikDTO> convert(List<Korisnik> korisnici){

        List<KorisnikDTO> korisnikDTOS = new ArrayList<>();
        for (Korisnik korisnik : korisnici) {
            KorisnikDTO dto = convert(korisnik);
            korisnikDTOS.add(dto);
        }
        return korisnikDTOS;
    }
}