package cl.cmartinezs.marifer.mb;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cl.cmartinezs.marifer.repository.model.User;
import cl.cmartinezs.marifer.repository.model.enums.UserStatus;
import cl.cmartinezs.marifer.services.UserService;

@Controller
@Scope("view")
public class UserMb implements Serializable {

	private static final long serialVersionUID = 755647858824474728L;
	private static final String BUNDLE_MESSAGES = "bundle.messages";
	private static final String BM_CRUD_USER_DIALOG_EDIT_HEADER = "crud.user.dialog.edit.header";
	private static final String BM_CRUD_USER_DIALOG_VIEW_HEADER = "crud.user.dialog.view.header";
	
	@Autowired
	private LanguageMb languageMb;

	@Autowired
	private UserService userService;

	private List<User> users = null;
	
	private User selectedUser = null;
	
	private String passwordOne = null;
	
	private String passwordTwo = null;
	
	private boolean readOnlySelectedUser = true;
	
	private String dlgUserHeader = null;
	
	private UserStatus[] statuses = UserStatus.values();

	@PostConstruct
	public void init() {
		users = userService.getUsers();
	}

	public List<User> getUsers() {
		return users;
	}

	public User getSelectedUser() {
		return selectedUser;
	}

	public void setSelectedUser(User selectedUser) {
		this.selectedUser = selectedUser;
	}	
	
	public String getPasswordOne() {
		return passwordOne;
	}

	public void setPasswordOne(String passwordOne) {
		this.passwordOne = passwordOne;
	}

	public String getPasswordTwo() {
		return passwordTwo;
	}

	public void setPasswordTwo(String passwordTwo) {
		this.passwordTwo = passwordTwo;
	}

	public boolean getReadOnlySelectedUser() {
		return readOnlySelectedUser;
	}

	public String getDlgUserHeader() {
		return dlgUserHeader;
	}

	public UserStatus[] getStatuses() {
		return statuses;
	}

	public String setOnlyReadDlgUser() {
		ResourceBundle bundle = ResourceBundle.getBundle(BUNDLE_MESSAGES, languageMb.getLocale()); // TODO Integrar el bundle dentro del LanguajeMb, para no volver a instanciar en cada metodo
		dlgUserHeader = MessageFormat.format(bundle.getString(BM_CRUD_USER_DIALOG_VIEW_HEADER), selectedUser.getUsername());
		readOnlySelectedUser = true;
		return null;
	}
	
	public String setEditableDlgUser() {
		ResourceBundle bundle = ResourceBundle.getBundle(BUNDLE_MESSAGES, languageMb.getLocale()); // TODO Integrar el bundle dentro del LanguajeMb, para no volver a instanciar en cada metodo
		dlgUserHeader = MessageFormat.format(bundle.getString(BM_CRUD_USER_DIALOG_EDIT_HEADER), selectedUser.getUsername());
		readOnlySelectedUser = false;
		return null;
	}
}
