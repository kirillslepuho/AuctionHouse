<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@include file="../templates/header.jsp"%>

<fmt:message bundle="${loc}" key="local.addLot" var="addLot" />
<fmt:message bundle="${loc}" key="local.lot" var="lot" />
<fmt:message bundle="${loc}" key="local.lotName" var="lotName" />
<fmt:message bundle="${loc}" key="local.category" var="category" />
<fmt:message bundle="${loc}" key="local.currentPrice" var="currentPrice" />
<fmt:message bundle="${loc}" key="local.description" var="description" />
<fmt:message bundle="${loc}" key="local.image" var="image" />
<fmt:message bundle="${loc}" key="local.create" var="create" />
<fmt:message bundle="${loc}" key="local.blitzBet" var="blitzBet" />
<fmt:message bundle="${loc}" key="local.blitzPrice" var="blitzPrice" />

<c:set var="user" value="${sessionScope.user}" />
<c:set var="admin" value="${sessionScope.admin}" />


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

						<form role="form" method="post" action="javascript:void(null);"
							onsubmit="addLot()" id="add_lot_form">
							<input type="hidden" id="add-lot-is-client" value="false" />
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
							<div class="top-margin">
								<label>${image}</label> <input type="text" class="form-control"
									id="add-lot-image">
							</div>

							<div id="blitz" class="top-margin"></div>
							<button class="btn btn-small" id="add" type="button">Add</button>
							<div class="val_error" id="error-add-auction-message">
								<b id="messageErr"></b>
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
<script type="text/javascript">
add.onclick = function() {
	var div = document.createElement("div");
	div.setAttribute('class', 'top-margin');
	var label = document.createElement("label")
	label.innerHTML="${blitzBet}";
	div.appendChild(label);
	var input = document.createElement("input");
	input.setAttribute('class','form-control');
	input.type = "text";
	input.id = "add-lot-blitz-bet";
	div.appendChild(input);
	var labelTwo = document.createElement("label")
	labelTwo.innerHTML="${blitzPrice}";
	div.appendChild(labelTwo);
	var inputPrice = document.createElement("input");
	inputPrice.setAttribute('class','form-control');
	inputPrice.type = "text";
	inputPrice.id = "add-lot-blitz-price";
	div.appendChild(inputPrice);
	var btn = document.createElement("button");
	btn.type="button";
	btn.name = "remove";
	btn.innerHTML="Remove";
	btn.setAttribute('class', 'btn btn-small');
	btn.onclick = function(){
		div.parentNode.removeChild(div);
		add.style.display = "block";
	};
	div.appendChild(btn);
	blitz.appendChild(div);
	add.style.display = "none"
		};
</script>
<%@include file="../templates/footer.jsp"%>