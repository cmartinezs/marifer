package cl.cmartinezs.marifer.mb;

import java.io.Serializable;
import java.util.Locale;

import javax.faces.context.FacesContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
@Scope("session")
public class LanguageMb implements Serializable {

	private static final long serialVersionUID = -8237470747508561119L;
	
	private Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();

	public Locale getLocale() {
		return locale;
	}
	
	public String getLanguage(){
		return locale.getLanguage();
	}
	
	public void changeLanguage(String language){
		locale = new Locale(language);
		FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
	}

}
