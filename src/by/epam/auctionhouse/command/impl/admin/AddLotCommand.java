package by.epam.auctionhouse.command.impl.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.auctionhouse.bean.Lot;
import by.epam.auctionhouse.command.ICommand;
import by.epam.auctionhouse.service.AdminService;
import by.epam.auctionhouse.service.exception.ServiceException;
import by.epam.auctionhouse.service.factory.ServiceFactory;

public class AddLotCommand implements ICommand{

	private static final String TYPE_PARAMETER = "lot_type";
	private static final String NAME_PARAMETER = "lot_name";
	private static final String CURRENT_PRICE_PARAMETER = "current_price";
	private static final String DESCRIPTION_PARAMETER = "description";
	private static final String IMAGE_PARAMETER = "image";
	private static final String PATH = "pages/admin/add_lot.jsp";

	@Override
	public void execute(HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws IOException, ServletException {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		AdminService adminService = serviceFactory.getAdminService();



		try{

			Lot lot = new Lot();
			lot.setType(httpRequest.getParameter(TYPE_PARAMETER));
			lot.setName(httpRequest.getParameter(NAME_PARAMETER));
			lot.setDescriprion(httpRequest.getParameter(DESCRIPTION_PARAMETER));
			lot.setImage(httpRequest.getParameter(IMAGE_PARAMETER));
			lot.setCurrentPrice(Integer.parseInt(httpRequest.getParameter(CURRENT_PRICE_PARAMETER)));

			adminService.addLot(lot);
		    httpResponse.sendRedirect(PATH);
		} catch (ServiceException e) {

		}


	}

}
