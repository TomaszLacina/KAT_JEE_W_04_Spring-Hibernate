package pl.coderslab.app.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.app.dao.BookDao;
import pl.coderslab.app.dao.PublisherDao;
import pl.coderslab.app.entity.Book;
import pl.coderslab.app.entity.Publisher;

@Controller
@RequestMapping("/book")
public class BookController {

    private final BookDao bookDao;
    private final PublisherDao publisherDao;

    public BookController(BookDao bookDao, PublisherDao publisherDao) {
        this.bookDao = bookDao;
        this.publisherDao = publisherDao;
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

}
