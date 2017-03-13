
<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@include file="../templates/header.jsp"%>

<c:set var="auctions" value="${requestScope.auctions}" />
<c:set var="lots" value="${requestScope.lots}" />
<div class="container">
	<div class="row">

		<!-- Article main content -->
		<article class="col-xs-12 maincontent">
			<header class="page-header">
				<h1 class="page-title">Страница администратора</h1>
			</header>
			<div class="col-md-1 col-md-offset-0 col-sm-8 col-sm-offset-2">
				<table class="table table-bordered table-striped">
					<thead>
						<tr>
							<th>#</th>
							<th>Лот</th>
							<th>Тип</th>
							<th>Начало</th>
							<th>Активность</th>
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
								class="">${temp.isActive}</a></td>
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
			
			<div class="col-md-5 col-md-offset-5 col-sm-8 col-sm-offset-2">
			<table class="table table-bordered table-striped">
					<thead>
						<tr>
							<th>#</th>
							<th>Тип</th>
							<th>Название</th>
							<th>Текущая цена</th>
							<th>Описание</th>
						</tr>
					</thead>
					<tbody>
					<c:forEach items="${lots}" var="temp">
						<tr>
						<td><a
								href=""
								class="">${temp.id}</a></td>
							<td><a
								href=""
								class="">${temp.type}</a></td>
								<td><a
								href=""
								class="">${temp.name}</a></td>
								 <td><a
								href=""
								class="">${temp.currentPrice}</a></td>
								<td><a
								href=""
								class="">${temp.descriprion}</a></td>
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