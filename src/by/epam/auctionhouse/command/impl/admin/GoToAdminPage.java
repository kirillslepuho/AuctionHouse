package by.epam.auctionhouse.command.impl.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.auctionhouse.bean.Auction;
import by.epam.auctionhouse.bean.Lot;
import by.epam.auctionhouse.command.ICommand;
import by.epam.auctionhouse.service.AdminService;
import by.epam.auctionhouse.service.ClientService;
import by.epam.auctionhouse.service.exception.ServiceException;
import by.epam.auctionhouse.service.factory.ServiceFactory;

public class GoToAdminPage implements ICommand{
	
	private final static String AUCTION_ATTRIBUTE_NAME = "auctions";
	private final static String LOT_ATTRIBUTE_NAME = "lots";
    private static final String PATH = "pages/admin/admin_page.jsp";
	@Override
	public void execute(HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws IOException, ServletException {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
        ClientService clientService = serviceFactory.getClientService();
        AdminService adminService = serviceFactory.getAdminService();
         
        try {
            List<Auction> auctionList;
            List<Lot> lotList;
            
            auctionList = clientService.getAuctions();
            lotList = adminService.getLots();
            

            httpRequest.setAttribute(AUCTION_ATTRIBUTE_NAME, auctionList);
            httpRequest.setAttribute(LOT_ATTRIBUTE_NAME, lotList);
            httpRequest.getRequestDispatcher(PATH).forward(httpRequest, httpResponse);
        } catch (ServiceException exception) {
            //httpResponse.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
        
		
	}
    
    

}
