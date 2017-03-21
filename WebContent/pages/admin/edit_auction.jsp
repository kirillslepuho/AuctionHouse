
<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@include file="../templates/header.jsp"%>


<fmt:message bundle="${loc}" key="local.editAuction" var="editAuction"/>
<fmt:message bundle="${loc}" key="local.auction" var="auction"/>
<fmt:message bundle="${loc}" key="local.place" var="place"/>
<fmt:message bundle="${loc}" key="local.beginDate" var="beginDate"/>
<fmt:message bundle="${loc}" key="local.time" var="time"/>
<fmt:message bundle="${loc}" key="local.expirationDate" var="expirationDate"/>
<fmt:message bundle="${loc}" key="local.type" var="type"/>
<fmt:message bundle="${loc}" key="local.active" var="active"/>
<fmt:message bundle="${loc}" key="local.rounds" var="rounds"/>
<fmt:message bundle="${loc}" key="local.edit" var="edit"/>

<c:set var="auctionInfo" value="${requestScope.auction}" />

<!-- container -->
<div class="container">

	<div class="row">

		<!-- Article main content -->
		<article class="col-xs-12 maincontent">
			<header class="page-header">
				<h1 class="page-title">${editAuction}</h1>
			</header>

			<div class="col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
				<div class="panel panel-default">
					<div class="panel-body">
						<h3 class="thin text-center">${auction}</h3>
						<hr>
						<form action="/AuctionHouse/Controller" method="post">
							<input type="hidden" name="command" value="edit_auction">

							<input type="hidden" name="change_id" value="${requestScope.id}" />

							<div class="top-margin">
								<label>Lot ID</label> <input type="text" class="form-control"
									name="lot_id" value="${auctionInfo.lot.id}">
							</div>
							<div class="top-margin">
								<label>${place}</label> <input type="text" class="form-control"
									name="place" value="${auctionInfo.place}">
							</div>
							<div class="top-margin">
								<label>${beginDate}</label> <input type="text"
									class="form-control" name="begin_date"
									value="${auctionInfo.beginDate}">
							</div>

							<div class="top-margin">
								<label>${time}</label> <input type="text" class="form-control"
									name="time" value="${auctionInfo.time}">
							</div>

							<div class="top-margin">
								<label>${expirationDate}</label> <input type="text"
									class="form-control" name="expiration_date"
									value="${auctionInfo.expirationDate}">
							</div>

							<div class="top-margin">
								<label>${type}</label> <input type="text" class="form-control"
									name="type" value="${auctionInfo.type}">
							</div>

							<div class="top-margin">
								<label>${active}?</label> <select class="form-control input-sm"
									name="is_active">
									<option value="" selected="selected"></option>
									<option value="Yes">Yes</option>
									<option value="No">No</option>
								</select>
								<!-- <input type="text" class="form-control" name = "is_active" > -->
							</div>

							<div class="top-margin">
								<label>${rounds}</label> <input type="text" class="form-control"
									name="rounds" value="${auctionInfo.rounds}">
							</div>
							<hr>
							<hr>

							<div class="row">
								<div class="col-lg-8"></div>
								<div class="col-lg-4 text-right">
									<button class="btn btn-action" type="submit">${edit}</button>
								</div>
							</div>
						</form>
					</div>
				</div>

			</div>

		</article>
		<!-- /Article -->

	</div>
</div>
<!-- /container -->

<%@include file="../templates/footer.jsp"%>