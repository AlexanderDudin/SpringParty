package com.gp.solutions.vaadin;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Data
@NoArgsConstructor
public class Email {

    private String name;
    private String message;
    private List<String> recipients;
    private LocalDate date;

    public Email(String name, String message, List<String> recipients, LocalDate date) {
        this.name = name;
        this.message = message;
        this.recipients = recipients;
        this.date = date;
    }

    public static List<Email> initExampleCollection() {
        final List<Email> emails = new ArrayList<>();
        final Email email1 = new Email("Dima", "Hello Dima", Collections.singletonList("Dima"), LocalDate.now());
        final Email email2 = new Email("Sergey", "Hello Sergey", Collections.singletonList("Sergey"), LocalDate.now());
        final String[] recepientsArray = {"Dima", "Sergey", "Tanya"};
        final Email email3 = new Email("Tanya", "Hello all", Arrays.asList(recepientsArray), LocalDate.now());
        emails.add(email1);
        emails.add(email2);
        emails.add(email3);
        return emails;
    }
}
