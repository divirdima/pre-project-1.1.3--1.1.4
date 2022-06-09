package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class UserDaoHibernateImpl implements UserDao {
	
	private final Util util = Util.getInstance();
	
    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
    	try (Session session = util.getSessionFactory().openSession();) {
            Transaction transaction = session.beginTransaction();
            session.createNativeQuery("CREATE TABLE IF NOT EXISTS Users" +
                            "(id int not null auto_increment, name VARCHAR(30), " +
                            "lastname VARCHAR(30), age numeric, PRIMARY KEY (id))", User.class)
                    .executeUpdate();
            transaction.commit();
        } catch (HibernateException ex) {
    		ex.printStackTrace();
    	} 
    }

    @Override
    public void dropUsersTable() {
    	
    	 try (Session session = util.getSessionFactory().openSession()) {
             Transaction transaction = session.beginTransaction();
             session.createNativeQuery("DROP TABLE IF EXISTS Users", User.class).executeUpdate();
             transaction.commit();
         } catch (HibernateException ex) {
     		ex.printStackTrace();
     	}
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
    	
    	try (Session session = util.getSessionFactory().openSession()) {
    		Transaction transaction = session.beginTransaction();
    		session.save(new User(name, lastName, age)); 
    		transaction.commit();
    	} catch (HibernateException ex) {
    		ex.printStackTrace();
    	}
    }

    @Override
    public void removeUserById(long id) {
	    try (Session session = util.getSessionFactory().getCurrentSession()) {
    		session.getTransaction().begin();
    		Query query = session.createQuery("DELETE User where id = :idParam");
    		query.setParameter("idParam", id);
    		query.executeUpdate();
    	} catch (HibernateException ex) {
    		ex.printStackTrace();
    	}
    }

    @Override
    public List<User> getAllUsers() {
	Transaction tr = null;
    	List<User> result = new ArrayList<>();
    	try (Session session = util.getSessionFactory().openSession()) {
            tr = session.beginTransaction();
            result = session.createQuery("FROM User", User.class).list();
		tr.commit();
    	} catch (HibernateException ex) {
		if (tr != null) {
			tr.rollback();
		}
    		ex.printStackTrace();
    	}
    	return result;
    }

    @Override
    public void cleanUsersTable() {
    	
    	try (Session session = util.getSessionFactory().openSession()) {
    		Transaction tr = session.beginTransaction();
        	session.createNativeQuery("TRUNCATE Users", User.class).executeUpdate();
        	tr.commit();
    	} catch (HibernateException ex) {
    		ex.printStackTrace();
    	}
    }
}
