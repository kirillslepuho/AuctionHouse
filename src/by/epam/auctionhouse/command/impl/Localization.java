package by.epam.auctionhouse.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.auctionhouse.command.ICommand;
import by.epam.auctionhouse.service.ClientService;
import by.epam.auctionhouse.service.exception.ServiceException;
import by.epam.auctionhouse.service.factory.ServiceFactory;

/**
 * Provides an implementation of the ICommand interface.
 *
 * @author Kirill Slepuho
 * @see ICommand
 */
public class Localization implements ICommand{

	private static final String LOCAL_ATTRIBUTE = "local";
    private final static String REFERRER = "referer";
    private static final Logger logger = LogManager.getLogger(Localization.class.getName());
	
    /**
     * Gets locale from the request parameter and sets it as the session attribute.
     *
     * @param req  the HttpServletRequest object that contains the request the client made of the servlet
     * @param resp the HttpServletResponse object that contains the response the servlet returns to the clien
     */
	@Override
	public void execute(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		String local;
        local = request.getParameter(LOCAL_ATTRIBUTE);
        
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        ClientService clientService = serviceFactory.getClientService();
        
        try {
			clientService.setLocal(local);
			request.getSession(true).setAttribute(LOCAL_ATTRIBUTE, local);
		} catch (ServiceException e) {
          logger.error(e);
		}
		
		response.sendRedirect(request.getHeader(REFERRER));
	}

}
