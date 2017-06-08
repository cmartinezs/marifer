package cl.cmartinezs.marifer.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.cmartinezs.marifer.repository.dao.MessageDAO;
import cl.cmartinezs.marifer.repository.model.Message;
import cl.cmartinezs.marifer.services.IndexService;

@Service("indexService")
public class IndexServiceImpl implements IndexService {
	
	@Autowired
	private MessageDAO messageDAO;

	@Override
	public String getMessage() {
		
		Message message = messageDAO.getById(Integer.valueOf(1));
		return message != null ? message.getMessage() : "HOLA MUNDO! desde un servicio de Spring Framework";
	}
}
