<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@include file="templates/header.jsp"%>

<fmt:message bundle="${loc}" key="local.offerLot" var="offerLot" />
<fmt:message bundle="${loc}" key="local.lot" var="lot" />
<fmt:message bundle="${loc}" key="local.lotName" var="lotName" />
<fmt:message bundle="${loc}" key="local.category" var="category" />
<fmt:message bundle="${loc}" key="local.currentPrice" var="currentPrice" />
<fmt:message bundle="${loc}" key="local.description" var="description" />
<fmt:message bundle="${loc}" key="local.image" var="image" />
<fmt:message bundle="${loc}" key="local.create" var="create" />



<!-- container -->
<div class="container">

	<div class="row">

		<!-- Article main content -->
		<article class="col-xs-12 maincontent">
			<header class="page-header">
				<h1 class="page-title">${offerLot}</h1>
			</header>

			<div class="col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
				<div class="panel panel-default">
					<div class="panel-body">
						<h3 class="thin text-center">${lot}</h3>
						<hr>

						<form role="form" method="post" action="javascript:void(null);"
							onsubmit="addLot()">
							<input type="hidden" id="add-lot-is-client" value="true" /> <input
								type="hidden" id="add-lot-image"
								value="/AuctionHouse/data/common/images/no_photo.png" />
							<div class="top-margin">
								<label>${lotName}</label> <input type="text"
									class="form-control" id="add-lot-lot-name">
							</div>
							<div class="top-margin">
								<label>${category}</label> <input type="text"
									class="form-control" id="add-lot-lot-type">
							</div>
							<div class="top-margin">
								<label>${currentPrice}</label> <input type="text"
									class="form-control" id="add-lot-current-price">
							</div>
							<div class="top-margin">
								<label>${description}</label> <input type="text"
									class="form-control" id="add-lot-description">
							</div>
							<div class="val_error" id="error-add-auction-message">
								<b id="messageErr"></b>
							</div>

							<hr>

							<c:set var="user" value="${sessionScope.user}" />
							<c:if test="${not empty admin}">
								<div class="row">
									<div class="col-lg-8"></div>
									<div class="col-lg-4 text-right">
										<button class="btn btn-action" type="submit">${create}</button>
									</div>
								</div>
							</c:if>
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