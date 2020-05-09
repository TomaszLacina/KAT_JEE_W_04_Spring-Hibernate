package pl.coderslab.app.dao;

import org.springframework.stereotype.Repository;
import pl.coderslab.app.entity.Publisher;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class PublisherDao {

    @PersistenceContext
    private EntityManager entityManager;

    public void save(Publisher publisher){
        entityManager.persist(publisher);
    }

    public List<Publisher> findAll(){
        return entityManager.createQuery("SELECT p FROM Publisher p").getResultList();
    }

    public Publisher findById(Long id) {
        return entityManager.find(Publisher.class, id);
    }
}
