package pl.coderslab.app.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "publishers")
public class Publisher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
//
//
//    @JsonIgnore
//    @OneToMany
//    @JoinColumn(name = "publisher_id")
//    private List<Book> books.jsp = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public List<Book> getBooks() {
//        return books.jsp;
//    }
//
//    public void setBooks(List<Book> books.jsp) {
//        this.books.jsp = books.jsp;
//    }

    @Override
    public String toString() {
        return "Publisher{" +
                "id=" + id +
                ", name='" + name + '\'' +
//                ", books.jsp=" + books.jsp +
                '}';
    }
}
