package pl.coderslab.app.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.app.entity.Author;
import pl.coderslab.app.entity.Book;
import pl.coderslab.app.entity.Publisher;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

@Controller
@RequestMapping("/validate")
public class ValidationController {

    private final Validator validator;

    public ValidationController(Validator validator) {
        this.validator = validator;
    }

    @RequestMapping(value = "/book", method = RequestMethod.GET)
    @ResponseBody
    public String validateBook(){
        StringBuffer sb = new StringBuffer();

        Book book = new Book();
        book.setTitle("a");
        book.setRating(101);
        book.setPages(-1000);
        book.setPublisher(new Publisher());

        Set<ConstraintViolation<Book>> constraintViolations = validator.validate(book);

        for( ConstraintViolation<Book> bookConstraintViolation : constraintViolations){
            sb.append(bookConstraintViolation.getPropertyPath());
            sb.append(" ");
            sb.append(bookConstraintViolation.getMessage());
            sb.append("<br>");
        }

        return sb.toString();
    }

    @RequestMapping("/author")
    @ResponseBody
    public String validateAuthor(){
        StringBuffer sb = new StringBuffer();

        Author author = new Author();
        author.setYearOfBirth(2012);

        Set<ConstraintViolation<Author>> constraintViolations = validator.validate(author);

        for( ConstraintViolation<Author> authorConstraintViolation : constraintViolations){
            sb.append(authorConstraintViolation.getPropertyPath());
            sb.append(" ");
            sb.append(authorConstraintViolation.getMessage());
            sb.append("\n");
        }

        return sb.toString();
    }
}
