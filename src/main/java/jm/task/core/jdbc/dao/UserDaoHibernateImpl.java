package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;


import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    SessionFactory sessionFactory = Util.getSessionFactory();

    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        Session session = sessionFactory.openSession();
        session.createSQLQuery("CREATE TABLE IF NOT EXISTS users(" +
                "id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY," +
                " name VARCHAR (50), lastName VARCHAR(50), age TINYINT)")
                .executeUpdate();
        session.close();
    }

    @Override
    public void dropUsersTable() {
        Session session = sessionFactory.openSession();
        session.createSQLQuery("DROP TABLE IF EXISTS users")
                .executeUpdate();
        session.close();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(new User(name, lastName, age));
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void removeUserById(long id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        User user = (User) session.get(User.class, id);
//        session.remove(user);
        session.delete(user);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<User> getAllUsers() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<User> result = session.createCriteria(User.class).list();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    @Override
    public void cleanUsersTable() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.createQuery("DELETE FROM User").executeUpdate();
        session.getTransaction().commit();
        session.close();
    }
}
