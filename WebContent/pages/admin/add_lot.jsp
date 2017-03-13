<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@include file="../templates/header.jsp"%>

<fmt:message bundle="${loc}" key="local.login" var="login"/>
<fmt:message bundle="${loc}" key="local.password" var="password"/>
<fmt:message bundle="${loc}" key="local.registration" var="registration"/>
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
				<h1 class="page-title">Лот</h1>
			</header>

			<div class="col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
				<div class="panel panel-default">
					<div class="panel-body">
						<h3 class="thin text-center">Лот</h3>
						<hr>

						<form action="/AuctionHouse/Controller?command=add_lot" method="post">
							<div class="top-margin">
								<label>Name</label> <input type="text" class="form-control"
									name="lot_name">
							</div>
							<div class="top-margin">
								<label>Type</label> <input type="text"
									class="form-control" name="lot_type">
							</div>
							<div class="top-margin">
								<label>Price</label> <input type="text"
									class="form-control" name="current_price">
							</div>
                            <div class="top-margin">
								<label>Description</label> <input type="text"
									class="form-control" name="description">
							</div>
							<div class="top-margin">
								<label>Image</label> <input type="text"
									class="form-control" name="image">
							</div>
							

							<hr>

							<div class="row">
								<div class="col-lg-8"></div>
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
</div>
<!-- /container -->

<%@include file="../templates/footer.jsp"%>