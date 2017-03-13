																																																								<%@ page language="java" contentType="text/html;charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@include file="../templates/header.jsp"%>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="localization.local" var="loc"/>
<fmt:message bundle="${loc}" key="local.authorization" var="authorization"/>
<fmt:message bundle="${loc}" key="local.log" var="log"/>
<fmt:message bundle="${loc}" key="local.login" var="login"/>

<!-- container -->
	<div class="container">

		<div class="row">
			
			<!-- Article main content -->
			<article class="col-xs-12 maincontent">
				<header class="page-header">
					<h1 class="page-title">Add auction</h1>
				</header>
				
				<div class="col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
					<div class="panel panel-default">
						<div class="panel-body">
							<h3 class="thin text-center">New auction</h3>
							<hr>
							<form action="/AuctionHouse/Controller" method="post">
							    <input type="hidden" name="command" value = "add_auction" > 
								<div class="top-margin">
									<label>Lot ID</label>
									<input type="text" class="form-control" name = "lot_id" > 
								</div>
								<div class="top-margin">
									<label>Place</label>
									<input type="text" class="form-control" name = "place" > 
								</div>
								<div class="top-margin">
									<label>Begin date</label>
									<input type="text" class="form-control" name = "begin_date" > 
								</div>

								<div class="top-margin">
									<label>Time</label>
									<input type="text" class="form-control" name = "time" > 
								</div>
								
								<div class="top-margin">
									<label>Expiration date</label>
									<input type="text" class="form-control" name = "expiration_date" > 
								</div>
								
								<div class="top-margin">
									<label>Type</label>
									<input type="text" class="form-control" name = "type" > 
								</div>

								<div class="top-margin">
									<label>Active?</label>
									<select class="form-control input-sm" name="is_active">
									<option value="" selected="selected"></option>
									<option value="Yes">Yes</option>
									<option value="No">No</option>
									</select>
									<!-- <input type="text" class="form-control" name = "is_active" > -->
								</div>
								
								<div class="top-margin">
									<label>Rounds</label>
									<input type="text" class="form-control" name = "rounds" > 
								</div>
								<hr>
								<hr>

								<div class="row">
									<div class="col-lg-8">                      
									</div>
									<div class="col-lg-4 text-right">
										<button class="btn btn-action" type="submit">Create</button>
									</div>
								</div>
							</form>
						</div>
					</div>

				</div>
				
			</article>
			<!-- /Article -->

		</div>
	</div>	<!-- /container -->
	
	    <%@include file="../templates/footer.jsp"%>