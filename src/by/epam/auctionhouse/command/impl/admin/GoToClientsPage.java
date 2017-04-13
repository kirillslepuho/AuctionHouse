package by.epam.auctionhouse.command.impl.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.auctionhouse.bean.User;
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
public class GoToClientsPage implements ICommand{
	private final static String CLIENT_ATTRIBUTE_NAME = "clients";
	private static final String PATH = "pages/admin/clients.jsp";
	private static final Logger logger = LogManager.getLogger(GoToClientsPage.class.getName());
	
	/**
	 * Gets clients list from the AdminService,and sets it as the request attribute,after forward to clients page.
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

		try {
			List<User> clientList;
			clientList = adminService.getUsers();
			httpRequest.setAttribute(CLIENT_ATTRIBUTE_NAME, clientList);
			httpRequest.getRequestDispatcher(PATH).forward(httpRequest, httpResponse);
		} catch (ServiceException exception) {
			httpResponse.sendError(HttpServletResponse.SC_NOT_FOUND);
			logger.warn(exception);
		}
	}
}