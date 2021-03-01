package com.ftninformatika.jwd.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ftninformatika.jwd.model.Rezervacija;

@Repository
public interface RezervacijaRepository extends JpaRepository<Rezervacija,Long> {
	
	List<Rezervacija> findByLinijaId(Long id); 
}