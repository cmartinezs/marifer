package cl.cmartinezs.marifer.exceptions.handle;

import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerFactory;

public class MariferExceptionHandleFactory extends ExceptionHandlerFactory {
	private ExceptionHandlerFactory parent;

	public MariferExceptionHandleFactory(ExceptionHandlerFactory parent) {
		this.parent = parent;
	}

	@Override
	public ExceptionHandler getExceptionHandler() {
		return new MariferExceptionHandle(parent.getExceptionHandler());
	}

}
