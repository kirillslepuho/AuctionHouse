package by.epam.auctionhouse.filter;


import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import by.epam.auctionhouse.bean.User;
import by.epam.auctionhouse.command.CommandName;

import static by.epam.auctionhouse.service.util.JspPageName.*;

public class RoleFilter implements Filter{


	private static final String USER = "user";
	private static final String COMMAND = "command";
	private static final String USER_ID = "userId";
	private static final String PATH = "/AuctionHouse/Controller?command=go_to_main_page";


	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest servletReq, ServletResponse servletResp, FilterChain chain) throws IOException, ServletException {
		setEncoding((HttpServletRequest) servletReq, (HttpServletResponse) servletResp);
		if (isAdminRequest((HttpServletRequest) servletReq)) {
			doAdminFilter(servletReq, servletResp, chain);
		} else if (isUserRequest((HttpServletRequest) servletReq)) {
			doUserFilter(servletReq, servletResp, chain);
		} else {
			chain.doFilter(servletReq, servletResp);
		}
	}

	private void doAdminFilter(ServletRequest servletReq, ServletResponse servletResp, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) servletReq;
		HttpServletResponse resp = (HttpServletResponse) servletResp;
		HttpSession session = req.getSession(false);
		if (isAdmin(session)) {
			chain.doFilter(servletReq, servletResp);
		} else {
			resp.sendRedirect(PATH);
		}
	}

	private void doUserFilter(ServletRequest servletReq, ServletResponse servletResp, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) servletReq;
		HttpServletResponse resp = (HttpServletResponse) servletResp;
		HttpSession session = req.getSession(false);
		if (isUser(session, req)) {
			chain.doFilter(servletReq, servletResp);
		} else {
			resp.sendRedirect(PATH);
		}
	}

	private boolean isAdminRequest(HttpServletRequest req) throws IOException {
		return isAdminPage(req) || isAdminCommand(req);

	}

	private boolean isUserRequest(HttpServletRequest req) throws IOException {
		return isUserPage(req) || isUserCommand(req);
	}

	private boolean isAdmin(HttpSession session) {
		if (session == null || session.getAttribute(USER) == null) {
			return false;
		} else {
			User user = (User) session.getAttribute(USER);
			return user.isAdmin();
		}
	}

	private boolean isUser(HttpSession session, HttpServletRequest req) {
		if (session == null || session.getAttribute(USER) == null) {
			return false;
		} else {
			User user = (User) session.getAttribute(USER);
			return !(user.isBlocked() && isBannedUserCommand(req)) && isClientPage(req);
		}
	}

	private boolean isAdminPage(HttpServletRequest req) {
		String url = req.getRequestURI();
		return url.equals(ADMIN_PAGE) ||
				url.equals(ADD_AUCTION_PAGE) ||
				url.equals(ADD_LOT_PAGE) ||
				url.equals(AUCTION_BETS_PAGE) ||
				url.equals(AUCTIONS_PAGE) ||
				url.equals(CLIENTS_PAGE) ||
				url.equals(EDIT_LOT_PAGE) ||
				url.equals(EDIT_AUCTION_PAGE) ||
				url.equals(LOTS_PAGE);
	}

	private boolean isAdminCommand(HttpServletRequest req) {
		if (req.getParameter(COMMAND) != null) {
			String command = req.getParameter(COMMAND).toUpperCase();      
			return command.equals(CommandName.ADD_AUCTION.toString()) ||
					command.equals(CommandName.CHANGE_USER_STATUS.toString()) ||
					command.equals(CommandName.DELETE_AUCTION.toString()) ||
					command.equals(CommandName.EDIT_AUCTION.toString()) ||
					command.equals(CommandName.EDIT_LOT.toString()) ||
					command.equals(CommandName.GO_TO_ADD_AUCTION_PAGE.toString()) ||
					command.equals(CommandName.GO_TO_ADMIN_PAGE.toString()) ||
					command.equals(CommandName.GO_TO_AUCTION_BETS_PAGE.toString()) ||
					command.equals(CommandName.GO_TO_AUCTIONS_PAGE.toString()) ||
					command.equals(CommandName.GO_TO_CLIENTS_PAGE.toString()) ||
					command.equals(CommandName.GO_TO_EDIT_PAGE.toString()) ||
					command.equals(CommandName.GO_TO_LOTS_PAGE.toString()) ||
					command.equals(CommandName.SET_AUCTION_WINNER.toString()) ||
					command.equals(CommandName.GO_TO_LOT_EDIT_PAGE.toString());
		} else {
			return false;
		}
	}

	private boolean isUserPage(HttpServletRequest req) {
		String url = req.getRequestURI();
		return url.equals(CLIENT_PAGE);
	}

	private boolean isUserCommand(HttpServletRequest req) {

		if (req.getParameter(COMMAND) != null) {
			String command = req.getParameter(COMMAND).toUpperCase();
			return  command.equals(CommandName.GO_TO_CLIENT_PAGE.toString()) ||
					command.equals(CommandName.PLACE_BLITZ_BET.toString()) ||
					command.equals(CommandName.PLACE_ENGLISH_BET.toString()) ||
					command.equals(CommandName.BLOCK_LOT.toString()) ||
					command.equals(CommandName.ADD_LOT.toString()) ||
					command.equals(CommandName.CANCEL_BET.toString());
		} else {
			return false;
		}
	}

	private boolean isClientPage(HttpServletRequest req) {
		HttpSession session = req.getSession(false);
		User user = (User) session.getAttribute(USER);
		String sessionId  = String.valueOf(user.getId());
		String userId = req.getParameter(USER_ID);

		if (req.getParameter(COMMAND) != null) {
			String command = req.getParameter(COMMAND).toUpperCase();
			if(!command.equals(CommandName.GO_TO_CLIENT_PAGE.toString()) || (command.equals(CommandName.GO_TO_CLIENT_PAGE.toString()) && userId.equals(sessionId))){
				return true;
			}else{
				return  false;
			}
		} else {
			return true;
		}
	}

	private boolean isBannedUserCommand(HttpServletRequest req) {
		if (req.getParameter(COMMAND) != null) {
			String command = req.getParameter(COMMAND).toUpperCase();
			return command.equals(CommandName.PLACE_ENGLISH_BET.toString()) ||
					command.equals(CommandName.PLACE_BLITZ_BET.toString()) ||
					command.equals(CommandName.PLACE_BLITZ_PRICE.toString());
		} else {
			return false;
		}
	}

	private void setEncoding(HttpServletRequest req, HttpServletResponse resp) throws UnsupportedEncodingException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
	}

	@Override
	public void destroy() {

	}

}
