<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@include file="templates/header.jsp"%>

<fmt:message bundle="${loc}" key="local.info" var="info" />
<fmt:message bundle="${loc}" key="local.lotType" var="lotType" />
<fmt:message bundle="${loc}" key="local.beginDate" var="beginDate" />
<fmt:message bundle="${loc}" key="local.expirationDate"
	var="expirationDate" />
<fmt:message bundle="${loc}" key="local.type" var="type" />
<fmt:message bundle="${loc}" key="local.time" var="time" />
<fmt:message bundle="${loc}" key="local.place" var="place" />
<fmt:message bundle="${loc}" key="local.round" var="round" />
<fmt:message bundle="${loc}" key="local.currentPrice" var="currentPrice" />
<fmt:message bundle="${loc}" key="local.placeBet" var="placeBet" />
<fmt:message bundle="${loc}" key="local.blitzBet" var="blitzBet" />
<fmt:message bundle="${loc}" key="local.close" var="close" />
<fmt:message bundle="${loc}" key="local.bet" var="bet" />
<fmt:message bundle="${loc}" key="local.winner" var="winner" />

<c:set var="auction" value="${requestScope.auction}" />


<div class="container">

	<div class="row">

		<!-- Article main content -->
		<article class="col-xs-12 maincontent">
			<header class="page-header">
				<h1 class="page-title">${info}</h1>
			</header>
			<div class="col-md-10 col-md-offset-1 main-style">

				<!--Картинка и краткие сведения об аукционе-->
				<div class="col-md-10 col-md-offset-1">

					<!--Картинка-->
					<div class="col-md-4" style="height: 100%;">
						<h2>${auction.lot.name}</h2>
						<img alt="cover" src="${auction.lot.image}"
							class="img-responsive img-rounded">
					</div>

					<!--Тип лота-->
					<div class="col-md-7 col-md-offset-1">
						<div class="col-md-12 information-border-style"
							id="first-information-block">
							<div class="col-md-6">
								<p class="lead info-type">${lotType}:</p>
							</div>
							<div class="col-md-6">
								<p class="lead">${auction.lot.type}</p>
							</div>
						</div>

						<!--Место-->
						<div class="col-md-12 information-border-style">
							<div class="col-md-6">
								<p class="lead info-type">${place}:</p>
							</div>
							<div class="col-md-6">
								<p class="lead">${auction.place}</p>
							</div>
						</div>

						<!--Начало аукциона-->
						<div class="col-md-12 information-border-style">
							<div class="col-md-6">
								<p class="lead info-type">${beginDate}:</p>
							</div>
							<div class="col-md-6">
								<p class="lead">${auction.beginDate}</p>
							</div>
						</div>

						<!--Время начала-->
						<div class="col-md-12 information-border-style">
							<div class="col-md-6">
								<p class="lead info-type">${time}:</p>
							</div>
							<div class="col-md-6">
								<p class="lead">${auction.time}</p>
							</div>
						</div>

						<!--Конец-->
						<div class="col-md-12 information-border-style">
							<div class="col-md-6">
								<p class="lead info-type">${expirationDate}:</p>
							</div>
							<div class="col-md-6">
								<p class="lead">${auction.expirationDate}</p>
							</div>
						</div>

						<!--Тип аукциона-->
						<div class="col-md-12 information-border-style">
							<div class="col-md-6">
								<p class="lead info-type">${type}:</p>
							</div>
							<div class="col-md-6">
								<p class="lead">${auction.type}</p>
							</div>
						</div>

						<!--Текущий раунд-->
						<div class="col-md-12 information-border-style">
							<div class="col-md-6">
								<p class="lead info-type">${round}:</p>
							</div>
							<div class="col-md-6">
								<p class="lead">${auction.rounds}</p>
							</div>
						</div>

						<!--Текущая цена-->
						<div class="col-md-12 information-border-style">
							<div class="col-md-6">
								<p class="lead info-type">${currentPrice}:</p>
							</div>
							<div class="col-md-6">
								<p class="lead">${auction.lot.currentPrice}</p>
							</div>
						</div>

						<!--Блиц ставка-->
						<c:if test="${auction.type eq 'блиц-аукцион'}">
							<div class="col-md-12 information-border-style">
								<div class="col-md-6">
									<p class="lead info-type">${blitzBet}:</p>
								</div>
								<div class="col-md-6">
									<p class="lead">${auction.lot.blitzBet}</p>
								</div>
							</div>
						</c:if>

                        <!--Победитель-->
						<c:if test="${not empty requestScope.winner}">
							<div class="col-md-12 information-border-style">
								<div class="col-md-6">
									<p class="lead info-type">${winner}:</p>
								</div>
								<div class="col-md-6">
									<p class="lead">${requestScope.winner.name}</p>
								</div>
							</div>
						</c:if>
					</div>
				</div>
				<br>

				<!--Описание лота-->
				<div class="col-md-10 col-md-offset-1 description-style">
					<p class="lead" id="auction-description">${auction.lot.descriprion}</p>
				</div>
			</div>

            <c:set var="admin" value="${sessionScope.admin}" />
			<c:if test="${not empty admin}">
						<c:if test="${admin eq true}">
					<div class="col-lg-4 text-right">
						<a
							href="/AuctionHouse/Controller?command=go_to_auction_bets_page&auctionId=${auction.id}">
							<button class="btn btn-info" type="button">
								<c:out value="${bet}" />
							</button>
						</a>
					</div>
			</c:if>
			</c:if>
			<c:set var="user" value="${sessionScope.user}" />
			<c:if test="${not empty user}">
				<c:if test="${auction.type eq 'английский аукцион'}">
					<div class="col-lg-10 text-right">
						<button class="btn btn-action" type="button" data-toggle="modal"
							data-target="#modal-place-bet">
							<c:out value="${placeBet}" />
						</button>
					</div>
				</c:if>
			</c:if>

			<c:if test="${not empty user}">
				<c:if test="${auction.type eq 'блиц-аукцион'}">
					<div class="col-lg-10 text-right">
						<button class="btn btn-action" type="button" data-toggle="modal"
							data-target="#modal-place-blitz-bet">
							<c:out value="${placeBet}" />
						</button>
					</div>
				</c:if>
			</c:if>


			<!-- Модальное окно для английского аукциона-->
			<div class="modal" id="modal-place-bet">
				<div class="modal-dialog">
					<div class="modal-content modal-window-style">
						<div class="modal-header modal-header-style">
							<div class="modal-title">
								<div class="col-md-5 h1">${placeBet}</div>
							</div>
						</div>

						<div class="modal-body">
							<form role="form" id="place-bet-form"
								action="javascript:void(null);" onsubmit="placeEnglishBet()"
								method="post">
								<input type="hidden" id="place-english-auction-id"
									value="${auction.id}" /> <input type="hidden"
									id="place-english-lot-id" value="${auction.lot.id}" /> <input
									type="hidden" id="place-english-client-id" value="${user.id}" />
								<div class="form-group">
									<label for="place-bet"></label> <input type="text"
										class="form-control" id="place-english-bet" placeholder="Bet">
								</div>
								<div class="val_error" id="error-add-auction-message">
									<b id="messageErr"></b>
								</div>
								<button class="btn btn-default modal-button-style" type="submit">${placeBet}</button>
							</form>
						</div>
						<div class="modal-footer modal-footer-style">
							<button class="btn btn-default modal-button-style" type="button"
								data-dismiss="modal">${close}</button>
						</div>
					</div>
				</div>
			</div>

			<div class="modal" id="modal-place-blitz-bet">
				<div class="modal-dialog">
					<div class="modal-content modal-window-style">
						<div class="modal-header modal-header-style">
							<div class="modal-title">
								<div class="col-md-5 h1">${placeBet}</div>
							</div>
						</div>

						<div class="modal-body">
							<form role="form" action="javascript:void(null);"
								onsubmit="placeBlitzBet()" method="post">
								<input type="hidden" id="place-blitz-auction-id"
									value="${auction.id}" /> <input type="hidden"
									id="place-blitz-lot-id" value="${auction.lot.id}" /> <input
									type="hidden" id="place-blitz-client-id" value="${user.id}" />
								<input type="hidden" id="place-blitz-bet-lot"
									value="${auction.lot.blitzBet}" />
								<div class="form-group">
									<p>Are you sure?</p>
								</div>

								<div class="val_error" id="error-add-auction-message">
									<b id="messageErr"></b>
								</div>
								<button class="btn btn-default modal-button-style" type="submit">${placeBet}</button>
							</form>
						</div>
						<div class="modal-footer modal-footer-style">
							<button class="btn btn-default modal-button-style" type="button"
								data-dismiss="modal">${close}</button>
						</div>
					</div>
				</div>
			</div>

		</article>
	</div>
</div>

<%@include file="templates/footer.jsp"%>