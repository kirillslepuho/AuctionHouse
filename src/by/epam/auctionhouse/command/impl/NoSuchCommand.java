package by.epam.auctionhouse.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.auctionhouse.command.ICommand;

/**
 * Provides an implementation of the ICommand interface.
 *
 * @author Kirill Slepuho
 * @see ICommand
 */
public class NoSuchCommand implements ICommand{

	private final static String PATH = "pages/admin/500.html";
	
	/**
     * Only redirect to the error page.
     *
     * @param req  the HttpServletRequest object that contains the request the client made of the servlet
     * @param resp the HttpServletResponse object that contains the response the servlet returns to the client
     */
	@Override
	public void execute(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect(PATH);
	}

}
