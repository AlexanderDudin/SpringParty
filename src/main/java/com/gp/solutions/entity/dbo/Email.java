package com.gp.solutions.entity.dbo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "email")
@Data
@NoArgsConstructor
public class Email {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "email_id")
    private long id;

    @Column
    @Size(min = 1, max = 10)
    @NotNull
    private String name;


    @Size(min = 1, max = 100)
    @NotNull
    private String message;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "email_recipient", joinColumns = @JoinColumn(name = "email_id"))
    @Column(name = "recipient")
    private List<String> recipients = new ArrayList<>();

    @Column
    @JsonFormat(pattern = "MM/dd/YYYY")
    private LocalDate date;

    public Email(String name, String message, List<String> recipients, LocalDate date) {
        this.name = name;
        this.message = message;
        this.recipients = recipients;
        this.date = date;
    }

//    public static List<Email> initExampleCollection() {
//        final List<Email> emails = new ArrayList<>();
//        final Email email1 = new Email("Dima", "Hello Dima", Collections.singletonList("Dima"), LocalDate.now());
//        final Email email2 = new Email("Sergey", "Hello Sergey", Collections.singletonList("Sergey"), LocalDate.now());
//        final String[] recepientsArray = {"Dima", "Sergey", "Tanya"};
//        final Email email3 = new Email("Tanya", "Hello all", Arrays.asList(recepientsArray), LocalDate.now());
//        emails.add(email1);
//        emails.add(email2);
//        emails.add(email3);
//        return emails;
//    }
}
