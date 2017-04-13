package by.epam.auctionhouse.command.impl.user;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.auctionhouse.command.ICommand;
import by.epam.auctionhouse.service.AdminService;
import by.epam.auctionhouse.service.ClientService;
import by.epam.auctionhouse.service.exception.ServiceException;
import by.epam.auctionhouse.service.factory.ServiceFactory;
import by.epam.auctionhouse.bean.Bet;
import by.epam.auctionhouse.bean.Lot;;
/**
 * Provides an implementation of the ICommand interface.
 *
 * @author Kirill Slepuho
 * @see ICommand
 */
public class GoToClientPage implements ICommand{
	 private static final String PATH = "pages/client_page.jsp";
	    private static final String userID = "userId";
	    private static final String BETS_ATTRIBUTE_NAME = "bets";
		private static final String LOTS_ATTRIBUTE_NAME = "lots";

		/**
		 * Gets user id from the request parameters and gets users bets,lots and contacts using this id from the ClientService,and sets it as the request attribute,after forward to client_page page.
		 *
		 * @param httpRequest  the HttpServletRequest object that contains the request the client made of the servlet
		 * @param httpResponse the HttpServletResponse object that contains the response the servlet returns to the client
		 * @see ServiceException
		 * @see AdminService
		 */
	    @Override
	    public void execute(HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws ServletException, IOException {

	        String userId;
	        userId = httpRequest.getParameter(userID);

	        ServiceFactory serviceFactory = ServiceFactory.getInstance();
	        ClientService clientService = serviceFactory.getClientService();

	        try {
	            List<Bet> bets;
	            List<Lot> lots;
	            bets = clientService.getUsersBets(userId);
	            lots = clientService.getUsersLots(userId);
	            httpRequest.setAttribute(BETS_ATTRIBUTE_NAME, bets);
	            httpRequest.setAttribute(LOTS_ATTRIBUTE_NAME, lots);
	            httpRequest.getRequestDispatcher(PATH).forward(httpRequest, httpResponse);
	        } catch (ServiceException exception) {
	            httpResponse.sendError(HttpServletResponse.SC_NOT_FOUND);
	        }
	    }
}
