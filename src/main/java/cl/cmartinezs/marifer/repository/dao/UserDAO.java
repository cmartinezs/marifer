package cl.cmartinezs.marifer.repository.dao;

import java.util.List;

import cl.cmartinezs.marifer.repository.model.User;

public interface UserDAO {

	public User getByUsername(String username);

	public List<User> getUsers();
}
