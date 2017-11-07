package cl.cmartinezs.marifer.repository.dao.impl;

import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cl.cmartinezs.marifer.repository.dao.UserDAO;
import cl.cmartinezs.marifer.repository.model.User;

@Repository
public class UserDAOImpl implements UserDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@Transactional
	public User getByUsername(String username) {
		Session session = this.sessionFactory.getCurrentSession();

		try {
			return session.createQuery("from User where username = :username", User.class)
					.setParameter("username", username).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	@Transactional
	public List<User> getUsers() {
		Session session = this.sessionFactory.getCurrentSession();

		try {
			return session.createQuery("from User", User.class).getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}
}
