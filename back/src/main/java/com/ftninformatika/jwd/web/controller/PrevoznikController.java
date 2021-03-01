package com.ftninformatika.jwd.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.ftninformatika.jwd.model.Prevoznik;
import com.ftninformatika.jwd.service.PrevoznikService;
import com.ftninformatika.jwd.support.PrevoznikToPrevoznikDto;
import com.ftninformatika.jwd.web.dto.PrevoznikDTO;

import java.util.List;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/prevoznici", produces = MediaType.APPLICATION_JSON_VALUE)
public class PrevoznikController {

    @Autowired
    private PrevoznikService prevoznikService;

    @Autowired
    private PrevoznikToPrevoznikDto toPrevoznikDto;

    @PreAuthorize("hasAnyRole('KORISNIK', 'ADMIN')")
    @GetMapping
    public ResponseEntity<List<PrevoznikDTO>> getAll() {

        List<Prevoznik> prevoznici = prevoznikService.findAll();

        return new ResponseEntity<>(toPrevoznikDto.convert(prevoznici), HttpStatus.OK);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PrevoznikDTO> create(@Valid @RequestBody PrevoznikDTO prevoznikDto) {		

        Prevoznik savedPrevoznik = prevoznikService.save(prevoznikDto);

        return new ResponseEntity<>(toPrevoznikDto.convert(savedPrevoznik), HttpStatus.CREATED);
    }
}