package by.epam.auctionhouse.command.impl.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.auctionhouse.bean.Auction;
import by.epam.auctionhouse.command.ICommand;
import by.epam.auctionhouse.service.ClientService;
import by.epam.auctionhouse.service.exception.ServiceException;
import by.epam.auctionhouse.service.factory.ServiceFactory;

public class CancelBetCommand implements ICommand{

	private static final Logger logger = LogManager.getLogger(BlockLotCommand.class.getName());

	private final static String REFERRER = "referer";
	private static final String AUCTION_ID="auctionId";
	private static final String CLIENT_ID="clientId";
	private static final String BET="bet";

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
			String lotId = null;
			auction = clientService.getAuction(auctionId);
			lotId = auction.getLot().getId();
			clientService.cancellationBet(clientId, auctionId, bet, lotId);
			httpResponse.sendRedirect(httpRequest.getHeader(REFERRER));
		} catch (ServiceException exception) {
			logger.error("Error deleting file", exception);
			httpResponse.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}
}
