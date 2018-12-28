package com.gp.solutions.vaadin;

import com.vaadin.ui.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StringListField extends CustomField<List<String>> {

    private VerticalLayout verticalLayout = new VerticalLayout();

    public StringListField(String caption) {
        setCaption(caption);
    }

    @Override
    protected Component initContent() {
        FormLayout content = new FormLayout();
        content.addComponent(verticalLayout);

        fillField(this.getValue());

        Button addRecipient = new Button("addRecipient", this::addItem); // Java 8
        content.addComponent(addRecipient);
        return content;
    }

    void addItem(Button.ClickEvent event) {
        List<String> list = new ArrayList<>(getValue());

        final HorizontalLayout horizontalLayout = new HorizontalLayout();
        final TextField tf = new TextField();
        final Button remove = new Button("Remove", this::removeItem);

        tf.addValueChangeListener(valueChange -> {
            fireValueChange(true);
            int index = verticalLayout.getComponentIndex(horizontalLayout);
            list.add(index, tf.getValue());
            setValue(list);
        });
        tf.focus();
        horizontalLayout.addComponent(tf);
        horizontalLayout.addComponent(remove);
        verticalLayout.addComponent(horizontalLayout);
    }

    void removeItem(Button.ClickEvent event) {
        verticalLayout.removeComponent(event.getButton().getParent());
    }

    @SuppressWarnings("unchecked")
    @Override
    public Class<? extends List<String>> getType() {
        return (Class<List<String>>) Arrays.stream(ArrayList.class.getInterfaces())
                .filter(a -> a.isAssignableFrom(List.class)).findAny().orElseThrow(RuntimeException::new);
    }

    @Override
    public List<String> getValue() {
        return super.getValue();
    }

    @Override
    public void setValue(final List<String> newFieldValue) {
        final List<String> list = newFieldValue;
        verticalLayout.removeAllComponents();
        fillField(list);
        super.setValue(list);
    }

    private void fillField(List<String> recipients) {
        final List<String> recipientsCopy = recipients == null ? new ArrayList<>() : recipients;
        for (String element : recipientsCopy) {
            final TextField tf = new TextField();
            tf.setValue(element);
            final Button remove = new Button("Remove", this::removeItem);
            final HorizontalLayout layout = new HorizontalLayout();
            layout.addComponent(tf);
            layout.addComponent(remove);
            verticalLayout.addComponent(layout);
        }
    }

}
