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


    private QueryBuilder getQueryBuilderArtifact() throws InterruptedException {

        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
        fullTextEntityManager.createIndexer().startAndWait();

        return fullTextEntityManager.getSearchFactory()
                .buildQueryBuilder()
                .forEntity(Artifact.class)
                .get();
    }

    private FullTextQuery getJpaQueryArtifact(org.apache.lucene.search.Query luceneQuery) {

        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);

        return fullTextEntityManager.createFullTextQuery(luceneQuery, Artifact.class);
    }


    private QueryBuilder getQueryBuilderUser() throws InterruptedException {

        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
        fullTextEntityManager.createIndexer().startAndWait();

        return fullTextEntityManager.getSearchFactory()
                .buildQueryBuilder()
                .forEntity(User.class)
                .get();
    }

    private FullTextQuery getJpaQueryUser(org.apache.lucene.search.Query luceneQuery) {

        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);

        if(fullTextEntityManager != null){
        }
        return fullTextEntityManager.createFullTextQuery(luceneQuery, User.class);
    }



    public List<Artifact> fuzzySearchArtifact(String searchTerm) throws InterruptedException {
        Query fuzzyQuery = getQueryBuilderArtifact().keyword().fuzzy()
                .withEditDistanceUpTo(2).withPrefixLength(0).onFields("title", "author","genre","language")
                .matching(searchTerm).createQuery();


        List<Artifact> results = getJpaQueryArtifact(fuzzyQuery).getResultList();
        return results;
    }


    public List<User> fuzzySearchUser(String searchTerm) throws InterruptedException {

        Query fuzzyQuery = getQueryBuilderUser().keyword().fuzzy()
                .withEditDistanceUpTo(2).withPrefixLength(0).onFields("username","email")
                .matching(searchTerm).createQuery();


        List<User> results = getJpaQueryUser(fuzzyQuery).getResultList();

        return results;
    }


}
