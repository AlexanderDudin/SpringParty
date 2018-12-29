package com.gp.solutions.vaadin;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FileResource;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

import java.io.File;

public class ErrorView extends CustomComponent implements View {
    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        final VerticalLayout verticalLayout = new VerticalLayout();
        final String basepath = VaadinService.getCurrent()
                .getBaseDirectory().getAbsolutePath();
        final Label label = new Label("Where do we go, Cat?" );
        ClassLoader classLoader = getClass().getClassLoader();
        FileResource resource = new FileResource(new File(classLoader.getResource("picture/FunnyCat.jpg").getFile()));
        final Image image = new Image("", resource);
        verticalLayout.addComponent(label);
        verticalLayout.addComponent(image);
        this.setCompositionRoot(verticalLayout);
    }
}
