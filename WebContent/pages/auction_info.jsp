<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="templates/header.jsp"%>


<div class="col-md-10 col-md-offset-1 main-style">
    <c:set var="auctionInfo" value="${requestScope.auction}" />
    <!--Картинка и краткие сведениея о фильме-->
    <div class="col-md-10 col-md-offset-1">

        <!--Картинка-->
        <div class="col-md-4" style="height: 100%;">
            <h2>${filmInfo.name}</h2>
            <img alt="cover" src="${filmInfo.image}" class="img-responsive img-rounded">
            <!-- Приобретение премиума -->
            <c:if test="${isPremium eq true}">
                <a href="#" id="premium_button" type="button" class="btn btn-success btn-lg btn-block">Перейти к просмотру.</a>
            </c:if>
            <c:if test="${isPremium eq false}">
                <a href="/main?command=SET_PREMIUM&premium=true" id="premium_button" type="button" class="btn btn-success btn-lg btn-block">Приобрести премиум.</a>
            </c:if>

        </div>



        <!--Год выпуска-->
        <div class="col-md-7 col-md-offset-1">

            <div class="col-md-12 information-border-style" id="first-information-block">
                <div class="col-md-6">
                    <p class="lead info-type">
                        ${releaseYear} :
                    </p>
                </div>
                <div class="col-md-6">
                    <p class="lead">
                        ${filmInfo.releaseDate}
                    </p>
                </div>
            </div>

            <!--Страна-->
            <div class="col-md-12 information-border-style">
                <div class="col-md-6">
                    <p class="lead info-type">
                        ${country} :
                    </p>
                </div>
                <div class="col-md-6">
                    <p class="lead">
                        ${filmInfo.country}
                    </p>
                </div>
            </div>

            <!--Режиссер-->
            <div class="col-md-12 information-border-style">
                <div class="col-md-6">
                    <p class="lead info-type">
                        ${producer} :
                    </p>
                </div>
                <div class="col-md-6">
                    <p class="lead">
                        ${filmInfo.producer}
                    </p>
                </div>
            </div>

            <!--Бюджет-->
            <div class="col-md-12 information-border-style">
                <div class="col-md-6">
                    <p class="lead info-type">
                        ${budget} :
                    </p>
                </div>
                <div class="col-md-6">
                    <p class="lead">
                        $ ${filmInfo.budget}
                    </p>
                </div>
            </div>

            <!--Сборы-->
            <div class="col-md-12 information-border-style">
                <div class="col-md-6">
                    <p class="lead info-type">
                        ${boxOffice} :
                    </p>
                </div>
                <div class="col-md-6">
                    <p class="lead">
                        $ ${filmInfo.boxOffice}
                    </p>
                </div>
            </div>

            <!--Возрастное ограничение-->
            <div class="col-md-12 information-border-style">
                <div class="col-md-6">
                    <p class="lead info-type">
                        ${ageRestriction} :
                    </p>
                </div>
                <div class="col-md-6">
                    <p class="lead">
                        ${filmInfo.ageRestriction.minimalAge}+
                    </p>
                </div>
            </div>

        </div>

    </div>

    <!--Описание фильма-->
    <div class="col-md-10 col-md-offset-1 description-style">
        <p class="lead" id="film-description">
            ${filmInfo.description}
        </p>
    </div>

    <!--Оценка фильма-->
    <div class="col-md-10 col-md-offset-1 mark-wrapper">
        <div class="col-md-7">
            <p class="lead">
                <i>${averageMark} : </i><i id="mark-style">${filmInfo.averageMark}</i>
            </p>
        </div>
        <div class="col-md-6">
            <div class="mark-bar">
                <div class="mark-status" style="width: ${filmInfo.averageMark * 10}%;">
                </div>
            </div>
        </div>
    </div>

    <!--Трейлер фильма-->
    <div class="col-md-6 col-md-offset-3 trailer-class">
        <video width="100%" height="100%" controls class="embed-responsive-item">
            <source src="${filmInfo.trailer}" type="video/mp4">
            ${videoNotSupportMessage}
        </video>
    </div>

<%@include file="templates/footer.jsp"%>