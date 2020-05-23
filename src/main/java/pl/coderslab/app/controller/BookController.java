package pl.coderslab.app.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.app.dao.BookDao;
import pl.coderslab.app.dao.PersonDao;
import pl.coderslab.app.dao.PersonDetailsDao;
import pl.coderslab.app.dao.PublisherDao;
import pl.coderslab.app.entity.*;
import pl.coderslab.app.repository.BookRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/book")
public class BookController {

    private final BookDao bookDao;

    private final BookRepository bookRepository;

    private final PublisherDao publisherDao;
    private final PersonDao personDao;
    private final PersonDetailsDao personDetailsDao;

    public BookController(BookDao bookDao, BookRepository bookRepository, PublisherDao publisherDao, PersonDao personDao, PersonDetailsDao personDetailsDao) {
        this.bookDao = bookDao;
        this.bookRepository = bookRepository;
        this.publisherDao = publisherDao;
        this.personDao = personDao;
        this.personDetailsDao = personDetailsDao;
    }



    @GetMapping(value = "/all")
    public String getAll(Model model){
//        List<Book> books = bookDao.findAll();
//        List<Book> books = bookRepository.findAll();
//        List<Book> books = bookRepository.findByTitle("KAT_JEE_W_04");
//        List<Book> books = bookRepository.customFindByTitle("KAT_JEE_W_04");

//        List<Book> byCategoryId = bookRepository.findByCategoryId(3L);
//
        Category category = new Category();
        category.setName("Takiej kategorii nie ma :-(");
        category.setId(3L);
//
        List<Book> byCategory = bookRepository.customFindByCategory(category);
//
        List<Book> books = new ArrayList<>();
//
        books.addAll(byCategory);
//        books.addAll(byCategoryId);

        model.addAttribute("books", books);
        return "books";
    }

    @GetMapping(value = "/testData")
    public void createTestData(){

        Book book = new Book();
        book.setTitle("Ksiazka z kategoria");
        book.setPages(123);
        book.setDescription("Takie tam, pisadlo");

        Category category = new Category();
        category.setName("Akcja");
        book.setCategory(category);
        book.setPublisher(publisherDao.findAll().get(0));
        bookRepository.save(book);
    }

    @GetMapping(value = "/rating/{rating}")
    @ResponseBody
    public List<Book> findWithRatingGreaterThen(@PathVariable Integer rating){
        List<Book> books = bookDao.findAllWithRatingGreaterThen(rating);

        return books;
    }

    @GetMapping(value = "/{id}")
    @ResponseBody
    public Book book(@PathVariable Long id) throws Exception {
        Optional<Book> book = bookRepository.findById(id);
        Book book3 = book.orElseThrow(Exception::new);


        List<Book> byCategoryId = bookRepository.findByCategoryId(1L);


        Book book2 = book.orElse(new Book());


        if(book.isPresent()) {
            Book book1 = book.get();
        }


        Book bookDaoById = bookDao.findById(id);
        if(bookDaoById == null){
            System.out.println("tutaj jakas obsluga bledu");
        }

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
//        publisher.getBooks().add(book);

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
