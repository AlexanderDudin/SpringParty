package com.gp.solutions.vaadin;

import java.util.function.Consumer;

public interface Broadcast<T> {

    void register(Consumer<T> listener);

    void unregister(Consumer<T> listener);

    void broadcast(final T message);

}
