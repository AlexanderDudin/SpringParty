package com.gp.solutions.vaadin;

import com.vaadin.ui.Component;
import com.vaadin.ui.CustomField;
import com.vaadin.ui.DateField;
import com.vaadin.ui.VerticalLayout;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class LocalDateField extends CustomField<LocalDate> {

    private VerticalLayout verticalLayout = new VerticalLayout();
    private DateField dateField = new DateField();

    public LocalDateField(String caption) {
        setCaption(caption);
    }

    @Override
    protected Component initContent() {
        dateField.setValue(new Date());
        verticalLayout.addComponent(dateField);
        return verticalLayout;
    }

    @Override
    public Class<? extends LocalDate> getType() {
        return LocalDate.class;
    }

    @Override
    public LocalDate getValue() {
        return LocalDate.parse(new SimpleDateFormat(" MM/dd/YYYY").format(dateField.getValue()));
    }

    @Override
    public void setValue(final LocalDate date) {
        dateField.setValue(Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant()));
    }
}
