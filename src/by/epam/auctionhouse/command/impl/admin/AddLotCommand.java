package by.epam.auctionhouse.command.impl.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.auctionhouse.bean.Lot;
import by.epam.auctionhouse.command.ICommand;
import by.epam.auctionhouse.service.AdminService;
import by.epam.auctionhouse.service.exception.ServiceException;
import by.epam.auctionhouse.service.factory.ServiceFactory;
import by.epam.auctionhouse.service.util.Util;

import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.apache.logging.log4j.LogManager;

public class AddLotCommand implements ICommand{

	private static final String TYPE_PARAMETER = "lot_type";
	private static final String NAME_PARAMETER = "lot_name";
	private static final String CURRENT_PRICE_PARAMETER = "current_price";
	private static final String DESCRIPTION_PARAMETER = "description";
	private static final String IMAGE_PARAMETER = "image";
	private static final String IS_CLIENT = "is_client";
	private static final String CLIENT_ID = "client_id";
	
	private static final String PATH = "/AuctionHouse/Controller?command=go_to_admin_page";

	private final static String ERROR_MESSAGE_JSON = "errorMessage";
	private final static String REDIRECT_JSON = "redirect";
	
	private static final Logger logger = LogManager.getLogger(AddLotCommand.class.getName());

	@Override
	public void execute(HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws IOException, ServletException {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		AdminService adminService = serviceFactory.getAdminService();
		
		try{

			Lot lot = new Lot();
			lot.setType(httpRequest.getParameter(TYPE_PARAMETER));
			lot.setName(httpRequest.getParameter(NAME_PARAMETER));
			lot.setDescriprion(httpRequest.getParameter(DESCRIPTION_PARAMETER));
			lot.setImage(httpRequest.getParameter(IMAGE_PARAMETER));
			lot.setCurrentPrice(Integer.parseInt(httpRequest.getParameter(CURRENT_PRICE_PARAMETER)));
			lot.setClients(Util.checkBoolean(httpRequest.getParameter(IS_CLIENT)));
			lot.setClientOwer(httpRequest.getParameter(CLIENT_ID));
			adminService.addLot(lot);

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
		}


	}

}
