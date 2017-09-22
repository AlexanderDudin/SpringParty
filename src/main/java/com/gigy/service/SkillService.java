package com.gigy.service;

import com.gigy.entity.dbo.Skill;

import java.util.List;

public interface SkillService {

    List<Skill> getSkillByLevel(String levelName);
}
