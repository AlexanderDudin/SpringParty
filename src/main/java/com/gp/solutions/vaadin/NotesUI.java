package com.gp.solutions.vaadin;

import com.vaadin.annotations.Theme;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.access.ViewAccessControl;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.UI;
import org.springframework.beans.factory.annotation.Autowired;


@SpringUI(path = "vaadin")
@Theme("mytheme")
public class NotesUI extends UI {

    @Autowired
    private final SpringViewProvider springViewProvider;

    public NotesUI(final SpringViewProvider springViewProvider) {
        this.springViewProvider = springViewProvider;
    }

    @Override
    protected void init(final VaadinRequest vaadinRequest) {
        final Navigator navigator = new Navigator(this, this);
        springViewProvider.setAccessDeniedViewClass(AccessDeniedView.class);

        navigator.addProvider(springViewProvider);
        navigator.navigateTo(MainView.NAME);
        navigator.setErrorView(new ErrorView());
    }
}
