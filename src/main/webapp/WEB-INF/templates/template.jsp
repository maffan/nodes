<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head> 
        <title>Nodes</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <c:set var="res" value="${pageContext.request.contextPath}/resources" />
        <link rel="stylesheet" type="text/css" href="${res}/css/normalize.css" />
        <link rel="stylesheet" type="text/css" href="${res}/css/bootstrap.css" />
        <script  type="text/javascript" src="${res}/js/jquery-1.11.3.min.js"></script>
        <link rel="icon" type="image/png" href="${res}/img/favicon.png">
    </head>
    <body class="container">
        <c:set var="root" value="${pageContext.request.contextPath}" />
        <nav class="navbar navbar-default" role="navigation">
            <div class="navbar-header">
                <a class="navbar-brand" href="/">NODES</a>
            </div>
        </nav>   
        <!-- Insert the dynamic content -->
        <div class="panel panel-default">
            <div class="panel-body"><jsp:include page="${param.partial}.jsp" /></div>
        </div>
    </body>
</html>

