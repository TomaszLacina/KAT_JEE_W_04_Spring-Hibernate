package pl.coderslab.app.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.app.dao.AuthorDao;
import pl.coderslab.app.dao.BookDao;
import pl.coderslab.app.entity.Author;
import pl.coderslab.app.entity.Book;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Random;

@Controller
@RequestMapping("/author")
public class AuthorController {

    private final AuthorDao authorDao;
    private final BookDao bookDao;



    @NotBlank
    public AuthorController(AuthorDao authorDao, BookDao bookDao) {
        this.authorDao = authorDao;
        this.bookDao = bookDao;
    }


    @GetMapping(value = "/all")
    public String getAll(Model model){
        List<Author> authors = authorDao.findAll();
        model.addAttribute("authors", authors);
        return "authors";
    }

    @GetMapping
    @ResponseBody
    public String createSomeAuthors(){
        Random random = new Random();
        List<Book> all = bookDao.findAll();
        for(int i = 0 ; i < 5 ; i++){
            Author author = new Author();

            Book book = all.get(random.nextInt(all.size()));
            author.getBookList().add(book);

            author.setName("tomasz " + i + " " + book.getTitle());

            authorDao.save(author);

            book.getAuthors().add(author);
            bookDao.update(book);
        }


        return "ok";
    }

}
