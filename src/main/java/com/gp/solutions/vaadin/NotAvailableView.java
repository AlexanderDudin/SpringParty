package com.gp.solutions.vaadin;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.CustomComponent;

@SpringView(name = NotAvailableView.NAME)
public class NotAvailableView extends CustomComponent implements View {
    public static final String NAME = "notAvailableView";

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

    }
}
