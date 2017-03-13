package by.epam.auctionhouse.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.auctionhouse.command.ICommand;


public class NoSuchCommand implements ICommand{

	private final static String REFERRER = "referer";
	
	@Override
	public void execute(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect(request.getHeader(REFERRER));
	}

}
