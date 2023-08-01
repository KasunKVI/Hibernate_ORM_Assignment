package lk.ijse.assignment;

import lk.ijse.assignment.entity.Author;
import lk.ijse.assignment.entity.Book;
import lk.ijse.assignment.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static Session session = FactoryConfiguration.getInstance().getSession();
    static Transaction transaction = session.beginTransaction();
    static List<Book> books = new ArrayList<>();
    static List<Author> authors = new ArrayList<>();
    public static void main(String[] args) {

        System.out.println("\n--- Book Management System ---");
        String dis = "y";



        while (dis.equalsIgnoreCase("y")) {
            System.out.println("\n--- Add Books ---");

            System.out.print("\nEnter Book Id: ");
            String b_id = scanner.nextLine();
            System.out.print("Enter Name: ");
            String b_name = scanner.nextLine();
            System.out.print("Enter Publisher: ");
            String publisher = scanner.nextLine();

            Book newBook = new Book();
            newBook.setId(b_id);
            newBook.setName(b_name);
            newBook.setPublisher(publisher);

            books.add(newBook);

            System.out.print("Did you want to add another book (y/n) ? : ");
            dis = scanner.nextLine();
        }

        System.out.println("These are the book list you added\n");
        for (Book book : books) {
            System.out.println(".....  " + book.getName());
        }

        for (Book book : books) {
            System.out.println("\n--- Add Author(s) for Book: " + book.getName() + " ---");

            String dis2 = "y";

            while (dis2.equalsIgnoreCase("y")) {
                System.out.println("\n--- Add Author ---");

                System.out.print("\nEnter Author Id: ");
                String id = scanner.nextLine();
                System.out.print("Enter Name: ");
                String name = scanner.nextLine();
                System.out.print("Enter Contact: ");
                String contact = scanner.nextLine();

                Author author = new Author();
                author.setA_Id(id);
                author.setName(name);
                author.setContact(contact);


                boolean authorExists = false;
                for (Author existingAuthor : authors) {
                    if (existingAuthor.getA_Id().equals(author.getA_Id())) {
                        authorExists = true;
                        break;
                    }
                }

                if (authorExists) {
                    System.out.println("Author with Id " + id + " already exists in the list.");
                } else {
                    authors.add(author);
                }
                book.getAuthor().add(author);
                author.getBooks().add(book);

                System.out.print("\nDid you want to add another author for this book (y/n) ? : ");
                dis2 = scanner.nextLine();
            }
        }

        for (Book book : books) {
            session.persist(book);
        }

        for (Author author : authors) {
            session.persist(author);
        }

        transaction.commit();
        session.close();

        System.out.println("\nAuthor(s) and Book(s) Added successfully!");
    }

}