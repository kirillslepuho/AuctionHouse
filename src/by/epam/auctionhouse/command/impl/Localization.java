package by.epam.auctionhouse.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.auctionhouse.command.ICommand;

public class Localization implements ICommand{

	private static final String LOCAL_ATTRIBUTE = "local";
    private final static String REFERRER = "referer";
	
	@Override
	public void execute(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		
		String local;
        local = request.getParameter(LOCAL_ATTRIBUTE);
        
		request.getSession(true).setAttribute(LOCAL_ATTRIBUTE, local);
		response.sendRedirect(request.getHeader(REFERRER));
	}

}
