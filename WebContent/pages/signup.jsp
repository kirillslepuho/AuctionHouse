<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@include file="../pages/templates/header.jsp"%>

<fmt:message bundle="${loc}" key="local.login" var="login"/>
<fmt:message bundle="${loc}" key="local.password" var="password"/>
<fmt:message bundle="${loc}" key="local.registration" var="registration"/>
<fmt:message bundle="${loc}" key="local.authorization" var="authorization"/>
<fmt:message bundle="${loc}" key="local.name" var="name"/>
<fmt:message bundle="${loc}" key="local.cardnumber" var="cardnumber"/>
<fmt:message bundle="${loc}" key="local.repeatPassword" var="repeatPassword"/>
<fmt:message bundle="${loc}" key="local.register" var="register"/>

<!-- container -->
<div class="container">

	<div class="row">

		<!-- Article main content -->
		<article class="col-xs-12 maincontent">
			<header class="page-header">
				<h1 class="page-title">${registration}</h1>
			</header>

			<div class="col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
				<div class="panel panel-default">
					<div class="panel-body">
						<h3 class="thin text-center">${registration}</h3>
						<p class="text-center text-muted">
							<a href="signin.jsp">${authorization}</a>
						</p>
						<hr>

						<form role="form" method="post" action="javascript:void(null);" onsubmit="registration()">
							<div class="top-margin">
								<label>${name}</label> <input type="text" class="form-control"
									id="registration-name">
							</div>
							<div class="top-margin">
								<label>${cardnumber}</label> <input type="text"
									class="form-control" id="registration-cardnumber">
							</div>
							<div class="top-margin">
								<label>${login}</label> <input type="email"
									class="form-control" id="registration-email">
							</div>

							<div class="row top-margin">
								<div class="col-sm-6">
									<label>${password}</label> <input type="password"
										class="form-control" id="registration-password">
								</div>
								<div class="col-sm-6">
									<label>${repeatPassword}</label> <input type="password"
										class="form-control" id="registration-repeat-password">
								</div>
							</div>
							<div class="val_error" id="error-registration-message">
                                    <b id="messageErr"></b>
                              </div>

							<hr>

							<div class="row">
								<div class="col-lg-6"></div>
								<div class="col-lg-6 text-right">
									<button class="btn btn-action" type="submit">${register}</button>
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