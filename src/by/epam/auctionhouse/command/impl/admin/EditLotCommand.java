package by.epam.auctionhouse.command.impl.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;

import by.epam.auctionhouse.bean.Lot;
import by.epam.auctionhouse.command.ICommand;
import by.epam.auctionhouse.service.AdminService;
import by.epam.auctionhouse.service.exception.ServiceException;
import by.epam.auctionhouse.service.factory.ServiceFactory;

public class EditLotCommand implements ICommand{

	private static final String TYPE_PARAMETER = "lot_type";
	private static final String NAME_PARAMETER = "lot_name";
	private static final String CURRENT_PRICE_PARAMETER = "current_price";
	private static final String DESCRIPTION_PARAMETER = "description";
	private static final String IMAGE_PARAMETER = "image";
	private static final String BLITZ_BET = "blitz_bet";
	private static final String CHANGE_PRODUCT_ID = "change_id";
	
	private static final String PATH = "/AuctionHouse/Controller?command=go_to_lots_page";
	
	private final static String ERROR_MESSAGE_JSON = "errorMessage";
	private final static  String REDIRECT_JSON = "redirect";
	
	private static final Logger logger = LogManager.getLogger(EditLotCommand.class.getName());
	
	public void execute(HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws ServletException, IOException {
		
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		AdminService adminService = serviceFactory.getAdminService();
		
		try {
			String changeId;
			changeId = httpRequest.getParameter(CHANGE_PRODUCT_ID);
			
			Lot lot = new Lot();
			lot.setName(httpRequest.getParameter(NAME_PARAMETER));
			lot.setBlitzBet(httpRequest.getParameter(BLITZ_BET));
			lot.setCurrentPrice(Integer.parseInt(httpRequest.getParameter(CURRENT_PRICE_PARAMETER)));
			lot.setDescriprion(httpRequest.getParameter(DESCRIPTION_PARAMETER));
			lot.setImage(IMAGE_PARAMETER);
			lot.setType(httpRequest.getParameter(TYPE_PARAMETER));
				
			adminService.editLot(lot, changeId);
			
			JSONObject jsonObject = new JSONObject();
			jsonObject.put(REDIRECT_JSON, PATH);
			String jsonString = jsonObject.toString();
			httpResponse.getWriter().write(jsonString);

		} catch (ServiceException e) {
			logger.error("Error editing auction", e);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put(ERROR_MESSAGE_JSON, e.getMessage());
			String jsonString = jsonObject.toString();
			httpResponse.getWriter().write(jsonString);
		} catch (NumberFormatException exception) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put(ERROR_MESSAGE_JSON, "Wrong rounds data");
			String jsonString = jsonObject.toString();
			httpResponse.getWriter().write(jsonString);
		}
	}
}
