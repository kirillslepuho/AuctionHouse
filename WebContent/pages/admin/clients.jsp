
<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@include file="../templates/header.jsp"%>

<fmt:message bundle="${loc}" key="local.userName" var="userName" />
<fmt:message bundle="${loc}" key="local.userEmail" var="userEmail" />
<fmt:message bundle="${loc}" key="local.userCardNumber"
	var="userCardNumber" />
<fmt:message bundle="${loc}" key="local.userBlockedStatus"
	var="userBlockedStatus" />
<fmt:message bundle="${loc}" key="local.id"
	var="id" />
<fmt:message bundle="${loc}" key="local.blocked"
	var="blocked" />
	<fmt:message bundle="${loc}" key="local.not_blocked"
	var="not_blocked" />
	<fmt:message bundle="${loc}" key="local.ok"
	var="ok" />



<div class="container">
	<div class="row">

		<!-- Article main content -->
		<article class="col-xs-12 maincontent">
			<header class="page-header">
				<h1 class="page-title">Клиенты</h1>
			</header>
			<div class="col-md-1 col-md-offset-0 col-sm-8 col-sm-offset-2">
				<table class="table table-striped table-bordered">
					<tr>
						<th>${id}</th>
						<th>${userName}</th>
						<th>${userEmail}</th>
						<th>${userCardNumber}</th>
						<th>${userBlockedStatus}</th>
					</tr>
					<c:forEach var="user" items="${requestScope.clients}">
						<tr>
							<td><c:out value="${user.id}" /></td>
							<td><c:out value="${user.name}" /></td>
							<td><c:out value="${user.email}" /></td>
							<td><c:out value="${user.cardnumber}" /></td>
							<td><c:out value="${user.blocked}" /></td>
						</tr>
					</c:forEach>
				</table>
			</div>
			<div class="col-md-5 col-md-offset-6 col-sm-8 col-sm-offset-2">
				<form action="/AuctionHouse/Controller"
					method="post">
					<div class="form-group ">
						<input type="hidden" name="command" value="change_user_status" /> <label
							for="id">${id}</label> <input class="form-control" type="number"
							id="id" name="user_id" min="1" max="2000000000" required>
					</div>
					<div class="form-group">
						<label for="isBlocked">${userBlockedStatus}</label> <select
							class="form-control" id="isBlocked" name="user_block">
							<option value=false>${not_blocked}</option>
							<option value=true>${blocked}</option>
						</select>
					</div>
					<button type="submit" class="btn btn-danger">${ok}</button>
				</form>
			</div>


		</article>

	</div>
</div>

<%@include file="../templates/footer.jsp"%>