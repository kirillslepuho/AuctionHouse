
<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@include file="../templates/header.jsp"%>

<fmt:message bundle="${loc}" key="local.beginDate" var="beginDate"/>
<fmt:message bundle="${loc}" key="local.type" var="type"/>
<fmt:message bundle="${loc}" key="local.expirationDate" var="expirationDate"/>
<fmt:message bundle="${loc}" key="local.lot" var="lot"/>
<fmt:message bundle="${loc}" key="local.lotName" var="lotName"/>
<fmt:message bundle="${loc}" key="local.category" var="category"/>
<fmt:message bundle="${loc}" key="local.currentPrice" var="currentPrice"/>
<fmt:message bundle="${loc}" key="local.description" var="description"/>
<fmt:message bundle="${loc}" key="local.blitzBet" var="blitzBet"/>
<fmt:message bundle="${loc}" key="local.clients" var="clients"/>
<fmt:message bundle="${loc}" key="local.lots" var="lotsInfo"/>
<fmt:message bundle="${loc}" key="local.delete" var="delete"/>

<c:set var="lots" value="${requestScope.lots}" />
<div class="container">
	<div class="row">

		<!-- Article main content -->
		<article class="col-xs-12 maincontent">
			<header class="page-header">
				<h1 class="page-title">${lotsInfo}</h1>
			</header>					
			<div class="col-md-12 col-md-offset-0 col-sm-8 col-sm-offset-2">
			<table class="table table-bordered table-striped">
					<thead>
						<tr>
							<th>#</th>
							<th>${category}</th>
							<th>${lotName}</th>
							<th>${currentPrice}</th>
							<th>${description}</th>
							<th>${clients}</th>
							<th>${blitzBet}</th>
							<th></th>
						</tr>
					</thead>
					<tbody>
					<c:forEach items="${lots}" var="temp">
						<tr>
						<td><a
								href="/AuctionHouse/Controller?command=go_to_lot_edit_page&id=${temp.id}"
								>${temp.id}</a></td>
							<td><a
								href="/AuctionHouse/Controller?command=go_to_lot_edit_page&id=${temp.id}"
								>${temp.type}</a></td>
								<td><a
								href="/AuctionHouse/Controller?command=go_to_lot_edit_page&id=${temp.id}"
								>${temp.name}</a></td>
								 <td><a
								href="/AuctionHouse/Controller?command=go_to_lot_edit_page&id=${temp.id}"
								>${temp.currentPrice}</a></td>
								<td><a
								href="/AuctionHouse/Controller?command=go_to_lot_edit_page&id=${temp.id}"
								>${temp.descriprion}</a></td>
								<td><a
								href="/AuctionHouse/Controller?command=go_to_lot_edit_page&id=${temp.id}"
								>${temp.clients}</a></td>
								<td><a
								href="/AuctionHouse/Controller?command=go_to_lot_edit_page&id=${temp.id}"
								>${temp.blitzBet}</a></td>
								<td><a
								href="/AuctionHouse/Controller?command=block_lot&id=${temp.id}"
								>${delete}</a></td>
								</tr>
					</c:forEach>
					</tbody>
				</table>
				<div class="col-md-12">
					<a class=""
						href="/AuctionHouse/pages/admin/add_lot.jsp"><strong>Добавить новый</strong></a>
				</div>
				</div>
		</article>

	</div>
</div>

<%@include file="../templates/footer.jsp"%>