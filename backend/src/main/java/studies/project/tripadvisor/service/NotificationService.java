package studies.project.tripadvisor.service;

import java.util.List;

public interface NotificationService {

    void notifyObservers(Long placeId, String message);

    void addObserver(Long placeId, Long userId);

    void removeObserver(Long placeId, Long userId);

    List<String> retrieveMessages(Long userId);
}
