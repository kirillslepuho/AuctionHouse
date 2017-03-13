package by.epam.auctionhouse.command.impl.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.auctionhouse.command.ICommand;

public class GoToAddAuctionPage implements ICommand{
	 private static final String PATH = "pages/admin/add_auction.jsp";

	    @Override
	    public void execute(HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws ServletException, IOException {
	        httpRequest.getRequestDispatcher(PATH).forward(httpRequest, httpResponse);
	    }
	
}
