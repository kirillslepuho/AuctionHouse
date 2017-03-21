<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@include file="templates/header.jsp"%>

<fmt:message bundle="${loc}" key="local.addLot" var="addLot"/>
<fmt:message bundle="${loc}" key="local.lot" var="lot"/>
<fmt:message bundle="${loc}" key="local.lotName" var="lotName"/>
<fmt:message bundle="${loc}" key="local.category" var="category"/>
<fmt:message bundle="${loc}" key="local.currentPrice" var="currentPrice"/>
<fmt:message bundle="${loc}" key="local.description" var="description"/>
<fmt:message bundle="${loc}" key="local.image" var="image"/>
<fmt:message bundle="${loc}" key="local.create" var="create"/>

<!-- container -->
<div class="container">

	<div class="row">

		<!-- Article main content -->
		<article class="col-xs-12 maincontent">
			<header class="page-header">
				<h1 class="page-title">${addLot}</h1>
			</header>

			<div class="col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
				<div class="panel panel-default">
					<div class="panel-body">
						<h3 class="thin text-center">${lot}</h3>
						<hr>

						<form action="/AuctionHouse/Controller?command=add_lot" method="post">
						<input type="hidden" name ="is_client" value = "true"/>
						<input type="hidden" name ="image" value = "/AuctionHouse/data/common/images/no_photo.png"/>
							<div class="top-margin">
								<label>${lotName}</label> <input type="text" class="form-control"
									name="lot_name">
							</div>
							<div class="top-margin">
								<label>${category}</label> <input type="text"
									class="form-control" name="lot_type">
							</div>
							<div class="top-margin">
								<label>${currentPrice}</label> <input type="text"
									class="form-control" name="current_price">
							</div>
                            <div class="top-margin">
								<label>${description}</label> <input type="text"
									class="form-control" name="description">
							</div>
				

							<hr>

							<div class="row">
								<div class="col-lg-8"></div>
								<div class="col-lg-4 text-right">
									<button class="btn btn-action" type="submit">${create}</button>
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

<%@include file="templates/footer.jsp"%>