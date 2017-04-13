
<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@include file="../pages/templates/header.jsp"%>

<fmt:message bundle="${loc}" key="local.authorization"
	var="authorization" />
<fmt:message bundle="${loc}" key="local.registration" var="registration" />
<fmt:message bundle="${loc}" key="local.log" var="log" />
<fmt:message bundle="${loc}" key="local.login" var="login" />
<fmt:message bundle="${loc}" key="local.password" var="password" />
<fmt:message bundle="${loc}" key="local.locbutton.name.ru"
	var="ru_button" />
<fmt:message bundle="${loc}" key="local.locbutton.name.en"
	var="en_button" />

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
						<h3 class="thin text-center">
							<c:out value="${authorization}" />
						</h3>
						<p class="text-center text-muted">
							<a href="/AuctionHouse/SignUp">${registration}</a>
						</p>
						<hr>

						<form role="form" id="sign-in-form" action="javascript:void(null);" onsubmit="signIn()" method="post">
							<div class="top-margin">
								<label>${login}</label> <input type="email" id="sign-in-email" class="form-control">
							</div>
							<div class="top-margin">
								<label>${password}</label> <input type="password" id="sign-in-password"
									class="form-control">
							</div>
							<div class="val_error" id="error-sign-in-message">
                                    <b id="messageErr"></b>
                              </div>

							<hr>

							<div class="row">
								<div class="col-lg-8"></div>
								<div class="col-lg-4 text-right">
									<button id="submit_button" class="btn btn-action" type="submit"> 
										<c:out value="${log}" />
									</button>
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


<%@include file="../pages/templates/footer.jsp"%>