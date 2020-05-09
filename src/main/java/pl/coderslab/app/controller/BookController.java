package pl.coderslab.app.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.app.dao.BookDao;
import pl.coderslab.app.dao.PersonDao;
import pl.coderslab.app.dao.PersonDetailsDao;
import pl.coderslab.app.dao.PublisherDao;
import pl.coderslab.app.entity.Book;
import pl.coderslab.app.entity.Person;
import pl.coderslab.app.entity.PersonDetails;
import pl.coderslab.app.entity.Publisher;

import java.util.List;

@Controller
@RequestMapping("/book")
public class BookController {

    private final BookDao bookDao;
    private final PublisherDao publisherDao;
    private final PersonDao personDao;
    private final PersonDetailsDao personDetailsDao;
    public BookController(BookDao bookDao, PublisherDao publisherDao, PersonDao personDao, PersonDetailsDao personDetailsDao) {
        this.bookDao = bookDao;
        this.publisherDao = publisherDao;
        this.personDao = personDao;
        this.personDetailsDao = personDetailsDao;
    }



    @GetMapping(value = "/all")
    @ResponseBody
    public List<Book> getAll(){
        List<Book> books = bookDao.findAll();

        return books;
    }

    @GetMapping(value = "/rating/{rating}")
    @ResponseBody
    public List<Book> findWithRatingGreaterThen(@PathVariable Integer rating){
        List<Book> books = bookDao.findAllWithRatingGreaterThen(rating);

        return books;
    }

    @GetMapping(value = "/{id}")
    @ResponseBody
    public Book book(@PathVariable Long id){
        return bookDao.findById(id);
    }


    @GetMapping(value = "/addBookWithPublisher")
    @ResponseBody
    public Publisher createWithPublisher(){
        Book book = new Book();
        book.setTitle("Nowa ksiazka");
        book.setDescription("Z nowym opisem");

        bookDao.save(book);

        Publisher publisher = new Publisher();
        publisher.setName("Nowy publisher");
        publisher.getBooks().add(book);

        publisherDao.save(publisher);

        return publisher;

    }

    @GetMapping(value = "/addBookWithPublisher2")
    @ResponseBody
    public String createWithPublisher2(){
        Book book = new Book();
        book.setTitle("Nowa ksiazka");
        book.setDescription("Z nowym opisem");

        bookDao.save(book);

        Publisher publisher = new Publisher();
        publisher.setName("Nowy publisher");
        publisher.getBooks().add(book);

        publisherDao.save(publisher);

        return publisher.toString();

    }

    @PostMapping("")
    @ResponseBody
    public Book create(@RequestBody Book book){

        bookDao.save(book);

        return book;
    }

    @PutMapping("/{id}")
    @ResponseBody
    public Book update(@PathVariable Long id, @RequestBody  Book book){
        Book bookInDb = bookDao.findById(id);

        bookInDb.setTitle(book.getTitle());
        bookInDb.setRating(book.getRating());
        bookInDb.setDescription(book.getDescription());

        bookDao.update(bookInDb);

        return bookInDb;
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public void delete(@PathVariable Long id){
        bookDao.remove(id);

    }

    @GetMapping("/userTest2")
    public void createUser2(){
        Person person = new Person();
        person.setEmail("nowy mail");

        PersonDetails personDetails = new PersonDetails();
        personDetails.setFirstName("Tomasz");

        personDetailsDao.save(personDetails);

        person.setPersonDetails(personDetails);
        personDao.save(person);

    }

}
