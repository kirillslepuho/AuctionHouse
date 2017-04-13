
<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@include file="../templates/header.jsp"%>
<fmt:message bundle="${loc}" key="local.AdminPage" var="adminPage" />
<fmt:message bundle="${loc}" key="local.users" var="users" />
<fmt:message bundle="${loc}" key="local.auctions" var="auctions" />
<fmt:message bundle="${loc}" key="local.lots" var="lots" />
<fmt:message bundle="${loc}" key="local.bets" var="bets" />

<c:set var="user" value="${sessionScope.user}" />
<c:set var="admin" value="${sessionScope.admin}" />

<c:if test="${empty admin}">
	<c:redirect url="/Controller?command=go_to_main_page" />
</c:if>
<c:if test="${admin eq false}">
	<c:redirect url="/Controller?command=go_to_main_page" />
</c:if>

<div class="container">
	<div class="row">

		<!-- Article main content -->
		<article class="col-xs-12 maincontent">
			<header class="page-header">
				<h1 class="page-title">${adminPage}</h1>
			</header>

			<div class="container-btn">
				<a href="/AuctionHouse/Controller?command=go_to_clients_page">
					<div>
						<h2>${users}</h2>
					</div>
				</a>
			</div>
			<div class="container-btn">
				<a href="/AuctionHouse/Controller?command=go_to_auctions_page">
					<div>
						<h2>${auctions}</h2>
					</div>
				</a>
			</div>
			<div class="container-btn">
				<a href="/AuctionHouse/Controller?command=go_to_lots_page">
					<div>
						<h2>${lots}</h2>
					</div>
				</a>
			</div>
		</article>

	</div>
</div>

<%@include file="../templates/footer.jsp"%>