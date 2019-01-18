package com.gp.solutions.vaadin;

import com.gp.solutions.entity.dbo.Email;
import com.gp.solutions.repository.EmailRepository;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinServlet;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManagerFactory;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringView(name = EmailView.NAME)
public class EmailView extends CustomComponent implements View {

    @Autowired
    private EmailRepository emailRepository;

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    public final static String NAME = "email";

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        final VerticalLayout layout = new VerticalLayout();
        final HorizontalLayout horizontalLayout = new HorizontalLayout();


//        final Label label = new Label("Hello, vaadin team!");
//        final Button button = new Button("Push me!");
//        button.setIcon(FontAwesome.YOUTUBE);
//        button.addStyleName(ValoTheme.BUTTON_FRIENDLY);
//        button.addClickListener(clickEvent -> {
//            label.setValue(UUID.randomUUID().toString());
//            Notification.show("Ho-ho-ho!!!");
//        });

//        final List<Skill> skills = skillRepository.findAll();

        final List<Email> emails = emailRepository.findAll();

        final JPAContainer<Email> container = JPAContainerFactory.make(Email.class, entityManagerFactory.createEntityManager());

//        final BeanItemContainer<Email> container = new BeanItemContainer<>(Email.class, emails);

        final Grid grid = new Grid(container);
        grid.setSizeFull();
        grid.setSelectionMode(Grid.SelectionMode.MULTI);

        //filter Message
        Grid.HeaderRow filterRow = grid.appendHeaderRow();
        Grid.HeaderCell cell = filterRow.getCell("message");
        TextField filterField = new TextField();
        filterField.addTextChangeListener(change -> {
            container.removeContainerFilters("message");
            if (!change.getText().isEmpty())
                container.addContainerFilter(new SimpleStringFilter("message", change.getText(), true, false));
        });
        cell.setComponent(filterField);

        Button add = new Button("Add", e -> {
            final Window myWindow = new Window("Email");

            final Email email = new Email("", "", new ArrayList<>(), LocalDate.now());
            final EmailForm emailForm = new EmailForm(email, () -> {
                if (email.getName() != "") {
//                    emails.add(email);
//                    container.addItem(email);
                    emailRepository.save(email);
                    container.refresh();
                }
                myWindow.close();
            });

            final VerticalLayout subContent = new VerticalLayout();
            subContent.setMargin(true);
            subContent.addComponent(emailForm);
            myWindow.setContent(subContent);
            myWindow.addCloseListener(clickEvent -> {
                grid.refreshAllRows();
            });

            UI.getCurrent().addWindow(myWindow);

        });
        Button editSelected = new Button("Edit", e -> {
            final Window myWindow = new Window("Email");

            Object selected = grid.getSelectedRows().iterator().next();
            final Email email = emailRepository.findById((Long) selected).orElseThrow(RuntimeException::new);
            final EmailForm emailForm = new EmailForm(email, () -> {
                emailRepository.save(email);
                container.refresh();
                myWindow.close();
            });
            final VerticalLayout subContent = new VerticalLayout();
            subContent.setMargin(true);
            subContent.addComponent(emailForm);
            myWindow.setContent(subContent);
            myWindow.addCloseListener(clickEvent -> {
                grid.refreshAllRows();
            });
            UI.getCurrent().addWindow(myWindow);

        });

        Button delSelected = new Button("Remove", e -> {
            // Delete all selected data items
            for (Object itemId : grid.getSelectedRows()) {
                emailRepository.deleteById((Long) itemId);
                grid.getContainerDataSource().removeItem(itemId);
                //emails.remove(itemId);
                container.refresh();
            }
        });
        editSelected.setVisible(false);
        delSelected.setVisible(false);

        grid.addSelectionListener(selectionEvent -> {
            editSelected.setVisible(grid.getSelectedRows().size() == 1);
            delSelected.setVisible(grid.getSelectedRows().size() > 0);
        });


        layout.addComponent(grid);
        horizontalLayout.addComponent(add);
        horizontalLayout.addComponent(editSelected);
        horizontalLayout.addComponent(delSelected);
        layout.addComponent(horizontalLayout);
        layout.addComponent(new Button("Cancel", c -> {
            Page.getCurrent().setLocation(VaadinServlet.getCurrent()
                    .getServletContext().getContextPath() +
                    "/vaadin");
        }));
        this.setCompositionRoot(layout);

    }
}
