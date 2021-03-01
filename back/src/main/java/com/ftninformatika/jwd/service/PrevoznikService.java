package com.ftninformatika.jwd.service;

import java.util.List;
import java.util.Optional;

import com.ftninformatika.jwd.model.Prevoznik;
import com.ftninformatika.jwd.web.dto.PrevoznikDTO;

public interface PrevoznikService {

	Optional<Prevoznik> one(Long id);

    List<Prevoznik> findAll();

    Prevoznik save(PrevoznikDTO prevoznikDto);
}