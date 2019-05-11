package studies.project.tripadvisor.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import studies.project.tripadvisor.entity.Message;
import studies.project.tripadvisor.entity.Observer;
import studies.project.tripadvisor.entity.Place;
import studies.project.tripadvisor.entity.User;
import studies.project.tripadvisor.exception.ElementNotFoundException;
import studies.project.tripadvisor.repository.MessageRepository;
import studies.project.tripadvisor.repository.ObserverRepository;
import studies.project.tripadvisor.repository.PlaceRepository;
import studies.project.tripadvisor.repository.UserRepository;
import studies.project.tripadvisor.service.NotificationService;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private ObserverRepository observerRepository;

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PlaceRepository placeRepository;

    @Override
    public void notifyObservers(Long placeId, String message) {
        log.info("NotificationService: notifyObservers");
        log.info(message);
        List<Observer> observers = observerRepository.findByPlace(placeRepository.getOne(placeId)).orElse(Collections.emptyList());
        observers.stream()
                .map(Observer::getUser)
                .forEach(user -> messageRepository.save(new Message(message, user)));
    }

    @Override
    public void addObserver(Long placeId, Long userId) {
        log.info("NotificationService: addObserver");
        if (!userRepository.existsById(userId) || !placeRepository.existsById(placeId)) {
            throw new ElementNotFoundException();
        } else {
            Place place = placeRepository.getOne(placeId);
            User user = userRepository.getOne(userId);
            log.info("Place:\n" + place + "\nUser:\n" + user);
            observerRepository.save(new Observer(place, user));
        }
    }

    @Override
    public void removeObserver(Long placeId, Long userId) {
        log.info("NotificationService: removeObserver");
        Place place = placeRepository.getOne(placeId);
        User user = userRepository.getOne(userId);
        log.info("Place:\n" + place + "\nUser:\n" + user);
        if (!observerRepository.existsByPlaceAndUser(place, user))
            throw new ElementNotFoundException();
        observerRepository.deleteByPlaceAndUser(place, user);
    }

    @Override
    public List<String> retrieveMessages(Long userId) {
        log.info("NotificationService: retrieveMessages");
        if (!userRepository.existsById(userId)) {
            throw new ElementNotFoundException();
        } else {
            return messageRepository.findByUser(userRepository.getOne(userId))
                    .orElse(Collections.emptyList())
                    .stream()
                    .map(Message::getText)
                    .collect(Collectors.toList());
        }
    }

    @Override
    public void deleteMessages(Long userId) {
        log.info("NotificationService: deleteMessages");
        if (!userRepository.existsById(userId)) {
            throw new ElementNotFoundException();
        } else {
            messageRepository.deleteByUser(userRepository.getOne(userId));
        }
    }
}
