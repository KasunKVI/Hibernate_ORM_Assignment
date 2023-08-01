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
    static List<Book> books =  new ArrayList<>();
    public static void main(String[] args) {

        System.out.println("\n--- Book Management System ---");
        String dis="y";




            while (dis.equalsIgnoreCase("y")) {
                System.out.println("\n--- Add Books Of One Author ---");

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


                System.out.print("Did you want add another book(y/n) ? : ");
                dis = scanner.nextLine();
            }



            System.out.println("\n--- Add Author ---");

            System.out.print("\nEnter Author Id: ");
            String id = scanner.nextLine();


                System.out.print("Enter Name: ");
                String name = scanner.nextLine();
                System.out.print("Enter Contact: ");
                String contact = scanner.nextLine();

                Author author = new Author(id, name, contact,books);
                session.persist(author);

            for (Book book :books) {
                book.setAuthor(author);
                session.persist(book);
            }

                System.out.print("\n\nClick Enter To Continue -->  ");
                scanner.nextLine();

            transaction.commit();
            session.close();

            System.out.println("\nAuthor Added successfully!");
            System.out.println("Book Added successfully!");


    }
}