package com.gp.solutions.controller;

import com.gp.solutions.entity.dbo.Skill;
import com.gp.solutions.service.SkillService;
import io.swagger.annotations.Api;
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
@RequestMapping(SkillController.REQUEST_MAPPING)
@Api(description = "Find skill by level name")
public class SkillController {

    public static final String REQUEST_MAPPING = "/skill";

    @Autowired
    private SkillService skillService;

    /**
     * @param levelName - name level for search
     * @return skills in accordance with the introduced level name
     */
    @RequestMapping(value = "/level/{levelName}", method = RequestMethod.GET)
    public ResponseEntity<Collection<Skill>> getSkillByLevel(@PathVariable final String levelName) {
        final List<Skill> skills = skillService.getSkillByLevel(levelName);
        if (!skills.isEmpty()) {
            return new ResponseEntity<>(skills, HttpStatus.OK);
        } else {
            return new ResponseEntity<>((Collection<Skill>) null, HttpStatus.NOT_FOUND);
        }
    }
}
