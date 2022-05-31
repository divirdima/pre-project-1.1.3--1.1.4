package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class UserDaoHibernateImpl implements UserDao {
	private Util util;
	
    public UserDaoHibernateImpl() {

    }
    
    public UserDaoHibernateImpl(String dbName, String username, String password) {
    	util = new Util(dbName, username, password);
    }

    @Override
    public void createUsersTable() {
    	Session session = util.getSessionFactory().getCurrentSession();
    	session.getTransaction().begin();
    	session.getTransaction().commit();
    	session.close(); 
    }

    @Override
    public void dropUsersTable() {
    	
//    	Session session = util.getSessionFactory().getCurrentSession();
//    	session.getTransaction().begin();
//    	SQLQuery query = session.createSQLQuery("DROP TABLE IF EXISTS User");
//    	query.executeUpdate();
//    	session.close();
    	
    	util.getSessionFactory().close();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
    	Session session = util.getSessionFactory().getCurrentSession();
    	session.getTransaction().begin();
    	User user = new User(name, lastName, age);
    	session.save(user);    	
    	session.getTransaction().commit();
    	session.close(); 
    }

    @Override
    public void removeUserById(long id) {
    	Session session = util.getSessionFactory().getCurrentSession();
    	session.getTransaction().begin();
    	Query query = session.createQuery("DELETE User where id = :idParam");
    	query.setParameter("idParam", id);
    	query.executeUpdate();
    }

    @Override
    public List<User> getAllUsers() {
    	List<User> result = new ArrayList<>();
    	Session session = util.getSessionFactory().getCurrentSession();
    	session.getTransaction();
    	result = session.createQuery("FROM User", User.class).list();
    	session.close(); 
    	return result;
    }

    @Override
    public void cleanUsersTable() {
    	Session session = util.getSessionFactory().getCurrentSession();
    	session.getTransaction().begin();
    	Query<User> query = session.createQuery("DELETE FROM User");
    	query.executeUpdate();
    	session.close();
    }
}
