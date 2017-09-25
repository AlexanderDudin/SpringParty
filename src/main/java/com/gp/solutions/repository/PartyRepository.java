package com.gp.solutions.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gp.solutions.entity.dbo.Party;

@Repository
public interface PartyRepository extends JpaRepository<Party, Long> {
	
	List<Party> findAll();

	Party findOneByLocation(String location);

}
