package com.gp.solutions.entity.dbo;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "level")
@Data
@NoArgsConstructor
public class Level {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "level_id")
    private long id;

    private String name;

//    @ManyToOne(cascade = CascadeType.MERGE)
//    @JsonBackReference
//    @JoinTable(name = "skill_level",
//            joinColumns = @JoinColumn(name = "level_id", referencedColumnName = "level_id"),
//            inverseJoinColumns = @JoinColumn(name = "skill_id", referencedColumnName = "skill_id"))
//    private Skill skill;
}
