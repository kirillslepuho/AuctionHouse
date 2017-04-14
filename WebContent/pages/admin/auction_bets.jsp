
<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@include file="../templates/header.jsp"%>

<fmt:message bundle="${loc}" key="local.client" var="client" />
<fmt:message bundle="${loc}" key="local.auction" var="auction" />
<fmt:message bundle="${loc}" key="local.bet" var="bet" />
<fmt:message bundle="${loc}" key="local.iswinner" var="winner" />

<main>
<div class="container">
	<div class="row">

		<!-- Article main content -->
		<article class="col-xs-12 maincontent">
			<header class="page-header">
				<h1 class="page-title">Страница администратора</h1>
			</header>					
			<div class="col-md-12 col-md-offset-0 col-sm-8 col-sm-offset-2">
			<table class="table table-bordered table-striped">
					<thead>
						<tr>
							<th>${client}</th>
							<th>${auction}</th>
							<th>${bet}</th>
							<th>${winner}</th>
							<th></th>
						</tr>
					</thead>
					<tbody>
					<c:forEach items="${requestScope.bets}" var="temp">
						<tr>
						<td><a
								href=""
								class="">${temp.client}</a></td>
							<td><a
								href=""
								class="">${temp.auction}</a></td>
								<td><a
								href=""
								class="">${temp.bet}</a></td>
								 <td><a
								href=""
								class="">${temp.winner}</a></td>
								<td><a href="/AuctionHouse/Controller?command=set_auction_winner&client=${temp.client}&auction=${temp.auction}&bet=${temp.bet}"
								class="">Win</a>
								</td>
								</tr>
					</c:forEach>
					</tbody>
				</table>
				</div>
		</article>

	</div>
</div>
</main>
<%@include file="../templates/footer.jsp"%>
