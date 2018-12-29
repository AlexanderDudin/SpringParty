package com.gp.solutions.vaadin;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class EmailsCache {
    private List<Email> emails;

    @PostConstruct
    public void init() {
        this.emails = Email.initExampleCollection();
    }

    public List<Email> getEmails() {
        return emails;
    }
}
