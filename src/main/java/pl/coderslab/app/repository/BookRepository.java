package pl.coderslab.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.app.entity.Book;
import pl.coderslab.app.entity.Category;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByRating(Integer rating);

    List<Book> findByTitle(String title);

    List<Book> findByCategory(Category category);

    List<Book> findByCategoryId(Long id);
}
