package com.gp.solutions.repository;

import java.util.List;

import com.gp.solutions.entity.dbo.Party;
import com.gp.solutions.entity.dbo.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    List<Person> findAll();

    Person findByUsername(String username);

    List<Person> findByParties(Party party);

    @Query(value = "select * from people AS p " +
            "join people_skill_level as psl on p.person_id = psl.person_id " +
            "join skill as s on s.skill_id = psl.skill_id " +
            "where s.name  = :skillName", nativeQuery = true)
    List<Person> findBySkill(@Param("skillName") String skillName);

    @Query(value = "SELECT * "+
            "FROM people AS p "+
            "JOIN people_skill_level AS psl ON p.person_id = psl.person_id "+
            "JOIN skill AS s ON s.skill_id = psl.skill_id " +
            "JOIN people_parties AS pp ON p.person_id = pp.person_id "+
            "JOIN party AS pr ON pr.party_id = pp.party_id "+
            "WHERE s.name  = :skillName AND pr.location = :location", nativeQuery = true)
    List<Person> findBySkillAndParty(@Param("skillName") String skillName, @Param("location") String location);

}
