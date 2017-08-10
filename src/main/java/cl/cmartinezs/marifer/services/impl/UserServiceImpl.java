package cl.cmartinezs.marifer.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cl.cmartinezs.marifer.repository.dao.UserDAO;
import cl.cmartinezs.marifer.repository.model.User;
import cl.cmartinezs.marifer.services.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {

	private User userLogged;

	@Autowired
	private UserDAO userDAO;

	@Override
	@Transactional
	public User getByUsername(String username) {
		return userDAO.getByUsername(username);
	}

	@Override
	public boolean isValid(Authentication authentication) {
		String username = (String) authentication.getPrincipal();
		String password = (String) authentication.getCredentials();
		userLogged = getByUsername(username);
		return userLogged != null ? userLogged.getPassword().equals(password) : false;
	}

	@Override
	public User getLogged() {
		return userLogged;
	}

}
