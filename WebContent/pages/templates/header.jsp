<%@page pageEncoding="UTF-8"%>

<fmt:requestEncoding value="UTF-8" />
<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="localization.local" var="loc"/>


<!DOCTYPE html>
<html>
<head>
<title>Auctions</title>
<meta charset="utf-8">
<link rel="stylesheet" media="screen"
	href="http://fonts.googleapis.com/css?family=Open+Sans:300,400,700">


<link rel="stylesheet" type="text/css" href="/AuctionHouse/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="/AuctionHouse/css/font-awesome.min.css">

<link rel="stylesheet" type="text/css" href="/AuctionHouse/css/bootstrap-theme.css" media="screen">
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
					<li><a href="/AuctionHouse/Controller?command=go_to_admin_page">AdminPage</a></li>
					<li><a href="#">Main</a></li>
					<li><a href="#">About</a></li>
					<li><a href="#">Contact</a></li>
					<li><a href="/AuctionHouse/pages/signin.jsp">SIGN IN / SIGN UP</a></li>
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