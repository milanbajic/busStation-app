package com.ftninformatika.jwd.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.ftninformatika.jwd.model.Rezervacija;
import com.ftninformatika.jwd.service.RezervacijaService;
import com.ftninformatika.jwd.support.RezervacijaToRezervacijaDto;
import com.ftninformatika.jwd.web.dto.RezervacijaDTO;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/rezervacije", produces = MediaType.APPLICATION_JSON_VALUE)
public class RezervacijaContorller {

    @Autowired
    private RezervacijaService rezervacijaService;

    @Autowired
    private RezervacijaToRezervacijaDto toRezervacija;

    @PreAuthorize("hasAnyRole('KORISNIK', 'ADMIN')")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RezervacijaDTO> create(@Valid @RequestBody RezervacijaDTO rezervacijaDto) {		

        Rezervacija savedRezervacija = rezervacijaService.save(rezervacijaDto);
        if (savedRezervacija == null) {
        	return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(toRezervacija.convert(savedRezervacija), HttpStatus.CREATED);
    }
}