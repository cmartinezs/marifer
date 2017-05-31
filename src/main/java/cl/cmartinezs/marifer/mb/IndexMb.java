package cl.cmartinezs.marifer.mb;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "indexMb")
@RequestScoped
public class IndexMb {

	private String message = "HOLA MUNDO! desde un ManagedBean";

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
