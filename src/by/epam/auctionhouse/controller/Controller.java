package by.epam.auctionhouse.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.auctionhouse.command.CommandHelper;
import by.epam.auctionhouse.command.ICommand;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class Controller extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Logger logger = LogManager.getRootLogger();

	public Controller(){
		super();
	}

	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		doProcess(request,response);  	
	}

	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		doProcess(request,response);  	

	}
	
	private void doProcess(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		
        String commandName = request.getParameter(RequestParameterName.COMMAND_NAME);
        
        ICommand command = CommandHelper.getInstance().getCommand(commandName);
        

		command.execute(request,response);

		
	}

}
