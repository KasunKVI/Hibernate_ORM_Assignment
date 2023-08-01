package lk.ijse.assignment;

import lk.ijse.assignment.entity.Author;
import lk.ijse.assignment.entity.Book;
import lk.ijse.assignment.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static Session session = FactoryConfiguration.getInstance().getSession();
    static Transaction transaction = session.beginTransaction();
    public static void main(String[] args) {


        while (true) {
            System.out.println("\n--- Book Management System ---");


            System.out.println("\n--- Add Author ---");

            System.out.print("\nEnter Author Id: ");
            String id = scanner.nextLine();

            Author auth = session.find(Author.class, id);

            if (auth == null) {
                System.out.print("Enter Name: ");
                String name = scanner.nextLine();
                System.out.print("Enter Contact: ");
                String contact = scanner.nextLine();

                Author author = new Author(id, name, contact);
                session.persist(author);

                System.out.print("\n\nClick Enter To Continue -->  ");
                scanner.nextLine();

                System.out.println("\n--- Add This Author Book ---");

                System.out.print("\nEnter Book Id: ");
                String b_id = scanner.nextLine();
                System.out.print("Enter Name: ");
                String b_name = scanner.nextLine();
                System.out.print("Enter Publisher: ");
                String publisher = scanner.nextLine();

                Book newBook = new Book(b_id, b_name, publisher, author);
                session.persist(newBook);

                transaction.commit();
                session.close();

                System.out.println("\nAuthor Added successfully!");
                System.out.println("Book Added successfully!");
                return;
            } else {
                System.out.println("This Author Already Exist Press Enter Add Again");
                scanner.nextLine(); // Consume newline character
            }
        }


    }
}