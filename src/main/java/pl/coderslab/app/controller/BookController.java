package pl.coderslab.app.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.app.dao.BookDao;
import pl.coderslab.app.entity.Book;

@Controller
@RequestMapping("/book")
public class BookController {

    private final BookDao bookDao;

    public BookController(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @GetMapping(value = "/{id}")
    @ResponseBody
    public Book book(@PathVariable Long id){
        return bookDao.findById(id);
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
