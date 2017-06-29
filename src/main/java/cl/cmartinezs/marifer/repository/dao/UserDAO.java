package cl.cmartinezs.marifer.repository.dao;

import cl.cmartinezs.marifer.repository.model.User;

public interface UserDAO {

	public User getByUsername(String username);
}
