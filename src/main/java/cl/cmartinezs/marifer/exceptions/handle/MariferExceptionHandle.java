package cl.cmartinezs.marifer.exceptions.handle;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.FacesException;
import javax.faces.application.ViewExpiredException;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.transaction.CannotCreateTransactionException;

public class MariferExceptionHandle extends ExceptionHandlerWrapper {

	private static final Logger log = Logger.getLogger(MariferExceptionHandle.class.getCanonicalName());
	private ExceptionHandler wrapped;

	MariferExceptionHandle(ExceptionHandler exception) {
		this.wrapped = exception;
	}

	@Override
	public ExceptionHandler getWrapped() {
		return wrapped;
	}

	@Override
	public void handle() throws FacesException {

		final Iterator<ExceptionQueuedEvent> i = getUnhandledExceptionQueuedEvents().iterator();
		final FacesContext fc = FacesContext.getCurrentInstance();
		
		if(fc == null){
			getWrapped().handle();
			return;
		}
		
		while (i.hasNext()) {
			ExceptionQueuedEvent event = i.next();
			ExceptionQueuedEventContext context = (ExceptionQueuedEventContext) event.getSource();

			Throwable t = context.getException();

			try {
				
				log.log(Level.FINE, "Error capturado", t);

				ExternalContext externalContext = fc.getExternalContext();

				ResourceBundle bundle = ResourceBundle.getBundle("bundle.error-page");

				String errorPage = null;

				if (isATypeException(t, CannotCreateTransactionException.class)) {
					errorPage = bundle.getString("exception.cannot-create-transaction");
				} else if (isATypeException(t, ViewExpiredException.class)) {
					errorPage = bundle.getString("exception.view-expired");
				} else if (isATypeException(t, NoClassDefFoundError.class)) {
					errorPage = bundle.getString("exception.no-class-def-found-error");
				} else if (isATypeException(t, FileNotFoundException.class)) {
					errorPage = bundle.getString("error.404");
				} else {
					errorPage = bundle.getString("error.500");
				}

				RequestDispatcher dispatcher = ((ServletRequest) externalContext.getRequest()).getRequestDispatcher(errorPage);

				dispatcher.forward((ServletRequest) externalContext.getRequest(), (ServletResponse) externalContext.getResponse());

				fc.responseComplete();

			} catch (ServletException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				// remove it from queue
				i.remove();
			}
		}
		// parent handle
		getWrapped().handle();
	}

	private <T extends Throwable> boolean isATypeException(Throwable e, Class<T> type) {
		boolean isCannotCreateTransactionException = false;
		FacesException feTmp = null;
		
		do {
			if(e instanceof FacesException) {
				feTmp = (FacesException) e;
				e = feTmp.getCause();
			} else if (type.isInstance(e)) {
				isCannotCreateTransactionException = true;
				break;
			} else {
				e = e.getCause();
			}

		} while (e != null);

		return isCannotCreateTransactionException;
	}

}
