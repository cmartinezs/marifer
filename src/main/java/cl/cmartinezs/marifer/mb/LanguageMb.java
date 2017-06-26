package cl.cmartinezs.marifer.mb;

import java.io.Serializable;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
@Scope("session")
public class LanguageMb implements Serializable {

	private static final long serialVersionUID = -8237470747508561119L;
	
	private Locale locale = null;
	
	@PostConstruct
    public void init() {
        locale = FacesContext.getCurrentInstance().getExternalContext().getRequestLocale();
    }

	public Locale getLocale() {
		return locale;
	}
	
	public String getLanguage(){
		return locale.getLanguage();
	}
	
	public void setLanguage(String language){
		locale = new Locale(language);
	}

	public void changeLanguage(AjaxBehaviorEvent vce){
		FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
	}
}
