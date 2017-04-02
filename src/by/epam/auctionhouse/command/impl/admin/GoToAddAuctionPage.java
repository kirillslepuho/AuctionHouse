package by.epam.auctionhouse.command.impl.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import by.epam.auctionhouse.bean.Lot;
import by.epam.auctionhouse.command.ICommand;
import by.epam.auctionhouse.service.AdminService;
import by.epam.auctionhouse.service.exception.ServiceException;
import by.epam.auctionhouse.service.factory.ServiceFactory;

public class GoToAddAuctionPage implements ICommand{
	 private static final String PATH = "pages/admin/add_auction.jsp";
	 private final static String LOT_ATTRIBUTE_NAME = "lots";

	    @Override
	    public void execute(HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws ServletException, IOException {
	    	ServiceFactory serviceFactory = ServiceFactory.getInstance();
	        AdminService adminService = serviceFactory.getAdminService();	        
	        try {
	            List<Lot> lotList;
	            lotList = adminService.getLots();
	            

	            httpRequest.setAttribute(LOT_ATTRIBUTE_NAME, lotList);
	            httpRequest.getRequestDispatcher(PATH).forward(httpRequest, httpResponse);
	        } catch (ServiceException exception) {
	            httpResponse.sendError(HttpServletResponse.SC_NOT_FOUND);
	        }
	    }
	
}
