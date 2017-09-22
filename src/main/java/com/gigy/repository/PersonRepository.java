package com.gigy.repository;

import java.util.List;

import com.gigy.entity.dbo.Party;
import com.gigy.entity.dbo.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gigy.entity.dbo.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    List<Person> findAll();

    Person findByUsername(String username);

    List<Person> findByParties(Party party);


//  @Query("SELECT p FROM Person as p join people_skill_level as psl on p.id = psl.person_id join Skill as s on s.id = psl.skill_id where s.name = :skillName")

    //   @Query("SELECT p FROM Person p " +
//           "join p.id people_skill_level " +
//           "join skill.skill_id people_skill_level " +
//           "where skill.name = :skillName")
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
