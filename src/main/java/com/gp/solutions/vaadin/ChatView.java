package com.gp.solutions.vaadin;

import com.gp.solutions.entity.dbo.Email;
import com.gp.solutions.repository.EmailRepository;
import com.vaadin.event.ShortcutAction;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import java.time.LocalDate;
import java.util.function.Consumer;

@SpringView(name = ChatView.NAME)
public class ChatView extends CustomComponent implements View, Consumer<Email> {

    public static final String NAME = "chat";

    private final Broadcast<Email> messageBroadcast;
    private final VerticalLayout messageLayout;
    private String name = null;

    public ChatView(final Broadcast<Email> messageBroadcast, final EmailRepository emailRepository) {
        this.messageBroadcast = messageBroadcast;
        this.messageLayout = new VerticalLayout();
        final VerticalLayout mainLayout = new VerticalLayout();

        final Panel messagePanel = new Panel();
        messagePanel.setSizeFull();
        messagePanel.setContent(messageLayout);

        final HorizontalLayout registerLayout = new HorizontalLayout();
        final HorizontalLayout changeMessagesLayout = new HorizontalLayout();
        changeMessagesLayout.setVisible(false);

        final Label label = new Label("Please, input your name");
        final TextField textField = new TextField();
        final Button join = new Button("Join Chat", event -> {
            if (!textField.getValue().isEmpty()) {
                name = textField.getValue();
                messagePanel.setCaption(name);
                registerLayout.setVisible(false);
                changeMessagesLayout.setVisible(true);
            } else {
                Notification.show("Please, enter your name", Notification.Type.WARNING_MESSAGE);
            }
        });
        registerLayout.addComponents(label, textField, join);


        final TextField messageField = new TextField();
        final Button enter = new Button("Enter", event -> {
            if (!messageField.getValue().isEmpty()) {
                final Email email = new Email();
                email.setName(name);
                email.setMessage(messageField.getValue());
                email.setDate(LocalDate.now());
                emailRepository.save(email);
                messageBroadcast.broadcast(email);
                messageField.clear();
            } else {
                Notification.show("Please, enter your message", Notification.Type.WARNING_MESSAGE);
            }
        });
        enter.addStyleName(ValoTheme.BUTTON_PRIMARY);
        enter.setClickShortcut(ShortcutAction.KeyCode.ENTER);
        changeMessagesLayout.addComponents(messageField, enter);

        mainLayout.addComponents(messagePanel, registerLayout, changeMessagesLayout);

        mainLayout.setExpandRatio(messagePanel, 20);
        mainLayout.setExpandRatio(registerLayout, 2);
        mainLayout.setExpandRatio(changeMessagesLayout, 2);

        mainLayout.setSizeFull();
        this.setCompositionRoot(mainLayout);
        this.setSizeFull();
    }

    @Override
    public void enter(final ViewChangeListener.ViewChangeEvent event) {
        messageBroadcast.register(this);
    }

    @Override
    public void detach() {
        messageBroadcast.unregister(this);
        super.detach();
    }

    @Override
    public void accept(final Email email) {
        getUI().access(() -> {
            final Label emailLabel = new Label(email.getName());
            emailLabel.setValue(email.getName() + ": " + email.getMessage());
            messageLayout.addComponent(emailLabel);
        });
    }
}
