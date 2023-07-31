package lk.ijse.assignment.util;

import lk.ijse.assignment.entity.Author;
import lk.ijse.assignment.entity.Book;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class FactoryConfiguration {

    private static  FactoryConfiguration factoryConfiguration;

    public SessionFactory sessionFactory;

    private FactoryConfiguration(){

        Configuration configuration = new Configuration().configure();
        configuration.addAnnotatedClass(Book.class);
        configuration.addAnnotatedClass(Author.class);

        sessionFactory=configuration.buildSessionFactory();
    }

    public static FactoryConfiguration getInstance(){

        return (factoryConfiguration==null)? factoryConfiguration=new FactoryConfiguration():factoryConfiguration;
    }

    public Session getSession(){
        return sessionFactory.openSession();
    }
}
