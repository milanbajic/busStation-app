package com.ftninformatika.jwd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ftninformatika.jwd.model.Prevoznik;

@Repository
public interface PrevoznikRepository extends JpaRepository<Prevoznik,Long> {

}