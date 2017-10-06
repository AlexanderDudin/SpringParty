package com.gp.solutions.entity.dbo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "people")
@Data
@NoArgsConstructor
@SuppressWarnings("JpaAttributeTypeInspection")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "person_id")
    private long id;

    private String name;

    private String username;

    private int age;


    @ManyToMany(cascade = CascadeType.MERGE)
    @JsonBackReference
    @JoinTable(name = "people_parties",
            joinColumns = @JoinColumn(name = "person_id", referencedColumnName = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "party_id", referencedColumnName = "party_id"))
    private List<Party> parties;

    @ElementCollection
    @JoinTable(name = "people_skill_level", joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "level_id"))
    @MapKeyJoinColumn(name = "skill_id")
    private Map<Skill, Level> skillByLevel;

}