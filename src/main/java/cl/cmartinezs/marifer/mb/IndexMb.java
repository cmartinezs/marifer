package cl.cmartinezs.marifer.mb;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cl.cmartinezs.marifer.services.IndexService;

@Controller
@Scope("session")
public class IndexMb {

	@Autowired
	private IndexService indexService;
	
	private String message;
	
	@PostConstruct
    public void init() {
		this.message = indexService.getMessage();
    }

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
