																																																								<%@ page language="java" contentType="text/html;charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@include file="../pages/templates/header.jsp"%>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="localization.local" var="loc"/>
<fmt:message bundle="${loc}" key="local.authorization" var="authorization"/>
<fmt:message bundle="${loc}" key="local.log" var="log"/>
<fmt:message bundle="${loc}" key="local.login" var="login"/>
<fmt:message bundle="${loc}" key="local.password" var="password"/>
<fmt:message bundle="${loc}" key="local.locbutton.name.ru" var="ru_button"/>
<fmt:message bundle="${loc}" key="local.locbutton.name.en" var="en_button"/>

	<!-- container -->
	<div class="container">

		<div class="row">
			
			<!-- Article main content -->
			<article class="col-xs-12 maincontent">
				<header class="page-header">
					<h1 class="page-title">${authorization}</h1>
				</header>
				
				<div class="col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
					<div class="panel panel-default">
						<div class="panel-body">
							<h3 class="thin text-center"><c:out value = "${authorization}"/></h3>
							<p class="text-center text-muted"><a href="signup.jsp">Registration</a></p>
							<hr>
							
							<form action="/AuctionHouse/Controller" method="post">
							    <input type="hidden" name="command" value = "sign_in" > 
								<div class="top-margin">
									<label>${login}</label>
									<input type="email" class="form-control" name = "login" > 
								</div>
								<div class="top-margin">
									<label>${password}</label>
									<input type="password" class="form-control"  name = "password" >
								</div>

								<hr>

								<div class="row">
									<div class="col-lg-8">
									</div>
									<div class="col-lg-4 text-right">
										<button class="btn btn-action" type="submit"><c:out value = "${log}"/></button>
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
	
	    <%@include file="../pages/templates/footer.jsp"%>