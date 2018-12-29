package com.gp.solutions.vaadin;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.access.ViewAccessControl;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.UI;

@SpringView(name = MainView.NAME)
public class MainView extends CustomComponent implements View, ViewAccessControl {

    public final static String NAME = "main";

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        final MenuBar menuBar = new MenuBar();
        menuBar.addItem("Email", (MenuBar.Command) selectedItem -> getUI().getNavigator().navigateTo(EmailView.NAME));
        menuBar.addItem("Error",
                (MenuBar.Command) selectedItem -> getUI().getNavigator().navigateTo(EmailView.NAME + "error"));
        menuBar.addItem("Denied", (MenuBar.Command) selectedItem -> getUI().getNavigator().navigateTo(AccessDeniedView.NAME));
        this.setCompositionRoot(menuBar);

    }

    @Override
    public boolean isAccessGranted(UI ui, String beanName) {
        if ("notAvailableView".equals(beanName)) {
            return false;
        }
        return true;
    }
}
