package com.gp.solutions.service;

import com.gp.solutions.entity.dbo.Person;
import com.gp.solutions.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DrinkingBuddyService implements BuddyService {


    private PersonRepository repository;

    @Autowired
    public DrinkingBuddyService(PersonRepository repository) {
        this.repository = repository;
    }

    /**
     * Finds and returns person with smallest age difference to the input.
     */
    @Override
    public Person findBuddy(Person person) {


        final List<Person> all = repository.findAll();
        final Optional<Person> buddy = all.stream()
                .filter(p -> p.getId() != person.getId() && p.getAge() != 0)
                .min((p1, p2) -> {
                    final int diff1 = Math.abs(person.getAge() - p1.getAge());
                    final int diff2 = Math.abs(person.getAge() - p2.getAge());
                    return Integer.min(diff1, diff2);
                });

        return buddy.orElse(null);

    }

}
