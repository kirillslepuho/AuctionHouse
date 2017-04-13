package by.epam.auctionhouse.command.impl.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.auctionhouse.bean.Auction;
import by.epam.auctionhouse.bean.User;
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
public class GoToAuctionPage implements ICommand{
	private static final String PATH = "pages/auction_info.jsp";
	private static final String ID = "id";
	private static final String AUCTION_ATTRIBUTE_NAME = "auction";
	private static final String WIN_USER_ATTRIBUTE_NAME = "winner";

	/**
	 * Gets auction id from the request parameters and gets auction using this id from the ClientService,and sets it as the request attribute,after forward to auction_info page.
	 *
	 * @param httpRequest  the HttpServletRequest object that contains the request the client made of the servlet
	 * @param httpResponse the HttpServletResponse object that contains the response the servlet returns to the client
	 * @see ServiceException
	 * @see AdminService
	 */
	@Override
	public void execute(HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws ServletException, IOException {

		String auctionId;
		auctionId = httpRequest.getParameter(ID);

		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		ClientService clientService = serviceFactory.getClientService();
		AdminService adminService = serviceFactory.getAdminService();

		try {
			Auction auction;
			User winUser;
			auction = clientService.getAuction(auctionId);
			winUser = adminService.getAuctionWinner(auctionId);
			httpRequest.setAttribute(AUCTION_ATTRIBUTE_NAME, auction);
			httpRequest.setAttribute(WIN_USER_ATTRIBUTE_NAME, winUser);
			httpRequest.setAttribute(ID, auctionId);
			httpRequest.getRequestDispatcher(PATH).forward(httpRequest, httpResponse);
		} catch (ServiceException exception) {
			httpResponse.sendError(HttpServletResponse.SC_NOT_FOUND);
		}
	}
}
