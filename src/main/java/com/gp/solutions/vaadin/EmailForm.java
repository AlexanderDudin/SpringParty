package com.gp.solutions.vaadin;

import com.gp.solutions.entity.dbo.Email;
import com.vaadin.data.Validator;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.ui.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class EmailForm extends Panel {

    @PropertyId("name")
    private final TextField nameTextField = new TextField("Name");
    @PropertyId("message")
    private final TextArea messageTextField = new TextArea("Message");
    @PropertyId("recipients")
    private final StringListField recipientsTextField = new StringListField("Recipients");
    @PropertyId("date")
    private final LocalDateField dateTextField = new LocalDateField("Date");

    private Runnable onSaveOrDiscard;
    private BeanFieldGroup<Email> emailFieldGroup;

    public EmailForm(final Email email, final Runnable onSaveOrDiscard) {
        setCaption("Email");
        this.onSaveOrDiscard = onSaveOrDiscard;

        VerticalLayout content = new VerticalLayout();
        content.setMargin(true);
        content.setSizeUndefined();
        setContent(content);
        setSizeUndefined();

        dateTextField.addValidator(value -> {
            if (value == null) {
                throw new Validator.InvalidValueException("date is empty");
            }
            if (((LocalDate)value).isBefore(LocalDate.now()))
            {
                throw new Validator.InvalidValueException("date is before today");
            }
        });

        FormLayout form = new FormLayout();
        form.addComponents(nameTextField, messageTextField, recipientsTextField, dateTextField);
        content.addComponent(form);

        content.addComponent(new Button("Save", this::commit));
        content.addComponent(new Button("Cancel", this::discard));
        emailFieldGroup = new BeanFieldGroup<>(Email.class);
        emailFieldGroup.setItemDataSource(email);
        emailFieldGroup.bindMemberFields(this);
    }

    private void commit(Button.ClickEvent event) {
        try {
            emailFieldGroup.commit();
            onSaveOrDiscard.run();
        } catch (FieldGroup.CommitException e) {
            Notification.show("Form isn't valid", Notification.Type.WARNING_MESSAGE);
        }
    }

    private void discard(Button.ClickEvent event) {
        emailFieldGroup.discard();
        onSaveOrDiscard.run();
    }
}
