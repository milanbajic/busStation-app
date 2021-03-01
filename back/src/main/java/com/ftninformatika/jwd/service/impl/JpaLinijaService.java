package com.ftninformatika.jwd.service.impl;

import com.ftninformatika.jwd.model.Linija;
import com.ftninformatika.jwd.repository.LinijaRepository;
import com.ftninformatika.jwd.service.LinijaService;
import com.ftninformatika.jwd.support.LinijaDtoToLinija;
import com.ftninformatika.jwd.web.dto.LinijaDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JpaLinijaService implements LinijaService {

    @Autowired
    private LinijaRepository linijaRepository;

    @Autowired
    private LinijaDtoToLinija toLinija;

	@Override
	public Optional<Linija> one(Long id) {
		return linijaRepository.findById(id);
	}

	@Override
	public Linija save(LinijaDTO linijaDto) {

		Linija linija = toLinija.convert(linijaDto);
		Linija savedLinija = linijaRepository.save(linija);
		return savedLinija;
	}

	@Override
	public Linija delete(Long id) {

		Optional<Linija> linijaOptional = linijaRepository.findById(id);
		if (linijaOptional.isPresent()) {
			Linija linija = linijaOptional.get();
			linijaRepository.deleteById(id);
			return linija;
		}
		return null;
	}

	@Override
	public Page<Linija> findAll(int pageNum) {
		return linijaRepository.findAll(PageRequest.of(pageNum, 5));
	}

	@Override
	public Page<Linija> pretraga(Long prevoznikId, String destinacija, Double cenaKarte, int pageNum) {
		return linijaRepository.pretraga(prevoznikId, destinacija, cenaKarte, PageRequest.of(pageNum, 5));
	}
}