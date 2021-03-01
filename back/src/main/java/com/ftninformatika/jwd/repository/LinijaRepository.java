package com.ftninformatika.jwd.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ftninformatika.jwd.model.Linija;

@Repository
public interface LinijaRepository extends JpaRepository<Linija,Long> {
	
	@Query("SELECT l FROM Linija l WHERE "
		+ "(:prevoznikId IS NULL OR l.prevoznik.id = :prevoznikId) AND " 
    	+ "(:destinacija IS NULL OR l.destinacija like %:destinacija%) AND " 
    	+ "(:cenaKarte IS NULL OR l.cenaKarte <= :cenaKarte)")	
	Page<Linija> pretraga(@Param("prevoznikId") Long prevoznikId, @Param("destinacija") String destinacija, @Param("cenaKarte") Double cenaKarte, Pageable pageable);
}