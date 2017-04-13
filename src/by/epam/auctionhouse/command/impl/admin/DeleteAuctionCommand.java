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

/**
 * Provides an implementation of the ICommand interface.
 *
 * @author Kirill Slepuho
 * @see ICommand
 */
public class DeleteAuctionCommand implements ICommand{

	private static final Logger logger = LogManager.getLogger(DeleteAuctionCommand.class.getName());

	private final static String REFERRER = "referer";
	private static final String ID="id";

	/**
	 * Gets auction id from request parameter and passes it to the deleteAuction method of the AdminService.
	 *
	 * @param httpRequest  the HttpServletRequest object that contains the request the client made of the servlet
	 * @param httpResponse the HttpServletResponse object that contains the response the servlet returns to the client
	 * @see ServiceException
	 * @see AdminService
	 */
	@Override
	public void execute(HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws ServletException, IOException {
		String deleteId;
		deleteId = httpRequest.getParameter(ID);

		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		AdminService adminService = serviceFactory.getAdminService();

		try {
			adminService.deleteAuction(deleteId);
			httpResponse.sendRedirect(httpRequest.getHeader(REFERRER));
		} catch (ServiceException exception) {
			logger.error("Error deleting file", exception);
			httpResponse.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}

}
