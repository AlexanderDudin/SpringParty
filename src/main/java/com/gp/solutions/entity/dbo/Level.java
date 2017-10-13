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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "level_id")
    private long id;

    private String name;

}
