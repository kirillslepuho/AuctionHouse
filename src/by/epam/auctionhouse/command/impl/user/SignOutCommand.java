package by.epam.auctionhouse.command.impl.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epam.auctionhouse.command.ICommand;
import by.epam.auctionhouse.service.ClientService;
import by.epam.auctionhouse.service.exception.ServiceException;
import by.epam.auctionhouse.service.factory.ServiceFactory;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
/**
 * Provides an implementation of the ICommand interface.
 *
 * @author Kirill Slepuho
 * @see ICommand
 */
public class SignOutCommand implements ICommand{
	private final static String PATH = "/AuctionHouse/Controller?command=go_to_main_page";

	private final static Logger logger = LogManager.getLogger("errorLogger");
	
	/**
     * Gets HttpSession from the request parameters and passes them to the signOut method in the ClientService, redirect to main page.
     *
     * @param httpRequest  the HttpServletRequest object that contains the request the client made of the servlet
	 * @param httpResponse the HttpServletResponse object that contains the response the servlet returns to the client
     * @see ServiceException
     * @see ClientService
     */
	@Override
	public void execute(HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws ServletException, IOException {

		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		ClientService clientService = serviceFactory.getClientService();

		try {
			HttpSession httpSession = httpRequest.getSession(false);
			clientService.signOut(httpSession);

			httpResponse.sendRedirect(PATH);

		} catch (ServiceException exception) {
          logger.error("Error while logging out");
		}
	}
}
