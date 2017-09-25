package com.gp.solutions.service;

import com.gp.solutions.entity.dbo.Skill;

import java.util.List;

public interface SkillService {

    List<Skill> getSkillByLevel(String levelName);
}
