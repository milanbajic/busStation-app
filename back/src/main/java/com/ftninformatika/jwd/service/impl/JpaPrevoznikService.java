package com.ftninformatika.jwd.service.impl;

import com.ftninformatika.jwd.model.Prevoznik;
import com.ftninformatika.jwd.repository.PrevoznikRepository;
import com.ftninformatika.jwd.service.PrevoznikService;
import com.ftninformatika.jwd.support.PrevoznikDtoToPrevoznik;
import com.ftninformatika.jwd.web.dto.PrevoznikDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JpaPrevoznikService implements PrevoznikService {

    @Autowired
    private PrevoznikRepository prevoznikRepository;

    @Autowired
    private PrevoznikDtoToPrevoznik toPrevoznik;

	@Override
	public Optional<Prevoznik> one(Long id) {
		return prevoznikRepository.findById(id);
	}

	@Override
	public List<Prevoznik> findAll() {
		return prevoznikRepository.findAll();
	}

	@Override
	public Prevoznik save(PrevoznikDTO prevoznikDto) {
		Prevoznik prevoznik = toPrevoznik.convert(prevoznikDto);
		Prevoznik savedPrevoznik = prevoznikRepository.save(prevoznik);
		return savedPrevoznik;
	}
}