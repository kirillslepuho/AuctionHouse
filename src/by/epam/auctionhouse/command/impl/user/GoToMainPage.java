package by.epam.auctionhouse.command.impl.user;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.auctionhouse.bean.Auction;
import by.epam.auctionhouse.command.ICommand;
import by.epam.auctionhouse.service.AdminService;
import by.epam.auctionhouse.service.ClientService;
import by.epam.auctionhouse.service.exception.ServiceException;
import by.epam.auctionhouse.service.factory.ServiceFactory;
/**
 * Provides an implementation of the ICommand interface.
 *
 * @author Kirill Slepuho
 * @see ICommand
 */
public class GoToMainPage implements ICommand{

	private final static String AUCTION_ATTRIBUTE_NAME = "auctions";
    private static final String PATH = "pages/main.jsp";
    
    /**
	 * Gets auctions from the ClientService,and sets it as the request attribute,after forward to main page.
	 *
	 * @param httpRequest  the HttpServletRequest object that contains the request the client made of the servlet
	 * @param httpResponse the HttpServletResponse object that contains the response the servlet returns to the client
	 * @see ServiceException
	 * @see AdminService
	 */
	@Override
	public void execute(HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws IOException, ServletException {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
        ClientService clientService = serviceFactory.getClientService();
         
        try {
            List<Auction> auctionList;
            auctionList = clientService.getAuctions();
            httpRequest.setAttribute(AUCTION_ATTRIBUTE_NAME, auctionList);
            httpRequest.getRequestDispatcher(PATH).forward(httpRequest, httpResponse);
        } catch (ServiceException exception) {
            httpResponse.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
}
}
