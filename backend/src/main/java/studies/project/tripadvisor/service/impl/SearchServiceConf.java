package studies.project.tripadvisor.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;


@EnableAutoConfiguration
@Configuration
public class SearchServiceConf {

    @Autowired
    private EntityManager bentityManager;

    @Bean
    SearchServiceImpl searchService() {
        SearchServiceImpl searchService = new SearchServiceImpl(bentityManager);
        searchService.initializeSearchServiceImpl();
        return searchService;
    }
}


