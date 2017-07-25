package cl.cmartinezs.marifer.mb;

import java.io.IOException;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;

@Controller
@Scope("request")
public class LoginMb implements PhaseListener {

	private static final long serialVersionUID = -4730479507145305973L;

	protected final Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	private LanguageMb languageMb;

	@Override
	public void afterPhase(PhaseEvent event) {

	}

	@Override
	public void beforePhase(PhaseEvent event) {

	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RENDER_RESPONSE;
	}

	public String doLogin() throws ServletException, IOException {
		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();

		RequestDispatcher dispatcher = ((ServletRequest) context.getRequest())
				.getRequestDispatcher("/auth/j_spring_security_check");

		dispatcher.forward((ServletRequest) context.getRequest(), (ServletResponse) context.getResponse());

		FacesContext.getCurrentInstance().responseComplete();

		return null;
	}

	public void updateMessages(boolean update) {

		Exception e = (Exception) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.get(WebAttributes.AUTHENTICATION_EXCEPTION);

		if (e instanceof BadCredentialsException) {
			logger.debug("Found exception in session map: " + e);
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
					.put(WebAttributes.AUTHENTICATION_EXCEPTION, null);
			
			ResourceBundle bundle = ResourceBundle.getBundle("bundle.messages", languageMb.getLocale());

			String error = bundle.getString("loginForm.error.invaliduserpassword");

			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, error, error);
			
			FacesContext.getCurrentInstance().addMessage(null, facesMessage);
		}

	}

}
