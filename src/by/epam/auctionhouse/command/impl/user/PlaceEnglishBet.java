package by.epam.auctionhouse.command.impl.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;

import by.epam.auctionhouse.bean.Lot;
import by.epam.auctionhouse.command.ICommand;
import by.epam.auctionhouse.command.impl.admin.AddLotCommand;
import by.epam.auctionhouse.service.AdminService;
import by.epam.auctionhouse.service.ClientService;
import by.epam.auctionhouse.service.exception.ServiceException;
import by.epam.auctionhouse.service.factory.ServiceFactory;
import by.epam.auctionhouse.service.util.Util;

public class PlaceEnglishBet implements ICommand{

	private static final String AUCTION_ID__PARAMETER = "auction_id";
	private static final String CLIENT_ID_PARAMETER = "client_id";
	private static final String CLIENT_BET_PARAMETER = "client_bet";
	private static final String LOT_ID_PARAMETER = "lot_id";
	
	 private final static String REFERRER = "referer";

	private final static String ERROR_MESSAGE_JSON = "errorMessage";
	private final static String REDIRECT_JSON = "redirect";
	
	private static final Logger logger = LogManager.getLogger(AddLotCommand.class.getName());

	@Override
	public void execute(HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws IOException, ServletException {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		AdminService adminService = serviceFactory.getAdminService();
		ClientService clientService = serviceFactory.getClientService();
		
		String clientId = httpRequest.getParameter(CLIENT_ID_PARAMETER);
		String auctionId = httpRequest.getParameter(AUCTION_ID__PARAMETER);
		String bet = httpRequest.getParameter(CLIENT_BET_PARAMETER);
		
		try{
            Lot lot = adminService.getLotById(httpRequest.getParameter(LOT_ID_PARAMETER));
            
            clientService.placeEngishBet(clientId, lot, auctionId, bet);

			JSONObject jsonObject = new JSONObject();
			jsonObject.put(REDIRECT_JSON, httpRequest.getHeader(REFERRER));
			String jsonString = jsonObject.toString();
			httpResponse.getWriter().write(jsonString);
			
		} catch (ServiceException e) {
			logger.warn(e);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put(ERROR_MESSAGE_JSON, e.getMessage());
			String jsonString = jsonObject.toString();
			httpResponse.getWriter().write(jsonString);
		}


	}

}
