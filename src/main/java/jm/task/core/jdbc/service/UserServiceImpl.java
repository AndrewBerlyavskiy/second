package jm.task.core.jdbc.service;

/* import jm.task.core.jdbc.dao.UserDaoJDBCImpl; */
import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.util.List;

/* import static jm.task.core.jdbc.util.Util.connection; */

public class UserServiceImpl implements UserService {
     public void createUsersTable() {
        /* userDaoJDBCImpl.createUsersTable(); */
         userDaoHibernate.createUsersTable();
    }

    public void dropUsersTable() {
       // userDaoJDBCImpl.dropUsersTable();
        userDaoHibernate.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
      //   userDaoJDBCImpl.saveUser(name, lastName, age);
        userDaoHibernate.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) {
      //  userDaoJDBCImpl.removeUserById(id);
        userDaoHibernate.removeUserById(id);
    }

    public List<User> getAllUsers() {
      // return userDaoJDBCImpl.getAllUsers();
        return userDaoHibernate.getAllUsers();
    }

    public void cleanUsersTable() {
       // userDaoJDBCImpl.cleanUsersTable();
        userDaoHibernate.cleanUsersTable();
    }

    UserDao userDaoHibernate = new UserDaoHibernateImpl();

    /* Connection connection = Util.getConnection();
    UserDaoJDBCImpl userDaoJDBCImpl = new UserDaoJDBCImpl(connection); */

}
