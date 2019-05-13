package studies.project.tripadvisor.service.impl;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@EnableAutoConfiguration
@Configuration
public class SearchServiceConf {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public SearchServiceConf(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Bean
    SearchServiceImpl searchService() {
        this.entityManager = entityManager.getEntityManagerFactory().createEntityManager();
        Session session = entityManager.unwrap(Session.class);

        SearchServiceImpl searchService = new SearchServiceImpl(entityManager);
        searchService.initializeSearchServiceImpl();
        return searchService;
    }
}

