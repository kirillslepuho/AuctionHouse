package by.epam.auctionhouse.command.impl.user;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import by.epam.auctionhouse.bean.User;
import by.epam.auctionhouse.command.ICommand;
import by.epam.auctionhouse.controller.JspPageName;
import by.epam.auctionhouse.service.ClientService;
import by.epam.auctionhouse.service.exception.ServiceException;
import by.epam.auctionhouse.service.factory.ServiceFactory;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class SignInCommand implements ICommand{

	private final static String USER_LOGIN = "login";
	private final static String USER_PASSWORD = "password";

	private final static String SESSION_USER_ATTRIBUTE = "user";
	private final static String SESSION_ADMIN_ATTRIBUTE = "admin";

	private final static String LOG_MESSAGE = "User with e-mail : %s sign in";
	private final static Logger logger = LogManager.getLogger("traceLogger");


	@Override
	public void execute(HttpServletRequest request,HttpServletResponse reponse) throws ServletException, IOException {

		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		ClientService clientService = serviceFactory.getClientService();

		String email = request.getParameter(USER_LOGIN);
		String password = request.getParameter(USER_PASSWORD);		
		HttpSession session = request.getSession(true);


		try{
			User user = clientService.signIn(email,password);
			session.setAttribute(SESSION_USER_ATTRIBUTE, user);
			session.setAttribute(SESSION_ADMIN_ATTRIBUTE, user.isAdmin());

			String logMessage;
			logMessage = String.format(LOG_MESSAGE, user.getEmail());
			logger.trace(logMessage);

			request.getRequestDispatcher(JspPageName.WELLCOME_PAGE).forward(request, reponse);

		}catch(ServiceException e){
			//request.getRequestDispatcher(JspPageName.ERROR_PAGE).forward(request, reponse);

		}

	}
}
