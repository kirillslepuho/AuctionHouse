package by.epam.auctionhouse.command.impl.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.auctionhouse.bean.Auction;
import by.epam.auctionhouse.command.ICommand;
import by.epam.auctionhouse.service.ClientService;
import by.epam.auctionhouse.service.exception.ServiceException;
import by.epam.auctionhouse.service.factory.ServiceFactory;

public class GoToAuctionPage implements ICommand{
	 private static final String PATH = "pages/auction_info.jsp";
	    private static final String ID = "id";
	    private static final String AUCTION_ATTRIBUTE_NAME = "auction";

	    @Override
	    public void execute(HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws ServletException, IOException {

	        String auctionId;
	        auctionId = httpRequest.getParameter(ID);

	        ServiceFactory serviceFactory = ServiceFactory.getInstance();
	        ClientService clientService = serviceFactory.getClientService();

	        try {
	            Auction auction;
	            auction = clientService.getAuction(auctionId);

	            httpRequest.setAttribute(AUCTION_ATTRIBUTE_NAME, auction);
	            httpRequest.setAttribute(ID, auctionId);
	            httpRequest.getRequestDispatcher(PATH).forward(httpRequest, httpResponse);
	        } catch (ServiceException exception) {
	            httpResponse.sendError(HttpServletResponse.SC_NOT_FOUND);
	        }
	    }
}