package com.gp.solutions.vaadin;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FileResource;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Image;
import com.vaadin.ui.VerticalLayout;

import java.io.File;

@SpringView(name = AccessDeniedView.NAME)
public class AccessDeniedView extends CustomComponent implements View {

    public static final String NAME = "denied";

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        final VerticalLayout verticalLayout = new VerticalLayout();
        ClassLoader classLoader = getClass().getClassLoader();
        FileResource resource = new FileResource(new File(classLoader.getResource("picture/no!.jpg").getFile()));
        final Image image = new Image("", resource);
        verticalLayout.addComponent(image);
        this.setCompositionRoot(verticalLayout);
    }
}
