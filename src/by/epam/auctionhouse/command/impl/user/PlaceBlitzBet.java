package by.epam.auctionhouse.command.impl.user;

import static by.epam.auctionhouse.service.util.error.MessageManager.getErrorMessage;

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
/**
 * Provides an implementation of the ICommand interface.
 *
 * @author Kirill Slepuho
 * @see ICommand
 */
public class PlaceBlitzBet implements ICommand{

	private static final String AUCTION_ID__PARAMETER = "auction_id";
	private static final String CLIENT_ID_PARAMETER = "client_id";
	private static final String BLITZ_BET_PARAMETER = "blitz_bet";
	private static final String LOT_ID_PARAMETER = "lot_id";

	private final static String REFERRER = "referer";

	private final static String ERROR_MESSAGE_JSON = "errorMessage";
	private final static String REDIRECT_JSON = "redirect";
	
	private static final int WRONG_NUMBER_FORMAT_CODE = 9;

	private static final Logger logger = LogManager.getLogger(PlaceBlitzBet.class.getName());
	/**
     * Gets client id, auction id and bet from the request parameters and passes them to the placeBlitzBet method in the ClientService, redirect to referer.
     *
     * @param httpRequest  the HttpServletRequest object that contains the request the client made of the servlet
	 * @param httpResponse the HttpServletResponse object that contains the response the servlet returns to the client
     * @see ServiceException
     * @see ClientService
     */
	@Override
	public void execute(HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws IOException, ServletException {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		AdminService adminService = serviceFactory.getAdminService();
		ClientService clientService = serviceFactory.getClientService();

		String clientId = httpRequest.getParameter(CLIENT_ID_PARAMETER);
		String auctionId = httpRequest.getParameter(AUCTION_ID__PARAMETER);
		String blitzBet = httpRequest.getParameter(BLITZ_BET_PARAMETER);


		try{
			Lot lot = adminService.getLotById(httpRequest.getParameter(LOT_ID_PARAMETER));
			int parseBlitzBet = Integer.parseInt(blitzBet);
			int bet = lot.getCurrentPrice() + parseBlitzBet;

			clientService.placeEngishBet(clientId, lot, auctionId, String.valueOf(bet));

			JSONObject jsonObject = new JSONObject();
			jsonObject.put(REDIRECT_JSON, httpRequest.getHeader(REFERRER));
			String jsonString = jsonObject.toString();
			httpResponse.getWriter().write(jsonString);

		} catch (ServiceException e) {
			logger.warn(e);
			JSONObject jsonObject = new JSONObject();
			String errorMessage = getErrorMessage(httpRequest, e.getErrorKey());
			jsonObject.put(ERROR_MESSAGE_JSON, errorMessage);
			String jsonString = jsonObject.toString();
			httpResponse.getWriter().write(jsonString);
		} catch (NumberFormatException e) {
			JSONObject jsonObject = new JSONObject();
			String errorMessage = getErrorMessage(httpRequest, WRONG_NUMBER_FORMAT_CODE);
			jsonObject.put(ERROR_MESSAGE_JSON, errorMessage);
			String jsonString = jsonObject.toString();
			httpResponse.getWriter().write(jsonString);
		}


	}
}
