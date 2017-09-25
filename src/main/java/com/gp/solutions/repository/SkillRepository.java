package com.gp.solutions.repository;


import com.gp.solutions.entity.dbo.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Long> {

    List<Skill> findAll();

    Skill findByName(String name);

    @Query(value = "select * from skill as s " +
            "join people_skill_level as psl on s.skill_id = psl.skill_id " +
            "join level as l on l.level_id = psl.level_id " +
            "where l.name =:levelName", nativeQuery = true)
    List<Skill> findByLevel(@Param("levelName") String levelName);

}
