package com.gigy.service;

import com.gigy.entity.dbo.Skill;
import com.gigy.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillServiceImpl implements SkillService {

    @Autowired
    private SkillRepository skillRepository;


    @Override
    public List<Skill> getSkillByLevel(String levelName) {
        return skillRepository.findByLevel(levelName);
    }
}
