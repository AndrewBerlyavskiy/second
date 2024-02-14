package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    public static final SessionFactory sessionFactory = Util.getSessionFactory();
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        String creation = "CREATE TABLE IF NOT EXISTS users (id BIGINT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(150), lastName VARCHAR(150), age TINYINT)";
        Transaction tr = null;
        Query query;
        try (Session session = sessionFactory.openSession()) {
            tr = session.beginTransaction();
            query = session.createSQLQuery(creation);
            int results = query.executeUpdate();
            tr.commit();
        } catch (Exception e) {
            if (tr != null) tr.rollback();
            throw e;
        }
    }

    @Override
    public void dropUsersTable() {
        String drop = "DROP TABLE IF EXISTS users";
        Transaction tr = null;
        Query query;
        try (Session session = sessionFactory.openSession()) {
            tr = session.beginTransaction();
            query = session.createSQLQuery(drop);
            int results = query.executeUpdate();
            tr.commit();
        } catch (Exception e) {
            if (tr != null) tr.rollback();
            throw e;
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Transaction tr = null;
        try (Session session = sessionFactory.openSession()) {
            tr = session.beginTransaction();
            User user = new User();
            user.setName(name);
            user.setLastName(lastName);
            user.setAge(age);
            session.save(user);
            tr.commit();
        } catch (Exception e) {
            if (tr != null) tr.rollback();
            throw e;
        }
    }

    @Override
    public void removeUserById(long id) {
        Transaction tr = null;
        /* String hql = "delete from User where id = :id";
        Query query; */
        try (Session session = sessionFactory.openSession()) {
            tr = session.beginTransaction();
            User user = session.load(User.class, id);
            session.delete(user);
            /* query = session.createQuery(hql);
            query.setParameter("id", id);
            int result = query.executeUpdate(); */
            tr.commit();
        } catch (Exception e) {
            if (tr != null) tr.rollback();
            throw e;
        }
    }

    @Override
    public List<User> getAllUsers() {
        Transaction tr = null;
        String hql = "from User";
        Query query;
        List<User> users;
        try (Session session = sessionFactory.openSession()) {
            tr = session.beginTransaction();
            query = session.createQuery(hql);
            users = query.list();
            tr.commit();
        } catch (Exception e) {
            if (tr != null) tr.rollback();
            throw e;
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        Transaction tr = null;
        String hql = "DELETE from User";
        Query query;
        try (Session session = sessionFactory.openSession()) {
            tr = session.beginTransaction();
            query = session.createQuery(hql);
            int results = query.executeUpdate();
            tr.commit();
            } catch (Exception e) {
                if (tr != null) tr.rollback();
                throw e;
            }
        }
    }
