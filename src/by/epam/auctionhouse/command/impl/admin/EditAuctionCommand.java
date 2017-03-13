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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EditAuctionCommand implements ICommand{

	private static final String LOT_ID_PARAMETER = "lot_id";
	private static final String PLACE_PARAMETER = "place";
	private static final String BEGIN_DATE_PARAMETER = "begin_date";
	private static final String EXPIRATION_DATE_PARAMETER = "expiration_date";
	private static final String TIME_PARAMETER = "time";
	private static final String TYPE_PARAMETER = "type";
	private static final String IS_ACTIVE__PARAMETER = "is_active";
	private static final String ROUNDS_PARAMETER = "rounds";

	 private static final Logger logger = LogManager.getLogger(EditAuctionCommand.class.getName());
	
    private static final String PATH = "/AuctionHouse/Controller?command=go_to_admin_page";

    private final static String ERROR_MESSAGE_JSON = "errorMessage";
    private final static  String REDIRECT_JSON = "redirect";
    private static final String CHANGE_PRODUCT_ID = "change_id";

    public void execute(HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws ServletException, IOException {
        try {
            String changeId;
            changeId = httpRequest.getParameter(CHANGE_PRODUCT_ID);

            ServiceFactory serviceFactory = ServiceFactory.getInstance();
			AdminService adminService = serviceFactory.getAdminService();

			Auction auction = new Auction();
			Lot lot = adminService.getLotById(httpRequest.getParameter(LOT_ID_PARAMETER));
			auction.setLot(lot);
			auction.setBeginDate(httpRequest.getParameter(BEGIN_DATE_PARAMETER));
			auction.setExpirationDate(httpRequest.getParameter(EXPIRATION_DATE_PARAMETER));			
			if(httpRequest.getParameter(IS_ACTIVE__PARAMETER).equals("Yes")){
				auction.setIsActive(true);
			}else if(httpRequest.getParameter(IS_ACTIVE__PARAMETER).equals("No")){
				auction.setIsActive(false);
			}
            auction.setPlace(httpRequest.getParameter(PLACE_PARAMETER));
            auction.setRounds(Integer.parseInt((httpRequest.getParameter(ROUNDS_PARAMETER))));
            auction.setTime(httpRequest.getParameter(TIME_PARAMETER));
            auction.setType(httpRequest.getParameter(TYPE_PARAMETER));

            adminService.editAuction(auction, changeId);

            httpResponse.sendRedirect(PATH);

        } catch (ServiceException exception) {
        	logger.error("Error deleting file", exception);
        } catch (NumberFormatException exception) {
            
        }
    }
}
