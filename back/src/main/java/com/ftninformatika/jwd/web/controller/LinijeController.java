package com.ftninformatika.jwd.web.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.ftninformatika.jwd.model.Linija;
import com.ftninformatika.jwd.service.LinijaService;
import com.ftninformatika.jwd.support.LinijaToLinijaDto;
import com.ftninformatika.jwd.web.dto.LinijaDTO;

@RestController
@RequestMapping(value = "/api/linije", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
public class LinijeController {

    @Autowired
    private LinijaService linijaService;

    @Autowired
    private LinijaToLinijaDto toLinijaDto;

    @PreAuthorize("hasAnyRole('KORISNIK', 'ADMIN')")
    @GetMapping
    public ResponseEntity<List<LinijaDTO>> getAll(
    		@RequestParam(required=false) Long prevoznikId,
            @RequestParam(required=false) String destinacija,
            @RequestParam(required=false) Double cenaKarte,
            @RequestParam(defaultValue = "0") int pageNum) {

    	Page<Linija> page = null;
		if (prevoznikId != null || destinacija != null || cenaKarte != null) {
			page = linijaService.pretraga(prevoznikId, destinacija, cenaKarte, pageNum);
		} else {
			page = linijaService.findAll(pageNum);
		}

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Total-Pages", Integer.toString(page.getTotalPages()));
        
        return new ResponseEntity<>(toLinijaDto.convert(page.getContent()), responseHeaders, HttpStatus.OK);
    }
    
    @PreAuthorize("hasAnyRole('KORISNIK', 'ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<LinijaDTO> getOne(@PathVariable Long id) {

		Optional<Linija> linija = linijaService.one(id);
		if (!linija.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(toLinijaDto.convert(linija.get()), HttpStatus.OK);
	}
    
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LinijaDTO> create(@Valid @RequestBody LinijaDTO linijaDto) {		

        Linija savedLinija = linijaService.save(linijaDto);

        return new ResponseEntity<>(toLinijaDto.convert(savedLinija), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(value = "/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LinijaDTO> update(@PathVariable Long id, @Valid @RequestBody LinijaDTO linijaDto) {

        if (!id.equals(linijaDto.getId())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Linija savedLinija = linijaService.save(linijaDto);

        return new ResponseEntity<>(toLinijaDto.convert(savedLinija), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<LinijaDTO> delete(@PathVariable Long id) {
    	
    	Linija linija = linijaService.delete(id);
        if (linija == null) {
        	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(toLinijaDto.convert(linija), HttpStatus.OK);
    }
}