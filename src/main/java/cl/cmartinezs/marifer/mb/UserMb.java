package cl.cmartinezs.marifer.mb;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cl.cmartinezs.marifer.repository.model.User;
import cl.cmartinezs.marifer.services.UserService;

@Controller
@Scope("session")
public class UserMb implements Serializable {

	private static final long serialVersionUID = -1462802013256579870L;

	@Autowired
	private UserService userService;

	private User user = null;

	public User getUser() {
		if (user == null) {
			user = userService.getLogged();
		}

		return user;
	}
}
