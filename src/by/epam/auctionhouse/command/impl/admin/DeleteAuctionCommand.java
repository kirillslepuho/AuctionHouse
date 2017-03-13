package by.epam.auctionhouse.command.impl.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.auctionhouse.command.ICommand;
import by.epam.auctionhouse.service.AdminService;
import by.epam.auctionhouse.service.exception.ServiceException;
import by.epam.auctionhouse.service.factory.ServiceFactory;


public class DeleteAuctionCommand implements ICommand{

	 private static final Logger logger = LogManager.getLogger(DeleteAuctionCommand.class.getName());
	
	private static final String PATH = "/AuctionHouse/Controller?command=go_to_admin_page";
	private static final String ID="id";
	
	@Override
	public void execute(HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws ServletException, IOException {
		String deleteId;
		deleteId = httpRequest.getParameter(ID);

		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		AdminService adminService = serviceFactory.getAdminService();

		try {
			adminService.deleteAuction(deleteId);
			httpResponse.sendRedirect(PATH);
		} catch (ServiceException exception) {
			logger.error("Error deleting file", exception);
            httpResponse.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}

}
