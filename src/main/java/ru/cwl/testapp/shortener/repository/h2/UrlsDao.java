package ru.cwl.testapp.shortener.repository.h2;

import org.springframework.stereotype.Repository;
import ru.cwl.testapp.shortener.model.Urls;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by vadim.tishenko
 * on 04.06.2017 21:24.
 */
@Repository
public class UrlsDao {
    @PersistenceContext
    private EntityManager entityManager;

    public void create(Urls urls) {
        entityManager.persist(urls);
    }

    public Urls getByShortUrl(String shortUrl) {
        return entityManager.find(Urls.class,shortUrl);
    }
}
