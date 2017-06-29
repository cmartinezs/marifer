package cl.cmartinezs.marifer.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.authentication.WebAuthenticationDetails;

public class MariferAuthenticationDetails extends WebAuthenticationDetails {

	private static final long serialVersionUID = 4997338626496545927L;
	
	private final String method;

	public MariferAuthenticationDetails(HttpServletRequest request) {
		super(request);
		method = request.getParameter("method");
	}
	
	public String getMethod() {
		return method;
	}
}
