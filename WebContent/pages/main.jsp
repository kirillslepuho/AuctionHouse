
<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@include file="../pages/templates/header.jsp"%>

<fmt:message bundle="${loc}" key="local.main" var="main"/>


<c:set var="auctions" value="${requestScope.auctions}" />
<!-- container -->
<div class="container">

	<div class="row">

		<!-- Article main content -->
		<article class="col-xs-12">
			<header class="page-header">
				<h1 class="page-title">${main}</h1>
			</header>
			
		<c:forEach items="${auctions}" var="temp">
		<a href="/AuctionHouse/Controller?command=go_to_auction_page&id=${temp.id}">
			<div class="col-sm-4">
				<div class="auction">
					<div>
						<img
							src="${temp.lot.image}" alt="cover">
					</div>
					<p class="auction-title">
						${temp.lot.name}
					</p>
					<p class="auction-desc">${temp.lot.type}</p>
					<p class="auction-price">${temp.lot.currentPrice}</p>
				</div>
			</div>
			</a>
			</c:forEach>
		</article>
		<!-- /Article -->

	</div>


</div>
<!-- /container -->

<%@include file="../pages/templates/footer.jsp"%>