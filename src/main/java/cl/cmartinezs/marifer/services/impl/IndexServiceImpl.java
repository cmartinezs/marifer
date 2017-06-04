package cl.cmartinezs.marifer.services.impl;

import cl.cmartinezs.marifer.services.IndexService;

public class IndexServiceImpl implements IndexService {

	@Override
	public String getMessage() {
		return "HOLA MUNDO! desde un servicio de Spring Framework";
	}

}
