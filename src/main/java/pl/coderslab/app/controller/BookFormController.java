package pl.coderslab.app.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.RedirectView;
import pl.coderslab.app.dao.BookDao;
import pl.coderslab.app.dao.PublisherDao;
import pl.coderslab.app.entity.Book;
import pl.coderslab.app.entity.Publisher;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/bookForm")
public class BookFormController {

    private final PublisherDao publisherDao;
    private final BookDao bookDao;


    public BookFormController(PublisherDao publisherDao, BookDao bookDao) {
        this.publisherDao = publisherDao;
        this.bookDao = bookDao;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String getForm(Model model){
        model.addAttribute("book", new Book());
        return "bookForm";
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String create(@Valid Book book, BindingResult result){
        if(result.hasErrors()){
            return "bookForm";
        }

        bookDao.save(book);
        return "redirect:/book/all";
    }

    @ModelAttribute("publishers")
    public List<Publisher> publisherList(){
        return publisherDao.findAll();
    }
}
