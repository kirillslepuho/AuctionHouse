<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@include file="../templates/header.jsp"%>

<fmt:message bundle="${loc}" key="local.editLot" var="editLot" />
<fmt:message bundle="${loc}" key="local.lot" var="lotInfo" />
<fmt:message bundle="${loc}" key="local.lotName" var="lotName" />
<fmt:message bundle="${loc}" key="local.category" var="category" />
<fmt:message bundle="${loc}" key="local.currentPrice" var="currentPrice" />
<fmt:message bundle="${loc}" key="local.description" var="description" />
<fmt:message bundle="${loc}" key="local.image" var="image" />
<fmt:message bundle="${loc}" key="local.edit" var="edit" />
<fmt:message bundle="${loc}" key="local.blitzBet" var="blitzBet" />
<fmt:message bundle="${loc}" key="local.blitzPrice" var="blitzPrice" />

<c:set var="user" value="${sessionScope.user}" />
<c:set var="admin" value="${sessionScope.admin}" />

<c:if test="${empty admin}">
	<c:redirect url="/Controller?command=go_to_main_page" />
</c:if>
<c:if test="${admin eq false}">
	<c:redirect url="/Controller?command=go_to_main_page" />
</c:if>


<c:set var="lot" value="${requestScope.lot}" />
<!-- container -->
<div class="container">

	<div class="row">

		<!-- Article main content -->
		<article class="col-xs-12 maincontent">
			<header class="page-header">
				<h1 class="page-title">${editLot}</h1>
			</header>

			<div class="col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
				<div class="panel panel-default">
					<div class="panel-body">
						<h3 class="thin text-center">${lotInfo}</h3>
						<hr>

						<form role="form" method="post" action="javascript:void(null);"
							onsubmit="editLot()" id="edit_lot_form">
							<input type="hidden" id="edit-lot-is-client" value="false" />
							<div class="top-margin">
								<input type="hidden" id="edit-lot-change-id"
									value="${requestScope.id}" /> <label>${lotName}</label> <input
									type="text" class="form-control" id="edit-lot-lot-name"
									value="${lot.name}">
							</div>
							<div class="top-margin">
								<label>${category}</label> <input type="text"
									class="form-control" id="edit-lot-lot-type" value="${lot.type}">
							</div>
							<div class="top-margin">
								<label>${currentPrice}</label> <input type="text"
									class="form-control" id="edit-lot-current-price"
									value="${lot.currentPrice}">
							</div>
							<div class="top-margin">
								<label>${description}</label> <input type="text"
									class="form-control" id="edit-lot-description"
									value="${lot.descriprion}">
							</div>
							<div class="top-margin">
								<label>${image}</label> <input type="text" class="form-control"
									id="edit-lot-image" value="${lot.image}">
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
			input.id = "edit-lot-blitz-bet";
			input.value = ${lot.blitzBet}
			div.appendChild(input);
			var labelTwo = document.createElement("label")
			labelTwo.innerHTML="${blitzPrice}";
			div.appendChild(labelTwo);
			var inputPrice = document.createElement("input");
			inputPrice.setAttribute('class','form-control');
			inputPrice.type = "text";
			inputPrice.id = "edit-lot-blitz-price";
			inputPrice.value = ${lot.blitzPrice}
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