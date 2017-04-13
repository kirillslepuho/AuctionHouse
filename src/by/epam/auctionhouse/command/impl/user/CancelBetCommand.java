package by.epam.auctionhouse.command.impl.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.auctionhouse.bean.Auction;
import by.epam.auctionhouse.bean.Lot;
import by.epam.auctionhouse.command.ICommand;
import by.epam.auctionhouse.command.impl.admin.BlockLotCommand;
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
public class CancelBetCommand implements ICommand{

	private static final Logger logger = LogManager.getLogger(BlockLotCommand.class.getName());

	private final static String REFERRER = "referer";
	private static final String AUCTION_ID="auctionId";
	private static final String CLIENT_ID="clientId";
	private static final String BET="bet";

	/**
     * Gets client id, auction id and bet from the request parameters and passes them to the cancellationBet method in the AdminService, redirect to referer.
     *
     * @param httpRequest  the HttpServletRequest object that contains the request the client made of the servlet
	 * @param httpResponse the HttpServletResponse object that contains the response the servlet returns to the client
     * @see ServiceException
     * @see ClientService
     */
	@Override
	public void execute(HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws ServletException, IOException {
		String auctionId;
		String clientId;
		String bet;
		auctionId = httpRequest.getParameter(AUCTION_ID);
		clientId = httpRequest.getParameter(CLIENT_ID);
		bet = httpRequest.getParameter(BET);

		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		ClientService clientService = serviceFactory.getClientService();

		try {
			Auction auction  = null;
			Lot lot = null;
			auction = clientService.getAuction(auctionId);
			lot = auction.getLot();
			clientService.cancellationBet(clientId, auctionId, bet, lot);
			httpResponse.sendRedirect(httpRequest.getHeader(REFERRER));
		} catch (ServiceException exception) {
			logger.error("Error deleting bet", exception);
			httpResponse.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}
}
