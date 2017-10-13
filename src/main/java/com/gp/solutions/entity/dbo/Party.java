package com.gp.solutions.entity.dbo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "party")
@Data
@NoArgsConstructor
public class Party {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "party_id")
    private long id;

    private String location;

    @Column(name = "party_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date date;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "people_parties",
            joinColumns = @JoinColumn(name = "party_id", referencedColumnName = "party_id"),
            inverseJoinColumns = @JoinColumn(name = "person_id", referencedColumnName = "person_id"))
    private List<Person> people;


}