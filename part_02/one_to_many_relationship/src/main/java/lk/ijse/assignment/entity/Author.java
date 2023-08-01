package lk.ijse.assignment.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class Author {

    @Id
    private String a_Id;
    private String name;
    private String contact;
    @OneToMany(mappedBy = "author" )
    private List<Book> books;
    public Author() {
    }

    public Author(String a_Id, String name, String contact, List<Book> books) {
        this.a_Id = a_Id;
        this.name = name;
        this.contact = contact;
        this.books = books;
    }

    public String getA_Id() {
        return a_Id;
    }

    public void setA_Id(String a_Id) {
        this.a_Id = a_Id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
