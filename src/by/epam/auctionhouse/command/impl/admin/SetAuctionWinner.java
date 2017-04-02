package by.epam.auctionhouse.command.impl.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.auctionhouse.command.ICommand;
import by.epam.auctionhouse.service.AdminService;
import by.epam.auctionhouse.service.exception.ServiceException;
import by.epam.auctionhouse.service.factory.ServiceFactory;

public class SetAuctionWinner implements ICommand{

	 private static final Logger logger = LogManager.getLogger(SetAuctionWinner.class.getName());
	
	 private final static String REFERRER = "referer";
	 private static final String CLIENT="client";
	 private static final String AUCTION="auction";
	 private static final String BET="bet";
	
	@Override
	public void execute(HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws ServletException, IOException {
		String clientId;
		String auctionId;
		String bet;
		clientId = httpRequest.getParameter(CLIENT);
		auctionId = httpRequest.getParameter(AUCTION);
		bet = httpRequest.getParameter(BET);

		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		AdminService adminService = serviceFactory.getAdminService();

		try {
			adminService.setAuctionWinner(auctionId, clientId, bet);
			httpResponse.sendRedirect(httpRequest.getHeader(REFERRER));
		} catch (ServiceException exception) {
			logger.error("Error setting winner", exception);
           httpResponse.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}
}
