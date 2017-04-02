
<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@include file="../templates/header.jsp"%>

<fmt:message bundle="${loc}" key="local.beginDate" var="beginDate"/>
<fmt:message bundle="${loc}" key="local.type" var="type"/>
<fmt:message bundle="${loc}" key="local.expirationDate" var="expirationDate"/>
<fmt:message bundle="${loc}" key="local.lot" var="lot"/>
<fmt:message bundle="${loc}" key="local.lotName" var="lotName"/>
<fmt:message bundle="${loc}" key="local.description" var="description"/>
<fmt:message bundle="${loc}" key="local.bet" var="bet"/>

<c:set var="auctions" value="${requestScope.auctions}" />
<div class="container">
	<div class="row">

		<!-- Article main content -->
		<article class="col-xs-12 maincontent">
			<header class="page-header">
				<h1 class="page-title">Аукционы</h1>
			</header>
			<div class="col-md-12 col-md-offset-0 col-sm-8 col-sm-offset-2">
				<table class="table table-bordered table-striped">
					<thead>
						<tr>
							<th>#</th>
							<th>${lot}</th>
							<th>${type}</th>
							<th>${beginDate}</th>
							<th>${expirationDate}</th>
							<th>${type}</th>
							<th></th>
							<th></th>
						</tr>
					</thead>
					<tbody>
					<c:forEach items="${auctions}" var="temp">
						<tr>
						<td><a
								href="/AuctionHouse/Controller?command=go_to_edit_page&id=${temp.id}"
								class="">${temp.id}</a></td>
							<td><a
								href="/AuctionHouse/Controller?command=go_to_edit_page&id=${temp.id}"
								class="">${temp.lot.name}</a></td>
								<td><a
								href="/AuctionHouse/Controller?command=go_to_edit_page&id=${temp.id}"
								class="">${temp.lot.type}</a></td>
								 <td><a
								href="/AuctionHouse/Controller?command=go_to_edit_page&id=${temp.id}"
								class="">${temp.beginDate}</a></td>
								<td><a
								href="/AuctionHouse/Controller?command=go_to_edit_page&id=${temp.id}"
								class="">${temp.expirationDate}</a></td>
								<td><a
								href="/AuctionHouse/Controller?command=go_to_edit_page&id=${temp.id}"
								class="">${temp.type}</a></td>
								<td><a
								href="/AuctionHouse/Controller?command=go_to_auction_bets_page&auctionId=${temp.id}"
								class="">${bet}</a></td>
								<td><a href="/AuctionHouse/Controller?command=delete_auction&id=${temp.id}"
								class="">Удалить</a>
								</td>
								</tr>
		
					</c:forEach>
					</tbody>
				</table>
				<div class="col-md-12">
					<a class=""
						href="/AuctionHouse/Controller?command=go_to_add_auction_page"><strong>Добавить новый</strong></a>
				</div>		
			</div>
		</article>
		</div>
		</div>

<%@include file="../templates/footer.jsp"%>