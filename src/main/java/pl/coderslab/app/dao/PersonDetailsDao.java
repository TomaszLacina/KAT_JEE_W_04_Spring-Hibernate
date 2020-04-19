package pl.coderslab.app.dao;


import org.springframework.stereotype.Repository;
import pl.coderslab.app.entity.PersonDetails;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
@Transactional
public class PersonDetailsDao {

    @PersistenceContext
    EntityManager entityManager;

    public void save(PersonDetails personDetails){
        entityManager.persist(personDetails);
    }

}
