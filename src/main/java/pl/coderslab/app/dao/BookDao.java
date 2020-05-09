package pl.coderslab.app.dao;

import org.springframework.stereotype.Repository;
import pl.coderslab.app.entity.Book;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class BookDao {

    @PersistenceContext
    EntityManager entityManager;


    public List<Book> findAll(){
        Query query = entityManager.createQuery("SELECT b FROM Book b");
        List<Book> books = query.getResultList();

        return books;
    }

    public List<Book> findAllWithRatingGreaterThen(Integer rating){
        Query query = entityManager.createQuery("SELECT b FROM Book b WHERE b.rating > :rating");
        query.setParameter("rating", rating);

        List<Book> books = query.getResultList();

        return books;
    }


    public void save(Book book){
        entityManager.persist(book);
    }

    public void update(Book book){

        entityManager.merge(book);
    }

    public Book findById(Long id){
        return entityManager.find(Book.class, id);
    }

    public void remove(Long id){
        entityManager.remove(findById(id));
    }
}
