																																																								<%@ page language="java" contentType="text/html;charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="css/style.css" media="screen" type="text/css" />
<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,700' rel='stylesheet' type='text/css'>
<title>Authorization</title>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="localization.local" var="loc"/>
<fmt:message bundle="${loc}" key="local.authorization" var="authorization"/>
<fmt:message bundle="${loc}" key="local.log" var="log"/>
<fmt:message bundle="${loc}" key="local.login" var="login"/>
<fmt:message bundle="${loc}" key="local.locbutton.name.ru" var="ru_button"/>
<fmt:message bundle="${loc}" key="local.locbutton.name.en" var="en_button"/>
</head>

<body>
 <div id="login-form">
      <h1><c:out value = "${authorization}"/></h1>
        <fieldset>
            <form action="Controller" method="post">
             <input type="hidden" name="command" value = "sign_in" > 
                <input type="email" name = "login" value='<c:out value = "${login}"/>' onBlur="if(this.value=='')this.value='<c:out value = "${login}"/>'" onFocus="if(this.value=='<c:out value = "${login}"/>')this.value='' "> 
                <input type="password" name = "password" value="Password" onBlur="if(this.value=='')this.value='Password'" onFocus="if(this.value=='Password')this.value='' "> 
                <input type="submit" value='<c:out value = "${log}"/>'>                
            </form>
  
            <form action="Controller" method="post">
             <input type="hidden" name="command" value = "localization" />
               <input type="hidden" name ="local" value = "ru"/>
                <input type="submit" value="${ru_button}"/> <br/>                
            </form>
            <form action="Controller" method="post"> 
                <input type="hidden" name="command" value = "localization" /> 
                <input type="hidden" name ="local" value = "en"/>
                <input type="submit" value="${en_button}"/> <br/>             
            </form>
        </fieldset>
    </div> 
</body>
</html>