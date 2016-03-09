<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head> 
        <title>Nodes</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <c:set var="res" value="${pageContext.request.contextPath}/resources" />
        <script src="//code.jquery.com/jquery-1.12.0.min.js"></script>
        <script src="//code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
        <link rel="stylesheet" type="text/css" href="${res}/css/normalize.css" />
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css" integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r" crossorigin="anonymous">
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>
        <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
        <link rel="stylesheet" type="text/css" href="${res}/css/custom.css" />
        <link rel="icon" type="image/png" href="${res}/img/favicon.png">
    </head>
    <body class="container">
        <c:set var="root" value="${pageContext.request.contextPath}" />
        <nav class="navbar navbar-default" role="navigation">
            <div class="container-fluid">
                <div class="navbar-header">
                    <a class="navbar-brand" href="./">NODES</a>
                </div>
                <c:if test="${user != null}">
                    <ul class="nav navbar-nav navbar-right">
                        <li><a href="${root}/">Start</a></li>
                        <li><a href="${root}/modules">Modules</a></li>
                        <li><a href="${root}/nodes">Nodes</a></li>
                        <li><a href="${root}/settings">Settings</a></li>
                        <li><a href="${root}/logout">Logout</a></li>
                    </ul>
                </c:if>
            </div>
        </nav>   
        <!-- Insert the dynamic content -->
        <div class="panel panel-default">
            <div class="panel-body"><jsp:include page="${param.partial}.jsp" /></div>
        </div>
    </body>
</html>

