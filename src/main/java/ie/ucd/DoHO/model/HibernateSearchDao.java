package ie.ucd.DoHO.model;
import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
@Repository
public class HibernateSearchDao {
    @PersistenceContext
    private EntityManager entityManager;


    private QueryBuilder getQueryBuilder() throws InterruptedException {

        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
        fullTextEntityManager.createIndexer().startAndWait();

        return fullTextEntityManager.getSearchFactory()
                .buildQueryBuilder()
                .forEntity(Artifact.class)
                .get();
    }

    private FullTextQuery getJpaQuery(org.apache.lucene.search.Query luceneQuery) {

        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);

        return fullTextEntityManager.createFullTextQuery(luceneQuery, Artifact.class);
    }

    public List<Artifact> fuzzySearch(String searchTerm) throws InterruptedException {
        Query fuzzyQuery = getQueryBuilder().keyword().fuzzy()
                .withEditDistanceUpTo(2).withPrefixLength(0).onFields("title", "author","genre","language")
                .matching(searchTerm).createQuery();


        List<Artifact> results = getJpaQuery(fuzzyQuery).getResultList();
        return results;
    }


}
