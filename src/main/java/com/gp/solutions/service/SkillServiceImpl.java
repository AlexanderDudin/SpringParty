package com.gp.solutions.service;

import com.gp.solutions.entity.dbo.Skill;
import com.gp.solutions.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SkillServiceImpl implements SkillService {

    @Autowired
    private SkillRepository skillRepository;

    /**
     * Finds and returns skills in accordance with the introduced level name.
     */
    @Override
    public List<Skill> getSkillByLevel(String levelName) {
        return skillRepository.findByLevel(levelName);
    }
}
