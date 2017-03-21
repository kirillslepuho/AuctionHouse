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

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class AddLotCommand implements ICommand{

	private static final Logger logger = LogManager.getLogger(AddLotCommand.class.getName());
	
	private static final String TYPE_PARAMETER = "lot_type";
	private static final String NAME_PARAMETER = "lot_name";
	private static final String CURRENT_PRICE_PARAMETER = "current_price";
	private static final String DESCRIPTION_PARAMETER = "description";
	private static final String IMAGE_PARAMETER = "image";
	private static final String IS_CLIENT = "is_client";
	private static final String PATH = "pages/admin/add_lot.jsp";
	
    private static final String TRUE = "true";
    private static final String FALSE = "false";
    
    

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
			
            String setPremium = httpRequest.getParameter(IS_CLIENT);
		        boolean isClient;

		        switch (setPremium) {
		            case TRUE:
		                isClient = true;
		                break;
		            case FALSE:
		                isClient = false;
		                break;
		            default:
		                httpResponse.sendError(HttpServletResponse.SC_BAD_REQUEST);
		                return;
		        }
		        
		        lot.setClients(isClient);

			adminService.addLot(lot);
		    httpResponse.sendRedirect(PATH);
		} catch (ServiceException e) {
			logger.error("Error adding lot", e);
            httpResponse.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}


	}

}
