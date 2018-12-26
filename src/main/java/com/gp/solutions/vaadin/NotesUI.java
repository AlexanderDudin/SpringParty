package com.gp.solutions.vaadin;

import com.gp.solutions.repository.SkillRepository;
import com.vaadin.annotations.Theme;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.List;


@SpringUI(path = "lol")
@Theme("valo")
public class NotesUI extends UI {

    @Autowired
    private SkillRepository skillRepository;

    @Override
    protected void init(final VaadinRequest vaadinRequest) {
        final VerticalLayout layout = new VerticalLayout();
        final HorizontalLayout horizontalLayout = new HorizontalLayout();
        final Window myWindow = new Window("Email");
//        final Label label = new Label("Hello, vaadin team!");
//        final Button button = new Button("Push me!");
//        button.setIcon(FontAwesome.YOUTUBE);
//        button.addStyleName(ValoTheme.BUTTON_FRIENDLY);
//        button.addClickListener(clickEvent -> {
//            label.setValue(UUID.randomUUID().toString());
//            Notification.show("Ho-ho-ho!!!");
//        });

//        final List<Skill> skills = skillRepository.findAll();

        final List<Email> emails = Email.initExampleCollection();

//        final BeanItemContainer<Skill> container = new BeanItemContainer<>(Skill.class, skills);

        final BeanItemContainer<Email> container = new BeanItemContainer<>(Email.class, emails);

        final Grid grid = new Grid(container);
        grid.setSizeFull();
        grid.setSelectionMode(Grid.SelectionMode.MULTI);

        Grid.MultiSelectionModel selection =
                (Grid.MultiSelectionModel) grid.getSelectionModel();

        Button add = new Button("Add");
        Button editSelected = new Button("Edit", e -> {

            grid.getSelectionModel().reset();

            Object selected = ((Grid.SingleSelectionModel) grid.getSelectionModel()).getSelectedRow();

            final EmailForm emailForm = new EmailForm((Email) selected, () -> {

            });
            final VerticalLayout subContent = new VerticalLayout();
            subContent.setMargin(true);
            subContent.addComponent(emailForm);
            myWindow.setContent(subContent);

            this.addWindow(myWindow);

        });

        Button delSelected = new Button("Remove", e -> {
            // Delete all selected data items
            for (Object itemId : selection.getSelectedRows()) {
                grid.getContainerDataSource().removeItem(itemId);
                emails.remove(itemId);
            }

            // Otherwise out of sync with container
            grid.getSelectionModel().reset();

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
        setContent(layout);

    }

}
