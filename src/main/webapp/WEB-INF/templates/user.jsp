<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="res" value="${pageContext.request.contextPath}/resources" />
<script>var startupFunctions = [];</script>

<div class="row">
    <div class="dropdown pull-left">
        <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown">
            Resolution
            <span class="caret"></span>
        </button>
        <ul class="dropdown-menu">
            <li><a href="home?resolution=1">Latest Hour</a></li>
            <li><a href="home?resolution=24">Latest Day</a></li>
            <li><a href="home?resolution=168">Latest Week</a></li>
            <li><a href="home?resolution=672">Latest Month</a></li>
            <li><a href="home?resolution=8064">Latest Year</a></li>
            <li><a href="home">100 Latest Values (Default)</a></li>
        </ul>
    </div>
    <label for="doPause">Pause</label><input type="checkbox" id="doPause">
    <a href="#" class="pull-right">Edit Modules</a>
</div>
<c:forEach items="${user.getCollectionList()}" var="collection">
    <c:if test="${collection.getModuleList().size() > 0}">
        <div class="row">
        <div class="panel panel-info">
            <div class="panel-heading">
                ${collection.getName()}
            </div>
            <div class="panel-body">
            <c:forEach items="${collection.getModuleList()}" var="module">
                <h3>${module.getName()}</h3>
                <div>
                    <jsp:include page="/WEB-INF/modules/${module.getJsp()}">             
                        <jsp:param name="moduleUser" value="${collection.getOwner().getMail()}"/>
                        <jsp:param name="moduleNodeList" value="${collection.getNodeListAsJson()}"/>
                        <jsp:param name="resolution" value="${param.resolution}"/>
                        <jsp:param name="collectionId" value="${collection.getId()}"/>
                    </jsp:include>
                </div>
            </c:forEach>
            </div>
        </div>
    </div>
    </c:if>            
</c:forEach>
<script type="text/javascript" src="${res}/js/user.js"></script>
