package org.jsp.springuserapp.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.jsp.springuserapp.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {
	@Autowired
	private EntityManager manager;

	public User saveUser(User user) {
		EntityTransaction transaction = manager.getTransaction();
		manager.persist(user);
		transaction.begin();
		transaction.commit();
		return user;
	}

	public User updateUser(User user) {
		EntityTransaction transaction = manager.getTransaction();
		User dbUser = manager.find(User.class, user.getId());
		if (dbUser != null) {
			dbUser.setEmail(user.getEmail());
			dbUser.setName(user.getName());
			dbUser.setPassword(user.getPassword());
			dbUser.setPhone(user.getPhone());
			transaction.begin();
			transaction.commit();
			return dbUser;
		}
		return null;
	}

	public User findById(int id) {
		return manager.find(User.class, id);
	}

	public User verifyUser(long phone, String password) {
		Query q = manager.createQuery("select u from User u where u.phone=?1 and u.password=?2");
		q.setParameter(1, phone);
		q.setParameter(2, password);
		try {
			return (User) q.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public User verifyUser(String email, String password) {
		Query q = manager.createQuery("select u from User u where u.email=?1 and u.password=?2");
		q.setParameter(1, email);
		q.setParameter(2, password);
		try {
			return (User) q.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public User verifyUser(int id, String password) {
		Query q = manager.createQuery("select u from User u where u.id=?1 and u.password=?2");
		q.setParameter(1, id);
		q.setParameter(2, password);
		try {
			return (User) q.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public boolean delete(int id) {
		User user = findById(id);
		if (user != null) {
			EntityTransaction transaction = manager.getTransaction();
			manager.remove(user);
			transaction.begin();
			transaction.commit();
			return true;
		}
		return false;
	}

	@SuppressWarnings(value = { "unchecked" })
	public List<String> getNames() {
		Query q = manager.createQuery("select u.name from User u");
		return q.getResultList();
	}
}
