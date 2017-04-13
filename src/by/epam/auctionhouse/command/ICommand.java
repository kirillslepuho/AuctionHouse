package by.epam.auctionhouse.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The ICommand interface should be implemented by any class whose instances are intended to be executed by a servlet. The class must define a method called execute.
 *
 * @author Kirill Slepuho
 */
public interface ICommand {
	/**
     * Causes the ICommand to perform its encapsulated behavior.
     *
     * @param req  the HttpServletRequest object that contains the request the client made of the servlet
     * @param resp the HttpServletResponse object that contains the response the servlet returns to the client
     */
  public void execute(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException;
}
