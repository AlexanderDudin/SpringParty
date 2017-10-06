package com.gp.solutions.repository;

import com.gp.solutions.entity.dbo.Party;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PartyRepository extends JpaRepository<Party, Long> {

    List<Party> findAll();

    Party findOneByLocation(String location);

}
