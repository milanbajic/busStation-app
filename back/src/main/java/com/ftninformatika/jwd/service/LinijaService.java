package com.ftninformatika.jwd.service;

import com.ftninformatika.jwd.model.Linija;
import com.ftninformatika.jwd.web.dto.LinijaDTO;

import java.util.Optional;

import org.springframework.data.domain.Page;

public interface LinijaService {

	Optional<Linija> one(Long id);

	Linija save(LinijaDTO linijaDto);

	Linija delete(Long id);

    Page<Linija> findAll(int pageNum);

    Page<Linija> pretraga(Long prevoznikId, String destinacija, Double cenaKarte, int pageNum);
}