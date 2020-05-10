package pl.coderslab.app.dao;

import org.springframework.stereotype.Repository;
import pl.coderslab.app.entity.Author;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class AuthorDao {

    @PersistenceContext
    EntityManager entityManager;


    public List<Author> findAll(){
        Query query = entityManager.createQuery("SELECT a FROM Author a");
        List<Author> authors = query.getResultList();

        return authors;
    }

    public void save(Author author){
        entityManager.persist(author);
    }

    public void update(Author author){

        entityManager.merge(author);
    }

    public Author findById(Long id){
        return entityManager.find(Author.class, id);
    }

    public void remove(Long id){
        entityManager.remove(findById(id));
    }
}
