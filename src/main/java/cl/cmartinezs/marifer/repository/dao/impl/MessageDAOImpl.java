package cl.cmartinezs.marifer.repository.dao.impl;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cl.cmartinezs.marifer.repository.dao.MessageDAO;
import cl.cmartinezs.marifer.repository.model.Message;

@Repository
public class MessageDAOImpl implements MessageDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@Transactional
	public Message getById(Integer id) {

		Session session = this.sessionFactory.getCurrentSession();
		
		try 
		{
			return (Message) session.createQuery("from Message where id = :id").setParameter("id", id).getSingleResult();
		} 
		catch (NoResultException e) 
		{
			return null;
		}
	}

}
