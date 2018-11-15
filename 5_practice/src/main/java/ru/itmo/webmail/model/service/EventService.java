package ru.itmo.webmail.model.service;

import ru.itmo.webmail.model.domain.Event;
import ru.itmo.webmail.model.repository.EventRepository;
import ru.itmo.webmail.model.repository.impl.EventRepositoryImpl;

public class EventService {
    private EventRepository eventRepository = new EventRepositoryImpl();

    public EventRepository getEventRepository() {
        return eventRepository;
    }

    public void addEnter(long userId) {
        addEnterOrLogout(userId, Event.Type.ENTER);
    }

    public void addLogout(long userId) {
        addEnterOrLogout(userId, Event.Type.LOGOUT);
    }

    private void addEnterOrLogout(long userId, Event.Type type) {
        Event event = new Event();
        event.setUserId(userId);
        event.setType(type);
        eventRepository.save(event);
    }
}
