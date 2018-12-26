package com.gp.solutions.vaadin;

import com.vaadin.ui.Component;
import com.vaadin.ui.CustomField;
import com.vaadin.ui.VerticalLayout;

import java.util.ArrayList;
import java.util.List;

public class StringListField extends CustomField<List<String>> {

    private VerticalLayout verticalLayout;

    @Override
    protected Component initContent() {
        verticalLayout = new VerticalLayout();
        return verticalLayout;
    }

    @Override
    public Class<? extends List<String>> getType() {
        return null;
    }

    @Override
    public List<String> getValue() {
        return null;
    }

    @Override
    public void setValue(final List<String> newFieldValue) {
        final List<String> copyList = newFieldValue;
    }

}
