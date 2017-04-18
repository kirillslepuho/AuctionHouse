package by.epam.auctionhouse.command.impl.user;

import static by.epam.auctionhouse.service.util.error.MessageManager.getErrorMessage;

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

import org.json.simple.JSONObject;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

/**
 * Provides an implementation of the ICommand interface.
 *
 * @author Kirill Slepuho
 * @see ICommand
 */
public class RegistrationCommand implements ICommand{

	private final static String ERROR_MESSAGE_JSON = "errorMessage";
	private final static String REDIRECT_JSON = "redirect";

	private final static String USER_EMAIL = "email";
	private final static String USER_PASSWORD = "password";
	private final static String USER_REPEAT_PASSWORD = "repeatPassword";
	private final static String USER_NAME = "name";
	private final static String USER_CARD = "cardnumber";
	private final static String SESSION_USER_ATTRIBUTE = "user";

	private final static String LOG_MESSAGE = "User with e-mail : %s sign in";
	private final static Logger logger = LogManager.getLogger("errorLogger");

	private static final int WRONG_NUMBER_FORMAT_CODE = 9;
	
	private final static String PATH = "/AuctionHouse/Controller?command=go_to_main_page";

	/**
     * Gets parameters users from the request parameters and passes them to the registration method in the ClientService,get user with signIn method, and sets it as the session attribute.

    * @param httpRequest  the HttpServletRequest object that contains the request the client made of the servlet
	 * @param httpResponse the HttpServletResponse object that contains the response the servlet returns to the client
     * @see ServiceException
     * @see ClientService
     */
	@Override
	public void execute(HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws IOException, ServletException {

		String userEmail = httpRequest.getParameter(USER_EMAIL);
		String userPassword = httpRequest.getParameter(USER_PASSWORD);
		String userName = httpRequest.getParameter(USER_NAME);
		String userCardNumber = httpRequest.getParameter(USER_CARD);
		String userRepeatPassword = httpRequest.getParameter(USER_REPEAT_PASSWORD);


		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		ClientService clientService = serviceFactory.getClientService();

		try {
			clientService.registration(userName, userEmail, userPassword,userRepeatPassword, Integer.parseInt(userCardNumber), 0);
			User user = clientService.signIn(userEmail,userPassword);

			HttpSession session = httpRequest.getSession(true);
			session.setAttribute(SESSION_USER_ATTRIBUTE, user);

			String logMessage;
			logMessage = String.format(LOG_MESSAGE, user.getEmail());
			logger.trace(logMessage);

			JSONObject jsonObject = new JSONObject();
			jsonObject.put(REDIRECT_JSON, PATH);
			String jsonString;
			jsonString = jsonObject.toString();
			httpResponse.getWriter().write(jsonString);


		} catch (ServiceException e) {
			logger.warn(e);
			JSONObject jsonObject = new JSONObject();
			String errorMessage = getErrorMessage(httpRequest, e.getErrorKey());
			jsonObject.put(ERROR_MESSAGE_JSON, errorMessage);
			String jsonString = jsonObject.toString();
			httpResponse.getWriter().write(jsonString);
		} catch (NumberFormatException e) {
			JSONObject jsonObject = new JSONObject();
			String errorMessage = getErrorMessage(httpRequest, WRONG_NUMBER_FORMAT_CODE);
			jsonObject.put(ERROR_MESSAGE_JSON, errorMessage);
			String jsonString = jsonObject.toString();
			httpResponse.getWriter().write(jsonString);
		}





	}

}
