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
import by.epam.auctionhouse.service.util.Util;
/**
 * Provides an implementation of the ICommand interface.
 *
 * @author Kirill Slepuho
 * @see ICommand
 */
public class ChangeUserStatusCommand implements ICommand{


	private static final String USER_ID_PARAMETER = "user_id";
	private static final String STATUS_PARAMETER = "user_block";

	private final static String REFERRER = "referer";
	private static final Logger logger = LogManager.getLogger(ChangeUserStatusCommand.class.getName());

	/**
	 * Gets user status and id from the request parameters and passes them to the setUserBlockStatus method in the AdminService.
	 *
	 * @param httpRequest  the HttpServletRequest object that contains the request the client made of the servlet
	 * @param httpResponse the HttpServletResponse object that contains the response the servlet returns to the client
	 * @see ServiceException
	 * @see AdminService
	 */
	@Override
	public void execute(HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws IOException, ServletException {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		AdminService adminService = serviceFactory.getAdminService();

		String userId = httpRequest.getParameter(USER_ID_PARAMETER);
		String status = httpRequest.getParameter(STATUS_PARAMETER);

		try{
			adminService.setUserBlockStatus(userId, Util.checkBoolean(status));
			httpResponse.sendRedirect(httpRequest.getHeader(REFERRER));

		} catch (ServiceException e) {
			logger.warn(e);
			httpResponse.sendRedirect(httpRequest.getHeader(REFERRER));
		}


	}

}
