package com.gp.solutions.repository;

import com.gp.solutions.entity.dbo.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmailRepository extends JpaRepository<Email, Long> {

    Optional<Email> findById(Long id);

    List<Email> findAll();


}
