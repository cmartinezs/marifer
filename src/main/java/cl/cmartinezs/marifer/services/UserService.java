package cl.cmartinezs.marifer.services;

import org.springframework.security.core.Authentication;

import cl.cmartinezs.marifer.repository.model.User;

public interface UserService {
	
	public User getByUsername(String username);

	public boolean isValid(Authentication authentication);
	
	public User getLogged();
}
