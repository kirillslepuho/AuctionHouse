
<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@include file="templates/header.jsp"%>

<fmt:message bundle="${loc}" key="local.userName" var="userName" />
<fmt:message bundle="${loc}" key="local.userEmail" var="userEmail" />
<fmt:message bundle="${loc}" key="local.userCardNumber"
	var="userCardNumber" />
<fmt:message bundle="${loc}" key="local.userBlockedStatus"
	var="userBlockedStatus" />
<fmt:message bundle="${loc}" key="local.auction" var="auction" />
<fmt:message bundle="${loc}" key="local.bet" var="bet" />
<fmt:message bundle="${loc}" key="local.iswinner" var="winner" />
<fmt:message bundle="${loc}" key="local.lot" var="lot" />
<fmt:message bundle="${loc}" key="local.lotName" var="lotName" />
<fmt:message bundle="${loc}" key="local.category" var="category" />
<fmt:message bundle="${loc}" key="local.currentPrice" var="currentPrice" />
<fmt:message bundle="${loc}" key="local.description" var="description" />
<fmt:message bundle="${loc}" key="local.blitzBet" var="blitzBet" />
<fmt:message bundle="${loc}" key="local.blitzPrice" var="blitzPrice" />
<fmt:message bundle="${loc}" key="local.clientPage" var="clientPage" />
<fmt:message bundle="${loc}" key="local.contactDetails"
	var="contactDetails" />
<fmt:message bundle="${loc}" key="local.yourBets" var="yourBets" />
<fmt:message bundle="${loc}" key="local.yourLots" var="yourLots" />


<c:set var="user" value="${sessionScope.user}" />
<c:set var="bets" value="${requestScope.bets}" />

<div class="container">
	<div class="row">
		<!-- Article main content -->
		<article class="col-xs-12 maincontent">
			<header class="page-header">
				<h1 class="page-title">${clientPage}</h1>
			</header>
			<!--Имя пользователя-->
			<div class="col-md-4 col-md-offset-1">
				<h2>${contactDetails}</h2>
				<div class="col-md-12 information-border-style">
					<div class="col-md-6">
						<p class="lead info-type">${userName}:</p>
					</div>
					<div class="col-md-6">
						<p class="lead">${user.name}</p>
					</div>
				</div>

				<!--Email-->
				<div class="col-md-12 information-border-style">
					<div class="col-md-6">
						<p class="lead info-type">${userEmail}:</p>
					</div>
					<div class="col-md-6">
						<p class="lead">${user.email}</p>
					</div>
				</div>

				<!--Номер счета-->
				<div class="col-md-12 information-border-style">
					<div class="col-md-6">
						<p class="lead info-type">${userCardNumber}:</p>
					</div>
					<div class="col-md-6">
						<p class="lead">${user.cardnumber}</p>
					</div>
				</div>

				<!--Статус блокировки-->
				<div class="col-md-12 information-border-style">
					<div class="col-md-6">
						<p class="lead info-type">${userBlockedStatus}:</p>
					</div>
					<div class="col-md-6">
						<p class="lead">${user.blocked}</p>
					</div>
				</div>
			</div>




			<div class="col-md-6 col-md-offset-0 col-sm-8 col-sm-offset-2">
				<table class="table table-bordered table-striped table-bets">
					<caption>
						<h2>${yourBets}</h2>
					</caption>
					<thead>
						<tr>
							<th>${auction}</th>
							<th>${bet}</th>
							<th>${winner}</th>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${bets}" var="temp">
							<tr>
								<td><a
									href="/AuctionHouse/Controller?command=go_to_auction_page&id=${temp.auction}"
									class="">${temp.auction}</a></td>
								<td><a
									href="/AuctionHouse/Controller?command=go_to_auction_page&id=${temp.auction}"
									class="">${temp.bet}</a></td>
								<td><a
									href="/AuctionHouse/Controller?command=go_to_auction_page&id=${temp.auction}"
									class="">${temp.winner}</a></td>
								<td><a
									href="/AuctionHouse/Controller?command=cancel_bet&auctionId=${temp.auction}&clientId=${temp.client}&bet=${temp.bet}"
									class="">Cancel</a></td>
							</tr>

						</c:forEach>
					</tbody>
				</table>
			</div>

			<br>
			<c:set var="lots" value="${requestScope.lots}" />
			<div class="col-md-12 col-md-offset-0 col-sm-8 col-sm-offset-2">
				<table class="table table-bordered table-striped">
					<caption>
						<h2>${yourLots}</h2>
					</caption>
					<thead>
						<tr>
							<th>${category}</th>
							<th>${lotName}</th>
							<th>${currentPrice}</th>
							<th>${description}</th>
							<th>${blitzBet}</th>
							<th>${blitzPrice}</th>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${lots}" var="temp">
							<tr>
								<td><a href="" class="">${temp.type}</a></td>
								<td><a href="" class="">${temp.name}</a></td>
								<td><a href="" class="">${temp.currentPrice}</a></td>
								<td><a href="" class="">${temp.descriprion}</a></td>
								<td><a href="" class="">${temp.blitzBet}</a></td>
								<td><a href="" class="">${temp.blitzPrice}</a></td>
								<td><a href="">${delete}</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>

		</article>

	</div>
</div>

<%@include file="templates/footer.jsp"%>