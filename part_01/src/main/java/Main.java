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
            System.out.println("1. Add Book");
            System.out.println("2. Update Book");
            System.out.println("3. Retrieve Book");
            System.out.println("4. Delete Book");
            System.out.println("5. Exit");
            System.out.print("\nEnter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    addBook();
                    break;
                case 2:
                    updateBook();
                    break;
                case 3:
                    retrieveBook();
                    break;
                case 4:
                    deleteBook();
                    break;
                case 5:
                    System.out.println("Exiting the system. Goodbye!");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

    }

    private static void deleteBook() {

        System.out.println("\n--- Delete Book ---");
        System.out.print("\nEnter Id of the book to delete: ");
        String id = scanner.nextLine();

        Book book = session.find(Book.class, id);
        if (book != null) {

            session.remove(book);
            commitTransaction();

            System.out.println("\nBook deleted successfully!");


        }else {

            System.out.println("\nBook not found with this Id: " + id);
        }
        scanner.nextLine(); // Consume newline character
    }

    private static void retrieveBook() {

        System.out.println("\n--- Retrieve Book ---");
        System.out.print("\nEnter Id of the book to retrieve: ");
        String id = scanner.nextLine();

        Book book=session.find(Book.class, id);

        if (book!= null) {



            System.out.println("\n-- Book Details --");
            System.out.println("\nName: " + book.getName());
            System.out.println("\nAuthor : "+book.getAuthor());
            System.out.println("Publisher: " + book.getPublisher());


        }else {

            System.out.println("\nBook not found with this Id: " + id);
        }

        scanner.nextLine(); // Consume newline character

    }

    private static void updateBook() {

        System.out.println("\n--- Update Book ---");
        System.out.print("\nEnter Id of the book to update: ");
        String id = scanner.nextLine();


            if (session.find(Book.class, id)!= null) {
                System.out.print("\nEnter updated Book Name: ");
                String name = scanner.nextLine();
                System.out.print("\nEnter updated Author Name: ");
                String author = scanner.nextLine();
                System.out.print("Enter updated Book Publisher: ");
                String publisher = scanner.nextLine();

                Book book = new Book(id,name,author,publisher);
                session.merge(book);

                commitTransaction();

                System.out.println("\nBook updated successfully!");

                scanner.nextLine(); // Consume newline character



            }else {

                System.out.println("\nBook not found with this Id: " + id);
            }



    }

    private static void addBook() {

        System.out.println("\n--- Add Book ---");
        System.out.print("\nEnter Book Id: ");
        String id = scanner.nextLine();
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Author: ");
        String author = scanner.nextLine();
        System.out.print("Enter Publisher: ");
        String publisher = scanner.nextLine();

        Book newBook = new Book(id, name, author,publisher);
        session.persist(newBook);

        commitTransaction();

        System.out.println("Book added successfully!");

        scanner.nextLine();

    }

    private   static void commitTransaction(){
        transaction.commit();
        session.close();
    }
}