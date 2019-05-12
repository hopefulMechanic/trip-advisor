package studies.project.tripadvisor.service.impl;

import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import studies.project.tripadvisor.entity.Place;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class SearchServiceImpl {

    @PersistenceContext
    @Autowired
    private final EntityManager entityManager;

    @Autowired
    protected SearchServiceImpl(EntityManager entityManager) {
        super();
        this.entityManager = entityManager;
    }

    protected void initializeSearchServiceImpl() {
        try {
            FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
            fullTextEntityManager.createIndexer().startAndWait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Transactional
    public List<Place> fuzzySearch(String searchTerm) {
        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
        QueryBuilder qb = fullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(Place.class).get();
        Query luceneQuery = qb.keyword().fuzzy().withEditDistanceUpTo(1).withPrefixLength(1).onFields("name", "description", "city", "country")
                .matching(searchTerm).createQuery();

        javax.persistence.Query jpaQuery = fullTextEntityManager.createFullTextQuery(luceneQuery, Place.class);

        List<Place> placeList = null;
        try {
            placeList = jpaQuery.getResultList();
        } catch (NoResultException nre) {

        }

        return placeList;
    }
}
