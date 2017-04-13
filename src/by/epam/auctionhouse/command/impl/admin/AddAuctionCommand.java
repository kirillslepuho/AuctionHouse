package by.epam.auctionhouse.command.impl.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import by.epam.auctionhouse.bean.Auction;
import by.epam.auctionhouse.bean.Lot;
import by.epam.auctionhouse.command.ICommand;
import by.epam.auctionhouse.service.AdminService;
import by.epam.auctionhouse.service.exception.ServiceException;
import by.epam.auctionhouse.service.factory.ServiceFactory;
import by.epam.auctionhouse.service.util.Util;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
/**
 * Provides an implementation of the ICommand interface.
 *
 * @author Kirill Slepuho
 * @see ICommand
 */
public class AddAuctionCommand implements ICommand{
	private static final String LOT_ID_PARAMETER = "lot_id";
	private static final String PLACE_PARAMETER = "place";
	private static final String BEGIN_DATE_PARAMETER = "begin_date";
	private static final String EXPIRATION_DATE_PARAMETER = "expiration_date";
	private static final String TIME_PARAMETER = "time";
	private static final String TYPE_PARAMETER = "type";
	private static final String IS_ACTIVE__PARAMETER = "is_active";

	private static final String PATH = "/AuctionHouse/Controller?command=go_to_auctions_page";

	private final static String ERROR_MESSAGE_JSON = "errorMessage";
	private final static String REDIRECT_JSON = "redirect";

	private final static Logger logger = LogManager.getLogger(AddAuctionCommand.class.getName());

	/**
	 * Creates a new Auction object from request parameters and passes it to the addAuction method of the AdminService.
	 *
	 * @param httpRequest  the HttpServletRequest object that contains the request the client made of the servlet
	 * @param httpResponse the HttpServletResponse object that contains the response the servlet returns to the client
	 * @see ServiceException
	 * @see NumberFormatException
	 * @see AdminService
	 */
	@Override
	public void execute(HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws ServletException, IOException {
		try {
			ServiceFactory serviceFactory = ServiceFactory.getInstance();
			AdminService adminService = serviceFactory.getAdminService();


			Auction auction = new Auction();
			Lot lot = adminService.getLotById(httpRequest.getParameter(LOT_ID_PARAMETER));
			auction.setLot(lot);
			auction.setBeginDate(httpRequest.getParameter(BEGIN_DATE_PARAMETER));
			auction.setExpirationDate(httpRequest.getParameter(EXPIRATION_DATE_PARAMETER));
			auction.setIsActive(Util.checkBoolean(httpRequest.getParameter(IS_ACTIVE__PARAMETER)));
			auction.setPlace(httpRequest.getParameter(PLACE_PARAMETER));
			auction.setTime(httpRequest.getParameter(TIME_PARAMETER));
			auction.setType(httpRequest.getParameter(TYPE_PARAMETER));

			adminService.addAuction(auction);

			JSONObject jsonObject = new JSONObject();
			jsonObject.put(REDIRECT_JSON, PATH);
			String jsonString = jsonObject.toString();
			httpResponse.getWriter().write(jsonString);

		} catch (ServiceException e) {
			logger.warn(e);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put(ERROR_MESSAGE_JSON, e.getMessage());
			String jsonString = jsonObject.toString();
			httpResponse.getWriter().write(jsonString);
		} catch (NumberFormatException e) {
			logger.warn(e);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put(ERROR_MESSAGE_JSON, "Wrong data in rounds");
			String jsonString = jsonObject.toString();
			httpResponse.getWriter().write(jsonString);
		} 
	}
}
