package com.gp.solutions.vaadin;

import com.gp.solutions.entity.dbo.Email;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

@Service
public class MessageBroadcast implements Broadcast<Email> {

    private final ExecutorService executorService = Executors.newSingleThreadExecutor();
    private final List<Consumer<Email>> listeners = new ArrayList<>();

    @Override
    public void register(Consumer<Email> listener) {
        listeners.add(listener);
    }

    @Override
    public void unregister(Consumer<Email> listener) {
        listeners.remove(listener);
    }

    @Override
    public void broadcast(Email message) {
        for (final Consumer<Email> listener : listeners) {
            executorService.execute(() -> {
                listener.accept(message);
            });
        }
    }
}
