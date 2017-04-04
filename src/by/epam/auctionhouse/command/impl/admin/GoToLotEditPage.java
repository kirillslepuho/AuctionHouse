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

public class GoToLotEditPage implements ICommand{
	 private static final String PATH = "pages/admin/edit_lot.jsp";
	    private static final String ID = "id";
	    private static final String LOT_ATTRIBUTE_NAME = "lot";

	    @Override
	    public void execute(HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws ServletException, IOException {

	        String lotId;
	        lotId = httpRequest.getParameter(ID);

	        ServiceFactory serviceFactory = ServiceFactory.getInstance();
	        AdminService adminService = serviceFactory.getAdminService();

	        try {
	         Lot lot;
	         lot = adminService.getLotById(lotId);
	           

	            httpRequest.setAttribute(LOT_ATTRIBUTE_NAME, lot);
	            httpRequest.setAttribute(ID, lotId);
	            httpRequest.getRequestDispatcher(PATH).forward(httpRequest, httpResponse);
	        } catch (ServiceException exception) {
	            httpResponse.sendError(HttpServletResponse.SC_NOT_FOUND);
	        }
	    }
	
}
