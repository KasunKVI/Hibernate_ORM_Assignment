package lk.ijse.assignment.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Book {

    @Id
    private String id;
    private String name;
    private String publisher;
    @ManyToMany
    private List<Author> author = new ArrayList<>();

    public Book() {
    }

    public Book(String id, String name, String publisher, List<Author> author) {
        this.id = id;
        this.name = name;
        this.publisher = publisher;
        this.author = author;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public List<Author> getAuthor() {
        return author;
    }

    public void setAuthor(List<Author> author) {
        this.author = author;
    }
}
