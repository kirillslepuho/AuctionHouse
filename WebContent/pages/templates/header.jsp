<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:requestEncoding value="UTF-8" />
<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />

<fmt:message bundle="${loc}" key="local.adminPage" var="adminPage" />
<fmt:message bundle="${loc}" key="local.main" var="main" />
<fmt:message bundle="${loc}" key="local.offer" var="offer" />
<fmt:message bundle="${loc}" key="local.contact" var="contact" />
<fmt:message bundle="${loc}" key="local.sign_in_up" var="sign_in_up" />
<fmt:message bundle="${loc}" key="local.signout" var="signout" />

<!DOCTYPE html>
<html>
<head>
<title>Auctions</title>
<meta charset="utf-8">
<link rel="stylesheet" media="screen"
	href="http://fonts.googleapis.com/css?family=Open+Sans:300,400,700">


<link rel="stylesheet" type="text/css"
	href="/AuctionHouse/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="/AuctionHouse/css/font-awesome.min.css">

<link rel="stylesheet" type="text/css"
	href="/AuctionHouse/css/bootstrap-theme.css" media="screen">
<link rel="stylesheet" type="text/css" href="/AuctionHouse/css/main.css">

</head>

<body>
	<div class="navbar navbar-inverse navbar-fixed-top headroom">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-collapse">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
			</div>
			<div class="navbar-collapse collapse">
				<ul class="nav navbar-nav pull-right">

					<c:set var="user" value="${sessionScope.user}" />
					<c:set var="admin" value="${sessionScope.admin}" />

					<c:if test="${not empty admin}">
						<c:if test="${admin eq true}">
							<li><a
								href="/AuctionHouse/Controller?command=go_to_admin_page">${adminPage}</a></li>
						</c:if>
					</c:if>

					<li><a href="/AuctionHouse/Controller?command=go_to_main_page">${main}</a></li>
					<li><a href=<c:url value ="/OfferLot" ></c:url>>${offer}</a></li>

					<c:if test="${empty user}">
						<li><a href=<c:url value ="/SignIn" ></c:url>>${sign_in_up}</a></li>
					</c:if>

					<c:if test="${not empty user}">
						<li><a href="/AuctionHouse/Controller?command=go_to_client_page&userId=${user.id}">${user.name}</a></li>
						<li><a href="/AuctionHouse/Controller?command=sign_out">${signout}</a></li>
					</c:if>

					<li>
						<ul class="nav navbar-nav">
							<li class=""><a class="btn"
								href="/AuctionHouse/Controller?command=localization&local=en">EN</a></li>
							<li class=""><a class="btn"
								href="/AuctionHouse/Controller?command=localization&local=ru">RU</a></li>
						</ul>
					</li>

				</ul>
			</div>
		</div>
	</div>

	<!-- Header -->
	<header id="head" class="secondary"></header>
	<!-- /Header -->