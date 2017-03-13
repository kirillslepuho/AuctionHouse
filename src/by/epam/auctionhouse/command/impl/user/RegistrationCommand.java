package by.epam.auctionhouse.command.impl.user;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epam.auctionhouse.command.ICommand;
import by.epam.auctionhouse.dao.UserDAO;
import by.epam.auctionhouse.dao.factory.DAOFactory;
import by.epam.auctionhouse.service.ClientService;
import by.epam.auctionhouse.service.exception.ServiceException;
import by.epam.auctionhouse.service.factory.ServiceFactory;


public class RegistrationCommand implements ICommand{

	private final static String USER_EMAIL = "email";
	private final static String USER_PASSWORD = "password";
	private final static String USER_REPEAT_PASSWORD = "repeatPassword";
	private final static String USER_NAME = "name";
	private final static String USER_CARD = "cardnumber";
	private final static String SESSION_USER_ATTRIBUTE = "user";
    


	@Override
	public void execute(HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws IOException, ServletException {

		String userEmail = httpRequest.getParameter(USER_EMAIL);
		String userPassword = httpRequest.getParameter(USER_PASSWORD);
		String userName = httpRequest.getParameter(USER_NAME);
		String userCardNumber = httpRequest.getParameter(USER_CARD);
		String userRepeatPassword = httpRequest.getParameter(USER_REPEAT_PASSWORD);
		

		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		ClientService clientService = serviceFactory.getClientService();

		DAOFactory daoFactory = DAOFactory.getInstance();
		UserDAO userDAO = daoFactory.getUserDAO();


		try {
			clientService.registration(userName, userEmail, userPassword,userRepeatPassword, Integer.parseInt(userCardNumber), 0);
			//HttpSession httpSession = httpRequest.getSession(true);
			//httpSession.setAttribute(SESSION_USER_ATTRIBUTE, userEmail);
		} catch (NumberFormatException e) {

		} catch (ServiceException e) {

		}





	}

}
