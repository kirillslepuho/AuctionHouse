package by.epam.auctionhouse.command.impl.user;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import by.epam.auctionhouse.bean.User;
import by.epam.auctionhouse.command.ICommand;
import by.epam.auctionhouse.service.ClientService;
import by.epam.auctionhouse.service.exception.ServiceException;
import by.epam.auctionhouse.service.factory.ServiceFactory;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.apache.logging.log4j.LogManager;

import static by.epam.auctionhouse.service.util.error.MessageManager.*;
/**
 * Provides an implementation of the ICommand interface.
 *
 * @author Kirill Slepuho
 * @see ICommand
 */
public class SignInCommand implements ICommand{

	private final static String USER_LOGIN = "login";
	private final static String USER_PASSWORD = "password";

	private final static String SESSION_USER_ATTRIBUTE = "user";
	private final static String SESSION_ADMIN_ATTRIBUTE = "admin";

	private final static String ERROR_MESSAGE_JSON = "errorMessage";
	private final static String REDIRECT_JSON = "redirect";

	private final static String LOG_MESSAGE = "User with e-mail : %s sign in";
	private final static Logger logger = LogManager.getLogger("errorLogger");

	private final static String PATH = "/AuctionHouse/Controller?command=go_to_main_page";

	/**
     * Gets email, passwword from the request parameters and gets user from signIn method in the ClientService, and sets it as the session attribute.
     *
     * @param httpRequest  the HttpServletRequest object that contains the request the client made of the servlet
	 * @param httpResponse the HttpServletResponse object that contains the response the servlet returns to the client
     * @see ServiceException
     * @see ClientService
     */
	@Override
	public void execute(HttpServletRequest httpRequest,HttpServletResponse httpResponse) throws ServletException, IOException {

		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		ClientService clientService = serviceFactory.getClientService();

		String email = httpRequest.getParameter(USER_LOGIN);
		String password = httpRequest.getParameter(USER_PASSWORD);		
		HttpSession session = httpRequest.getSession(true);


		try{
			User user = clientService.signIn(email,password);
			session.setAttribute(SESSION_USER_ATTRIBUTE, user);
			session.setAttribute(SESSION_ADMIN_ATTRIBUTE, user.isAdmin());

			String logMessage;
			logMessage = String.format(LOG_MESSAGE, user.getEmail());
			logger.trace(logMessage);

			JSONObject jsonObject = new JSONObject();
			jsonObject.put(REDIRECT_JSON, PATH);
			String jsonString = jsonObject.toString();
			httpResponse.getWriter().write(jsonString);
		}catch(ServiceException e){
			logger.warn(e);
			JSONObject jsonObject = new JSONObject();
			String errorMessage = getErrorMessage(httpRequest, e.getErrorKey());
			jsonObject.put(ERROR_MESSAGE_JSON, errorMessage);
			String jsonString = jsonObject.toString();
			httpResponse.getWriter().write(jsonString);
		}

	}
}
