package cl.cmartinezs.marifer.services.impl;

import org.springframework.stereotype.Service;

import cl.cmartinezs.marifer.services.IndexService;

@Service("indexService")
public class IndexServiceImpl implements IndexService {

	@Override
	public String getMessage() {
		return "HOLA MUNDO! desde un servicio de Spring Framework";
	}
}
