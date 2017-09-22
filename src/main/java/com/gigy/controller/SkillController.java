package com.gigy.controller;

import com.gigy.entity.dbo.Skill;
import com.gigy.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/skill")
public class SkillController {

    @Autowired
    private SkillService skillService;

    @RequestMapping(value = "/level/{levelName}", method = RequestMethod.GET)
    public ResponseEntity<Collection<Skill>> getSkillByLevel(@PathVariable String levelName) {
        List<Skill> skills = skillService.getSkillByLevel(levelName);
        if (!skills.isEmpty()) {
            return new ResponseEntity<>(skills, HttpStatus.OK);
        } else {
            return new ResponseEntity<>((Collection<Skill>) null, HttpStatus.NOT_FOUND);
        }
    }
}
