package by.epam.auctionhouse.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ICommand {
  public void execute(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException;
}
